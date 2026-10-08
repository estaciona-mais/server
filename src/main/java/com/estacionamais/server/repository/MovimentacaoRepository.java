package com.estacionamais.server.repository;

import java.time.OffsetDateTime;

import com.estacionamais.server.model.entities.Movimentacao;
import com.estacionamais.server.model.entities.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    Movimentacao findFirstByVeiculo(Veiculo veiculo);
}
