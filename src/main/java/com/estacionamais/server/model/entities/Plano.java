package com.estacionamais.server.model.entities;

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

    public Plano() {}

    public int getValor() {
        return this.valor;
    }

    public String getTitulo() {
        return this.titulo;
    }
}
