package cotacao.dao.moedas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import cotacao.controller.DataUtils;
import cotacao.entity.moedas.Moedas;
import cotacao.service.WEBStatus;
import dao.JPADAO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ViewScoped
@ManagedBean
@Data
@EqualsAndHashCode(callSuper = false)
public class MoedasJPADAO extends JPADAO<Moedas, Integer> implements Serializable, MoedasDAO{
	

	private static final long serialVersionUID = 1L;
	
	@Inject
	private String inputMoeda;

	@Inject
	private Moedas moeda;
	
	@Inject
	private Moedas selectMoeda; 
	
	@Inject
	private List<Moedas> moedas;

	@Inject
	private DataUtils dataUtils = new DataUtils();

	@Inject
	private Date dataInicial;
	
	@Override
	public void cotacaoMoedasSave(String input ,String date, String method) {
		moeda = new Moedas();
		List<Moedas> verificacaoMoeda = null;
		try {
			verificacaoMoeda = WEBStatus.listarCotas(input, date,
					this.dataUtils.todayAsString());
			if(verificacaoMoeda.isEmpty()) {
				messageView(false, "Moeda ainda não foi atualizada");
			}else if(method.contentEquals("cotacaomoeda")){
				setMoedas(verificacaoMoeda);
				varreduraLista(date);
				save(moeda);
			}else if(method.contains("email")) {
				setMoedas(verificacaoMoeda);
				varreduraLista(date);
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
	
	public Moedas cotacaoMoedasSaveEmail(String input) {
		cotacaoMoedasSave(input, this.dataUtils.todayAsString(), "email");
		return moeda;
	}
	
	public void varreduraLista(String date) {
		setMoeda(moedas.get(moedas.size() - 1));
		moeda.setMoedaOrigem(getInputMoeda());
		moeda.setVlrCompraAjust(moeda.getCotacaoCompra() + (moeda.getCotacaoCompra() * moeda.getPercentLucro()));
		moeda.setVlrVendaAjust(moeda.getCotacaoVenda() + (moeda.getCotacaoVenda() * moeda.getPercentLucro()));
		moeda.setDataSave(date);
	}

	public void moedaStorage() {
		setMoedas(search(Moedas.class));
	}
	
	public void removeMoeda() {
		if(getSelectMoeda().getId() == null) {
			messageView(false, "Selecione uma moeda");
		}else {
			int pk = getSelectMoeda().getId();
			System.out.println("Teste: " + pk + "\n");
			remove(Moedas.class, pk);
			moedaStorage();
			setSelectMoeda(null);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void onRowSelect(SelectEvent event) {		
		Moedas select = (Moedas) event.getObject();
		setSelectMoeda(select);
	}
	
	public void close() {
		setMoeda(null);
	}

}
