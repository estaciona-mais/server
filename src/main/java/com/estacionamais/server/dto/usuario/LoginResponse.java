package com.estacionamais.server.dto.usuario;

public record LoginResponse(String mensagem, String token, String nome, String email, String papel) {}
