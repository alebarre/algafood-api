package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.server.HttpHeaderAccessManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

import ch.qos.logback.core.status.Status;

@RestController
@RequestMapping("/estado")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	//Instancia de EstadoService, unica para cadastro de Estado.
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	//LISTAR
	@GetMapping
	public List<Estado> todos(){
		return estadoRepository.findAll();
	}
	
	//BUSCAR POR ID
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
		
		Optional <Estado> estado = estadoRepository.findById(estadoId);
		
		if (estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}else {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  ou usar a forma resumida abaixo
			return ResponseEntity.notFound().build();
		}	
	}
	
	//ADICIONAR
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Estado estado){
		
		try {
			estado = cadastroEstado.salvar(estado);
			return ResponseEntity.status(HttpStatus.CREATED).body(estado);
		}catch(EntidadeNaoEncontradaException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//ALTERAR
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId,
											 @RequestBody Estado estado){
		
		Optional <Estado> estadoAtual = estadoRepository.findById(estadoId);
		
		if (estadoAtual.isPresent()) {
			
			//estadoAtual.setNome(estado.getNome()); mais simplificado na forma abaixo
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			
			Estado estadoSalvo = cadastroEstado.salvar(estadoAtual.get());
			
			return ResponseEntity.ok(estadoSalvo);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//DELETAR
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<Estado> remover(@PathVariable Long estadoId){
		
		//Trata se há conflitos, como em chaves estrangeiras.
		try {
			
			cadastroEstado.excluir(estadoId);
			return ResponseEntity.noContent().build();
			
		}catch(EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeEmUsoException e){
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}
	}
	
}
