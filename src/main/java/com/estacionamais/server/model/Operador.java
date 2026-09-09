package com.estacionamais.server.model;

import java.security.NoSuchAlgorithmException;

public class Operador extends Usuario {
    public Operador(String nome, String email, String senha) throws NoSuchAlgorithmException {
        super(nome, email, senha);
    }

    public void registrarEntrada() {}

    public void registrarSaida() {}

    public void registrarPagamento() {}

    // public boolean conferirVaga(int numeroVaga) {}
}
