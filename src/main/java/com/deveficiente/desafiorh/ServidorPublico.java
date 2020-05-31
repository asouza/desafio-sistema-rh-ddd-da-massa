package com.deveficiente.desafiorh;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
public class ServidorPublico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	@Valid
	private Cargo cargo;
	@NotNull
	private Natureza natureza;

	@NotBlank
	private String nome;

	@OneToMany(mappedBy = "servidorPublico")
	private Set<Vinculo> vinculos = new HashSet<>();
	
	@Deprecated
	public ServidorPublico() {

	}

	public ServidorPublico(@NotBlank String nome, @NotNull @Valid Cargo cargo,
			@NotNull Natureza natureza) {
		this.nome = nome;
		this.cargo = cargo;
		this.natureza = natureza;
	}

	public Vinculo adicionaVinculo(Entidade entidade) {
		Assert.isTrue(!this.temVinculoAtivo(entidade),"Este vinculo nao pode ser criado dado que o servidor ja tem vinculo ativo com a entidade");
		Vinculo novoVinculo = new Vinculo(this,entidade);
		this.vinculos.add(novoVinculo);
		return novoVinculo;
	}

	boolean temVinculoAtivo(@NotNull @Valid Entidade entidade) {
		return vinculos.stream().anyMatch(vinculo -> vinculo.ativoComAEntidade(entidade));
	}

	public Vantagem lancamentoSalario(Movimentacao movimentacao) {
		return cargo.lancamentoSalario(movimentacao);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		//é claro que poderia rolar um cpf aqui para diferenciar os servidores
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServidorPublico other = (ServidorPublico) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}	

}
