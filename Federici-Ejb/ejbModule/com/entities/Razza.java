package com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the razza database table.
 * 
 */
@Entity
@NamedQuery(name = "Razza.findAll", query = "SELECT r FROM Razza r")
public class Razza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "raz_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int razId;

	@Column(name = "raz_sigla")
	private String razSigla;

	@Column(name = "raz_descrizione")
	private String razDescrizione;

	@Column(name = "raz_cod_aia")
	private String razCodAia;

	public Razza() {
	}

	public int getRazId() {
		return razId;
	}

	public void setRazId(int razId) {
		this.razId = razId;
	}

	public String getRazSigla() {
		return razSigla;
	}

	public void setRazSigla(String razSigla) {
		this.razSigla = razSigla;
	}

	public String getRazDescrizione() {
		return razDescrizione;
	}

	public void setRazDescrizione(String razDescrizione) {
		this.razDescrizione = razDescrizione;
	}

	public String getRazCodAia() {
		return razCodAia;
	}

	public void setRazCodAia(String razCodAia) {
		this.razCodAia = razCodAia;
	}

}