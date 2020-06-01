package com.deveficiente.desafiorh;

import java.math.BigDecimal;

public class ImpostoRenda implements Desconto{
		

	public static final BigDecimal multiplicadorFaixa1 = new BigDecimal("0.075");
	public static final BigDecimal multiplicadorFaixa2 = new BigDecimal("0.15");
	public static final BigDecimal multiplicadorFaixa3 = new BigDecimal("0.225");
	public static final BigDecimal multiplicadorFaixa4 = new BigDecimal("0.275");
	public static final BigDecimal tetoIsencao = new BigDecimal("1903.98");
	public static final BigDecimal tetoFaixa1 = new BigDecimal("2826.65");
	public static final BigDecimal tetoFaixa2 = new BigDecimal("3751.05");
	public static final BigDecimal tetoFaixa3 = new BigDecimal("4664.68");
	
	private ContribuicaoPrevidenciaria contribuicaoPrevidenciaria;	
	
	
	public ImpostoRenda(ContribuicaoPrevidenciaria contribuicaoPrevidenciaria) {
		this.contribuicaoPrevidenciaria = contribuicaoPrevidenciaria;
		
	}
	
	
	public BigDecimal aplica(Movimentacao movimentacao) {
		
		BigDecimal valorSalario = movimentacao.valorVantagemBruto(NaturezaVantagem.salario_cargo);
		BigDecimal valorASerTaxado = valorSalario.subtract(contribuicaoPrevidenciaria.aplica(movimentacao));
		
		if(valorASerTaxado.compareTo(tetoIsencao) <= 0) {
			return valorASerTaxado;
		}
		
		if(valorASerTaxado.compareTo(tetoFaixa1) <= 0) {
			return valorASerTaxado.multiply(multiplicadorFaixa1).subtract(new BigDecimal("142.80"));
		}
		
		if(valorASerTaxado.compareTo(tetoFaixa2) <= 0) {
			return valorASerTaxado.multiply(multiplicadorFaixa2).subtract(new BigDecimal("354.80"));
		}
		
		if(valorASerTaxado.compareTo(tetoFaixa3) <= 0) {
			return valorASerTaxado.multiply(multiplicadorFaixa3).subtract(new BigDecimal("636.13"));
		}
		
		
		return valorASerTaxado.multiply(multiplicadorFaixa4).subtract(new BigDecimal("869.36"));
	}

}
