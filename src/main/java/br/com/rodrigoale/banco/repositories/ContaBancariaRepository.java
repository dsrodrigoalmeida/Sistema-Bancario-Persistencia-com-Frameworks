package br.com.rodrigoale.banco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigoale.banco.domain.ContaBancaria;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {
	ContaBancaria findByConta(Integer conta);
	List<ContaBancaria> findByAgencia(Integer agencia);
	List<ContaBancaria> findByClienteCpfOuCpnj(Integer cpfOuCpnj);

}
