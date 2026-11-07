package com.estacionamais.server.dto.assinatura;

import java.time.OffsetDateTime;

public record ResumoAssinatura(
        Long id,
        String plano,
        String nomeCliente,
        OffsetDateTime emissao,
        Integer valor) {}
