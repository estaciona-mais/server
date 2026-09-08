package com.estacionamais.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "plano")
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false, length = 10)
    private String titulo;

    public Plano(String titulo, int valor) {
        this.titulo = titulo;
        this.valor = valor / 100;
    }

    public int getValor() {
        return this.valor;
    }

    public String getTitulo() {
        return this.titulo;
    }
}
