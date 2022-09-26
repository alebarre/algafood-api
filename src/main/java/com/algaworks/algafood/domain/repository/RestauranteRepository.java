package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;


@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>
				, RestauranteRepositoryQueries
				, JpaSpecificationExecutor<Restaurante> {
	
	//usando o prefixo find e between
	//Retorna restaurantes por taxas de frete, usando o BETWEEN do SQL
	List<Restaurante> findByTaxaFreteBetween (BigDecimal taxaInicial, BigDecimal taxaFinal);
		
	//Usando a anotação @Param que utiliza o qualquer nome para o método, fazendo bind de parametros.
	//@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	//Tambem esta implementado externalizado com o arquivo orm.xml
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);
	
	//usando o prefixo find, containg e And
	//Retorna restaurantes por taxas de frete, usando o BETWEEN do SQL
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
	
	//usando o prefixo find, containg e First
	//Retorna restaurantes filtrando so o primeiro resultado LIKE e LIMIT
	Optional<Restaurante> findFirstByNomeContaining(String nome);
	
	//usando o prefixo find e Top
	//Retorna restaurantes filtrando só os primeiros 2
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
	//conta restaurantes que tenham id da cozinha COUNT
	int countByCozinhaId(Long cozinha);
	
	//Implementação feita na classe ResrautanteRepositoryImpl
	List<Restaurante> find (String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	
}