package com.deveficiente.desafiorh;

import java.math.BigDecimal;
import java.util.function.Function;

public class ContribuicaoPrevidenciaria implements Desconto{
	/*
	 * Aqui poderia ser uma interface e cada faixa poderia ser uma implementação. 
	 * Daria para compor todas elas e tal... só que essa regra não muda muito...
	 * Pq eu vou generalizar? 	
	 */

	public static final BigDecimal multiplicadorFaixa1 = new BigDecimal("0.075");
	public static final BigDecimal multiplicadorFaixa2 = new BigDecimal("0.09");
	public static final BigDecimal multiplicadorFaixa3 = new BigDecimal("0.12");
	public static final BigDecimal multiplicadorFaixa4 = new BigDecimal("0.14");
	public static final BigDecimal tetoFaixa1 = new BigDecimal("1045");
	public static final BigDecimal tetoFaixa2 = new BigDecimal("2089.60");
	public static final BigDecimal tetoFaixa3 = new BigDecimal("3134.40");
	public static final BigDecimal tetoFaixa4 = new BigDecimal("6101.06");
	public static final BigDecimal aliquotaFixa1 = tetoFaixa1.multiply(multiplicadorFaixa1);
	public static final BigDecimal aliquotaFixa2 = tetoFaixa2.subtract(tetoFaixa1)
			.multiply(multiplicadorFaixa2);
	public static final  BigDecimal aliquotaFixa3 = tetoFaixa3.subtract(tetoFaixa2)
			.multiply(multiplicadorFaixa3);	
	public static final  BigDecimal aliquotaFixa4 = new BigDecimal("713.09");
	
	
	public BigDecimal aplica(Movimentacao movimentacao) {
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
		
		BigDecimal valor = movimentacao.valorVantagemBruto(NaturezaVantagem.salario_cargo);

		if (valor.compareTo(tetoFaixa1) <= 0) {
			return valor.multiply(multiplicadorFaixa1);
		}
		
		if (valor.compareTo(tetoFaixa2) <= 0) {
			BigDecimal sobraParaAplicarAliquota2 = valor.subtract(tetoFaixa1);
			BigDecimal aliquota2 = sobraParaAplicarAliquota2
					.multiply(multiplicadorFaixa2);
			return aliquotaFixa1.add(aliquota2);

		}

		if (valor.compareTo(tetoFaixa3) <= 0) {
			BigDecimal sobraParaAplicarAliquota3 = valor.subtract(tetoFaixa2);
			BigDecimal aliquota3 = sobraParaAplicarAliquota3
					.multiply(multiplicadorFaixa3);
			return aliquotaFixa1.add(aliquotaFixa2).add(aliquota3);
		}

		if (valor.compareTo(tetoFaixa4) <= 0) {
			BigDecimal sobraParaAplicarAliquota4 = valor.subtract(tetoFaixa3);
			BigDecimal aliquota4 = sobraParaAplicarAliquota4
					.multiply(multiplicadorFaixa4);
			return aliquotaFixa1.add(aliquotaFixa2).add(aliquotaFixa3).add(aliquota4);
		}

		//tem salario maior que a faixa 4
		return new BigDecimal("713.09");

	}
}
