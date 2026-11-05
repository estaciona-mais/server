package com.estacionamais.server.service;

import com.estacionamais.server.dto.usuario.*;
import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.model.enums.Papel;
import com.estacionamais.server.repository.ClienteRepository;
import com.estacionamais.server.repository.UsuarioRepository;
import com.estacionamais.server.security.DadosGoogle;
import com.estacionamais.server.security.GoogleTokenService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final GoogleTokenService googleTokenService;
    private final AuthService authService;
    private final ClienteRepository clienteRepository;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            GoogleTokenService googleTokenService,
            AuthService authService,
            ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.googleTokenService = googleTokenService;
        this.authService = authService;
        this.clienteRepository = clienteRepository;
    }

    public List<FichaUsuario> listarTodos() {
        return this.usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(this::paraDto)
                .toList();
    }

    private FichaUsuario paraDto(Usuario usuario) {
        return new FichaUsuario(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    public Optional<FichaUsuario> buscarPorId(Long id) {
        return listarTodos().stream().filter(usuario -> usuario.id().equals(id)).findFirst();
    }

    public CadastroUsuarioResponse cadastrar(UsuarioCadastro usuarioCadastro) throws IllegalArgumentException {
        Optional<Usuario> cadastrado = usuarioRepository.findByEmail(usuarioCadastro.email());
        if (cadastrado.isPresent() && usuarioCadastrado(cadastrado.get())) {
            return new CadastroUsuarioResponse("Usuário já cadastrado.", 409, "", "", "", "");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioCadastro.nome());
        usuario.setEmail(usuarioCadastro.email());
        usuario.setSenha(
                usuarioCadastro.senha() == null || usuarioCadastro.senha().isBlank()
                        ? null
                        : this.passwordEncoder.encode(usuarioCadastro.senha()));
        usuario.setPapel(Papel.CLIENTE);
        usuario.setProvider(usuarioCadastro.provider());
        usuario.setProviderId(usuarioCadastro.providerId());

        Usuario novoUsuario = usuarioRepository.existsByEmail(usuarioCadastro.email())
                ? usuarioRepository.findByEmail(usuarioCadastro.email()).orElseThrow()
                : usuarioRepository.save(usuario);
        LoginResponse loginResponse =
                this.authService.login(new LoginRequest(novoUsuario.getEmail(), usuarioCadastro.senha()));
        String token = loginResponse.token();
        return new CadastroUsuarioResponse(
                "",
                201,
                token,
                novoUsuario.getNome(),
                novoUsuario.getEmail(),
                novoUsuario.getPapel().name());
    }

    public BuscaUsuarioGoogleResponse buscarUsuarioGoogle(String credential) {
        DadosGoogle dados = this.googleTokenService.validar(credential);

        boolean contemProviderId = this.usuarioRepository.existsByProviderId(dados.providerId());
        if (!contemProviderId) {
            return new BuscaUsuarioGoogleResponse(false);
        }

        Usuario usuario = this.usuarioRepository.findByEmail(dados.email()).orElseThrow();

        boolean usuarioValido = usuarioCadastrado(usuario);

        return new BuscaUsuarioGoogleResponse(usuarioValido);
    }

    private boolean usuarioCadastrado(Usuario usuario) {
        boolean papelAdmin = usuario.getPapel().equals(Papel.ADMIN);
        boolean papelCliente = usuario.getPapel().equals(Papel.CLIENTE);
        boolean clienteExiste = this.clienteRepository.existsByUsuarioId(usuario.getId());
        return papelAdmin || (papelCliente && clienteExiste);
    }

    // TODO: atualizar os valores no banco
    // public void atualizarNome(String novoNome) throws IllegalArgumentException {
    //     Utils.atualizarDado(novoNome, "Um nome", () -> this.nome = novoNome);
    // }

    // public void atualizarEmail(String novoEmail) throws IllegalArgumentException {
    //     Utils.atualizarDado(novoEmail, "Um e-mail", () -> this.email = novoEmail);
    // }

    // public void atualizarSenha(String novaSenha) throws IllegalArgumentException {
    //     String hashedSenha = this.passwordEncoder.encode(novaSenha);
    //     Utils.atualizarDado(novaSenha, "Uma senha", () -> this.senha = hashedSenha);
    // }
}
