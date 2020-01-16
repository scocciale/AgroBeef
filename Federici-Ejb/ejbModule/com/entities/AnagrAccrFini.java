package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQuery;

/**
 * The persistent class for the anagr_accr_finis database table.
 * 
 */
@Entity
@NamedQuery(name = "AnagrAccrFini.findAll", query = "SELECT a FROM AnagrAccrFini a")
public class AnagrAccrFini implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "aaf_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aafId;

	@Temporal(TemporalType.DATE)
	@Column(name = "aaf_data_entrata")
	private Date aafDataEntrata;

	@Temporal(TemporalType.DATE)
	@Column(name = "aaf_data_uscita")
	private Date aafDataUscita;

	@Column(name = "aaf_destinazione_uscita")
	private String aafDestinazioneUscita;

	@Column(name = "aaf_ana_id")
	private int aafAnaId;

	@Column(name = "aaf_saf_id")
	private int aafSafId;

	// bi-directional many-to-one association to Anagrafica
	@ManyToOne
	@JoinColumn(name = "aaf_ana_id", insertable = false, updatable = false)
	private Anagrafica anagrafica;

	// bi-directional many-to-one association to StoricoAccrescFini
	@ManyToOne
	@JoinColumn(name = "aaf_saf_id", insertable = false, updatable = false)
	private StoricoAccrescFini storicoAccrescFini;

	public AnagrAccrFini() {
	}

	public int getAafId() {
		return this.aafId;
	}

	public void setAafId(int aafId) {
		this.aafId = aafId;
	}

	public Date getAafDataEntrata() {
		return this.aafDataEntrata;
	}

	public void setAafDataEntrata(Date aafDataEntrata) {
		this.aafDataEntrata = aafDataEntrata;
	}

	public Date getAafDataUscita() {
		return this.aafDataUscita;
	}

	public void setAafDataUscita(Date aafDataUscita) {
		this.aafDataUscita = aafDataUscita;
	}

	public String getAafDestinazioneUscita() {
		return this.aafDestinazioneUscita;
	}

	public void setAafDestinazioneUscita(String aafDestinazioneUscita) {
		this.aafDestinazioneUscita = aafDestinazioneUscita;
	}

	public Anagrafica getAnagrafica() {
		return this.anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public StoricoAccrescFini getStoricoAccrescFini() {
		return this.storicoAccrescFini;
	}

	public void setStoricoAccrescFini(StoricoAccrescFini storicoAccrescFini) {
		this.storicoAccrescFini = storicoAccrescFini;
	}

	public int getAafAnaId() {
		return aafAnaId;
	}

	public void setAafAnaId(int aafAnaId) {
		this.aafAnaId = aafAnaId;
	}

	public int getAafSafId() {
		return aafSafId;
	}

	public void setAafSafId(int aafSafId) {
		this.aafSafId = aafSafId;
	}

}