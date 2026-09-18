package com.estacionamais.server.service;

import com.estacionamais.server.dto.FichaUsuario;
import com.estacionamais.server.dto.UsuarioCadastro;
import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.model.enums.Provider;
import com.estacionamais.server.model.enums.Papel;
import com.estacionamais.server.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<FichaUsuario> listarTodos() {
        return this.usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(this::paraDto)
                .toList();
    }

    private FichaUsuario paraDto(Usuario usuario) {
        return new FichaUsuario(
                usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenhaHash(), usuario.getPapel());
    }

    public Optional<FichaUsuario> buscarPorId(Long id) {
        return listarTodos().stream().filter(usuario -> usuario.id().equals(id)).findFirst();
    }

    public void cadastrar(UsuarioCadastro usuarioCadastro) throws IllegalArgumentException {
        if (usuarioRepository.existsByEmail(usuarioCadastro.email())) {
            throw new IllegalArgumentException("Usuário já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioCadastro.nome());
        usuario.setEmail(usuarioCadastro.email());
        usuario.setSenhaHash(passwordEncoder.encode(usuarioCadastro.senha()));
        usuario.setPapel(Papel.CLIENTE);
        usuario.setProvider(Provider.LOCAL);

        usuarioRepository.save(usuario);
    }
}
