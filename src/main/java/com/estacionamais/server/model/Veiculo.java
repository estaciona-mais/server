package com.estacionamais.server.model;

import com.estacionamais.server.core.Config;
import jakarta.persistence.*;

@Entity
@Table(name = "veiculo")
public abstract class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private String placa;

    @Column(nullable = false)
    private String fabricante;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Config.CorVeiculo cor;

    @Column
    private String observacao;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Config.TipoVeiculo tipo;

    protected static int contagemVeiculos;

    protected Veiculo(Cliente cliente, String placa, String modelo, Config.CorVeiculo cor, Config.TipoVeiculo tipo)
            throws IllegalArgumentException {
        Veiculo.checkArguments(placa, modelo, cor, tipo);
        if (cliente == null) {
            throw new IllegalArgumentException("Um cliente deve ser informado para registrar um veículo.");
        }

        this.cliente = cliente;
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
