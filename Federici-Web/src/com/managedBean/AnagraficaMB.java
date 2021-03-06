package com.managedBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.dto.AnagraficaDTO;
import com.dto.PartoDTO;
import com.dto.RazzaDTO;
import com.dto.ValutazioneMaceDTO;
import com.service.FedericiService;

@ManagedBean(name = "anagMB")
@ViewScoped
public class AnagraficaMB extends BaseMB {

	Logger logger = Logger.getLogger(AnagraficaMB.class.getName());

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

	// private List<AnagraficaDTO> anagraficaList;
	private List<AnagraficaDTO> anagraficaFilteredList;
	private AnagraficaDTO nuovoAnimale;
	private AnagraficaDTO animaleEdit;
	private boolean flagToro = false;
	private boolean flagGemello = false;
	private String sesso = "";
	private String anaNumMatricola = "";
	private List<String> valEsistenti;
	private List<String> razzeString;
	private List<RazzaDTO> razzeList;
	private AnagraficaDTOLazyModel lazyModel;
	private String expNome = "Export_Anagrafica_";

	@EJB
	FedericiService federiciService;

	@PostConstruct
	public void init() {

		// anagraficaList = new ArrayList<>();
		nuovoAnimale = new AnagraficaDTO();
		animaleEdit = new AnagraficaDTO();
		razzeList = new ArrayList<>();
		razzeString = new ArrayList<>();
		sesso = "";
		anaNumMatricola = "";

		lazyModel = new AnagraficaDTOLazyModel(userMB, federiciService);

		List<ValutazioneMaceDTO> valutazioneMaceList = federiciService
				.getAllValutazioniMace(userMB.getUtente().getUteRifId());

		razzeList = federiciService.getAllRazze();

		for (RazzaDTO r : razzeList) {
			razzeString.add(r.getRazSigla());
		}

		valEsistenti = new ArrayList<>();
		for (ValutazioneMaceDTO val : valutazioneMaceList) {
			valEsistenti.add(val.getAnagrafica().getAnaNumMatricola());
		}

		expNome = new String(expNome.concat(new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date())));
	}

	public String salvaDatiAnag() {

		if (nuovoAnimale == null) {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "message.fatal.error.login");
			return null;
		} else {
			if (nuovoAnimale.getAnaNumMatricola() == null || nuovoAnimale.getAnaNumMatricola().equals("")) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "numero.matricola.non.inserito");
				sesso = "";
				return null;
			}
			if (nuovoAnimale.getAnaRazza() == null || nuovoAnimale.getAnaRazza().equals("")) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "razza.animale.mancante");
				sesso = "";
				return null;
			}
			// if (flagGemello || nuovoAnimale.getAnaNumMatricola().equals(""))
			// {
			// addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione
			// !", "numero.matricola.non.inserito");
			// sesso = "";
			// return null;
			// }
			if (nuovoAnimale.getAnaSesso() == null || nuovoAnimale.getAnaSesso().equals("")) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "sesso.animale.mancante");
				sesso = "";
				return null;
			}

			if (nuovoAnimale.getAnaDataNascita() == null) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "data.nascita.mancante");
				sesso = "";
				return null;
			}
			if (flagToro && sesso.equals("f")) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "sesso.animale.flag.toro");
				sesso = "";
				return null;
			}

			nuovoAnimale.setAnaFlagToro(flagToro ? 1 : 0);
			nuovoAnimale.setAnaFlagGemello(flagGemello ? "1" : "0");
			nuovoAnimale.setAnaFlagDisponibile("1");
			for (RazzaDTO r : razzeList) {
				if (nuovoAnimale.getAnaRazza().equals(r.getRazSigla()))
					nuovoAnimale.setAnaRazId(r.getRazId());
			}

			// nuovoAnimale.setAnaSesso(sesso);
			nuovoAnimale = federiciService.salvaNuovoAnimale(nuovoAnimale, userMB.getUtente().getUteRifId());
			// if (saved)
			// anagraficaList.add(federiciService.getAnimale(nuovoAnimale.getAnaNumMatricola()));
			RequestContext.getCurrentInstance().update("@all");
			sesso = "";
			flagGemello = false;
			flagToro = false;
//			return saved ? "ok" : "ko";

			if (nuovoAnimale.getAnaId() != 0) {
				// inserisco parto per la madre
				if (nuovoAnimale.getAnaNumMatricolaMadre() != null) {
					AnagraficaDTO madre = federiciService.getAnimale(nuovoAnimale.getAnaNumMatricolaMadre());
					if (madre != null) {
						PartoDTO newParto = new PartoDTO();
						newParto.setParMadreAnaId(madre.getAnaId());
						newParto.setParData(nuovoAnimale.getAnaDataNascita());
						newParto.setParAnaId(nuovoAnimale.getAnaId());
						newParto = federiciService.salvaNuovoParto(newParto);
					}
				}
				return "ok";
			} else
				return "ko";
		}
	}

	public String updateDatiAnag() {
		if (animaleEdit == null) {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "message.fatal.error.login");
			return null;
		} else {
			if (animaleEdit.getAnaNumMatricola() == null || animaleEdit.getAnaNumMatricola().equals("")) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "numero.matricola.non.inserito");
				sesso = "";
				return null;
			}
			// if (flagGemello || nuovoAnimale.getAnaNumMatricola().equals(""))
			// {
			// addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione
			// !", "numero.matricola.non.inserito");
			// sesso = "";
			// return null;
			// }
			if (sesso == null || sesso.equals("")) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "sesso.animale.mancante");
				sesso = "";
				return null;
			}
			if (animaleEdit.getAnaDataNascita() == null) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "data.nascita.mancante");
				sesso = "";
				return null;
			}
			if (flagToro && sesso.equals("F")) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "sesso.animale.flag.toro");
				sesso = "";
				return null;
			}
			if (animaleEdit.getAnaDataUscita() != null) {
				animaleEdit.setAnaFlagDisponibile("0");
			}
			animaleEdit.setAnaFlagToro(flagToro ? 1 : 0);
			animaleEdit.setAnaFlagGemello(flagGemello ? "1" : "0");
			animaleEdit.setAnaSesso(sesso);
			for (RazzaDTO r : razzeList) {
				if (animaleEdit.getAnaRazza().equals(r.getRazSigla()))
					animaleEdit.setAnaRazId(r.getRazId());
			}
			boolean saved = federiciService.updateCambiamentiAnimale(animaleEdit);
			// if (saved) {
			// Iterator<AnagraficaDTO> iter = anagraficaList.iterator();
			// while (iter.hasNext()) {
			// AnagraficaDTO ana = iter.next();
			// if
			// (ana.getAnaNumMatricola().equals(animaleEdit.getAnaNumMatricola()))
			// iter.remove();
			// }
			// anagraficaList.add(federiciService.getAnimale(animaleEdit.getAnaNumMatricola()));
			// }
			RequestContext.getCurrentInstance().update("@all");
			sesso = "";
			flagGemello = false;
			flagToro = false;
			return saved ? "ok" : "ko";
		}
	}

	public void checkRazza() {
		if (nuovoAnimale.getAnaNumMatricolaMadre() != null && !nuovoAnimale.getAnaNumMatricolaMadre().equals("")
				&& nuovoAnimale.getAnaNumMatricolaPadre() != null
				&& !nuovoAnimale.getAnaNumMatricolaPadre().equals("")) {
			String razzaMadre = federiciService.getRazza(nuovoAnimale.getAnaNumMatricolaMadre(),
					userMB.getUtente().getUteRifId());
			String razzaPadre = federiciService.getRazza(nuovoAnimale.getAnaNumMatricolaPadre(),
					userMB.getUtente().getUteRifId());
			if (razzaMadre.toUpperCase().equals(razzaPadre.toUpperCase())) {
				nuovoAnimale.setAnaRazza(razzaMadre);
			}
		}
	}

	public void editAnimale(AnagraficaDTO ana) {
		// lo uso per modificare i valori dell'animale in questione
		animaleEdit = new AnagraficaDTO();

		animaleEdit.setAnaId(ana.getAnaId());
		animaleEdit.setAnaDataAcquisto(ana.getAnaDataAcquisto());
		animaleEdit.setAnaDataNascita(ana.getAnaDataNascita());
		animaleEdit.setAnaDifficoltaParto(ana.getAnaDifficoltaParto());
		animaleEdit.setAnaFlagGemello(ana.getAnaFlagGemello());

		if (new Integer(ana.getAnaFlagGemello()) != null)
			flagGemello = ana.getAnaFlagGemello().equals("1") ? true : false;
		// flagGemello = true;
		animaleEdit.setAnaFlagToro(ana.getAnaFlagToro());

		if (new Integer(ana.getAnaFlagToro()) != null)
			flagToro = ana.getAnaFlagToro() == 1 ? true : false;
		// flagToro=true;
		animaleEdit.setAnaNumMatricola(ana.getAnaNumMatricola());
		animaleEdit.setAnaNumMatricolaMadre(ana.getAnaNumMatricolaMadre());
		animaleEdit.setAnaNumMatricolaPadre(ana.getAnaNumMatricolaPadre());
		animaleEdit.setAnaNumParto(ana.getAnaNumParto());
		animaleEdit.setAnaRazza(ana.getAnaRazza());
		animaleEdit.setAnaSesso(ana.getAnaSesso());
		sesso = ana.getAnaSesso();
		animaleEdit.setAnaUteId(ana.getAnaUteId());
		animaleEdit.setAnaUscitaCausa(ana.getAnaUscitaCausa());
		animaleEdit.setAnaFlagDisponibile(ana.getAnaFlagDisponibile());
	}

	public List<String> cercaSeMadreEsiste(String valParam) {
		List<String> listaStringAnimali = new ArrayList<>();
		List<AnagraficaDTO> listaFemmine = new ArrayList<>();
		listaFemmine = federiciService.getAllFemaleFromUte(valParam, userMB.getUtente().getUteRifId());
		for (AnagraficaDTO ana : listaFemmine) {
			listaStringAnimali.add(ana.getAnaNumMatricola());
		}
		return listaStringAnimali;
	}

	public void showDialogAnag() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgNuovoAnag').show();");

	}

	public UserMB getUserMB() {
		return userMB;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public AnagraficaDTO getNuovoAnimale() {
		return nuovoAnimale;
	}

	public void setNuovoAnimale(AnagraficaDTO nuovoAnimale) {
		this.nuovoAnimale = nuovoAnimale;
	}

	public boolean isFlagToro() {
		return flagToro;
	}

	public void setFlagToro(boolean flagToro) {
		this.flagToro = flagToro;
	}

	public boolean isFlagGemello() {
		return flagGemello;
	}

	public void setFlagGemello(boolean flagGemello) {
		this.flagGemello = flagGemello;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getAnaNumMatricola() {
		return anaNumMatricola;
	}

	public void setAnaNumMatricola(String anaNumMatricola) {
		this.anaNumMatricola = anaNumMatricola;
	}

	public AnagraficaDTO getAnimaleEdit() {
		return animaleEdit;
	}

	public void setAnimaleEdit(AnagraficaDTO animaleEdit) {
		this.animaleEdit = animaleEdit;
	}

	public List<String> getValEsistenti() {
		return valEsistenti;
	}

	public void setValEsistenti(List<String> valEsistenti) {
		this.valEsistenti = valEsistenti;
	}

	public List<RazzaDTO> getRazzeList() {
		return razzeList;
	}

	public void setRazzeList(List<RazzaDTO> razzeList) {
		this.razzeList = razzeList;
	}

	public List<String> getRazzeString() {
		return razzeString;
	}

	public void setRazzeString(List<String> razzeString) {
		this.razzeString = razzeString;
	}

	public List<AnagraficaDTO> getAnagraficaFilteredList() {
		return anagraficaFilteredList;
	}

	public void setAnagraficaFilteredList(List<AnagraficaDTO> anagraficaFilteredList) {
		this.anagraficaFilteredList = anagraficaFilteredList;
	}

	public AnagraficaDTOLazyModel getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(AnagraficaDTOLazyModel lazyModel) {
		this.lazyModel = lazyModel;
	}

	public String getExpNome() {
		return expNome;
	}

	public void setExpNome(String expNome) {
		this.expNome = expNome;
	}

}
