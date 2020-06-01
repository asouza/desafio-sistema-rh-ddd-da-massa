package com.deveficiente.desafiorh;

import java.math.BigDecimal;
import java.util.Set;

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
	
	/*
	 * uma folha de pagamento é gerada por mês
	 * toda folha de pagamento pertence a um entidade
	 * uma folha tem uma movimentacao que pertence a cada servidor
	 * cada movimentacao tem uma colecao de vantagens
	 * a vantagem é regular ou complementar
	 * precisa ter pelo menos uma vantagem regular do tipo salário
	 * a colecao de processamentos, baseada em todos os tipos de vantagem, calcula o desconto.  
	 */	
	
	/*
	 * Aqui realmente era melhor eu perguntar mais a rubens. O que realmente representa um
	 * processamento? Pq no fim parece que precisa ter salário e outras vantagens regulares e pode 
	 * ou nao ter vantagens complementares(bonus,plr,salários extras...)
	 */

	@Test
	@DisplayName("uma movimentacao nao pode ter mais de uma vantagem do tipo salario")
	void soPodeTerUmaVantagemDeSalario() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Movimentacao movimentacao = new Movimentacao(seya);
			movimentacao.adicionaVantagem(BigDecimal.TEN,NaturezaVantagem.salario_cargo);
		});
	}

	@Test
	@DisplayName("calcular total de vantagens bruta de uma movimentacao")
	void calculaVantagemBrutaDeUmaMovimentacao() {		
		Movimentacao movimentacao = new Movimentacao(seya);
		movimentacao.adicionaVantagem(BigDecimal.TEN,NaturezaVantagem.refeicao);
		Assertions.assertEquals(new BigDecimal("7000"),
				movimentacao.valorVantagemBruto(NaturezaVantagem.salario_cargo));		
		Assertions.assertEquals(BigDecimal.TEN,
				movimentacao.valorVantagemBruto(NaturezaVantagem.refeicao));		
	}
	
	@Test
	@DisplayName("calcular total de vantagens bruta de uma movimentacao com vantagens complementares")
	void calculaVantagemBrutaDeUmaMovimentacaoComVantagemComplementar() {
		Movimentacao movimentacao = new Movimentacao(seya);
		movimentacao.adicionaVantagem(BigDecimal.TEN,NaturezaVantagem.refeicao);
		
		movimentacao.adicionaVantagem(new BigDecimal("1000"),NaturezaVantagem.bonus);
		
		
		Assertions.assertEquals(new BigDecimal("7000"),
				movimentacao.valorVantagemBruto(NaturezaVantagem.salario_cargo));		
		Assertions.assertEquals(BigDecimal.TEN,
				movimentacao.valorVantagemBruto(NaturezaVantagem.refeicao));
		Assertions.assertEquals(new BigDecimal("1000"),
				movimentacao.valorVantagemBruto(NaturezaVantagem.bonus));
	}

}
