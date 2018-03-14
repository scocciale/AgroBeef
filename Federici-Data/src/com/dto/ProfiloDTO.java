package com.dto;

import java.util.List;

public class ProfiloDTO {
	private int proId;
	private String proDescrizione;
	private String proNome;
	private List<UtenteDTO> utentes;

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProDescrizione() {
		return proDescrizione;
	}

	public void setProDescrizione(String proDescrizione) {
		this.proDescrizione = proDescrizione;
	}

	public String getProNome() {
		return proNome;
	}

	public void setProNome(String proNome) {
		this.proNome = proNome;
	}

	public List<UtenteDTO> getUtentes() {
		return utentes;
	}

	public void setUtentes(List<UtenteDTO> utentes) {
		this.utentes = utentes;
	}

}
