package com.estacionamais.server.service;

import com.estacionamais.server.model.entities.Assinatura;
import com.estacionamais.server.model.entities.Cliente;
import com.estacionamais.server.model.entities.Plano;
import com.estacionamais.server.model.enums.StatusAssinatura;
import com.estacionamais.server.repository.AssinaturaRepository;
import com.estacionamais.server.repository.ClienteRepository;
import com.estacionamais.server.repository.PlanoRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import com.stripe.model.SubscriptionItem;
import com.stripe.model.checkout.Session;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StripeSincronizacaoService {
    private static final Logger log = LoggerFactory.getLogger(StripeSincronizacaoService.class);
    private final ClienteRepository clienteRepository;
    private final PlanoRepository planoRepository;
    private final AssinaturaRepository assinaturaRepository;

    public StripeSincronizacaoService(
            ClienteRepository clienteRepository,
            PlanoRepository planoRepository,
            AssinaturaRepository assinaturaRepository) {
        this.clienteRepository = clienteRepository;
        this.planoRepository = planoRepository;
        this.assinaturaRepository = assinaturaRepository;
    }

    public void processar(Event evento) {
        switch (evento.getType()) {
            case "checkout.session.completed" -> {
                Session sessao = (Session) extrairObjeto(evento);
                if (sessao.getSubscription() != null) {
                    sincronizar(sessao.getSubscription());
                }
            }

            case "customer.subscription.updated", "customer.subscription.deleted" -> {
                Subscription inscricao = (Subscription) extrairObjeto(evento);
                sincronizar(inscricao.getId());
            }

            default -> log.debug("Evento ignorado: {}", evento.getType());
        }
    }

    private StripeObject extrairObjeto(Event evento) {
        EventDataObjectDeserializer leitor = evento.getDataObjectDeserializer();
        return leitor.getObject().orElseGet(() -> {
            try {
                return leitor.deserializeUnsafe();
            } catch (StripeException e) {
                log.error("Erro ao deserializar objeto Stripe. ", e);
                return null;
            }
        });
    }

    private void sincronizar(String subscriptionId) {
        try {
            Subscription atual = Subscription.retrieve(subscriptionId);
            Cliente cliente = this.clienteRepository
                    .findByStripeCustomerId(atual.getCustomer())
                    .orElse(null);

            if (cliente == null) {
                log.info("Assinatura {} de cliente desconhecido, ignorada", subscriptionId);
                return;
            }

            SubscriptionItem item = atual.getItems().getData().getFirst();
            Plano plano = this.planoRepository
                    .findByStripePriceId(item.getPrice().getId())
                    .orElseThrow(() -> new RuntimeException("Plano não encontrado."));
            // Indepotência – se existe, atualize; se não existe, crie.
            Assinatura assinatura = this.assinaturaRepository
                    .findByStripeSubscriptionId(atual.getId())
                    .orElseGet(() -> {
                        Assinatura novaAssinatura = new Assinatura();
                        novaAssinatura.setStripeSubscriptionId(atual.getId());
                        novaAssinatura.setCliente(cliente);
                        return novaAssinatura;
                    });
            assinatura.setPlano(plano);
            assinatura.setStatus(StatusAssinatura.valueOf(atual.getStatus().toUpperCase()));
            assinatura.setValidade(
                    LocalDate.ofInstant(Instant.ofEpochSecond(item.getCurrentPeriodEnd()), ZoneOffset.UTC));

            this.assinaturaRepository.save(assinatura);
        } catch (StripeException e) {
            log.error("Erro ao sincronizar assinatura {}", subscriptionId, e);
        }
    }
}
