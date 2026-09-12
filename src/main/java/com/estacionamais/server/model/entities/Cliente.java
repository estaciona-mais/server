package com.estacionamais.server.model.entities;

import com.estacionamais.server.model.enums.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
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
    private StatusContrato statusContrato;

    public Cliente() {}

    public Long getId() {
        return this.id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public String getNome() {
        return this.usuario.getNome();
    }

    public String getEmail() {
        return this.usuario.getEmail();
    }

    public Plano getPlano() {
        return this.plano;
    }

    // public String getStripeCustomerId() {
    //     return this.stripeCustomerId;
    // }

    // public String getCpf() {
    //     return this.cpf;
    // }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public StatusContrato getStatusContrato() {
        return this.statusContrato;
    }

    public void atualizarStatusContrato(StatusContrato novoStatus) {
        this.statusContrato = novoStatus;
    }

    // public String[] getHistoricoAcesso() {}
}
