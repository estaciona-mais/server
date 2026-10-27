package com.estacionamais.server.dto.stripe;

public record CheckoutResponse(String url, String mensagem, Integer codigoStatus) {}
