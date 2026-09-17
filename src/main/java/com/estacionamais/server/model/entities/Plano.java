package com.estacionamais.server.model.entities;

import com.estacionamais.server.model.enums.*;
import jakarta.persistence.*;

@Entity
@Table(name = "plano")
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private PeriodoPlano titulo;

    public Plano() {}

    public Long getId() {
        return this.id;
    }

    public int getValor() {
        return this.valor;
    }

    public PeriodoPlano getTitulo() {
        return this.titulo;
    }
}
