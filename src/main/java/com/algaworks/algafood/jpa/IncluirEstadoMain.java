package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
	
public class IncluirEstadoMain {
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository cadastroRestaurante = applicationContext.getBean(EstadoRepository.class);		
		
		Estado estado1 = new Estado();
		estado1.setNome("Amapá");
		
		Estado estado2 = new Estado();
		estado2.setNome("Acre");
		
		estado1 = cadastroRestaurante.salvar(estado1);
		estado2 = cadastroRestaurante.salvar(estado2);

		List<Estado> todosEstados = cadastroRestaurante.todos();
		listarFim(todosEstados);
		
	}
	
	public static void listarFim(List<Estado> todosEstados) {
		for(Estado item: todosEstados) {
			System.out.printf("Estado: %s\n", item.getNome());
		}
	}
}
