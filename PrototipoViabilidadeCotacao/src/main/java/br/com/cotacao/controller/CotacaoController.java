package br.com.cotacao.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.cotacao.entidade.datasource.Moedas;
import br.com.cotacao.entidade.repository.CotacoesRepository;
import br.com.cotacao.service.WEBStatus;

@ApplicationScoped
@ManagedBean
public class CotacaoController implements Serializable {

	private static final long serialVersionUID = 1L;

	// Iiniciando construção de lista
	
	private CotacoesRepository cotacaoAtual;
	
	private String inputMoeda;
	
	private Moedas cotacao;
	
	private List<Moedas> cotacoes;
	
	private Date dataInicial;
	
	private Date dataFinal;

	
	
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Moedas getCotacao() {
		return cotacao;
	}

	public void setCotacao(Moedas cotacao) {
		this.cotacao = cotacao;
	}

	public CotacoesRepository getCotacaoAtual() {
		return cotacaoAtual;
	}

	public void setCotacaoAtual(CotacoesRepository cotacaoAtual) {
		this.cotacaoAtual = cotacaoAtual;
	}

	public String getInputMoeda() {
		return inputMoeda;
	}

	public void setInputMoeda(String inputMoeda) {
		this.inputMoeda = inputMoeda;
	}

	public List<Moedas> getCotacoes() {
		return cotacoes;
	}

	public void setCotacoes(List<Moedas> cotacoes) {
		this.cotacoes = cotacoes;
	}

	public String calendarioAtual() {
		Calendar calendar = new GregorianCalendar();
		String dia = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		String mes = Integer.toString(calendar.get((Calendar.MONTH))+1);
		String  ano = Integer.toString(calendar.get(Calendar.YEAR));
		
		String dataAtual = mes+dia+ano;
		
		return dataAtual;	 
	}
	 
	
	public void varreduraLista(){
		
		setCotacao(cotacoes.get(cotacoes.size()-1));
		System.out.print("\n"+cotacao.getCotacaoCompra());
	}
	
	public void moedaCotacaoAtual(String inputMoeda, String dataInicial, String dataFinal) throws Exception {
		setCotacoes(WEBStatus.listarCotas(inputMoeda, dataInicial, dataFinal));
		varreduraLista();
	}
	
	/*public void moedaCotacaoAtual(){
		
		String dataAtual = calendarioAtual();
		try {
			cotacaoAtual =  WEBStatus.moedasDetalhes(inputMoeda, dataAtual);
			varreduraLista();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	// Inicio automático da página
	@PostConstruct
	public void init() {
		try {
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
