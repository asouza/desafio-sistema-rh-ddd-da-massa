package com.deveficiente.desafiorh;

import java.math.BigDecimal;

public interface DescontoPagamento {

	/**
	 * Em geral, neste calculo, não levamos em consideração o número de casas decimais. 
	 * @param valor de entrada para calculo do desconto
	 * @return valor calculado.
	 */
	BigDecimal calcula(BigDecimal valor);

}
