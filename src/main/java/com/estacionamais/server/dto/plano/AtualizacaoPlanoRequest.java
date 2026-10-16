package com.estacionamais.server.dto.plano;

import jakarta.validation.constraints.*;

public record AtualizacaoPlanoRequest(
        @NotEmpty String titulo, @Positive Integer novoValor) {}
