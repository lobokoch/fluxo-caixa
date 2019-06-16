/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.banco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface BancoRepository extends JpaRepository<BancoEntity, java.util.UUID>, QuerydslPredicateExecutor<BancoEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.numero as numero, ac.nome as nome from BancoEntity ac where ( upper(ac.numero) like upper(concat('%', :query, '%')) ) or ( upper(ac.nome) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<BancoAutoComplete> autoComplete(@Param("query") String query);
	
}
