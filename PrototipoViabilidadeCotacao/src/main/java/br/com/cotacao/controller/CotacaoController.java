package br.com.cotacao.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.cotacao.entidade.datasource.Estados;
import br.com.cotacao.entidade.repository.PaisRepository;
import br.com.cotacao.service.WEBStatus;

@ApplicationScoped
@ManagedBean
public class CotacaoController implements Serializable {

	private static final long serialVersionUID = 1L;

	// Iiniciando construção de lista

	@Inject
	private Estados estado;

	@Inject
	private PaisRepository pais;
	
	@Inject
	private PaisRepository paisExterno;
	
	@Inject
	private String inputPais;

	public String getInputPais() {
		return inputPais;
	}

	public void setInputPais(String inputPais) {
		this.inputPais = inputPais;
	}

	@Inject
	private List<Estados> estados;

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public PaisRepository getPais() {
		return pais;
	}

	public void setPais(PaisRepository pais) {
		this.pais = pais;
	}

	public PaisRepository getPaisExterno() {
		return paisExterno;
	}

	public void setPaisExterno(PaisRepository paisExterno) {
		this.paisExterno = paisExterno;
	}

	public List<Estados> getEstados() {
		return estados;
	}

	public void setEstados(List<Estados> estados) {
		this.estados = estados;
	}

	public void pesquisaExterna() throws Exception {
		
		paisExterno = WEBStatus.paisesDetalhes(inputPais);
	}
	
	
	// Inicio automático da página
	@PostConstruct
	public void init() {
		try {
			setEstados(WEBStatus.listarEstados());
			pais = WEBStatus.paisesDetalhes("Brazil");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
