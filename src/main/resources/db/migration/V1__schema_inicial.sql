DROP TABLE IF EXISTS movimentacao  CASCADE;
DROP TABLE IF EXISTS assinatura    CASCADE;
DROP TABLE IF EXISTS endereco      CASCADE;
DROP TABLE IF EXISTS veiculo       CASCADE;
DROP TABLE IF EXISTS administrador CASCADE;
DROP TABLE IF EXISTS cliente       CASCADE;
DROP TABLE IF EXISTS vaga          CASCADE;
DROP TABLE IF EXISTS usuario       CASCADE;
DROP TABLE IF EXISTS plano         CASCADE;




CREATE TABLE plano
(
  id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  valor           INT    NOT NULL,
  titulo          TEXT   NOT NULL UNIQUE,
  stripe_price_id TEXT   NOT NULL UNIQUE,

  CONSTRAINT ck_titulo_plano
    CHECK (titulo IN ('MENSAL', 'BIMESTRAL', 'TRIMESTRAL', 'SEMESTRAL', 'ANUAL')),
  CONSTRAINT ck_largura_stripe_price_id_plano
    CHECK (length(stripe_price_id) = 30)
);


CREATE TABLE usuario
(
  id          BIGINT        GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nome        TEXT          NOT NULL,
  email       VARCHAR(255)  NOT NULL UNIQUE,
  senha       TEXT,
  papel       VARCHAR(10)   NOT NULL DEFAULT 'CLIENTE',
  provider    VARCHAR(10)   NOT NULL DEFAULT 'LOCAL',
  provider_id VARCHAR(120),

  CONSTRAINT ck_papel_usuario
    CHECK (papel IN ('ADMIN', 'CLIENTE')),
  CONSTRAINT ck_provider_usuario
    CHECK (provider IN ('LOCAL', 'GOOGLE')),
  CONSTRAINT ck_provider_senha_usuario
    CHECK ((provider = 'LOCAL' AND senha IS NOT NULL) OR (provider = 'GOOGLE' AND senha IS NULL))
);


CREATE TABLE administrador
(
  id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  usuario_id BIGINT NOT NULL,

  CONSTRAINT fk_administrador_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
    ON DELETE CASCADE
);


CREATE TABLE cliente
(
  id                 BIGINT      GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  usuario_id         BIGINT      NOT NULL,

  stripe_customer_id VARCHAR(60) UNIQUE,
  criado_em          TIMESTAMPTZ NOT NULL DEFAULT now(),
  aviso              TEXT,

  CONSTRAINT fk_cliente_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
    ON DELETE CASCADE
);


CREATE TABLE assinatura
(
  id                     BIGINT       GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  cliente_id             BIGINT       NOT NULL,
  plano_id               BIGINT       NOT NULL,

  emissao                TIMESTAMPTZ  NOT NULL DEFAULT now(),
  validade               DATE         NOT NULL,
  atualizada_em          TIMESTAMPTZ,
  status                 TEXT         NOT NULL DEFAULT 'ATIVA',
  metodo_pagamento       TEXT         NOT NULL,
  stripe_subscription_id TEXT         NOT NULL UNIQUE,

  CONSTRAINT fk_cliente_assinatura
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_plano_assinatura
    FOREIGN KEY (plano_id) REFERENCES plano (id)
    ON DELETE CASCADE,
  CONSTRAINT ck_status_assinatura
    CHECK (status IN ('ATIVA', 'INATIVA', 'CANCELADA')),
  CONSTRAINT ck_metodo_pagamento_cliente
      CHECK (metodo_pagamento IN ('DINHEIRO', 'DEBITO', 'CREDITO', 'PIX', 'BOLETO'))
);


CREATE TABLE endereco
(
  id          BIGINT       GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  cliente_id  BIGINT       NOT NULL,

  cep         TEXT         NOT NULL,
  uf          TEXT         NOT NULL,
  cidade      VARCHAR(96)  NOT NULL,
  bairro      VARCHAR(96)  NOT NULL,
  rua         VARCHAR(96)  NOT NULL,
  numero      INT          NOT NULL,
  complemento VARCHAR(96),

  CONSTRAINT fk_cliente_endereco
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
    ON DELETE CASCADE,
  CONSTRAINT ck_largura_cep_endereco
    CHECK (length(cep) = 9),
  CONSTRAINT ck_largura_uf_endereco
    CHECK (length(uf) = 2)
);


CREATE TABLE veiculo
(
  id         BIGINT       GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  cliente_id BIGINT       NOT NULL,

  placa      TEXT         NOT NULL UNIQUE,
  fabricante VARCHAR(255) NOT NULL,
  modelo     VARCHAR(255) NOT NULL,
  ano        INT          NOT NULL,
  cor        VARCHAR(20)  NOT NULL,
  tipo       TEXT         NOT NULL,
  observacao TEXT,

  CONSTRAINT fk_veiculo_cliente
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
    ON DELETE CASCADE,
  CONSTRAINT ck_placa_veiculo
    CHECK (length(placa) = 7),
  CONSTRAINT ck_tipo_veiculo
    CHECK (tipo IN ('CARRO', 'MOTOCICLETA')),
  CONSTRAINT ck_ano_veiculo
    CHECK (ano <= EXTRACT(YEAR FROM CURRENT_DATE)),
  CONSTRAINT ck_cor_veiculo
    CHECK (cor IN ('PRETO', 'BRANCO', 'CINZA', 'PRATA',
                   'BEGE', 'MARROM', 'VERMELHO', 'LARANJA',
                   'LARANJA_NEON', 'OURO', 'AMARELO', 'AMARELO_NEON',
                   'VERDE', 'CIANO', 'AZUL', 'ROXO',
                   'ROSA', 'ROSA_NEON', 'FANTASIA'))
);


CREATE TABLE vaga
(
  id           BIGINT  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  numero       INT     NOT NULL,
  zona         TEXT    NOT NULL,
  tipo         TEXT    NOT NULL,
  disponivel   BOOLEAN NOT NULL DEFAULT TRUE,

  CONSTRAINT ck_largura_zona_vaga
    CHECK (length(zona) = 1),
  CONSTRAINT ck_tipo_vaga
    CHECK (tipo IN ('CARRO', 'MOTOCICLETA'))

);

CREATE TABLE movimentacao
(
  id           BIGINT     GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  veiculo_id   BIGINT     NOT NULL REFERENCES veiculo(id),
  vaga_id      BIGINT     NOT NULL REFERENCES vaga(id),
  data_entrada TIMESTAMP  NOT NULL,
  data_saida   TIMESTAMP,

  CONSTRAINT fk_movimentacao_veiculo
    FOREIGN KEY (veiculo_id) REFERENCES veiculo (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_movimentacao_vaga
    FOREIGN KEY (vaga_id) REFERENCES vaga (id)
    ON DELETE CASCADE
);




CREATE INDEX idx_cliente_usuario      ON cliente      (usuario_id);
CREATE INDEX idx_assinatura_cliente   ON assinatura   (cliente_id);
CREATE INDEX idx_assinatura_plano     ON assinatura   (plano_id);
CREATE INDEX idx_endereco_cliente     ON endereco     (cliente_id);
CREATE INDEX idx_veiculo_cliente      ON veiculo      (cliente_id);
CREATE INDEX idx_movimentacao_veiculo ON movimentacao (veiculo_id);
CREATE INDEX idx_movimentacao_vaga    ON movimentacao (vaga_id);

