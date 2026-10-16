package com.estacionamais.server.dto.vaga;

import com.estacionamais.server.model.enums.TipoVeiculo;

public record OcupacaoVagas(TipoVeiculo tipo, Integer total, Integer disponíveis, Integer ocupadas) {}
