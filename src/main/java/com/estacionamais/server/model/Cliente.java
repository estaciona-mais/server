package com.estacionamais.server.model;

import com.estacionamais.server.core.Config;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
// @Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @OneToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "usuario_id", nullable = false)
    // private Usuario usuario;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plano_id", nullable = false)
    private Plano plano;

    @Column(nullable = false, length = 60)
    private String stripeCustomerId;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Config.StatusContrato statusContrato;

    public Cliente() {}

    public Config.StatusContrato getStatusContrato() {
        return this.statusContrato;
    }

    public void atualizarStatusContrato(Config.StatusContrato novoStatus) {
        this.statusContrato = novoStatus;
    }

    // public String[] getHistoricoAcesso() {}
}
