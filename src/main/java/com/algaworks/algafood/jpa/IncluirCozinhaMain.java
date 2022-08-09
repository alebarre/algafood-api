package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class IncluirCozinhaMain {

public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);		
		
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Brasileira");
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Japonesa");
		
		cozinha = cadastroCozinha.salvar(cozinha);
		cozinha1 = cadastroCozinha.salvar(cozinha1);
		
		System.out.printf("%d - %s/n", cozinha.getId(), cozinha.getNome());
		System.out.printf("%d - %s/n", cozinha1.getId(), cozinha1.getNome());
		
	}
	
}
