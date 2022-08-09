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
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class ExclusaoRestauranteaMain {
	

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository cadastroRestaurante = applicationContext.getBean(FormaPagamentoRepository.class);		
		
		List<FormaPagamento> listarInicio = cadastroRestaurante.todas();
		listarTodos(listarInicio);
		
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setId(2L);
		
		cadastroRestaurante.remover(formaPagamento);
		
		List<FormaPagamento> listarFinal = cadastroRestaurante.todas();
		listarTodos(listarFinal);
		
	}
	
	
	
	public static void listarTodos(List<FormaPagamento> listar) {
		int cont = 1;
		for(FormaPagamento item: listar) {
			System.out.printf("Cozinha %s: - %s\n ",cont, item.getDescricao());
			cont++;
		}
	}
	
}
