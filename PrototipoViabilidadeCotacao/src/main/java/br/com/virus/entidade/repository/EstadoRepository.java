package br.com.virus.entidade.repository;

import java.util.Arrays;

import br.com.virus.entidade.datasource.Estados;

//Dados dos Estados
public class EstadoRepository {
	
	private Estados[] data;
	
	public Estados[] getData() {
		return data;
	}

	public void setData(Estados[] data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(data);
	}
}
