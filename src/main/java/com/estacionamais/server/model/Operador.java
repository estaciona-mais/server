package com.estacionamais.server.model;

public class Operador extends Usuario {
    public Operador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void registrarEntrada() {}

    public void registrarSaida() {}

    public void registrarPagamento() {}

    public boolean conferirVaga(int numeroVaga) {}
}
