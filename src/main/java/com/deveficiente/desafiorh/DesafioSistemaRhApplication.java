package com.deveficiente.desafiorh;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioSistemaRhApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DesafioSistemaRhApplication.class, args);
	}
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Cargo programador = new Cargo("programador",new BigDecimal("7000"));
		manager.persist(programador);
		ServidorPublico seya = new ServidorPublico("seya",programador ,Natureza.estatutaria);
		manager.persist(seya);
		
		Cargo marketing = new Cargo("marketing",new BigDecimal("10000"));
		manager.persist(marketing);
		ServidorPublico yoga = new ServidorPublico("yoga",marketing ,Natureza.comissionada);
		manager.persist(yoga);
	}

}
