package com.estacionamais.server.service;

import com.estacionamais.server.dto.stripe.CheckoutResponse;
import com.estacionamais.server.model.entities.Cliente;
import com.estacionamais.server.model.entities.Plano;
import com.estacionamais.server.model.enums.StatusAssinatura;
import com.estacionamais.server.repository.AssinaturaRepository;
import com.estacionamais.server.repository.ClienteRepository;
import com.estacionamais.server.repository.PlanoRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {
    private final ClienteRepository clienteRepository;
    private final PlanoRepository planoRepository;
    private final AssinaturaRepository assinaturaRepository;
    private final String frontUrl;

    public CheckoutService(
            ClienteRepository clienteRepository,
            PlanoRepository planoRepository,
            AssinaturaRepository assinaturaRepository,
            @Value("${app.front-url}") String frontUrl) {
        this.clienteRepository = clienteRepository;
        this.planoRepository = planoRepository;
        this.assinaturaRepository = assinaturaRepository;
        this.frontUrl = frontUrl;
    }

    public CheckoutResponse criarSessao(Long clienteId, Long planoId) {
        Cliente cliente = this.clienteRepository.findById(clienteId).orElseThrow();
        Plano plano = this.planoRepository.findById(planoId).orElseThrow();

        String urlSucesso = frontUrl + "/assinatura/sucesso?session_id={CHECKOUT_SESSION_ID}";
        String urlCancelada = frontUrl + "/assinatura/cancelada";

        boolean jaAssina = this.assinaturaRepository.existsByClienteIdAndStatusInAndValidadeAfter(
                clienteId, List.of(StatusAssinatura.ATIVA), OffsetDateTime.now().toLocalDate());

        if (jaAssina) {
            return new CheckoutResponse(urlCancelada, "Você já possui uma assinatura ativa.", 400);
        }

        try {
            String customerId = this.obterOuCriarCustomer(cliente);

            SessionCreateParams parametros = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                    .setCustomer(customerId)
                    .addLineItem(SessionCreateParams.LineItem.builder()
                            .setPrice(plano.getStripePriceId())
                            .setQuantity(1L)
                            .build())
                    .setSuccessUrl(urlSucesso)
                    .setCancelUrl(urlCancelada)
                    .setClientReferenceId(cliente.getId().toString())
                    .build();

            Session sessao = Session.create(parametros);
            return new CheckoutResponse(sessao.getUrl(), "", 201);
        } catch (StripeException e) {
            return new CheckoutResponse(
                    urlCancelada, "Ocorreu um erro durante o pagamento. Por favor, tente novamente.", 400);
        }
    }

    /// Busca um usuário já existente; caso contrário, registra um novo seguindo o princípio da idempotência.
    private String obterOuCriarCustomer(Cliente cliente) throws StripeException {
        if (cliente.getStripeCustomerId() != null
                && !cliente.getStripeCustomerId().isBlank()) {
            return cliente.getStripeCustomerId();
        }

        CustomerCreateParams parametros = CustomerCreateParams.builder()
                .setEmail(cliente.getUsuario().getEmail())
                .setName(cliente.getUsuario().getNome())
                .putMetadata("clienteId", cliente.getId().toString())
                .build();

        Customer customer = Customer.create(parametros);

        cliente.setStripeCustomerId(customer.getId());
        this.clienteRepository.save(cliente);
        return customer.getId();
    }
}
