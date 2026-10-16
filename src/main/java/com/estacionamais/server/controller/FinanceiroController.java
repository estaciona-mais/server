package com.estacionamais.server.controller;

import com.estacionamais.server.dto.financeiro.*;
import com.estacionamais.server.service.FinanceiroService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financeiro")
public class FinanceiroController {
    private final FinanceiroService financeiroService;

    public FinanceiroController(FinanceiroService financeiroService) {
        this.financeiroService = financeiroService;
    }

    @PostMapping("/relatorio")
    public RelatorioFinanceiroResponse gerarRelatorioFinanceiro(@RequestBody RelatorioFinanceiroRequest req) {
        return this.financeiroService.gerarRelatorioFinanceiro(req);
    }
}
