package com.dto;

import java.sql.Timestamp;
import java.util.List;

public class UtenteDTO {
	private int uteId;
	private String uteCognome;
	private String uteNome;
	private String utePwd;
	private String uteUsername;
	private Timestamp uteUltimoAcc;
	private List<AnagraficaDTO> anagraficas;
	private List<StoricoAccrescFiniDTO> storicoAccrescFinis;
	private List<StoricoGruppiMontaDTO> storicoGruppiMontas;
	private ProfiloDTO profilo;
	private int uteRifId;

	public int getUteId() {
		return uteId;
	}

	public void setUteId(int uteId) {
		this.uteId = uteId;
	}

	public String getUteCognome() {
		return uteCognome;
	}

	public void setUteCognome(String uteCognome) {
		this.uteCognome = uteCognome;
	}

	public String getUteNome() {
		return uteNome;
	}

	public void setUteNome(String uteNome) {
		this.uteNome = uteNome;
	}

	public String getUtePwd() {
		return utePwd;
	}

	public void setUtePwd(String utePwd) {
		this.utePwd = utePwd;
	}

	public String getUteUsername() {
		return uteUsername;
	}

	public void setUteUsername(String uteUsername) {
		this.uteUsername = uteUsername;
	}

	public List<AnagraficaDTO> getAnagraficas() {
		return anagraficas;
	}

	public void setAnagraficas(List<AnagraficaDTO> anagraficas) {
		this.anagraficas = anagraficas;
	}

	public List<StoricoAccrescFiniDTO> getStoricoAccrescFinis() {
		return storicoAccrescFinis;
	}

	public void setStoricoAccrescFinis(List<StoricoAccrescFiniDTO> storicoAccrescFinis) {
		this.storicoAccrescFinis = storicoAccrescFinis;
	}

	public List<StoricoGruppiMontaDTO> getStoricoGruppiMontas() {
		return storicoGruppiMontas;
	}

	public void setStoricoGruppiMontas(List<StoricoGruppiMontaDTO> storicoGruppiMontas) {
		this.storicoGruppiMontas = storicoGruppiMontas;
	}

	public ProfiloDTO getProfilo() {
		return profilo;
	}

	public void setProfilo(ProfiloDTO profilo) {
		this.profilo = profilo;
	}

	public int getUteRifId() {
		return uteRifId;
	}

	public void setUteRifId(int uteRifId) {
		this.uteRifId = uteRifId;
	}

	public Timestamp getUteUltimoAcc() {
		return uteUltimoAcc;
	}

	public void setUteUltimoAcc(Timestamp uteUltimoAcc) {
		this.uteUltimoAcc = uteUltimoAcc;
	}
}
