/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa;

import javax.persistence.Version;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.kerubin.api.database.entity.AuditingEntity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "caixa")
public class CaixaEntity extends AuditingEntity {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotBlank(message="\"Nome do caixa\" é obrigatório.")
	@Size(max = 255, message = "\"Nome do caixa\" pode ter no máximo 255 caracteres.")
	@Column(name="nome")
	private String nome;
	
	@NotNull(message="\"Caixa ativo\" é obrigatório.")
	@Column(name="ativo")
	private Boolean ativo = true;
	
	@Column(name="saldo")
	private java.math.BigDecimal saldo;
	
	@Size(max = 255, message = "\"Observações\" pode ter no máximo 255 caracteres.")
	@Column(name="observacoes")
	private String observacoes;
	
	@Version
	@Column(name="entity_version")
	private short version;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public java.math.BigDecimal getSaldo() {
		return saldo;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
	public short getVersion() {
		return version;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome != null ? nome.trim() : nome; // Chamadas REST fazem trim.
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setSaldo(java.math.BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes != null ? observacoes.trim() : observacoes; // Chamadas REST fazem trim.
	}
	
	public void setVersion(short version) {
		this.version = version;
	}
	
	public void assign(CaixaEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setNome(source.getNome());
			this.setAtivo(source.getAtivo());
			this.setSaldo(source.getSaldo());
			this.setObservacoes(source.getObservacoes());
			this.setCreatedBy(source.getCreatedBy());
			this.setCreatedDate(source.getCreatedDate());
			this.setLastModifiedBy(source.getLastModifiedBy());
			this.setLastModifiedDate(source.getLastModifiedDate());
			this.setVersion(source.getVersion());
		}
	}
	
	public CaixaEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public CaixaEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (CaixaEntity) visited.get(this);
		}
				
		CaixaEntity theClone = new CaixaEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setNome(this.getNome());
		theClone.setAtivo(this.getAtivo());
		theClone.setSaldo(this.getSaldo());
		theClone.setObservacoes(this.getObservacoes());
		theClone.setCreatedBy(this.getCreatedBy());
		theClone.setCreatedDate(this.getCreatedDate());
		theClone.setLastModifiedBy(this.getLastModifiedBy());
		theClone.setLastModifiedDate(this.getLastModifiedDate());
		theClone.setVersion(this.getVersion());
		
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
		CaixaEntity other = (CaixaEntity) obj;
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