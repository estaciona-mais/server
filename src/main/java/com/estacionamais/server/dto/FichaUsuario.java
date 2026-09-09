package com.estacionamais.server.dto;

import com.estacionamais.server.core.Config;

public record FichaUsuario(Long id, String nome, String email, String senhaHash, Config.PerfilUsuario papel) {}
