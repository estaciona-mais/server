package com.estacionamais.server.model.entities;

import com.estacionamais.server.core.Utils;
import com.estacionamais.server.model.enums.*;
import java.security.NoSuchAlgorithmException;

public class Administrador extends Usuario {
    public Administrador(String nome, String email, String senha, Papel perfil) throws NoSuchAlgorithmException {}

    // public String getUsuario(String nomeUsuario) {}

    // public String getRelatorio() {}

    public static void cadastrarUsuario(String nome, String email, String senha) {
        Utils.conferirCredenciais(nome, email, senha);
        // TODO: registrar um usuário no db
    }
}
