package br.com.kerubin.api.financeiro.fluxocaixa.service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaAutoCompleteImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioAutoCompleteImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioServiceImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.QCaixaDiarioEntity;

@Primary
@Service
public class CustomCaixaDiarioServiceImpl extends CaixaDiarioServiceImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<CaixaDiarioAutoComplete> autoComplete(String query) {
		JPAQueryFactory queryDSL = new JPAQueryFactory(em);
		 QCaixaDiarioEntity qCaixaDiario = QCaixaDiarioEntity.caixaDiarioEntity;
		 
		 Collection<? extends CaixaDiarioAutoComplete> result = queryDSL.select(
				Projections.bean(CaixaDiarioAutoCompleteImpl.class, 
				qCaixaDiario.id, 
					Projections.bean(
							CaixaAutoCompleteImpl.class, 
							qCaixaDiario.caixa.id,
							qCaixaDiario.caixa.nome,
							qCaixaDiario.caixa.version).as("caixa"),
				qCaixaDiario.dataHoraAbertura, 
				qCaixaDiario.version)
		)
		.from(qCaixaDiario)
		.where(
				qCaixaDiario.caixa.nome.containsIgnoreCase(query).
				and(qCaixaDiario.caixaDiarioSituacao.eq(CaixaDiarioSituacao.ABERTO))
			)
		.orderBy(qCaixaDiario.dataHoraAbertura.desc())
		.fetch();
		 
		return (Collection<CaixaDiarioAutoComplete>) result;
	}

}
