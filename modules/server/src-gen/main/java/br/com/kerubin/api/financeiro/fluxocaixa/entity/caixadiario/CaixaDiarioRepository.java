/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

@Transactional(readOnly = true)
public interface CaixaDiarioRepository extends JpaRepository<CaixaDiarioEntity, java.util.UUID>, QuerydslPredicateExecutor<CaixaDiarioEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.caixa as caixa, ac.caixaDiarioSituacao as caixaDiarioSituacao, ac.dataHoraAbertura as dataHoraAbertura, ac.version as version from CaixaDiarioEntity ac where ( upper(ac.caixa.nome) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<CaixaDiarioAutoComplete> autoComplete(@Param("query") String query);
}
