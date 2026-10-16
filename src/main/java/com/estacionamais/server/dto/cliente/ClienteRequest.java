package com.estacionamais.server.dto.cliente;

import com.estacionamais.server.model.enums.StatusAssinatura;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public record ClienteRequest(
        @NotBlank String stripeCustomerId,
        @NotBlank @Size(min = 14, max = 14) String cpf,
        @NotBlank LocalDate dataNascimento,
        @NotBlank StatusAssinatura statusContrato,
        @NotBlank OffsetDateTime dataAssinatura,
        @NotBlank OffsetDateTime criadoEm,
        List<String> avisos,
        @Positive Long usuarioId,
        @Positive Long planoId) {}
