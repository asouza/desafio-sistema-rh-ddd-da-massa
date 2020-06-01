package com.deveficiente.desafiorh;

import java.math.BigDecimal;

public class Recibo {

	private Movimentacao movimentacao;
	private ContribuicaoPrevidenciaria inss;
	private ImpostoRenda impostoRenda;

	public Recibo(Movimentacao movimentacao, ContribuicaoPrevidenciaria inss,
			ImpostoRenda impostoRenda) {
				this.movimentacao = movimentacao;
				this.inss = inss;
				this.impostoRenda = impostoRenda;
	}
	
	public BigDecimal inss() {
		return movimentacao.calculaInss(inss);
	}
	
	public BigDecimal impostoRenda() {
		return movimentacao.calculaImpostoRenda(inss,impostoRenda);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((movimentacao == null) ? 0 : movimentacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recibo other = (Recibo) obj;
		if (movimentacao == null) {
			if (other.movimentacao != null)
				return false;
		} else if (!movimentacao.equals(other.movimentacao))
			return false;
		return true;
	}
	
	
	
	

}
