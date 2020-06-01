package com.managedBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.dto.AnagraficaDTO;
import com.dto.GruppoMontaDTO;
import com.dto.StoricoGruppiMontaDTO;
import com.service.FedericiService;

@ManagedBean(name = "gdmMB")
@ViewScoped
public class GruppiDiMontaMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

	private List<StoricoGruppiMontaDTO> gruppiDiMontaList;
	private StoricoGruppiMontaDTO nuovoStoricoGruppoMonta;
	private List<AnagraficaDTO> animaliDisponibiliList;
	private List<AnagraficaDTO> animaliSelezionateTarget;
	private StoricoGruppiMontaDTO storicoGruppoMontaAppoggio;

	private List<AnagraficaDTO> animaliDisponibiliFinal;
	private List<AnagraficaDTO> toriDisponibiliFinal;
	private List<String> toriDisponibiliFinalString;
	private Set<AnagraficaDTO> animaliAggiuntiView;
	private String animaliAggiunti;
	private String valueAna;
	private String valueAnaToro;
	private Set<AnagraficaDTO> animaliEditAggiuntiView;
	private String animaleRemover;
	private String animaleEditRemover;

	private boolean toroAdded;

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

		gruppiDiMontaList = new ArrayList<>();
		toriDisponibiliFinal = new ArrayList<>();
		nuovoStoricoGruppoMonta = new StoricoGruppiMontaDTO();
		storicoGruppoMontaAppoggio = new StoricoGruppiMontaDTO();
		animaleRemover = new String();
		animaleEditRemover = new String();

		// animali che sono disponibili per i gruppi di monta
		animaliDisponibiliList = new ArrayList<>();

		animaliDisponibiliFinal = new ArrayList<>();
		animaliAggiuntiView = new LinkedHashSet<>();
		animaliAggiunti = "";
		valueAna = "";
		valueAnaToro = "";
		animaliEditAggiuntiView = new LinkedHashSet<>();
		toroAdded = false;

		gruppiDiMontaList = federiciService.getAllGruppiDiMontaOpen(userMB.getUtente().getUteRifId());
		toriDisponibiliFinal = federiciService.getAllToriDisponibili(userMB.getUtente().getUteRifId());

		toriDisponibiliFinalString = new ArrayList<>();
		for (AnagraficaDTO toro : toriDisponibiliFinal) {
			toriDisponibiliFinalString.add(toro.getAnaNumMatricola());
		}

	}

	public List<String> findFromParms(String valParam) {
		List<String> listaStringAnimali = new ArrayList<>();
		animaliDisponibiliFinal = federiciService.getAllMatricoleDisponibiliFemale(userMB.getUtente().getUteRifId(),
				valParam);
		for (AnagraficaDTO anagraficaDTO : animaliDisponibiliFinal) {
			listaStringAnimali
					.add((anagraficaDTO.getAnaFlagToro() == 1) ? anagraficaDTO.getAnaNumMatricola().concat(" *")
							: anagraficaDTO.getAnaNumMatricola());
		}

		return listaStringAnimali;
	}

	public void addToEditGruppoDiMonta() {
//		int quantitaAnimali = animaliEditAggiuntiView.size();
		boolean added = false;
		if (valueAna.contains(" *"))
			valueAna = valueAna.replace(" *", "");
		for (AnagraficaDTO a : animaliDisponibiliFinal) {
			if (a.getAnaNumMatricola().equals(valueAna) && !animaliAggiunti.contains(valueAna)) {
				animaliEditAggiuntiView.add(a);
				added = true;
				animaliAggiunti = animaliAggiunti.concat(valueAna);
			}
		}
//		if (animaliEditAggiuntiView.size() == quantitaAnimali)
		if (added)
			showDialogFromName("dlgAnimaleAddedYet");
	}

	public void addToGruppoDiMonta() {
		for (AnagraficaDTO a : animaliDisponibiliFinal) {
			if (a.getAnaNumMatricola().equals(valueAna) && !animaliAggiunti.contains(valueAna)) {
				if (animaliAggiuntiView.contains(a)) {
					showDialogFromName("dlgAnimaleAddedYet");
					System.out.println("sasasasasasasass");
					return;
				} else {
					animaliAggiuntiView.add(a);
					animaliAggiunti = animaliAggiunti.concat(valueAna);
				}
			}
		}

	}

	public void addToroToGruppoDiMonta() {
		for (AnagraficaDTO a : toriDisponibiliFinal) {
			if (a.getAnaNumMatricola().equals(valueAnaToro) && !animaliAggiunti.contains(valueAnaToro)) {
				animaliAggiuntiView.add(a);
				animaliAggiunti = animaliAggiunti.concat(valueAnaToro);

				setToroAdded(true);

			}
		}
		valueAnaToro= "";
	}

	public void removeAnagraficaFromSelectedEditList() {

		if (animaliEditAggiuntiView.isEmpty()) {
			// errore lista vuota
		} else {
			if (animaleEditRemover == null) {
				// nessun animale selzionato
			} else {
				for (AnagraficaDTO a : animaliEditAggiuntiView) {
					if (a.getAnaNumMatricola().equals(animaleEditRemover)) {
						animaliEditAggiuntiView.remove(a);
						animaliAggiunti = animaliAggiunti.replace(animaleEditRemover, "");
					}
				}
			}
		}
	}

	public void removeAnagraficaFromSelectedList() {

		if (animaliAggiuntiView.isEmpty()) {
			// errore lista vuota
		} else {
			if (animaleRemover == null) {
				// nessun animale selzionato
			} else {
				for (AnagraficaDTO a : animaliAggiuntiView) {
					if (a.getAnaNumMatricola().equals(animaleRemover)) {
						animaliAggiuntiView.remove(a);
						animaliAggiunti = animaliAggiunti.replace(animaleRemover, "");
					}
				}
			}
		}
	}

	public void showDialogFromName(String name) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('" + name + "').show();");
	}

	public void modificaGruppoDiMonta() {
		boolean saved = false;
		boolean toro = false;
		if (storicoGruppoMontaAppoggio.getGruppoMontas() != null) {
			for (GruppoMontaDTO gm : storicoGruppoMontaAppoggio.getGruppoMontas()) {
				if (gm.getAnagrafica().getAnaFlagToro() == 1)
					toro = true;
			}
		}
		if (animaliEditAggiuntiView != null && animaliEditAggiuntiView.size() > 0) {
			for (AnagraficaDTO ana : animaliEditAggiuntiView) {
				GruppoMontaDTO gmDTO;
				// è giusto che puo esistere solo un toro in un gruppo di monta
				// ???
				if (ana.getAnaFlagToro() == 1 && toro) {
					addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "toro.gia.selezionato");
					return;
				} else if (ana.getAnaFlagToro() == 1 && !toro) {
					toro = true;
				}
				gmDTO = new GruppoMontaDTO();
				gmDTO.setAnagrafica(ana);
				gmDTO.setGmoAnaId(ana.getAnaId());
				gmDTO.setGmoDataInserimento(Calendar.getInstance(Locale.ITALY).getTime());
				gmDTO.setGmoSgmId(storicoGruppoMontaAppoggio.getSgmId());
				gmDTO.getAnagrafica().setAnaFlagDisponibile("0");
				storicoGruppoMontaAppoggio.getGruppoMontas().add(gmDTO);
				saved = federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio,
						storicoGruppoMontaAppoggio.getGruppoMontas().indexOf(gmDTO));
			}
		} else {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "nessun.animale.selezionato");
		}
		if (saved) {
			animaliEditAggiuntiView.clear();
			valueAna = "";
		}
		animaleEditRemover = new String();
		animaliEditAggiuntiView = new LinkedHashSet<>();
		animaliAggiunti = new String();
		System.out.println("Aggiunta anagrafica a gruppo di monta ? " + saved);
	}

	public void showDialogAddAnimali(StoricoGruppiMontaDTO sgm) {
		storicoGruppoMontaAppoggio = sgm;
		showDialogFromName("dlg2");
	}

	public String modificaAnimaleInGruppoDiMonta(StoricoGruppiMontaDTO sgm, int gmId) {
		boolean saved = false;
		storicoGruppoMontaAppoggio = new StoricoGruppiMontaDTO();
		storicoGruppoMontaAppoggio = sgm;
		Iterator<GruppoMontaDTO> iter = sgm.getGruppoMontas().iterator();
		while (iter.hasNext()) {
			GruppoMontaDTO gm = iter.next();
			if (gm.getGmoId() == gmId) {
				if (gm.getAnagrafica().getAnaFlagToro() == 1
						&& gm.getAnagrafica().getAnaSesso().equalsIgnoreCase("M")) {
					// eliminazione toro, quindi chiusura gruppo con dialog
					showDialogFromName("dlgEliminaToro");
					RequestContext.getCurrentInstance().update("");
					return "toro";
				} else {
					gm.setGmoDataUscita(Calendar.getInstance(Locale.ITALY).getTime());
					gm.getAnagrafica().setAnaFlagDisponibile("1");
					saved = federiciService.updateStoricoGruppoMonta(sgm, sgm.getGruppoMontas().indexOf(gm));
					if (saved) {
						iter.remove();
					}
				}
			}
		}
		return "ok";
	}

	public void chiusuraGruppoMonta() {
		if (storicoGruppoMontaAppoggio.getGruppoMontas() != null
				&& storicoGruppoMontaAppoggio.getGruppoMontas().size() > 0) {
			for (GruppoMontaDTO gm : storicoGruppoMontaAppoggio.getGruppoMontas()) {
				gm.setGmoDataUscita(Calendar.getInstance(Locale.ITALY).getTime());
				gm.getAnagrafica().setAnaFlagDisponibile("1");
				gm.setGmoSgmId(storicoGruppoMontaAppoggio.getSgmId());
				federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio,
						storicoGruppoMontaAppoggio.getGruppoMontas().indexOf(gm));
			}
			storicoGruppoMontaAppoggio.setSgmDataChiusura(Calendar.getInstance(Locale.ITALY).getTime());
			storicoGruppoMontaAppoggio.setGruppoMontas(new ArrayList<GruppoMontaDTO>());
			federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio);
		}
	}

	public void chiusuraGruppoMonta(StoricoGruppiMontaDTO sgma) {
		storicoGruppoMontaAppoggio = sgma;
		if (storicoGruppoMontaAppoggio.getGruppoMontas() != null
				&& storicoGruppoMontaAppoggio.getGruppoMontas().size() > 0) {
			for (GruppoMontaDTO gm : storicoGruppoMontaAppoggio.getGruppoMontas()) {
				gm.setGmoDataUscita(Calendar.getInstance(Locale.ITALY).getTime());
				gm.getAnagrafica().setAnaFlagDisponibile("1");
				gm.setGmoSgmId(storicoGruppoMontaAppoggio.getSgmId());
				federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio,
						storicoGruppoMontaAppoggio.getGruppoMontas().indexOf(gm));
			}
			storicoGruppoMontaAppoggio.setSgmDataChiusura(Calendar.getInstance(Locale.ITALY).getTime());
			storicoGruppoMontaAppoggio.setGruppoMontas(new ArrayList<GruppoMontaDTO>());
			federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio);
		}
	}

	public String salvaGruppoDiMonta() {
		Date date = Calendar.getInstance(Locale.ITALY).getTime();
		boolean saved = false;
		List<GruppoMontaDTO> nuoviGruppiMontaDTOList = new ArrayList<>();
		boolean toro = false;

		if (nuovoStoricoGruppoMonta != null && (nuovoStoricoGruppoMonta.getSgmNome() != null
				&& !nuovoStoricoGruppoMonta.getSgmNome().trim().equals(""))) {

			// è giusto che non è possibile creare gruppi in date future ???
			if (nuovoStoricoGruppoMonta.getSgmDataApertura() != null
					&& (nuovoStoricoGruppoMonta.getSgmDataApertura().before(date)
							|| nuovoStoricoGruppoMonta.getSgmDataApertura().equals(date))) {
				if (animaliAggiuntiView != null && animaliAggiuntiView.size() > 0) {
					for (AnagraficaDTO ana : animaliAggiuntiView) {
						GruppoMontaDTO gmDTO;
						gmDTO = new GruppoMontaDTO();
						gmDTO.setAnagrafica(ana);
						gmDTO.setGmoAnaId(ana.getAnaId());
						gmDTO.getAnagrafica().setAnaFlagDisponibile("0");
						gmDTO.setGmoDataInserimento(nuovoStoricoGruppoMonta.getSgmDataApertura());
						// gmDTO.setGmoSgmId(gmoSgmId);
						nuoviGruppiMontaDTOList.add(gmDTO);
						if (ana.getAnaFlagToro() == 1)
							toro = true;
					}
					if (!toro) {
						addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "nessun.toro.selezionato");
						return null;
					}
					nuovoStoricoGruppoMonta.setGruppoMontas(nuoviGruppiMontaDTOList);
					nuovoStoricoGruppoMonta.setSgmUteId(userMB.getUtente().getUteRifId());
					saved = federiciService.saveNuovoGruppoMonta(nuovoStoricoGruppoMonta);
				} else {
					addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "nessun.animale.selezionato");
					return null;
				}
			} else {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "data.sbagliata");
				return null;
			}
		} else {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "storico.gruppo.monta.sbagliato");
			return null;
		}

		if (saved) {
			gruppiDiMontaList.add(nuovoStoricoGruppoMonta);
			valueAna = "";
			animaliAggiunti = new String();
			animaliAggiuntiView = new LinkedHashSet<>();
			animaleRemover = new String();
			init();
			return "ok";
		} else
			return "ko";
	}

	public boolean cambiaNomeGdm(StoricoGruppiMontaDTO sgmDTO, String newName) {

		boolean existingName = federiciService.checkExistingNameInTable(newName, "gdm",
				userMB.getUtente().getUteRifId());
		if (existingName) {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "storico.gruppo.monta.cambio.nome");
			sgmDTO.setSgmNome(sgmDTO.getNomeDuplicato());
		} else {
			sgmDTO.setNomeDuplicato(newName);
			return federiciService.updateNomeGDM(newName, sgmDTO);
		}

		return false;
	}

	public List<StoricoGruppiMontaDTO> getGruppiDiMontaList() {
		return gruppiDiMontaList;
	}

	public void setGruppiDiMontaList(List<StoricoGruppiMontaDTO> gruppiDiMontaList) {
		this.gruppiDiMontaList = gruppiDiMontaList;
	}

	public UserMB getUserMB() {
		return userMB;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public StoricoGruppiMontaDTO getNuovoStoricoGruppoMonta() {
		return nuovoStoricoGruppoMonta;
	}

	public void setNuovoStoricoGruppoMonta(StoricoGruppiMontaDTO nuovoStoricoGruppoMonta) {
		this.nuovoStoricoGruppoMonta = nuovoStoricoGruppoMonta;
	}

	public List<AnagraficaDTO> getAnimaliDisponibiliList() {
		return animaliDisponibiliList;
	}

	public void setAnimaliDisponibiliList(List<AnagraficaDTO> animaliDisponibiliList) {
		this.animaliDisponibiliList = animaliDisponibiliList;
	}

	public List<AnagraficaDTO> getAnimaliSelezionateTarget() {
		return animaliSelezionateTarget;
	}

	public void setAnimaliSelezionateTarget(List<AnagraficaDTO> animaliSelezionateTarget) {
		this.animaliSelezionateTarget = animaliSelezionateTarget;
	}

	public StoricoGruppiMontaDTO getStoricoGruppoMontaAggiuntaAnimali() {
		return storicoGruppoMontaAppoggio;
	}

	public void setStoricoGruppoMontaAggiuntaAnimali(StoricoGruppiMontaDTO storicoGruppoMontaAggiuntaAnimali) {
		this.storicoGruppoMontaAppoggio = storicoGruppoMontaAggiuntaAnimali;
	}

	public StoricoGruppiMontaDTO getStoricoGruppoMontaAppoggio() {
		return storicoGruppoMontaAppoggio;
	}

	public void setStoricoGruppoMontaAppoggio(StoricoGruppiMontaDTO storicoGruppoMontaAppoggio) {
		this.storicoGruppoMontaAppoggio = storicoGruppoMontaAppoggio;
	}

	public List<AnagraficaDTO> getAnimaliDisponibiliFinal() {
		return animaliDisponibiliFinal;
	}

	public void setAnimaliDisponibiliFinal(List<AnagraficaDTO> animaliDisponibiliFinal) {
		this.animaliDisponibiliFinal = animaliDisponibiliFinal;
	}

	public Set<AnagraficaDTO> getAnimaliAggiuntiView() {
		return animaliAggiuntiView;
	}

	public void setAnimaliAggiuntiView(Set<AnagraficaDTO> animaliAggiuntiView) {
		this.animaliAggiuntiView = animaliAggiuntiView;
	}

	public String getAnimaliAggiunti() {
		return animaliAggiunti;
	}

	public void setAnimaliAggiunti(String animaliAggiunti) {
		this.animaliAggiunti = animaliAggiunti;
	}

	public String getValueAna() {
		return valueAna;
	}

	public void setValueAna(String valueAna) {
		this.valueAna = valueAna;
	}

	public Set<AnagraficaDTO> getAnimaliEditAggiuntiView() {
		return animaliEditAggiuntiView;
	}

	public void setAnimaliEditAggiuntiView(Set<AnagraficaDTO> animaliEditAggiuntiView) {
		this.animaliEditAggiuntiView = animaliEditAggiuntiView;
	}

	public String getAnimaleRemover() {
		return animaleRemover;
	}

	public void setAnimaleRemover(String animaleRemover) {
		this.animaleRemover = animaleRemover;
	}

	public String getAnimaleEditRemover() {
		return animaleEditRemover;
	}

	public void setAnimaleEditRemover(String animaleEditRemover) {
		this.animaleEditRemover = animaleEditRemover;
	}

	public String getValueAnaToro() {
		return valueAnaToro;
	}

	public void setValueAnaToro(String valueAnaToro) {
		this.valueAnaToro = valueAnaToro;
	}

	public List<AnagraficaDTO> getToriDisponibiliFinal() {
		return toriDisponibiliFinal;
	}

	public void setToriDisponibiliFinal(List<AnagraficaDTO> toriDisponibiliFinal) {
		this.toriDisponibiliFinal = toriDisponibiliFinal;
	}

	public List<String> getToriDisponibiliFinalString() {
		return toriDisponibiliFinalString;
	}

	public void setToriDisponibiliFinalString(List<String> toriDisponibiliFinalString) {
		this.toriDisponibiliFinalString = toriDisponibiliFinalString;
	}

	public boolean isToroAdded() {
		return toroAdded;
	}

	public void setToroAdded(boolean toroAdded) {
		this.toroAdded = toroAdded;
	}
}
