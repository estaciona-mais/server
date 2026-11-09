package com.estacionamais.server.dto.cliente;

import com.estacionamais.server.model.enums.StatusAssinatura;
import java.time.OffsetDateTime;

public record DadosCliente(
        Long id, String nome, String email, OffsetDateTime criadoEm, String aviso, StatusAssinatura status) {}
