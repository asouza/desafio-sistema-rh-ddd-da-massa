package com.deveficiente.desafiorh;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

public class Movimentacao {

	@ManyToOne
	private @NotNull @Valid ServidorPublico servidor;
	private Set<Vantagem> vantagens = new HashSet<>();

	public Movimentacao(@NotNull @Valid ServidorPublico servidor) {
		this.servidor = servidor;
		this.vantagens.add(servidor.lancamentoSalario(this));

		Assert.isTrue(temSalarioAdicionado(),
				"Sempre precisa ter um salário adicionado");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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

	private BigDecimal valorVantagemBruto(NaturezaVantagem naturezaVantagem) {
		return vantagens.stream().filter(
				vantagem -> vantagem.ehDaMesmaNatureza(naturezaVantagem))
				.map(Vantagem::getValor).reduce(BigDecimal.ZERO,
						(atual, proximo) -> atual.add(proximo));
		// TODO tem que fazer alguem checagem de estado final? só pode ter uma
		// vantagem por tipo?
	}

	public boolean temVinculoAtivo(@NotNull @Valid Entidade entidade) {
		return this.servidor.temVinculoAtivo(entidade);
	}

	public BigDecimal calculaInss(ContribuicaoPrevidenciaria inss) {
		return inss.aplica(valorVantagemBruto(NaturezaVantagem.salario_cargo));
	}

	public BigDecimal calculaImpostoRenda(ContribuicaoPrevidenciaria inss,
			ImpostoRenda impostoRenda) {
		BigDecimal valorInss = calculaInss(inss);
		BigDecimal valorASerTaxado = valorVantagemBruto(
				NaturezaVantagem.salario_cargo).subtract(valorInss);
		return impostoRenda.aplica(valorASerTaxado);
	}

}
