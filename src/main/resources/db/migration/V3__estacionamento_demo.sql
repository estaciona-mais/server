INSERT INTO usuario (nome, email, senha, papel) VALUES
  ('Igor Borges',       'i.b@mail.com',    '$2a$12$o2SPR43r2SkA927KZ.rd0OA.MMkvKbznYoY0h4zwl6ZdZJ7y8dDTK',   'ADMIN'), -- abc123abc123
  ('Josimar Pereira',   'j.p@mail.com',    '$2a$12$PyRLZMh8lJ3lF1euRvR1wuEj7sJ7QOjgZROTYWQF1SGfYXQP92qBa',   'ADMIN'), -- def456def456
  ('Jonas Meireles',    'jonas@mail.com',  '$2a$12$SNCWgCLf1/MrsNnH9oGd7eLtwiGDJD459L0YIzc4XFIPvYnMIcW/y', 'CLIENTE'), -- abababab
  ('Clara Bittencourt', 'clara@mail.com',  '$2a$12$YpyPDD8JzMjaw7p9.0/vkeeQ694dcchr0ZhApW68cwJNIDySMhJSW', 'CLIENTE'), -- cllccllc
  ('Otávio Nunes',      'otavio@mail.com', '$2a$12$/7ceTGZLm7i2GviiidRkl.Wp6Pu5Vw4U7lJ5RnE2TMt9AiALqjcUe', 'CLIENTE'), -- ottootto
  ('Helena Prado',      'helena@mail.com', '$2a$12$7qivS6r1UwCQUabKXoUiO.bCIkDzvldXMDaeGmYqFHltxmRRyRcs2', 'CLIENTE'), -- helehele
  ('Marcos Vieira',     'varcos@mail.com', '$2a$12$kaR8booO3fGI4lqOWpKHBeavGhsXiyxV.yDRsnOsmxE35hclBX/GW', 'CLIENTE'), -- marcmarc
  ('Sofia Andrade',     'sofia@mail.com',  '$2a$12$ACDawymp543uY8w9/xWlIe3R66ig40BMtGeBeFsIYK/OYG32JVJw2', 'CLIENTE'); -- sfiasfia


INSERT INTO administrador (usuario_id) VALUES
  (1),
  (2);


INSERT INTO cliente (usuario_id, stripe_customer_id) VALUES
  (3, 'cus_w8B5UFcI2epMZTVioMxn5Yc5qhaJghRz9jKMqTJJmu0xzkJVD6ZWjsZ1'),
  (4, 'cus_wt1VYsxpncGCGkwUT1zsoAXibwSL0kedeQbU5L7UZGLcxpSxhNMU6LTI'),
  (5, 'cus_kVioNi306U4aGwHfHLvF4c4qtpDlGF8N9QP1GK5rDLtwbXdIonU49Wb3'),
  (6, 'cus_vAiwdE66ZkLiOyLMS7B7jSxf8zDT53bPB400C6F2hgpouFGGZoD6RPu1'),
  (7, 'cus_4gdDpKc0GOCvcojDji3bl0clNVjNSfUeSvJaUkKLyY44c6M6hd9hVOyt'),
  (8, 'cus_vod9wfxMilwb3Lcx1tQfsB1z541d9RRFMiSJaPo2auMPME4BQD4poI4O');


INSERT INTO assinatura (cliente_id, plano_id, emissao, validade, stripe_subscription_id) VALUES
  (1, 3, '2026-04-12 12:00:00', '2026-07-12', 'sub_WtWR26RciEl2UDMPft0TrzBp2t2xag1YZtZVasKpjkLxLu6jl3wg7e8v'),
  (2, 1, '2026-04-12 12:00:00', '2026-05-12', 'sub_icOiAID3CGe5U7f2ibqgqWDpLY2aXKMzXTMRm94eMuFxBNTctEY5XuEh'),
  (3, 1, '2026-04-12 12:00:00', '2026-05-12', 'sub_Et88IaPMheXyrAQsSzwTQvMKYJe5yhMqZuYYVVjRqYTaN62CYkRxcDzZ'),
  (4, 4, '2026-04-12 12:00:00', '2026-10-12', 'sub_GP0ialN2t1acNBEeKxuECkSC47CN8rMcJzvmw7meLzguEkWwwxksmuOM'),
  (5, 2, '2026-04-12 12:00:00', '2026-06-12', 'sub_lamXp4IBdGmda2zvENEcWAlkGQsxa6oi56yFXcgkis5L4OBsRHN2J7uv'),
  (6, 5, '2026-04-12 12:00:00', '2027-04-12', 'sub_EVCrx92l93QKiXJT7aWDCOGix4jLQTibPx7n8yvlEdBIoLxjDxougiPL');


INSERT INTO endereco (cliente_id, cep, uf, cidade, bairro, rua, numero) VALUES
  (1, '61910-000', 'CE', 'Maracanaú',  'Grageru',             'Rua Capitão José de Barros Pimentel', 4462),
  (2, '67010-000', 'PA', 'Ananindeua', 'Centro',              'Rodovia BR-316',                      9920),
  (3, '49025-000', 'SE', 'Aracaju',    'Cidade Alta',         'Rua Urquiza Leal',                    7724),
  (4, '77001-002', 'TO', 'Palmas',     'Plano Diretor Norte', 'Avenida Hermes da Fonseca',           4074),
  (5, '57025-000', 'AL', 'Maceió',     'Poço',                'Avenida Engenheiro Mário de Gusmão',  9426),
  (6, '77001-002', 'TO', 'Palmas',     'Plano Diretor Norte', 'Rua Omar Sabino',                     9306);


INSERT INTO veiculo (cliente_id, placa, fabricante, modelo, ano, cor, tipo) VALUES
  (1, 'AXH0V47', 'Nissan',   'TIIDA S 1.8/1.8 Flex 16V Aut.',            2008, 'VERDE',    'CARRO'),
  (2, 'KLS4J09', 'Fibravan', 'Buggy Vip 1.6 8V Total Flex',              2007, 'VERMELHO', 'CARRO'),
  (3, 'KLI4B94', 'Walk',     'Buggy  Walk Sport 1.6 8V 58cv',            2005, 'BEGE',     'CARRO'),
  (4, 'MWI2A54', 'Lada',     'Samara 1.3/ 1.5',                          1991, 'AMARELO',  'CARRO'),
  (5, 'MOQ9T43', 'Engesa',   'Engesa 4x4 2.5/4.1',                       1985, 'MARROM',   'CARRO'),
  (6, 'NEU8K98', 'Peugeot',  '208 Allure inconcert 1.5 Flex 8V 5p Mec.', 2016, 'VERDE',    'CARRO');


INSERT INTO vaga (numero, zona, tipo, disponivel) VALUES
  (12, 'A', 'CARRO', FALSE),
  (3,  'A', 'CARRO',  TRUE),
  (9,  'B', 'CARRO', FALSE),
  (23, 'B', 'CARRO',  TRUE),
  (18, 'C', 'CARRO', FALSE),
  (6,  'D', 'CARRO',  TRUE);


INSERT INTO movimentacao (veiculo_id, vaga_id, data_entrada, data_saida) VALUES
  (1, 1, '2026-04-12 12:00:00', '2026-04-12 14:00:00'),
  (2, 2, '2026-04-12 12:00:00', '2026-04-12 19:00:00'),
  (3, 3, '2026-04-12 12:00:00', '2026-04-12 15:30:00'),
  (4, 4, '2026-04-12 12:00:00', '2026-04-12 18:00:00'),
  (5, 5, '2026-04-12 12:00:00', '2026-04-12 14:20:00'),
  (6, 6, '2026-04-12 12:00:00', '2026-04-12 20:00:00');


