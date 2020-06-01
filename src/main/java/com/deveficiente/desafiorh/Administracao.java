package com.deveficiente.desafiorh;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.util.Assert;

@Entity
public class Administracao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	private @NotNull TipoAdm tipo;
	@OneToMany(mappedBy = "administracao")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Entidade> entidades = new HashSet<>();

	public Administracao(@NotBlank String nome, @NotNull TipoAdm tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public Entidade adicionaEntidade(String nome) {
		Assert.isTrue(this.respeitaLimiteDeEntidades(),"Essa administracao nao pode ter outra entidade");
		
		Entidade novaEntidade = new Entidade(this,nome);
		this.entidades.add(novaEntidade);
		
		//esse daqui é um código que parece bobo, mas é legal. Se não tivesse isso aqui, como seria seu teste de unidade?
		//claro que uma opção era não ter, eu não te julgaria. Mas aqui a gente garante as restrições de entrada e saída do método
		Assert.isTrue(this.entidades.contains(novaEntidade),"Deveria ter a entidade "+nome+" adicionada");
		return novaEntidade;
	}

	boolean respeitaLimiteDeEntidades() {
		if(this.tipo == TipoAdm.direta) {
			return this.entidades.isEmpty();
		}
		
		return true;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administracao other = (Administracao) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

}
