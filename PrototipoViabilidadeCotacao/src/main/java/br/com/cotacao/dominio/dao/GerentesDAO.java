package br.com.cotacao.dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.cotacao.controller.CotacaoBean;
import br.com.cotacao.entidade.datasource.Gerentes;

public class GerentesDAO {
	
		protected EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		CotacaoBean cotacao = new CotacaoBean();

		public GerentesDAO(){
			entityManager = getEntityManager();
		} 
		
		//Iniciando parâmetros de banco de dados/Padrão Sigleton
		public EntityManager getEntityManager() {
			entityManagerFactory = Persistence.createEntityManagerFactory("cotacaomoeda");
			if (entityManager == null) {
				entityManager = entityManagerFactory.createEntityManager();
			}
			return entityManager;
		}
		
		//Salvar no banco de dados
		public void salvar(Gerentes gerente) {
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(gerente);
				entityManager.getTransaction().commit();
				entityManagerFactory.close();
				cotacao.successExport(true, "Gerente Salvo no Banco de Dados");
			} catch (Exception e) {
				e.printStackTrace();
				entityManager.getTransaction().rollback();
				cotacao.successExport(true, "Gerente não foi Salvo no Banco de Dados");
			}

		}
		
		//Remover do banco de dados
		public Gerentes getById(final int id) {
	         return entityManager.find(Gerentes.class, id);
	       }

		
		public void removeById(final int id) {
	         try {
	        	 Gerentes g = new Gerentes();
	             g = getById(id);
	             remover(g);
	         } catch (Exception ex) {
	        	 ex.printStackTrace();
	         }
	       }
		
		
		public void remover(Gerentes gerente) {
			try {
				entityManager.getTransaction().begin();
				gerente = entityManager.find(Gerentes.class, gerente.getId());
				entityManager.remove(gerente);
				entityManager.getTransaction().commit();
				entityManagerFactory.close();
			} catch (Exception e) {
				e.printStackTrace();
				entityManager.getTransaction().rollback();
			}

		}
		
		//Buscar no banco de dados
		@SuppressWarnings("unchecked")
		public List<Gerentes> listar(){
			entityManager.getTransaction().begin();
			Query consulta = entityManager.createQuery("select gerente from Gerentes gerente");
			List<Gerentes> lista = consulta.getResultList();
			entityManager.getTransaction().commit();
			
			return lista;
		}
		
		//Buscar no banco de dados emails
		@SuppressWarnings("unchecked")
		public List<Gerentes> listarEmails(){		
			entityManager.getTransaction().begin();
			Query consulta = entityManager.createQuery("select email from Gerentes gerente");
			List<Gerentes> lista = consulta.getResultList();
			entityManager.getTransaction().commit();
			
			return lista;
		}
		
}
