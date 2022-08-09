package com.algaworks.algafood.di.notificador;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@TipoDoNotificador (NivelUrgencia.ALTA_PRIORIDADE)
@Component
public class NotificadorSMS implements Notificador  {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s atavés do telefone %s: %s\n", 
				cliente.getNome(), 
				cliente.getTelefone(), 
				mensagem);
	}

}

