package com.estacionamais.server.repository;

import com.estacionamais.server.model.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {}
