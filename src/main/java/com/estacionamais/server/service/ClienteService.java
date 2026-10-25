package com.estacionamais.server.service;

import com.estacionamais.server.dto.cliente.*;
import com.estacionamais.server.dto.usuario.FichaUsuario;
import com.estacionamais.server.dto.usuario.UsuarioCadastro;
import com.estacionamais.server.model.entities.Cliente;
import com.estacionamais.server.model.entities.Usuario;
import com.estacionamais.server.model.entities.Veiculo;
import com.estacionamais.server.repository.ClienteRepository;
import com.estacionamais.server.repository.UsuarioRepository;
import com.estacionamais.server.repository.VeiculoRepository;
import com.estacionamais.server.security.DadosGoogle;
import com.estacionamais.server.security.GoogleTokenService;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final GoogleTokenService googleTokenService;
    private final GoogleAuthService googleAuthService;

    public ClienteService(
            UsuarioService usuarioService,
            UsuarioRepository usuarioRepository,
            ClienteRepository clienteRepository,
            VeiculoRepository veiculoRepository,
            GoogleTokenService googleTokenService,
            GoogleAuthService googleAuthService) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
        this.googleTokenService = googleTokenService;
        this.googleAuthService = googleAuthService;
    }

    public List<DadosCliente> listarTodos() {
        return this.listarClientes().stream().map(this::paraDto).toList();
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        return clientes;
    }

    private DadosCliente paraDto(Cliente cliente) {
        return new DadosCliente(
                cliente.getId(),
                cliente.getUsuario().getNome(),
                cliente.getUsuario().getEmail(),
                cliente.getCriadoEm(),
                cliente.getAviso());
    }

    public Optional<DadosCliente> buscarPorId(Long id) {
        return listarTodos().stream().filter(usuario -> usuario.id().equals(id)).findFirst();
    }

    public Optional<DadosCliente> buscarCliente(ConsultaClienteRequest req) {
        Cliente cliente = this.clienteRepository.findByUsuarioNome(req.nome()).orElse(null);
        if (cliente == null) {
            cliente = this.clienteRepository.findById(req.id()).orElse(null);
        }
        if (cliente == null) {
            Veiculo veiculo = this.veiculoRepository.findByPlaca(req.placa()).orElseThrow();
            cliente = veiculo.getCliente();
        }

        if (cliente == null) {
            throw new RuntimeException("Nome ou placa deve ser informado.");
        }

        return Optional.of(new DadosCliente(
                cliente.getId(),
                cliente.getUsuario().getNome(),
                cliente.getUsuario().getEmail(),
                cliente.getCriadoEm(),
                cliente.getAviso()));
    }

    public DadosCliente cadastrar(ClienteRequest req) {
        FichaUsuario usuarioCadastrado = this.usuarioService.cadastrar(
                new UsuarioCadastro(req.nome(), req.email(), req.senha(), req.provider(), req.providerId()));
        Usuario usuario =
                this.usuarioRepository.findById(usuarioCadastrado.id()).orElseThrow();

        Cliente cliente = criarCliente(usuario);
        return new DadosCliente(
                cliente.getId(),
                cliente.getUsuario().getNome(),
                cliente.getUsuario().getEmail(),
                cliente.getCriadoEm(),
                cliente.getAviso());
    }

    public DadosCliente cadastrarGoogle(ClienteRequest clienteRequest) {
        DadosGoogle dados = this.googleTokenService.validar(clienteRequest.credential());
        Usuario usuario = this.googleAuthService.entrarOuCadastrar(dados);
        Cliente cliente = criarCliente(usuario);
        usuario = cliente.getUsuario();
        return new DadosCliente(
                cliente.getId(), usuario.getNome(), usuario.getEmail(), cliente.getCriadoEm(), cliente.getAviso());
    }

    private Cliente criarCliente(Usuario usuario) {
        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setCriadoEm(OffsetDateTime.now());
        cliente.setAviso("");
        return this.clienteRepository.save(cliente);
    }
}
