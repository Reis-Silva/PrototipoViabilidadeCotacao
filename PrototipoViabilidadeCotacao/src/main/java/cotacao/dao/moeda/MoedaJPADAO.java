package cotacao.dao.moeda;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

import cotacao.controller.calendar.DataUtils;
import cotacao.controller.calendar.TaskManager;
import cotacao.entity.moeda.Moeda;
import cotacao.service.WEBStatus;
import dao.JPADAO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ViewScoped
@ManagedBean
@Data
@EqualsAndHashCode(callSuper = false)
public class MoedaJPADAO extends JPADAO<Moeda, Integer> implements Serializable, MoedaDAO{
	

	private static final long serialVersionUID = 1L;
	
	@Inject
	private String inputMoeda;

	@Inject
	private Moeda moeda;
	
	@Inject
	private Moeda selectMoeda; 
	
	@Inject
	private List<Moeda> moedas;

	@Inject
	private DataUtils dataUtils = new DataUtils();

	@Inject
	private Date dataInicial;
	
	@Override
	public void cotacaoMoedasSave(String input ,String date, String method) {
		moeda = new Moeda();
		List<Moeda> verificacaoMoeda = null;
		try {
			verificacaoMoeda = WEBStatus.listarCotas(input, date,
					this.dataUtils.todayAsString());
			if(verificacaoMoeda.isEmpty()) {
				messageView(false, "Moeda ainda não foi atualizada");
			}else if(method.contentEquals("cotacaomoeda")){
				setMoedas(verificacaoMoeda);
				varreduraLista(date, method, input);
				save(moeda);
			}else if(method.contains("email")) {
				setMoedas(verificacaoMoeda);
				varreduraLista(date, method, input);
			}else {
				messageView(false, "Nenhum método encontrado");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		
	}
	
	public void cotacaoMoedasSaveToday(){
		cotacaoMoedasSave(getInputMoeda(), this.dataUtils.dateAsString(getDataInicial()), "cotacaomoeda");
	}
	
	public Moeda cotacaoMoedasSaveEmail(String input) {
		cotacaoMoedasSave(input, this.dataUtils.todayAsString(), "email");
		return moeda;
	}
	
	public void varreduraLista(String date, String method, String input) {
		setMoeda(moedas.get(moedas.size() - 1));
		if(method.contentEquals("cotacaomoeda")) {
			moeda.setMoedaOrigem(getInputMoeda());
		}else {
			moeda.setMoedaOrigem(input);
		}
		moeda.setVlrCompraAjust(moeda.getCotacaoCompra() + (moeda.getCotacaoCompra() * moeda.getPercentLucro()));
		moeda.setVlrVendaAjust(moeda.getCotacaoVenda() + (moeda.getCotacaoVenda() * moeda.getPercentLucro()));
		moeda.setDataSave(date);
	}

	public void moedaStorage() {
		setMoedas(search(Moeda.class));
	}
	
	public void removeMoeda() {
		if(getSelectMoeda().getId() == null) {
			messageView(false, "Selecione uma moeda");
		}else {
			int pk = getSelectMoeda().getId();
			System.out.println("Teste: " + pk + "\n");
			remove(Moeda.class, pk);
			moedaStorage();
			setSelectMoeda(null);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void onRowSelect(SelectEvent event) {		
		Moeda select = (Moeda) event.getObject();
		setSelectMoeda(select);
	}
	
	public void close() {
		setMoeda(null);
	}
	
	
	TaskManager calendar = new TaskManager();
	
	@PostConstruct
	public void init(){
		calendar.cron();
	}
}
