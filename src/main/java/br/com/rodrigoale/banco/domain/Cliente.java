package br.com.rodrigoale.banco.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.rodrigoale.banco.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private Integer cpfOuCpnj;
	private String nome;
	private Integer tipo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conta", cascade = {CascadeType.ALL})
	private List<ContaBancaria> contas = new ArrayList<>();
	
	
	public Cliente() {
		
	}

	public Cliente(Integer cpfOuCpnj, String nome, TipoCliente tipo) {
		this.cpfOuCpnj = cpfOuCpnj;
		this.nome = nome;
		this.tipo = tipo.getCod();
	}

	public Integer getCpfOuCpnj() {
		return cpfOuCpnj;
	}

	public void setId(Integer cpfOuCpnj) {
		this.cpfOuCpnj = cpfOuCpnj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public List<ContaBancaria> getContas() {
		return contas;
	}

	public void setContas(List<ContaBancaria> contas) {
		this.contas = contas;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpfOuCpnj == null) ? 0 : cpfOuCpnj.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpfOuCpnj == null) {
			if (other.cpfOuCpnj != null)
				return false;
		} else if (!cpfOuCpnj.equals(other.cpfOuCpnj))
			return false;
		return true;
	}


	
	
	
}
