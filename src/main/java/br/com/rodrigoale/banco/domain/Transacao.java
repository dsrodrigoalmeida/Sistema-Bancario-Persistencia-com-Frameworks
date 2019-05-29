package br.com.rodrigoale.banco.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.rodrigoale.banco.domain.enums.TipoTransacao;

@Entity
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer tipo;
	private Double valor;
	private Date dataTransacao;
	
	@ManyToOne
	@JoinColumn(name="conta_cpf")
	@JsonIgnore
	private ContaBancaria conta;


	public Transacao() {

	}

	public Transacao(Integer id, TipoTransacao tipo, Double valor, Date dataTransacao,ContaBancaria conta) {
		this.id = id;
		this.tipo = tipo.getCod();
		this.valor = valor;
		this.dataTransacao = dataTransacao;
		this.conta = conta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoTransacao getTipo() {
		return TipoTransacao.toEnum(tipo);
	}

	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo.getCod();
	}
	
	

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
	public ContaBancaria getConta() {
		return conta;
	}

	public void setConta(ContaBancaria conta) {
		this.conta = conta;
	}

	
	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}


	@Override
	public String toString() {
		return "Transacao [id=" + id + ", tipo=" + tipo + ", valor=" + valor + ", dataTransacao=" + dataTransacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
