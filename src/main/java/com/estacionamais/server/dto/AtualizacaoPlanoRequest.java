package com.estacionamais.server.dto;

import jakarta.validation.constraints.*;

public record AtualizacaoPlanoRequest(
        @NotEmpty String titulo,
        @Positive Integer novoValor) {
}
