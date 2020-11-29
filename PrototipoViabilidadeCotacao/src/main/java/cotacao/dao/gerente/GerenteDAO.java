package cotacao.dao.gerentes;

import java.util.List;

import cotacao.entity.gerentes.Gerentes;
import dao.DAO;

public interface GerentesDAO extends DAO<Gerentes, Integer>{
	
	public List<Gerentes> emailSearch(Class<Gerentes> classGeneric);
	public void sendMail();
}
