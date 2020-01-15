/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoPessoa;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class ClienteEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotNull(message="\"tipoPessoa\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_pessoa")
	private TipoPessoa tipoPessoa;
	
	@NotBlank(message="\"Nome\" é obrigatório.")
	@Size(max = 255, message = "\"Nome\" pode ter no máximo 255 caracteres.")
	@Column(name="nome")
	private String nome;
	
	@Size(max = 255, message = "\"Documento (CNPJ/CPF)\" pode ter no máximo 255 caracteres.")
	@Column(name="cnpj_cpf")
	private String cnpjCPF;
	
	@Column(name="deleted")
	private Boolean deleted = false;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCnpjCPF() {
		return cnpjCPF;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	public void setNome(String nome) {
		this.nome = nome != null ? nome.trim() : nome; // Chamadas REST fazem trim.
	}
	
	public void setCnpjCPF(String cnpjCPF) {
		this.cnpjCPF = cnpjCPF != null ? cnpjCPF.trim() : cnpjCPF; // Chamadas REST fazem trim.
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public void assign(ClienteEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setTipoPessoa(source.getTipoPessoa());
			this.setNome(source.getNome());
			this.setCnpjCPF(source.getCnpjCPF());
			this.setDeleted(source.getDeleted());
		}
	}
	
	public ClienteEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public ClienteEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (ClienteEntity) visited.get(this);
		}
				
		ClienteEntity theClone = new ClienteEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setTipoPessoa(this.getTipoPessoa());
		theClone.setNome(this.getNome());
		theClone.setCnpjCPF(this.getCnpjCPF());
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
		ClienteEntity other = (ClienteEntity) obj;
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
