package com.estacionamais.server.dto;

import com.estacionamais.server.model.enums.CorVeiculo;
import com.estacionamais.server.model.enums.TipoVeiculo;

import jakarta.validation.constraints.*;

public record VeiculoRequest(
        @Positive(message = "O ID do cliente deve ser positivo.") Long clienteId,
        @NotBlank @Size(min = 7, max = 7, message = "A placa deve conter exatamente 7 caracteres.") String placa,
        @NotBlank String fabricante,
        @NotBlank String modelo,
        @Positive Integer ano,
        @NotNull CorVeiculo cor,
        @NotNull TipoVeiculo tipo,
        String ovservacao) {
}
