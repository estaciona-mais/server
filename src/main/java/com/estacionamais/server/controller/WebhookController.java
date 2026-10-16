package com.estacionamais.server.controller;

import com.estacionamais.server.service.*;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {
    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

    private final StripeSincronizacaoService stripeSincronizacaoService;
    private final String secretWebhook;

    public WebhookController(
            StripeSincronizacaoService stripeSincronizacaoService,
            @Value("${stripe.webhook-secret}") String secretWebhook) {
        this.stripeSincronizacaoService = stripeSincronizacaoService;
        this.secretWebhook = secretWebhook;
    }

    @PostMapping("/stripe")
    public ResponseEntity<Void> receber(@RequestBody String payload,
            @RequestHeader("Stripe-Signature") String assinatura) {
        Event evento;
        try {
            evento = Webhook.constructEvent(payload, assinatura, this.secretWebhook);
        } catch (SignatureVerificationException e) {
            log.error("Erro ao validar assinatura do webhook.", e);
            return ResponseEntity.badRequest().build();
        }

        this.stripeSincronizacaoService.processar(evento);
        return ResponseEntity.ok().build();
    }
}
