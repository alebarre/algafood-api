package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class IncluirPermissaoMain {

public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository cadastroCozinha = applicationContext.getBean(PermissaoRepository.class);		
		
		Permissao permissao1 = new Permissao();
		permissao1.setNome("Servente");
		permissao1.setDescricao("Listar");
		
		Permissao permissao2 = new Permissao();
		permissao2.setNome("Estoquista");
		permissao2.setDescricao("Listar - Cadastrar");
		
		permissao1 = cadastroCozinha.salvar(permissao1);
		permissao2 = cadastroCozinha.salvar(permissao2);
		
		System.out.printf("%d - %s - (%s)\n", permissao1.getId(), permissao1.getNome(), permissao1.getDescricao());
		System.out.printf("%d - %s - (%s)\n", permissao2.getId(), permissao2.getNome(), permissao2.getDescricao());
		
	}
	
}
