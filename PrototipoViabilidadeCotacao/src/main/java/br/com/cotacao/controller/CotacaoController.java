package br.com.cotacao.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.cotacao.dominio.dao.MoedasDAO;
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
	private Moedas cotacao;
	
	@Inject
	private List<Moedas> cotacoes;
	
	@Inject
	private DataUtils dataUtils = new DataUtils();
	
	@Inject
	private Date dataInicial;
	
	@Inject
	private MoedasDAO gerenciar;
	
	public MoedasDAO getGerenciar() {
		return gerenciar;
	}

	public void setGerenciar(MoedasDAO gerenciar) {
		this.gerenciar = gerenciar;
	}

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
		cotacao.setVlrCompraAjust(cotacao.getCotacaoCompra()+(cotacao.getCotacaoCompra()*cotacao.getPercentLucro()));
		cotacao.setVlrVendaAjust(cotacao.getCotacaoVenda()+(cotacao.getCotacaoVenda()*cotacao.getPercentLucro()));
		cotacao.setMoedaOrigem(getInputMoeda());
		cotacao.setDataSave(this.dataUtils.dateAsString(getDataInicial()));
	}
	
	public void moedaCotacaoAtual() throws Exception {
		cotacao = new Moedas();
		setCotacoes(WEBStatus.listarCotas(getInputMoeda(), this.dataUtils.dateAsString(getDataInicial()), this.dataUtils.todayAsString()));
		varreduraLista();
		save();
	}
	
	
	//Função para salvar no banco de dados
		public void save() {
			gerenciar = new MoedasDAO();
			gerenciar.salvar(cotacao);
			//buscar();	
		}
		
		public void remove(int id) {
			gerenciar.removeById(id);
			buscar();
		}
		
		//Busca no banco de dados
		public void buscar() {
			gerenciar = new MoedasDAO();
			setCotacoes(gerenciar.listar());
		}
		
		/*//Função para editar no banco de dados
		public void inicioEditar(int id ,String inputnJogo, String inputrID, String inputvSistema) {
			jogo.setId(id);
			jogo.setNomeJogo(inputnJogo);
			jogo.setRegistroID(inputrID);
			jogo.setVersao(inputvSistema);
		}
		
		
		public void editar(int id, String inputnJogo, String inputrID, String inputvSistema) {
			gerenciar.atualizar(id, inputnJogo, inputrID, inputvSistema);
			buscar();
		}*/
		
		//Iniciando o sistema
	
	// Inicio automático da página
	@PostConstruct
	public void init() {
		try {
			
			//buscar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
