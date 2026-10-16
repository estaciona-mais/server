package com.estacionamais.server.service;

import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.model.enums.Papel;
import com.estacionamais.server.model.enums.Provider;
import com.estacionamais.server.repository.UsuarioRepository;
import com.estacionamais.server.security.DadosGoogle;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GoogleAuthService {
    private final UsuarioRepository usuarioRepository;

    public GoogleAuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario entrarOuCadastrar(DadosGoogle dados) {
        if (!dados.emailVerified()) {
            throw new IllegalArgumentException("E-mail do Google não verificado.");
        }

        return this.usuarioRepository
                .findByProviderId(dados.id())
                .or(() -> this.vincularPorEmail(dados))
                .orElseGet(() -> this.criarConta(dados));
    }

    private Optional<Usuario> vincularPorEmail(DadosGoogle dados) {
        return this.usuarioRepository.findByEmail(dados.email()).map(u -> {
            u.setProviderId(dados.id());
            return u;
        });
    }

    private Usuario criarConta(DadosGoogle dados) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dados.email());
        usuario.setNome(dados.nome());
        usuario.setSenha(null);
        usuario.setProvider(Provider.GOOGLE);
        usuario.setProviderId(dados.id());
        usuario.setPapel(Papel.CLIENTE);
        return this.usuarioRepository.save(usuario);
    }
}
