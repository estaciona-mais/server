package com.estacionamais.server.dto;

public record RegistroMovimentacaoRequest(
        String placaVeiculo,
        String zonaVaga,
        Integer numeroVaga) {
}
