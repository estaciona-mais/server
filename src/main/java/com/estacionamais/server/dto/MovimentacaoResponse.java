package com.estacionamais.server.dto;

import java.time.OffsetDateTime;

public record MovimentacaoResponse(String placaVeiculo, String codigoVaga, OffsetDateTime dataEntrada,
        OffsetDateTime dataSaida) {
}
