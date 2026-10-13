package com.estacionamais.server.service;

import com.estacionamais.server.dto.MovimentacaoClienteRequest;
import com.estacionamais.server.dto.MovimentacaoResponse;
import com.estacionamais.server.dto.RegistroMovimentacaoRequest;
import com.estacionamais.server.model.entities.Movimentacao;
import com.estacionamais.server.model.entities.Vaga;
import com.estacionamais.server.model.entities.Veiculo;
import com.estacionamais.server.repository.MovimentacaoRepository;
import com.estacionamais.server.repository.VagaRepository;
import com.estacionamais.server.repository.VeiculoRepository;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;
    private final VeiculoRepository veiculoRepository;
    private final VagaRepository vagaRepository;

    public MovimentacaoService(
            MovimentacaoRepository movimentacaoRepository,
            VeiculoRepository veiculoRepository,
            VagaRepository vagaRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.veiculoRepository = veiculoRepository;
        this.vagaRepository = vagaRepository;
    }

    public List<MovimentacaoResponse> listarTodas() {
        return this.movimentacaoRepository.findAll(Sort.by(Sort.Direction.DESC, "dataEntrada")).stream()
                .map(this::paraDto)
                .toList();
    }

    public MovimentacaoResponse paraDto(Movimentacao movimentacao) {
        return new MovimentacaoResponse(
                movimentacao.getVeiculo().getPlaca(),
                movimentacao.getVaga().getCodigo(),
                movimentacao.getDataEntrada(),
                movimentacao.getDataSaida());
    }

    public List<MovimentacaoResponse> listarRelatorio(Integer limite) {
        List<MovimentacaoResponse> items = this.listarTodas();
        return limite == null || limite == 0 ? items : items.subList(0, limite);
    }

    public List<MovimentacaoResponse> listarHistoricoCliente(MovimentacaoClienteRequest req) {
        return this.movimentacaoRepository.findAll(Sort.by(Sort.Direction.DESC, "dataEntrada")).stream()
                .filter(m -> m.getVeiculo().getCliente().getId().equals(req.id()))
                .map(this::paraDto)
                .toList();
    }

    public void registrarEntrada(RegistroMovimentacaoRequest req) {
        Veiculo veiculo = this.veiculoRepository.findByPlaca(req.placaVeiculo()).orElseThrow();
        Vaga vaga = this.vagaRepository.findByZonaAndNumero(req.zonaVaga(), req.numeroVaga());
        Movimentacao mov = new Movimentacao();

        mov.setVeiculo(veiculo);
        mov.setVaga(vaga);
        mov.setDataEntrada(OffsetDateTime.now());
        mov.setDataSaida(null);
        this.movimentacaoRepository.save(mov);
    }

    public void registrarSaida(RegistroMovimentacaoRequest req) {
        Veiculo veiculo = this.veiculoRepository.findByPlaca(req.placaVeiculo()).orElseThrow();
        Vaga vaga = this.vagaRepository.findByZonaAndNumero(req.zonaVaga(), req.numeroVaga());
        Movimentacao mov = this.movimentacaoRepository.findFirstByVeiculo(veiculo);

        if (mov.getVeiculo() == null) {
            mov.setVeiculo(veiculo);
        }
        if (mov.getVaga() == null) {
            mov.setVaga(vaga);
        }

        mov.setDataSaida(OffsetDateTime.now());
        this.movimentacaoRepository.save(mov);
    }
}
