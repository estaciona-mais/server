package com.estacionamais.server.dto.usuario;

public record LoginResponse(String token, String nome, String papel) {}
