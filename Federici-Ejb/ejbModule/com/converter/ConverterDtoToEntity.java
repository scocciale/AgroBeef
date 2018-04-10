package com.converter;

import java.util.ArrayList;
import java.util.List;

import com.dto.AnagrAccrFiniDTO;
import com.dto.AnagraficaDTO;
import com.dto.GruppoMontaDTO;
import com.dto.PesataDTO;
import com.dto.ProfiloDTO;
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
import com.entities.StoricoAccrescFini;
import com.entities.StoricoGruppiMonta;
import com.entities.Utente;
import com.entities.ValutazioneMace;
import com.entities.Veterinaria;

public class ConverterDtoToEntity {

	public static Anagrafica anagraficaDTOToAngrafica(AnagraficaDTO anagDTO) {
		Anagrafica anag = new Anagrafica();

		if (anagDTO.getAnaId() != 0)
			anag.setAnaId(anagDTO.getAnaId());
		anag.setAnaFlagToro(anagDTO.getAnaFlagToro());
		anag.setAnaNumMatricola(anagDTO.getAnaNumMatricola());
		anag.setAnaNumParto(anagDTO.getAnaNumParto());
		anag.setAnaSesso(anagDTO.getAnaSesso());
		if (anagDTO.getAnaFlagDisponibile() != null)
			anag.setAnaFlagDisponibile(anagDTO.getAnaFlagDisponibile());
		if (anagDTO.getAnaUteId() != 0)
			anag.setAnaUteId(anagDTO.getAnaUteId());
		if (anagDTO.getUtente() != null)
			anag.setUtente(utenteDTOToUtente(anagDTO.getUtente()));
		if (anagDTO.getAnaDataAcquisto() != null)
			anag.setAnaDataAcquisto(anagDTO.getAnaDataAcquisto());
		if (anagDTO.getAnaDifficoltaParto() != null)
			anag.setAnaDifficoltaParto(anagDTO.getAnaDifficoltaParto());
		if (anagDTO.getAnaDataNascita() != null)
			anag.setAnaDataNascita(anagDTO.getAnaDataNascita());
		if (anagDTO.getAnaFlagGemello() != null)
			anag.setAnaFlagGemello(anagDTO.getAnaFlagGemello());
		if (anagDTO.getAnaNumMatricolaMadre() != null)
			anag.setAnaNumMatricolaMadre(anagDTO.getAnaNumMatricolaMadre());
		if (anagDTO.getAnaNumMatricolaPadre() != null)
			anag.setAnaNumMatricolaPadre(anagDTO.getAnaNumMatricolaPadre());
		if (anagDTO.getAnaRazId() != 0) {
			// anag.setAnaRazza(anagDTO.getAnaRazza());
			anag.setAnaRazId(anagDTO.getAnaRazId());
		}
		if (anagDTO.getAnaRazza() != null)
			anag.setAnaRazza(anagDTO.getAnaRazza());
		if (anagDTO.getAnaDataUscita() != null)
			anag.setAnaDataUscita(anagDTO.getAnaDataUscita());
		if (anagDTO.getAnaUscitaCausa() != null)
			anag.setAnaUscitaCausa(anagDTO.getAnaUscitaCausa());
		// if (anagrafica.getAnagrAccrFinis() != null &&
		// anagrafica.getAnagrAccrFinis().size() > 0) {
		// List<AnagrAccrFiniDTO> list = new ArrayList<>();
		// for (AnagrAccrFini obj : anagrafica.getAnagrAccrFinis()) {
		// list.add(anagrAccrFiniEntityToAnagrAccrFiniDTO(obj));
		// }
		// anagraficaDTO.setAnagrAccrFinis(list);
		// }
		// if (anagrafica.getPesatas() != null && anagrafica.getPesatas().size()
		// > 0) {
		// List<PesataDTO> list = new ArrayList<>();
		// for (Pesata obj : anagrafica.getPesatas()) {
		// list.add(pesataEntityToPesataDTO(obj));
		// }
		// anagraficaDTO.setPesatas(list);
		// }
		// if (anagrafica.getGruppoMontas() != null &&
		// anagrafica.getGruppoMontas().size() > 0) {
		// List<GruppoMontaDTO> list = new ArrayList<>();
		// for (GruppoMonta obj : anagrafica.getGruppoMontas()) {
		// list.add(gruppoMontaEntityToGruppoMontaDTO(obj));
		// }
		// anagraficaDTO.setGruppoMontas(list);
		// }
		// if (anagrafica.getVeterinarias() != null &&
		// anagrafica.getVeterinarias().size() > 0) {
		// List<VeterinariaDTO> list = new ArrayList<>();
		// for (Veterinaria obj : anagrafica.getVeterinarias()) {
		// list.add(veterinariaEntityToVeterinariaDTO(obj));
		// }
		// }
		// if (anagDTO.getValutazioneMaces() != null &&
		// anagDTO.getValutazioneMaces().size() > 0) {
		// List<ValutazioneMaceDTO> list = new ArrayList<>();
		// for (ValutazioneMace obj : anagDTO.getValutazioneMaces()) {
		// list.add(valutazioneMaceEntityToValutazioneMaceDTO(obj));
		// }
		// }

		return anag;

	}

	// TODO
	public static Veterinaria veterinariaDtoToVeterinariaEntity(VeterinariaDTO vet) {
		Veterinaria vetEntity = new Veterinaria();

		if (vet.getVetAnaId() != 0)
			vetEntity.setVetAnaId(vet.getVetAnaId());
		if (vet.getVetDataDiagnosiGrav() != null)
			vetEntity.setVetDataDiagnosiGrav(vet.getVetDataDiagnosiGrav());
		if (vet.getVetDataVisita() != null)
			vetEntity.setVetDataVisita(vet.getVetDataVisita());
		if (vet.getVetEsitoGrav() != null)
			vetEntity.setVetEsitoGrav(vet.getVetEsitoGrav());
		if (vet.getVetFarmaciUtilizzati() != null)
			vetEntity.setVetFarmaciUtilizzati(vet.getVetFarmaciUtilizzati());
		if (vet.getVetMotivoVisita() != null)
			vetEntity.setVetMotivoVisita(vet.getVetMotivoVisita());
		if (vet.getVetId() != 0)
			vetEntity.setVetId(vet.getVetId());
		System.out.println("convertito vet");
		return vetEntity;
	}

	// TODO
	public static ValutazioneMace valutazioneMaceEntityToValutazioneMaceDTO(ValutazioneMaceDTO nv) {
		ValutazioneMace vma = new ValutazioneMace();

		if (nv.getVmaId() != 0)
			vma.setVmaId(nv.getVmaId());
		if (nv.getVmaPesoCarcassa() != 0)
			vma.setVmaPesoCarcassa(nv.getVmaPesoCarcassa());
		if (nv.getVmaPesoMacellazione() != 0)
			vma.setVmaPesoMacellazione(nv.getVmaPesoMacellazione());
		if (nv.getVmaPesoTaglioTot() != 0)
			vma.setVmaPesoTaglioTot(nv.getVmaPesoTaglioTot());
		if (nv.getVmaUteId() != 0)
			vma.setVmaUteId(nv.getVmaUteId());
		if (nv.getVmaGrassoSeurop() != null && !nv.getVmaGrassoSeurop().equals(""))
			vma.setVmaGrassoSeurop(nv.getVmaGrassoSeurop());
		if (nv.getVmaClassSeurop() != null && !nv.getVmaClassSeurop().equals(""))
			vma.setVmaClassSeurop(nv.getVmaClassSeurop());
		if (nv.getVmaData() != null)
			vma.setVmaData(nv.getVmaData());
		if (nv.getAnagrafica() != null) {
			vma.setAnagrafica(anagraficaDTOToAngrafica(nv.getAnagrafica()));
			vma.setVmaAnaId(nv.getAnagrafica().getAnaId());
		}

		return vma;
	}

	// TODO
	public static Utente utenteDTOToUtente(UtenteDTO uteDTO) {
		Utente ute = new Utente();

		ute.setUteId(uteDTO.getUteId());
		ute.setUtePwd(uteDTO.getUtePwd());
		ute.setUteUsername(uteDTO.getUteUsername());
		ute.setUteUltimoAcc(uteDTO.getUteUltimoAcc());
		if (uteDTO.getUteCognome() != null)
			ute.setUteCognome(uteDTO.getUteCognome());
		if (uteDTO.getUteNome() != null)
			ute.setUteNome(uteDTO.getUteNome());
		ute.setProfilo(profiloDTOToProfilo(uteDTO.getProfilo()));
		ute.setUteRifId(uteDTO.getUteRifId());

		return ute;
	}

	public static Profilo profiloDTOToProfilo(ProfiloDTO proDTO) {
		Profilo pro = new Profilo();

		pro.setProDescrizione(proDTO.getProDescrizione());
		pro.setProId(proDTO.getProId());
		pro.setProNome(proDTO.getProNome());

		return pro;
	}

	public static StoricoGruppiMonta storicoGruppiMontaDTOtoStoricoGruppiMontaEntity(StoricoGruppiMontaDTO dto) {
		StoricoGruppiMonta entity = new StoricoGruppiMonta();

		if (dto.getSgmId() != 0)
			entity.setSgmId(dto.getSgmId());

		if (dto.getUtente() != null)
			entity.setUtente(utenteDTOToUtente(dto.getUtente()));
		if (dto.getSgmUteId() != 0)
			entity.setSgmUteId(dto.getSgmUteId());
		if (dto.getSgmNome() != null)
			entity.setSgmNome(dto.getSgmNome());
		if (dto.getSgmDataApertura() != null)
			entity.setSgmDataApertura(dto.getSgmDataApertura());
		if (dto.getSgmDataChiusura() != null)
			entity.setSgmDataChiusura(dto.getSgmDataChiusura());
		if (dto.getGruppoMontas() != null && dto.getGruppoMontas().size() > 0) {
			List<GruppoMonta> gmList = new ArrayList<>();
			GruppoMonta gm;
			for (GruppoMontaDTO gmdto : dto.getGruppoMontas()) {
				gm = new GruppoMonta();
				gm = gruppoMontaDTOtoGruppoMontaEntity(gmdto);
				gmList.add(gm);
			}
			entity.setGruppoMontas(gmList);
		}

		return entity;
	}

	public static StoricoAccrescFini storicoGruppiAccrFiniDTOtoStoricoGruppiAccrFiniEntity(StoricoAccrescFiniDTO dto) {
		StoricoAccrescFini entity = new StoricoAccrescFini();

		if (dto.getSafId() != 0)
			entity.setSafId(dto.getSafId());

		if (dto.getUtente() != null)
			entity.setUtente(utenteDTOToUtente(dto.getUtente()));
		if (dto.getSafUteId() != 0)
			entity.setSafUteId(dto.getSafUteId());
		if (dto.getSafNumAppezzamento() != null)
			entity.setSafNumAppezzamento(dto.getSafNumAppezzamento());
		if (dto.getSafDataInizio() != null)
			entity.setSafDataInizio(dto.getSafDataInizio());
		if (dto.getSafDataFine() != null)
			entity.setSafDataFine(dto.getSafDataFine());
		if (dto.getSafScopo() != null)
			entity.setSafScopo(dto.getSafScopo());

		if (dto.getAnagrAccrFinis() != null && dto.getAnagrAccrFinis().size() > 0) {
			List<AnagrAccrFini> accrFiniList = new ArrayList<>();
			AnagrAccrFini accrFini;
			for (AnagrAccrFiniDTO aafdto : dto.getAnagrAccrFinis()) {
				accrFini = new AnagrAccrFini();
				accrFini = anagrAccrFiniDTOtoAnagrAccrFiniEntity(aafdto);
				accrFiniList.add(accrFini);
			}
			entity.setAnagrAccrFinis(accrFiniList);
		}

		return entity;
	}

	public static GruppoMonta gruppoMontaDTOtoGruppoMontaEntity(GruppoMontaDTO dto) {
		GruppoMonta entity = new GruppoMonta();
		if (dto.getAnagrafica() != null)
			entity.setAnagrafica(anagraficaDTOToAngrafica(dto.getAnagrafica()));
		if (dto.getGmoAnaId() != 0)
			entity.setGmoAnaId(dto.getGmoAnaId());
		if (dto.getGmoId() != 0)
			entity.setGmoId(dto.getGmoId());
		if (dto.getGmoDataInserimento() != null)
			entity.setGmoDataInserimento(dto.getGmoDataInserimento());
		if (dto.getGmoDataUscita() != null)
			entity.setGmoDataUscita(dto.getGmoDataUscita());
		if (dto.getGmoDestinazioneUscita() != null)
			entity.setGmoDestinazioneUscita(dto.getGmoDestinazioneUscita());

		return entity;
	}

	// TODO
	public static AnagrAccrFini anagrAccrFiniDTOtoAnagrAccrFiniEntity(AnagrAccrFiniDTO dto) {
		AnagrAccrFini entity = new AnagrAccrFini();

		if (dto.getAnagrafica() != null)
			entity.setAnagrafica(anagraficaDTOToAngrafica(dto.getAnagrafica()));
		if (dto.getAafAnaId() != 0)
			entity.setAafAnaId(dto.getAafAnaId());
		if (dto.getAafId() != 0)
			entity.setAafId(dto.getAafId());
		if (dto.getAafDataEntrata() != null)
			entity.setAafDataEntrata(dto.getAafDataEntrata());
		if (dto.getAafDataUscita() != null)
			entity.setAafDataUscita(dto.getAafDataUscita());
		if (dto.getAafDestinazioneUscita() != null)
			entity.setAafDestinazioneUscita(dto.getAafDestinazioneUscita());

		return entity;
	}

	// TODO
	public static GruppoMontaDTO gruppoMontaEntityToGruppoMontaDTO(GruppoMonta gmo) {
		GruppoMontaDTO gmoDTO = new GruppoMontaDTO();

		return gmoDTO;
	}

	// TODO
	public static Pesata pesataDtoToPesataEntity(PesataDTO pesDto) {
		Pesata pes = new Pesata();

		if (pesDto.getPesAnaId() != 0)
			pes.setPesAnaId(pesDto.getPesAnaId());
		if (pesDto.getPesData() != null)
			pes.setPesData(pesDto.getPesData());
		if (pesDto.getPesFase() != null)
			pes.setPesFase(pesDto.getPesFase());
		if (pesDto.getPesId() != 0)
			pes.setPesId(pesDto.getPesId());
		if (pesDto.getPesPeso() != null)
			pes.setPesPeso(pesDto.getPesPeso());
		System.out.println("pesDto.getPesPeso()    " + pesDto.getPesPeso());
		return pes;
	}

}
