package cotacao.entity.moeda;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "moedas")
@Data
public class Moeda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Moeda() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE) private Integer id;
	
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
	
	private String[] unidadeMoedas = { "AUD", "CAD", "CHF", "DKK", "GBP", "JPY", "NOK", "SEK", "USD" };

	@Override
	public String toString() {
		return "<br/><br/>moedaOrigem=" + moedaOrigem + "<br/>moedaDestino=" + moedaDestino
				+ "<br/>cotacaoCompra=" + cotacaoCompra + "<br/>cotacaoVenda=" + cotacaoVenda + "<br/>percentLucro=" + percentLucro 
				+"<br/>vlrCompraAjust=" + vlrCompraAjust + "<br/>vlrVendaAjust=" + vlrVendaAjust +  "<br/>dataSave=" + dataSave+"<br/><br/>";
	}


	
}
