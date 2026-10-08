package com.estacionamais.server.repository;

import com.estacionamais.server.model.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    public Veiculo findByPlaca(String placa);
}
