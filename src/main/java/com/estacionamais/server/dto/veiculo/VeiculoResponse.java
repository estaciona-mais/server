package com.estacionamais.server.dto.veiculo;

import com.estacionamais.server.model.enums.*;

public record VeiculoResponse(
        Long clienteId,
        String nomeCliente,
        String placa,
        String fabricante,
        String modelo,
        Integer ano,
        CorVeiculo cor,
        TipoVeiculo tipo,
        String observacao) {}
