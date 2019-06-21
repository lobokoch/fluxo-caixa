/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface CaixaDiarioRepository extends JpaRepository<CaixaDiarioEntity, java.util.UUID>, QuerydslPredicateExecutor<CaixaDiarioEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.caixa as caixa, ac.dataHoraAbertura as dataHoraAbertura, ac.version as version from CaixaDiarioEntity ac where ( upper(ac.caixa.nome) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<CaixaDiarioAutoComplete> autoComplete(@Param("query") String query);
	
}
