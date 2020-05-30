package com.deveficiente.desafiorh;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

	public ServidorPublico(@NotBlank String nome, @NotNull @Valid Cargo cargo,
			@NotNull Natureza natureza) {
		this.nome = nome;
		this.cargo = cargo;
		this.natureza = natureza;
	}

}
