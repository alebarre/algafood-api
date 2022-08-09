package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;

public interface CidadeRepository {

	List<Cidade> todas();
	Cidade porId(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Cidade cidade);
	
}