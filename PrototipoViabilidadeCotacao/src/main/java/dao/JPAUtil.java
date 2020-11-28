package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static final String UNIT_NAME = "cotacaomoeda";
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	public EntityManagerFactory getEmf() {
		return emf;
	}
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public EntityManager getEntityManager() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory(UNIT_NAME);
		}
		if(em == null) {
			em = emf.createEntityManager();
		}
		return em;
	}
	public void offlineServer() {
		setEmf(null);
		setEm(null);
	}
	
	
}
