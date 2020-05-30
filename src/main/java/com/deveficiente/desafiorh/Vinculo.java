package com.deveficiente.desafiorh;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
public class Vinculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private @NotNull @Valid ServidorPublico servidorPublico;
	@ManyToOne
	private @NotNull @Valid Entidade entidade;
	private boolean ativo = true;
	
	
	@Deprecated
	public Vinculo() {

	}

	public Vinculo(@NotNull @Valid ServidorPublico servidorPublico, @NotNull @Valid Entidade entidade) {
		Assert.isTrue(!servidorPublico.temVinculoAtivo(entidade),"Este vinculo nao pode ser criado dado que o servidor ja tem vinculo ativo com a entidade");
		this.servidorPublico = servidorPublico;
		this.entidade = entidade;
		
	}

	public boolean pertenceAEntidade(@NotNull @Valid Entidade outraEntidade) {
		return this.entidade.equals(outraEntidade);
	}

	
}
