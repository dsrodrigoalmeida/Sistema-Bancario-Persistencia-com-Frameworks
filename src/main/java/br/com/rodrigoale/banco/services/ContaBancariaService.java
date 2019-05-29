package br.com.rodrigoale.banco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigoale.banco.domain.ContaBancaria;
import br.com.rodrigoale.banco.repositories.ContaBancariaRepository;

@Service
public class ContaBancariaService {

	@Autowired
	private ContaBancariaRepository repo;

	public ContaBancaria find(Integer id) {
		ContaBancaria obj = repo.findByConta(id);
		return obj;

	}

	public ContaBancaria insert(ContaBancaria obj) {
		return repo.save(obj);
	}
	public ContaBancaria update(ContaBancaria obj) {
		return repo.save(obj);
	}

	public List<ContaBancaria> findByAgencia(Integer agencia) {
		List<ContaBancaria> lista = repo.findByAgencia(agencia);
		return lista;
	}
	
	public List<ContaBancaria> findByCpfOuCnpj(Integer cpfOuCpnj) {
		List<ContaBancaria> lista = repo.findByClienteCpfOuCpnj(cpfOuCpnj);
		return lista;
	}
}