package com.deveficiente.desafiorh;

import java.math.BigDecimal;

public interface Desconto {

	/**
	 * 
	 * @param movimentacao
	 * @return o desconto aplicado sobre aquela movimentacao
	 */
	public BigDecimal aplica(Movimentacao movimentacao);
}
