package br.com.kerubin.api.financeiro.fluxocaixa.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FluxoCaixaPlanoContasForMonth extends MonthItemImpl implements MonthVisitable {
	
	List<FluxoCaixaPlanoContasItem> items;
	
	public FluxoCaixaPlanoContasForMonth() {
		
	}

	@Override
	public void accectMonthVisitor(MonthVisitor visitor) {
		visitor.visit(this);
	}

}
