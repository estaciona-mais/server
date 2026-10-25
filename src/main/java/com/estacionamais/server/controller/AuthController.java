package com.estacionamais.server.controller;

import com.estacionamais.server.dto.google.*;
import com.estacionamais.server.dto.usuario.*;
import com.estacionamais.server.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return this.authService.login(loginRequest);
    }

    @PostMapping("/google")
    public LoginResponse loginGoogle(@RequestBody GoogleLoginRequest googleLoginRequest) {
        return this.authService.loginGoogle(googleLoginRequest);
    }
}
