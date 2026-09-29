package com.estacionamais.server.service;

import com.estacionamais.server.dto.ResumoAssinatura;
import com.estacionamais.server.model.entities.Assinatura;
import com.estacionamais.server.repository.AssinaturaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AssinaturaService {
    private AssinaturaRepository pagamentoRepository;

    public AssinaturaService(AssinaturaRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<ResumoAssinatura> listarTodos() {
        return this.pagamentoRepository.findAll().stream().map(this::paraDto).toList();
    }

    private ResumoAssinatura paraDto(Assinatura assinatura) {
        return new ResumoAssinatura(
                assinatura.getId(),
                assinatura.getPlano().getTitulo().toString(),
                assinatura.getCliente().getUsuario().getNome(),
                assinatura.getEmissao(),
                assinatura.getCliente().getMetodoPagamento());
    }

    // TODO: busca por cliente ou intervalo de data|valor
}
