package br.com.cotacao.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.cotacao.dominio.dao.GerentesDAO;
import br.com.cotacao.dominio.dao.MoedasDAO;
import br.com.cotacao.entidade.datasource.Gerentes;
import br.com.cotacao.entidade.datasource.Moedas;
import br.com.cotacao.service.WEBStatus;

@ApplicationScoped
@ManagedBean
public class CotacaoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// Iiniciando construção de lista


	@Inject
	private String inputMoeda;

	@Inject
	private Moedas moeda;

	@Inject
	private List<Moedas> moedas;

	@Inject
	private DataUtils dataUtils = new DataUtils();

	@Inject
	private Date dataInicial;

	@Inject
	private MoedasDAO gerenciarMoedas;
	
	@Inject
	private GerentesDAO gerenciarGerentes;
	
	@Inject
	private Gerentes gerente;
	
	@Inject
	private List<Gerentes> gerentes;
	
	@Inject
	private String inputNomeGerente;
	
	@Inject
	private String inputAgencia;
	
	@Inject
	private String inputEmail;
	
	@Inject
	public String[] UnidadeMoedas = {"AUD","CAD","CHF","DKK","GBP","JPY","NOK","SEK","USD"};
	
	@Inject 
	public String[] VlrCompra;
	
	@Inject
	public String[] VlrVenda;
	
	@Inject
	public String[] VlrCompraAjust;
	
	@Inject
	public String[] VlrVendaAjust;


	public String getInputMoeda() {
		return inputMoeda;
	}

	public void setInputMoeda(String inputMoeda) {
		this.inputMoeda = inputMoeda;
	}

	public Moedas getMoeda() {
		return moeda;
	}

	public void setMoeda(Moedas moeda) {
		this.moeda = moeda;
	}

	public List<Moedas> getMoedas() {
		return moedas;
	}

	public void setMoedas(List<Moedas> moedas) {
		this.moedas = moedas;
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

	public MoedasDAO getGerenciarMoedas() {
		return gerenciarMoedas;
	}

	public void setGerenciarMoedas(MoedasDAO gerenciarMoedas) {
		this.gerenciarMoedas = gerenciarMoedas;
	}

	public GerentesDAO getGerenciarGerentes() {
		return gerenciarGerentes;
	}

	public void setGerenciarGerentes(GerentesDAO gerenciarGerentes) {
		this.gerenciarGerentes = gerenciarGerentes;
	}

	public Gerentes getGerente() {
		return gerente;
	}

	public void setGerente(Gerentes gerente) {
		this.gerente = gerente;
	}

	public List<Gerentes> getGerentes() {
		return gerentes;
	}

	public void setGerentes(List<Gerentes> gerentes) {
		this.gerentes = gerentes;
	}

	public String getInputNomeGerente() {
		return inputNomeGerente;
	}

	public void setInputNomeGerente(String inputNomeGerente) {
		this.inputNomeGerente = inputNomeGerente;
	}

	public String getInputAgencia() {
		return inputAgencia;
	}

	public void setInputAgencia(String inputAgencia) {
		this.inputAgencia = inputAgencia;
	}

	public String getInputEmail() {
		return inputEmail;
	}

	public void setInputEmail(String inputEmail) {
		this.inputEmail = inputEmail;
	}

	public String[] getUnidadeMoedas() {
		return UnidadeMoedas;
	}

	public void setUnidadeMoedas(String[] unidadeMoedas) {
		UnidadeMoedas = unidadeMoedas;
	}

	public String[] getVlrCompra() {
		return VlrCompra;
	}

	public void setVlrCompra(String[] vlrCompra) {
		VlrCompra = vlrCompra;
	}

	public String[] getVlrVenda() {
		return VlrVenda;
	}

	public void setVlrVenda(String[] vlrVenda) {
		VlrVenda = vlrVenda;
	}

	public String[] getVlrCompraAjust() {
		return VlrCompraAjust;
	}

	public void setVlrCompraAjust(String[] vlrCompraAjust) {
		VlrCompraAjust = vlrCompraAjust;
	}

	public String[] getVlrVendaAjust() {
		return VlrVendaAjust;
	}

	public void setVlrVendaAjust(String[] vlrVendaAjust) {
		VlrVendaAjust = vlrVendaAjust;
	}

 boolean laco=true;
 
	//******************************** Cotação Moedas ***********************************
	public void varreduraLista() {
		setMoeda(moedas.get(moedas.size() - 1));
		moeda.setMoedaOrigem(getInputMoeda());
		moeda.setVlrCompraAjust(
				moeda.getCotacaoCompra() + (moeda.getCotacaoCompra() * moeda.getPercentLucro()));
		moeda.setVlrVendaAjust(
				moeda.getCotacaoVenda() + (moeda.getCotacaoVenda() * moeda.getPercentLucro()));
		moeda.setDataSave(this.dataUtils.dateAsString(getDataInicial()));
	}

	public void moedaCotacaoAtual() throws Exception {
		moeda = new Moedas();
		setMoedas(WEBStatus.listarCotas(getInputMoeda(), this.dataUtils.dateAsString(getDataInicial()),
				this.dataUtils.todayAsString()));
		varreduraLista();
		saveMoeda();
		
	}
	
	public Moedas moedaCotacaoAtualEmail(String input){
		moeda = new Moedas();
		try {
			setMoedas(WEBStatus.listarCotas(input, this.dataUtils.todayAsString(),this.dataUtils.todayAsString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setMoeda(moedas.get(moedas.size() - 1));
		moeda.setMoedaOrigem(input);
		moeda.setVlrCompraAjust(
				moeda.getCotacaoCompra() + (moeda.getCotacaoCompra() * moeda.getPercentLucro()));
		moeda.setVlrVendaAjust(
				moeda.getCotacaoVenda() + (moeda.getCotacaoVenda() * moeda.getPercentLucro()));
		moeda.setDataSave(this.dataUtils.todayAsString());
		return moeda;
	}

	// Função para salvar no banco de dados
	public void saveMoeda() {
		gerenciarMoedas = new MoedasDAO();
		gerenciarMoedas.salvar(moeda);
		buscarMoeda();
	}

	// Busca no banco de dados
	public void buscarMoeda() {
		gerenciarMoedas = new MoedasDAO();
		setMoedas(gerenciarMoedas.listar());
	}

	public void removeMoeda(int id) {
		gerenciarMoedas.removeById(id);
		buscarMoeda();
	}
	
	//******************************** Gerentes ***********************************
	
	// Função para salvar no banco de dados
	public void saveGerente() {
		gerente = new Gerentes();
		gerente.setNomeGerente(getInputNomeGerente());
		gerente.setAgencia(getInputAgencia());
		gerente.setEmail(getInputEmail());
		
		gerenciarGerentes = new GerentesDAO();
		gerenciarGerentes.salvar(gerente);
		buscarGerente();
	}

	// Busca no banco de dados
	public void buscarGerente() {
		gerente = new Gerentes();
		gerenciarGerentes = new GerentesDAO();
		setGerentes(gerenciarGerentes.listar());
	}
	
	// Busca email dos gerentes
	public void buscarEmailGerente() throws Exception {
		gerente = new Gerentes();
		gerenciarGerentes = new GerentesDAO();
		setGerentes(gerenciarGerentes.listarEmails());
		sendMail(getGerentes());
	}

	public void removeGerente(int id) {
		gerenciarGerentes.removeById(id);
		buscarGerente();
	}
	
	public <T> void sendMail(List<T> mails){
		JavaMailApp mail = new JavaMailApp();
		try {
			mail.javamail(mails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void successExport(Boolean success, String mensagem) {
		if (success) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "Email Enviado"));
		}else {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "Nao foi possivel enviar o Email..."));
		}
	}
	//******************************************************************************
	
	// Inicio automático da página
	
	DataUtils tempoE = new DataUtils();
	
	@PostConstruct
	public void init() {
		try {
			buscarGerente();
			buscarMoeda();
			
			tempoE.setPriority(Thread.MIN_PRIORITY);
			tempoE.calendarioEnvioEmail();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

