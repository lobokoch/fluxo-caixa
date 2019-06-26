package br.com.kerubin.api.financeiro.fluxocaixa.core;

import java.math.BigDecimal;

public class Utils {
	
	public static BigDecimal getSafeVal(BigDecimal value) {
		return value != null ? value : new BigDecimal("0.0");
	}

}
