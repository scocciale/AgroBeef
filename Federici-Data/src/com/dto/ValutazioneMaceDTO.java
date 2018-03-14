package com.dto;

import java.util.Date;

public class ValutazioneMaceDTO {
	private int vmaId;
	private String vmaClassSeurop;
	private Date vmaData;
	private String vmaGrassoSeurop;
	private float vmaPesoCarcassa;
	private float vmaPesoMacellazione;
	private float vmaPesoTaglioTot;
	private int vmaAnaId;
	private int vmaUteId;
	private AnagraficaDTO anagrafica;

	public int getVmaId() {
		return vmaId;
	}

	public void setVmaId(int vmaId) {
		this.vmaId = vmaId;
	}

	public String getVmaClassSeurop() {
		return vmaClassSeurop;
	}

	public void setVmaClassSeurop(String vmaClassSeurop) {
		this.vmaClassSeurop = vmaClassSeurop;
	}

	public Date getVmaData() {
		return vmaData;
	}

	public void setVmaData(Date vmaData) {
		this.vmaData = vmaData;
	}

	public String getVmaGrassoSeurop() {
		return vmaGrassoSeurop;
	}

	public void setVmaGrassoSeurop(String vmaGrassoSeurop) {
		this.vmaGrassoSeurop = vmaGrassoSeurop;
	}

	public float getVmaPesoCarcassa() {
		return vmaPesoCarcassa;
	}

	public void setVmaPesoCarcassa(float vmaPesoCarcassa) {
		this.vmaPesoCarcassa = vmaPesoCarcassa;
	}

	public float getVmaPesoMacellazione() {
		return vmaPesoMacellazione;
	}

	public void setVmaPesoMacellazione(float vmaPesoMacellazione) {
		this.vmaPesoMacellazione = vmaPesoMacellazione;
	}

	public float getVmaPesoTaglioTot() {
		return vmaPesoTaglioTot;
	}

	public void setVmaPesoTaglioTot(float vmaPesoTaglioTot) {
		this.vmaPesoTaglioTot = vmaPesoTaglioTot;
	}

	public int getVmaAnaId() {
		return vmaAnaId;
	}

	public void setVmaAnaId(int vmaAnaId) {
		this.vmaAnaId = vmaAnaId;
	}

	public AnagraficaDTO getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(AnagraficaDTO anagrafica) {
		this.anagrafica = anagrafica;
	}

	public int getVmaUteId() {
		return vmaUteId;
	}

	public void setVmaUteId(int vmaUteId) {
		this.vmaUteId = vmaUteId;
	}
}
