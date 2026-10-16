package com.estacionamais.server.dto.google;

import jakarta.validation.constraints.NotBlank;

public record GoogleLoginRequest(
        @NotBlank(message = "O token do Google é obrigatório!")
        String credential) {}
