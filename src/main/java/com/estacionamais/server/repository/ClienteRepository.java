package com.estacionamais.server.repository;

import com.estacionamais.server.model.entities.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Optional<Cliente> findByUsuarioId(Long usuarioId);

    public Optional<Cliente> findByUsuarioNome(String nome);

    public Optional<Cliente> findByStripeCustomerId(String stripeCustomerId);
}
