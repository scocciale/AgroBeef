package com.dto;

import java.util.List;

public class ReportResultDTO {

	private AnagraficaDTO anag;
	private Integer mesiEta;
	private Integer numParti;
	private Double indice;
	private String seuropString;
	private Integer seuropIndice;
	private Integer giorniInterparto;
//	private List<AnagraficaDTO> anagPartiList;
	private List<ReportResultDTO> rrList;
	
	public AnagraficaDTO getAnag() {
		return anag;
	}

	public void setAnag(AnagraficaDTO anag) {
		this.anag = anag;
	}

	public Integer getMesiEta() {
		return mesiEta;
	}

	public void setMesiEta(Integer mesiEta) {
		this.mesiEta = mesiEta;
	}

	public Integer getNumParti() {
		return numParti;
	}

	public void setNumParti(Integer numParti) {
		this.numParti = numParti;
	}

	public Double getIndice() {
		return indice;
	}

	public void setIndice(Double indice) {
		this.indice = indice;
	}

	public String getSeuropString() {
		return seuropString;
	}

	public void setSeuropString(String seuropString) {
		this.seuropString = seuropString;
	}

	public Integer getSeuropIndice() {
		return seuropIndice;
	}

	public void setSeuropIndice(Integer seuropIndice) {
		this.seuropIndice = seuropIndice;
	}

	public Integer getGiorniInterparto() {
		return giorniInterparto;
	}

	public void setGiorniInterparto(Integer giorniInterparto) {
		this.giorniInterparto = giorniInterparto;
	}

	public List<ReportResultDTO> getRrList() {
		return rrList;
	}

	public void setRrList(List<ReportResultDTO> rrList) {
		this.rrList = rrList;
	}

//	public List<AnagraficaDTO> getAnagPartiList() {
//		return anagPartiList;
//	}
//
//	public void setAnagPartiList(List<AnagraficaDTO> anagPartiList) {
//		this.anagPartiList = anagPartiList;
//	}

}
