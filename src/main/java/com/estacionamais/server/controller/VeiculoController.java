package com.estacionamais.server.controller;

import com.estacionamais.server.dto.veiculo.*;
// import com.estacionamais.server.dto.VeiculoSearchRequest;
// import com.estacionamais.server.model.enums.*;
import com.estacionamais.server.service.VeiculoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public List<VeiculoResponse> listar() {
        return this.veiculoService.listarTodos();
    }

    // @PostMapping("/busca/id/{id}")
    // public VeiculoResponse buscarPorId(@PathVariable Long id) {
    //     return this.veiculoService.buscarPorId(id).orElse(null);
    // }

    // @GetMapping("/busca/nome-cliente/{nome}")
    // public List<VeiculoResponse> buscarPorNomeCliente(@PathVariable String nomeCliente) {
    //     return this.veiculoService.buscarPorNomeCliente(nomeCliente);
    // }

    // @GetMapping("/busca/placa/{placa}")
    // public VeiculoResponse buscarPorPlaca(@PathVariable String placa) {
    //     return this.veiculoService.buscarPorPlaca(placa).orElse(null);
    // }

    // @GetMapping("/busca/fabricante/{fabricante}")
    // public List<VeiculoResponse> buscarPorFabricante(@PathVariable String fabricante) {
    //     return this.veiculoService.buscarPorFabricante(fabricante);
    // }

    // @GetMapping("/busca/modelo/{modelo}")
    // public List<VeiculoResponse> buscarPorModelo(@PathVariable String modelo) {
    //     return this.veiculoService.buscarPorModelo(modelo);
    // }

    // @GetMapping("/busca/ano/{ano}")
    // public List<VeiculoResponse> buscarPorAno(@PathVariable Integer ano) {
    //     return this.veiculoService.buscarPorAno(ano);
    // }

    // @GetMapping("/busca/cor/{cor}")
    // public List<VeiculoResponse> buscarPorCor(@PathVariable CorVeiculo cor) {
    //     return this.veiculoService.buscarPorCor(cor);
    // }

    // @GetMapping("/busca/tipo/{tipo}")
    // public List<VeiculoResponse> buscarPorTipo(@PathVariable TipoVeiculo tipo) {
    //     return this.veiculoService.buscarPorTipo(tipo);
    // }

    // @GetMapping("/busca/observacao/{obs}")
    // public List<VeiculoResponse> buscarPorObservacao(@PathVariable String obs) {
    //     return this.veiculoService.buscarPorObservacao(obs);
    // }

    // // TODO: VeiculoSearchRequest
    // @PostMapping("/busca")
    // public List<VeiculoResponse> buscar(VeiculoSearchRequest req) {
    //     Long clienteId;
    //     String placa;
    //     String fabricante;
    //     String modelo;
    //     Integer ano;
    //     CorVeiculo cor;
    //     TipoVeiculo tipo;
    //     String observacao;

    //     if (req.clienteId() != null) {
    //         clienteId = req.clienteId();
    //     }
    //     if (req.placa() != null) {
    //         placa = req.placa();
    //     }
    //     if (req.fabricante() != null) {
    //         fabricante = req.fabricante();
    //     }
    //     if (req.modelo() != null) {
    //         modelo = req.modelo();
    //     }
    //     if (req.ano() != null) {
    //         ano = req.ano();
    //     }
    //     if (req.cor() != null) {
    //         cor = req.cor();
    //     }
    //     if (req.tipo() != null) {
    //         tipo = req.tipo();
    //     }
    //     if (req.observacao() != null) {
    //         observacao = req.observacao();
    //     }

    //     return new VeiculoResponse(
    //             clienteId,
    //             placa,
    //             fabricante,
    //             modelo,
    //             ano,
    //             cor,
    //             tipo, observacao);
    // }

    @PostMapping("/cadastro")
    public ResponseEntity<VeiculoResponse> cadastrar(@RequestBody @Valid VeiculoRequest veiculoRequest) {
        VeiculoResponse criado = this.veiculoService.criar(veiculoRequest);
        return ResponseEntity.ok(criado);
    }
}
