package com.estacionamais.server.service;

import com.estacionamais.server.dto.ClienteRequest;
import com.estacionamais.server.dto.ClienteResponse;
import com.estacionamais.server.dto.DadosCliente;
import com.estacionamais.server.model.entities.Cliente;
import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.repository.ClienteRepository;
import com.estacionamais.server.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private UsuarioRepository usuarioRepository;
    private ClienteRepository clienteRepository;

    public ClienteService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<DadosCliente> listarTodos() {
        return this.clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(this::paraDto)
                .toList();
    }

    private DadosCliente paraDto(Cliente cliente) {
        return new DadosCliente(
                cliente.getId(),
                cliente.getUsuario().getNome(),
                cliente.getUsuario().getEmail());
    }

    public Optional<DadosCliente> buscarPorId(Long id) {
        return listarTodos().stream().filter(usuario -> usuario.id().equals(id)).findFirst();
    }

    public ClienteResponse cadastrar(ClienteRequest req) {
        Usuario usuario = usuarioRepository.findById(req.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        Cliente cliente = new Cliente();
        cliente.setCpf(req.cpf());
        cliente.setDataNascimento(req.dataNascimento());
        cliente.setCriadoEm(req.criadoEm());
        cliente.setAvisos(req.avisos());
        cliente.setUsuario(usuario);

        Cliente salvo = clienteRepository.save(cliente);
        return new ClienteResponse(
                salvo.getId(),
                salvo.getUsuario().getNome(),
                salvo.getCriadoEm(),
                salvo.getAvisos());
    }
}
