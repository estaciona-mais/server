package server.core;

public interface Config {
    int CAPACIDADE_VAGAS_CARRO = 60;
    int CAPACIDADE_VAGAS_MOTOCICLETA = 50;
    int CAPACIDADE_VAGAS_TOTAL = CAPACIDADE_VAGAS_CARRO + CAPACIDADE_VAGAS_MOTOCICLETA;
    // TODO: quais inscrições nós teremos?
    double PRECO_INSCRICAO = 40;
    // TODO: quais serão os preços de cada período?
    double PRECO_HORA = 10;
    double PRECO_PRIMEIRA_HORA = 10;
    double PRECO_DIARIA = 30;

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
