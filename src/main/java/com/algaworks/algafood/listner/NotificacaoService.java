package com.algaworks.algafood.listner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.notificador.NivelUrgencia;
import com.algaworks.algafood.di.notificador.Notificador;
import com.algaworks.algafood.di.notificador.TipoDoNotificador;
import com.algaworks.algafood.di.service.ClienteAtivadoEvent;

//Classe que escuta os eventos que foram disparados
@Component
public class NotificacaoService {
	
	@TipoDoNotificador(NivelUrgencia.BAIXA_PRIORIDADE)
	@Autowired
	private Notificador notificador;
	
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro esta ativo.");
	}

}
