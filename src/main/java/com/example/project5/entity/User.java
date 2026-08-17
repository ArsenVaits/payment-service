package com.example.project5.entity;

import com.example.project5.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    @Column(nullable = false, unique = true, length = 30)
    private String username;
    @Column(nullable = false, length = 120)
    private String password;
    @Column(nullable = false, unique = true, length = 60)
    private String email;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<BankAccount> bankAccountList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
