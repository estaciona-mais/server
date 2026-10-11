package com.estacionamais.server.dto;

public record ConsultaClienteRequest(
        String nome,
        String cpf,
        String placa) {
}
