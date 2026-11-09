package com.estacionamais.server.dto.vaga;

import com.estacionamais.server.model.enums.TipoVeiculo;

public record OcupacaoVagas(TipoVeiculo tipo, Integer total, Integer disponiveis, Integer ocupadas) {}
