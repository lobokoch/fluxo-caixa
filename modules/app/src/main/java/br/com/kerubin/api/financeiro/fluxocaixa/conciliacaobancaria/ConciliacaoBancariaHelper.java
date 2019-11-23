package br.com.kerubin.api.financeiro.fluxocaixa.conciliacaobancaria;

import java.util.List;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;

public interface ConciliacaoBancariaHelper {

	List<CaixaLancamentoEntity> findLancamentosPelaTransacaoBancaria(ConciliacaoTransacaoDTO transacao);

	ContaBancariaEntity findContaBancaria(String bancoId, String agenciaId, String contaBancariaId);

	PlanoContaEntity findPlanoContaPelaTransacaoBancaria(ConciliacaoTransacaoDTO transacao);


}
