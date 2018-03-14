package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the storico_accresc_finis database table.
 * 
 */
@Entity
@Table(name = "storico_accresc_finis")
@NamedQuery(name = "StoricoAccrescFini.findAll", query = "SELECT s FROM StoricoAccrescFini s")
public class StoricoAccrescFini implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "saf_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int safId;

	@Temporal(TemporalType.DATE)
	@Column(name = "saf_data_fine")
	private Date safDataFine;

	@Temporal(TemporalType.DATE)
	@Column(name = "saf_data_inizio")
	private Date safDataInizio;

	@Column(name = "saf_num_appezzamento")
	private String safNumAppezzamento;

	@Column(name = "saf_scopo")
	private String safScopo;

	@Column(name = "saf_ute_id")
	private int safUteId;

	// bi-directional many-to-one association to AnagrAccrFini
	@OneToMany(mappedBy = "storicoAccrescFini")
	private List<AnagrAccrFini> anagrAccrFinis;

	// bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name = "saf_ute_id", insertable = false, updatable = false)
	private Utente utente;

	public StoricoAccrescFini() {
	}

	public int getSafId() {
		return this.safId;
	}

	public void setSafId(int safId) {
		this.safId = safId;
	}

	public Date getSafDataFine() {
		return this.safDataFine;
	}

	public void setSafDataFine(Date safDataFine) {
		this.safDataFine = safDataFine;
	}

	public Date getSafDataInizio() {
		return this.safDataInizio;
	}

	public void setSafDataInizio(Date safDataInizio) {
		this.safDataInizio = safDataInizio;
	}

	public String getSafNumAppezzamento() {
		return this.safNumAppezzamento;
	}

	public void setSafNumAppezzamento(String safNumAppezzamento) {
		this.safNumAppezzamento = safNumAppezzamento;
	}

	public String getSafScopo() {
		return this.safScopo;
	}

	public void setSafScopo(String safScopo) {
		this.safScopo = safScopo;
	}

	public List<AnagrAccrFini> getAnagrAccrFinis() {
		return this.anagrAccrFinis;
	}

	public void setAnagrAccrFinis(List<AnagrAccrFini> anagrAccrFinis) {
		this.anagrAccrFinis = anagrAccrFinis;
	}

	public AnagrAccrFini addAnagrAccrFini(AnagrAccrFini anagrAccrFini) {
		getAnagrAccrFinis().add(anagrAccrFini);
		anagrAccrFini.setStoricoAccrescFini(this);

		return anagrAccrFini;
	}

	public AnagrAccrFini removeAnagrAccrFini(AnagrAccrFini anagrAccrFini) {
		getAnagrAccrFinis().remove(anagrAccrFini);
		anagrAccrFini.setStoricoAccrescFini(null);

		return anagrAccrFini;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public int getSafUteId() {
		return safUteId;
	}

	public void setSafUteId(int safUteId) {
		this.safUteId = safUteId;
	}

}