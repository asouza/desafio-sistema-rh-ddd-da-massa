package com.deveficiente.desafiorh;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
public class Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private @NotNull @Valid Administracao administracao;
	private @NotBlank String nome;

	public Entidade(@NotNull @Valid Administracao administracao, @NotBlank String nome) {
		Assert.isTrue(administracao.respeitaLimiteDeEntidades(),"Não rola criar uma nova entidade para essa administracao");
		this.administracao = administracao;
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((administracao == null) ? 0 : administracao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Entidade other = (Entidade) obj;
		if (administracao == null) {
			if (other.administracao != null)
				return false;
		} else if (!administracao.equals(other.administracao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

	
}
