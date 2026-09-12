package com.estacionamais.server.dto;

public record ResumoEndereco(
        Long id,
        String nomeCliente,
        String uf,
        String cep,
        String cidade,
        String bairro,
        String rua,
        Integer numero,
        String complemento) {}
