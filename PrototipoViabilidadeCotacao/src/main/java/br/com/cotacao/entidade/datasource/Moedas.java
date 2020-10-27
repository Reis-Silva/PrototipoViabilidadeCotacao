package br.com.cotacao.entidade.datasource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "moedas")
public class Moedas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Moedas() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "MoedaOrigem", length = 10, nullable = false )
	private String moedaOrigem;
	
	@Column(name = "MoedaDestino", length = 10, nullable = false ) 
	private String moedaDestino="BRL";
	
	@Transient
	private double paridadecompra;
	
	@Transient
	private double paridadevenda;
	
	@Column(name = "CotacaoCompra", length = 10, nullable = false ) 
	private double cotacaoCompra;
	
	@Column(name = "CotacaoVenda", length = 10, nullable = false ) 
	private double cotacaoVenda;
		
	@Column(name = "vlrCompraAjust", length = 10, nullable = false )
	private double vlrCompraAjust;
	
	@Column(name = "vlrVendaAjust", length = 10, nullable = false )
	private double vlrVendaAjust;
	
	@Column(name = "percentLucro", length = 10, nullable = false )
	private double percentLucro=0.025;
	
	@Column(name = "Data", length = 10, nullable = false )
	private String dataSave;
	
	@Transient
	private String tipoBoletim;

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

	public String getTipoBoletim() {
		return tipoBoletim;
	}

	public void setTipoBoletim(String tipoBoletim) {
		this.tipoBoletim = tipoBoletim;
	}


}
