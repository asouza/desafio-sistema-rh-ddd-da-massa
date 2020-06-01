package com.deveficiente.desafiorh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntidadeTest {

	@Test
	public void deveAdicionarEntidade() {
		Administracao adm1 = new Administracao("adm1",TipoAdm.direta);
		adm1.adicionaEntidade("entidade1");
	}
	
	@Test
	@DisplayName("uma adm so pode ter uma entidade direta")
	public void naoPodeTerMaisDeUmaEntidadeDireta() {
		Administracao adm1 = new Administracao("adm1",TipoAdm.direta);
		adm1.adicionaEntidade("entidade1");
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			adm1.adicionaEntidade("entidade2");			
		});
	}
}
