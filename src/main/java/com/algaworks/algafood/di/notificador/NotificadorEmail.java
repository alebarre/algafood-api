package com.algaworks.algafood.di.notificador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@TipoDoNotificador (NivelUrgencia.BAIXA_PRIORIDADE)
@Component
public class NotificadorEmail implements Notificador  {

	@Autowired
	private NotificadorProperties properties;
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.println("host: " + properties.getHostServidor());
		System.out.println("port: " + properties.getPortaServidor());

		System.out.printf("Notificando %s, atavés do e-mail: %s - %s\n", 
				cliente.getNome(), 
				cliente.getEmail(), 
				mensagem);
	}

}

