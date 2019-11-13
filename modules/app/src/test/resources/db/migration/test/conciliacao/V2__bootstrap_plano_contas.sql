-- Registros de inicialização do plano de contas pré-cadastrado.

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5','1','DESPESAS','DESPESA',NULL,NULL,true,false)
,('1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe','2','RECEITAS','RECEITA',NULL,NULL,true,false)
,('2abba5e2-df3f-4b6f-96b9-9d6017c6a94a','2.1','DIVERSOS','RECEITA',NULL,'1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe',true,false)
,('e56e6da7-d57e-405f-abc9-724d3a3e58e0','2.1.1','Diversos','RECEITA',NULL,'2abba5e2-df3f-4b6f-96b9-9d6017c6a94a',true,false);

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('4d405422-53e1-48ad-9335-f57cece3563a','1.1','DIVERSOS','DESPESA',NULL,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('e80d6a4b-75cc-4155-9463-b71ac3e65600','1.1.1','Diversos','DESPESA',NULL,'4d405422-53e1-48ad-9335-f57cece3563a',true,false);

-- Para conciliação bancária
INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('edd70429-20ba-4a07-a15b-942051e5eb29','1.999','BANCO','DESPESA',null,'5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5',true,false)
,('5c29f176-eef5-4658-b00a-7e6d282429db','1.999.1','Conciliação bancária (dédito)','DESPESA',NULL,'edd70429-20ba-4a07-a15b-942051e5eb29',true,false)
--
,('02dcf115-a574-47dc-a598-93394d1e0b94','2.999','BANCO','RECEITA',null,'1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe',true,false)
,('34ff8dca-0bea-41ef-84a6-25440c87b211','2.999.1','Conciliação bancária (crédito)','RECEITA',NULL,'02dcf115-a574-47dc-a598-93394d1e0b94',true,false);