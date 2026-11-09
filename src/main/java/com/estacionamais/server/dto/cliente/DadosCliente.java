package com.estacionamais.server.dto.cliente;

import com.estacionamais.server.model.enums.PeriodoPlano;
import com.estacionamais.server.model.enums.StatusAssinatura;
import java.time.OffsetDateTime;

public record DadosCliente(
        Long clienteId,
        String nome,
        String email,
        OffsetDateTime criadoEm,
        String aviso,
        PeriodoPlano plano,
        StatusAssinatura status) {}
