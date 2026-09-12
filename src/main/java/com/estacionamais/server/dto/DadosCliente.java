package com.estacionamais.server.dto;

import com.estacionamais.server.model.enums.*;

public record DadosCliente(Long id, String nome, String email, PeriodoPlano plano, StatusContrato statusContrato) {}
