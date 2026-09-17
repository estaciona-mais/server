package com.estacionamais.server.dto;

import com.estacionamais.server.model.enums.Papel;

public record FichaUsuario(Long id, String nome, String email, String senhaHash, Papel papel) {}
