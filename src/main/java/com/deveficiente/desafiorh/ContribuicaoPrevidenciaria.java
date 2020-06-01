package com.deveficiente.desafiorh;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ContribuicaoPrevidenciaria {

	private final BigDecimal multiplicadorFaixa1 = new BigDecimal("0.075");
	private final BigDecimal multiplicadorFaixa2 = new BigDecimal("0.09");
	private final BigDecimal multiplicadorFaixa3 = new BigDecimal("0.12");
	private final BigDecimal multiplicadorFaixa4 = new BigDecimal("0.14");
	private final BigDecimal tetoFaixa1 = new BigDecimal("1045");
	private final BigDecimal tetoFaixa2 = new BigDecimal("2089.60");
	private final BigDecimal tetoFaixa3 = new BigDecimal("3134.40");
	private final BigDecimal tetoFaixa4 = new BigDecimal("6101.06");
	
	
	
	//private BigDecimal duasCasasDecimais()

	public BigDecimal calcula(BigDecimal valor) {
		/*
		 * 6.101,06
		 * 
		 * 1.045 7.5% 
		 * 1.044,60 (2.089,60) 9% 
		 * 1.044,80 (3.134,40) 12% 
		 * 2.966,66(6.101,06) 14%
		 * 
		 * 
		 */

		if (valor.compareTo(tetoFaixa1) <= 0) {
			return valor.multiply(multiplicadorFaixa1);
		}
		
		if (valor.compareTo(tetoFaixa2) <= 0) {
			BigDecimal aliquota1 = tetoFaixa1.multiply(multiplicadorFaixa1);
			BigDecimal sobraParaAplicarAliquota2 = valor.subtract(tetoFaixa1);
			BigDecimal aliquota2 = sobraParaAplicarAliquota2
					.multiply(multiplicadorFaixa2);
			return aliquota1.add(aliquota2);

		}

		if (valor.compareTo(tetoFaixa3) <= 0) {
			BigDecimal aliquota1 = tetoFaixa1.multiply(multiplicadorFaixa1);
			BigDecimal aliquota2 = tetoFaixa2.subtract(tetoFaixa1)
					.multiply(multiplicadorFaixa2);
			BigDecimal sobraParaAplicarAliquota3 = valor.subtract(tetoFaixa2);
			BigDecimal aliquota3 = sobraParaAplicarAliquota3
					.multiply(multiplicadorFaixa3);
			return aliquota1.add(aliquota2).add(aliquota3);
		}

		if (valor.compareTo(tetoFaixa4) <= 0) {
			BigDecimal aliquota1 = tetoFaixa1.multiply(multiplicadorFaixa1);
			BigDecimal aliquota2 = tetoFaixa2.subtract(tetoFaixa1)
					.multiply(multiplicadorFaixa2);
			BigDecimal aliquota3 = tetoFaixa3.subtract(tetoFaixa2)
					.multiply(multiplicadorFaixa3);
			BigDecimal sobraParaAplicarAliquota4 = valor.subtract(tetoFaixa3);
			BigDecimal aliquota4 = sobraParaAplicarAliquota4
					.multiply(multiplicadorFaixa4);
			return aliquota1.add(aliquota2).add(aliquota3).add(aliquota4);
		}

		//tem salario maior que a faixa 4
		return new BigDecimal("713.09");

	}
}
