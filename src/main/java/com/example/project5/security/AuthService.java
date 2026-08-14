package com.example.project5.security;

import com.example.project5.dto.ResponseAuthTokenDTO;
import com.example.project5.dto.LoginRequestDTO;
import com.example.project5.dto.RegisterRequestDTO;
import com.example.project5.entity.User;
import com.example.project5.exception.UserAlreadyExistsException;
import com.example.project5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;



    public ResponseAuthTokenDTO register(RegisterRequestDTO dto){
        if(userRepository.loadByUsername(dto.username()).isPresent()){
            throw new UserAlreadyExistsException("Пользователь уже существует!");
        }

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        userRepository.save(user);

        return new ResponseAuthTokenDTO(jwtService.generateToken(user));


    }

    public ResponseAuthTokenDTO login(LoginRequestDTO dto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        return new ResponseAuthTokenDTO(token);
    }
}
