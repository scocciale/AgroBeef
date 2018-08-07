package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.sql.Timestamp;

/**
 * The persistent class for the utente database table.
 * 
 */
@Entity
@NamedQuery(name = "Utente.findAll", query = "SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ute_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uteId;

	@Column(name = "ute_cognome")
	private String uteCognome;

	@Column(name = "ute_nome")
	private String uteNome;

	@Column(name = "ute_pwd")
	private String utePwd;

	@Column(name = "ute_username")
	private String uteUsername;
	
	@Column(name = "ute_rif_id")
	private int uteRifId;
	
	@Column(name = "ute_ultimo_acc")
	private Timestamp uteUltimoAcc;

	// bi-directional many-to-one association to Anagrafica
	@OneToMany(mappedBy = "utente")
	private List<Anagrafica> anagraficas;

	// bi-directional many-to-one association to StoricoAccrescFini
	@OneToMany(mappedBy = "utente")
	private List<StoricoAccrescFini> storicoAccrescFinis;

	// bi-directional many-to-one association to StoricoGruppiMonta
	@OneToMany(mappedBy = "utente")
	private List<StoricoGruppiMonta> storicoGruppiMontas;

	// bi-directional many-to-one association to Profilo
	@ManyToOne
	@JoinColumn(name = "ute_pro_id")
	private Profilo profilo;

	public Utente() {
	}

	public int getUteId() {
		return this.uteId;
	}

	public void setUteId(int uteId) {
		this.uteId = uteId;
	}

	public String getUteCognome() {
		return this.uteCognome;
	}

	public void setUteCognome(String uteCognome) {
		this.uteCognome = uteCognome;
	}

	public String getUteNome() {
		return this.uteNome;
	}

	public void setUteNome(String uteNome) {
		this.uteNome = uteNome;
	}

	public String getUtePwd() {
		return this.utePwd;
	}

	public void setUtePwd(String utePwd) {
		this.utePwd = utePwd;
	}

	public String getUteUsername() {
		return this.uteUsername;
	}

	public void setUteUsername(String uteUsername) {
		this.uteUsername = uteUsername;
	}

	public List<Anagrafica> getAnagraficas() {
		return this.anagraficas;
	}

	public void setAnagraficas(List<Anagrafica> anagraficas) {
		this.anagraficas = anagraficas;
	}

	public Anagrafica addAnagrafica(Anagrafica anagrafica) {
		getAnagraficas().add(anagrafica);
		anagrafica.setUtente(this);

		return anagrafica;
	}

	public Anagrafica removeAnagrafica(Anagrafica anagrafica) {
		getAnagraficas().remove(anagrafica);
		anagrafica.setUtente(null);

		return anagrafica;
	}

	public List<StoricoAccrescFini> getStoricoAccrescFinis() {
		return this.storicoAccrescFinis;
	}

	public void setStoricoAccrescFinis(List<StoricoAccrescFini> storicoAccrescFinis) {
		this.storicoAccrescFinis = storicoAccrescFinis;
	}

	public StoricoAccrescFini addStoricoAccrescFini(StoricoAccrescFini storicoAccrescFini) {
		getStoricoAccrescFinis().add(storicoAccrescFini);
		storicoAccrescFini.setUtente(this);

		return storicoAccrescFini;
	}

	public StoricoAccrescFini removeStoricoAccrescFini(StoricoAccrescFini storicoAccrescFini) {
		getStoricoAccrescFinis().remove(storicoAccrescFini);
		storicoAccrescFini.setUtente(null);

		return storicoAccrescFini;
	}

	public List<StoricoGruppiMonta> getStoricoGruppiMontas() {
		return this.storicoGruppiMontas;
	}

	public void setStoricoGruppiMontas(List<StoricoGruppiMonta> storicoGruppiMontas) {
		this.storicoGruppiMontas = storicoGruppiMontas;
	}

	public StoricoGruppiMonta addStoricoGruppiMonta(StoricoGruppiMonta storicoGruppiMonta) {
		getStoricoGruppiMontas().add(storicoGruppiMonta);
		storicoGruppiMonta.setUtente(this);

		return storicoGruppiMonta;
	}

	public StoricoGruppiMonta removeStoricoGruppiMonta(StoricoGruppiMonta storicoGruppiMonta) {
		getStoricoGruppiMontas().remove(storicoGruppiMonta);
		storicoGruppiMonta.setUtente(null);

		return storicoGruppiMonta;
	}

	public Profilo getProfilo() {
		return this.profilo;
	}

	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}

	public int getUteRifId() {
		return uteRifId;
	}

	public void setUteRifId(int uteRifId) {
		this.uteRifId = uteRifId;
	}

	public Timestamp getUteUltimoAcc() {
		return uteUltimoAcc;
	}

	public void setUteUltimoAcc(Timestamp uteUltimoAcc) {
		this.uteUltimoAcc = uteUltimoAcc;
	}

	@Override
	public String toString() {
		return "Utente [uteId=" + uteId + ", uteCognome=" + uteCognome + ", uteNome=" + uteNome + ", utePwd=" + utePwd
				+ ", uteUsername=" + uteUsername + ", uteRifId=" + uteRifId + ", uteUltimoAcc=" + uteUltimoAcc
				+ ", anagraficas=" + anagraficas + ", storicoAccrescFinis=" + storicoAccrescFinis
				+ ", storicoGruppiMontas=" + storicoGruppiMontas + ", profilo=" + profilo + "]";
	}

}