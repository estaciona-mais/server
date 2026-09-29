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

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String placa;

    @Column(nullable = false)
    private String fabricante;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TipoVeiculo tipo;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CorVeiculo cor;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    protected static int contagemVeiculos;

    public Veiculo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    public CorVeiculo getCor() {
        return cor;
    }

    public void setCor(CorVeiculo cor) {
        this.cor = cor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
