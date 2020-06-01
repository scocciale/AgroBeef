package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the parto database table.
 * 
 */
@Entity
@NamedQuery(name = "Parto.findAll", query = "SELECT p FROM Parto p")
public class Parto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "par_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int parId;

	// ana_id matricola del bovino nato
	@Column(name = "par_ana_id")
	private int parAnaId;

	@Temporal(TemporalType.DATE)
	@Column(name = "par_data")
	private Date parData;

	// ana_id matricola della madre
	@Column(name = "par_madre_ana_id")
	private int parMadreAnaId;

	public Parto() {
	}

	public int getParId() {
		return this.parId;
	}

	public void setParId(int parId) {
		this.parId = parId;
	}

	public int getParAnaId() {
		return this.parAnaId;
	}

	public void setParAnaId(int parAnaId) {
		this.parAnaId = parAnaId;
	}

	public Date getParData() {
		return this.parData;
	}

	public void setParData(Date parData) {
		this.parData = parData;
	}

	public int getParMadreAnaId() {
		return this.parMadreAnaId;
	}

	public void setParMadreAnaId(int parMadreAnaId) {
		this.parMadreAnaId = parMadreAnaId;
	}

}