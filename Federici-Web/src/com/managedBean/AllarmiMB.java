package com.managedBean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.dto.AnagraficaDTO;
import com.dto.ReportDTO;
import com.dto.ReportResultDTO;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.service.FedericiService;

@ManagedBean(name = "allarmiMB")
@ViewScoped
public class AllarmiMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

	private List<ReportDTO> reportDisponibiliList;
	private String query;
	List<ReportResultDTO> reportResultList;
	private int dtRendering;
	private String repNome = "";

	DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");

	RequestContext context = RequestContext.getCurrentInstance();

	@EJB
	FedericiService federiciService;

	@PostConstruct
	public void init() {

		reportDisponibiliList = new ArrayList<>();

		reportDisponibiliList = federiciService.getAllReportDisponibili();

		reportResultList = null;

	}

	public void eseguiEstrazione(ReportDTO report) {

		repNome = report.getRepNome() + dateFormat.format(new Date());

		query = report.getRepQuery();

		Integer uteCod = userMB.getUtente().getUteId();

		query = query.replaceAll("\\?", uteCod.toString());

		if (report.getRepNome().equals("allarmeUno") || report.getRepNome().equals("allarmeDue")
				|| report.getRepNome().equals("allarmeCinque")) {
			reportResultList = federiciService.getReportResultEta(query);
			dtRendering = 125;
		} else if (report.getRepNome().equals("allarmeTreParteUno")) {

			List<ReportResultDTO> tempList = federiciService.getReportResultPrimaParte(query);

			reportResultList = new ArrayList<>();
			for (ReportResultDTO res : tempList) {

				if (res.getNumParti() >= 2) {

					List<AnagraficaDTO> partiList = federiciService
							.getAnimaliDaMatMadre(res.getAnag().getAnaNumMatricola(), uteCod);

					List<ReportResultDTO> listaInterparti = new ArrayList<>();

					for (int i = 0; i < partiList.size(); i++) {
						ReportResultDTO rr = new ReportResultDTO();
						AnagraficaDTO anag = new AnagraficaDTO();

						anag.setAnaNumMatricola(partiList.get(i).getAnaNumMatricola());
						anag.setAnaNumMatricolaMadre(partiList.get(i).getAnaNumMatricolaMadre());
						anag.setAnaNumMatricolaPadre(partiList.get(i).getAnaNumMatricolaPadre());
						anag.setAnaDataNascita(partiList.get(i).getAnaDataNascita());

						rr.setAnag(anag);

						if (i != 0) {
							rr.setGiorniInterparto(new Integer((int) TimeUnit.DAYS.convert(
									partiList.get(i).getAnaDataNascita().getTime()
											- partiList.get(i - 1).getAnaDataNascita().getTime(),
									TimeUnit.MILLISECONDS)));
						}

						listaInterparti.add(rr);
					}
					List<ReportResultDTO> tempList2 = new ArrayList<>();
					for (ReportResultDTO rrd : listaInterparti) {
						if (rrd.getGiorniInterparto() != null && rrd.getGiorniInterparto() >= new Integer(400))
							tempList2.add(rrd);
					}
					if (tempList2.size() >= 1) {
						res.setRrList(tempList2);
						res.getRrList().add(0, listaInterparti.get(0));
					}

					reportResultList.add(res);
				}
			}

			dtRendering = 3;
		} else if (report.getRepNome().equals("allarmeQuattro")) {
			reportResultList = federiciService.getReportResultNoVal(query);
			dtRendering = 4;
		} else if (report.getRepNome().equals("allarmeSei") || report.getRepNome().equals("allarmeSette")) {
			try {
				reportResultList = federiciService.getReportResultPerc(query);
			} catch (Exception e) {
				context.execute("PF('dlgErrore6e7').show();");
			}
			dtRendering = 67;
		} else if (report.getRepNome().equals("allarmeOtto")) {
			try {
				reportResultList = federiciService.getReportResultClassSeurop(query);
				context.execute("PF('dlgErrore8').show();");
			} catch (Exception e) {
				context.execute("PF('dlgErrore8').show();");
				// aggiungere dialog che appare se animale con MACELLAZIONE non
				// hanno classificazione SEUROP
			}
			dtRendering = 8;
		} else if (report.getRepNome().equals("allarmeNove")) {
			try {
				reportResultList = federiciService.getReportResultIndiceSeurop(query);
			} catch (Exception e) {
				context.execute("PF('dlgErrore9').show();");
				// aggiungere dialog che appare se animale con MACELLAZIONE non
				// hanno indice SEUROP
			}
			dtRendering = 9;
		}

	}

	// public void eseguiDownload(ReportDTO report) {
	// eseguiEstrazione(report);
	//
	// }

	// da migliorare
	//
	// <p:dataExporter type="pdf" target="esitoDt"
	// fileName="#{allarmiMB.repNome}"
	// preProcessor="#{allarmiMB.preProcessPDF}"/>
	public void preProcessPDF(Object document)
			throws BadElementException, MalformedURLException, DocumentException, IOException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);

		String logo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("")
				+ "/resources/images/agroDesk_logo.png";

		Image logoSx = Image.getInstance(logo);

		pdf.add(logoSx);
	}

	public List<ReportDTO> getReportDisponibiliList() {
		return reportDisponibiliList;
	}

	public void setReportDisponibiliList(List<ReportDTO> reportDisponibiliList) {
		this.reportDisponibiliList = reportDisponibiliList;
	}

	public UserMB getUserMB() {
		return userMB;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<ReportResultDTO> getReportResultList() {
		return reportResultList;
	}

	public void setReportResultList(List<ReportResultDTO> reportResultList) {
		this.reportResultList = reportResultList;
	}

	public int getDtRendering() {
		return dtRendering;
	}

	public void setDtRendering(int dtRendering) {
		this.dtRendering = dtRendering;
	}

	public String getRepNome() {
		return repNome;
	}

	public void setRepNome(String repNome) {
		this.repNome = repNome;
	}

}
