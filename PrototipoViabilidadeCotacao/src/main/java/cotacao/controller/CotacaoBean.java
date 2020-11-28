package cotacao.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import dao.JPADAO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@ManagedBean
@Data
@EqualsAndHashCode(callSuper=false)
public class CotacaoBean<T, I> extends JPADAO<T, I> implements Serializable {

	private static final long serialVersionUID = 1L;

	DataUtils tempoE = new DataUtils();

	@PostConstruct
	public void init() {
		try {
			tempoE.setPriority(Thread.MIN_PRIORITY);
			tempoE.calendarioEnvioEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
