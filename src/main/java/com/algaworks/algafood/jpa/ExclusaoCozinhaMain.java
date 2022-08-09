package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

public class ExclusaoCozinhaMain {
	

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);		
		
		List<Cozinha> listar1 = cozinhas.todas();
		listarTodos(listar1);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(3L);
		
		cozinhas.remover(cozinha);
		
		List<Cozinha> listar2 = cozinhas.todas();
		listarTodos(listar2);
		
	}
	
	public static void listarTodos(List<Cozinha> listar) {
		int cont = 0;
		for(Cozinha item: listar) {
			System.out.printf("Cozinha %s: - %s\n ",cont, item.getNome());
			cont++;
		}
	}
	
}
