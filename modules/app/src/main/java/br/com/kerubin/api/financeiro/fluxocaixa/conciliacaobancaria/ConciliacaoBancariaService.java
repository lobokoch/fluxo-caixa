package br.com.kerubin.api.financeiro.fluxocaixa.conciliacaobancaria;

public interface ConciliacaoBancariaService {

	ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO);

	ConciliacaoBancariaDTO aplicarConciliacaoBancaria(ConciliacaoBancariaDTO conciliacaoBancariaDTO);

}
