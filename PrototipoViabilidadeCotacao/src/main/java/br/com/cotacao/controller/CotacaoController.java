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
	
	private DataUtils dataUtils = new DataUtils();
	
	private Date dataInicial;
	
	private Enum TipoMoeda;
	
	public Enum getTipoMoeda() {
		return TipoMoeda;
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

	public Moedas getCotacao() {
		return cotacao;
	}

	public void setCotacao(Moedas cotacao) {
		this.cotacao = cotacao;
	}

	public List<Moedas> getCotacoes() {
		return cotacoes;
	}

	public void setCotacoes(List<Moedas> cotacoes) {
		this.cotacoes = cotacoes;
	}

	public DataUtils getDataUtils() {
		return dataUtils;
	}

	public void setDataUtils(DataUtils dataUtils) {
		this.dataUtils = dataUtils;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public void setTipoMoeda(Enum tipoMoeda) {
		TipoMoeda = tipoMoeda;
	}


	public void varreduraLista(){
		setCotacao(cotacoes.get(cotacoes.size()-1));
		System.out.print("\n"+cotacao.getCotacaoCompra());
	}
	
	public void moedaCotacaoAtual() throws Exception {
		
		setCotacoes(WEBStatus.listarCotas(getInputMoeda(), this.dataUtils.dateAsString(getDataInicial()), this.dataUtils.todayAsString()));
		varreduraLista();
		
	}
	
	

	
	// Inicio automático da página
	@PostConstruct
	public void init() {
		try {
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
