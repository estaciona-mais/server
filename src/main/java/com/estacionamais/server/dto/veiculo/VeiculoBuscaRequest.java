package com.estacionamais.server.dto.veiculo;

import com.estacionamais.server.model.enums.CorVeiculo;
import com.estacionamais.server.model.enums.TipoVeiculo;

public record VeiculoBuscaRequest(
        Long clienteId,
        String placa,
        String fabricante,
        String modelo,
        Integer ano,
        CorVeiculo cor,
        TipoVeiculo tipo) {}
