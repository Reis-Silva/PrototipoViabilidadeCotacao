package br.com.cotacao.entidade.repository;

import java.util.Arrays;

import br.com.cotacao.entidade.datasource.Moedas;

//Dados dos Estados
public class EstadoRepository {

	private Moedas[] data;

	public Moedas[] getData() {
		return data;
	}

	public void setData(Moedas[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return Arrays.toString(data);
	}
}
