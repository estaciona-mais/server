# server
API Java utilizada no projeto Estaciona+.

## Execução
A API pode ser executada de várias formas:

### Maven
Para executar com o Maven, basta instalar o Maven e rodar o comando `mvn spring-boot:run`.

### Makefile
Caso tenha o gnumake instalado, o programa também pode ser executado com `make run`.

### Docker
Um contêiner Docker pode ser executado a partir de `docker-compose up --build`.

## Estrutura de arquivos
```
src                                   # código fonte do projeto
├── main                              # arquivos principais do projeto
│   ├── java/com/estacionamais/server # códigos específicos do projeto "server"
│   │   ├── controller                # definições de endpoints e suas funções
│   │   ├── core                      # configurações do projeto
│   │   ├── dto                       # objetos para transferências de dados
│   │   ├── exception                 # classes de erros personalizados
│   │   ├── model                     # entidades do banco de dados e enums
│   │   ├── repository                # conexões entre entidades com o banco de dados
│   │   ├── security                  # implementações de segurança
│   │   └── service                   # funcionalidades utilizadas pelos controllers
│   └── resources                     # recursos extras
│       └── db                        # arquivos SQL para o banco de dados
└── test                              # códigos relacionados a testes
wrapper                               # executável do Maven, utilizado pelo Docker
```

