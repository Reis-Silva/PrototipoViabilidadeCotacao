package br.com.cotacao.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

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
	private MoedasDAO gerenciar;
	
	@Inject
	private Gerentes gerente;
	
	@Inject
	private List<Gerentes> gerentes;
	
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
		return gerenciar;
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
		this.gerenciar = gerenciar;
	}

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
		gerenciar = new MoedasDAO();
		gerenciar.salvar(moeda);
		buscarMoeda();
	}

	// Busca no banco de dados
	public void buscarMoeda() {
		moeda = new Moedas();
		gerenciar = new MoedasDAO();
		setMoedas(gerenciar.listar());
	}

	public void removeMoeda(int id) {
		gerenciar.removeById(id);
		buscarMoeda();
	}
	
	
	// Função para salvar no banco de dados
	public void saveGerente() {
		gerenciar = new MoedasDAO();
		gerenciar.salvar(moeda);
		buscarMoeda();
	}

	// Busca no banco de dados
	public void buscarGerente() {
		moeda = new Moedas();
		gerenciar = new MoedasDAO();
		setMoedas(gerenciar.listar());
	}

	public void removeGerente(int id) {
		gerenciar.removeById(id);
		buscarMoeda();
	}
	
	
	
	public void sendMail(String[] mails) {
		JavaMailApp mail = new JavaMailApp();
		mail.javamail(mails);
	}

	// Inicio automático da página
	@PostConstruct
	public void init() {
		try {
			buscarMoeda();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
