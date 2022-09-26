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
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

import ch.qos.logback.core.status.Status;

@RestController
@RequestMapping("/formaPagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	//Instancia de CadastroformaPagamentoService, unica para cadastro de formaPagamento
	@Autowired
	private CadastroFormaPagamentoService cadastroformaPagamento;
	
	//LISTAR JSON
	@GetMapping
	public List<FormaPagamento> todas(){
		return formaPagamentoRepository.findAll();
	}
	
	//BUSCAR POR ID
	//@ResponseStatus(code = HttpStatus.CREATED)
	@GetMapping("/{formaPagamentoId}")
	//public formaPagamento buscar(@PathVariable Long formaPagamentoId){
	public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId){
		
		Optional <FormaPagamento> formaPagamento= formaPagamentoRepository.findById(formaPagamentoId);
		
		if (formaPagamento.isPresent()) {
			return ResponseEntity.ok(formaPagamento.get());
		}else {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  ou usar a forma resumida abaixo
			return ResponseEntity.notFound().build();
		}	
	}
	
	//INCLUIR
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamento adicionar (@RequestBody FormaPagamento formaPagamento) {
		//Cadastro de formaPagamento usando o metodo exclusivo (CadstroformaPagamentoService)
		return cadastroformaPagamento.salvar(formaPagamento);
	}
	
	//ALTERAR
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long formaPagamentoId,
											 @RequestBody FormaPagamento formaPagamento){
		Optional <FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);
		
		if (formaPagamentoAtual.isPresent()) {
			
			//formaPagamentoAtual.setNome(formaPagamento.getNome()); mais simplificado na forma abaixo
			BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual.get(), "id");
			
			FormaPagamento formaPagamentoSalva = cadastroformaPagamento.salvar(formaPagamentoAtual.get());
			
			return ResponseEntity.ok(formaPagamentoSalva);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//DELETAR
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> remover(@PathVariable Long formaPagamentoId){
		
		//Trata se há conflitos, como em chaves estrangeiras.
		try {
			
			cadastroformaPagamento.excluir(formaPagamentoId);
			return ResponseEntity.noContent().build();
			
		}catch(EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeEmUsoException e){	
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}
	}
}
