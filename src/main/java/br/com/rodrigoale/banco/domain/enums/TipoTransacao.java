package br.com.rodrigoale.banco.domain.enums;

public enum TipoTransacao {

	CREDITO(1, "Crédito"), DEBITO(2, "Debito");

	private int cod;
	private String descricao;

	private TipoTransacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoTransacao toEnum(Integer id) {
		if (id == null)

		{
			return null;
		}
		for (TipoTransacao x : TipoTransacao.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
	
	
}