package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository cadastroRrestaurante = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> todosrestaurantes = cadastroRrestaurante.todos();
		
		for (Restaurante restaurante :  todosrestaurantes) {
			System.out.printf("Restaurante: " + restaurante.getNome() 
			+ " - taxa de entrega R$ " + restaurante.getTaxaFrete() 
			+ " - cozinha: " + restaurante.getCozinha().getNome() 
			+ "\n");
		}
		
	}
	
}
