package com.estacionamais.server.dto.movimentacao;

import java.time.OffsetDateTime;

public record MovimentacaoResponse(
        String placaVeiculo, String codigoVaga, OffsetDateTime dataEntrada, OffsetDateTime dataSaida) {}
