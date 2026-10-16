package com.estacionamais.server.repository;

import com.estacionamais.server.model.entities.Plano;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
    Optional<Plano> findByTitulo(String titulo);

    Optional<Plano> findByStripePriceId(String stripePriceId);
}
