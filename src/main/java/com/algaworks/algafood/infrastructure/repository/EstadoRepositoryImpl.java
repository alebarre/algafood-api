package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	//LISTAR
	@Override
	public List<Estado> todos(){
		
		//TypedQuery<Estado> query = manager.createQuery("from Estado", Cozinha.class);
		//return query.getResultList();
		
		//Mesma implementação de cima, simplificada.
		return manager.createQuery("from Estado", Estado.class).getResultList();
		
	}
	
	//BUSCAR ESPECIFICO
	@Transactional
	@Override
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}
	
	//CADASTRAR
	@Transactional
	@Override
	public Estado salvar (Estado estado) {
		return manager.merge(estado);
	}
	
	//EXCLUIR
	@Transactional
	@Override
	public void remover(Estado estado) {
		estado = porId(estado.getId());
		manager.remove(estado);
	}
	
}
