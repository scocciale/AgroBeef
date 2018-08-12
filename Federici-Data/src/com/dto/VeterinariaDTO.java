package com.dto;

import java.util.Date;

public class VeterinariaDTO {

	private int vetId;
	private AnagraficaDTO anagrafica;

	private int vetAnaId;

	private Date vetDataDiagnosiGrav;
	private String vetEsitoGrav;
	private String vetFarmaciUtilizzati;
	private String vetMotivoVisita;
	private Date vetDataVisita;
	private String vetCommento;
	
	private int commentoColumnLen=255;

	public int getVetId() {
		return vetId;
	}

	public void setVetId(int vetId) {
		this.vetId = vetId;
	}

	public AnagraficaDTO getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(AnagraficaDTO anagrafica) {
		this.anagrafica = anagrafica;
	}

	public int getVetAnaId() {
		return vetAnaId;
	}

	public void setVetAnaId(int vetAnaId) {
		this.vetAnaId = vetAnaId;
	}

	public Date getVetDataDiagnosiGrav() {
		return vetDataDiagnosiGrav;
	}

	public void setVetDataDiagnosiGrav(Date vetDataDiagnosiGrav) {
		this.vetDataDiagnosiGrav = vetDataDiagnosiGrav;
	}

	public String getVetEsitoGrav() {
		return vetEsitoGrav;
	}

	public void setVetEsitoGrav(String vetEsitoGrav) {
		this.vetEsitoGrav = vetEsitoGrav;
	}

	public String getVetFarmaciUtilizzati() {
		return vetFarmaciUtilizzati;
	}

	public void setVetFarmaciUtilizzati(String vetFarmaciUtilizzati) {
		this.vetFarmaciUtilizzati = vetFarmaciUtilizzati;
	}

	public String getVetMotivoVisita() {
		return vetMotivoVisita;
	}

	public void setVetMotivoVisita(String vetMotivoVisita) {
		this.vetMotivoVisita = vetMotivoVisita;
	}

	public Date getVetDataVisita() {
		return vetDataVisita;
	}

	public void setVetDataVisita(Date vetDataVisita) {
		this.vetDataVisita = vetDataVisita;
	}

	public String getVetCommento() {
		return vetCommento;
	}

	public void setVetCommento(String vetCommento) {
		this.vetCommento = vetCommento;
	}

	public int getCommentoColumnLen() {
		return commentoColumnLen;
	}

	public void setCommentoColumnLen(int commentoColumnLen) {
		this.commentoColumnLen = commentoColumnLen;
	}

}
