/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaLookupResult;

public class PlanoConta {

	private java.util.UUID id;
	
	@NotBlank(message="\"Código\" é obrigatório.")
	@Size(max = 255, message = "\"Código\" pode ter no máximo 255 caracteres.")
	private String codigo;
	
	@NotBlank(message="\"Descrição\" é obrigatório.")
	@Size(max = 255, message = "\"Descrição\" pode ter no máximo 255 caracteres.")
	private String descricao;
	
	@NotNull(message="\"tipoFinanceiro\" é obrigatório.")
	private TipoPlanoContaFinanceiro tipoFinanceiro;
	
	private TipoReceitaDespesa tipoReceitaDespesa;
	
	private PlanoContaLookupResult planoContaPai;
	
	@NotNull(message="\"Ativo\" é obrigatório.")
	private Boolean ativo = true;
	
	private Boolean deleted = false;
	
	
	public PlanoConta() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public TipoPlanoContaFinanceiro getTipoFinanceiro() {
		return tipoFinanceiro;
	}
	
	public TipoReceitaDespesa getTipoReceitaDespesa() {
		return tipoReceitaDespesa;
	}
	
	public PlanoContaLookupResult getPlanoContaPai() {
		return planoContaPai;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setTipoFinanceiro(TipoPlanoContaFinanceiro tipoFinanceiro) {
		this.tipoFinanceiro = tipoFinanceiro;
	}
	
	public void setTipoReceitaDespesa(TipoReceitaDespesa tipoReceitaDespesa) {
		this.tipoReceitaDespesa = tipoReceitaDespesa;
	}
	
	public void setPlanoContaPai(PlanoContaLookupResult planoContaPai) {
		this.planoContaPai = planoContaPai;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoConta other = (PlanoConta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return 31;
	}
	
	/* 
	@Override
	public String toString() {
		// Enabling toString for JPA entities will implicitly trigger lazy loading on all fields.
	}
	*/

}
