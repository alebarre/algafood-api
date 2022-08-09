package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;

//Evento que ativa o Cliente
public class ClienteAtivadoEvent {

	private Cliente cliente;

	public ClienteAtivadoEvent(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	
	
}
