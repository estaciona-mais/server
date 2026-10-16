package com.estacionamais.server.dto.financeiro;

import java.time.LocalDate;

public record RelatorioFinanceiroRequest(LocalDate inicio, LocalDate fim) {}
