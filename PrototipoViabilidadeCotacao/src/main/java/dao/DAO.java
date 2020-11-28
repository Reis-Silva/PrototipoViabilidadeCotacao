package dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface DAO <T,I>{
	public void save(T entity);
	public List<T> search (Class<T> T);
	public void  remove(Class<T> classGeneric, I pk);
	EntityManager getEntityManager();
}
