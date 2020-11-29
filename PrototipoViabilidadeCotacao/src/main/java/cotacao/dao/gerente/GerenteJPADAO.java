package cotacao.dao.gerentes;

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

import cotacao.controller.JavaMailApp;
import cotacao.entity.gerentes.Gerentes;
import dao.JPADAO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@ViewScoped
@ManagedBean
@Data
@EqualsAndHashCode(callSuper=false)
public class GerentesJPADAO extends JPADAO<Gerentes, Integer> implements Serializable, GerentesDAO {

	private static final long serialVersionUID = 1L;

	@Inject
	private List<Gerentes> gerentes;
	
	@Inject
	private Gerentes selectGerente;
	
	@Inject
	private String inputNomeGerente;

	@Inject
	private String inputAgencia;

	@Inject
	private String inputEmail;
	
	@Override
	public List<Gerentes> emailSearch(Class<Gerentes> classGeneric) {
		getEntityManager().getTransaction().begin();
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Gerentes> criteriaQuery = criteriaBuilder.createQuery(classGeneric);
		Root<Gerentes> root = criteriaQuery.from(classGeneric);
		criteriaQuery.select(root.get("email"));
		TypedQuery<Gerentes> typedQuery = getEntityManager().createQuery(criteriaQuery);
		List<Gerentes> emaillist = typedQuery.getResultList();
		
		return emaillist;
	}

	@Override
	public void sendMail() {
		JavaMailApp mail = new JavaMailApp();
		List<Gerentes> mails = new ArrayList<Gerentes>();
		mails = emailSearch(Gerentes.class);
		System.out.println("\nteste: "+ mails + "\n");
		try {
			mail.javamail(mails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gerenteSave() {
		Gerentes gerente = new Gerentes();
		gerente.setAgencia(inputNomeGerente);
		gerente.setAgencia(inputAgencia);
		gerente.setEmail(inputEmail);
		save(gerente);
	}
	
	public void gerentesStorage(){
		setGerentes(search(Gerentes.class));
	}
	
	public void removeGerente(){
		if(getSelectGerente().getId() == null) {
			messageView(false, "Selecione um gerente");
		}else {
			int pk = getSelectGerente().getId();
			remove(Gerentes.class, pk);
			setSelectGerente(null);
			gerentesStorage();
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public void onRowSelect(SelectEvent event) {
		selectGerente = new Gerentes();
		Gerentes select = (Gerentes) (event.getObject());
		setSelectGerente(select);
	}
}

