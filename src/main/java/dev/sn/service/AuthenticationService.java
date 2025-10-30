package dev.sn.service;

import dev.sn.dtos.LoginUserDto;
import dev.sn.dtos.RegisterUserDto;
import dev.sn.entities.User1;
import dev.sn.entities.enums.Role;
import dev.sn.repositories.User1Repository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final User1Repository user1Repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            User1Repository user1Repository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.user1Repository = user1Repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User1 signup(RegisterUserDto input) {
        User1 user = User1.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(Role.SERVEUR)
                .build();

        return user1Repository.save(user);
    }



    public User1 authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return user1Repository.findByEmail(input.getEmail())
                .orElseThrow();
    }


}
