package com.dto;

import java.util.Date;

public class GruppoMontaDTO {
	private int gmoId;
	private Date gmoDataInserimento;
	private Date gmoDataUscita;
	private String gmoDestinazioneUscita;
	private int gmoAnaId;
	private AnagraficaDTO anagrafica;
	private StoricoGruppiMontaDTO storicoGruppiMonta;
	private int gmoSgmId;

	public int getGmoId() {
		return gmoId;
	}

	public void setGmoId(int gmoId) {
		this.gmoId = gmoId;
	}

	public Date getGmoDataInserimento() {
		return gmoDataInserimento;
	}

	public void setGmoDataInserimento(Date gmoDataInserimento) {
		this.gmoDataInserimento = gmoDataInserimento;
	}

	public Date getGmoDataUscita() {
		return gmoDataUscita;
	}

	public void setGmoDataUscita(Date gmoDataUscita) {
		this.gmoDataUscita = gmoDataUscita;
	}

	public String getGmoDestinazioneUscita() {
		return gmoDestinazioneUscita;
	}

	public void setGmoDestinazioneUscita(String gmoDestinazioneUscita) {
		this.gmoDestinazioneUscita = gmoDestinazioneUscita;
	}

	public int getGmoAnaId() {
		return gmoAnaId;
	}

	public void setGmoAnaId(int gmoAnaId) {
		this.gmoAnaId = gmoAnaId;
	}

	public AnagraficaDTO getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(AnagraficaDTO anagrafica) {
		this.anagrafica = anagrafica;
	}

	public StoricoGruppiMontaDTO getStoricoGruppiMonta() {
		return storicoGruppiMonta;
	}

	public void setStoricoGruppiMonta(StoricoGruppiMontaDTO storicoGruppiMonta) {
		this.storicoGruppiMonta = storicoGruppiMonta;
	}

	public int getGmoSgmId() {
		return gmoSgmId;
	}

	public void setGmoSgmId(int gmoSgmId) {
		this.gmoSgmId = gmoSgmId;
	}
}
