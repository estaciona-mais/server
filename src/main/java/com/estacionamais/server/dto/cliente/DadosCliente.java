package com.estacionamais.server.dto.cliente;

import java.time.OffsetDateTime;

public record DadosCliente(Long id, String nome, String email, OffsetDateTime criadoEm, String aviso) {}
