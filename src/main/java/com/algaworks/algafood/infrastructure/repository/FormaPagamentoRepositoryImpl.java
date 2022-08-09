package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	//LISTAR
	@Override
	public List<FormaPagamento> todas(){
		
		//TypedQuery<Cozinha> query = manager.createQuery("from FormaPagamento", FormaPagamento.class);
		//return query.getResultList();
		
		//Mesma implementação de cima, simplificada.
		return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
		
	}
	
	//BUSCAR ESPECIFICO
	@Transactional
	@Override
	public FormaPagamento porId(Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	//CADASTRAR
	@Transactional
	@Override
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return manager.merge(formaPagamento);
	}
	
	//EXCLUIR
	@Transactional
	@Override
	public void remover(FormaPagamento formaPagamento) {
		formaPagamento = porId(formaPagamento.getId());
		manager.remove(formaPagamento);
	}
	
}
