package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

/*import lombok.Getter;
import lombok.Setter;*/

//@Getter @Setter
public class CaixaLancamentoAutoCompleteWithOwnerParams {
	
	private String query;
	private CaixaLancamento caixaLancamento;
	
	public CaixaLancamentoAutoCompleteWithOwnerParams() {
		
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public CaixaLancamento getCaixaLancamento() {
		return caixaLancamento;
	}

	public void setCaixaLancamento(CaixaLancamento caixaLancamento) {
		this.caixaLancamento = caixaLancamento;
	}

}
