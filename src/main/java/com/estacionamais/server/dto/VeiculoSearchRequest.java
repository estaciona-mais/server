package com.estacionamais.server.dto;

import com.estacionamais.server.model.enums.CorVeiculo;
import com.estacionamais.server.model.enums.TipoVeiculo;

public record VeiculoSearchRequest(
        Long clienteId,
        String placa,
        String fabricante,
        String modelo,
        Integer ano,
        CorVeiculo cor,
        TipoVeiculo tipo,
        String observacao) {
}
