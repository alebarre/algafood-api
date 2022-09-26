package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	//Instancia de CadastroRestauranteService, unica para cadastro de restaurante
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	//LISTAR JSON
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurante> todas1(){
		return restauranteRepository.findAll();
	}
	
	//BUSCAR POR ID
	@GetMapping("/{restaurantesId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restaurantesId){
		
		Optional <Restaurante> restaurante = restauranteRepository.findById(restaurantesId);
		
		if (restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}else {
			return ResponseEntity.notFound().build();
		}	
	}
	
	//ADICIONAR
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		}catch(EntidadeNaoEncontradaException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//ATUALIZAR
		@PutMapping("/{restaurantesId}")
		public ResponseEntity<Restaurante> atualizar(@PathVariable Long restaurantesId,
												 @RequestBody Restaurante restaurantes){
			
			Optional <Restaurante> restauranteAtual = restauranteRepository.findById(restaurantesId);
			
			if (restauranteAtual.isPresent()) {
				
				//restauranteAtual.setNome(restaurante.getNome()); mais simplificado na forma abaixo
				BeanUtils.copyProperties(restaurantes, restauranteAtual.get(), "id");
				
				Restaurante restauranteSalvo = cadastroRestaurante.salvar(restauranteAtual.get());
				
				return ResponseEntity.ok(restauranteSalvo);
				
			}else {
				return ResponseEntity.notFound().build();
			}
		}
	
		//DELETAR
		@DeleteMapping("/{restauranteId}")
		public ResponseEntity<Estado> remover(@PathVariable Long restauranteId){
			
			//Trata se há conflitos, como em chaves estrangeiras.
			try {
				
				cadastroRestaurante.excluir(restauranteId);
				return ResponseEntity.noContent().build();
				
			}catch(EntidadeNaoEncontradaException e) {
				
				return ResponseEntity.notFound().build();
				
			}catch(EntidadeEmUsoException e){
				
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
				
			}
		}
		
		//ATUALIZAR PARCIAL
		@PatchMapping("/{restauranteId}")
		public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos){
			
			//Faz a busca do restaurante
			Restaurante restauranteAtual = restauranteRepository.findById(restauranteId).orElse(null);
			
			//Testa se e null
			if(restauranteAtual == null) {
				return ResponseEntity.notFound().build();
			}
			
			//Chama o metodo que mescla o que veio do POST com o restauranteAtual
			merge(campos, restauranteAtual);
			
			return atualizar(restauranteId, restauranteAtual);
			
		}

		//EndPoint "ATUALIZAR PARCIAL" - Mescla o conteúdo de 'restauranteAtual' usando a API 'reflections' do Spring
		public void merge(Map<String, Object> dados, Restaurante restaurante) {
			
			
			//Conversão automatica dos tipos dentro do objeto
			ObjectMapper objetcMapper = new ObjectMapper();
			
			//Faz a conversão.
			Restaurante restauranteOrigem = objetcMapper.convertValue(dados, Restaurante.class);
			
			System.out.println(restauranteOrigem);
			
			dados.forEach((chave, valor) -> {
				
				Field campo = ReflectionUtils.findField(Restaurante.class, chave);
				campo.setAccessible(true);//necessário pois os atributos de 'Restaurante' são 'private'.
				
				Object novoValor = ReflectionUtils.getField(campo, restauranteOrigem);
				
				System.out.println(chave + " - " + valor + " - " + novoValor);
				
				ReflectionUtils.setField(campo, restaurante, novoValor);
				
			});
		}
	
}
