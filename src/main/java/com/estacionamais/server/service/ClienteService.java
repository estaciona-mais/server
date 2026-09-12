package com.estacionamais.server.service;

import com.estacionamais.server.dto.DadosCliente;
import com.estacionamais.server.model.entities.Cliente;
import com.estacionamais.server.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private ClienteRepository usuarioRepository;

    public ClienteService(ClienteRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<DadosCliente> listarTodos() {
        return this.usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(this::paraDto)
                .toList();
    }

    private DadosCliente paraDto(Cliente cliente) {
        return new DadosCliente(
                cliente.getId(),
                cliente.getUsuario().getNome(),
                cliente.getUsuario().getEmail(),
                cliente.getPlano().getTitulo(),
                cliente.getStatusContrato());
    }

    public Optional<DadosCliente> buscarPorId(Long id) {
        return listarTodos().stream().filter(usuario -> usuario.id().equals(id)).findFirst();
    }
}
