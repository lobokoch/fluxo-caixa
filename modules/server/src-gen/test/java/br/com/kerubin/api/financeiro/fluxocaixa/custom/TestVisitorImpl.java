package br.com.kerubin.api.financeiro.fluxocaixa.custom;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;
import br.com.kerubin.api.financeiro.fluxocaixa.FinanceiroFluxoCaixaBaseEntityTest;
import br.com.kerubin.api.financeiro.fluxocaixa.TestOperation;
import br.com.kerubin.api.financeiro.fluxocaixa.TestVisitor;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamento;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoServiceTest;

@Primary
@Component
public class TestVisitorImpl implements TestVisitor {
	
	private static List<String> CAIXA_DIARIO_SITUACAO_MUST_BE_ABERTO = Arrays.asList("testUpdateWithAllFields", 
			"testDelete1", 
			"testUpdateWithOnlyRecairedFields", 
			"testCreateWithAllFields",
			"testCreateWithOnlyRecairedFields");
	
	public TestVisitorImpl() {
		FinanceiroFluxoCaixaBaseEntityTest.setCustomTestVisitor(this);
	}

	@Override
	public void visit(FinanceiroFluxoCaixaBaseEntityTest testInstance, String testMethodName, Object testSubject, TestOperation testOperation) {
		
		// Provide a "help" for tests, because the rules on the service definitions.
		if (testInstance.getClass() == CaixaLancamentoServiceTest.class && TestOperation.BEFORE.equals(testOperation)) {
			if (CAIXA_DIARIO_SITUACAO_MUST_BE_ABERTO.stream().anyMatch(it -> it.equals(testMethodName))) {
				
				if ("testDelete1".equals(testMethodName)) {
					CaixaLancamentoEntity caixaLancamento = (CaixaLancamentoEntity) testSubject;
					if (!caixaLancamento.getCaixaDiario().getCaixaDiarioSituacao().equals(CaixaDiarioSituacao.ABERTO)) {
						caixaLancamento.getCaixaDiario().setCaixaDiarioSituacao(CaixaDiarioSituacao.ABERTO);
					}
				}
				else {
					CaixaLancamento caixaLancamento = (CaixaLancamento) testSubject;
					if (!caixaLancamento.getCaixaDiario().getCaixaDiarioSituacao().equals(CaixaDiarioSituacao.ABERTO)) {
						caixaLancamento.getCaixaDiario().setCaixaDiarioSituacao(CaixaDiarioSituacao.ABERTO);
						
						CaixaDiarioEntity caixaDiarioEntity = (CaixaDiarioEntity) testInstance.getEm().find(CaixaDiarioEntity.class, caixaLancamento.getCaixaDiario().getId());
						caixaDiarioEntity.setCaixaDiarioSituacao(CaixaDiarioSituacao.ABERTO);
						testInstance.getEm().persist(caixaDiarioEntity);
					}
				}
				
			}
			
		}
		
	}

}
