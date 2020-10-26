package br.com.cotacao.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import br.com.cotacao.entidade.repository.CotacoesRepository;
import br.com.cotacao.service.WEBStatus;

@ApplicationScoped
@ManagedBean
public class CotacaoController implements Serializable {

	private static final long serialVersionUID = 1L;

	// Iiniciando construção de lista
	
	private CotacoesRepository cotacaoAtual;
	
	private String inputMoeda;
	
	private List cotacoes;
		
	public String getInputMoeda() {
		return inputMoeda;
	}

	public void setInputMoeda(String inputMoeda) {
		this.inputMoeda = inputMoeda;
	}

	public CotacoesRepository getCotacaoAtual() {
		return cotacaoAtual;
	}

	public void setCotacaoAtual(CotacoesRepository cotacaoAtual) {
		this.cotacaoAtual = cotacaoAtual;
	}

	public List getCotacoes() {
		return cotacoes;
	}

	public void setCotacoes(List cotacoes) {
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
	
	
	public void moedaCotacaoAtual(){
		
		String dataAtual = calendarioAtual();
		try {
			cotacaoAtual =  WEBStatus.moedasDetalhes(inputMoeda, dataAtual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	// Inicio automático da página
	@PostConstruct
	public void init() {
		try {
			setCotacoes(WEBStatus.listarEstados());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
