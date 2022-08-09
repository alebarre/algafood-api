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

public class ExclusaoFormaPagamentoMain {
	

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository cadastroRestaurante = applicationContext.getBean(RestauranteRepository.class);		
		
		List<Restaurante> listarInicio = cadastroRestaurante.todos();
		listarTodos(listarInicio);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setId(3L);
		
		cadastroRestaurante.remover(restaurante);
		
		List<Restaurante> listarFim = cadastroRestaurante.todos();
		listarTodos(listarFim);
		
	}
	
	public static void listarTodos(List<Restaurante> listar) {
		int cont = 0;
		for(Restaurante item: listar) {
			System.out.printf("Cozinha %s: - %s\n ",cont, item.getNome());
			cont++;
		}
	}
	
}
