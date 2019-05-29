package br.com.rodrigoale.banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigoale.banco.domain.Transacao;
import br.com.rodrigoale.banco.repositories.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository repo;

	
	public Transacao insert(Transacao obj) {
		return repo.save(obj);
	}
}