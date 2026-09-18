package com.estacionamais.server.repository;

import java.security.Provider;
import java.util.Optional;

import com.estacionamais.server.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Usuario> findByProviderAndProviderId(Provider provider, String providerId);
}
