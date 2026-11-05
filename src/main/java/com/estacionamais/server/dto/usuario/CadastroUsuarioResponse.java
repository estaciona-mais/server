package com.estacionamais.server.dto.usuario;

public record CadastroUsuarioResponse(
        String mensagem, Integer codigoStatus, String token, String nome, String email, String papel) {}
