package com.estacionamais.server.dto;

import java.time.LocalDate;

public record RelatorioFinanceiroRequest(
        LocalDate inicio,
        LocalDate fim) {
}
