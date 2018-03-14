package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the valutazione_mace database table.
 * 
 */
@Entity
@Table(name = "valutazione_mace")
@NamedQuery(name = "ValutazioneMace.findAll", query = "SELECT v FROM ValutazioneMace v")
public class ValutazioneMace implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "vma_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vmaId;

	@Column(name = "vma_class_seurop")
	private String vmaClassSeurop;

	@Temporal(TemporalType.DATE)
	@Column(name = "vma_data")
	private Date vmaData;

	@Column(name = "vma_grasso_seurop")
	private String vmaGrassoSeurop;

	@Column(name = "vma_peso_carcassa")
	private float vmaPesoCarcassa;

	@Column(name = "vma_peso_macellazione")
	private float vmaPesoMacellazione;

	@Column(name = "vma_peso_taglio_tot")
	private float vmaPesoTaglioTot;

	@Column(name = "vma_ana_id")
	private int vmaAnaId;
	
	@Column(name = "vma_ute_id")
	private int vmaUteId;

	// bi-directional many-to-one association to Anagrafica
	@ManyToOne
	@JoinColumn(name = "vma_ana_id", insertable = false, updatable = false)
	private Anagrafica anagrafica;

	public ValutazioneMace() {
	}

	public int getVmaId() {
		return this.vmaId;
	}

	public void setVmaId(int vmaId) {
		this.vmaId = vmaId;
	}

	public String getVmaClassSeurop() {
		return this.vmaClassSeurop;
	}

	public void setVmaClassSeurop(String vmaClassSeurop) {
		this.vmaClassSeurop = vmaClassSeurop;
	}

	public Date getVmaData() {
		return this.vmaData;
	}

	public void setVmaData(Date vmaData) {
		this.vmaData = vmaData;
	}

	public String getVmaGrassoSeurop() {
		return this.vmaGrassoSeurop;
	}

	public void setVmaGrassoSeurop(String vmaGrassoSeurop) {
		this.vmaGrassoSeurop = vmaGrassoSeurop;
	}

	public float getVmaPesoCarcassa() {
		return this.vmaPesoCarcassa;
	}

	public void setVmaPesoCarcassa(float vmaPesoCarcassa) {
		this.vmaPesoCarcassa = vmaPesoCarcassa;
	}

	public float getVmaPesoMacellazione() {
		return this.vmaPesoMacellazione;
	}

	public void setVmaPesoMacellazione(float vmaPesoMacellazione) {
		this.vmaPesoMacellazione = vmaPesoMacellazione;
	}

	public float getVmaPesoTaglioTot() {
		return this.vmaPesoTaglioTot;
	}

	public void setVmaPesoTaglioTot(float vmaPesoTaglioTot) {
		this.vmaPesoTaglioTot = vmaPesoTaglioTot;
	}

	public Anagrafica getAnagrafica() {
		return this.anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public int getVmaAnaId() {
		return vmaAnaId;
	}

	public void setVmaAnaId(int vmaAnaId) {
		this.vmaAnaId = vmaAnaId;
	}

	public int getVmaUteId() {
		return vmaUteId;
	}

	public void setVmaUteId(int vmaUteId) {
		this.vmaUteId = vmaUteId;
	}

}