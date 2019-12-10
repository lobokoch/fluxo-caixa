package br.com.kerubin.api.financeiro.fluxocaixa.conciliacaobancaria;

import static br.com.kerubin.api.servicecore.util.CoreUtils.format;
import static br.com.kerubin.api.servicecore.util.CoreUtils.formatMoney;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.core.CaixaGeral;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoService;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciliacaoBancariaServiceImpl implements ConciliacaoBancariaService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private CaixaLancamentoService caixaLancamentoService;
	
	@Inject
	private ConciliacaoBancariaHelper conciliacaoBancariaHelper;
	
	@Inject
	private CaixaGeral caixaGeral;
	
	@Transactional(readOnly = true)
	@Override
	public ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		if (isEmpty(conciliacaoBancariaDTO) || isEmpty(conciliacaoBancariaDTO.getTransacoes())) {
			return conciliacaoBancariaDTO;
		}
		
		conciliacaoBancariaDTO.getTransacoes().forEach(transacao -> {
			
			List<CaixaLancamentoEntity> lancamentos = conciliacaoBancariaHelper.findLancamentosPelaTransacaoBancaria(transacao);
			
			CaixaLancamentoEntity lancamento = null;
			if (isNotEmpty(lancamentos)) {
				lancamento = lancamentos.stream().filter(it -> isConciliado(it, transacao)).findFirst().orElse(lancamentos.get(0));
			}
			atualizarTransacaoSemErroPeloLancamento(transacao, lancamento);
			
			
			// Caso tenha mais de um título, empacota eles junto para o usuário decidir qual é o título certo.
			if (isNotEmpty(lancamentos) && lancamentos.size() > 0) {
				
				List<ConciliacaoTransacaoTituloDTO> titulos = lancamentos.stream().map(lanc -> {
					String valor = transacao.isCredito() ? formatMoney(lanc.getValorCredito()) : formatMoney(lanc.getValorDebito());
					
					ConciliacaoTransacaoTituloDTO titulo = ConciliacaoTransacaoTituloDTO.builder()
							.tituloConciliadoId(lanc.getId())
							.tituloConciliadoDesc(lanc.getDescricao() + " (" + valor + ")")
							.tituloConciliadoDataVen(lanc.getDataLancamento())
							.tituloConciliadoDataPag(lanc.getDataLancamento())
							.build();
					
					// Situação do título
					SituacaoConciliacaoTrn situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CAIXA_BAIXADO_SEM_CONCILIACAO;
					if (isNotEmpty(lanc.getIdConcBancaria())) {
						situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONCILIADO_CAIXA;
						titulo.setDataConciliacao(lanc.getDataLancamento());
					}
					titulo.setSituacaoConciliacaoTrn(situacaoConciliacaoTrn);
					
					return titulo;
					
				}).collect(Collectors.toList());
				
				transacao.setConciliacaoTransacaoTitulosDTO(titulos);
				
			} // if (lancamentos.size() > 1)
			
		});
		
		return conciliacaoBancariaDTO;
	}
	
	private boolean isConciliado(CaixaLancamentoEntity lancamento, ConciliacaoTransacaoDTO transacao) {
		boolean result = transacao.getTrnId().equals(lancamento.getIdConcBancaria());
		return result;
	}
	
	//@Transactional
	@Override
	public ConciliacaoBancariaDTO aplicarConciliacaoBancaria(ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		log.info("INICIO aplicarConciliacaoBancaria...");
		
		if (isEmpty(conciliacaoBancariaDTO) || isEmpty(conciliacaoBancariaDTO.getTransacoes())) {
			return conciliacaoBancariaDTO;
		}
		
		List<ConciliacaoTransacaoDTO> transacoes = conciliacaoBancariaDTO.getTransacoes(); 
		
		String msg = null;
		String bancoId = conciliacaoBancariaDTO.getBancoId();
		String agenciaId = conciliacaoBancariaDTO.getAgenciaId();
		String contaBancariaId = conciliacaoBancariaDTO.getContaId();
		ContaBancariaEntity contaBancariaEntity = conciliacaoBancariaHelper.findContaBancaria(bancoId, agenciaId, contaBancariaId);
		if (isEmpty(contaBancariaEntity)) {
			msg = MessageFormat.format("Conta bancária não encontrada para bancoId:{0}, agenciaId:{1}, contaBancariaId:{2}", bancoId, agenciaId, contaBancariaId);
			log.error(msg);
			
			final String erroMsg = msg; 
			transacoes.forEach(it -> atualizarTransacaoComErroPeloLancamento(it, null, erroMsg));
			
			return conciliacaoBancariaDTO;
		}
		
		for (ConciliacaoTransacaoDTO transacao : transacoes) {
			msg = null;
			String logHeader = MessageFormat.format("Tran. id: {0}, doc.: {1}, hist.: {2}", 
					transacao.getId(), transacao.getTrnId(), transacao.getTrnHistorico());
			
			if (isNotEmpty(transacao.getTituloConciliadoId())) {
				msg = format("A transação já possui id ({0}) de título de conciliação", transacao.getTituloConciliadoId());
			}
			
			if (isEmpty(transacao.getTrnData())) {
				msg = "Data da transação está vazia ao baixar conta via conciliação";
			}
			
			if (isEmpty(transacao.getTrnDocumento())) {
				msg = "Documento da transação está vazio ao baixar conta via conciliação";
			}
			
			if (isEmpty(transacao.getTrnValor())) {
				msg = "Valor da transação está vazio ao baixar conta via conciliação";
			}
			
			if (isNotEmpty(msg)) {
				log.error(msg + ": " + logHeader);
				transacao = atualizarTransacaoComErroPeloLancamento(transacao, null, msg);
				continue;
			}
			
			List<CaixaLancamentoEntity> lancamentos = conciliacaoBancariaHelper.findLancamentosPelaTransacaoBancaria(transacao);
			CaixaLancamentoEntity lancamento = null;
			if (isNotEmpty(lancamentos)) { // Encontrou lançamento, não pode ser baixado via conciliação
				lancamento = lancamentos.get(0);
				msg = format("Transação já baixada no lançamento id: {0}, data: {1}, descrição: {2}", lancamento.getId(), lancamento.getDataLancamento(), lancamento.getDescricao());
				log.warn(msg);
				
				transacao = atualizarTransacaoComErroPeloLancamento(transacao, lancamento, msg);
				continue;
			}
			
			// Ok, não encontrou nenhum lançamento, então pode baixar.
			try {
				lancamento = newCaixaLancamentoEntityPelaTransacaoBancaria(transacao, contaBancariaEntity);
			}
			catch (Exception e) {
				msg = format("Erro ao criar lancamento no caixa via conciliação: " + e.getMessage());
				log.error(msg, e);
				transacao = atualizarTransacaoComErroPeloLancamento(transacao, lancamento, msg);
				continue;
			}
			
			try {
				lancamento = caixaLancamentoService.create(lancamento);
				transacao = atualizarTransacaoSemErroPeloLancamento(transacao, lancamento);
				transacao.setDataConciliacao(LocalDate.now());
			} catch(Exception e) {
				msg = format("Erro ao salvar lancamento no caixa via conciliação: " + e.getMessage());
				log.error(msg, e);
				transacao = atualizarTransacaoComErroPeloLancamento(transacao, lancamento, msg);
			}
			
		} // for
		
		log.info("FIM aplicarConciliacaoBancaria...");
		
		return conciliacaoBancariaDTO;
	}
	
	private CaixaLancamentoEntity newCaixaLancamentoEntityPelaTransacaoBancaria(ConciliacaoTransacaoDTO transacao, ContaBancariaEntity contaBancaria) {
		
		CaixaLancamentoEntity caixaLancamentoEntity = new CaixaLancamentoEntity();
		
		CaixaDiarioEntity caixaDiario = caixaGeral.getCaixaGeralDiarioAberto();
		caixaLancamentoEntity.setCaixaDiario(caixaDiario);
		
		caixaLancamentoEntity.setDescricao(transacao.getTrnHistorico());
		caixaLancamentoEntity.setDataLancamento(transacao.getTrnData());
		
		boolean isCredito = transacao.isCredito();
		if (isCredito) {
			caixaLancamentoEntity.setValorCredito(transacao.getTrnValor());
			caixaLancamentoEntity.setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro.CREDITO);
		}
		else {
			caixaLancamentoEntity.setValorDebito(transacao.getTrnValor());
			caixaLancamentoEntity.setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro.DEBITO);
		}
		
		caixaLancamentoEntity.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
		
		PlanoContaEntity planoContas = conciliacaoBancariaHelper.findPlanoContaPelaTransacaoBancaria(transacao);
		caixaLancamentoEntity.setPlanoContas(planoContas);
		
		caixaLancamentoEntity.setTipoFonteMovimento(TipoFonteMovimento.LANCEMENTO_CAIXA);
		
		caixaLancamentoEntity.setContaBancaria(contaBancaria);
		
		caixaLancamentoEntity.setCliente(null);
		
		caixaLancamentoEntity.setDocumento(transacao.getTrnDocumento());
		
		caixaLancamentoEntity.setIdConcBancaria(transacao.getTrnId());
		caixaLancamentoEntity.setNumDocConcBancaria(transacao.getTrnDocumento());
		caixaLancamentoEntity.setHistConcBancaria(transacao.getTrnHistorico());
		
		StringBuilder sb = new StringBuilder("Baixa via conciliacao bancária: documento: ")
				.append(transacao.getTrnDocumento())
				.append(", histórico: ")
				.append(transacao.getTrnHistorico());
		
		caixaLancamentoEntity.setObservacoes(sb.toString());
		
		return caixaLancamentoEntity;
	}

	private ConciliacaoTransacaoDTO atualizarTransacaoSemErroPeloLancamento(ConciliacaoTransacaoDTO transacao, CaixaLancamentoEntity lancamento) {
		return atualizarTransacaoPeloLancamento(transacao, lancamento, false, null);
	}
	
	private ConciliacaoTransacaoDTO atualizarTransacaoComErroPeloLancamento(ConciliacaoTransacaoDTO transacao, CaixaLancamentoEntity lancamento, String msg) {
		return atualizarTransacaoPeloLancamento(transacao, lancamento, true, msg);
	}
	
	private ConciliacaoTransacaoDTO atualizarTransacaoPeloLancamento(ConciliacaoTransacaoDTO transacao, CaixaLancamentoEntity lancamento, boolean isError, String msg) {
		transacao.setConciliadoComErro(isError);
		transacao.setConciliadoMsg(msg);
		
		SituacaoConciliacaoTrn situacaoConciliacaoTrn = transacao.getSituacaoConciliacaoTrn(); // Valor atual é o default.
		if (isNotEmpty(lancamento)) {
			transacao.setTituloConciliadoId(lancamento.getId());
			
			// TODO: temporário, criar coluna valor
			String valor = transacao.isCredito() ? formatMoney(lancamento.getValorCredito()) : formatMoney(lancamento.getValorDebito());
			
			transacao.setTituloConciliadoDesc(lancamento.getDescricao() + " (" + valor  + ")");
			
			if (isNotEmpty(lancamento.getIdConcBancaria())) {
				situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONCILIADO_CAIXA;
				if (isEmpty(transacao.getDataConciliacao())) {
					transacao.setDataConciliacao(lancamento.getDataLancamento());
				}
			}
			else {
				situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CAIXA_BAIXADO_SEM_CONCILIACAO;
			}
		}
		else { // Não há lancamento para a transação, mas está apto a ter
			situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONCILIAR_CAIXA;
		}
		transacao.setSituacaoConciliacaoTrn(situacaoConciliacaoTrn);
		
		return transacao;
	}
	
	

}
