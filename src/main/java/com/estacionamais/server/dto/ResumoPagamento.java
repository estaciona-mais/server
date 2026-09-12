package com.estacionamais.server.dto;

import com.estacionamais.server.model.enums.*;
import java.time.OffsetDateTime;

public record ResumoPagamento(
        Long id, String plano, String nomeCliente, OffsetDateTime data, MetodoPagamento metodoPagamento) {}
