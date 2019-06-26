package br.com.kerubin.api.financeiro.fluxocaixa.core;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiario;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.QCaixaDiarioEntity;

public class CaixaUtils {
	
	public static CaixaDiarioEntity getCaixaDiarioAnterior(EntityManager em, UUID caixaId) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		QCaixaDiarioEntity qCaixaDiarioEntity = QCaixaDiarioEntity.caixaDiarioEntity;
		
		JPQLQuery<LocalDateTime> maxLastModifiedDate = JPAExpressions
				.select(qCaixaDiarioEntity.lastModifiedDate.max())
				.from(qCaixaDiarioEntity)
				.where(qCaixaDiarioEntity.caixa.id.eq(caixaId));
		
		List<CaixaDiarioEntity> caixas = query
				.selectFrom(qCaixaDiarioEntity)
				.where(qCaixaDiarioEntity.caixa.id.eq(caixaId)
						.and(qCaixaDiarioEntity.lastModifiedDate.eq(maxLastModifiedDate)))
				.orderBy(qCaixaDiarioEntity.version.desc())
				.fetch();
		
		if (caixas.isEmpty()) {
			return null;
		}
		
		return caixas.get(0);
	}
	
	public static CaixaDiarioEntity getCaixaDiarioAnterior(EntityManager em, UUID caixaId, UUID caixaDiarioAtual) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		QCaixaDiarioEntity qCaixaDiarioEntity = QCaixaDiarioEntity.caixaDiarioEntity;
		
		JPQLQuery<LocalDateTime> maxLastModifiedDate = JPAExpressions
				.select(qCaixaDiarioEntity.lastModifiedDate.max())
				.from(qCaixaDiarioEntity)
				.where(qCaixaDiarioEntity.caixa.id.eq(caixaId)
						.and(qCaixaDiarioEntity.id.ne(caixaDiarioAtual)));
		
		List<CaixaDiarioEntity> caixas = query
			.selectFrom(qCaixaDiarioEntity)
			.where(qCaixaDiarioEntity.caixa.id.eq(caixaId)
					.and(qCaixaDiarioEntity.id.ne(caixaDiarioAtual))
					.and(qCaixaDiarioEntity.lastModifiedDate.eq(maxLastModifiedDate)))
			.orderBy(qCaixaDiarioEntity.version.desc())
			.fetch();
		
		if (caixas.isEmpty()) {
			return null;
		}
		
		return caixas.get(0);
	}
	
	public static String getNomeDoCaixa(CaixaDiario caixaDiario) {
		String result = "<Sem nome>";
		if (caixaDiario != null && caixaDiario.getCaixa() != null && caixaDiario.getCaixa().getNome() != null) {
			result = caixaDiario.getCaixa().getNome();
		}
		
		return result;
	}

}
