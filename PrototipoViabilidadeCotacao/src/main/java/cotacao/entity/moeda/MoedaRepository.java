package cotacao.entity.moedas;

import java.util.Arrays;

//Dados dos Estados
public class MoedaRepository {

	private Moedas[] value;

	public Moedas[] getValue() {
		return value;
	}

	public void setData(Moedas[] value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Arrays.toString(value);
	}
}
