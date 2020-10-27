package br.com.cotacao.entidade.datasource;

public class Moedas {
	
	private double paridadeCompra;
	private double paridadeVenda;
	private double cotacaoCompra;
	private double cotacaoVenda;
	private String tipoBoletim;
	
	
	public double getParidadeCompra() {
		return paridadeCompra;
	}
	public void setParidadeCompra(double paridadeCompra) {
		this.paridadeCompra = paridadeCompra;
	}
	public double getParidadeVenda() {
		return paridadeVenda;
	}
	public void setParidadeVenda(double paridadeVenda) {
		this.paridadeVenda = paridadeVenda;
	}
	public double getCotacaoCompra() {
		return cotacaoCompra;
	}
	public void setCotacaoCompra(double cotacaoCompra) {
		this.cotacaoCompra = cotacaoCompra;
	}
	public double getCotacaoVenda() {
		return cotacaoVenda;
	}
	public void setCotacaoVenda(double cotacaoVenda) {
		this.cotacaoVenda = cotacaoVenda;
	}
	public String getTipoBoletim() {
		return tipoBoletim;
	}
	public void setTipoBoletim(String tipoBoletim) {
		this.tipoBoletim = tipoBoletim;
	}
	


}
