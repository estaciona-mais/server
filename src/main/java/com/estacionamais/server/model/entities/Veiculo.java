package com.estacionamais.server.model.entities;

import com.estacionamais.server.core.Utils;
import com.estacionamais.server.model.enums.CorVeiculo;
import com.estacionamais.server.model.enums.TipoVeiculo;

import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @JdbcType(CharJdbcType.class)
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
    private CorVeiculo cor;

    @Column
    private String observacao;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TipoVeiculo tipo;

    protected static int contagemVeiculos;

    public Veiculo() {
    }

    public Long getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public String getNomeCliente() {
        return this.getCliente().getNome();
    }

    public String getPlaca() {
        return this.placa;
    }

    public String getFabricante() {
        return this.fabricante;
    }

    public String getModelo() {
        return this.modelo;
    }

    public Integer getAno() {
        return this.ano;
    }

    public CorVeiculo getCor() {
        return this.cor;
    }

    public TipoVeiculo getTipo() {
        return this.tipo;
    }

    public String getObservacao() {
        return this.observacao;
    }

    static void conferirCampos(String placa, String modelo, CorVeiculo cor, TipoVeiculo tipo)
            throws IllegalArgumentException {
        Utils.conferirCamposString(new String[][] {
                { placa, "Placa do veículo vazia." },
                { modelo, "Modelo do veículo vazia." },
        });

        if (cor == null) {
            throw new IllegalArgumentException("A cor do veículo deve ser especificada.");
        } else if (tipo == null) {
            throw new IllegalArgumentException("O tipo do veículo deve ser especificado.");
        }
    }

    static void cadastrarVeiculo(String placa, String modelo, CorVeiculo cor, TipoVeiculo tipo)
            throws IllegalArgumentException {
        Veiculo.conferirCampos(placa, modelo, cor, tipo);

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
