package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class AlteracaoPermissaoMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository cadastroRestaurante = applicationContext.getBean(PermissaoRepository.class);		
		
		Permissao permissao = new Permissao();
		permissao.setId(1L);
		permissao.setNome("Garçom");
		permissao.setDescricao("Listar");
		
		cadastroRestaurante.salvar(permissao);
		
		cadastroRestaurante.todas();
		
		List<Permissao> listarTodasFim = cadastroRestaurante.todas();
		listarTodos(listarTodasFim);
		
	}
	
	public static void listarTodos (List<Permissao> listarTodas) {
		for(Permissao item: listarTodas) {
			System.out.printf("Permissao para %s abrange: %s\n", item.getNome(), item.getDescricao());
		}
	}
	
}
