package com.estacionamais.servicos;

import modelos.Usuario;

public class Estacionamento {
    private Usuario[] usuarios;
    private Vaga[] vagas;
    private int usuariosTotais; // provavelmente usada para evitar criação excessiva de usuários
    private int vagasTotais;
}
