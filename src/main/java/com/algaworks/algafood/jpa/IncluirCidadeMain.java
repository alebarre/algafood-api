package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class IncluirCidadeMain {

	public static void main(String[] args) {
			
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cadastro = applicationContext.getBean(CidadeRepository.class);		
		
		List<Cidade> listarTodasInicio = cadastro.todas();
		listarTodos(listarTodasInicio);
		
		Cidade cidade1 = new Cidade();
		cidade1.setNome("São Fidélis");
		
		Cidade cidade2 = new Cidade();
		cidade2.setNome("Niterói");
		
		Cidade cidade3 = new Cidade();
		cidade3.setNome("Teresópolis");
		
		cidade1 = cadastro.salvar(cidade1);
		cidade2 = cadastro.salvar(cidade2);
		cidade3= cadastro.salvar(cidade3);
		
		List<Cidade> listarTodasFim = cadastro.todas();
		listarTodos(listarTodasFim);
		
	}
	
	public static void listarTodos (List<Cidade> listarTodas) {
		for(Cidade item: listarTodas) {
			System.out.printf("Cidade: %s\n", item.getNome());
		}
	}
	
}


