package com.estacionamais.server.security;

import com.estacionamais.server.model.entities.Cliente;

public class ClienteAutenticado extends UsuarioAutenticado {
    private final Cliente cliente;

    public ClienteAutenticado(Cliente cliente) {
        super(cliente.getUsuario());
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return this.cliente;
    }
}
