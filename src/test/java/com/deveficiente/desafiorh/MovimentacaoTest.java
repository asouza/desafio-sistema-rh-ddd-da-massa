package com.deveficiente.desafiorh;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovimentacaoTest {

	private ServidorPublico seya;

	@BeforeEach
	public void before() {
		Cargo programador = new Cargo("programador", new BigDecimal("7000"));
		seya = new ServidorPublico("seya", programador, Natureza.estatutaria);

		Administracao adm1 = new Administracao("adm1", TipoAdm.direta);

		Entidade entidade1 = adm1.adicionaEntidade("entidade1");

		seya.adicionaVinculo(entidade1);

	}

	@Test
	@DisplayName("uma movimentacao nao pode ter mais de uma vantagem do tipo salario")
	void soPodeTerUmaMovimentacaoDeSalario() {
		/*
		 * todo mês tem pelo menos uma folha regular a folha regular ter um ou
		 * mais ou lançamentos um lançamento tem um valor e uma
		 * natureza(vantagem/desconto) todo mês, na folha regular, precisa ter
		 * pelo menos um lançamento do tipo salário que é calculado a partir do
		 * cargo
		 */
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Movimentacao movimentacao = new Movimentacao(seya);
			movimentacao.adicionaVantagem(
					new Vantagem(NaturezaVantagem.salario_cargo, BigDecimal.TEN,
							movimentacao));
		});
	}

	@Test
	@DisplayName("calcular total de vantagens bruta de uma movimentacao")
	void calculaVantagemBrutaDeUmaMovimentacao() {
		Movimentacao movimentacao = new Movimentacao(seya);
		movimentacao.adicionaVantagem(new Vantagem(NaturezaVantagem.refeicao,
				BigDecimal.TEN, movimentacao));
		Assertions.assertEquals(new BigDecimal("7010"),
				movimentacao.valorVantagemBruto());
	}

	@Test
	@DisplayName("nao pode ter movimentacao para servidor inativo")
	void naoPodeTerMovimentacaoParaServidorInativo() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Movimentacao(seya);
		});
	}

}
