package com.estacionamais.server.service;

import com.estacionamais.server.dto.*;
import com.estacionamais.server.dto.veiculo.*;
import com.estacionamais.server.model.entities.Cliente;
import com.estacionamais.server.model.entities.Veiculo;
import com.estacionamais.server.model.enums.CorVeiculo;
import com.estacionamais.server.model.enums.TipoVeiculo;
import com.estacionamais.server.repository.ClienteRepository;
import com.estacionamais.server.repository.VeiculoRepository;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<VeiculoResponse> listarTodos() {
        return this.veiculoRepository.findAll().stream().map(this::paraDto).toList();
    }

    public List<CorVeiculo> listarCores() {
        return List.of(CorVeiculo.values());
    }

    public List<TipoVeiculo> listarTipos() {
        return List.of(TipoVeiculo.values());
    }

    public VeiculoResponse paraDto(Veiculo veiculo) {
        return new VeiculoResponse(
                veiculo.getId(),
                veiculo.getCliente().getUsuario().getNome(),
                veiculo.getPlaca(),
                veiculo.getFabricante(),
                veiculo.getModelo(),
                veiculo.getAno(),
                veiculo.getCor(),
                veiculo.getTipo(),
                veiculo.getObservacao());
    }

    public List<VeiculoResponse> buscar(VeiculoRequest req) {
        List<VeiculoResponse> itens = new ArrayList<>();

        return itens;
    }

    // AVISO: o veículo deve ser armazenado APÓS o cadastro do cliente.
    public CadastroResponse cadastrar(VeiculoRequest req) {
        if (veiculoRepository.existsByPlaca(req.placa())) {
            return new CadastroResponse("Veículo já registrado.", 409);
        }

        Cliente cliente = clienteRepository.findById(req.clienteId()).orElseThrow();

        Veiculo veiculo = new Veiculo();
        veiculo.setCliente(cliente);
        veiculo.setPlaca(req.placa());
        veiculo.setFabricante(req.fabricante());
        veiculo.setModelo(req.modelo());
        veiculo.setAno(req.ano());
        veiculo.setTipo(req.tipo());
        veiculo.setCor(req.cor());
        veiculo.setObservacao(req.observacao());

        this.veiculoRepository.save(veiculo);
        return new CadastroResponse("", 201);
    }
}
