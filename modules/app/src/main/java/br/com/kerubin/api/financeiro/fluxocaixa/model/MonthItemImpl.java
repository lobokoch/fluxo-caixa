package br.com.kerubin.api.financeiro.fluxocaixa.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthItemImpl implements MonthItem {
	
	private Integer monthId;
	private String monthName;
	
	public MonthItemImpl() {
		
	}

}
