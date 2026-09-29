package com.estacionamais.server.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vaga")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String zona;

    @Column(nullable = false)
    private Boolean disponivel;

    // private static int vagasDisponiveis;
    // private static int vagasOcupadas = 0;

    public Vaga() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    // public String conferirStatus() {
    //     if (this.disponivel) {
    //         return String.format("Número: %d\nStatus: disponível", this.numero);
    //     }

    //     String tempoOcupacao =
    //             Duration.between(Instant.from(this.dataEntrada), Instant.now()).toString();
    //     return String.format(
    //             "Número: %d\nPlaca: %s\nHora de entrada: %s\nDuração: %s",
    //             this.numero, this.veiculo.getPlaca(), this.dataEntrada.toString(), tempoOcupacao);
    // }

    public boolean conferirDisponibilidade() {
        return this.disponivel;
    }

    // public void ocupar(Veiculo veiculo) {
    //     this.disponivel = false;
    //     // this.veiculo = veiculo;
    //     this.dataEntrada = OffsetDateTime.now();
    //     // TODO: nulificar a saída após a entrada de um novo veículo?
    //     // this.dataSaida = null;
    //     // Vaga.vagasDisponiveis--;
    // }

    // public void liberar() {
    //     this.disponivel = true;
    //     this.dataSaida = OffsetDateTime.now();
    //     // this.veiculo = null;
    //     // Vaga.vagasDisponiveis++;
    // }
}
