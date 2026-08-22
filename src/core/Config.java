package server.core;

public interface Config {
    int CAPACIDADE_VAGAS = 50;
    double PRECO_INSCRICAO = 40;

    enum PerfilUsuario {
        Administrador,
        Operador,
        Mensalista
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
