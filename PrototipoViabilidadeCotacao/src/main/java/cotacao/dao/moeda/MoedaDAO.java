package cotacao.dao.moedas;

import cotacao.entity.moedas.Moedas;
import dao.DAO;

public interface MoedasDAO extends DAO<Moedas, Integer>{
	
	 public void cotacaoMoedasSave(String input, String date, String method);

}
