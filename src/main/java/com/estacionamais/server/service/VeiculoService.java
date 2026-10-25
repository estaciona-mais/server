package com.estacionamais.server.service;

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
    private VeiculoRepository veiculoRepository;
    private ClienteRepository clienteRepository;

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

    public Optional<VeiculoResponse> buscarPorId(Long id) {
        return listarTodos().stream().filter(v -> v.clienteId() == id).findFirst();
    }

    public List<VeiculoResponse> buscarPorNomeCliente(String nomeCliente) {
        return listarTodos().stream()
                .filter(v -> v.nomeCliente().toLowerCase().equals(nomeCliente))
                .toList();
    }

    public Optional<VeiculoResponse> buscarPorPlaca(String placa) {
        return listarTodos().stream()
                .filter(v -> v.placa().toUpperCase().equals(placa.toUpperCase()))
                .findFirst();
    }

    public List<VeiculoResponse> buscarPorFabricante(String fabricante) {
        return listarTodos().stream()
                .filter(v -> v.fabricante().toLowerCase().equals(fabricante))
                .toList();
    }

    public List<VeiculoResponse> buscarPorModelo(String modelo) {
        return listarTodos().stream()
                .filter(v -> v.modelo().toLowerCase().equals(modelo))
                .toList();
    }

    public List<VeiculoResponse> buscarPorAno(Integer ano) {
        return listarTodos().stream().filter(v -> v.ano() == ano).toList();
    }

    public List<VeiculoResponse> buscarPorCor(CorVeiculo cor) {
        return listarTodos().stream().filter(v -> v.cor().equals(cor)).toList();
    }

    public List<VeiculoResponse> buscarPorTipo(TipoVeiculo tipo) {
        return listarTodos().stream().filter(v -> v.tipo().equals(tipo)).toList();
    }

    public List<VeiculoResponse> buscarPorObservacao(String obs) {
        return listarTodos().stream()
                .filter(v -> v.observacao().toLowerCase().contains(obs))
                .toList();
    }

    // AVISO: o veículo deve ser armazenado APÓS o cadastro do usuário
    public VeiculoResponse criar(VeiculoRequest req) {
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

        Veiculo salvo = this.veiculoRepository.save(veiculo);
        return this.paraDto(salvo);
    }
}
