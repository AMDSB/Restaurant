package dev.sn.web;

import dev.sn.dtos.LoginResponse;
import dev.sn.dtos.LoginUserDto;
import dev.sn.dtos.RegisterUserDto;
import dev.sn.entities.User1;
import dev.sn.service.AuthenticationService;
import dev.sn.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {


    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(
            JwtService jwtService,
            AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User1> register(@RequestBody RegisterUserDto registerUserDto) {
        User1 registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User1 authenticatedUser = authenticationService.authenticate(loginUserDto);

//        String jwtToken = jwtService.generateToken(authenticatedUser);

//        String jwtToken = jwtService.generateToken(Collections.emptyMap(), authenticatedUser);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
