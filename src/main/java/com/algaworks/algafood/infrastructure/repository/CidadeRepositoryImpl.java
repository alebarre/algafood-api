package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository{

	@PersistenceContext
	private EntityManager manager;
	
	//LISTAR
	@Override
	public List<Cidade> todas(){
		
		//TypedQuery<Cozinha> query = manager.createQuery("from Cidade", Cidade.class);
		//return query.getResultList();
		
		//Mesma implementação de cima, simplificada.
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
		
	}
	
	//BUSCAR ESPECIFICO
	@Transactional
	@Override
	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	//CADASTRAR
	@Transactional
	@Override
	public Cidade salvar (Cidade cidade) {
		return manager.merge(cidade);
	}
	
	//EXCLUIR
	@Transactional
	@Override
	public void remover(Cidade cidade) {
		cidade = porId(cidade.getId());
		manager.remove(cidade);
	}
	
}
