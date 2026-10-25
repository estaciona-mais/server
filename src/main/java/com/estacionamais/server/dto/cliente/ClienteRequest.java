package com.estacionamais.server.dto.cliente;

import com.estacionamais.server.model.enums.Provider;
import jakarta.validation.constraints.*;

public record ClienteRequest(
        @NotBlank String nome,
        @NotBlank @Email String email,
        String senha,
        @NotNull Provider provider,
        String providerId,
        String credential) {}
