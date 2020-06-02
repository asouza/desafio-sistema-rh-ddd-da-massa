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

	public boolean ativoComAEntidade(@NotNull @Valid Entidade outraEntidade) {
		return this.entidade.equals(outraEntidade) && this.ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entidade == null) ? 0 : entidade.hashCode());
		result = prime * result
				+ ((servidorPublico == null) ? 0 : servidorPublico.hashCode());
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
		Vinculo other = (Vinculo) obj;
		if (entidade == null) {
			if (other.entidade != null)
				return false;
		} else if (!entidade.equals(other.entidade))
			return false;
		if (servidorPublico == null) {
			if (other.servidorPublico != null)
				return false;
		} else if (!servidorPublico.equals(other.servidorPublico))
			return false;
		return true;
	}
	
	

	
}
