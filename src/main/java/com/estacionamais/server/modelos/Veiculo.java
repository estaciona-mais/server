package com.estacionamais.modelos;

import core.Config;

public abstract class Veiculo {
    protected String placa;
    protected String modelo;
    protected Config.CorVeiculo cor;
    protected Config.TipoVeiculo tipo;
    protected static int contagemVeiculos;

    protected Veiculo(String placa, String modelo, Config.CorVeiculo cor, Config.TipoVeiculo tipo)
            throws IllegalArgumentException {
        Veiculo.checkArguments(placa, modelo, cor, tipo);

        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.tipo = tipo;
        Veiculo.contagemVeiculos++;
    }

    public String getPlaca() {
        return this.placa;
    }

    public String getModelo() {
        return this.modelo;
    }

    static void checkArguments(String placa, String modelo, Config.CorVeiculo cor, Config.TipoVeiculo tipo)
            throws IllegalArgumentException {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("Placa do veículo vazia.");
        } else if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Modelo do veículo vazio.");
        } else if (cor == null) {
            throw new IllegalArgumentException("A cor do veículo deve ser especificada.");
        } else if (tipo == null) {
            throw new IllegalArgumentException("O tipo do veículo deve ser especificado.");
        }
    }

    static void cadastrarVeiculo(String placa, String modelo, Config.CorVeiculo cor, Config.TipoVeiculo tipo)
            throws IllegalArgumentException {
        Veiculo.checkArguments(placa, modelo, cor, tipo);

        // TODO: salvar veículo no database
        Veiculo.contagemVeiculos++;
    }

    static void removerVehicle(String placa) throws IllegalArgumentException {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("Placa do veículo deve ser informada para a remoção.");
        }

        // TODO: remover veículo no database
        Veiculo.contagemVeiculos--;
    }
}
