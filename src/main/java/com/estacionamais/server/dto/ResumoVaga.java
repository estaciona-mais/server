package com.estacionamais.server.dto;

import com.estacionamais.server.model.enums.*;
import java.time.OffsetDateTime;

public record ResumoVaga(
        Long id,
        String nomeCliente,
        String placa,
        Integer numero,
        TipoVeiculo tipo,
        OffsetDateTime dataEntrada,
        OffsetDateTime dataSaida) {}
