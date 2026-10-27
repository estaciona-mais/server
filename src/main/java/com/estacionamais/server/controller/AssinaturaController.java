package com.estacionamais.server.controller;

import com.estacionamais.server.dto.assinatura.*;
import com.estacionamais.server.dto.stripe.CheckoutRequest;
import com.estacionamais.server.dto.stripe.CheckoutResponse;
import com.estacionamais.server.model.entities.Cliente;
import com.estacionamais.server.repository.ClienteRepository;
import com.estacionamais.server.security.UsuarioAutenticado;
import com.estacionamais.server.service.AssinaturaService;
import com.estacionamais.server.service.CheckoutService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/assinaturas")
public class AssinaturaController {
    private final AssinaturaService assinaturaService;
    private final CheckoutService checkoutService;
    private final ClienteRepository clienteRepository;

    public AssinaturaController(
            AssinaturaService assinaturaService, CheckoutService checkoutService, ClienteRepository clienteRepository) {
        this.assinaturaService = assinaturaService;
        this.checkoutService = checkoutService;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<ResumoAssinatura> listar() {
        return this.assinaturaService.listarTodas();
    }

    @PostMapping("/checkout")
    public CheckoutResponse iniciarCheckout(
            @AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado,
            @RequestBody @Valid CheckoutRequest checkoutRequest) {
        Cliente cliente = this.clienteRepository
                .findByUsuarioId(usuarioAutenticado.getUsuario().getId())
                .orElseThrow();
        return this.checkoutService.criarSessao(cliente.getId(), checkoutRequest.planoId());
    }
}
