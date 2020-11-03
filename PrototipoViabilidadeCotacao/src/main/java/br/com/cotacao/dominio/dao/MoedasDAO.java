package br.com.cotacao.dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.cotacao.controller.CotacaoBean;
import br.com.cotacao.entidade.datasource.Moedas;

public class MoedasDAO {
	
		protected EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		CotacaoBean cotacao = new CotacaoBean();
		
		public MoedasDAO(){
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
		public void salvar(Moedas moeda) {
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(moeda);
				entityManager.getTransaction().commit();
				entityManagerFactory.close();
				cotacao.successExport(true, "Cotação de Moeda Salva no Banco de Dados");
				
			} catch (Exception e) {
				e.printStackTrace();
				entityManager.getTransaction().rollback();
				cotacao.successExport(true, "Cotação de Moeda não foi Salva no Banco de Dados");
			}

		}
		
	
		//Remover do banco de dados
		public Moedas getById(final int id) {
	         return entityManager.find(Moedas.class, id);
	       }
		
		
		public void removeById(final int id) {
	         try {
	        	 Moedas m = new Moedas();
	             m = getById(id);
	             remover(m);
	         } catch (Exception ex) {
	        	 ex.printStackTrace();
	         }
	       }
		
		
		public void remover(Moedas m) {
			try {
				entityManager.getTransaction().begin();
				m = entityManager.find(Moedas.class, m.getId());
				entityManager.remove(m);
				entityManager.getTransaction().commit();
				entityManagerFactory.close();
			} catch (Exception e) {
				e.printStackTrace();
				entityManager.getTransaction().rollback();
			}

		}
		
		
		//Buscar no banco de dados
		@SuppressWarnings("unchecked")
		public List<Moedas> listar(){
			
			entityManager.getTransaction().begin();
			Query consulta = entityManager.createQuery("from Moedas");
			List<Moedas> lista = consulta.getResultList();
			entityManager.getTransaction().commit();
			
			return lista;
		}
		
}
