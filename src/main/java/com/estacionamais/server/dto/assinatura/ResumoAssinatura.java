package com.estacionamais.server.dto.assinatura;

import com.estacionamais.server.model.enums.*;
import java.time.OffsetDateTime;

public record ResumoAssinatura(
        Long id,
        String plano,
        String nomeCliente,
        OffsetDateTime emissao,
        Integer valor,
        MetodoPagamento metodoPagamento) {}
