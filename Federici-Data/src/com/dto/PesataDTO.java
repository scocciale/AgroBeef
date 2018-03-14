package com.dto;

import java.util.Date;

public class PesataDTO {
	private int pesId;
	private Date pesData;
	private String pesFase;
	private Double pesPeso;
	private int pesAnaId;
	private AnagraficaDTO anagrafica;

	public int getPesId() {
		return pesId;
	}

	public void setPesId(int pesId) {
		this.pesId = pesId;
	}

	public Date getPesData() {
		return pesData;
	}

	public void setPesData(Date pesData) {
		this.pesData = pesData;
	}

	public String getPesFase() {
		return pesFase;
	}

	public void setPesFase(String pesFase) {
		this.pesFase = pesFase;
	}

	public Double getPesPeso() {
		return pesPeso;
	}

	public void setPesPeso(Double pesPeso) {
		this.pesPeso = pesPeso;
	}

	public int getPesAnaId() {
		return pesAnaId;
	}

	public void setPesAnaId(int pesAnaId) {
		this.pesAnaId = pesAnaId;
	}

	public AnagraficaDTO getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(AnagraficaDTO anagrafica) {
		this.anagrafica = anagrafica;
	}
}
