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
		return novaEntidade;
	}

	boolean respeitaLimiteDeEntidades() {
		if(this.tipo == TipoAdm.direta) {
			return this.entidades.isEmpty();
		}
		
		return true;
	}

}
