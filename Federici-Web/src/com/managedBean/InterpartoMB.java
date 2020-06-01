package com.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.dto.AnagraficaDTO;
import com.service.FedericiService;

@ManagedBean(name = "interMB")
@ViewScoped
public class InterpartoMB extends BaseMB {

	Logger logger = Logger.getLogger(InterpartoMB.class.getName());

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

	private List<AnagraficaDTO> anaList;
	private List<AnagraficaDTO> anaFilteredList;
	private List<AnagraficaDTO> figlifromMadre;
	private String searchParam;
	private InterpartoAnagraficaDTOLazyModel lazyModel;

	@EJB
	FedericiService federiciService;

	@PostConstruct
	public void init() {
		searchParam = "";
		anaList = new ArrayList<AnagraficaDTO>();
		figlifromMadre = new ArrayList<AnagraficaDTO>();

		setLazyModel(new InterpartoAnagraficaDTOLazyModel(userMB, federiciService));

	}

	public void getDetailParto(String matricolaMadre) {
		figlifromMadre = federiciService.getAllFigliFromMadre(matricolaMadre);
		if (figlifromMadre != null && figlifromMadre.size() > 0) {
			showDialogFigli();
		} 
	}

	public void findFromParms(String valParam) {
		anaList = federiciService.getAllMatricoleDisponibiliFemale(userMB.getUtente().getUteRifId(), valParam);
	}

	public void showDialogFigli() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgAnagraficheParto').show();");

	}

	public UserMB getUserMB() {
		return userMB;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public List<AnagraficaDTO> getAnaList() {
		return anaList;
	}

	public void setAnaList(List<AnagraficaDTO> anaList) {
		this.anaList = anaList;
	}

	public List<AnagraficaDTO> getAnaFilteredList() {
		return anaFilteredList;
	}

	public void setAnaFilteredList(List<AnagraficaDTO> anaFilteredList) {
		this.anaFilteredList = anaFilteredList;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}

	public InterpartoAnagraficaDTOLazyModel getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(InterpartoAnagraficaDTOLazyModel lazyModel) {
		this.lazyModel = lazyModel;
	}

	public List<AnagraficaDTO> getFiglifromMadre() {
		return figlifromMadre;
	}

	public void setFiglifromMadre(List<AnagraficaDTO> figlifromMadre) {
		this.figlifromMadre = figlifromMadre;
	}

}
