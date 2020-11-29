package cotacao.dao.moeda;

import cotacao.entity.moeda.Moeda;
import dao.DAO;

public interface MoedaDAO extends DAO<Moeda, Integer>{
	
	 public void cotacaoMoedasSave(String input, String date, String method);

}
