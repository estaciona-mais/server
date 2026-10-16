package com.estacionamais.server.repository;

import java.util.Optional;

import com.estacionamais.server.model.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    public Optional<Veiculo> findByPlaca(String placa);
}
