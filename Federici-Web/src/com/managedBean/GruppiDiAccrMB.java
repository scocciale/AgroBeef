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

import com.dto.AnagrAccrFiniDTO;
import com.dto.AnagraficaDTO;
import com.dto.StoricoAccrescFiniDTO;
import com.service.FedericiService;

@ManagedBean(name = "accrMB")
@ViewScoped
public class GruppiDiAccrMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

	private List<StoricoAccrescFiniDTO> gruppiDiAccrList;
	private StoricoAccrescFiniDTO nuovoStoricoGruppoAccr;
	private List<AnagraficaDTO> animaliNONDisponibiliList;
	private List<AnagraficaDTO> animaliSelezionateTarget;
	private List<AnagraficaDTO> animaliDisponibiliList;
	private StoricoAccrescFiniDTO storicoGruppoAccrAppoggio;

	private List<AnagraficaDTO> animaliDisponibiliFinal;
	private List<AnagraficaDTO> animaliDisponibiliFinalSelected;
	private Set<AnagraficaDTO> animaliAggiuntiView;
	private AnagraficaDTO val;
	private String animaliAggiunti;
	private String valueAna;
	private Set<AnagraficaDTO> animaliEditAggiuntiView;

	private String animaleRemover;
	private String animaleEditRemover;
	// // private StoricoGruppiMontaDTO storicoGruppoMontaAggiuntaAnimali;
	//

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

		gruppiDiAccrList = new ArrayList<>();
		nuovoStoricoGruppoAccr = new StoricoAccrescFiniDTO();
		storicoGruppoAccrAppoggio = new StoricoAccrescFiniDTO();

		// animali che sono disponibili per i gruppi di accrescimento
		animaliDisponibiliList = new ArrayList<>();
		animaliNONDisponibiliList = new ArrayList<>();

		// animali che sono nei gruppi di accrescimento
		// List<Integer> anagraficaCodice = new ArrayList<>();

		// animaliDisponibili = new DualListModel<>();
		// animaliDisponibiliPerAggiunta = new DualListModel<>();
		// animaliSource = new ArrayList<String>();
		// animaliTarget = new ArrayList<String>();
		// animaliSourcePerAggiunta = new ArrayList<>();
		// animaliTargetPerAggiunta = new ArrayList<>();

		//
		// themesSource =
		// federiciService.getAllMatricoleDisponibili(userMB.getUtente().getUteRifId(),
		// "mat");
		// themesTarget = new ArrayList<AnagraficaDTO>();
		//
		// aaaaa = new DualListModel<AnagraficaDTO>(themesSource, themesTarget);
		// gruppiDiAccrList =
		// federiciService.getAllGruppiDiAccrescimento(userMB.getUtente().getUteRifId());

		///////////////////////////

		// List<AnagraficaDTO> animaliDisponibiliFirst =
		// federiciService.getAllAnimali(userMB.getUtente().getUteRifId());
		//
		// for (AnagraficaDTO ana : animaliDisponibiliFirst) {
		// boolean esiste = false;
		// for (StoricoAccrescFiniDTO sac : gruppiDiAccrList) {
		// for (AnagrAccrFiniDTO aaf : sac.getAnagrAccrFinis()) {
		// if (aaf.getAnagrafica().getAnaId() == ana.getAnaId()) {
		// esiste = true;
		// if (aaf.getAafDataUscita() != null &&
		// aaf.getAafDataUscita().after(aaf.getAafDataEntrata())) {
		// animaliDisponibiliListAppoggio.add(ana);
		// // if (ana.getAnaFlagToro() == 1)
		// // animaliSource.add(ana.getAnaNumMatricola() + "
		// // *");
		// // else
		// // animaliSource.add(ana.getAnaNumMatricola());
		// } else {
		// animaliNONDisponibiliList.add(ana);
		// }
		// }
		// }
		// }
		// if (!esiste) {
		// animaliDisponibiliListAppoggio.add(ana);
		// // if (ana.getAnaFlagToro() == 1)
		// // animaliSource.add(ana.getAnaNumMatricola() + " *");
		// // else
		// // animaliSource.add(ana.getAnaNumMatricola());
		// }
		// }
		// for (AnagraficaDTO ana : animaliDisponibiliListAppoggio) {
		// if (federiciService.getLastGruppoMonta(ana.getAnaId())) {
		// animaliDisponibiliList.add(ana);
		// animaliSource.add(ana.getAnaNumMatricola());
		// }
		// }
		// System.out.println(animaliDisponibiliList.size() + "
		// animalidisponibili");
		// System.out.println(animaliNONDisponibiliList.size() + " animali NON
		// disponibili");

		///////////////////////////

		// List<AnagraficaDTO> allAnimali =
		// federiciService.getAllAnimali(userMB.getUtente().getUteRifId());
		// List<StoricoAccrescFiniDTO> gruppiDiAccrList = federiciService
		// .getAllGruppiDiAccrescimentoOpen(userMB.getUtente().getUteRifId());
		// List<StoricoGruppiMontaDTO> gruppiDiMontaOpenList = federiciService
		// .getAllGruppiDiMontaOpen(userMB.getUtente().getUteRifId());

		// animaliDisponibiliFinal =
		// federiciService.getAllMatricoleDisponibili(userMB.getUtente().getUteRifId(),paramRicerca);

		animaliDisponibiliFinal = new ArrayList<>();
		animaleRemover = new String();
		animaleEditRemover = new String();

		animaliDisponibiliFinalSelected = new ArrayList<>();
		val = new AnagraficaDTO();
		animaliAggiuntiView = new LinkedHashSet<>();
		animaliAggiunti = "";
		valueAna = "";
		animaliEditAggiuntiView = new LinkedHashSet<>();

		gruppiDiAccrList = federiciService.getAllGruppiDiAccrescimentoOpen(userMB.getUtente().getUteRifId());

		// List<String> animaliFromGdm = new ArrayList<>();
		// List<String> animaliFromGda = new ArrayList<>();
		//
		// for (StoricoAccrescFiniDTO sgda : gruppiDiAccrList) {
		// for (AnagrAccrFiniDTO gdf : sgda.getAnagrAccrFinis()) {
		// if (gdf.getAafDataUscita() == null) {
		// animaliFromGda.add(gdf.getAnagrafica().getAnaNumMatricola());
		// }
		// }
		// }
		// for (StoricoGruppiMontaDTO sgdm : gruppiDiMontaOpenList) {
		// for (GruppoMontaDTO gdm : sgdm.getGruppoMontas()) {
		// if (gdm.getGmoDataUscita() == null) {
		// animaliFromGdm.add(gdm.getAnagrafica().getAnaNumMatricola());
		// }
		// }
		// }
		//
		// for (AnagraficaDTO a : allAnimali) {
		// if (!animaliFromGda.contains(a.getAnaNumMatricola()) &&
		// !animaliFromGdm.contains(a.getAnaNumMatricola())) {
		// animaliDisponibiliList.add(a);
		// animaliSource.add(a.getAnaNumMatricola());
		// }
		// }
		//
		// setAnimaliDisponibili(new DualListModel<String>(animaliSource,
		// animaliTarget));

	}

	public List<String> findFromParms(String valParam) {
		List<String> listaStringAnimali = new ArrayList<>();
		animaliDisponibiliFinal = federiciService.getAllMatricoleDisponibili(userMB.getUtente().getUteRifId(),
				valParam);
		for (AnagraficaDTO anagraficaDTO : animaliDisponibiliFinal) {
			listaStringAnimali.add((anagraficaDTO.getAnaFlagToro() == 1)
					? anagraficaDTO.getAnaNumMatricola().concat(" *") : anagraficaDTO.getAnaNumMatricola());
		}

		return listaStringAnimali;
	}

	public String salvaGruppoDiAccr() {
		Date date = Calendar.getInstance(Locale.ITALY).getTime();
		boolean saved = false;
		List<AnagrAccrFiniDTO> nuoviGruppiAccrDTOList = new ArrayList<>();

		if (nuovoStoricoGruppoAccr != null) {
			// è giusto che non è possibile creare gruppi in date future ???
			if (nuovoStoricoGruppoAccr.getSafDataInizio().before(date)
					|| nuovoStoricoGruppoAccr.getSafDataInizio().equals(date)) {
				if (animaliAggiuntiView != null && animaliAggiuntiView.size() > 0) {
					// boolean toro = false;
					for (AnagraficaDTO ana : animaliAggiuntiView) {
						AnagrAccrFiniDTO gaccrDTO;
						gaccrDTO = new AnagrAccrFiniDTO();
						gaccrDTO.setAnagrafica(ana);
						gaccrDTO.setAafAnaId(ana.getAnaId());
						gaccrDTO.getAnagrafica().setAnaFlagDisponibile("0");
						gaccrDTO.setAafDataEntrata(nuovoStoricoGruppoAccr.getSafDataInizio());
						// gmDTO.setGmoSgmId(gmoSgmId);
						nuoviGruppiAccrDTOList.add(gaccrDTO);
						// if (ana.getAnaFlagToro() == 1)
						// toro = true;

					}
					nuovoStoricoGruppoAccr.setAnagrAccrFinis(nuoviGruppiAccrDTOList);
					nuovoStoricoGruppoAccr.setSafUteId(userMB.getUtente().getUteId());
					saved = federiciService.saveNuovoGruppoAccrescimentoEfinissaggio(nuovoStoricoGruppoAccr);
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
			gruppiDiAccrList.add(nuovoStoricoGruppoAccr);
			animaliAggiuntiView.clear();
			valueAna = "";
			animaliAggiunti = new String();
			animaliAggiuntiView = new LinkedHashSet<>();
			animaleRemover = new String();
			return "ok";
		} else
			return "ko";

	}

	public void chiusuraGruppoAccr(StoricoAccrescFiniDTO sgaccr) {
		storicoGruppoAccrAppoggio = sgaccr;
		if (storicoGruppoAccrAppoggio.getAnagrAccrFinis() != null
				&& storicoGruppoAccrAppoggio.getAnagrAccrFinis().size() > 0) {
			for (AnagrAccrFiniDTO accr : storicoGruppoAccrAppoggio.getAnagrAccrFinis()) {
				accr.setAafDataUscita(Calendar.getInstance(Locale.ITALY).getTime());
				accr.getAnagrafica().setAnaFlagDisponibile("1");
				accr.setAafSafId(storicoGruppoAccrAppoggio.getSafId());
				federiciService.updateStoricoAccrFinis(storicoGruppoAccrAppoggio,
						storicoGruppoAccrAppoggio.getAnagrAccrFinis().indexOf(accr));
			}
			storicoGruppoAccrAppoggio.setSafDataFine(Calendar.getInstance(Locale.ITALY).getTime());
			storicoGruppoAccrAppoggio.setAnagrAccrFinis(new ArrayList<AnagrAccrFiniDTO>());
			federiciService.updateStoricoAccrFiniss(storicoGruppoAccrAppoggio);
		}
	}

	public String modificaAnimaleInGruppoDiAccr(StoricoAccrescFiniDTO saccrVar, int agaId) {
		boolean saved = false;
		storicoGruppoAccrAppoggio = new StoricoAccrescFiniDTO();
		storicoGruppoAccrAppoggio = saccrVar;
		Iterator<AnagrAccrFiniDTO> iter = saccrVar.getAnagrAccrFinis().iterator();
		while (iter.hasNext()) {
			AnagrAccrFiniDTO accr = iter.next();
			if (accr.getAafId() == agaId) {
				accr.setAafDataUscita(Calendar.getInstance(Locale.ITALY).getTime());
				accr.getAnagrafica().setAnaFlagDisponibile("1");
				saved = federiciService.updateStoricoAccrFinis(saccrVar, saccrVar.getAnagrAccrFinis().indexOf(accr));
				if (saved) {
					iter.remove();
				}
			}
		}
		return "ok";

	}

	public void modificaGruppoDiAcrr() {
		boolean saved = false;
		if (animaliEditAggiuntiView != null && animaliEditAggiuntiView.size() > 0) {
			for (AnagraficaDTO ana : animaliEditAggiuntiView) {
				AnagrAccrFiniDTO accrDTO;
				accrDTO = new AnagrAccrFiniDTO();
				accrDTO.setAnagrafica(ana);
				accrDTO.setAafAnaId(ana.getAnaId());
				accrDTO.setAafDataEntrata(Calendar.getInstance(Locale.ITALY).getTime());
				accrDTO.setAafSafId(storicoGruppoAccrAppoggio.getSafId());
				accrDTO.getAnagrafica().setAnaFlagDisponibile("0");
				storicoGruppoAccrAppoggio.getAnagrAccrFinis().add(accrDTO);
				saved = federiciService.updateStoricoAccrFinis(storicoGruppoAccrAppoggio,
						storicoGruppoAccrAppoggio.getAnagrAccrFinis().indexOf(accrDTO));
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
		System.out.println("Aggiunta anagrafica in gruppo di accr/finiss ? " + saved);
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

	public void showDialogAddAnimali(StoricoAccrescFiniDTO saccrVar) {
		storicoGruppoAccrAppoggio = saccrVar;
		// animaliSourcePerAggiunta = animaliDisponibili.getSource();
		// animaliTargetPerAggiunta = animaliDisponibili.getTarget();
		// animaliDisponibiliPerAggiunta = new
		// DualListModel<String>(animaliSourcePerAggiunta,
		// animaliTargetPerAggiunta);
		showDialogFromName("dlgAggiuntaAnimaliInGruppoAccr");
		// System.out.println("finito");
	}

	public void addToEditGruppoDiAccr() {
		int quantitaAnimali = animaliEditAggiuntiView.size();
		if (valueAna.contains(" *"))
			valueAna = valueAna.replace(" *", "");
		for (AnagraficaDTO a : animaliDisponibiliFinal) {
			if (a.getAnaNumMatricola().equals(valueAna) && !animaliAggiunti.contains(valueAna)) {
				animaliEditAggiuntiView.add(a);
				animaliAggiunti = animaliAggiunti.concat(valueAna);
			}
		}
		if (animaliEditAggiuntiView.size() == quantitaAnimali)
			showDialogFromName("dlgAnimaleAddedYet");
	}

	public void addToGruppoDiAccr() {
		int quantitaAnimali = animaliAggiuntiView.size();
		if (valueAna.contains(" *"))
			valueAna = valueAna.replace(" *", "");
		for (AnagraficaDTO a : animaliDisponibiliFinal) {
			if (a.getAnaNumMatricola().equals(valueAna) && !animaliAggiunti.contains(valueAna)) {
				animaliAggiuntiView.add(a);
				animaliAggiunti = animaliAggiunti.concat(valueAna);
			}
		}
		if (animaliAggiuntiView.size() == quantitaAnimali)
			showDialogFromName("dlgAnimaleAddedYet");
	}

	public void showDialogFromName(String name) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('" + name + "').show();");
	}

	public void chiusuraGruppoMonta() {

	}

	public StoricoAccrescFiniDTO getNuovoStoricoGruppoAccr() {
		return nuovoStoricoGruppoAccr;
	}

	public void setNuovoStoricoGruppoAccr(StoricoAccrescFiniDTO nuovoStoricoGruppoAccr) {
		this.nuovoStoricoGruppoAccr = nuovoStoricoGruppoAccr;
	}

	public List<AnagraficaDTO> getAnimaliSelezionateTarget() {
		return animaliSelezionateTarget;
	}

	public void setAnimaliSelezionateTarget(List<AnagraficaDTO> animaliSelezionateTarget) {
		this.animaliSelezionateTarget = animaliSelezionateTarget;
	}

	// public DualListModel<String> getAnimaliDisponibili() {
	// return animaliDisponibili;
	// }
	//
	// public void setAnimaliDisponibili(DualListModel<String>
	// animaliDisponibili) {
	// this.animaliDisponibili = animaliDisponibili;
	// }
	//
	// public DualListModel<String> getAnimaliDisponibiliPerAggiunta() {
	// return animaliDisponibiliPerAggiunta;
	// }
	//
	// public void setAnimaliDisponibiliPerAggiunta(DualListModel<String>
	// animaliDisponibiliPerAggiunta) {
	// this.animaliDisponibiliPerAggiunta = animaliDisponibiliPerAggiunta;
	// }
	//
	// public List<String> getAnimaliSourcePerAggiunta() {
	// return animaliSourcePerAggiunta;
	// }
	//
	// public void setAnimaliSourcePerAggiunta(List<String>
	// animaliSourcePerAggiunta) {
	// this.animaliSourcePerAggiunta = animaliSourcePerAggiunta;
	// }
	//
	// public List<String> getAnimaliTargetPerAggiunta() {
	// return animaliTargetPerAggiunta;
	// }
	//
	// public void setAnimaliTargetPerAggiunta(List<String>
	// animaliTargetPerAggiunta) {
	// this.animaliTargetPerAggiunta = animaliTargetPerAggiunta;
	// }
	//
	// public List<String> getAnimaliSource() {
	// return animaliSource;
	// }
	//
	// public void setAnimaliSource(List<String> animaliSource) {
	// this.animaliSource = animaliSource;
	// }
	//
	// public List<String> getAnimaliTarget() {
	// return animaliTarget;
	// }
	//
	// public void setAnimaliTarget(List<String> animaliTarget) {
	// this.animaliTarget = animaliTarget;
	// }

	public List<StoricoAccrescFiniDTO> getGruppiDiAccrList() {
		return gruppiDiAccrList;
	}

	public void setGruppiDiAccrList(List<StoricoAccrescFiniDTO> gruppiDiAccrList) {
		this.gruppiDiAccrList = gruppiDiAccrList;
	}

	public List<AnagraficaDTO> getAnimaliDisponibiliList() {
		return animaliDisponibiliList;
	}

	public void setAnimaliDisponibiliList(List<AnagraficaDTO> animaliDisponibiliList) {
		this.animaliDisponibiliList = animaliDisponibiliList;
	}

	public List<AnagraficaDTO> getAnimaliNONDisponibiliList() {
		return animaliNONDisponibiliList;
	}

	public void setAnimaliNONDisponibiliList(List<AnagraficaDTO> animaliNONDisponibiliList) {
		this.animaliNONDisponibiliList = animaliNONDisponibiliList;
	}

	public UserMB getUserMB() {
		return userMB;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public StoricoAccrescFiniDTO getStoricoGruppoAccrAppoggio() {
		return storicoGruppoAccrAppoggio;
	}

	public void setStoricoGruppoAccrAppoggio(StoricoAccrescFiniDTO storicoGruppoAccrAppoggio) {
		this.storicoGruppoAccrAppoggio = storicoGruppoAccrAppoggio;
	}

	public List<AnagraficaDTO> getAnimaliDisponibiliFinal() {
		return animaliDisponibiliFinal;
	}

	public void setAnimaliDisponibiliFinal(List<AnagraficaDTO> animaliDisponibiliFinal) {
		this.animaliDisponibiliFinal = animaliDisponibiliFinal;
	}

	public List<AnagraficaDTO> getAnimaliDisponibiliFinalSelected() {
		return animaliDisponibiliFinalSelected;
	}

	public void setAnimaliDisponibiliFinalSelected(List<AnagraficaDTO> animaliDisponibiliFinalSelected) {
		this.animaliDisponibiliFinalSelected = animaliDisponibiliFinalSelected;
	}

	public AnagraficaDTO getVal() {
		return val;
	}

	public void setVal(AnagraficaDTO val) {
		this.val = val;
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

	public String getAnimaleEditRemover() {
		return animaleEditRemover;
	}

	public void setAnimaleEditRemover(String animaleEditRemover) {
		this.animaleEditRemover = animaleEditRemover;
	}

	public String getAnimaleRemover() {
		return animaleRemover;
	}

	public void setAnimaleRemover(String animaleRemover) {
		this.animaleRemover = animaleRemover;
	}

}
