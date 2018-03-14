package com.service;

import java.util.List;

import com.dto.AnagraficaDTO;
import com.dto.PesataDTO;
import com.dto.RazzaDTO;
import com.dto.ReportDTO;
import com.dto.ReportResultDTO;
import com.dto.StoricoAccrescFiniDTO;
import com.dto.StoricoGruppiMontaDTO;
import com.dto.UtenteDTO;
import com.dto.ValutazioneMaceDTO;
import com.dto.VeterinariaDTO;

public interface FedericiService {

	// REPORT

	public List<ReportDTO> getAllReportDisponibili();

	public List<ReportResultDTO> getReportResultEta(String query);

	public List<ReportResultDTO> getReportResultPerc(String query) throws Exception;

	public List<ReportResultDTO> getReportResultPrimaParte(String query);

	public List<ReportResultDTO> getReportResultClassSeurop(String query) throws Exception;

	public List<ReportResultDTO> getReportResultIndiceSeurop(String query) throws Exception;

	public List<ReportResultDTO> getReportResultNoVal(String query);

	public UtenteDTO logIn(String username, String pwd);

	// ANIMALI

	public List<AnagraficaDTO> getAllAnimali(int uteCod);

	// public List<AnagraficaDTO> getAllAnimaliAnagrafica(int uteCod, int first,
	// int pageSize, String sortField,
	// SortOrder sortOrder);

	public AnagraficaDTO getAnimale(String matricola);

	public boolean salvaNuovoAnimale(AnagraficaDTO nuovoAnimale, int uteId);

	public String getRazza(String anaNumMatricola, int uteId);

	public List<StoricoGruppiMontaDTO> getAllGruppiDiMonta(int uteId);

	public List<StoricoGruppiMontaDTO> getAllGruppiDiMontaOpen(int uteId);

	public List<ValutazioneMaceDTO> getAllValutazioniMace(int uteId);

	public boolean saveNuovoGruppoMonta(StoricoGruppiMontaDTO nuovoStoricoGruppoMonta);

	public boolean updateGruppoMonta(StoricoGruppiMontaDTO sgmDTO);

	public void updateStoricoGruppoMonta(StoricoGruppiMontaDTO sgm);

	public boolean updateStoricoGruppoMonta(StoricoGruppiMontaDTO sgm, int indexOf);

	public List<StoricoAccrescFiniDTO> getAllGruppiDiAccrescimento(int uteId);

	public List<StoricoAccrescFiniDTO> getAllGruppiDiAccrescimentoOpen(int uteId);

	public boolean getLastGruppoMonta(int anaId);

	public boolean saveNuovoGruppoAccrescimentoEfinissaggio(StoricoAccrescFiniDTO nuovoStoricoAccrFini);

	public boolean updateStoricoAccrFinis(StoricoAccrescFiniDTO sAccr, int indexOf);

	public void updateStoricoAccrFiniss(StoricoAccrescFiniDTO storicoGruppoAccrAppoggio);

	public boolean getLastGruppiAccrFini(int anaId);

	public boolean salvaNuovaVetEPesata(VeterinariaDTO nuovoIntVeterinario, PesataDTO pesataDto);

	public boolean updateCambiamentiAnimale(AnagraficaDTO animaleEdited);

	public List<AnagraficaDTO> getAllAnimaliUsciti(int uteId);

	public boolean salvaNuovaValutazione(ValutazioneMaceDTO nuovaValutazione);

	public List<RazzaDTO> getAllRazze();

	public List<AnagraficaDTO> getAnimaliDaMatMadre(String anaNumMatricola, int uteId);

	// public List<AnagraficaDTO> getAllAnimaliDisponibili(int uteId);

	public List<AnagraficaDTO> getAllMatricoleDisponibili(int uteRifId, String queryParams);
}