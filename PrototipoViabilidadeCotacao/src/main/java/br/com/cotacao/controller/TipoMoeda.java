package br.com.cotacao.controller;

public enum TipoMoeda {

	USD("1", "USD"),
	EUR("2", "EUR" ),
	CAD("3", "CAD" );
	
	private String valor;
	private String descricao;

	TipoMoeda(String valor, String descricao) {
		this.valor = valor;
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	
	
}
