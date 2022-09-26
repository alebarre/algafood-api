package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {
	
	@Autowired
	private PermissaoRepository PermissaoRepository;
	
	public Permissao salvar (Permissao Permissao) {
		return PermissaoRepository.save(Permissao);
	}
	
	public void excluir (Long PermissaoId) {
		try {
			PermissaoRepository.deleteById(PermissaoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de Permissao com o código %d"
					, PermissaoId));
		}catch(DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(
					String.format("Permissao código %d não pode ser removida. Cozinha em uso."
					, PermissaoId));
		}
	}
}
