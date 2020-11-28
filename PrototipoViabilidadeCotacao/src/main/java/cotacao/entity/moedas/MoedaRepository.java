package br.com.cotacao.entidade.repository;

import java.util.Arrays;

import br.com.cotacao.entidade.datasource.Moedas;

//Dados dos Estados
public class MoedaRepository {

	private Moedas[] value;

	public Moedas[] getValue() {
		return value;
	}

	public void setData(Moedas[] value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Arrays.toString(value);
	}
}
