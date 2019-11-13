package br.com.kerubin.api.financeiro.fluxocaixa.conciliacaobancaria;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;

public interface ConciliacaoBancariaHelper {

	CaixaLancamentoEntity findLancamentoPelaTransacaoBancaria(ConciliacaoTransacaoDTO transacao);

	ContaBancariaEntity findContaBancaria(String bancoId, String agenciaId, String contaBancariaId);

	PlanoContaEntity findPlanoContaPelaTransacaoBancaria(ConciliacaoTransacaoDTO transacao);


}
