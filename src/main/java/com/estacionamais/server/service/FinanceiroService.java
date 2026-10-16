package com.estacionamais.server.service;

import com.estacionamais.server.dto.assinatura.*;
import com.estacionamais.server.dto.financeiro.*;
import com.estacionamais.server.model.entities.Cliente;
import com.estacionamais.server.model.enums.StatusAssinatura;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FinanceiroService {
    private final AssinaturaService assinaturaService;
    private final ClienteService clienteService;

    public FinanceiroService(AssinaturaService assinaturaService, ClienteService clienteService) {
        this.assinaturaService = assinaturaService;
        this.clienteService = clienteService;
    }

    public RelatorioFinanceiroResponse gerarRelatorioFinanceiro(RelatorioFinanceiroRequest req)
            throws RuntimeException {
        if ((req.inicio() != null && req.fim() != null)
                && (req.inicio().isAfter(req.fim()) || req.inicio().isEqual(req.fim()))) {
            throw new RuntimeException("A data de início não pode ser maior que a data de fim.");
        }

        List<ResumoAssinatura> assinaturas = this.assinaturaService.listarTodas();
        OffsetDateTime inicio = req.inicio() == null
                ? assinaturas.getLast().emissao()
                : OffsetDateTime.of(req.inicio(), LocalTime.MIN, ZoneOffset.UTC);
        OffsetDateTime fim = req.fim() == null
                ? assinaturas.getFirst().emissao()
                : OffsetDateTime.of(req.fim(), LocalTime.MAX, ZoneOffset.UTC);

        double faturamento = this.calcularFaturamento(assinaturas, inicio, fim);

        List<Cliente> clientes = this.filtrarClientes(inicio, fim);
        int quantidadeClientes = clientes.size();
        double ticketMedio = this.calcularTicketMedio(faturamento, quantidadeClientes);

        return new RelatorioFinanceiroResponse(faturamento, quantidadeClientes, ticketMedio);
    }

    private double calcularFaturamento(List<ResumoAssinatura> assinaturas, OffsetDateTime inicio, OffsetDateTime fim) {
        return assinaturas.stream()
                        .filter(a -> this.conferirLimiteData(a.emissao(), inicio, fim))
                        .map(a -> a.valor())
                        .reduce(0, Integer::sum)
                / 100;
    }

    private List<Cliente> filtrarClientes(OffsetDateTime inicio, OffsetDateTime fim) {
        return this.assinaturaService.listarAssinaturas().stream()
                .filter(a ->
                        a.getStatus().equals(StatusAssinatura.ATIVA) && conferirLimiteData(a.getEmissao(), inicio, fim))
                .map(a -> a.getCliente())
                .toList();
    }

    private double calcularTicketMedio(double faturamento, int quantidadeClientes) {
        return quantidadeClientes == 0 ? 0 : (faturamento / quantidadeClientes);
    }

    private boolean conferirLimiteData(OffsetDateTime data, OffsetDateTime inicio, OffsetDateTime fim) {
        return ((data.isAfter(inicio) || data.isEqual(inicio)) && (data.isBefore(fim) || data.isEqual(fim)));
    }
}
