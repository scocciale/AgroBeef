package com.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

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
	private Set<AnagraficaDTO> animaliAggiuntiView;
	private String animaliAggiunti;
	private String valueAna;

	private DualListModel<String> animaliDisponibili;
	private List<String> animaliSource;
	private List<String> animaliTarget;
	private DualListModel<String> animaliDisponibiliPerAggiunta;
	private List<String> animaliSourcePerAggiunta;
	private List<String> animaliTargetPerAggiunta;

	@EJB
	FedericiService federiciService;

	@PostConstruct
	public void init() {

		///////////////////////////////////

		// gruppiDiMontaList =
		// federiciService.getAllGruppiDiMonta(userMB.getUtente().getUteRifId());
		// List<AnagraficaDTO> animaliDisponibiliFirst =
		// federiciService.getAllAnimali(userMB.getUtente().getUteRifId());
		//
		// for (AnagraficaDTO ana : animaliDisponibiliFirst) {
		// boolean esiste = false;
		// for (StoricoGruppiMontaDTO sgm : gruppiDiMontaList) {
		// for (GruppoMontaDTO gm : sgm.getGruppoMontas()) {
		// if (gm.getAnagrafica().getAnaId() == ana.getAnaId()) {
		// esiste = true;
		// if (gm.getGmoDataUscita() != null &&
		// gm.getGmoDataUscita().after(gm.getGmoDataInserimento())) {
		// // || !gm.getGmoDataUscita().equals(new Date(0))) {
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
		// }
		// }
		// for (AnagraficaDTO ana : animaliDisponibiliListAppoggio) {
		// if (federiciService.getLastGruppiAccrFini(ana.getAnaId())) {
		// animaliDisponibiliList.add(ana);
		// if (ana.getAnaFlagToro() == 1)
		// animaliSource.add(ana.getAnaNumMatricola() + " *");
		// else
		// animaliSource.add(ana.getAnaNumMatricola());
		// }
		// }

		///////////////////////////
		// DA QUI é GIUSTO
		//
		// gruppiDiMontaList = new ArrayList<>();
		// nuovoStoricoGruppoMonta = new StoricoGruppiMontaDTO();
		// storicoGruppoMontaAppoggio = new StoricoGruppiMontaDTO();
		//
		// // animali che sono disponibili per i gruppi di monta
		// animaliDisponibiliList = new ArrayList<>();
		//
		// // animali che sono nei gruppi di monta
		// // List<Integer> anagraficaCodice = new ArrayList<>();
		//
		// animaliDisponibili = new DualListModel<>();
		// animaliDisponibiliPerAggiunta = new DualListModel<>();
		// animaliSource = new ArrayList<String>();
		// animaliTarget = new ArrayList<String>();
		// animaliSourcePerAggiunta = new ArrayList<>();
		// animaliTargetPerAggiunta = new ArrayList<>();
		// gruppiDiMontaList =
		// federiciService.getAllGruppiDiMonta(userMB.getUtente().getUteRifId());
		//
		// List<AnagraficaDTO> allAnimali =
		// federiciService.getAllAnimali(userMB.getUtente().getUteRifId());
		// List<StoricoAccrescFiniDTO> gruppiDiAccrOpenList = federiciService
		// .getAllGruppiDiAccrescimentoOpen(userMB.getUtente().getUteRifId());
		// List<StoricoGruppiMontaDTO> gruppiDiMontaOpenList = federiciService
		// .getAllGruppiDiMontaOpen(userMB.getUtente().getUteRifId());
		//
		// System.out.println(allAnimali.size());
		//
		// List<String> animaliFromGdm = new ArrayList<>();
		// List<String> animaliFromGda = new ArrayList<>();
		//
		// for (StoricoAccrescFiniDTO sgda : gruppiDiAccrOpenList) {
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
		// System.out.println(a.getAnaNumMatricola() + " mat ");
		// System.out.println(animaliFromGda.size() + " gda ");
		// System.out.println(animaliFromGdm.size() + " gdm ");
		// if (!animaliFromGda.contains(a.getAnaNumMatricola()) &&
		// !animaliFromGdm.contains(a.getAnaNumMatricola())) {
		// animaliDisponibiliList.add(a);
		// animaliSource.add(a.getAnaNumMatricola());
		// }
		// }
		//
		// System.out.println(animaliDisponibiliList.size() + " animali
		// disponibili");
		//
		// animaliDisponibili = new DualListModel<String>(animaliSource,
		// animaliTarget);
		///////////////////////// VALIDO FINO A QUI

		//
		gruppiDiMontaList = new ArrayList<>();
		nuovoStoricoGruppoMonta = new StoricoGruppiMontaDTO();
		storicoGruppoMontaAppoggio = new StoricoGruppiMontaDTO();

		// animali che sono disponibili per i gruppi di monta
		animaliDisponibiliList = new ArrayList<>();

		animaliDisponibili = new DualListModel<>();
		animaliDisponibiliPerAggiunta = new DualListModel<>();
		animaliSource = new ArrayList<String>();
		animaliTarget = new ArrayList<String>();
		animaliSourcePerAggiunta = new ArrayList<>();
		animaliTargetPerAggiunta = new ArrayList<>();

		animaliDisponibiliFinal = new ArrayList<>();
		animaliAggiuntiView = new LinkedHashSet<>();
		animaliAggiunti = "";
		valueAna = "";

		gruppiDiMontaList = federiciService.getAllGruppiDiMontaOpen(userMB.getUtente().getUteRifId());
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

	public void addToGruppoDiMonta() {
		int quantitaAnimali = animaliAggiuntiView.size();
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

	public void modificaGruppoDiMonta() {
		animaliTargetPerAggiunta = animaliDisponibiliPerAggiunta.getTarget();
		List<String> eliminare = new ArrayList<>();
		boolean toro = false;
		if (storicoGruppoMontaAppoggio.getGruppoMontas() != null) {
			for (GruppoMontaDTO gm : storicoGruppoMontaAppoggio.getGruppoMontas()) {
				if (gm.getAnagrafica().getAnaFlagToro() == 1)
					toro = true;
			}
		}
		for (AnagraficaDTO ana : animaliDisponibiliList) {
			GruppoMontaDTO gmDTO;
			for (String animali : animaliTargetPerAggiunta) {
				boolean saved;
				if (animali.contains("*"))
					animali = animali.replace(" *", "");
				if (ana.getAnaNumMatricola().equals(animali)) {
					if (ana.getAnaFlagToro() == 1 && toro) {
						addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "toro.gia.selezionato");
						return;
					} else if (ana.getAnaFlagToro() == 1 && !toro) {
						toro = true;
					}
					gmDTO = new GruppoMontaDTO();
					gmDTO.setAnagrafica(ana);
					gmDTO.setGmoAnaId(ana.getAnaId());
					gmDTO.setGmoDataInserimento(new Date());
					gmDTO.setGmoSgmId(storicoGruppoMontaAppoggio.getSgmId());
					storicoGruppoMontaAppoggio.getGruppoMontas().add(gmDTO);
					saved = federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio,
							storicoGruppoMontaAppoggio.getGruppoMontas().indexOf(gmDTO));
					if (gmDTO.getAnagrafica().getAnaFlagToro() == 1)
						eliminare.add(gmDTO.getAnagrafica().getAnaNumMatricola() + " *");
					else
						eliminare.add(gmDTO.getAnagrafica().getAnaNumMatricola());

				}
			}

		}
		// federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAggiuntaAnimali);
		for (String eli : eliminare) {
			animaliTargetPerAggiunta.remove(eli);
			animaliDisponibili.getSource().remove(eli);
		}
		System.out.println("finito ");
	}

	public void showDialogAddAnimali(StoricoGruppiMontaDTO sgm) {
		storicoGruppoMontaAppoggio = sgm;
		animaliSourcePerAggiunta = animaliDisponibili.getSource();
		animaliTargetPerAggiunta = animaliDisponibili.getTarget();
		animaliDisponibiliPerAggiunta = new DualListModel<String>(animaliSourcePerAggiunta, animaliTargetPerAggiunta);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg2').show();");
	}

	public String modificaAnimaleInGruppoDiMonta(StoricoGruppiMontaDTO sgm, int gmId) {
		boolean saved = false;
		storicoGruppoMontaAppoggio = new StoricoGruppiMontaDTO();
		storicoGruppoMontaAppoggio = sgm;
		Iterator<GruppoMontaDTO> iter = sgm.getGruppoMontas().iterator();
		while (iter.hasNext()) {
			GruppoMontaDTO gm = iter.next();
			if (gm.getGmoId() == gmId) {
				if (gm.getAnagrafica().getAnaFlagToro() == 1 && gm.getAnagrafica().getAnaSesso().equals("m")) {
					// eliminazione toro, quindi chiusura gruppo con dialog
					RequestContext context = RequestContext.getCurrentInstance();
					context.execute("PF('dlgEliminaToro').show();");
					RequestContext.getCurrentInstance().update("");
					return "toro";
				} else {
					gm.setGmoDataUscita(new Date());
					saved = federiciService.updateStoricoGruppoMonta(sgm, sgm.getGruppoMontas().indexOf(gm));
					if (saved) {
						if (gm.getAnagrafica().getAnaFlagToro() == 1)
							animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola() + " *");
						else
							animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola());
						iter.remove();
					}
				}
			}
		}
		// federiciService.updateStoricoGruppoMonta(sgm);
		return "ok";
	}

	public void chiusuraGruppoMonta() {
		if (storicoGruppoMontaAppoggio.getGruppoMontas() != null
				&& storicoGruppoMontaAppoggio.getGruppoMontas().size() > 0) {
			for (GruppoMontaDTO gm : storicoGruppoMontaAppoggio.getGruppoMontas()) {
				gm.setGmoDataUscita(new Date());
				gm.setGmoSgmId(storicoGruppoMontaAppoggio.getSgmId());
				federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio,
						storicoGruppoMontaAppoggio.getGruppoMontas().indexOf(gm));
				if (gm.getAnagrafica().getAnaFlagToro() == 1)
					animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola() + " *");
				else
					animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola());
			}
			storicoGruppoMontaAppoggio.setSgmDataChiusura(new Date());
			storicoGruppoMontaAppoggio.setGruppoMontas(new ArrayList<GruppoMontaDTO>());
			federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio);
		}
	}

	public void chiusuraGruppoMonta(StoricoGruppiMontaDTO sgma) {
		storicoGruppoMontaAppoggio = sgma;
		if (storicoGruppoMontaAppoggio.getGruppoMontas() != null
				&& storicoGruppoMontaAppoggio.getGruppoMontas().size() > 0) {
			for (GruppoMontaDTO gm : storicoGruppoMontaAppoggio.getGruppoMontas()) {
				gm.setGmoDataUscita(new Date());
				gm.setGmoSgmId(storicoGruppoMontaAppoggio.getSgmId());
				federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio,
						storicoGruppoMontaAppoggio.getGruppoMontas().indexOf(gm));
				if (gm.getAnagrafica().getAnaFlagToro() == 1)
					animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola() + " *");
				else
					animaliDisponibili.getSource().add(gm.getAnagrafica().getAnaNumMatricola());
			}
			storicoGruppoMontaAppoggio.setSgmDataChiusura(new Date());
			storicoGruppoMontaAppoggio.setGruppoMontas(new ArrayList<GruppoMontaDTO>());
			federiciService.updateStoricoGruppoMonta(storicoGruppoMontaAppoggio);
		}
	}

	public String salvaGruppoDiMonta() {
		Date date = new Date();
		boolean saved = false;

		if (nuovoStoricoGruppoMonta != null) {
			if (nuovoStoricoGruppoMonta.getSgmDataApertura().before(date)
					|| nuovoStoricoGruppoMonta.getSgmDataApertura().equals(date)) {
				List<String> animaliTargetTemp = animaliDisponibili.getTarget();
				System.out.println("animali selected " + animaliTargetTemp.size());
				System.out.println("animali selected " + animaliDisponibili.getTarget());
				if (animaliTargetTemp != null && animaliTargetTemp.size() > 0) {
					List<GruppoMontaDTO> nuoviGruppiMontaDTOList = new ArrayList<>();
					boolean toro = false;
					for (AnagraficaDTO ana : animaliDisponibiliList) {
						GruppoMontaDTO gmDTO;
						for (String animali : animaliTargetTemp) {
							if (animali.contains("*"))
								animali = animali.replace(" *", "");
							if (ana.getAnaNumMatricola().equals(animali)) {
								gmDTO = new GruppoMontaDTO();
								gmDTO.setAnagrafica(ana);
								gmDTO.setGmoAnaId(ana.getAnaId());
								gmDTO.setGmoDataInserimento(nuovoStoricoGruppoMonta.getSgmDataApertura());
								// gmDTO.setGmoSgmId(gmoSgmId);
								nuoviGruppiMontaDTOList.add(gmDTO);
								if (ana.getAnaFlagToro() == 1)
									toro = true;
							}
						}
					}
					if (!toro) {
						addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "nessun.toro.selezionato");
						return null;
					}
					nuovoStoricoGruppoMonta.setGruppoMontas(nuoviGruppiMontaDTOList);
					nuovoStoricoGruppoMonta.setSgmUteId(userMB.getUtente().getUteId());
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
		if (saved)
			return "ok";
		else
			return "ko";
	}

	// TODO
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

	public DualListModel<String> getAnimaliDisponibili() {
		return animaliDisponibili;
	}

	public void setAnimaliDisponibili(DualListModel<String> animaliDisponibili) {
		this.animaliDisponibili = animaliDisponibili;
	}

	public List<String> getAnimaliSource() {
		return animaliSource;
	}

	public void setAnimaliSource(List<String> animaliSource) {
		this.animaliSource = animaliSource;
	}

	public List<String> getAnimaliTarget() {
		return animaliTarget;
	}

	public void setAnimaliTarget(List<String> animaliTarget) {
		this.animaliTarget = animaliTarget;
	}

	public StoricoGruppiMontaDTO getStoricoGruppoMontaAggiuntaAnimali() {
		return storicoGruppoMontaAppoggio;
	}

	public void setStoricoGruppoMontaAggiuntaAnimali(StoricoGruppiMontaDTO storicoGruppoMontaAggiuntaAnimali) {
		this.storicoGruppoMontaAppoggio = storicoGruppoMontaAggiuntaAnimali;
	}

	public DualListModel<String> getAnimaliDisponibiliPerAggiunta() {
		return animaliDisponibiliPerAggiunta;
	}

	public void setAnimaliDisponibiliPerAggiunta(DualListModel<String> animaliDisponibiliPerAggiunta) {
		this.animaliDisponibiliPerAggiunta = animaliDisponibiliPerAggiunta;
	}

	public List<String> getAnimaliSourcePerAggiunta() {
		return animaliSourcePerAggiunta;
	}

	public void setAnimaliSourcePerAggiunta(List<String> animaliSourcePerAggiunta) {
		this.animaliSourcePerAggiunta = animaliSourcePerAggiunta;
	}

	public List<String> getAnimaliTargetPerAggiunta() {
		return animaliTargetPerAggiunta;
	}

	public void setAnimaliTargetPerAggiunta(List<String> animaliTargetPerAggiunta) {
		this.animaliTargetPerAggiunta = animaliTargetPerAggiunta;
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

}
