package com.deveficiente.desafiorh;

import java.math.BigDecimal;

public class ImpostoRenda {
		

	public static final BigDecimal multiplicadorFaixa1 = new BigDecimal("0.075");
	public static final BigDecimal multiplicadorFaixa2 = new BigDecimal("0.15");
	public static final BigDecimal multiplicadorFaixa3 = new BigDecimal("0.225");
	public static final BigDecimal multiplicadorFaixa4 = new BigDecimal("0.275");
	public static final BigDecimal tetoIsencao = new BigDecimal("1903.98");
	public static final BigDecimal tetoFaixa1 = new BigDecimal("2826.65");
	public static final BigDecimal tetoFaixa2 = new BigDecimal("3751.05");
	public static final BigDecimal tetoFaixa3 = new BigDecimal("4664.68");	
	
	
	public BigDecimal aplica(BigDecimal valor) {
		if(valor.compareTo(tetoIsencao) <= 0) {
			return valor;
		}
		
		if(valor.compareTo(tetoFaixa1) <= 0) {
			return valor.multiply(multiplicadorFaixa1).subtract(new BigDecimal("142.80"));
		}
		
		if(valor.compareTo(tetoFaixa2) <= 0) {
			return valor.multiply(multiplicadorFaixa2).subtract(new BigDecimal("354.80"));
		}
		
		if(valor.compareTo(tetoFaixa3) <= 0) {
			return valor.multiply(multiplicadorFaixa3).subtract(new BigDecimal("636.13"));
		}
		
		
		return valor.multiply(multiplicadorFaixa4).subtract(new BigDecimal("869.36"));
	}

}
