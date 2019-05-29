package br.com.rodrigoale.banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigoale.banco.domain.Cliente;
import br.com.rodrigoale.banco.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Cliente obj = repo.findByCpfOuCpnj(id);
		return obj;

	}

	public Cliente insert(Cliente obj) {
		return repo.save(obj);
	}
}