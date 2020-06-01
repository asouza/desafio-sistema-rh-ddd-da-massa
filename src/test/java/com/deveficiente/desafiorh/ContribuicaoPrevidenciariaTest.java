package com.deveficiente.desafiorh;


import java.math.BigDecimal;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ContribuicaoPrevidenciariaTest {

	@ParameterizedTest
	@ValueSource(strings = { "6101.06,713.09", "1045,78.37", "2000,164.32",
			"3000,281.63", "5000,558.94", "10000,713.09" })
	public void testaCalculo(String entradaSaidaSeparadaPorVirgula) {
		
		
		String[] entradaSaida = entradaSaidaSeparadaPorVirgula.split(",");
		BigDecimal entrada = new BigDecimal(entradaSaida[0]);
		BigDecimal resultado = new ContribuicaoPrevidenciaria().aplica(entrada);

		BigDecimal saidaEsperada = new BigDecimal(entradaSaida[1]);
		MatcherAssert.assertThat(saidaEsperada,Matchers.closeTo(resultado,new BigDecimal("0.01")));
	}
}
