package com.estacionamais.server.model.entities;

import com.estacionamais.server.model.enums.*;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plano_id", nullable = false)
    private Plano plano;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private OffsetDateTime data;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MetodoPagamento metodo;

    public Pagamento() {
    }

    public Long getId() {
        return this.id;
    }

    public Plano getPlano() {
        return this.plano;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public OffsetDateTime getData() {
        return this.data;
    }

    public MetodoPagamento getMetodo() {
        return this.metodo;
    }
}
