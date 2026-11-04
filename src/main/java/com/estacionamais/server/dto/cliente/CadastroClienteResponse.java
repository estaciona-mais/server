package com.estacionamais.server.dto.cliente;

public record CadastroClienteResponse(
        String mensagem, Integer codigoStatus, String token, Long id, String nome, String email, String papel) {}
