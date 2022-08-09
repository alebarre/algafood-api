package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class ConsultaPermissaoMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository cadastroRrestaurante = applicationContext.getBean(PermissaoRepository.class);
		
		List<Permissao> todosrestaurantes = cadastroRrestaurante.todas();
		
		for (Permissao permissao :  todosrestaurantes) {
			System.out.printf("Permissao: " + permissao.getNome() 
			+ " - Descrição: " + permissao.getDescricao() + "\n");
		}
		
	}
	
}
