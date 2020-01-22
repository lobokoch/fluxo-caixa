/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import javax.persistence.Version;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.kerubin.api.database.entity.AuditingEntity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;
import javax.validation.constraints.Size;

@Entity
@Table(name = "caixa_diario")
public class CaixaDiarioEntity extends AuditingEntity {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotNull(message="\"Caixa\" é obrigatório.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caixa")
	private CaixaEntity caixa;
	
	@NotNull(message="\"caixaDiarioSituacao\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="caixa_diario_situacao")
	private CaixaDiarioSituacao caixaDiarioSituacao;
	
	@Column(name="data_hora_abertura")
	private java.time.LocalDateTime dataHoraAbertura;
	
	@Column(name="saldo_inicial")
	private java.math.BigDecimal saldoInicial;
	
	@Column(name="data_hora_fechamento")
	private java.time.LocalDateTime dataHoraFechamento;
	
	@Column(name="saldo_final")
	private java.math.BigDecimal saldoFinal;
	
	@Size(max = 1000, message = "\"Observações\" pode ter no máximo 1000 caracteres.")
	@Column(name="observacoes")
	private String observacoes;
	
	@Version
	@Column(name="entity_version")
	private short version;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public CaixaEntity getCaixa() {
		return caixa;
	}
	
	public CaixaDiarioSituacao getCaixaDiarioSituacao() {
		return caixaDiarioSituacao;
	}
	
	public java.time.LocalDateTime getDataHoraAbertura() {
		return dataHoraAbertura;
	}
	
	public java.math.BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	
	public java.time.LocalDateTime getDataHoraFechamento() {
		return dataHoraFechamento;
	}
	
	public java.math.BigDecimal getSaldoFinal() {
		return saldoFinal;
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
	
	public void setCaixa(CaixaEntity caixa) {
		this.caixa = caixa;
	}
	
	public void setCaixaDiarioSituacao(CaixaDiarioSituacao caixaDiarioSituacao) {
		this.caixaDiarioSituacao = caixaDiarioSituacao;
	}
	
	public void setDataHoraAbertura(java.time.LocalDateTime dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}
	
	public void setSaldoInicial(java.math.BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	
	public void setDataHoraFechamento(java.time.LocalDateTime dataHoraFechamento) {
		this.dataHoraFechamento = dataHoraFechamento;
	}
	
	public void setSaldoFinal(java.math.BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes != null ? observacoes.trim() : observacoes; // Chamadas REST fazem trim.
	}
	
	public void setVersion(short version) {
		this.version = version;
	}
	
	public void assign(CaixaDiarioEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setCaixa(source.getCaixa());
			this.setCaixaDiarioSituacao(source.getCaixaDiarioSituacao());
			this.setDataHoraAbertura(source.getDataHoraAbertura());
			this.setSaldoInicial(source.getSaldoInicial());
			this.setDataHoraFechamento(source.getDataHoraFechamento());
			this.setSaldoFinal(source.getSaldoFinal());
			this.setObservacoes(source.getObservacoes());
			this.setCreatedBy(source.getCreatedBy());
			this.setCreatedDate(source.getCreatedDate());
			this.setLastModifiedBy(source.getLastModifiedBy());
			this.setLastModifiedDate(source.getLastModifiedDate());
			this.setVersion(source.getVersion());
		}
	}
	
	public CaixaDiarioEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public CaixaDiarioEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (CaixaDiarioEntity) visited.get(this);
		}
				
		CaixaDiarioEntity theClone = new CaixaDiarioEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setCaixa(this.getCaixa() != null ? this.getCaixa().clone(visited) : null);
		theClone.setCaixaDiarioSituacao(this.getCaixaDiarioSituacao());
		theClone.setDataHoraAbertura(this.getDataHoraAbertura());
		theClone.setSaldoInicial(this.getSaldoInicial());
		theClone.setDataHoraFechamento(this.getDataHoraFechamento());
		theClone.setSaldoFinal(this.getSaldoFinal());
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
		CaixaDiarioEntity other = (CaixaDiarioEntity) obj;
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
