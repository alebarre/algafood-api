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

public class AlteracaoFormaPagamentoMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository cadastroRestaurante = applicationContext.getBean(FormaPagamentoRepository.class);		
		
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setId(1L);
		formaPagamento.setDescricao("PayPal");
		
		cadastroRestaurante.salvar(formaPagamento);
		
		cadastroRestaurante.todas();
		
		List<FormaPagamento> listarTodasFim = cadastroRestaurante.todas();
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
