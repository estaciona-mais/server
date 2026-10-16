package com.estacionamais.server.service;

import com.estacionamais.server.dto.ResumoAssinatura;
import com.estacionamais.server.model.entities.Assinatura;
import com.estacionamais.server.repository.AssinaturaRepository;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AssinaturaService {
    private AssinaturaRepository assinaturaRepository;

    public AssinaturaService(AssinaturaRepository pagamentoRepository) {
        this.assinaturaRepository = pagamentoRepository;
    }

    public List<ResumoAssinatura> listarTodas() {
        return this.assinaturaRepository
                .findAll(Sort.by(Sort.Direction.DESC, "emissao"))
                .stream()
                .map(this::paraDto)
                .toList();
    }

    public List<Assinatura> listarAssinaturas() {
        return this.assinaturaRepository.findAll(Sort.by(Sort.Direction.DESC, "emissao"));
    }

    private ResumoAssinatura paraDto(Assinatura assinatura) {
        return new ResumoAssinatura(
                assinatura.getId(),
                assinatura.getPlano().getTitulo().toString(),
                assinatura.getCliente().getUsuario().getNome(),
                assinatura.getEmissao(),
                assinatura.getPlano().getValor(),
                assinatura.getCliente().getMetodoPagamento());
    }

    // TODO: busca por cliente ou intervalo de data|valor
}
