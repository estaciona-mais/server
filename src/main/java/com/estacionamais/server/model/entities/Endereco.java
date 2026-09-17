package com.estacionamais.server.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @JdbcType(CharJdbcType.class)
    @Column(nullable = false, length = 2)
    private String uf;

    @JdbcType(CharJdbcType.class)
    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 96)
    private String cidade;

    @Column(nullable = false, length = 96)
    private String bairro;

    @Column(nullable = false, length = 96)
    private String rua;

    @Column(nullable = false)
    private Integer numero;

    @Column(length = 32)
    private String complemento;

    public Endereco() {}

    public Long getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public String getNomeCliente() {
        return this.getCliente().getNome();
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
