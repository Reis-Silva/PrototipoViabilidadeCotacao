package cotacao.dao.gerente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.primefaces.event.SelectEvent;
import cotacao.controller.calendar.JavaMailApp;
import cotacao.entity.gerente.Gerente;
import dao.JPADAO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@ViewScoped
@ManagedBean
@Data
@EqualsAndHashCode(callSuper=false)
public class GerenteJPADAO extends JPADAO<Gerente, Integer> implements Serializable, GerenteDAO {

	private static final long serialVersionUID = 1L;

	@Inject
	private List<Gerente> gerentes;
	
	@Inject
	private Gerente selectGerente;
	
	@Inject
	private String inputNomeGerente;

	@Inject
	private String inputAgencia;

	@Inject
	private String inputEmail;
	
	@Override
	public List<Gerente> emailSearch(Class<Gerente> classGeneric) {
		getEntityManager().getTransaction().begin();
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Gerente> criteriaQuery = criteriaBuilder.createQuery(classGeneric);
		Root<Gerente> root = criteriaQuery.from(classGeneric);
		criteriaQuery.select(root.get("email"));
		TypedQuery<Gerente> typedQuery = getEntityManager().createQuery(criteriaQuery);
		List<Gerente> emaillist = typedQuery.getResultList();
		
		return emaillist;
	}

	@Override
	public void sendMail() {
		JavaMailApp mail = new JavaMailApp();
		List<Gerente> mails = new ArrayList<Gerente>();
		mails = emailSearch(Gerente.class);
		System.out.println("\nteste: "+ mails + "\n");
		try {
			mail.javamail(mails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gerenteSave() {
		Gerente gerente = new Gerente();
		gerente.setAgencia(inputNomeGerente);
		gerente.setAgencia(inputAgencia);
		gerente.setEmail(inputEmail);
		save(gerente);
	}
	
	public void gerentesStorage(){
		setGerentes(search(Gerente.class));
	}
	
	public void removeGerente(){
		if(getSelectGerente().getId() == null) {
			messageView(false, "Selecione um gerente");
		}else {
			int pk = getSelectGerente().getId();
			remove(Gerente.class, pk);
			setSelectGerente(null);
			gerentesStorage();
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public void onRowSelect(SelectEvent event) {
		selectGerente = new Gerente();
		Gerente select = (Gerente) (event.getObject());
		setSelectGerente(select);
	}
	
	public void close() {
		setGerentes(null);
	}

}

