package com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the report database table.
 * 
 */
@Entity
@NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "rep_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int repId;

	@Column(name = "rep_nome")
	private String repNome;

	@Column(name = "rep_descrizione")
	private String repDescrizione;

	@Column(name = "rep_query")
	private String repQuery;

	public Report() {
	}

	public int getRepId() {
		return repId;
	}

	public void setRepId(int repId) {
		this.repId = repId;
	}

	public String getRepNome() {
		return repNome;
	}

	public void setRepNome(String repNome) {
		this.repNome = repNome;
	}

	public String getRepDescrizione() {
		return repDescrizione;
	}

	public void setRepDescrizione(String repDescrizione) {
		this.repDescrizione = repDescrizione;
	}

	public String getRepQuery() {
		return repQuery;
	}

	public void setRepQuery(String repQuery) {
		this.repQuery = repQuery;
	}

}