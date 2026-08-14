package com.example.project5.entity;

import com.example.project5.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Setter
@Getter
public class User extends BaseEntity implements UserDetails {
    @Column(nullable = false, unique = true, length = 30)
    private String username;
    @Column(nullable = false, length = 120)
    private String password;
    @Column(nullable = false,unique = true, length = 60)
    private String email;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.name()))
                .toList();
    }
}
