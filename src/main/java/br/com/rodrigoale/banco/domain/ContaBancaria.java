package br.com.rodrigoale.banco.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ContaBancaria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer conta;
	private Integer agencia;
	private Double saldo;
	
	@ManyToOne
	@JoinColumn(name="conta_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "conta")
	private List<Transacao> transacoes = new ArrayList<>();

	
	public ContaBancaria() {
		
	}

	public ContaBancaria(Integer conta, Integer agencia, Double saldo, Cliente cliente) {

		this.conta = conta;
		this.agencia = agencia;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	@Override
	public String toString() {
		return "conta=" + conta + ", agencia=" + agencia + ", saldo=" + saldo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaBancaria other = (ContaBancaria) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		return true;
	}
	
	
	
	
}
