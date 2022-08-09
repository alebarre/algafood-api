package com.algaworks.algafood.di.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificador.NivelUrgencia;
import com.algaworks.algafood.di.notificador.Notificador;
import com.algaworks.algafood.di.notificador.TipoDoNotificador;

@Component
public class AtivacaoClienteService {

	@TipoDoNotificador(NivelUrgencia.BAIXA_PRIORIDADE)
	@Autowired
	private ApplicationEventPublisher eventPublisher; //Classe do Spring que publica o eventoCz
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		//Evento que dispara a ativacao do cliente
		this.eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
	}
}