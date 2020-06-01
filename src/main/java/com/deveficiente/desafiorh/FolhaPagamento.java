package com.deveficiente.desafiorh;

import java.math.BigDecimal;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

public class FolhaPagamento {

	private Entidade entidade;
	private Month mes;
	private int ano;

	@ElementCollection
	private Set<Movimentacao> movimentacoes = new HashSet<>();

	public FolhaPagamento(@NotNull @Valid Entidade entidade) {
		this.entidade = entidade;
		YearMonth mesCorrente = YearMonth.now();
		this.mes = mesCorrente.getMonth();
		this.ano = mesCorrente.getYear();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result
				+ ((entidade == null) ? 0 : entidade.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
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
		FolhaPagamento other = (FolhaPagamento) obj;
		if (ano != other.ano)
			return false;
		if (entidade == null) {
			if (other.entidade != null)
				return false;
		} else if (!entidade.equals(other.entidade))
			return false;
		if (mes != other.mes)
			return false;
		return true;
	}

	public void adicionaMovimentacao(Movimentacao movimentacao) {
		Assert.isTrue(movimentacao.temVinculoAtivo(this.entidade),
				"a movimentacao precisa ser de um servidor com vinculo ativo");
		Assert.isTrue(!this.movimentacoes.contains(movimentacao),
				"essa movimentacao ja foi adicionada " + movimentacao);

		this.movimentacoes.add(movimentacao);

	}

	public BigDecimal valorVantagensBruto() {
		Assert.isTrue(!this.movimentacoes.isEmpty(),
				"precisa ter adicionado movimentacao para saber o valor bruto");
		return this.movimentacoes.stream().map(Movimentacao::valorVantagemBruto)
				.reduce(BigDecimal.ZERO,
						(atual, proximo) -> atual.add(proximo));
	}

}
