package br.com.cotacao.entidade.datasource;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "moedas")
public class Moedas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Moedas() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String moedaOrigem;
	
	@Column
	private String MoedaDestino="BRL";
	
	@Column
	private double paridadecompra;
	
	@Column
	private double paridadevenda;
	
	@Column
	private double cotacaoCompra;
	
	@Column
	private double cotacaoVenda;
		
	@Column
	private double vlrCompraAjust;
	
	@Column
	private double vlrVendaAjust;
	
	@Column
	private double percentLucro=0.025;
	
	@Column
	private String dataSave;

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
		return MoedaDestino;
	}

	public void setMoedaDestino(String moedaDestino) {
		MoedaDestino = moedaDestino;
	}

	public double getParidadecompra() {
		return paridadecompra;
	}

	public void setParidadecompra(double paridadecompra) {
		this.paridadecompra = paridadecompra;
	}

	public double getParidadevenda() {
		return paridadevenda;
	}

	public void setParidadevenda(double paridadevenda) {
		this.paridadevenda = paridadevenda;
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

	public double getVlrCompraAjust() {
		return vlrCompraAjust;
	}

	public void setVlrCompraAjust(double vlrCompraAjust) {
		this.vlrCompraAjust = vlrCompraAjust;
	}

	public double getVlrVendaAjust() {
		return vlrVendaAjust;
	}

	public void setVlrVendaAjust(double vlrVendaAjust) {
		this.vlrVendaAjust = vlrVendaAjust;
	}

	public double getPercentLucro() {
		return percentLucro;
	}

	public void setPercentLucro(double percentLucro) {
		this.percentLucro = percentLucro;
	}

	public String getDataSave() {
		return dataSave;
	}

	public void setDataSave(String dataSave) {
		this.dataSave = dataSave;
	}

	


}
