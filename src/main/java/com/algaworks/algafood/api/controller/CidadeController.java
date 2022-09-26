package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

	@Autowired
	private CidadeRepository CidadeRepository;
	
	//Instancia de CadastroCidadeService, unica para cadastro de Cidade
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	//LISTAR JSON
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cidade> todas(){
		return CidadeRepository.findAll();
	}
	
	//BUSCAR POR ID
	//@ResponseStatus(code = HttpStatus.CREATED)
	@GetMapping("/{cidadeId}")
	//public Cidade buscar(@PathVariable Long cidadeId){
	public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId){
		
		Optional <Cidade> cidade= CidadeRepository.findById(cidadeId);
		
		if (cidade.isPresent()) {
			return ResponseEntity.ok(cidade.get());
		}else {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  ou usar a forma resumida abaixo
			return ResponseEntity.notFound().build();
		}	
	}
	
	//INCLUIR
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar (@RequestBody Cidade cidade) {
		//Cadastro de Cidade usando o metodo exclusivo (CadstroCidadeService)
		return cadastroCidade.salvar(cidade);
	}
	
	//ALTERAR
	@PutMapping("/{cidadeId}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId,
											 @RequestBody Cidade Cidade){
		Optional <Cidade> cidadeAtual = CidadeRepository.findById(cidadeId);
		
		if (cidadeAtual.isPresent()) {
			
			//CidadeAtual.setNome(Cidade.getNome()); mais simplificado na forma abaixo
			BeanUtils.copyProperties(Cidade, cidadeAtual.get(), "id");
			
			Cidade cidadeSalva = cadastroCidade.salvar(cidadeAtual.get());
			
			return ResponseEntity.ok(cidadeSalva);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//DELETAR
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId){
		
		//Trata se há conflitos, como em chaves estrangeiras.
		try {
			
			cadastroCidade.excluir(cidadeId);
			return ResponseEntity.noContent().build();
			
		}catch(EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeEmUsoException e){	
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}
	}
}
