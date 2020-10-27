package br.com.cotacao.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.cotacao.dominio.dao.GerentesDAO;
import br.com.cotacao.dominio.dao.MoedasDAO;
import br.com.cotacao.entidade.datasource.Gerentes;
import br.com.cotacao.entidade.datasource.Moedas;
import br.com.cotacao.entidade.repository.CotacoesRepository;
import br.com.cotacao.service.WEBStatus;

@ApplicationScoped
@ManagedBean
public class CotacaoController implements Serializable {

	private static final long serialVersionUID = 1L;

	// Iiniciando construção de lista

	private CotacoesRepository cotacaoAtual;

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

	public GerentesDAO getGerenciarGerentes() {
		return gerenciarGerentes;
	}

	public void setGerenciarGerentes(GerentesDAO gerenciarGerentes) {
		this.gerenciarGerentes = gerenciarGerentes;
	}

	public MoedasDAO getGerenciarMoedas() {
		return gerenciarMoedas;
	}

	public void setGerenciarMoedas(MoedasDAO gerenciarMoedas) {
		this.gerenciarMoedas = gerenciarMoedas;
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

	public MoedasDAO getGerenciar() {
		return gerenciarMoedas;
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

	public void setGerenciar(MoedasDAO gerenciar) {
		this.gerenciarMoedas = gerenciar;
	}

	
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

	// Função para salvar no banco de dados
	public void saveMoeda() {
		gerenciarMoedas = new MoedasDAO();
		gerenciarMoedas.salvar(moeda);
		buscarMoeda();
	}

	// Busca no banco de dados
	public void buscarMoeda() {
		moeda = new Moedas();
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
	public void buscarEmailGerente() {
		gerente = new Gerentes();
		gerenciarGerentes = new GerentesDAO();
		setGerentes(gerenciarGerentes.listarEmails());
	}

	public void removeGerente(int id) {
		gerenciarGerentes.removeById(id);
		buscarGerente();
	}
	
	//******************************************************************************
	
	public void sendMail(String[] mails) {
		JavaMailApp mail = new JavaMailApp();
		mail.javamail(mails);
	}

	// Inicio automático da página
	@PostConstruct
	public void init() {
		try {
			buscarMoeda();
			buscarGerente();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
