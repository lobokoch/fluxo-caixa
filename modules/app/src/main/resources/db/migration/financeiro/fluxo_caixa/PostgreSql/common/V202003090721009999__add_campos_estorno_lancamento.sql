alter table caixa_lancamento
	add column estorno BOOLEAN,
	add column estorno_lancamento UUID /* estornoLancamento */,
	add column estorno_historico VARCHAR(1000) /* estornoHistorico */,
	add column created_by VARCHAR(255) /* createdBy */,
	add column created_date TIMESTAMP /* createdDate */,
	add column last_modified_by VARCHAR(255) /* lastModifiedBy */,
	add column last_modified_date TIMESTAMP /* lastModifiedDate */;

ALTER TABLE caixa_lancamento 
	ADD CONSTRAINT fk_caixa_lancamento_estorno_lancamento 
	FOREIGN KEY (estorno_lancamento) REFERENCES caixa_lancamento (id);