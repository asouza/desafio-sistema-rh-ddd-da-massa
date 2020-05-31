package com.deveficiente.desafiorh;

import java.math.BigDecimal;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private @NotNull @Valid ServidorPublico servidor;
	@NotNull
	private Month mes;
	@Positive
	private int ano;
	private Set<Vantagem> vantagens = new HashSet<>();

	public Movimentacao(@NotNull @Valid ServidorPublico servidor) {
		this.servidor = servidor;
		YearMonth mesCorrente = YearMonth.now();
		this.mes = mesCorrente.getMonth();
		this.ano = mesCorrente.getYear();
		this.vantagens.add(servidor.lancamentoSalario(this));

		Assert.isTrue(temSalarioAdicionado(),
				"Sempre precisa ter um salário adicionado");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result
				+ ((servidor == null) ? 0 : servidor.hashCode());
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
		Movimentacao other = (Movimentacao) obj;
		if (ano != other.ano)
			return false;
		if (mes != other.mes)
			return false;
		if (servidor == null) {
			if (other.servidor != null)
				return false;
		} else if (!servidor.equals(other.servidor))
			return false;
		return true;
	}

	public void adicionaVantagem(Vantagem vantagem) {
		Assert.isTrue(temSalarioAdicionado(),
				"Sempre deveria ter pelo menos o salário adicionado");
		Assert.isTrue(
				!vantagem.ehDaMesmaNatureza(NaturezaVantagem.salario_cargo),
				"Não pode adicionar salario, isso já existe");

		this.vantagens.add(vantagem);

	}

	private boolean temSalarioAdicionado() {
		return this.vantagens.stream().anyMatch(vantagemAtual -> vantagemAtual
				.ehDaMesmaNatureza(NaturezaVantagem.salario_cargo));
	}

	public BigDecimal valorVantagemBruto() {
		return vantagens.stream().map(Vantagem::getValor).reduce(
				BigDecimal.ZERO, (atual, proximo) -> atual.add(proximo));
	}

}
