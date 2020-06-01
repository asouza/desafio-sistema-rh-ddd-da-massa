package com.deveficiente.desafiorh;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioSistemaRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSistemaRhApplication.class, args);
	}
	
	@PersistenceContext
	private EntityManager manager;
	


}
