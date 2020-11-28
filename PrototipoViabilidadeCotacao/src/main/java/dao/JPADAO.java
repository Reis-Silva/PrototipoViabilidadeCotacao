package dao;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public abstract class JPADAO<T, I> implements DAO<T, I>{
	
	
	private JPAUtil conexao;
	
	@Override
	public void save(T entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
		conexao.offlineServer();
	}
	
	@Override
	public List<T> search(Class<T> classGeneric) {
		getEntityManager().getTransaction().begin();
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(classGeneric);
		@SuppressWarnings("unused")
		Root<T> root = criteriaQuery.from(classGeneric);
		TypedQuery<T> typedQuery = (TypedQuery<T>) getEntityManager().createQuery(criteriaQuery);
		List<T> list = typedQuery.getResultList();
		getEntityManager().getTransaction().commit();		
		getEntityManager().close();
		conexao.offlineServer();
		return (List<T>) list;
	}

	@Override
	public void remove(Class<T> classGeneric, I pk) {
		getEntityManager().getTransaction().begin();
		T reference = getEntityManager().getReference(classGeneric, pk);
		getEntityManager().remove(reference);
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
		conexao.offlineServer();
	}
	
	public void messageView(Boolean success, String menssage) {
		if (success == true) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, menssage, null));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, menssage, null));
		}
	}
	
	@Override
	public EntityManager getEntityManager() {
		if(conexao == null) {
			conexao = new JPAUtil();
		}
		return conexao.getEntityManager();
	}
	
}
