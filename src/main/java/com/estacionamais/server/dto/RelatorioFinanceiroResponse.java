package com.estacionamais.server.dto;

public record RelatorioFinanceiroResponse(
        Double faturamento,
        Integer quantidadeClientes,
        Double ticketMedio) {
}
