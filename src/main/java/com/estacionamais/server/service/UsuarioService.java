package com.estacionamais.server.service;

import com.estacionamais.server.dto.usuario.*;
import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.model.enums.Papel;
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

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            GoogleTokenService googleTokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.googleTokenService = googleTokenService;
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

    public FichaUsuario cadastrar(UsuarioCadastro usuarioCadastro) throws IllegalArgumentException {
        if (usuarioRepository.existsByEmail(usuarioCadastro.email())) {
            throw new IllegalArgumentException("Usuário já cadastrado.");
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

        Usuario novoUsuario = usuarioRepository.save(usuario);
        return new FichaUsuario(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail());
    }

    public BuscaUsuarioGoogleResponse buscarUsuarioGoogle(String credential) {
        DadosGoogle dados = this.googleTokenService.validar(credential);
        boolean contemProviderId = this.usuarioRepository.existsByProviderId(dados.providerId());
        // boolean contemVeiculosVinculados =
        return new BuscaUsuarioGoogleResponse(contemProviderId);
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
