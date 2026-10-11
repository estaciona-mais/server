package com.estacionamais.server.service;

import com.estacionamais.server.dto.ClienteRequest;
import com.estacionamais.server.dto.ClienteResponse;
import com.estacionamais.server.dto.ConsultaClienteRequest;
import com.estacionamais.server.dto.DadosCliente;
import com.estacionamais.server.model.entities.Cliente;
import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.model.entities.Veiculo;
import com.estacionamais.server.repository.ClienteRepository;
import com.estacionamais.server.repository.UsuarioRepository;
import com.estacionamais.server.repository.VeiculoRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private UsuarioRepository usuarioRepository;
    private ClienteRepository clienteRepository;
    private VeiculoRepository veiculoRepository;

    public ClienteService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository,
            VeiculoRepository veiculoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
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

    public Optional<DadosCliente> buscarCliente(ConsultaClienteRequest req) {
        Cliente cliente = this.clienteRepository.findByUsuarioNome(req.nome()).orElse(null);
        if (cliente == null) {
            cliente = this.clienteRepository.findByCpf(req.cpf()).orElse(null);
        }
        if (cliente == null) {
            Veiculo veiculo = this.veiculoRepository.findByPlaca(req.placa()).orElseThrow();
            cliente = veiculo.getCliente();
        }

        if (cliente == null) {
            throw new RuntimeException("Nome, CPF ou placa deve ser informado.");
        }

        return Optional.of(
                new DadosCliente(cliente.getId(), cliente.getUsuario().getNome(), cliente.getUsuario().getEmail()));
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
