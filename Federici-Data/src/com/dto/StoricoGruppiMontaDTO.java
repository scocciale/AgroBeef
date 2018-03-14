package com.dto;

import java.util.Date;
import java.util.List;

public class StoricoGruppiMontaDTO {
	private int sgmId;
	private Date sgmDataApertura;
	private Date sgmDataChiusura;
	private String sgmNome;
	private int sgmUteId;
	private List<GruppoMontaDTO> gruppoMontas;
	private UtenteDTO utente;

	public int getSgmId() {
		return sgmId;
	}

	public void setSgmId(int sgmId) {
		this.sgmId = sgmId;
	}

	public Date getSgmDataApertura() {
		return sgmDataApertura;
	}

	public void setSgmDataApertura(Date sgmDataApertura) {
		this.sgmDataApertura = sgmDataApertura;
	}

	public Date getSgmDataChiusura() {
		return sgmDataChiusura;
	}

	public void setSgmDataChiusura(Date sgmDataChiusura) {
		this.sgmDataChiusura = sgmDataChiusura;
	}

	public String getSgmNome() {
		return sgmNome;
	}

	public void setSgmNome(String sgmNome) {
		this.sgmNome = sgmNome;
	}

	public int getSgmUteId() {
		return sgmUteId;
	}

	public void setSgmUteId(int sgmUteId) {
		this.sgmUteId = sgmUteId;
	}

	public List<GruppoMontaDTO> getGruppoMontas() {
		return gruppoMontas;
	}

	public void setGruppoMontas(List<GruppoMontaDTO> gruppoMontas) {
		this.gruppoMontas = gruppoMontas;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

}
