package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the gruppo_monta database table.
 * 
 */
@Entity
@Table(name = "gruppo_monta")
@NamedQuery(name = "GruppoMonta.findAll", query = "SELECT g FROM GruppoMonta g")
public class GruppoMonta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "gmo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gmoId;

	@Temporal(TemporalType.DATE)
	@Column(name = "gmo_data_inserimento")
	private Date gmoDataInserimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "gmo_data_uscita")
	private Date gmoDataUscita;

	@Column(name = "gmo_destinazione_uscita")
	private String gmoDestinazioneUscita;

	@Column(name = "gmo_ana_id")
	private int gmoAnaId;

	// bi-directional many-to-one association to Anagrafica
	@ManyToOne
	@JoinColumn(name = "gmo_ana_id", insertable = false, updatable = false)
	private Anagrafica anagrafica;
	
	@Column(name = "gmo_sgm_id")
	private int gmoSgmId;

	// bi-directional many-to-one association to StoricoGruppiMonta
	@ManyToOne
	@JoinColumn(name = "gmo_sgm_id", insertable = false, updatable = false)
	private StoricoGruppiMonta storicoGruppiMonta;

	public GruppoMonta() {
	}

	public int getGmoId() {
		return this.gmoId;
	}

	public void setGmoId(int gmoId) {
		this.gmoId = gmoId;
	}

	public Date getGmoDataInserimento() {
		return this.gmoDataInserimento;
	}

	public void setGmoDataInserimento(Date gmoDataInserimento) {
		this.gmoDataInserimento = gmoDataInserimento;
	}

	public Date getGmoDataUscita() {
		return this.gmoDataUscita;
	}

	public void setGmoDataUscita(Date gmoDataUscita) {
		this.gmoDataUscita = gmoDataUscita;
	}

	public String getGmoDestinazioneUscita() {
		return this.gmoDestinazioneUscita;
	}

	public void setGmoDestinazioneUscita(String gmoDestinazioneUscita) {
		this.gmoDestinazioneUscita = gmoDestinazioneUscita;
	}

	public Anagrafica getAnagrafica() {
		return this.anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public StoricoGruppiMonta getStoricoGruppiMonta() {
		return this.storicoGruppiMonta;
	}

	public void setStoricoGruppiMonta(StoricoGruppiMonta storicoGruppiMonta) {
		this.storicoGruppiMonta = storicoGruppiMonta;
	}

	public int getGmoAnaId() {
		return gmoAnaId;
	}

	public void setGmoAnaId(int gmoAnaId) {
		this.gmoAnaId = gmoAnaId;
	}

	public int getGmoSgmId() {
		return gmoSgmId;
	}

	public void setGmoSgmId(int gmoSgmId) {
		this.gmoSgmId = gmoSgmId;
	}

}