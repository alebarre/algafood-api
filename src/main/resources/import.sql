INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa')
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana')
INSERT INTO cozinha (id, nome) VALUES (3, 'Coreana')
INSERT INTO cozinha (id, nome) VALUES (4, 'Russa')
INSERT INTO cozinha (id, nome) VALUES (5, 'Americana')

INSERT INTO restaurante (nome, taxa_frete, fk_cozinha_id) VALUES ('Mocelin',6.50, 1)
INSERT INTO restaurante (nome, taxa_frete, fk_cozinha_id) VALUES ('Noi',4.50, 1)
INSERT INTO restaurante (nome, taxa_frete, fk_cozinha_id) VALUES ('Verdana',9.50, 3)

INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Dinheiro')
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'Débito')

INSERT INTO permissao (id, nome, descricao) VALUES (1, 'Gerente', 'Total')
INSERT INTO permissao (id, nome, descricao) VALUES (2, 'Cozinheiro', 'Listar - Cadsatrar')

INSERT INTO estado (id, nome) VALUES (1, 'Rio de Janeiro')
INSERT INTO estado (id, nome) VALUES (2, 'São Paulo')
INSERT INTO estado (id, nome) VALUES (3, 'Minas Gerais')

INSERT INTO cidade (id, nome) VALUES (1, 'Teixeira de Freitas')
INSERT INTO cidade (id, nome) VALUES (2, 'Camocim')
INSERT INTO cidade (id, nome) VALUES (3, 'Saquarema')
