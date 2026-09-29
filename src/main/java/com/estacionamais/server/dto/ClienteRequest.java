package com.estacionamais.server.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import com.estacionamais.server.model.enums.StatusContrato;

import jakarta.validation.constraints.*;

public record ClienteRequest(
        @NotBlank String stripeCustomerId,
        @NotBlank @Size(min = 14, max = 14) String cpf,
        @NotBlank LocalDate dataNascimento,
        @NotBlank StatusContrato statusContrato,
        @NotBlank OffsetDateTime dataAssinatura,
        @NotBlank OffsetDateTime criadoEm,
        List<String> avisos,
        @Positive Long usuarioId,
        @Positive Long planoId) {
}
