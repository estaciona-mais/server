package com.estacionamais.server.service;

import com.estacionamais.server.dto.google.GoogleLoginRequest;
import com.estacionamais.server.dto.usuario.LoginRequest;
import com.estacionamais.server.dto.usuario.LoginResponse;
import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.model.enums.Papel;
import com.estacionamais.server.repository.ClienteRepository;
import com.estacionamais.server.security.DadosGoogle;
import com.estacionamais.server.security.GoogleTokenService;
import com.estacionamais.server.security.JwtService;
import com.estacionamais.server.security.UsuarioAutenticado;

import jakarta.security.auth.message.AuthException;
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
    private final ClienteRepository clienteRepository;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            GoogleAuthService googleAuthService,
            GoogleTokenService googleTokenService,
            ClienteRepository clienteRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.googleAuthService = googleAuthService;
        this.googleTokenService = googleTokenService;
        this.clienteRepository = clienteRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha()));
            UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authentication.getPrincipal();
            String token = jwtService.gerarToken(usuarioAutenticado);

            return new LoginResponse(
                    null,
                    token,
                    usuarioAutenticado.getUsuario().getNome(),
                    usuarioAutenticado.getUsuario().getEmail(),
                    usuarioAutenticado.getUsuario().getPapel().name());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciais invalidas.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao autenticar usuário.");
        }
    }

    // TODO: o cliente atualmente pode se cadastrar pelo login, sem cadastrar um veículo
    public LoginResponse loginGoogle(GoogleLoginRequest googleLoginRequest) {
        DadosGoogle dados = this.googleTokenService.validar(googleLoginRequest.credential());
        try {
            Usuario usuarioCadastrado = this.googleAuthService.entrarOuCadastrar(dados);
            boolean estaCadastrado = this.buscarCadastroUsuario(usuarioCadastrado);
            if (!estaCadastrado) {
                throw new AuthException("Usuário não existente. Por favor, realize seu cadastro.");
            }

            String token = this.jwtService.gerarToken(new UsuarioAutenticado(usuarioCadastrado));
            return new LoginResponse(
                    null,
                    token,
                    usuarioCadastrado.getNome(),
                    usuarioCadastrado.getEmail(),
                    usuarioCadastrado.getPapel().name());
        } catch (Exception e) {
            return new LoginResponse(e.getMessage(), null, null, null, null);
        }
    }

    public boolean buscarCadastroUsuario(Usuario usuario) {
        boolean papelAdmin = usuario.getPapel().equals(Papel.ADMIN);
        boolean papelCliente = usuario.getPapel().equals(Papel.CLIENTE);
        boolean clienteExiste = this.clienteRepository.existsByUsuarioId(usuario.getId());
        return papelAdmin || (papelCliente && clienteExiste);
    }
}
