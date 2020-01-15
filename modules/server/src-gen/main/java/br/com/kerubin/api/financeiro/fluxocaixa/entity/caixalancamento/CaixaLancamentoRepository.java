/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

@Transactional(readOnly = true)
public interface CaixaLancamentoRepository extends JpaRepository<CaixaLancamentoEntity, java.util.UUID>, QuerydslPredicateExecutor<CaixaLancamentoEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.descricao as descricao, ac.version as version from CaixaLancamentoEntity ac where ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<CaixaLancamentoAutoComplete> autoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.descricao as descricao from CaixaLancamentoEntity ac where ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<CaixaLancamentoDescricaoAutoComplete> caixaLancamentoDescricaoAutoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.histConcBancaria as histConcBancaria from CaixaLancamentoEntity ac where ( upper(ac.histConcBancaria) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<CaixaLancamentoHistConcBancariaAutoComplete> caixaLancamentoHistConcBancariaAutoComplete(@Param("query") String query);
}
