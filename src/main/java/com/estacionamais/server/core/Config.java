package com.estacionamais.server.core;

public interface Config {
    int CAPACIDADE_VAGAS_CARRO = 60;
    int CAPACIDADE_VAGAS_MOTOCICLETA = 50;
    int CAPACIDADE_VAGAS_TOTAL = CAPACIDADE_VAGAS_CARRO + CAPACIDADE_VAGAS_MOTOCICLETA;
    double PRECO_INSCRICAO_MENSAL = 40;
    double PRECO_INSCRICAO_BIMESTRAL = 64;
    double PRECO_INSCRICAO_TRIMESTRAL = 84;
    double PRECO_INSCRICAO_SEMESTRAL = 134;
    double PRECO_INSCRICAO_ANUAL = 214;

    enum PerfilUsuario {
        Administrador,
        Operador,
        Mensalista
    }

    enum MetodoPagamento {
        Dinheiro,
        CartaoDebito,
        CartaoCredito,
        Pix
    }

    enum StatusContrato {
        Ativo,
        Inadimplente,
        Cancelado
    }

    enum CorVeiculo {
        Preto,
        Branco,
        Cinza,
        Prata,
        Bege,
        Marrom,
        Vermelho,
        Laranja,
        LaranjaNeon,
        Ouro,
        Amarelo,
        AmareloNeon,
        Verde,
        Ciano,
        Azul,
        Roxo,
        Rosa,
        RosaNeon,
        Fantasia // cores predominantes mistas
    }

    enum TipoVeiculo {
        Carro,
        Motocicleta,
    }

    enum CategoriaCarro {
        Hatchback,
        Sedan,
        SUV,
        Pickup,
        Esportivo,
        Minivan
    }

    enum CategoriaMotocicleta {
        Scooter,
        Commuter,
        Touring,
        Esportivo,
        Bobber,
        Adventure
    }
}
