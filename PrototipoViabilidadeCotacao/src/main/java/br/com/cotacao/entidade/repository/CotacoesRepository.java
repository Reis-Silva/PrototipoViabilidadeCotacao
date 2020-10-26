package br.com.cotacao.entidade.repository;

import br.com.cotacao.entidade.datasource.Moedas;

//Dados Gerais
public class CotacoesRepository {

	private Moedas value;

	public Moedas getValue() {
		return value;
	}

	public void setValue(Moedas value) {
		this.value = value;
	}

}
