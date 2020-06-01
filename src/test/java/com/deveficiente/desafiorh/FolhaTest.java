package com.deveficiente.desafiorh;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FolhaTest {

	/*
	 * uma folha de pagamento é gerada por mês
	 * toda folha de pagamento pertence a um entidade
	 * uma folha tem uma movimentacao que pertence a cada servidor
	 * cada movimentacao tem uma colecao de vantagens
	 * a vantagem é regular ou complementar
	 * precisa ter pelo menos uma vantagem regular do tipo salário
	 * a colecao de processamentos, baseada em todos os tipos de vantagem, calcula o desconto.  
	 */
	
	@Test
	@DisplayName("precisamos criar uma folha e adicionar uma movimentacao")
	public void podeAdicionarMovimentacaoNaFolha() {
		Administracao adm1 = new Administracao("adm1", TipoAdm.direta);
		Entidade entidade1 = adm1.adicionaEntidade("entidade1");
						
		Cargo programador = new Cargo("programador", new BigDecimal("7000"));
		ServidorPublico seya = new ServidorPublico("seya", programador, Natureza.estatutaria);
		seya.adicionaVinculo(entidade1);
				
		Movimentacao movimentacao = new Movimentacao(seya);
		FolhaPagamento folhaPagamento = new FolhaPagamento(entidade1);
		
		folhaPagamento.adicionaMovimentacao(movimentacao);
		
		Assertions.assertEquals(new BigDecimal("7000"), folhaPagamento.salarioBrutoTotal());
		
	}
	
	@Test
	@DisplayName("precisamos criar uma folha e adicionar mais de uma movimentacao")
	public void podeAdicionarMaisDeUmaMovimentacaoNaFolha() {
		Administracao adm1 = new Administracao("adm1", TipoAdm.direta);
		Entidade entidade1 = adm1.adicionaEntidade("entidade1");
		
		Cargo programador = new Cargo("programador", new BigDecimal("7000"));
		ServidorPublico seya = new ServidorPublico("seya", programador, Natureza.estatutaria);
		seya.adicionaVinculo(entidade1);
		
		ServidorPublico yoga = new ServidorPublico("yoga", programador, Natureza.estatutaria);
		yoga.adicionaVinculo(entidade1);
		
		FolhaPagamento folhaPagamento = new FolhaPagamento(entidade1);
		
		Movimentacao movimentacaoSeya = new Movimentacao(seya);
		folhaPagamento.adicionaMovimentacao(movimentacaoSeya);
		Movimentacao movimentacaoYoga = new Movimentacao(yoga);
		folhaPagamento.adicionaMovimentacao(movimentacaoYoga);
		
		Assertions.assertEquals(new BigDecimal("14000"), folhaPagamento.salarioBrutoTotal());
		
	}
}
