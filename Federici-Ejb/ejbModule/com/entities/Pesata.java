package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the pesata database table.
 * 
 */
@Entity
@NamedQuery(name = "Pesata.findAll", query = "SELECT p FROM Pesata p")
public class Pesata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pes_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pesId;

	@Temporal(TemporalType.DATE)
	@Column(name = "pes_data")
	private Date pesData;

	@Column(name = "pes_fase")
	private String pesFase;

	@Column(name = "pes_peso")
	private Double pesPeso;

	@Column(name = "pes_ana_id")
	private int pesAnaId;

	// bi-directional many-to-one association to Anagrafica
	@ManyToOne
	@JoinColumn(name = "pes_ana_id", insertable = false, updatable = false)
	private Anagrafica anagrafica;

	public Pesata() {
	}

	public int getPesId() {
		return this.pesId;
	}

	public void setPesId(int pesId) {
		this.pesId = pesId;
	}

	public Date getPesData() {
		return this.pesData;
	}

	public void setPesData(Date pesData) {
		this.pesData = pesData;
	}

	public String getPesFase() {
		return this.pesFase;
	}

	public void setPesFase(String pesFase) {
		this.pesFase = pesFase;
	}

	public Double getPesPeso() {
		return this.pesPeso;
	}

	public void setPesPeso(Double pesPeso) {
		this.pesPeso = pesPeso;
	}

	public Anagrafica getAnagrafica() {
		return this.anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public int getPesAnaId() {
		return pesAnaId;
	}

	public void setPesAnaId(int pesAnaId) {
		this.pesAnaId = pesAnaId;
	}

}