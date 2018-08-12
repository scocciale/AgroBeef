package com.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dto.AnagrAccrFiniDTO;
import com.dto.AnagraficaDTO;
import com.dto.GruppoMontaDTO;
import com.dto.PesataDTO;
import com.dto.ProfiloDTO;
import com.dto.RazzaDTO;
import com.dto.ReportDTO;
import com.dto.StoricoAccrescFiniDTO;
import com.dto.StoricoGruppiMontaDTO;
import com.dto.UtenteDTO;
import com.dto.ValutazioneMaceDTO;
import com.dto.VeterinariaDTO;
import com.entities.AnagrAccrFini;
import com.entities.Anagrafica;
import com.entities.GruppoMonta;
import com.entities.Pesata;
import com.entities.Profilo;
import com.entities.Razza;
import com.entities.Report;
import com.entities.StoricoAccrescFini;
import com.entities.StoricoGruppiMonta;
import com.entities.Utente;
import com.entities.ValutazioneMace;
import com.entities.Veterinaria;

public class ConverterEntityToDto {

	public static AnagraficaDTO anagraficaEntityToAngraficaDTO(Anagrafica anagrafica) {
		AnagraficaDTO anagraficaDTO = new AnagraficaDTO();

		anagraficaDTO.setAnaId(anagrafica.getAnaId());
		anagraficaDTO.setAnaFlagToro(anagrafica.getAnaFlagToro());
		anagraficaDTO.setAnaNumMatricola(anagrafica.getAnaNumMatricola());
		anagraficaDTO.setAnaNumParto(anagrafica.getAnaNumParto());
		anagraficaDTO.setAnaSesso(anagrafica.getAnaSesso());
		anagraficaDTO.setUtente(utenteEntityToUtenteDTO(anagrafica.getUtente()));
		anagraficaDTO.setAnaUteId(anagrafica.getUtente().getUteId());
		if (anagrafica.getAnaFlagDisponibile() != null)
			anagraficaDTO.setAnaFlagDisponibile(anagrafica.getAnaFlagDisponibile());
		if (anagrafica.getAnaDataAcquisto() != null)
			anagraficaDTO.setAnaDataAcquisto(anagrafica.getAnaDataAcquisto());
		if (anagrafica.getAnaDifficoltaParto() != null)
			anagraficaDTO.setAnaDifficoltaParto(anagrafica.getAnaDifficoltaParto());
		if (anagrafica.getAnaDataNascita() != null)
			anagraficaDTO.setAnaDataNascita(anagrafica.getAnaDataNascita());
		if (anagrafica.getAnaFlagGemello() != null)
			anagraficaDTO.setAnaFlagGemello(anagrafica.getAnaFlagGemello());

		if (anagrafica.getPesatas() != null && anagrafica.getPesatas().size() > 0) {
			List<PesataDTO> list = new ArrayList<>();
			PesataDTO pes = new PesataDTO();

			// creo on the fly un comparator custom per ordinare la mia lista in
			// base alle date
			Collections.sort(anagrafica.getPesatas(), new Comparator<Pesata>() {
				public int compare(Pesata p1, Pesata p2) {
					// ordina dalla più vecchia alla più recente
					return p1.getPesData().compareTo(p2.getPesData());
				}
			});

			for (Pesata obj : anagrafica.getPesatas()) {
				pes = new PesataDTO();
				pes = pesataEntityToPesataDTO(obj);
				if (anagrafica.getPesatas().indexOf(obj) > 0) {
					pes.setDeltaPeso(pes.getPesPeso()
							- anagrafica.getPesatas().get((anagrafica.getPesatas().indexOf(obj) - 1)).getPesPeso());
				}
				list.add(pes);
			}

			// if (list != null && list.size() > 0) {
			// for (PesataDTO pes1 : list) {
			// System.out.println("pes.setDeltaPeso: " + pes1.getDeltaPeso()
			// + " ||||| pes.getPesPeso() -
			// anagrafica.getPesatas().get((anagrafica.getPesatas().indexOf(obj)
			// - 1)).getPesPeso(): "
			// + (pes1.getPesPeso() -
			// list.get((anagrafica.getPesatas().indexOf(pes1) -
			// 1)).getPesPeso()));
			// }
			// }
			anagraficaDTO.setPesatas(list);

		}
		if (anagrafica.getVeterinarias() != null && anagrafica.getVeterinarias().size() > 0) {
			List<VeterinariaDTO> list = new ArrayList<>();
			for (Veterinaria obj : anagrafica.getVeterinarias()) {
				list.add(veterinariaEntityToVeterinariaDTO(obj));
			}
			anagraficaDTO.setVeterinarias(list);
		}

		if (anagrafica.getAnaNumMatricolaMadre() != null)
			anagraficaDTO.setAnaNumMatricolaMadre(anagrafica.getAnaNumMatricolaMadre());
		if (anagrafica.getAnaNumMatricolaPadre() != null)
			anagraficaDTO.setAnaNumMatricolaPadre(anagrafica.getAnaNumMatricolaPadre());
		if (anagrafica.getAnaRazza() != null)
			anagraficaDTO.setAnaRazza(anagrafica.getAnaRazza());
		if (anagrafica.getAnaDataUscita() != null)
			anagraficaDTO.setAnaDataUscita(anagrafica.getAnaDataUscita());
		if (anagrafica.getAnaUscitaCausa() != null)
			anagraficaDTO.setAnaUscitaCausa(anagrafica.getAnaUscitaCausa());

		if (anagrafica.getRazza() != null) {
			anagraficaDTO.setRazza(razzaEntityToRazzaDTO(anagrafica.getRazza()));
			anagraficaDTO.setAnaRazId(anagrafica.getRazza().getRazId());
		}
		// if (anagrafica.getValutazioneMaces() != null &&
		// anagrafica.getValutazioneMaces().size() > 0) {
		// List<ValutazioneMaceDTO> list = new ArrayList<>();
		// for (ValutazioneMace obj : anagrafica.getValutazioneMaces()) {
		// list.add(valutazioneMaceEntityToValutazioneMaceDTO(obj));
		// }
		// anagraficaDTO.setValutazioneMaces(list);
		// }

		// if (anagrafica.getGruppoMontas() != null &&
		// anagrafica.getGruppoMontas().size() > 0) {
		// System.out.println("------------------------------------------------");
		// List<GruppoMontaDTO> list = new ArrayList<>();
		// for (GruppoMonta obj : anagrafica.getGruppoMontas()) {
		// list.add(gruppoMontaEntityToGruppoMontaDTO(obj));
		// }
		// }
		// if (anagrafica.getAnagrAccrFinis() != null &&
		// anagrafica.getAnagrAccrFinis().size() > 0) {
		// List<AnagrAccrFiniDTO> list1 = new ArrayList<>();
		// for (AnagrAccrFini obj : anagrafica.getAnagrAccrFinis()) {
		// list1.add(anagrAccrFiniEntityToAnagrAccrFiniDTO(obj));
		// }
		// anagraficaDTO.setAnagrAccrFinis(list1);
		// }
		return anagraficaDTO;

	}

	public static RazzaDTO razzaEntityToRazzaDTO(Razza ra) {
		RazzaDTO raDTO = new RazzaDTO();

		if (ra.getRazDescrizione() != null)
			raDTO.setRazDescrizione(ra.getRazDescrizione());
		if (ra.getRazCodAia() != null)
			raDTO.setRazCodAia(ra.getRazCodAia());
		raDTO.setRazId(ra.getRazId());
		raDTO.setRazSigla(ra.getRazSigla());

		return raDTO;
	}

	public static VeterinariaDTO veterinariaEntityToVeterinariaDTO(Veterinaria vet) {
		VeterinariaDTO vetDTO = new VeterinariaDTO();

		if (vet.getVetId() != 0)
			vetDTO.setVetId(vet.getVetId());
		if (vet.getVetDataVisita() != null)
			vetDTO.setVetDataVisita(vet.getVetDataVisita());
		if (vet.getVetAnaId() != 0)
			vetDTO.setVetAnaId(vet.getVetAnaId());
		if (vet.getVetDataDiagnosiGrav() != null)
			vetDTO.setVetDataDiagnosiGrav(vet.getVetDataDiagnosiGrav());
		if (vet.getVetEsitoGrav() != null)
			vetDTO.setVetEsitoGrav(vet.getVetEsitoGrav());
		if (vet.getVetFarmaciUtilizzati() != null)
			vetDTO.setVetFarmaciUtilizzati(vet.getVetFarmaciUtilizzati());
		if (vet.getVetMotivoVisita() != null)
			vetDTO.setVetMotivoVisita(vet.getVetMotivoVisita());

		return vetDTO;
	}

	public static ValutazioneMaceDTO valutazioneMaceEntityToValutazioneMaceDTO(ValutazioneMace vma) {
		ValutazioneMaceDTO vmaDTO = new ValutazioneMaceDTO();

		vmaDTO.setVmaId(vma.getVmaId());
		vmaDTO.setVmaUteId(vma.getVmaUteId());
		vmaDTO.setVmaAnaId(vma.getAnagrafica().getAnaId());
		vmaDTO.setAnagrafica(anagraficaEntityToAngraficaDTO(vma.getAnagrafica()));
		if (vma.getVmaClassSeurop() != null) {
			vmaDTO.setVmaClassSeurop(vma.getVmaClassSeurop());
		}
		if (vma.getVmaGrassoSeurop() != null) {
			vmaDTO.setVmaGrassoSeurop(vma.getVmaGrassoSeurop());
		}
		if (vma.getVmaData() != null) {
			vmaDTO.setVmaData(vma.getVmaData());
		}
		if (vma.getVmaPesoCarcassa() != 0) {
			vmaDTO.setVmaPesoCarcassa(vma.getVmaPesoCarcassa());
		}
		if (vma.getVmaPesoMacellazione() != 0) {
			vmaDTO.setVmaPesoMacellazione(vma.getVmaPesoMacellazione());
		}
		if (vma.getVmaPesoTaglioTot() != 0) {
			vmaDTO.setVmaPesoTaglioTot(vma.getVmaPesoTaglioTot());
		}

		return vmaDTO;
	}

	public static UtenteDTO utenteEntityToUtenteDTO(Utente ute) {
		UtenteDTO uteDTO = new UtenteDTO();

		uteDTO.setUteId(ute.getUteId());
		uteDTO.setUtePwd(ute.getUtePwd());
		uteDTO.setUteUsername(ute.getUteUsername());
		uteDTO.setUteRifId(ute.getUteRifId());
		uteDTO.setUteUltimoAcc(ute.getUteUltimoAcc());
		if (ute.getUteCognome() != null)
			uteDTO.setUteCognome(ute.getUteCognome());
		if (ute.getUteNome() != null)
			uteDTO.setUteNome(ute.getUteNome());
		uteDTO.setProfilo(profiloEntityToProfiloDTO(ute.getProfilo()));

		return uteDTO;
	}

	public static ProfiloDTO profiloEntityToProfiloDTO(Profilo pro) {
		ProfiloDTO proDTO = new ProfiloDTO();

		proDTO.setProDescrizione(pro.getProDescrizione());
		proDTO.setProId(pro.getProId());
		proDTO.setProNome(pro.getProNome());

		return proDTO;
	}

	public static AnagrAccrFiniDTO anagrAccrFiniEntityToAnagrAccrFiniDTO(AnagrAccrFini aaf) {
		AnagrAccrFiniDTO aafDTO = new AnagrAccrFiniDTO();

		if (aaf.getAafId() != 0)
			aafDTO.setAafId(aaf.getAafId());
		if (aaf.getAafAnaId() != 0)
			aafDTO.setAafAnaId(aaf.getAafAnaId());
		if (aaf.getAafDataEntrata() != null)
			aafDTO.setAafDataEntrata(aaf.getAafDataEntrata());
		if (aaf.getAafDataUscita() != null)
			aafDTO.setAafDataUscita(aaf.getAafDataUscita());
		if (aaf.getAafDestinazioneUscita() != null)
			aafDTO.setAafDestinazioneUscita(aaf.getAafDestinazioneUscita());
		// if (gmo.getStoricoGruppiMonta() != null)
		// gmoDTO.setStoricoGruppiMonta(storicoGruppiMontaEntityTostoricoGruppiMontaDto(gmo.getStoricoGruppiMonta()));
		if (aaf.getAafAnaId() != 0)
			aafDTO.setAafAnaId(aaf.getAafAnaId());
		if (aaf.getAnagrafica() != null)
			aafDTO.setAnagrafica(anagraficaEntityToAngraficaDTO(aaf.getAnagrafica()));

		return aafDTO;
	}

	public static GruppoMontaDTO gruppoMontaEntityToGruppoMontaDTO(GruppoMonta gmo) {
		GruppoMontaDTO gmoDTO = new GruppoMontaDTO();

		if (gmo.getGmoId() != 0)
			gmoDTO.setGmoId(gmo.getGmoId());
		if (gmo.getGmoAnaId() != 0)
			gmoDTO.setGmoAnaId(gmo.getGmoAnaId());
		if (gmo.getGmoDataInserimento() != null)
			gmoDTO.setGmoDataInserimento(gmo.getGmoDataInserimento());
		if (gmo.getGmoDataUscita() != null)
			gmoDTO.setGmoDataUscita(gmo.getGmoDataUscita());
		if (gmo.getGmoDestinazioneUscita() != null)
			gmoDTO.setGmoDestinazioneUscita(gmo.getGmoDestinazioneUscita());
		// if (gmo.getStoricoGruppiMonta() != null)
		// gmoDTO.setStoricoGruppiMonta(storicoGruppiMontaEntityTostoricoGruppiMontaDto(gmo.getStoricoGruppiMonta()));
		if (gmo.getGmoSgmId() != 0)
			gmoDTO.setGmoSgmId(gmo.getGmoSgmId());
		if (gmo.getAnagrafica() != null)
			gmoDTO.setAnagrafica(anagraficaEntityToAngraficaDTO(gmo.getAnagrafica()));

		return gmoDTO;
	}

	public static PesataDTO pesataEntityToPesataDTO(Pesata pes) {
		PesataDTO pesDTO = new PesataDTO();

		if (pes.getPesAnaId() != 0)
			pesDTO.setPesAnaId(pes.getPesAnaId());
		if (pes.getPesData() != null)
			pesDTO.setPesData(pes.getPesData());
		if (pes.getPesFase() != null)
			pesDTO.setPesFase(pes.getPesFase());
		if (pes.getPesPeso() != 0)
			pesDTO.setPesPeso(new Double(pes.getPesPeso()));
		if (pes.getPesId() != 0)
			pesDTO.setPesId(pes.getPesId());

		return pesDTO;
	}

	public static StoricoAccrescFiniDTO storicoGruppiAccrEntityTostoricoGruppiAccrDto(StoricoAccrescFini sga) {
		StoricoAccrescFiniDTO dto = new StoricoAccrescFiniDTO();

		if (sga.getSafDataInizio() != null)
			dto.setSafDataInizio(sga.getSafDataInizio());
		if (sga.getSafDataFine() != null)
			dto.setSafDataFine(sga.getSafDataFine());
		if (sga.getSafNumAppezzamento() != null)
			dto.setSafNumAppezzamento(sga.getSafNumAppezzamento());
		if (sga.getSafUteId() != 0)
			dto.setSafUteId(sga.getSafUteId());
		if (sga.getSafId() != 0)
			dto.setSafId(sga.getSafId());
		if (sga.getSafScopo() != null)
			dto.setSafScopo(sga.getSafScopo());
		if (sga.getAnagrAccrFinis() != null && sga.getAnagrAccrFinis().size() > 0) {
			List<AnagrAccrFiniDTO> accrDTOList = new ArrayList<>();
			for (AnagrAccrFini acc : sga.getAnagrAccrFinis()) {
				if (acc.getAafDataUscita() == null || acc.getAafDataUscita().before(acc.getAafDataEntrata())) {
					AnagrAccrFiniDTO accDTO = new AnagrAccrFiniDTO();
					accDTO = anagrAccrFiniEntityToAnagrAccrFiniDTO(acc);

					accrDTOList.add(accDTO);
				}
			}
			dto.setAnagrAccrFinis(accrDTOList);
		}

		return dto;
	}

	public static StoricoGruppiMontaDTO storicoGruppiMontaEntityTostoricoGruppiMontaDto(StoricoGruppiMonta sgdm) {
		StoricoGruppiMontaDTO dto = new StoricoGruppiMontaDTO();

		if (sgdm.getSgmDataApertura() != null)
			dto.setSgmDataApertura(sgdm.getSgmDataApertura());
		if (sgdm.getSgmDataChiusura() != null)
			dto.setSgmDataChiusura(sgdm.getSgmDataChiusura());
		if (sgdm.getSgmNome() != null)
			dto.setSgmNome(sgdm.getSgmNome());
		if (sgdm.getSgmUteId() != 0)
			dto.setSgmUteId(sgdm.getSgmUteId());
		if (sgdm.getSgmId() != 0)
			dto.setSgmId(sgdm.getSgmId());
		if (sgdm.getGruppoMontas() != null && sgdm.getGruppoMontas().size() > 0) {
			List<GruppoMontaDTO> gmoDTOList = new ArrayList<>();
			for (GruppoMonta gm : sgdm.getGruppoMontas()) {
				if (gm.getGmoDataUscita() == null || gm.getGmoDataUscita().before(gm.getGmoDataInserimento())) {
					GruppoMontaDTO gmoDTO = new GruppoMontaDTO();
					gmoDTO = gruppoMontaEntityToGruppoMontaDTO(gm);

					gmoDTOList.add(gmoDTO);
				}
			}
			dto.setGruppoMontas(gmoDTOList);
		}

		return dto;
	}

	public static ReportDTO reportEntityToReportDto(Report rep) {
		ReportDTO r = new ReportDTO();

		r.setRepId(rep.getRepId());
		r.setRepNome(rep.getRepNome());
		r.setRepDescrizione(rep.getRepDescrizione());
		r.setRepQuery(rep.getRepQuery());

		return r;
	}

}
