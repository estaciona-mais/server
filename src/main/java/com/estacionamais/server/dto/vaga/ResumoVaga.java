package com.estacionamais.server.dto.vaga;

import com.estacionamais.server.model.enums.TipoVeiculo;

public record ResumoVaga(Long id, Integer numero, String zona, TipoVeiculo tipo, Boolean disponivel) {}
