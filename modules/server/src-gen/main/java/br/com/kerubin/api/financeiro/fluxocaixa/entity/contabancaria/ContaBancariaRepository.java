/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

@Transactional(readOnly = true)
public interface ContaBancariaRepository extends JpaRepository<ContaBancariaEntity, java.util.UUID>, QuerydslPredicateExecutor<ContaBancariaEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.nomeTitular as nomeTitular, ac.numeroConta as numeroConta from ContaBancariaEntity ac where ( upper(ac.nomeTitular) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaBancariaAutoComplete> autoComplete(@Param("query") String query);
	
	// Begin generated findBy
	
	/** 
	 * Busca a conta bancária, pelo número da conta, número da agência e número o banco.
	 **/
	ContaBancariaEntity findByNumeroContaAndAgenciaNumeroAgenciaAndAgenciaBancoNumero(String conta, String agencia, String banco);
	// End generated findBy
}
