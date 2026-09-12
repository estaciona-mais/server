package com.estacionamais.server.repository;

import com.estacionamais.server.model.entities.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoRepository extends JpaRepository<Plano, Long> {}
