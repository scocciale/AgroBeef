package com.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.converter.ConverterDtoToEntity;
import com.converter.ConverterEntityToDto;
import com.dto.AnagrAccrFiniDTO;
import com.dto.AnagraficaDTO;
import com.dto.GruppoMontaDTO;
import com.dto.PesataDTO;
import com.dto.RazzaDTO;
import com.dto.ReportDTO;
import com.dto.ReportResultDTO;
import com.dto.StoricoAccrescFiniDTO;
import com.dto.StoricoGruppiMontaDTO;
import com.dto.UtenteDTO;
import com.dto.ValutazioneMaceDTO;
import com.dto.VeterinariaDTO;
import com.entities.AnagrAccrFini;
import com.entities.Anagrafica;
import com.entities.GruppoMonta;
import com.entities.Pesata;
import com.entities.Razza;
import com.entities.Report;
import com.entities.StoricoAccrescFini;
import com.entities.StoricoGruppiMonta;
import com.entities.Utente;
import com.entities.ValutazioneMace;
import com.entities.Veterinaria;
import com.util.BCrypt;

/**
 * Session Bean implementation class FedericiServiceImpl
 */
@Stateless
@Local(FedericiService.class)
public class FedericiServiceImpl extends BaseService implements FedericiService {

	Logger logger = Logger.getLogger(FedericiServiceImpl.class.getName());

	// final static String animaliDisponibiliQueryWithParams = "SELECT a.ana_id,
	// a.ana_num_matricola, a.ana_num_matricola_madre,
	// a.ana_num_matricola_padre, a.ana_flag_toro, a.ana_sesso,
	// a.ana_data_nascita, a.ana_raz_id, a.ana_razza, a.ana_flag_gemello,
	// a.ana_data_acquisto, a.ana_num_parto, a.ana_difficolta_parto,
	// a.ana_ute_id, a.ana_uscita_causa, a.ana_data_uscita FROM Anagrafica a
	// WHERE a.ana_ute_id = {0} AND a.ana_id NOT IN (SELECT ana.ana_id FROM
	// Anagrafica ana, anagr_accr_finis aaf, (SELECT saf.saf_id saf_id FROM
	// storico_accresc_finis saf WHERE saf.saf_data_fine IS NULL AND saf_ute_id
	// = {1} GROUP BY saf.saf_id) open_group WHERE open_group.saf_id =
	// aaf.aaf_saf_id AND aaf.aaf_ana_id = ana.ana_id ) AND a.ana_id NOT IN
	// (SELECT ana.ana_id FROM Anagrafica ana, gruppo_monta gmo, (SELECT
	// sgm.sgm_id sgm_id FROM storico_gruppi_monta sgm WHERE
	// sgm.sgm_data_chiusura IS NULL AND sgm_ute_id = {2} GROUP BY sgm.sgm_id)
	// open_group_monta WHERE open_group_monta.sgm_id = gmo.gmo_sgm_id AND
	// gmo.gmo_ana_id = ana.ana_id ) AND a.ana_num_matricola LIKE '%{3}%' AND
	// (a.ana_uscita_causa LIKE '' OR a.ana_uscita_causa IS NULL)";

	final static String animaliDisponibiliQueryWithParams = "SELECT a.ana_id, a.ana_num_matricola, a.ana_num_matricola_madre, a.ana_num_matricola_padre, a.ana_flag_toro, a.ana_sesso, a.ana_data_nascita, a.ana_raz_id, a.ana_razza, a.ana_flag_gemello, a.ana_data_acquisto, a.ana_num_parto, a.ana_difficolta_parto, a.ana_ute_id, a.ana_uscita_causa, a.ana_data_uscita, a.ana_flag_disponibile FROM Anagrafica a WHERE a.ana_ute_id = {0} AND a.ana_flag_disponibile = '1' AND UPPER(a.ana_num_matricola) LIKE '%{1}%' AND (a.ana_uscita_causa LIKE '' OR a.ana_uscita_causa IS NULL)";

	final static String animaliDisponibiliFemaleQueryWithParams = "SELECT a.ana_id, a.ana_num_matricola, a.ana_num_matricola_madre, a.ana_num_matricola_padre, a.ana_flag_toro, a.ana_sesso, a.ana_data_nascita, a.ana_raz_id, a.ana_razza, a.ana_flag_gemello, a.ana_data_acquisto, a.ana_num_parto, a.ana_difficolta_parto, a.ana_ute_id, a.ana_uscita_causa, a.ana_data_uscita, a.ana_flag_disponibile FROM Anagrafica a WHERE a.ana_ute_id = {0} AND a.ana_flag_disponibile = '1' AND a.ana_sesso = 'F' AND UPPER(a.ana_num_matricola) LIKE '%{1}%' AND (a.ana_uscita_causa LIKE '' OR a.ana_uscita_causa IS NULL)";

	final static String countRowInAnagraficaFromUte = "SELECT count(*) FROM Anagrafica a WHERE a.ana_ute_id = {0}";

	final static String causaUscita = "Macellazione";

	@Override
	public UtenteDTO logIn(String username, String pwd) {
		Utente ute = new Utente();
		try {
			ute = (Utente) getSession(em).createCriteria(Utente.class).add(Restrictions.eq("uteUsername", username)).uniqueResult();
			if (ute != null) {
				if (BCrypt.checkpw(pwd, ute.getUtePwd())) {
					return ConverterEntityToDto.utenteEntityToUtenteDTO(ute);
				} else {
					logger.info("L'utente ha inserito una password errata");
				}
			} else {
				logger.info("L'utente con userId: '" + username + "' non esiste.");
			}
		} catch (Exception e) {
			logger.info("Errore nel prendere da DB l'utente '" + username + "'.");
			e.printStackTrace();
		}
		return null;

	}

	public boolean modifyPwd(UtenteDTO user) {
		Utente ute = new Utente();
		ute = ConverterDtoToEntity.utenteDTOToUtente(user);
		try {
			getSession(em).update(ute);
			getSession(em).flush();
			return true;
		} catch (Exception e) {
			logger.info("Errore nel modificare la pwd per l'utente con userId= " + ute.getUteId() + ".");
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<AnagraficaDTO> getAllAnimali(int uteCod) {
		List<AnagraficaDTO> listDTO = new ArrayList<>();
		List<Anagrafica> list = new ArrayList<>();
		try {
			list = (List<Anagrafica>) getSession(em).createCriteria(Anagrafica.class).add(Restrictions.eq("anaUteId", uteCod))
					.add(Restrictions.disjunction().add(Restrictions.eq("anaUscitaCausa", "")).add(Restrictions.isNull("anaUscitaCausa"))).add(Restrictions.eq("anaFlagDisponibile", "1")).list();

			if (list != null && list.size() > 0) {
				AnagraficaDTO dto;
				for (Anagrafica ana : list) {
					dto = new AnagraficaDTO();
					dto = ConverterEntityToDto.anagraficaEntityToAngraficaDTO(ana);
					listDTO.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listDTO;
	}

	@SuppressWarnings("unchecked")
	public List<AnagraficaDTO> getAllAnimaliAnagrafica(int uteCod, int start, int size, String sortOrderToStr, String sortField) {

		List<AnagraficaDTO> listDTO = new ArrayList<>();
		List<Anagrafica> list = new ArrayList<>();
		try {

			Criteria crit = getSession(em).createCriteria(Anagrafica.class);
			crit.add(Restrictions.eq("anaUteId", uteCod));

			// ordering query
			if (sortField != null && !sortField.isEmpty()) {
				if (sortOrderToStr != null && !sortOrderToStr.isEmpty()) {
					if (sortOrderToStr.equals("desc")) {
						crit.addOrder(Order.desc(sortField));
					} else {
						crit.addOrder(Order.asc(sortField));
					}
				}
			}

			// limit in query
			crit.setFirstResult(start);
			crit.setMaxResults(size);

			list = (List<Anagrafica>) crit.list();

			if (list != null && list.size() > 0) {
				AnagraficaDTO dto;
				for (Anagrafica ana : list) {
					dto = new AnagraficaDTO();
					dto = ConverterEntityToDto.anagraficaEntityToAngraficaDTO(ana);
					listDTO.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listDTO;
	}

	public AnagraficaDTO getAnimale(String matricola) {
		AnagraficaDTO animale = ConverterEntityToDto
				.anagraficaEntityToAngraficaDTO((Anagrafica) getSession(em).createCriteria(Anagrafica.class).add(Restrictions.eq("anaNumMatricola", matricola)).uniqueResult());
		return animale;
	}

	public boolean salvaNuovoAnimale(AnagraficaDTO nuovoAnimale, int uteId) {
		Anagrafica na = new Anagrafica();

		nuovoAnimale.setAnaUteId(uteId);
		na = ConverterDtoToEntity.anagraficaDTOToAngrafica(nuovoAnimale);
		try {
			getSession(em).saveOrUpdate(na);
			getSession(em).flush();

			logger.info("Salvato nuovo animale con matricola '" + na.getAnaNumMatricola() + "'.");
			nuovoAnimale = new AnagraficaDTO();
			nuovoAnimale = ConverterEntityToDto.anagraficaEntityToAngraficaDTO(na);
			return true;
		} catch (Exception e) {
			logger.info("Impossibile salvare nuovo animale con matricola '" + na.getAnaNumMatricola() + "'.");
			e.printStackTrace();
			return false;
		}

	}

	public boolean salvaNuovaValutazione(ValutazioneMaceDTO nuovaValutazione) {
		ValutazioneMace nv = new ValutazioneMace();

		nv = ConverterDtoToEntity.valutazioneMaceEntityToValutazioneMaceDTO(nuovaValutazione);
		try {
			getSession(em).saveOrUpdate(nv);
			getSession(em).flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateCambiamentiAnimale(AnagraficaDTO animaleEdited) {
		Anagrafica na = new Anagrafica();

		na = ConverterDtoToEntity.anagraficaDTOToAngrafica(animaleEdited);

		try {
			getSession(em).update(na);
			getSession(em).flush();

			logger.info("Modficato animale con matricola '" + na.getAnaNumMatricola() + "'.");
			return true;
		} catch (Exception e) {
			logger.info("Impossibile salvare modifiche animale con matricola '" + na.getAnaNumMatricola() + "'.");
			e.printStackTrace();
			return false;
		}
	}

	public boolean salvaNuovaVetEPesata(VeterinariaDTO nuovoIntVeterinario, PesataDTO pesataDto) {
		Veterinaria vet = new Veterinaria();
		Pesata pes = new Pesata();
		vet = ConverterDtoToEntity.veterinariaDtoToVeterinariaEntity(nuovoIntVeterinario);
		if (pesataDto != null) {
			pes = ConverterDtoToEntity.pesataDtoToPesataEntity(pesataDto);
		}
		try {
			getSession(em).saveOrUpdate(vet);
			getSession(em).flush();
			if (pes != null) {
				getSession(em).saveOrUpdate(pes);
				getSession(em).flush();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean salvaNuovaVet(VeterinariaDTO nuovoIntVeterinario) {

		Veterinaria vet = new Veterinaria();
		vet = ConverterDtoToEntity.veterinariaDtoToVeterinariaEntity(nuovoIntVeterinario);
		try {
			getSession(em).saveOrUpdate(vet);
			getSession(em).flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean salvaNuovaPesata(PesataDTO pesataDto) {

		Pesata pes = new Pesata();
		pes = ConverterDtoToEntity.pesataDtoToPesataEntity(pesataDto);
		try {
			getSession(em).save(pes);
			getSession(em).flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getRazza(String anaNumMatricola, int uteId) {
		String razza = "";
		Anagrafica na = new Anagrafica();
		try {

			Criteria crit = getSession(em).createCriteria(Anagrafica.class);
			crit.add(Restrictions.eq("anaUteId", uteId));
			crit.add(Restrictions.eq("anaNumMatricola", anaNumMatricola));
			if (null != crit.uniqueResult()) {
				na = (Anagrafica) crit.uniqueResult();
				razza = na.getAnaRazza();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return razza;
	}

	@SuppressWarnings("unchecked")
	public boolean getLastGruppiAccrFini(int anaId) {

		List<AnagrAccrFini> anagraficaEntity = new ArrayList<>();
		try {
			// anagraficaEntity = (List<GruppoMonta>)
			// getSession(em).createSQLQuery("SELECT * FROM GRUPPO_MONTA GM
			// ORDER BY
			// GM.GMO_DATA_INSERIMENTO DESC").list();
			anagraficaEntity = (List<AnagrAccrFini>) getSession(em).createCriteria(AnagrAccrFini.class).add(Restrictions.eq("aafAnaId", anaId)).addOrder(Order.desc("aafDataEntrata")).list();
			// .add(Restrictions.eq("gmoAnaId",anaId)).setProjection(Projections.max("gmoDataInserimento")).uniqueResult();
			// .add(Restrictions.isNull("gmoDataUscita"))

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (anagraficaEntity == null || anagraficaEntity.size() == 0 || (anagraficaEntity != null && anagraficaEntity.size() > 0
				&& (anagraficaEntity.get(0).getAafDataUscita() != null && anagraficaEntity.get(0).getAafDataUscita().after(anagraficaEntity.get(0).getAafDataEntrata())))) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean getLastGruppoMonta(int anaId) {

		List<GruppoMonta> anagraficaEntity = new ArrayList<>();
		try {
			// anagraficaEntity = (List<GruppoMonta>)
			// getSession(em).createSQLQuery("SELECT * FROM GRUPPO_MONTA GM
			// ORDER BY
			// GM.GMO_DATA_INSERIMENTO DESC").list();
			anagraficaEntity = (List<GruppoMonta>) getSession(em).createCriteria(GruppoMonta.class).add(Restrictions.eq("gmoAnaId", anaId)).addOrder(Order.desc("gmoDataInserimento")).list();
			// .add(Restrictions.eq("gmoAnaId",anaId)).setProjection(Projections.max("gmoDataInserimento")).uniqueResult();
			// .add(Restrictions.isNull("gmoDataUscita"))
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (anagraficaEntity == null || anagraficaEntity.size() == 0 || (anagraficaEntity != null && anagraficaEntity.size() > 0
				&& (anagraficaEntity.get(0).getGmoDataUscita() != null && anagraficaEntity.get(0).getGmoDataUscita().after(anagraficaEntity.get(0).getGmoDataInserimento())))) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StoricoGruppiMontaDTO> getAllGruppiDiMontaOpen(int uteId) {
		List<StoricoGruppiMontaDTO> gruppiListDTO = new ArrayList<>();
		List<StoricoGruppiMonta> gruppiEntity = new ArrayList<>();
		StoricoGruppiMontaDTO dto;

		gruppiEntity = (List<StoricoGruppiMonta>) getSession(em).createCriteria(StoricoGruppiMonta.class).add(Restrictions.eq("sgmUteId", uteId)).add(Restrictions.isNull("sgmDataChiusura")).list();

		if (gruppiEntity != null && gruppiEntity.size() > 0) {
			for (StoricoGruppiMonta gm : gruppiEntity) {
				dto = new StoricoGruppiMontaDTO();
				dto = ConverterEntityToDto.storicoGruppiMontaEntityTostoricoGruppiMontaDto(gm);
				gruppiListDTO.add(dto);
			}
		}

		return gruppiListDTO;
	}

	@SuppressWarnings("unchecked")
	public List<StoricoGruppiMontaDTO> getAllGruppiDiMonta(int uteId) {
		List<StoricoGruppiMontaDTO> gruppiListDTO = new ArrayList<>();
		List<StoricoGruppiMonta> gruppiEntity = new ArrayList<>();
		StoricoGruppiMontaDTO dto;

		gruppiEntity = (List<StoricoGruppiMonta>) getSession(em).createCriteria(GruppoMonta.class, "gruppoMonta").createAlias("gruppoMonta.storicoGruppiMonta", "storicoGruppoMonta")
				.add(Restrictions.eq("storicoGruppoMonta.sgmUteId", uteId)).setProjection(Projections.projectionList().add(Projections.groupProperty("storicoGruppiMonta"))).list();

		if (gruppiEntity != null && gruppiEntity.size() > 0) {
			for (StoricoGruppiMonta gm : gruppiEntity) {
				dto = new StoricoGruppiMontaDTO();
				dto = ConverterEntityToDto.storicoGruppiMontaEntityTostoricoGruppiMontaDto(gm);
				gruppiListDTO.add(dto);
			}
		}

		return gruppiListDTO;
	}

	@SuppressWarnings("unchecked")
	public List<ValutazioneMaceDTO> getAllValutazioniMace(int uteId) {
		List<ValutazioneMaceDTO> valutazioniListDTO = new ArrayList<>();
		List<ValutazioneMace> valutazioniEntity = new ArrayList<>();
		ValutazioneMaceDTO dto;

		valutazioniEntity = (List<ValutazioneMace>) getSession(em).createCriteria(ValutazioneMace.class).add(Restrictions.eq("vmaUteId", uteId)).list();

		if (valutazioniEntity != null && valutazioniEntity.size() > 0) {
			for (ValutazioneMace entity : valutazioniEntity) {
				dto = new ValutazioneMaceDTO();
				dto = ConverterEntityToDto.valutazioneMaceEntityToValutazioneMaceDTO(entity);
				valutazioniListDTO.add(dto);
			}
		}

		return valutazioniListDTO;
	}

	@SuppressWarnings("unchecked")
	public List<AnagraficaDTO> getAllAnimaliUsciti(int uteId) {
		List<AnagraficaDTO> listDTO = new ArrayList<>();
		List<Anagrafica> list = new ArrayList<>();
		try {

			list = (List<Anagrafica>) getSession(em).createCriteria(Anagrafica.class).add(Restrictions.eq("anaUteId", uteId)).add(Restrictions.eq("anaUscitaCausa", causaUscita)).list();

			if (list != null && list.size() > 0) {
				AnagraficaDTO dto;
				for (Anagrafica ana : list) {
					dto = new AnagraficaDTO();
					dto = ConverterEntityToDto.anagraficaEntityToAngraficaDTO(ana);
					listDTO.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listDTO;
	}

	@SuppressWarnings("unchecked")
	public List<StoricoAccrescFiniDTO> getAllGruppiDiAccrescimentoOpen(int uteId) {
		List<StoricoAccrescFiniDTO> gruppiListDTO = new ArrayList<>();
		List<StoricoAccrescFini> gruppiEntity = new ArrayList<>();
		StoricoAccrescFiniDTO dto;

		gruppiEntity = (List<StoricoAccrescFini>) getSession(em).createCriteria(StoricoAccrescFini.class).add(Restrictions.eq("safUteId", uteId)).add(Restrictions.isNull("safDataFine")).list();

		if (gruppiEntity != null && gruppiEntity.size() > 0) {
			for (StoricoAccrescFini gm : gruppiEntity) {
				dto = new StoricoAccrescFiniDTO();
				dto = ConverterEntityToDto.storicoGruppiAccrEntityTostoricoGruppiAccrDto(gm);
				gruppiListDTO.add(dto);
			}
		}

		return gruppiListDTO;
	}

	@SuppressWarnings("unchecked")
	public List<StoricoAccrescFiniDTO> getAllGruppiDiAccrescimento(int uteId) {
		List<StoricoAccrescFiniDTO> gruppiListDTO = new ArrayList<>();
		List<StoricoAccrescFini> gruppiEntity = new ArrayList<>();
		StoricoAccrescFiniDTO dto;

		gruppiEntity = (List<StoricoAccrescFini>) getSession(em).createCriteria(AnagrAccrFini.class, "gruppoAccr").createAlias("gruppoAccr.storicoAccrescFini", "storicoGruppoAccr")
				.add(Restrictions.eq("storicoGruppoAccr.safUteId", uteId)).setProjection(Projections.projectionList().add(Projections.groupProperty("storicoAccrescFini"))).list();

		if (gruppiEntity != null && gruppiEntity.size() > 0) {
			for (StoricoAccrescFini gm : gruppiEntity) {
				dto = new StoricoAccrescFiniDTO();
				dto = ConverterEntityToDto.storicoGruppiAccrEntityTostoricoGruppiAccrDto(gm);
				gruppiListDTO.add(dto);
			}
		}

		return gruppiListDTO;
	}

	public boolean saveNuovoGruppoMonta(StoricoGruppiMontaDTO nuovoStoricoGruppoMonta) {
		StoricoGruppiMonta entity;

		entity = ConverterDtoToEntity.storicoGruppiMontaDTOtoStoricoGruppiMontaEntity(nuovoStoricoGruppoMonta);
		try {
			getSession(em).saveOrUpdate(entity);
			getSession(em).flush();

			if (nuovoStoricoGruppoMonta.getGruppoMontas() != null && nuovoStoricoGruppoMonta.getGruppoMontas().size() > 0) {
				GruppoMonta gmEntity;
				for (GruppoMontaDTO gmo : nuovoStoricoGruppoMonta.getGruppoMontas()) {
					gmEntity = new GruppoMonta();
					gmEntity = ConverterDtoToEntity.gruppoMontaDTOtoGruppoMontaEntity(gmo);
					gmEntity.setGmoSgmId(entity.getSgmId());
					getSession(em).saveOrUpdate(gmEntity);
				}
			}
			getSession(em).flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<RazzaDTO> getAllRazze() {
		List<RazzaDTO> razzeList = new ArrayList<>();
		List<Razza> razzeListEntity = (List<Razza>) getSession(em).createCriteria(Razza.class).addOrder(Order.asc("razSigla")).list();
		for (Razza razza : razzeListEntity) {
			razzeList.add(ConverterEntityToDto.razzaEntityToRazzaDTO(razza));
		}

		return razzeList;
	}

	public boolean saveNuovoGruppoAccrescimentoEfinissaggio(StoricoAccrescFiniDTO nuovoStoricoAccrFini) {
		StoricoAccrescFini entity;

		entity = ConverterDtoToEntity.storicoGruppiAccrFiniDTOtoStoricoGruppiAccrFiniEntity(nuovoStoricoAccrFini);
		try {
			getSession(em).saveOrUpdate(entity);
			getSession(em).flush();

			if (nuovoStoricoAccrFini.getAnagrAccrFinis() != null && nuovoStoricoAccrFini.getAnagrAccrFinis().size() > 0) {
				AnagrAccrFini gAccrEntity;
				for (AnagrAccrFiniDTO aaf : nuovoStoricoAccrFini.getAnagrAccrFinis()) {
					gAccrEntity = new AnagrAccrFini();
					gAccrEntity = ConverterDtoToEntity.anagrAccrFiniDTOtoAnagrAccrFiniEntity(aaf);
					gAccrEntity.setAafSafId(entity.getSafId());
					getSession(em).saveOrUpdate(gAccrEntity);
				}
			}
			getSession(em).flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateGruppoMonta(StoricoGruppiMontaDTO sgmDTO) {
		try {
			StoricoGruppiMonta entity = ConverterDtoToEntity.storicoGruppiMontaDTOtoStoricoGruppiMontaEntity(sgmDTO);
			getSession(em).update(entity);
			getSession(em).flush();
			for (GruppoMontaDTO gm : sgmDTO.getGruppoMontas()) {
				System.out.println(entity.getSgmId() + "<----*******");
				gm.setGmoSgmId(entity.getSgmId());
				getSession(em).saveOrUpdate(ConverterDtoToEntity.gruppoMontaDTOtoGruppoMontaEntity(gm));
				getSession(em).flush();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateStoricoGruppoMonta(StoricoGruppiMontaDTO sgm, int gmId) {
		try {
			GruppoMonta entity = ConverterDtoToEntity.gruppoMontaDTOtoGruppoMontaEntity(sgm.getGruppoMontas().get(gmId));
			entity.setGmoSgmId(sgm.getSgmId());
			getSession(em).saveOrUpdate(entity);
			getSession(em).update(entity.getAnagrafica());
			getSession(em).flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateStoricoAccrFinis(StoricoAccrescFiniDTO sAccr, int accrId) {
		try {
			AnagrAccrFini entity = ConverterDtoToEntity.anagrAccrFiniDTOtoAnagrAccrFiniEntity(sAccr.getAnagrAccrFinis().get(accrId));
			entity.setAafSafId(sAccr.getSafId());
			getSession(em).saveOrUpdate(entity);
			getSession(em).update(entity.getAnagrafica());
			getSession(em).flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void updateStoricoAccrFiniss(StoricoAccrescFiniDTO sAccrFinis) {
		try {
			StoricoAccrescFini entity = ConverterDtoToEntity.storicoGruppiAccrFiniDTOtoStoricoGruppiAccrFiniEntity(sAccrFinis);
			getSession(em).update(entity);
			getSession(em).flush();
			System.out.println("SALVATO TUTTO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateStoricoGruppoMonta(StoricoGruppiMontaDTO sgm) {
		try {
			StoricoGruppiMonta entity = ConverterDtoToEntity.storicoGruppiMontaDTOtoStoricoGruppiMontaEntity(sgm);
			getSession(em).update(entity);
			getSession(em).flush();
			// for (GruppoMonta gm : entity.getGruppoMontas()) {
			// gm.setGmoSgmId(entity.getSgmId());
			// getSession(em).update(gm);
			// getSession(em).flush();
			// }
			// for (GruppoMonta gm : entity.getGruppoMontas()) {
			// boolean salvato
			// =updateStoricoGruppoMonta(sgm,sgm.getGruppoMontas().indexOf(gm));
			// }
			System.out.println("SALVATO TUTTO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ReportDTO> getAllReportDisponibili() {
		List<ReportDTO> list = new ArrayList<>();
		try {
			List<Report> listEntity = (List<Report>) getSession(em).createCriteria(Report.class).list();

			if (listEntity != null && listEntity.size() > 0) {
				ReportDTO r = new ReportDTO();
				for (Report report : listEntity) {
					r = ConverterEntityToDto.reportEntityToReportDto(report);
					list.add(r);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ReportResultDTO> getReportResultEta(String query) {
		List<ReportResultDTO> resultList = new ArrayList<>();
		try {

			javax.persistence.Query q = em.createNativeQuery(query);

			List<Object[]> list = q.getResultList();
			ReportResultDTO result;
			AnagraficaDTO anag;
			for (Object[] obj : list) {

				anag = new AnagraficaDTO();

				anag.setAnaNumMatricola(obj[0].toString());
				anag.setAnaNumMatricolaMadre(obj[1].toString());
				anag.setAnaNumMatricolaPadre(obj[2].toString());

				result = new ReportResultDTO();

				result.setAnag(anag);
				result.setMesiEta(new Integer(obj[3].toString()));

				resultList.add(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<ReportResultDTO> getReportResultPerc(String query) throws Exception {
		List<ReportResultDTO> resultList = new ArrayList<>();

		javax.persistence.Query q = em.createNativeQuery(query);

		List<Object[]> list = q.getResultList();
		ReportResultDTO result;
		AnagraficaDTO anag;
		for (Object[] obj : list) {

			anag = new AnagraficaDTO();

			anag.setAnaNumMatricola(obj[0].toString());
			anag.setAnaNumMatricolaMadre(obj[1].toString());
			anag.setAnaNumMatricolaPadre(obj[2].toString());

			result = new ReportResultDTO();

			result.setAnag(anag);
			result.setIndice(new Double(obj[3].toString()));

			resultList.add(result);
		}

		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<ReportResultDTO> getReportResultPrimaParte(String query) {

		List<ReportResultDTO> resultList = new ArrayList<>();
		try {

			javax.persistence.Query q = em.createNativeQuery(query);

			List<Object[]> list = q.getResultList();
			ReportResultDTO result;
			AnagraficaDTO anag;
			for (Object[] obj : list) {

				result = new ReportResultDTO();

				result.setNumParti(new Integer(obj[1].toString()));

				anag = new AnagraficaDTO();

				anag.setAnaNumMatricola(obj[0].toString());
				anag.setAnaNumMatricolaMadre(obj[2].toString());
				anag.setAnaNumMatricolaPadre(obj[3].toString());

				result.setAnag(anag);

				resultList.add(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<ReportResultDTO> getReportResultClassSeurop(String query) throws Exception {
		List<ReportResultDTO> resultList = new ArrayList<>();

		javax.persistence.Query q = em.createNativeQuery(query);

		List<Object[]> list = q.getResultList();
		ReportResultDTO result;
		AnagraficaDTO anag;
		for (Object[] obj : list) {

			anag = new AnagraficaDTO();

			anag.setAnaNumMatricola(obj[0].toString());
			anag.setAnaNumMatricolaMadre(obj[1].toString());
			anag.setAnaNumMatricolaPadre(obj[2].toString());

			result = new ReportResultDTO();

			result.setAnag(anag);
			result.setSeuropString(obj[3].toString());

			resultList.add(result);
		}

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<ReportResultDTO> getReportResultIndiceSeurop(String query) throws Exception {
		List<ReportResultDTO> resultList = new ArrayList<>();

		javax.persistence.Query q = em.createNativeQuery(query);

		List<Object[]> list = q.getResultList();
		ReportResultDTO result;
		AnagraficaDTO anag;
		for (Object[] obj : list) {

			anag = new AnagraficaDTO();

			anag.setAnaNumMatricola(obj[0].toString());
			anag.setAnaNumMatricolaMadre(obj[1].toString());
			anag.setAnaNumMatricolaPadre(obj[2].toString());

			result = new ReportResultDTO();

			result.setAnag(anag);
			result.setSeuropIndice(new Integer(obj[3].toString()));

			resultList.add(result);
		}

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<ReportResultDTO> getReportResultNoVal(String query) {
		List<ReportResultDTO> resultList = new ArrayList<>();
		try {

			javax.persistence.Query q = em.createNativeQuery(query);

			List<Object[]> list = q.getResultList();
			ReportResultDTO result;
			AnagraficaDTO anag;
			for (Object[] obj : list) {

				anag = new AnagraficaDTO();

				anag.setAnaNumMatricola(obj[0].toString());
				anag.setAnaNumMatricolaMadre(obj[1].toString());
				anag.setAnaNumMatricolaPadre(obj[2].toString());

				result = new ReportResultDTO();

				result.setAnag(anag);

				resultList.add(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<AnagraficaDTO> getAnimaliDaMatMadre(String anaNumMatricola, int uteId) {
		List<AnagraficaDTO> listaParti = new ArrayList<>();
		try {

			List<Anagrafica> list = (List<Anagrafica>) getSession(em).createCriteria(Anagrafica.class).add(Restrictions.eq("anaNumMatricolaMadre", anaNumMatricola))
					.add(Restrictions.eq("anaUteId", uteId)).addOrder(Order.asc("anaDataNascita")).list();

			AnagraficaDTO dto;
			for (Anagrafica anagrafica : list) {
				dto = new AnagraficaDTO();
				dto = ConverterEntityToDto.anagraficaEntityToAngraficaDTO(anagrafica);
				listaParti.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaParti;
	}

	@SuppressWarnings("unchecked")
	public List<AnagraficaDTO> getAllMatricoleDisponibili(int uteRifId, String queryParams) {
		List<AnagraficaDTO> listaAnimaliDisp = new ArrayList<>();
		try {
			// String query = animaliDisponibiliQueryWithParams.replace("{0}",
			// new Integer(uteRifId).toString())
			// .replace("{1}", new Integer(uteRifId).toString()).replace("{2}",
			// new Integer(uteRifId).toString())
			// .replace("{3}", queryParams);

			String query = animaliDisponibiliQueryWithParams.replace("{0}", new Integer(uteRifId).toString()).replace("{1}", queryParams.toUpperCase());

			List<Anagrafica> list = (List<Anagrafica>) getSession(em).createSQLQuery(query).addEntity(Anagrafica.class).list();

			AnagraficaDTO dto;
			for (Anagrafica anagrafica : list) {
				dto = new AnagraficaDTO();
				dto = ConverterEntityToDto.anagraficaEntityToAngraficaDTO(anagrafica);
				listaAnimaliDisp.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAnimaliDisp;

	}

	@SuppressWarnings("unchecked")
	public List<AnagraficaDTO> getAllMatricoleDisponibiliFemale(int uteRifId, String queryParams) {
		List<AnagraficaDTO> listaAnimaliDisp = new ArrayList<>();
		try {

			String query = animaliDisponibiliFemaleQueryWithParams.replace("{0}", new Integer(uteRifId).toString()).replace("{1}", queryParams.toUpperCase());

			List<Anagrafica> list = (List<Anagrafica>) getSession(em).createSQLQuery(query).addEntity(Anagrafica.class).list();

			AnagraficaDTO dto;
			for (Anagrafica anagrafica : list) {
				dto = new AnagraficaDTO();
				dto = ConverterEntityToDto.anagraficaEntityToAngraficaDTO(anagrafica);
				listaAnimaliDisp.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAnimaliDisp;

	}

	public BigInteger countAllAnagrafica(int uteRifId) {

		String query = countRowInAnagraficaFromUte.replace("{0}", new Integer(uteRifId).toString());

		return (BigInteger) getSession(em).createSQLQuery(query).uniqueResult();
	}

	public List<AnagraficaDTO> getAllAnimaliAnagraficaFiltered(int uteRifId, int first, int pageSize, String sortOrderToStr, Map<String, Object> filters, String sortField) {

		List<Anagrafica> listaAnimaliFiltered = new ArrayList<>();
		List<AnagraficaDTO> listaAnimaliDone = new ArrayList<>();

		try {
			Criteria crit = getSession(em).createCriteria(Anagrafica.class);
			crit.add(Restrictions.eq("anaUteId", uteRifId));

			for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
				String filterProperty = it.next();
				if (filterProperty != null && !filterProperty.equals("")) {
					if (filters.get(filterProperty) != null && !filters.get(filterProperty).toString().isEmpty())
						crit.add(Restrictions.ilike(filterProperty, filters.get(filterProperty).toString(), MatchMode.ANYWHERE));
				}
			}

			// ordering query
			if (sortField != null && !sortField.isEmpty()) {
				if (sortOrderToStr != null && !sortOrderToStr.isEmpty()) {
					if (sortOrderToStr.equals("desc")) {
						crit.addOrder(Order.desc(sortField));
					} else {

						crit.addOrder(Order.asc(sortField));
					}
				}
			}

			// limit query
			crit.setFirstResult(first);
			crit.setMaxResults(pageSize);

			listaAnimaliFiltered = (List<Anagrafica>) crit.list();

			AnagraficaDTO dto;
			for (Anagrafica anagrafica : listaAnimaliFiltered) {
				dto = new AnagraficaDTO();
				dto = ConverterEntityToDto.anagraficaEntityToAngraficaDTO(anagrafica);
				listaAnimaliDone.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAnimaliDone;

	}

	public int countAllAnagraficaFiltered(int uteRifId, Map<String, Object> filters) {

		Criteria crit = getSession(em).createCriteria(Anagrafica.class);
		crit.add(Restrictions.eq("anaUteId", uteRifId));
		for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
			String filterProperty = it.next();
			if (filterProperty != null && !filterProperty.equals("")) {
				if (filters.get(filterProperty) != null && !filters.get(filterProperty).toString().isEmpty())
					crit.add(Restrictions.ilike(filterProperty, filters.get(filterProperty).toString(), MatchMode.ANYWHERE));
			}
		}

		crit.setProjection(Projections.rowCount());

		Long l = (Long) crit.uniqueResult();

		return Integer.parseInt(l.toString());

	}

	public boolean updateLastAccess(UtenteDTO utente) {
		Utente ute = new Utente();

		ute = ConverterDtoToEntity.utenteDTOToUtente(utente);
		ute.setUteUltimoAcc(new Timestamp(System.currentTimeMillis()));

		try {
			getSession(em).update(ute);
			getSession(em).flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<AnagraficaDTO> getAllToriDisponibili(int uteRifId) {

		Criteria crit = getSession(em).createCriteria(Anagrafica.class);
		crit.add(Restrictions.eq("anaUteId", uteRifId)).add(Restrictions.eq("anaSesso", "M")).add(Restrictions.eq("anaFlagDisponibile", "1"));
		return ConverterEntityToDto.anagraficaListEntityToAnagraficaListDto((List<Anagrafica>) crit.list());

	}

}
