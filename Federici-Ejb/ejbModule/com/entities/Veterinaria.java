package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the veterinaria database table.
 * 
 */
@Entity
@NamedQuery(name = "Veterinaria.findAll", query = "SELECT v FROM Veterinaria v")
public class Veterinaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "vet_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vetId;

	@Temporal(TemporalType.DATE)
	@Column(name = "vet_data_diagnosi_grav")
	private Date vetDataDiagnosiGrav;

	@Temporal(TemporalType.DATE)
	@Column(name = "vet_data_visita")
	private Date vetDataVisita;

	@Column(name = "vet_esito_grav")
	private String vetEsitoGrav;

	@Column(name = "vet_farmaci_utilizzati")
	private String vetFarmaciUtilizzati;

	@Column(name = "vet_motivo_visita")
	private String vetMotivoVisita;

	@Column(name = "vet_ana_id")
	private int vetAnaId;

	// bi-directional many-to-one association to Anagrafica
	@ManyToOne
	@JoinColumn(name = "vet_ana_id", insertable = false, updatable = false)
	private Anagrafica anagrafica;

	public Veterinaria() {
	}

	public int getVetId() {
		return this.vetId;
	}

	public void setVetId(int vetId) {
		this.vetId = vetId;
	}

	public Date getVetDataDiagnosiGrav() {
		return this.vetDataDiagnosiGrav;
	}

	public void setVetDataDiagnosiGrav(Date vetDataDiagnosiGrav) {
		this.vetDataDiagnosiGrav = vetDataDiagnosiGrav;
	}

	public String getVetEsitoGrav() {
		return this.vetEsitoGrav;
	}

	public void setVetEsitoGrav(String vetEsitoGrav) {
		this.vetEsitoGrav = vetEsitoGrav;
	}

	public String getVetFarmaciUtilizzati() {
		return this.vetFarmaciUtilizzati;
	}

	public void setVetFarmaciUtilizzati(String vetFarmaciUtilizzati) {
		this.vetFarmaciUtilizzati = vetFarmaciUtilizzati;
	}

	public String getVetMotivoVisita() {
		return this.vetMotivoVisita;
	}

	public void setVetMotivoVisita(String vetMotivoVisita) {
		this.vetMotivoVisita = vetMotivoVisita;
	}

	public Anagrafica getAnagrafica() {
		return this.anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public int getVetAnaId() {
		return vetAnaId;
	}

	public void setVetAnaId(int vetAnaId) {
		this.vetAnaId = vetAnaId;
	}

	public Date getVetDataVisita() {
		return vetDataVisita;
	}

	public void setVetDataVisita(Date vetDataVisita) {
		this.vetDataVisita = vetDataVisita;
	}

}