package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
	
public class IncluirRestauranteMain {
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository cadastroRestaurante = applicationContext.getBean(RestauranteRepository.class);		
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Amarelinho");
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Braseirinho da Glória");
		
		restaurante = cadastroRestaurante.salvar(restaurante);
		restaurante1 = cadastroRestaurante.salvar(restaurante1);
		
		System.out.printf("%d - %s/n", restaurante.getId(), restaurante.getNome());
		System.out.printf("%d - %s/n", restaurante1.getId(), restaurante1.getNome());
		
	}
	
}
