package com.dto;

import java.util.Date;

public class AnagrAccrFiniDTO {
	private int aafId;
	private Date aafDataEntrata;
	private Date aafDataUscita;
	private String aafDestinazioneUscita;
	private int aafAnaId;
	private int aafSafId;
	private AnagraficaDTO anagrafica;
	private StoricoAccrescFiniDTO storicoAccrescFini;

	public int getAafId() {
		return aafId;
	}

	public void setAafId(int aafId) {
		this.aafId = aafId;
	}

	public Date getAafDataEntrata() {
		return aafDataEntrata;
	}

	public void setAafDataEntrata(Date aafDataEntrata) {
		this.aafDataEntrata = aafDataEntrata;
	}

	public Date getAafDataUscita() {
		return aafDataUscita;
	}

	public void setAafDataUscita(Date aafDataUscita) {
		this.aafDataUscita = aafDataUscita;
	}

	public String getAafDestinazioneUscita() {
		return aafDestinazioneUscita;
	}

	public void setAafDestinazioneUscita(String aafDestinazioneUscita) {
		this.aafDestinazioneUscita = aafDestinazioneUscita;
	}

	public int getAafAnaId() {
		return aafAnaId;
	}

	public void setAafAnaId(int aafAnaId) {
		this.aafAnaId = aafAnaId;
	}

	public AnagraficaDTO getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(AnagraficaDTO anagrafica) {
		this.anagrafica = anagrafica;
	}

	public StoricoAccrescFiniDTO getStoricoAccrescFini() {
		return storicoAccrescFini;
	}

	public void setStoricoAccrescFini(StoricoAccrescFiniDTO storicoAccrescFini) {
		this.storicoAccrescFini = storicoAccrescFini;
	}

	public int getAafSafId() {
		return aafSafId;
	}

	public void setAafSafId(int aafSafId) {
		this.aafSafId = aafSafId;
	}

}
