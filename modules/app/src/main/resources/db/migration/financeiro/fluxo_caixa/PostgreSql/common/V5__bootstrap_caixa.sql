INSERT INTO caixa (id,nome,ativo,saldo,observacoes,created_by,created_date,last_modified_by,last_modified_date,entity_version) VALUES 
('bd1e9cb7-e7f6-40da-af5c-1f461dac1d11','Caixa geral',true,0,NULL,'kerubin.platform@gmail.com','2019-06-29 08:37:34.646','kerubin.platform@gmail.com','2019-06-29 08:37:34.646',0)
ON CONFLICT ON CONSTRAINT pk_caixa_id DO NOTHING;

INSERT INTO caixa_diario (id,caixa,caixa_diario_situacao,data_hora_abertura,saldo_inicial,data_hora_fechamento,saldo_final,observacoes,created_by,created_date,last_modified_by,last_modified_date,entity_version) VALUES 
('2e6c3d1d-9b25-4c15-b8de-3ddf5fac6fd5','bd1e9cb7-e7f6-40da-af5c-1f461dac1d11','ABERTO','2019-06-29 08:38:28.511',0.0,NULL,NULL,NULL,'kerubin.platform@gmail.com','2019-06-29 08:38:25.024','kerubin.platform@gmail.com','2019-06-29 08:38:28.520',1)
;