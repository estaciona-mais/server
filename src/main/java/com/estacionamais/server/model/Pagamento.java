package com.estacionamais.server.model;

import com.estacionamais.server.core.Config;
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
    private final LocalDateTime data;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private final Config.MetodoPagamento metodoPagamento;

    public Pagamento(Plano plano, Cliente cliente, LocalDateTime data, Config.MetodoPagamento metodoPagamento)
            throws IllegalArgumentException {
        if (plano == null) {
            throw new IllegalArgumentException("Um plano deve ser informado para criar um pagamento.");
        } else if (cliente == null) {
            throw new IllegalArgumentException("Um cliente deve ser informado para criar um pagamento.");
        } else if (data == null) {
            throw new IllegalArgumentException("Uma data deve ser informado para criar um pagamento.");
        } else if (metodoPagamento == null) {
            throw new IllegalArgumentException("Um método de pagamento deve ser informado para criar um pagamento.");
        }

        this.plano = plano;
        this.cliente = cliente;
        this.data = data;
        this.metodoPagamento = metodoPagamento;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public Config.MetodoPagamento getMetodoPagamento() {
        return this.metodoPagamento;
    }
}
