package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class IncluirFromaPagamentoMain {

	public static void main(String[] args) {
			
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository cadastroCozinha = applicationContext.getBean(FormaPagamentoRepository.class);		
		
		List<FormaPagamento> listarTodasInicio = cadastroCozinha.todas();
		listarTodos(listarTodasInicio);
		
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setDescricao("Cartão de Crédito");
		
		FormaPagamento formaPagamento1 = new FormaPagamento();
		formaPagamento1.setDescricao("PIX");
		
		FormaPagamento formaPagamento2 = new FormaPagamento();
		formaPagamento2.setDescricao("AME");
		
		formaPagamento = cadastroCozinha.salvar(formaPagamento);
		formaPagamento1 = cadastroCozinha.salvar(formaPagamento1);
		
		List<FormaPagamento> listarTodasFim = cadastroCozinha.todas();
		listarTodos(listarTodasFim);
		
	}
	
	public static void listarTodos (List<FormaPagamento> listarTodas) {
		int cont = 1;
		for(FormaPagamento item: listarTodas) {
			System.out.printf("Forma de Pagamento %d: %s\n", cont, item.getDescricao());
			cont++;
		}
	}
	
}


