package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository{

	@PersistenceContext
	private EntityManager manager;
	
	//LISTAR
	@Override
	public List<Cozinha> todas(){
		
		//TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
		//return query.getResultList();
		
		//Mesma implementação de cima, simplificada.
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
		
	}
	
	//BUSCAR ESPECIFICO
	@Transactional
	@Override
	public Cozinha porId(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	//CADASTRAR
	@Transactional
	@Override
	public Cozinha salvar (Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	//EXCLUIR
	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		cozinha = porId(cozinha.getId());
		manager.remove(cozinha);
	}
	
}
