package cotacao.dao.gerente;

import java.util.List;

import cotacao.entity.gerente.Gerente;
import dao.DAO;

public interface GerenteDAO extends DAO<Gerente, Integer>{
	
	public List<Gerente> emailSearch(Class<Gerente> classGeneric);
	public void sendMail();
}
