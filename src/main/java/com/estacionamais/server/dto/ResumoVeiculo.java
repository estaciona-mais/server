package com.estacionamais.server.dto;

import com.estacionamais.server.model.enums.*;

public record ResumoVeiculo(
        Long id,
        String nomeCliente,
        String placa,
        String fabricante,
        String modelo,
        Integer ano,
        CorVeiculo cor,
        TipoVeiculo tipo,
        String observacao) {}
