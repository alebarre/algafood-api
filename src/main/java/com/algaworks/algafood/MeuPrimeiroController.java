package com.algaworks.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {
	
	private AtivacaoClienteService ativacaoClienteService;

	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
		
	}


	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		
		Cliente alexandre = new Cliente("Alexandre Barreto", "plotartemail@gmail.com", "21998526500");
		ativacaoClienteService.ativar(alexandre);
		
		return "Hello";
	}
	
}
