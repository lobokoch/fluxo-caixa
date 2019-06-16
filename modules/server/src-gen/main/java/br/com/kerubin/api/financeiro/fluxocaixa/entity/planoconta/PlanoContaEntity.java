/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "plano_conta")
public class PlanoContaEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="descricao")
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_financeiro")
	private TipoPlanoContaFinanceiro tipoFinanceiro;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_receita_despesa")
	private TipoReceitaDespesa tipoReceitaDespesa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plano_conta_pai")
	
	private PlanoContaEntity planoContaPai;
	
	@Column(name="ativo")
	private Boolean ativo = true;
	
	@Column(name="deleted")
	private Boolean deleted = false;
	
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
	
	public PlanoContaEntity getPlanoContaPai() {
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
		this.codigo = codigo != null ? codigo.trim() : codigo; // Chamadas REST fazem trim.
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao != null ? descricao.trim() : descricao; // Chamadas REST fazem trim.
	}
	
	public void setTipoFinanceiro(TipoPlanoContaFinanceiro tipoFinanceiro) {
		this.tipoFinanceiro = tipoFinanceiro;
	}
	
	public void setTipoReceitaDespesa(TipoReceitaDespesa tipoReceitaDespesa) {
		this.tipoReceitaDespesa = tipoReceitaDespesa;
	}
	
	public void setPlanoContaPai(PlanoContaEntity planoContaPai) {
		this.planoContaPai = planoContaPai;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public void assign(PlanoContaEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setCodigo(source.getCodigo());
			this.setDescricao(source.getDescricao());
			this.setTipoFinanceiro(source.getTipoFinanceiro());
			this.setTipoReceitaDespesa(source.getTipoReceitaDespesa());
			this.setPlanoContaPai(source.getPlanoContaPai());
			this.setAtivo(source.getAtivo());
			this.setDeleted(source.getDeleted());
		}
	}
	
	public PlanoContaEntity clone() {
		PlanoContaEntity theClone = new PlanoContaEntity();
		theClone.setId(this.getId());
		theClone.setCodigo(this.getCodigo());
		theClone.setDescricao(this.getDescricao());
		theClone.setTipoFinanceiro(this.getTipoFinanceiro());
		theClone.setTipoReceitaDespesa(this.getTipoReceitaDespesa());
		theClone.setPlanoContaPai(this.getPlanoContaPai());
		theClone.setAtivo(this.getAtivo());
		theClone.setDeleted(this.getDeleted());
		
		return theClone;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoContaEntity other = (PlanoContaEntity) obj;
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
