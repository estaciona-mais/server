package com.estacionamais.server.controller;

import com.estacionamais.server.dto.google.*;
import com.estacionamais.server.dto.usuario.*;
import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.security.DadosGoogle;
import com.estacionamais.server.security.GoogleTokenService;
import com.estacionamais.server.security.JwtService;
import com.estacionamais.server.security.UsuarioAutenticado;
import com.estacionamais.server.service.GoogleAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final GoogleAuthService googleAuthService;
    private final GoogleTokenService googleTokenService;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            GoogleAuthService googleAuthService,
            GoogleTokenService googleTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.googleAuthService = googleAuthService;
        this.googleTokenService = googleTokenService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha()));
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

    @PostMapping("/google")
    public LoginResponse loginGoogle(@RequestBody GoogleLoginRequest googleLoginRequest) {
        DadosGoogle dados = this.googleTokenService.validar(googleLoginRequest.credential());
        Usuario usuario = this.googleAuthService.entrarOuCadastrar(dados);
        String token = this.jwtService.gerarToken(new UsuarioAutenticado(usuario));
        return new LoginResponse(token, usuario.getNome(), usuario.getPapel().name());
    }
}
