package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the profilo database table.
 * 
 */
@Entity
@NamedQuery(name = "Profilo.findAll", query = "SELECT p FROM Profilo p")
public class Profilo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pro_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int proId;

	@Column(name = "pro_descrizione")
	private String proDescrizione;

	@Column(name = "pro_nome")
	private String proNome;

	// bi-directional many-to-one association to Utente
	@OneToMany(mappedBy = "profilo")
	private List<Utente> utentes;

	public Profilo() {
	}

	public int getProId() {
		return this.proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProDescrizione() {
		return this.proDescrizione;
	}

	public void setProDescrizione(String proDescrizione) {
		this.proDescrizione = proDescrizione;
	}

	public String getProNome() {
		return this.proNome;
	}

	public void setProNome(String proNome) {
		this.proNome = proNome;
	}

	public List<Utente> getUtentes() {
		return this.utentes;
	}

	public void setUtentes(List<Utente> utentes) {
		this.utentes = utentes;
	}

	public Utente addUtente(Utente utente) {
		getUtentes().add(utente);
		utente.setProfilo(this);

		return utente;
	}

	public Utente removeUtente(Utente utente) {
		getUtentes().remove(utente);
		utente.setProfilo(null);

		return utente;
	}

}