package com.estacionamais.server.dto;

public record ResumoVaga(
        Long id,
        Integer numero,
        String zona,
        Boolean disponivel
) {}
