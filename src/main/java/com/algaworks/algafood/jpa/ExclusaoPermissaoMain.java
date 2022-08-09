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
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

public class ExclusaoPermissaoMain {
	

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository cozinhas = applicationContext.getBean(PermissaoRepository.class);		
		
		List<Permissao> listar1 = cozinhas.todas();
		listarTodos(listar1);
		
		Permissao permissao = new Permissao();
		permissao.setId(2L);
		
		cozinhas.remover(permissao);
		
		List<Permissao> listar2 = cozinhas.todas();
		listarTodos(listar2);
		
	}
	
	public static void listarTodos(List<Permissao> listar) {
		int cont = 0;
		for(Permissao item: listar) {
			System.out.printf("Permissao %s: - %s\n ",item.getNome(), item.getDescricao());
			cont++;
		}
	}
	
}
