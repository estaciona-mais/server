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

    @Column(nullable = false, unique = true, length = 60)
    private String stripePriceId;

    public Plano() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public PeriodoPlano getTitulo() {
        return titulo;
    }

    public void setTitulo(PeriodoPlano titulo) {
        this.titulo = titulo;
    }

    public String getStripePriceId() {
        return stripePriceId;
    }

    public void setStripePriceId(String stripePriceId) {
        this.stripePriceId = stripePriceId;
    }
}
