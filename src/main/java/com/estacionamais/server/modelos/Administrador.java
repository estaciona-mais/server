package com.estacionamais.modelos;

import server.core.Utils;

public class Administrador extends Usuario {
    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    // public String getUsuario(Usuario usuario) {}
    public String getUsuario(String nomeUsuario) {}

    public String getRelatorio() {}

    public static void cadastrarUsuario(String nome, String email, String senha) {
        Utils.conferirCredenciais(nome, email, senha);
        // TODO: registrar um usuário no db
    }
}
