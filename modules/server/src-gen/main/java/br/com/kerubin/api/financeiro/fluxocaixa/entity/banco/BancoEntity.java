/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.banco;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "banco")
public class BancoEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotBlank(message="\"Número\" é obrigatório.")
	@Size(max = 20, message = "\"Número\" pode ter no máximo 20 caracteres.")
	@Column(name="numero")
	private String numero;
	
	@NotBlank(message="\"Nome\" é obrigatório.")
	@Size(max = 255, message = "\"Nome\" pode ter no máximo 255 caracteres.")
	@Column(name="nome")
	private String nome;
	
	@Column(name="deleted")
	private Boolean deleted = false;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setNumero(String numero) {
		this.numero = numero != null ? numero.trim() : numero; // Chamadas REST fazem trim.
	}
	
	public void setNome(String nome) {
		this.nome = nome != null ? nome.trim() : nome; // Chamadas REST fazem trim.
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public void assign(BancoEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setNumero(source.getNumero());
			this.setNome(source.getNome());
			this.setDeleted(source.getDeleted());
		}
	}
	
	public BancoEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public BancoEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (BancoEntity) visited.get(this);
		}
				
		BancoEntity theClone = new BancoEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setNumero(this.getNumero());
		theClone.setNome(this.getNome());
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
		BancoEntity other = (BancoEntity) obj;
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
