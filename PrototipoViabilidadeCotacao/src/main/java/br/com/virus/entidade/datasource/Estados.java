package br.com.virus.entidade.datasource;

import java.util.Date;

public class Estados {

	private String uid;
	private String uf;
	private String state;
	private String cases;
	private String deaths;
	private String suspects;
	private Date datetime;
	private String refuses;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCases() {
		return cases;
	}
	public void setCases(String cases) {
		this.cases = cases;
	}
	public String getDeaths() {
		return deaths;
	}
	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}
	public String getSuspects() {
		return suspects;
	}
	public void setSuspects(String suspects) {
		this.suspects = suspects;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getRefuses() {
		return refuses;
	}
	public void setRefuses(String refuses) {
		this.refuses = refuses;
	}

	

}
