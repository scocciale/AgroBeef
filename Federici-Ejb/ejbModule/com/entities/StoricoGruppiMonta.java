package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the storico_gruppi_monta database table.
 * 
 */
@Entity
@Table(name = "storico_gruppi_monta")
@NamedQuery(name = "StoricoGruppiMonta.findAll", query = "SELECT s FROM StoricoGruppiMonta s")
public class StoricoGruppiMonta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sgm_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sgmId;

	@Temporal(TemporalType.DATE)
	@Column(name = "sgm_data_apertura")
	private Date sgmDataApertura;

	@Temporal(TemporalType.DATE)
	@Column(name = "sgm_data_chiusura")
	private Date sgmDataChiusura;

	@Column(name = "sgm_nome")
	private String sgmNome;

	@Column(name = "sgm_ute_id")
	private int sgmUteId;

	// bi-directional many-to-one association to GruppoMonta
	@OneToMany(mappedBy = "storicoGruppiMonta")
	private List<GruppoMonta> gruppoMontas;

	// bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name = "sgm_ute_id", insertable = false, updatable = false)
	private Utente utente;

	public StoricoGruppiMonta() {
	}

	public int getSgmId() {
		return this.sgmId;
	}

	public void setSgmId(int sgmId) {
		this.sgmId = sgmId;
	}

	public Date getSgmDataApertura() {
		return this.sgmDataApertura;
	}

	public void setSgmDataApertura(Date sgmDataApertura) {
		this.sgmDataApertura = sgmDataApertura;
	}

	public Date getSgmDataChiusura() {
		return this.sgmDataChiusura;
	}

	public void setSgmDataChiusura(Date sgmDataChiusura) {
		this.sgmDataChiusura = sgmDataChiusura;
	}

	public String getSgmNome() {
		return this.sgmNome;
	}

	public void setSgmNome(String sgmNome) {
		this.sgmNome = sgmNome;
	}

	public List<GruppoMonta> getGruppoMontas() {
		return this.gruppoMontas;
	}

	public void setGruppoMontas(List<GruppoMonta> gruppoMontas) {
		this.gruppoMontas = gruppoMontas;
	}

	public GruppoMonta addGruppoMonta(GruppoMonta gruppoMonta) {
		getGruppoMontas().add(gruppoMonta);
		gruppoMonta.setStoricoGruppiMonta(this);

		return gruppoMonta;
	}

	public GruppoMonta removeGruppoMonta(GruppoMonta gruppoMonta) {
		getGruppoMontas().remove(gruppoMonta);
		gruppoMonta.setStoricoGruppiMonta(null);

		return gruppoMonta;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public int getSgmUteId() {
		return sgmUteId;
	}

	public void setSgmUteId(int sgmUteId) {
		this.sgmUteId = sgmUteId;
	}

	@Override
	public String toString() {
		return "StoricoGruppiMonta [sgmId=" + sgmId + ", sgmDataApertura=" + sgmDataApertura + ", sgmDataChiusura="
				+ sgmDataChiusura + ", sgmNome=" + sgmNome + ", sgmUteId=" + sgmUteId + "]";
	}

}