package com.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	int indexOfanimale = 0;

	private AnagraficaDTOLazyModel lazyModel;
	private List<AnagraficaDTO> anagraficaFilteredList;

	@EJB
	FedericiService federiciService;

	@PostConstruct
	public void init() {
		nuovoIntVeterinario = new VeterinariaDTO();
		pesataDto = new PesataDTO();
		indexOfanimale = 0;
		motivoVisita = null;

		lazyModel = new AnagraficaDTOLazyModel(userMB, federiciService);

		// anagraficaList =
		// federiciService.getAllAnimali(userMB.getUtente().getUteRifId());
	}

	public void saveDataVet() {

		System.out.println("entrato");

		if (nuovoIntVeterinario == null) {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "errore.tecnico");
			System.out.println("nuovo animale");
			return;
		} else {
			System.out.println("nell'else");
			if (nuovoIntVeterinario.getVetDataVisita() == null) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "data.visita.mancante");
				System.out.println("Inserire la data della visita");
				return;
			}
			if (motivoVisita == null || motivoVisita.isEmpty()) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "motivo.visita.mancante");
				System.out.println("Inserire la motivo della visita");
				return;
			}
			if (nuovoIntVeterinario.getVetDataDiagnosiGrav() != null
					&& nuovoIntVeterinario.getVetDataDiagnosiGrav().after(new Date())) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "data.gravidanza.nonvalida");
				System.out.println("data della presunta gravidanza non può essere nel futuro");
				return;
			}
			nuovoIntVeterinario.setVetMotivoVisita(motivoVisita);
			pesataDto.setPesData(nuovoIntVeterinario.getVetDataVisita());
			pesataDto.setPesAnaId(nuovoIntVeterinario.getVetAnaId());
			boolean saved = federiciService.salvaNuovaVetEPesata(nuovoIntVeterinario, pesataDto);
			if (saved) {
				if (nuovoIntVeterinario.getAnagrafica().getVeterinarias() == null) {
					System.out.println("lista vet a null");
					List<VeterinariaDTO> listaVet = new ArrayList<>();
					listaVet.add(nuovoIntVeterinario);
					nuovoIntVeterinario.getAnagrafica().setVeterinarias(listaVet);
				} else
					nuovoIntVeterinario.getAnagrafica().getVeterinarias().add(nuovoIntVeterinario);
				if (nuovoIntVeterinario.getAnagrafica().getPesatas() == null) {
					System.out.println("lista pes a null");
					List<PesataDTO> listaPes = new ArrayList<>();
					listaPes.add(pesataDto);
					nuovoIntVeterinario.getAnagrafica().setPesatas(listaPes);
				} else
					nuovoIntVeterinario.getAnagrafica().getPesatas().add(pesataDto);
				nuovoIntVeterinario = new VeterinariaDTO();
				pesataDto = new PesataDTO();
				motivoVisita = "";
				RequestContext.getCurrentInstance().update("@all");
			} else {
				System.out.println("false");
			}

			return;
		}
	}

	public void openDialogVet(AnagraficaDTO ana) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgNuovoVet').show();");
		// setIndexOfanimale(indexOfAna);
		nuovoIntVeterinario = new VeterinariaDTO();
		nuovoIntVeterinario.setAnagrafica(ana);
		nuovoIntVeterinario.setVetAnaId(ana.getAnaId());
		nuovoIntVeterinario.setVetDataVisita(new Date());
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

	public int getIndexOfanimale() {
		return indexOfanimale;
	}

	public void setIndexOfanimale(int indexOfanimale) {
		this.indexOfanimale = indexOfanimale;
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

}
