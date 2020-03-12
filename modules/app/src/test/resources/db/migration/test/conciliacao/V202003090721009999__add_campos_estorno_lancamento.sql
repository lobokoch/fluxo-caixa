alter table caixa_lancamento
	add column estorno BOOLEAN;

alter table caixa_lancamento	
	add column estorno_lancamento UUID;
	
alter table caixa_lancamento
	add column estorno_historico VARCHAR(1000);
	
alter table caixa_lancamento	
	add column created_by VARCHAR(255);

alter table caixa_lancamento	
	add column created_date TIMESTAMP;
	
alter table caixa_lancamento	
	add column last_modified_by VARCHAR(255);
	
alter table caixa_lancamento	
	add column last_modified_date TIMESTAMP;

ALTER TABLE caixa_lancamento 
	ADD CONSTRAINT fk_caixa_lancamento_estorno_lancamento 
	FOREIGN KEY (estorno_lancamento) REFERENCES caixa_lancamento (id);