package com.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.dto.AnagraficaDTO;
import com.dto.ValutazioneMaceDTO;
import com.service.FedericiService;

@ManagedBean(name = "pcommMB")
@ViewScoped
public class PesataCommercialeMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

	private List<ValutazioneMaceDTO> valutazioneMaceList;
	private ValutazioneMaceDTO nuovaValutazione;
	private List<AnagraficaDTO> animaliUsciti;
	private List<String> animaliUscitiMatricola;
	private String matricolaAnimale;

	// private List<AnagraficaDTO> animaliDisponibiliList;
	// private List<AnagraficaDTO> animaliNONDisponibiliList;
	// private List<AnagraficaDTO> animaliSelezionateTarget;
	// private StoricoGruppiMontaDTO storicoGruppoMontaAppoggio;
	// private List<AnagraficaDTO> animaliDisponibiliListAppoggio;
	// private StoricoGruppiMontaDTO storicoGruppoMontaAggiuntaAnimali;
	// private DualListModel<String> animaliDisponibili;
	// private List<String> animaliSource;
	// private List<String> animaliTarget;
	// private DualListModel<String> animaliDisponibiliPerAggiunta;
	// private List<String> animaliSourcePerAggiunta;
	// private List<String> animaliTargetPerAggiunta;

	@EJB
	FedericiService federiciService;

	@PostConstruct
	public void init() {

		nuovaValutazione = new ValutazioneMaceDTO();
		valutazioneMaceList = new ArrayList<>();
		animaliUsciti = new ArrayList<>();
		animaliUscitiMatricola = new ArrayList<>();
		matricolaAnimale = "";

		valutazioneMaceList = federiciService.getAllValutazioniMace(userMB.getUtente().getUteRifId());

		animaliUsciti = federiciService.getAllAnimaliUsciti(userMB.getUtente().getUteRifId());

		List<String> temp = new ArrayList<>();
		for (ValutazioneMaceDTO valutazione : valutazioneMaceList) {
			temp.add(valutazione.getAnagrafica().getAnaNumMatricola());
		}

		if (animaliUsciti != null && animaliUsciti.size() > 0) {
			for (AnagraficaDTO ana : animaliUsciti) {
				if (!temp.contains(ana.getAnaNumMatricola()))
					animaliUscitiMatricola.add(ana.getAnaNumMatricola());
			}
		}
	}

	public void showDialogNuovaVal() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgNuovaVal').show();");
	}

	public void getAnagraficaFromMatricola() {
		for (AnagraficaDTO ana : animaliUsciti) {
			if (ana.getAnaNumMatricola().equals(matricolaAnimale)) {
				nuovaValutazione.setAnagrafica(ana);
			}
		}
	}

	public String salvaDatiValutazione() {

		if (nuovaValutazione == null) {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "message.fatal.error.login");
			System.out.println("nuova valutazione");
			return null;
		} else {
			if (nuovaValutazione.getAnagrafica() == null) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "numero.matricola.non.inserito");
				return null;
			}
			if (nuovaValutazione.getVmaPesoMacellazione() == 0) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "sesso.animale.mancante");
				return null;
			}
			if (nuovaValutazione.getVmaPesoCarcassa() == 0) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "data.nascita.mancante");
				return null;
			}
			if (nuovaValutazione.getVmaPesoTaglioTot() == 0) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "sesso.animale.flag.toro");
				return null;
			}

			nuovaValutazione.setVmaUteId(userMB.getUtente().getUteRifId());
			boolean saved = federiciService.salvaNuovaValutazione(nuovaValutazione);
			if (saved)
				valutazioneMaceList.add(nuovaValutazione);
			RequestContext.getCurrentInstance().update("@all");
			nuovaValutazione = new ValutazioneMaceDTO();
			return saved ? "ok" : "ko";

		}

	}
	// public void modificaGruppoDiMonta() {
	// animaliTargetPerAggiunta = animaliDisponibiliPerAggiunta.getTarget();
	// List<String> eliminare = new ArrayList<>();
	// boolean toro = false;
	// if (storicoGruppoMontaAppoggio.getGruppoMontas() != null) {
	// for (GruppoMontaDTO gm : storicoGruppoMontaAppoggio.getGruppoMontas()) {
	// if (gm.getAnagrafica().getAnaFlagToro() == 1)
	// toro = true;
	// }
	// }
	// for (AnagraficaDTO ana : animaliDisponibiliList) {
	// GruppoMontaDTO gmDTO;
	// for (String animali : animaliTargetPerAggiunta) {
	// boolean saved;
	// if (animali.contains("*"))
	// animali = animali.replace(" *", "");
	// if (ana.getAnaNumMatricola().equals(animali)) {
	// if (ana.getAnaFlagToro() == 1 && toro) {
	// addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !",
	// "toro.gia.selezionato");
	// return;
	// } else if (ana.getAnaFlagToro() == 1 && !toro) {
	// toro = true;
	// }
	// gmDTO = new GruppoMontaDTO();
	// gmDTO.setAnagrafica(ana);
	// gmDTO.setGmoAnaId(ana.getAnaId());
	// gmDTO.setGmoDataInserimento(Calendar.getInstance(Locale.ITALY).getTime());
	// gmDTO.setGmoSgmId(storicoGruppoMontaAppoggio.getSgmId());
	// storicoGruppoMontaAppoggio.getGruppoMontas().add(gmDTO);
	// saved =
	// federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio,
	// storicoGruppoMontaAppoggio.getGruppoMontas().indexOf(gmDTO));
	// if (gmDTO.getAnagrafica().getAnaFlagToro() == 1)
	// eliminare.add(gmDTO.getAnagrafica().getAnaNumMatricola() + " *");
	// else
	// eliminare.add(gmDTO.getAnagrafica().getAnaNumMatricola());
	//
	// }
	// }
	//
	// }
	// //
	// federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAggiuntaAnimali);
	// for (String eli : eliminare) {
	// animaliTargetPerAggiunta.remove(eli);
	// animaliDisponibili.getSource().remove(eli);
	// }
	// System.out.println("finito ");
	// }
	//
	// public void showDialogAddAnimali(StoricoGruppiMontaDTO sgm) {
	// storicoGruppoMontaAppoggio = sgm;
	// animaliSourcePerAggiunta = animaliDisponibili.getSource();
	// animaliTargetPerAggiunta = animaliDisponibili.getTarget();
	// animaliDisponibiliPerAggiunta = new
	// DualListModel<String>(animaliSourcePerAggiunta,
	// animaliTargetPerAggiunta);
	// RequestContext context = RequestContext.getCurrentInstance();
	// context.execute("PF('dlg2').show();");
	// }
	//
	// public String modificaAnimaleInGruppoDiMonta(StoricoGruppiMontaDTO sgm,
	// int gmId) {
	// boolean saved = false;
	// storicoGruppoMontaAppoggio = new StoricoGruppiMontaDTO();
	// storicoGruppoMontaAppoggio = sgm;
	// Iterator<GruppoMontaDTO> iter = sgm.getGruppoMontas().iterator();
	// while (iter.hasNext()) {
	// GruppoMontaDTO gm = iter.next();
	// if (gm.getGmoId() == gmId) {
	// if (gm.getAnagrafica().getAnaFlagToro() == 1 &&
	// gm.getAnagrafica().getAnaSesso().equals("m")) {
	// // eliminazione toro, quindi chiusura gruppo con dialog
	// RequestContext context = RequestContext.getCurrentInstance();
	// context.execute("PF('dlgEliminaToro').show();");
	// RequestContext.getCurrentInstance().update("");
	// return "toro";
	// } else {
	// gm.setGmoDataUscita(Calendar.getInstance(Locale.ITALY).getTime());
	// saved = federiciService.updateStoricoGruppoMonta(sgm,
	// sgm.getGruppoMontas().indexOf(gm));
	// if (saved) {
	// if (gm.getAnagrafica().getAnaFlagToro() == 1)
	// animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola()
	// + " *");
	// else
	// animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola());
	// iter.remove();
	// }
	// }
	// }
	// }
	// // federiciService.updateStoricoGruppoMonta(sgm);
	// return "ok";
	// }
	//
	// public void chiusuraGruppoMonta() {
	// if (storicoGruppoMontaAppoggio.getGruppoMontas() != null
	// && storicoGruppoMontaAppoggio.getGruppoMontas().size() > 0) {
	// for (GruppoMontaDTO gm : storicoGruppoMontaAppoggio.getGruppoMontas()) {
	// gm.setGmoDataUscita(Calendar.getInstance(Locale.ITALY).getTime());
	// gm.setGmoSgmId(storicoGruppoMontaAppoggio.getSgmId());
	// federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio,
	// storicoGruppoMontaAppoggio.getGruppoMontas().indexOf(gm));
	// if (gm.getAnagrafica().getAnaFlagToro() == 1)
	// animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola()
	// + " *");
	// else
	// animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola());
	// }
	// storicoGruppoMontaAppoggio.setSgmDataChiusura(Calendar.getInstance(Locale.ITALY).getTime());
	// storicoGruppoMontaAppoggio.setGruppoMontas(new
	// ArrayList<GruppoMontaDTO>());
	// federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio);
	// }
	// }
	//
	// public void chiusuraGruppoMonta(StoricoGruppiMontaDTO sgma) {
	// storicoGruppoMontaAppoggio = sgma;
	// if (storicoGruppoMontaAppoggio.getGruppoMontas() != null
	// && storicoGruppoMontaAppoggio.getGruppoMontas().size() > 0) {
	// for (GruppoMontaDTO gm : storicoGruppoMontaAppoggio.getGruppoMontas()) {
	// gm.setGmoDataUscita(Calendar.getInstance(Locale.ITALY).getTime());
	// gm.setGmoSgmId(storicoGruppoMontaAppoggio.getSgmId());
	// federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio,
	// storicoGruppoMontaAppoggio.getGruppoMontas().indexOf(gm));
	// if (gm.getAnagrafica().getAnaFlagToro() == 1)
	// animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola()
	// + " *");
	// else
	// animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola());
	// }
	// storicoGruppoMontaAppoggio.setSgmDataChiusura(Calendar.getInstance(Locale.ITALY).getTime());
	// storicoGruppoMontaAppoggio.setGruppoMontas(new
	// ArrayList<GruppoMontaDTO>());
	// federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio);
	// }
	// }
	//
	// public String salvaGruppoDiMonta() {
	// Date date = Calendar.getInstance(Locale.ITALY).getTime();
	// boolean saved = false;
	//
	// if (nuovoStoricoGruppoMonta != null) {
	// if (nuovoStoricoGruppoMonta.getSgmDataApertura().before(date)
	// || nuovoStoricoGruppoMonta.getSgmDataApertura().equals(date)) {
	// List<String> animaliTargetTemp = animaliDisponibili.getTarget();
	// System.out.println("animali selected " + animaliTargetTemp.size());
	// System.out.println("animali selected " + animaliDisponibili.getTarget());
	// if (animaliTargetTemp != null && animaliTargetTemp.size() > 0) {
	// List<GruppoMontaDTO> nuoviGruppiMontaDTOList = new ArrayList<>();
	// boolean toro = false;
	// for (AnagraficaDTO ana : animaliDisponibiliList) {
	// GruppoMontaDTO gmDTO;
	// for (String animali : animaliTargetTemp) {
	// if (animali.contains("*"))
	// animali = animali.replace(" *", "");
	// if (ana.getAnaNumMatricola().equals(animali)) {
	// gmDTO = new GruppoMontaDTO();
	// gmDTO.setAnagrafica(ana);
	// gmDTO.setGmoAnaId(ana.getAnaId());
	// gmDTO.setGmoDataInserimento(nuovoStoricoGruppoMonta.getSgmDataApertura());
	// // gmDTO.setGmoSgmId(gmoSgmId);
	// nuoviGruppiMontaDTOList.add(gmDTO);
	// if (ana.getAnaFlagToro() == 1)
	// toro = true;
	// }
	// }
	// }
	// if (!toro) {
	// addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !",
	// "nessun.toro.selezionato");
	// return null;
	// }
	// nuovoStoricoGruppoMonta.setGruppoMontas(nuoviGruppiMontaDTOList);
	// nuovoStoricoGruppoMonta.setSgmUteId(userMB.getUtente().getUteId());
	// saved = federiciService.saveNuovoGruppoMonta(nuovoStoricoGruppoMonta);
	// } else {
	// addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !",
	// "nessun.animale.selezionato");
	// return null;
	// }
	// } else {
	// addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !",
	// "data.sbagliata");
	// return null;
	// }
	// } else {
	// addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !",
	// "storico.gruppo.monta.sbagliato");
	// return null;
	// }
	// if (saved)
	// return "ok";
	// else
	// return "ko";
	// }

	public List<ValutazioneMaceDTO> getValutazioneMaceList() {
		return valutazioneMaceList;
	}

	public void setValutazioneMaceList(List<ValutazioneMaceDTO> valutazioneMaceList) {
		this.valutazioneMaceList = valutazioneMaceList;
	}

	public UserMB getUserMB() {
		return userMB;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public ValutazioneMaceDTO getNuovaValutazione() {
		return nuovaValutazione;
	}

	public void setNuovaValutazione(ValutazioneMaceDTO nuovaValutazione) {
		this.nuovaValutazione = nuovaValutazione;
	}

	public List<AnagraficaDTO> getAnimaliUsciti() {
		return animaliUsciti;
	}

	public void setAnimaliUsciti(List<AnagraficaDTO> animaliUsciti) {
		this.animaliUsciti = animaliUsciti;
	}

	public List<String> getAnimaliUscitiMatricola() {
		return animaliUscitiMatricola;
	}

	public void setAnimaliUscitiMatricola(List<String> animaliUscitiMatricola) {
		this.animaliUscitiMatricola = animaliUscitiMatricola;
	}

	public String getMatricolaAnimale() {
		return matricolaAnimale;
	}

	public void setMatricolaAnimale(String matricolaAnimale) {
		this.matricolaAnimale = matricolaAnimale;
	}

}
