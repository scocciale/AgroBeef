package com.dto;

import java.util.Date;

public class PesataDTO {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pesId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PesataDTO other = (PesataDTO) obj;
		if (pesId != other.pesId)
			return false;
		return true;
	}

	private int pesId;
	private Date pesData;
	private String pesFase;
	private Double pesPeso;
	private int pesAnaId;
	private AnagraficaDTO anagrafica;
	private Double deltaPeso;

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

	public Double getDeltaPeso() {
		return deltaPeso;
	}

	public void setDeltaPeso(Double deltaPeso) {
		this.deltaPeso = deltaPeso;
	}

}
