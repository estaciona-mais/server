package com.estacionamais.server.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime data;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MetodoPagamento metodoPagamento;

    public Pagamento() {}

    public LocalDateTime getData() {
        return this.data;
    }

    public MetodoPagamento getMetodoPagamento() {
        return this.metodoPagamento;
    }
}
