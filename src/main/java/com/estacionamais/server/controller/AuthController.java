package com.estacionamais.server.controller;

import com.estacionamais.server.dto.LoginRequest;
import com.estacionamais.server.dto.LoginResponse;
import com.estacionamais.server.security.JwtService;
import com.estacionamais.server.security.UsuarioAutenticado;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha()));
            UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authentication.getPrincipal();
            String token = jwtService.gerarToken(usuarioAutenticado);

            return new LoginResponse(
                    token,
                    usuarioAutenticado.getUsuario().getNome(),
                    usuarioAutenticado.getUsuario().getPapel().name());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciais invalidas.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao autenticar usuário.");
        }
    }
}
