package com.estacionamais.server.dto.usuario;

import com.estacionamais.server.model.enums.Provider;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioCadastro(
        @NotBlank String nome,
        @NotBlank @Email String email,
        String senha,
        @NotNull Provider provider,
        String providerId) {}
