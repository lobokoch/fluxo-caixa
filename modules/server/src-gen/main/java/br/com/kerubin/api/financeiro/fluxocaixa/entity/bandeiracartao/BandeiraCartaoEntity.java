/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.bandeiracartao;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bandeira_cartao")
public class BandeiraCartaoEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotBlank(message="\"Bandeira do cartão\" é obrigatório.")
	@Size(max = 255, message = "\"Bandeira do cartão\" pode ter no máximo 255 caracteres.")
	@Column(name="nome_bandeira")
	private String nomeBandeira;
	
	@Column(name="deleted")
	private Boolean deleted = false;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getNomeBandeira() {
		return nomeBandeira;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira != null ? nomeBandeira.trim() : nomeBandeira; // Chamadas REST fazem trim.
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public void assign(BandeiraCartaoEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setNomeBandeira(source.getNomeBandeira());
			this.setDeleted(source.getDeleted());
		}
	}
	
	public BandeiraCartaoEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public BandeiraCartaoEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (BandeiraCartaoEntity) visited.get(this);
		}
				
		BandeiraCartaoEntity theClone = new BandeiraCartaoEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setNomeBandeira(this.getNomeBandeira());
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
		BandeiraCartaoEntity other = (BandeiraCartaoEntity) obj;
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
