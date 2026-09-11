package com.estacionamais.server.service;

import com.estacionamais.server.dto.FichaUsuario;
import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<FichaUsuario> listarTodos() {
        return this.usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(this::paraDto)
                .toList();
    }

    private FichaUsuario paraDto(Usuario usuario) {
        return new FichaUsuario(
                usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getPapel());
    }

    public Optional<FichaUsuario> buscarPorId(Long id) {
        return listarTodos().stream().filter(usuario -> usuario.id().equals(id)).findFirst();
    }
}
