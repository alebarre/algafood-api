package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

public class AlteracaoCidadeMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cadastro= applicationContext.getBean(CidadeRepository.class);		
		
		Cidade cidade = new Cidade();
		cidade.setId(3L);
		cidade.setNome("Araruama");
		
		cadastro.salvar(cidade);
		
		List<Cidade> todasCidades = cadastro.todas();
		listarFim(todasCidades);
		
	}
	
	public static void listarFim(List<Cidade> todasCidades) {
		for(Cidade item: todasCidades) {
			System.out.printf("Cidade: %s\n", item.getNome());
		}
	}
}
