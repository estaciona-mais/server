package com.estacionamais.server.service;

import com.estacionamais.server.dto.google.GoogleLoginRequest;
import com.estacionamais.server.dto.usuario.LoginRequest;
import com.estacionamais.server.dto.usuario.LoginResponse;
import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.security.DadosGoogle;
import com.estacionamais.server.security.GoogleTokenService;
import com.estacionamais.server.security.JwtService;
import com.estacionamais.server.security.UsuarioAutenticado;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final GoogleAuthService googleAuthService;
    private final GoogleTokenService googleTokenService;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            GoogleAuthService googleAuthService,
            GoogleTokenService googleTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.googleAuthService = googleAuthService;
        this.googleTokenService = googleTokenService;
    }

    public LoginResponse login(LoginRequest loginRequest) {
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

    public LoginResponse loginGoogle(GoogleLoginRequest googleLoginRequest) {
        DadosGoogle dados = this.googleTokenService.validar(googleLoginRequest.credential());
        Usuario usuario = this.googleAuthService.entrarOuCadastrar(dados);
        String token = this.jwtService.gerarToken(new UsuarioAutenticado(usuario));
        return new LoginResponse(token, usuario.getNome(), usuario.getPapel().name());
    }
}
