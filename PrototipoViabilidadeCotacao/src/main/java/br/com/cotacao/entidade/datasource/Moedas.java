package br.com.cotacao.entidade.datasource;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "moedas")
public class Moedas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private double id;
	
	@Column
	private String moedaOrigem;
	
	@Column
	private String MoedaDestino="BRL";
	
	private double paridadeCompra;
	private double paridadeVenda;
	
	@Column
	private double cotacaoCompra;
	
	@Column
	private double cotacaoVenda;
	
	private String tipoBoletim;
	
	@Column
	private double vlrCompraAjust;
	
	@Column
	private double vlrVendaAjust;
	
	@Column
	private double percentLucro=0.025;
	
	@Column
	private String dataSave;

	public double getId() {
		return id;
	}

	public void setId(double id) {
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
