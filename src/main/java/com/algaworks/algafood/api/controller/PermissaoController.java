package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.service.CadastroPermissaoService;

@RestController
@RequestMapping("/permissao")
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	//Instancia de CadastropermissaoService, unica para cadastro de permissao
	@Autowired
	private CadastroPermissaoService cadastropermissao;
	
	//LISTAR 
	@GetMapping
	public List<Permissao> todas(){
		return permissaoRepository.findAll();
	}
	
	//BUSCAR POR ID
	@GetMapping("/{permissaoId}")
	public ResponseEntity<Permissao> buscar(@PathVariable Long permissaoId){
		
		Optional <Permissao> permissao= permissaoRepository.findById(permissaoId);
		
		if (permissao.isPresent()) {
			return ResponseEntity.ok(permissao.get());
		}else {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  ou usar a forma resumida abaixo
			return ResponseEntity.notFound().build();
		}	
	}
	
	//INCLUIR
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Permissao adicionar (@RequestBody Permissao permissao) {
		//Cadastro de permissao usando o metodo exclusivo (CadstropermissaoService)
		return cadastropermissao.salvar(permissao);
	}
	
	//ALTERAR
	@PutMapping("/{permissaoId}")
	public ResponseEntity<Permissao> atualizar(@PathVariable Long permissaoId,
											 @RequestBody Permissao permissao){
		Optional <Permissao> permissaoAtual = permissaoRepository.findById(permissaoId);
		
		if (permissaoAtual.isPresent()) {
			
			//permissaoAtual.setNome(permissao.getNome()); mais simplificado na forma abaixo
			BeanUtils.copyProperties(permissao, permissaoAtual.get(), "id");
			
			Permissao permissaoSalva = cadastropermissao.salvar(permissaoAtual.get());
			
			return ResponseEntity.ok(permissaoSalva);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//DELETAR
	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<Permissao> remover(@PathVariable Long permissaoId){
		
		//Trata se há conflitos, como em chaves estrangeiras.
		try {
			
			cadastropermissao.excluir(permissaoId);
			return ResponseEntity.noContent().build();
			
		}catch(EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeEmUsoException e){	
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}
	}
}
