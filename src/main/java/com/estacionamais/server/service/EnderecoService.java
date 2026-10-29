package com.estacionamais.server.service;

import com.estacionamais.server.dto.endereco.*;
import com.estacionamais.server.model.entities.Endereco;
import com.estacionamais.server.repository.EnderecoRepository;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    private final EnderecoRepository planoRepository;

    public EnderecoService(EnderecoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<ResumoEndereco> listarTodos() {
        return this.planoRepository.findAll().stream().map(this::paraDto).toList();
    }

    private ResumoEndereco paraDto(Endereco endereco) {
        return new ResumoEndereco(
                endereco.getId(),
                endereco.getCliente().getUsuario().getNome(),
                endereco.getUf(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento());
    }

    public Optional<ResumoEndereco> buscarPorNome(String nome) {
        return listarTodos().stream()
                .filter(endereco ->
                        nome.toLowerCase().contains(endereco.nomeCliente().toLowerCase()))
                .findFirst();
    }
}
