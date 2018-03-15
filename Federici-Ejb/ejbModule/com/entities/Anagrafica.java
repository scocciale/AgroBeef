package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the anagrafica database table.
 * 
 */
@Entity
@NamedQuery(name = "Anagrafica.findAll", query = "SELECT a FROM Anagrafica a")
public class Anagrafica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ana_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int anaId;

	@Temporal(TemporalType.DATE)
	@Column(name = "ana_data_acquisto")
	private Date anaDataAcquisto;

	@Temporal(TemporalType.DATE)
	@Column(name = "ana_data_nascita")
	private Date anaDataNascita;

	@Column(name = "ana_difficolta_parto")
	private String anaDifficoltaParto;

	@Column(name = "ana_flag_gemello")
	private String anaFlagGemello;
	
	@Column(name = "ana_flag_disponibile")
	private String anaFlagDisponibile;

	@Column(name = "ana_flag_toro")
	private int anaFlagToro;

	@Column(name = "ana_num_matricola")
	private String anaNumMatricola;

	@Column(name = "ana_num_matricola_madre")
	private String anaNumMatricolaMadre;

	@Column(name = "ana_num_matricola_padre")
	private String anaNumMatricolaPadre;

	@Column(name = "ana_num_parto")
	private int anaNumParto;

	@Column(name = "ana_razza")
	private String anaRazza;

	@Column(name = "ana_sesso")
	private String anaSesso;

	@Column(name = "ana_ute_id")
	private int anaUteId;

	@Temporal(TemporalType.DATE)
	@Column(name = "ana_data_uscita")
	private Date anaDataUscita;

	@Column(name = "ana_uscita_causa")
	private String anaUscitaCausa;

	@Column(name = "ana_raz_id")
	private int anaRazId;

	// bi-directional many-to-one association to Razza
	@ManyToOne
	@JoinColumn(name = "ana_raz_id", insertable = false, updatable = false)
	private Razza razza;

	// bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name = "ana_ute_id", insertable = false, updatable = false)
	private Utente utente;

	// bi-directional many-to-one association to AnagrAccrFini
	@OneToMany(mappedBy = "anagrafica")
	private List<AnagrAccrFini> anagrAccrFinis;

	// bi-directional many-to-one association to GruppoMonta
	@OneToMany(mappedBy = "anagrafica")
	private List<GruppoMonta> gruppoMontas;

	// bi-directional many-to-one association to Pesata
	@OneToMany(mappedBy = "anagrafica")
	private List<Pesata> pesatas;

	// bi-directional many-to-one association to ValutazioneMace
	@OneToMany(mappedBy = "anagrafica")
	private List<ValutazioneMace> valutazioneMaces;

	// bi-directional many-to-one association to Veterinaria
	@OneToMany(mappedBy = "anagrafica")
	private List<Veterinaria> veterinarias;

	public Anagrafica() {
	}

	public int getAnaId() {
		return this.anaId;
	}

	public void setAnaId(int anaId) {
		this.anaId = anaId;
	}

	public Date getAnaDataAcquisto() {
		return anaDataAcquisto;
	}

	public void setAnaDataAcquisto(Date anaDataAcquisto) {
		this.anaDataAcquisto = anaDataAcquisto;
	}

	public Date getAnaDataNascita() {
		return this.anaDataNascita;
	}

	public void setAnaDataNascita(Date anaDataNascita) {
		this.anaDataNascita = anaDataNascita;
	}

	public String getAnaDifficoltaParto() {
		return this.anaDifficoltaParto;
	}

	public void setAnaDifficoltaParto(String anaDifficoltaParto) {
		this.anaDifficoltaParto = anaDifficoltaParto;
	}

	public String getAnaFlagGemello() {
		return this.anaFlagGemello;
	}

	public void setAnaFlagGemello(String anaFlagGemello) {
		this.anaFlagGemello = anaFlagGemello;
	}

	public int getAnaFlagToro() {
		return this.anaFlagToro;
	}

	public void setAnaFlagToro(int anaFlagToro) {
		this.anaFlagToro = anaFlagToro;
	}

	public String getAnaNumMatricola() {
		return this.anaNumMatricola;
	}

	public void setAnaNumMatricola(String anaNumMatricola) {
		this.anaNumMatricola = anaNumMatricola;
	}

	public String getAnaNumMatricolaMadre() {
		return this.anaNumMatricolaMadre;
	}

	public void setAnaNumMatricolaMadre(String anaNumMatricolaMadre) {
		this.anaNumMatricolaMadre = anaNumMatricolaMadre;
	}

	public String getAnaNumMatricolaPadre() {
		return this.anaNumMatricolaPadre;
	}

	public void setAnaNumMatricolaPadre(String anaNumMatricolaPadre) {
		this.anaNumMatricolaPadre = anaNumMatricolaPadre;
	}

	public int getAnaNumParto() {
		return this.anaNumParto;
	}

	public void setAnaNumParto(int anaNumParto) {
		this.anaNumParto = anaNumParto;
	}

	public String getAnaRazza() {
		return this.anaRazza;
	}

	public void setAnaRazza(String anaRazza) {
		this.anaRazza = anaRazza;
	}

	public String getAnaSesso() {
		return this.anaSesso;
	}

	public void setAnaSesso(String anaSesso) {
		this.anaSesso = anaSesso;
	}

	public List<AnagrAccrFini> getAnagrAccrFinis() {
		return this.anagrAccrFinis;
	}

	public void setAnagrAccrFinis(List<AnagrAccrFini> anagrAccrFinis) {
		this.anagrAccrFinis = anagrAccrFinis;
	}

	public AnagrAccrFini addAnagrAccrFini(AnagrAccrFini anagrAccrFini) {
		getAnagrAccrFinis().add(anagrAccrFini);
		anagrAccrFini.setAnagrafica(this);

		return anagrAccrFini;
	}

	public AnagrAccrFini removeAnagrAccrFini(AnagrAccrFini anagrAccrFini) {
		getAnagrAccrFinis().remove(anagrAccrFini);
		anagrAccrFini.setAnagrafica(null);

		return anagrAccrFini;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<GruppoMonta> getGruppoMontas() {
		return this.gruppoMontas;
	}

	public void setGruppoMontas(List<GruppoMonta> gruppoMontas) {
		this.gruppoMontas = gruppoMontas;
	}

	public GruppoMonta addGruppoMonta(GruppoMonta gruppoMonta) {
		getGruppoMontas().add(gruppoMonta);
		gruppoMonta.setAnagrafica(this);

		return gruppoMonta;
	}

	public GruppoMonta removeGruppoMonta(GruppoMonta gruppoMonta) {
		getGruppoMontas().remove(gruppoMonta);
		gruppoMonta.setAnagrafica(null);

		return gruppoMonta;
	}

	public List<Pesata> getPesatas() {
		return this.pesatas;
	}

	public void setPesatas(List<Pesata> pesatas) {
		this.pesatas = pesatas;
	}

	public Pesata addPesata(Pesata pesata) {
		getPesatas().add(pesata);
		pesata.setAnagrafica(this);

		return pesata;
	}

	public Pesata removePesata(Pesata pesata) {
		getPesatas().remove(pesata);
		pesata.setAnagrafica(null);

		return pesata;
	}

	public List<ValutazioneMace> getValutazioneMaces() {
		return this.valutazioneMaces;
	}

	public void setValutazioneMaces(List<ValutazioneMace> valutazioneMaces) {
		this.valutazioneMaces = valutazioneMaces;
	}

	public ValutazioneMace addValutazioneMace(ValutazioneMace valutazioneMace) {
		getValutazioneMaces().add(valutazioneMace);
		valutazioneMace.setAnagrafica(this);

		return valutazioneMace;
	}

	public ValutazioneMace removeValutazioneMace(ValutazioneMace valutazioneMace) {
		getValutazioneMaces().remove(valutazioneMace);
		valutazioneMace.setAnagrafica(null);

		return valutazioneMace;
	}

	public List<Veterinaria> getVeterinarias() {
		return this.veterinarias;
	}

	public void setVeterinarias(List<Veterinaria> veterinarias) {
		this.veterinarias = veterinarias;
	}

	public Veterinaria addVeterinaria(Veterinaria veterinaria) {
		getVeterinarias().add(veterinaria);
		veterinaria.setAnagrafica(this);

		return veterinaria;
	}

	public Veterinaria removeVeterinaria(Veterinaria veterinaria) {
		getVeterinarias().remove(veterinaria);
		veterinaria.setAnagrafica(null);

		return veterinaria;
	}

	public int getAnaUteId() {
		return anaUteId;
	}

	public void setAnaUteId(int anaUteId) {
		this.anaUteId = anaUteId;
	}

	public Date getAnaDataUscita() {
		return anaDataUscita;
	}

	public void setAnaDataUscita(Date anaDataUscita) {
		this.anaDataUscita = anaDataUscita;
	}

	public String getAnaUscitaCausa() {
		return anaUscitaCausa;
	}

	public void setAnaUscitaCausa(String anaUscitaCausa) {
		this.anaUscitaCausa = anaUscitaCausa;
	}

	public int getAnaRazId() {
		return anaRazId;
	}

	public void setAnaRazId(int anaRazId) {
		this.anaRazId = anaRazId;
	}

	public Razza getRazza() {
		return razza;
	}

	public void setRazza(Razza razza) {
		this.razza = razza;
	}

	public String getAnaFlagDisponibile() {
		return anaFlagDisponibile;
	}

	public void setAnaFlagDisponibile(String anaFlagDisponibile) {
		this.anaFlagDisponibile = anaFlagDisponibile;
	}

}