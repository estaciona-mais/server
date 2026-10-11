package com.estacionamais.server.repository;

import java.util.Optional;

import com.estacionamais.server.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Optional<Cliente> findByUsuarioNome(String nome);

    public Optional<Cliente> findByCpf(String cpf);
}
