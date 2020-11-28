package br.com.cotacao.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import br.com.cotacao.entidade.datasource.Moedas;
import br.com.cotacao.entidade.repository.MoedaRepository;

public class WEBStatus {

	// Armazenando Dados na lista
	public static List<Moedas> listarCotas(String moeda, String dataInicial, String dataFinal) throws Exception {

		WEBStatus ws = new WEBStatus();
		String url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaPeriodo(moeda=@moeda,dataInicial=@dataInicial,dataFinalCotacao=@dataFinalCotacao)?"
				+ "%40moeda='"+ moeda+"'&%40dataInicial='"+dataInicial+"'&%40dataFinalCotacao='"+dataFinal+"'&%24format=json";

		String json = ws.obterDados(url);
		Gson g = new Gson();
		MoedaRepository cotacaoReposit = new MoedaRepository();
		cotacaoReposit = g.fromJson(json, MoedaRepository.class);

		List<Moedas> dadosCotacaoAtual = convertArrayToList(cotacaoReposit.getValue());
		return dadosCotacaoAtual;
	}

	// Obtendo dados da URL
	public String obterDados(String url) throws Exception {

		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		StringBuffer response = new StringBuffer();

		String line;
		while ((line = br.readLine()) != null) {
			response.append(line);
		}

		conn.disconnect();
		return response.toString();
	}

	// Conversão de Arrays
	public static <T> List<T> convertArrayToList(T array[]) {
		List<T> list = new ArrayList<>();
		for (T t : array) {
			list.add(t);
		}
		return list;
	}

}
