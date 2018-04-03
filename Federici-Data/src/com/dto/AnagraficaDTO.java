package com.dto;

import java.util.Date;
import java.util.List;

public class AnagraficaDTO {

	private int anaId;
	private Date anaDataAcquisto;
	private Date anaDataNascita;
	private String anaDifficoltaParto;
	private String anaFlagGemello;
	private int anaFlagToro;
	public String anaNumMatricola;
	private String anaNumMatricolaMadre;
	private String anaNumMatricolaPadre;
	private int anaNumParto;
	private String anaRazza;
	private String anaSesso;
	private int anaUteId;
	private UtenteDTO utente;
	private List<AnagrAccrFiniDTO> anagrAccrFinis;
	private List<GruppoMontaDTO> gruppoMontas;
	private List<PesataDTO> pesatas;
	private List<ValutazioneMaceDTO> valutazioneMaces;
	private List<VeterinariaDTO> veterinarias;
	private Date anaDataUscita;
	private String anaUscitaCausa;
	private RazzaDTO razza;
	private int anaRazId;
	private String anaFlagDisponibile;
	
	public int getAnaId() {
		return anaId;
	}

	public void setAnaId(int anaId) {
		this.anaId = anaId;
	}

	@Override
	public String toString() {
		return "AnagraficaDTO [anaId=" + anaId + ", anaDataAcquisto=" + anaDataAcquisto + ", anaDataNascita="
				+ anaDataNascita + ", anaDifficoltaParto=" + anaDifficoltaParto + ", anaFlagGemello=" + anaFlagGemello
				+ ", anaFlagToro=" + anaFlagToro + ", anaNumMatricola=" + anaNumMatricola + ", anaNumMatricolaMadre="
				+ anaNumMatricolaMadre + ", anaNumMatricolaPadre=" + anaNumMatricolaPadre + ", anaNumParto="
				+ anaNumParto + ", anaRazza=" + anaRazza + ", anaSesso=" + anaSesso + ", anaUteId=" + anaUteId + "]";
	}

	public Date getAnaDataAcquisto() {
		return anaDataAcquisto;
	}

	public void setAnaDataAcquisto(Date anaDataAcquisto) {
		this.anaDataAcquisto = anaDataAcquisto;
	}

	public Date getAnaDataNascita() {
		return anaDataNascita;
	}

	public void setAnaDataNascita(Date anaDataNascita) {
		this.anaDataNascita = anaDataNascita;
	}

	public String getAnaDifficoltaParto() {
		return anaDifficoltaParto;
	}

	public void setAnaDifficoltaParto(String anaDifficoltaParto) {
		this.anaDifficoltaParto = anaDifficoltaParto;
	}

	public String getAnaFlagGemello() {
		return anaFlagGemello;
	}

	public void setAnaFlagGemello(String anaFlagGemello) {
		this.anaFlagGemello = anaFlagGemello;
	}

	public int getAnaFlagToro() {
		return anaFlagToro;
	}

	public void setAnaFlagToro(int anaFlagToro) {
		this.anaFlagToro = anaFlagToro;
	}

	public String getAnaNumMatricola() {
		return anaNumMatricola;
	}

	public void setAnaNumMatricola(String anaNumMatricola) {
		this.anaNumMatricola = anaNumMatricola;
	}

	public String getAnaNumMatricolaMadre() {
		return anaNumMatricolaMadre;
	}

	public void setAnaNumMatricolaMadre(String anaNumMatricolaMadre) {
		this.anaNumMatricolaMadre = anaNumMatricolaMadre;
	}

	public String getAnaNumMatricolaPadre() {
		return anaNumMatricolaPadre;
	}

	public void setAnaNumMatricolaPadre(String anaNumMatricolaPadre) {
		this.anaNumMatricolaPadre = anaNumMatricolaPadre;
	}

	public int getAnaNumParto() {
		return anaNumParto;
	}

	public void setAnaNumParto(int anaNumParto) {
		this.anaNumParto = anaNumParto;
	}

	public String getAnaRazza() {
		return anaRazza;
	}

	public void setAnaRazza(String anaRazza) {
		this.anaRazza = anaRazza;
	}

	public String getAnaSesso() {
		return anaSesso;
	}

	public void setAnaSesso(String anaSesso) {
		this.anaSesso = anaSesso;
	}

	public int getAnaUteId() {
		return anaUteId;
	}

	public void setAnaUteId(int anaUteId) {
		this.anaUteId = anaUteId;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public List<AnagrAccrFiniDTO> getAnagrAccrFinis() {
		return anagrAccrFinis;
	}

	public void setAnagrAccrFinis(List<AnagrAccrFiniDTO> anagrAccrFinis) {
		this.anagrAccrFinis = anagrAccrFinis;
	}

	public List<GruppoMontaDTO> getGruppoMontas() {
		return gruppoMontas;
	}

	public void setGruppoMontas(List<GruppoMontaDTO> gruppoMontas) {
		this.gruppoMontas = gruppoMontas;
	}

	public List<PesataDTO> getPesatas() {
		return pesatas;
	}

	public void setPesatas(List<PesataDTO> pesatas) {
		this.pesatas = pesatas;
	}

	public List<ValutazioneMaceDTO> getValutazioneMaces() {
		return valutazioneMaces;
	}

	public void setValutazioneMaces(List<ValutazioneMaceDTO> valutazioneMaces) {
		this.valutazioneMaces = valutazioneMaces;
	}

	public List<VeterinariaDTO> getVeterinarias() {
		return veterinarias;
	}

	public void setVeterinarias(List<VeterinariaDTO> veterinarias) {
		this.veterinarias = veterinarias;
	}

	public Date getAnaDataUscita() {
		return anaDataUscita;
	}

	public void setAnaDataUscita(Date anaDataUscita) {
		this.anaDataUscita = anaDataUscita;
	}

	public String getAnaUscitaCausa() {
		return anaUscitaCausa;
	}

	public void setAnaUscitaCausa(String anaUscitaCausa) {
		this.anaUscitaCausa = anaUscitaCausa;
	}

	public RazzaDTO getRazza() {
		return razza;
	}

	public void setRazza(RazzaDTO razza) {
		this.razza = razza;
	}

	public int getAnaRazId() {
		return anaRazId;
	}

	public void setAnaRazId(int anaRazId) {
		this.anaRazId = anaRazId;
	}

	public String getAnaFlagDisponibile() {
		return anaFlagDisponibile;
	}

	public void setAnaFlagDisponibile(String anaFlagDisponibile) {
		this.anaFlagDisponibile = anaFlagDisponibile;
	}

}
