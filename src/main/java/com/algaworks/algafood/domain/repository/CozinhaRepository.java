package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;


@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	//procura por nome
	List<Cozinha> findByNome (String nome);
	
	//procura por nome com o LIKE
	List<Cozinha> findByNomeContaining (String nome);
	
	//procura por nome unico
	Optional<Cozinha> findUnicoByNome(String nome);	
	
	//procura por nome unico e retorna TRUE ou FALSE
	boolean existsByNome(String nome);
	

	
}