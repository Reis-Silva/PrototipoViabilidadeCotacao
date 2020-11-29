package cotacao.entity.moeda;

import java.util.Arrays;

//Dados dos Estados
public class MoedaRepository {

	private Moeda[] value;

	public Moeda[] getValue() {
		return value;
	}

	public void setData(Moeda[] value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Arrays.toString(value);
	}
}
