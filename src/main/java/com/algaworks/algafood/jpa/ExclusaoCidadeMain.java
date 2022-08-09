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
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class ExclusaoCidadeMain {
	

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cadastro = applicationContext.getBean(CidadeRepository.class);		
		
		List<Cidade> listarInicio = cadastro.todas();
		listarTodos(listarInicio);
		
		Cidade cidade = new Cidade();
		cidade.setId(2L);
		
		cadastro.remover(cidade);
		
		List<Cidade> listarFinal = cadastro.todos();
		listarTodos(listarFinal);
		
	}
	
	public static void listarTodos(List<Cidade> listar) {
		for(Cidade item: listar) {
			System.out.printf("Cidade %s:\n", item.getNome());
		}
	}
	
}
