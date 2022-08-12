package com.algaworks.algafood.api;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.server.HttpHeaderAccessManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

import ch.qos.logback.core.status.Status;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> todas1(){
		return cozinhaRepository.todas();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXmlformatado(){
		return new CozinhasXmlWrapper(cozinhaRepository.todas());
	}
	
	//@ResponseStatus(code = HttpStatus.CREATED)
	@GetMapping("/{cozinhaId}")
	//public Cozinha buscar(@PathVariable Long cozinhaId){
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId){
		
		Cozinha cozinha = cozinhaRepository.porId(cozinhaId);
		
		//return cozinhaRepository.porId(cozinhaId);
			
		//return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//		return ResponseEntity
//				.status(HttpStatus.FOUND)
//				.headers(headers)
//				.build();
		
		if (cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}else {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  ou usar a forma resumida abaixo
			return ResponseEntity.notFound().build();
		}	
	}
}
