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
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class AlteracaoEstadoMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository cadastro = applicationContext.getBean(EstadoRepository.class);		
		
		List<Estado> listarInicial = cadastro.todos();
		listarTodos(listarInicial);
		
		Estado estado = new Estado();
		estado.setId(1L);
		estado.setNome("Rio Grande do Sul");
		
		cadastro.salvar(estado);
		
		List<Estado> listarFinal = cadastro.todos();
		listarTodos(listarFinal);
		
	}
	
	public static void listarTodos(List<Estado> listar) {
		for(Estado item: listar) {
			System.out.printf("Estado %s:\n", item.getNome());
		}
	}
	
}
