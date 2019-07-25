package br.com.kerubin.api.financeiro.fluxocaixa.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FluxoCaixaMonthItemImpl implements FluxoCaixaMonthItem {
	
	private Integer monthId;
	private String monthName;
	private BigDecimal creditValue;
	private BigDecimal debitValue;
	private BigDecimal balanceValue;
	private BigDecimal balanceAccumulated;
	
	public FluxoCaixaMonthItemImpl() {
		monthId = 0;
		monthName = null;
		creditValue = BigDecimal.ZERO;
		debitValue = BigDecimal.ZERO;
		balanceValue = BigDecimal.ZERO;
		balanceAccumulated = null;
	}
	
	public FluxoCaixaMonthItemImpl(int monthId) {
		this();
		this.monthId = monthId;
	}
	
	@Override
	public void accectMonthVisitor(MonthVisitor visitor) {
		visitor.visit(this);
	}
}
