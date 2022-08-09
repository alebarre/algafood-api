package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	//LISTAR
	@Override
	public List<Permissao> todas(){
		
		//TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
		//return query.getResultList();
		
		//Mesma implementação de cima, simplificada.
		return manager.createQuery("from Permissao", Permissao.class).getResultList();
		
	}
	
	//BUSCAR ESPECIFICO
	@Transactional
	@Override
	public Permissao porId(Long id) {
		return manager.find(Permissao.class, id);
	}
	
	//CADASTRAR
	@Transactional
	@Override
	public Permissao salvar (Permissao permissao) {
		return manager.merge(permissao);
	}
	
	//EXCLUIR
	@Transactional
	@Override
	public void remover(Permissao permissao) {
		permissao = porId(permissao.getId());
		manager.remove(permissao);
	}
	
}
