package com.estacionamais.server.dto.cliente;

import com.estacionamais.server.model.enums.Provider;
import jakarta.validation.constraints.*;

public record ClienteRequest(
        String nome, String email, String senha, @NotNull Provider provider, String providerId, String credential) {}
