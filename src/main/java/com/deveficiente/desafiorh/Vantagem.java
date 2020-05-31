package com.deveficiente.desafiorh;

import java.math.BigDecimal;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Vantagem {

	private @NotNull NaturezaVantagem natureza;
	private @Positive @NotNull BigDecimal valor;
	@ManyToOne
	private @NotNull @Valid Movimentacao movimentacao;

	public Vantagem(@NotNull NaturezaVantagem natureza,
			@Positive @NotNull BigDecimal valor,@NotNull @Valid Movimentacao movimentacao) {
				this.natureza = natureza;
				this.valor = valor;
				this.movimentacao = movimentacao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((movimentacao == null) ? 0 : movimentacao.hashCode());
		result = prime * result
				+ ((natureza == null) ? 0 : natureza.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		//TODO será que o criterio de igualdade de uma vantagem é esse mesmo?
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vantagem other = (Vantagem) obj;
		if (movimentacao == null) {
			if (other.movimentacao != null)
				return false;
		} else if (!movimentacao.equals(other.movimentacao))
			return false;
		if (natureza != other.natureza)
			return false;
		return true;
	}

	public boolean ehDaMesmaNatureza(NaturezaVantagem outraNatureza) {
		return this.natureza.equals(outraNatureza);
	}

	
	

}
