package com.estacionamais.server.service;

import com.estacionamais.server.model.Usuario;
import com.estacionamais.server.model.Vaga;

public class Estacionamento {
    private Usuario[] usuarios;
    private Vaga[] vagas;
    private int usuariosTotais; // provavelmente usada para evitar criação excessiva de usuários
    private int vagasTotais;
}
