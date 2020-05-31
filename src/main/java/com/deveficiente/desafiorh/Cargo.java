package com.deveficiente.desafiorh;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	private @Positive @NotNull BigDecimal salario;

	@Deprecated
	public Cargo() {

	}

	public Cargo(@NotBlank String nome, @Positive @NotNull BigDecimal salario) {
		this.nome = nome;
		this.salario = salario;
	}

	public Vantagem lancamentoSalario(Movimentacao movimentacao) {
		return new Vantagem(NaturezaVantagem.salario_cargo, salario,
				movimentacao);
	}

}
