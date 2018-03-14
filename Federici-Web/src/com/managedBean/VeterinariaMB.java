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

	@EJB
	FedericiService federiciService;

	@PostConstruct
	public void init() {
		nuovoIntVeterinario = new VeterinariaDTO();
		pesataDto = new PesataDTO();
		indexOfanimale = 0;
		motivoVisita = null;

		anagraficaList = federiciService.getAllAnimali(userMB.getUtente().getUteRifId());
	}

	public void salvaDatiVet() {
		System.out.println("entrato");

		if (nuovoIntVeterinario == null) {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "errore.tecnico");
			System.out.println("nuovo animale");
			return;
		} else {
			if (nuovoIntVeterinario.getVetDataVisita() == null) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "data.visita.mancante");
				System.out.println("Inserire la data della visita");
				return;
			}
			if (motivoVisita == null) {
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
			// if (pesataDto == null) {
			// addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione
			// !",
			// "Errore Tecnico");
			// return null;
			// }
			// if (pesataDto.getPesPeso() == null ||
			// pesataDto.getPesPeso().equals(new Double (0))) {
			// addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione
			// !", "Inserire il peso corretto");
			// return null;
			// }
			// if (pesataDto != null && (pesataDto.getPesPeso() != null &&
			// !pesataDto.getPesPeso().equals(new Double(0)))
			// && pesataDto.getPesFase() == null) {
			// addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione
			// !",
			// "Inserire la fase del peso dell'animale");
			// return null;
			// }
			nuovoIntVeterinario.setVetMotivoVisita(motivoVisita);
			pesataDto.setPesData(nuovoIntVeterinario.getVetDataVisita());
			pesataDto.setPesAnaId(nuovoIntVeterinario.getVetAnaId());
			boolean saved = federiciService.salvaNuovaVetEPesata(nuovoIntVeterinario, pesataDto);
			if (saved) {
				if (null == anagraficaList.get(getIndexOfanimale()).getVeterinarias()) {
					System.out.println("lista vet a null");
					List<VeterinariaDTO> listaVet = new ArrayList<>();
					listaVet.add(nuovoIntVeterinario);
					anagraficaList.get(getIndexOfanimale()).setVeterinarias(listaVet);
				} else
					anagraficaList.get(getIndexOfanimale()).getVeterinarias().add(nuovoIntVeterinario);
				if (null == anagraficaList.get(getIndexOfanimale()).getPesatas()) {
					System.out.println("lista pes a null");
					List<PesataDTO> listaPes = new ArrayList<>();
					listaPes.add(pesataDto);
					anagraficaList.get(getIndexOfanimale()).setPesatas(listaPes);
				} else
					anagraficaList.get(getIndexOfanimale()).getPesatas().add(pesataDto);
				nuovoIntVeterinario = new VeterinariaDTO();
				pesataDto = new PesataDTO();
				motivoVisita = "";
				RequestContext.getCurrentInstance().update("@all");
			} else {
				System.out.println("false");
			}

			return
			// saved ? "ok" : "ko"
			;

		}
		// return null;
	}

	public void openDialogVet(int indexOfAna) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgNuovoVet').show();");
		setIndexOfanimale(indexOfAna);
		nuovoIntVeterinario = new VeterinariaDTO();
		nuovoIntVeterinario.setAnagrafica(anagraficaList.get(indexOfAna));
		nuovoIntVeterinario.setVetAnaId(anagraficaList.get(indexOfAna).getAnaId());
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

}
