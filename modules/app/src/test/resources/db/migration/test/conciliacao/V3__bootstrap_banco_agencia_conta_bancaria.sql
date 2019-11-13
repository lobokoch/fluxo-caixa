INSERT INTO banco (id, numero, nome, deleted)
VALUES('e35b4be6-1f71-47db-8737-09e0cae6a7ce', '237', 'Banco Bradesco S.A.', false);

INSERT INTO agencia_bancaria (id, banco, numero_agencia, digito_agencia, endereco, deleted)
VALUES('96265830-7c24-4b53-99c6-d343ca602476', 'e35b4be6-1f71-47db-8737-09e0cae6a7ce', '12345', '7', NULL, false);

INSERT INTO conta_bancaria (id, nome_titular, agencia, tipo_conta_bancaria, numero_conta, digito, data_validade, ativo, deleted)
VALUES('e6b46988-769a-4e95-a947-f73697a962b2', 'Márcio Koch', '96265830-7c24-4b53-99c6-d343ca602476', 'CONTA_CORRENTE', '98765', '6', '2021-11-30', true, false);
