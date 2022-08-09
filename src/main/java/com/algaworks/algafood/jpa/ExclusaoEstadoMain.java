package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class ExclusaoEstadoMain {
	

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository cadastro = applicationContext.getBean(EstadoRepository.class);		
		
		List<Estado> listarInicio = cadastro.todos();
		listarTodos(listarInicio);
		
		Estado estado = new Estado();
		estado.setId(2L);
		
		cadastro.remover(estado);
		
		List<Estado> listarFinal = cadastro.todos();
		listarTodos(listarFinal);
		
	}
	
	public static void listarTodos(List<Estado> listar) {
		int cont = 1;
		for(Estado item: listar) {
			System.out.printf("Estado %s: - %s\n ",cont, item.getNome());
			cont++;
		}
	}
	
}
