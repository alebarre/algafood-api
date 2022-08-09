package com.algaworks.algafood.di.notificador;

import com.algaworks.algafood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}