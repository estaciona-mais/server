package com.estacionamais.server.repository;

import com.estacionamais.server.model.entities.Assinatura;
import com.estacionamais.server.model.enums.StatusAssinatura;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    Optional<Assinatura> findByStripeSubscriptionId(String stripeSubscriptionId);

    List<Assinatura> findByClienteIdOrderByEmissaoDesc(Long clienteId);

    boolean existsByClienteIdAndStatusInAndValidadeAfter(
            Long clienteId, Collection<StatusAssinatura> status, LocalDate agora);
}
