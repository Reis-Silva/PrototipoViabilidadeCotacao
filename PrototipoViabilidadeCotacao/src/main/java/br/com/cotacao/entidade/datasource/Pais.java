package br.com.cotacao.entidade.datasource;

import java.util.Date;

public class Pais {

	private String country;
	private String confirmed;
	private String cases;
	private Date updated_at;
	private String deaths;
	private String recovered;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public String getCases() {
		return cases;
	}
	public void setCases(String cases) {
		this.cases = cases;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public String getDeaths() {
		return deaths;
	}
	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}
	public String getRecovered() {
		return recovered;
	}
	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

}
