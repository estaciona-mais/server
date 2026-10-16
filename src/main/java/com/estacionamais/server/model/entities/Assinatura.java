package com.estacionamais.server.model.entities;

import com.estacionamais.server.model.enums.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "assinatura")
public class Assinatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plano_id", nullable = false)
    private Plano plano;

    @Column(nullable = false)
    private OffsetDateTime emissao;

    @Column(nullable = false)
    private LocalDate validade;

    @Column
    private OffsetDateTime atualizadaEm;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private StatusAssinatura status;

    @Column(nullable = false, unique = true, length = 60)
    private String stripeSubscriptionId;

    public Assinatura() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public OffsetDateTime getEmissao() {
        return emissao;
    }

    public void setEmissao(OffsetDateTime emissao) {
        this.emissao = emissao;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public OffsetDateTime getAtualizadaEm() {
        return atualizadaEm;
    }

    public void setAtualizadaEm(OffsetDateTime atualizadaEm) {
        this.atualizadaEm = atualizadaEm;
    }

    public StatusAssinatura getStatus() {
        return status;
    }

    public void setStatus(StatusAssinatura status) {
        this.status = status;
    }

    public String getStripeSubscriptionId() {
        return stripeSubscriptionId;
    }

    public void setStripeSubscriptionId(String stripeCustomerId) {
        this.stripeSubscriptionId = stripeCustomerId;
    }

    public boolean estaVigente() {
        boolean statusPermiteAcesso = this.status == StatusAssinatura.ATIVA;
        boolean periodoValido = this.validade != null
                && OffsetDateTime.of(this.validade, LocalTime.MIN, ZoneOffset.UTC)
                        .isAfter(OffsetDateTime.now());
        return statusPermiteAcesso && periodoValido;
    }
}
