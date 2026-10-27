package com.estacionamais.server.security;

public record DadosGoogle(String providerId, String email, boolean emailVerified, String nome, String fotoUrl) {}
