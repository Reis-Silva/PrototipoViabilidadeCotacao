package br.com.cotacao.entidade.datasource;

import java.util.Date;

public class Moedas {
	
	private int id;
	private String moedaOrigem;
	private String moedaDestino;
	private double vrlCompra;
	private double vlrVenda;
	private double percentualLuvro = 2.5;
	private double vrlCompraAjuste;
	private double vrlVendaAjuste;
	private Date datetime;
	
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMoedaOrigem() {
		return moedaOrigem;
	}
	public void setMoedaOrigem(String moedaOrigem) {
		this.moedaOrigem = moedaOrigem;
	}
	public String getMoedaDestino() {
		return moedaDestino;
	}
	public void setMoedaDestino(String moedaDestino) {
		this.moedaDestino = moedaDestino;
	}
	public double getVrlCompra() {
		return vrlCompra;
	}
	public void setVrlCompra(double vrlCompra) {
		this.vrlCompra = vrlCompra;
	}
	public double getVlrVenda() {
		return vlrVenda;
	}
	public void setVlrVenda(double vlrVenda) {
		this.vlrVenda = vlrVenda;
	}
	public double getPercentualLuvro() {
		return percentualLuvro;
	}
	public void setPercentualLuvro(double percentualLuvro) {
		this.percentualLuvro = percentualLuvro;
	}
	public double getVrlCompraAjuste() {
		return vrlCompraAjuste;
	}
	public void setVrlCompraAjuste(double vrlCompraAjuste) {
		this.vrlCompraAjuste = vrlCompraAjuste;
	}
	public double getVrlVendaAjuste() {
		return vrlVendaAjuste;
	}
	public void setVrlVendaAjuste(double vrlVendaAjuste) {
		this.vrlVendaAjuste = vrlVendaAjuste;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
}
