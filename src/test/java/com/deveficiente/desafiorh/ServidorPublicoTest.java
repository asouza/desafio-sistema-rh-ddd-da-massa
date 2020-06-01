package com.deveficiente.desafiorh;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServidorPublicoTest {

	@Test
	public void deveCriarVinculoComEntidade() {
		Cargo programador = new Cargo("programador",new BigDecimal("7000"));
		ServidorPublico seya = new ServidorPublico("seya",programador ,Natureza.estatutaria);
		
		Administracao adm1 = new Administracao("adm1",TipoAdm.direta);
		
		Entidade entidade1 = adm1.adicionaEntidade("entidade1");
		
		seya.adicionaVinculo(entidade1);		
	}
	
	@Test
	@DisplayName("o servidor pode ter vinculo com mais de uma entidade")
	public void podeCriarVinculoComMaisDeUmEntidade() {
		Cargo programador = new Cargo("programador",new BigDecimal("7000"));
		ServidorPublico seya = new ServidorPublico("seya",programador ,Natureza.estatutaria);
		
		Administracao adm1 = new Administracao("adm1",TipoAdm.direta);
		Administracao adm2 = new Administracao("adm1",TipoAdm.direta);
		
		Entidade entidade1 = adm1.adicionaEntidade("entidade1");
		Entidade entidade2 = adm2.adicionaEntidade("entidade2");
		
		seya.adicionaVinculo(entidade1);
		seya.adicionaVinculo(entidade2);
	}
	
	@Test
	@DisplayName("o servidor nao pode ter dois vinculos com a mesma entidade")
	public void naoPodeTerMaisDeUmVinculoComAMesmaEntidade() {
		Cargo programador = new Cargo("programador",new BigDecimal("7000"));
		ServidorPublico seya = new ServidorPublico("seya",programador ,Natureza.estatutaria);
		
		Administracao adm1 = new Administracao("adm1",TipoAdm.direta);
		
		Entidade entidade1 = adm1.adicionaEntidade("entidade1");
		
		seya.adicionaVinculo(entidade1);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			seya.adicionaVinculo(entidade1);			
		});
	}
}
