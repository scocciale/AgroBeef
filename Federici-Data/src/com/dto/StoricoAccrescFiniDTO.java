package com.dto;

import java.util.Date;
import java.util.List;

public class StoricoAccrescFiniDTO {
	private int safId;
	private Date safDataFine;
	private Date safDataInizio;
	private String safNumAppezzamento;
	private String safScopo;
	private int safUteId;
	private List<AnagrAccrFiniDTO> anagrAccrFinis;
	private UtenteDTO utente;

	public int getSafId() {
		return safId;
	}

	public void setSafId(int safId) {
		this.safId = safId;
	}

	public Date getSafDataFine() {
		return safDataFine;
	}

	public void setSafDataFine(Date safDataFine) {
		this.safDataFine = safDataFine;
	}

	public Date getSafDataInizio() {
		return safDataInizio;
	}

	public void setSafDataInizio(Date safDataInizio) {
		this.safDataInizio = safDataInizio;
	}

	public String getSafNumAppezzamento() {
		return safNumAppezzamento;
	}

	public void setSafNumAppezzamento(String safNumAppezzamento) {
		this.safNumAppezzamento = safNumAppezzamento;
	}

	public String getSafScopo() {
		return safScopo;
	}

	public void setSafScopo(String safScopo) {
		this.safScopo = safScopo;
	}

	public int getSafUteId() {
		return safUteId;
	}

	public void setSafUteId(int safUteId) {
		this.safUteId = safUteId;
	}

	public List<AnagrAccrFiniDTO> getAnagrAccrFinis() {
		return anagrAccrFinis;
	}

	public void setAnagrAccrFinis(List<AnagrAccrFiniDTO> anagrAccrFinis) {
		this.anagrAccrFinis = anagrAccrFinis;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

}
