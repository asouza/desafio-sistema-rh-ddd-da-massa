package com.deveficiente.desafiorh;

public enum NaturezaVantagem {

	salario_cargo(TipoVantagem.regular), refeicao(TipoVantagem.regular), bonus(TipoVantagem.complementar);

	private final TipoVantagem tipo;

	NaturezaVantagem(TipoVantagem tipo) {
		this.tipo = tipo;
	}
	
}
