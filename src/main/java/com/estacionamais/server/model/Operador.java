package com.estacionamais.server.model;

import com.estacionamais.server.model.entities.Usuario;
import java.security.NoSuchAlgorithmException;

public class Operador extends Usuario {
    public Operador(String nome, String email, String senha) throws NoSuchAlgorithmException {}

    public void registrarEntrada() {}

    public void registrarSaida() {}

    public void registrarPagamento() {}

    // public boolean conferirVaga(int numeroVaga) {}
}
