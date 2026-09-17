package com.estacionamais.server.service;

import com.estacionamais.server.dto.ResumoPagamento;
import com.estacionamais.server.model.entities.Pagamento;
import com.estacionamais.server.repository.PagamentoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {
    private PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<ResumoPagamento> listarTodos() {
        return this.pagamentoRepository.findAll().stream().map(this::paraDto).toList();
    }

    private ResumoPagamento paraDto(Pagamento pagamento) {
        return new ResumoPagamento(
                pagamento.getId(),
                pagamento.getPlano().getTitulo().toString(),
                pagamento.getCliente().getNome(),
                pagamento.getData(),
                pagamento.getMetodoPagamento());
    }

    // TODO: busca por cliente ou intervalo de data|valor
}
