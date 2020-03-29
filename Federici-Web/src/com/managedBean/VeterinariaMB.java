package com.managedBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.dto.AnagraficaDTO;
import com.dto.PesataDTO;
import com.dto.VeterinariaDTO;
import com.service.FedericiService;

@ManagedBean(name = "veteMB")
@ViewScoped
public class VeterinariaMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

	private List<AnagraficaDTO> anagraficaList;
	private VeterinariaDTO nuovoIntVeterinario;
	private PesataDTO pesataDto;
	private boolean flagToro = false;
	private boolean flagGemello = false;
	private String motivoVisita = "";
	private String anaNumMatricola = "";
	private String commentoVeterinario = "";

	private AnagraficaDTOLazyModel lazyModel;
	private List<AnagraficaDTO> anagraficaFilteredList;

	@EJB
	FedericiService federiciService;

	@PostConstruct
	public void init() {
		nuovoIntVeterinario = new VeterinariaDTO();
		pesataDto = new PesataDTO();
		motivoVisita = null;

		lazyModel = new AnagraficaDTOLazyModel(userMB, federiciService);

		// anagraficaList =
		// federiciService.getAllAnimali(userMB.getUtente().getUteRifId());
	}

	public void saveDataVet() {

		if (nuovoIntVeterinario == null) {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "errore.tecnico");
			return;
		} else {

			if (nuovoIntVeterinario.getVetDataVisita() == null) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "data.visita.mancante");
				return;
			}
			if (motivoVisita == null || motivoVisita.isEmpty()) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "motivo.visita.mancante");
				return;
			}
			if (nuovoIntVeterinario.getVetDataDiagnosiGrav() != null
					&& nuovoIntVeterinario.getVetDataDiagnosiGrav().after(Calendar.getInstance(Locale.ITALY).getTime())) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "data.gravidanza.nonvalida");
				return;
			}
			nuovoIntVeterinario.setVetMotivoVisita(motivoVisita);
			// pesataDto.setPesData(nuovoIntVeterinario.getVetDataVisita());
			// pesataDto.setPesAnaId(nuovoIntVeterinario.getVetAnaId());
			boolean saved = federiciService.salvaNuovaVet(nuovoIntVeterinario);
			if (saved) {
				if (nuovoIntVeterinario.getAnagrafica().getVeterinarias() == null) {
					System.out.println("lista vet a null");
					List<VeterinariaDTO> listaVet = new ArrayList<>();
					listaVet.add(nuovoIntVeterinario);
					nuovoIntVeterinario.getAnagrafica().setVeterinarias(listaVet);
				} else {
					nuovoIntVeterinario.getAnagrafica().getVeterinarias().add(nuovoIntVeterinario);
				}
				// if (nuovoIntVeterinario.getAnagrafica().getPesatas() == null)
				// {
				// System.out.println("lista pes a null");
				// List<PesataDTO> listaPes = new ArrayList<>();
				// listaPes.add(pesataDto);
				// nuovoIntVeterinario.getAnagrafica().setPesatas(listaPes);
				// } else {
				// nuovoIntVeterinario.getAnagrafica().getPesatas().add(pesataDto);
				// }
				nuovoIntVeterinario = new VeterinariaDTO();
				// pesataDto = new PesataDTO();
				motivoVisita = "";
				RequestContext.getCurrentInstance().update("@all");
			} else {
				// log
			}

			return;
		}
	}

	public void openDialogVet(AnagraficaDTO ana) {

		nuovoIntVeterinario = new VeterinariaDTO();
		nuovoIntVeterinario.setAnagrafica(ana);
		nuovoIntVeterinario.setVetAnaId(ana.getAnaId());
		nuovoIntVeterinario.setVetDataVisita(Calendar.getInstance(Locale.ITALY).getTime());

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgNuovoVet').show();");
	}

	public void openDialogCommento(VeterinariaDTO vet) {
		
		
		setCommentoVeterinario(vet.getVetCommento());

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('commentDialog').show();");
	}

	public void checkRazza() {
		// if (nuovoAnimale.getAnaNumMatricolaMadre() != null &&
		// !nuovoAnimale.getAnaNumMatricolaMadre().equals("")
		// && nuovoAnimale.getAnaNumMatricolaPadre() != null
		// && !nuovoAnimale.getAnaNumMatricolaPadre().equals("")) {
		// String razzaMadre =
		// federiciService.getRazza(nuovoAnimale.getAnaNumMatricolaMadre(),
		// userMB.getUtente().getUteRifId());
		// String razzaPadre =
		// federiciService.getRazza(nuovoAnimale.getAnaNumMatricolaPadre(),
		// userMB.getUtente().getUteRifId());
		// if (razzaMadre.toUpperCase().equals(razzaPadre.toUpperCase())) {
		// nuovoAnimale.setAnaRazza(razzaMadre);
		// }
		// }
	}

	public UserMB getUserMB() {
		return userMB;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public List<AnagraficaDTO> getAnagraficaList() {
		return anagraficaList;
	}

	public void setAnagraficaList(List<AnagraficaDTO> anagraficaList) {
		this.anagraficaList = anagraficaList;
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

	public String getAnaNumMatricola() {
		return anaNumMatricola;
	}

	public void setAnaNumMatricola(String anaNumMatricola) {
		this.anaNumMatricola = anaNumMatricola;
	}

	public VeterinariaDTO getNuovoIntVeterinario() {
		return nuovoIntVeterinario;
	}

	public void setNuovoIntVeterinario(VeterinariaDTO nuovoIntVeterinario) {
		this.nuovoIntVeterinario = nuovoIntVeterinario;
	}

	public PesataDTO getPesataDto() {
		return pesataDto;
	}

	public void setPesataDto(PesataDTO pesataDto) {
		this.pesataDto = pesataDto;
	}

	public String getMotivoVisita() {
		return motivoVisita;
	}

	public void setMotivoVisita(String motivoVisita) {
		this.motivoVisita = motivoVisita;
	}

	public AnagraficaDTOLazyModel getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(AnagraficaDTOLazyModel lazyModel) {
		this.lazyModel = lazyModel;
	}

	public List<AnagraficaDTO> getAnagraficaFilteredList() {
		return anagraficaFilteredList;
	}

	public void setAnagraficaFilteredList(List<AnagraficaDTO> anagraficaFilteredList) {
		this.anagraficaFilteredList = anagraficaFilteredList;
	}

	public String getCommentoVeterinario() {
		return commentoVeterinario;
	}

	public void setCommentoVeterinario(String commentoVeterinario) {
		this.commentoVeterinario = commentoVeterinario;
	}

}
