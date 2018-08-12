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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.dto.AnagraficaDTO;
import com.dto.PesataDTO;
import com.service.FedericiService;

@ManagedBean(name = "pesataMB")
@ViewScoped
public class PesataAnagraficaMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

	private AnagraficaDTOLazyModel lazyModel;
	private List<AnagraficaDTO> anagraficaFilteredList;
	private PesataDTO nuovaPesata;
	private LineChartModel lineModelAndamento;

	boolean graficoRendered = false;

	int indexOfanimale = 0;

	@EJB
	FedericiService federiciService;

	@PostConstruct
	public void init() {

		setLazyModel(new AnagraficaDTOLazyModel(userMB, federiciService));

		nuovaPesata = new PesataDTO();
	}

	public void openDialogPes(AnagraficaDTO ana) {

		nuovaPesata = new PesataDTO();

		nuovaPesata.setAnagrafica(ana);
		nuovaPesata.setPesAnaId(ana.getAnaId());
		nuovaPesata.setPesData(new Date());

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgNuovoPeso').show();");

	}

	public int renderingArrowIcon(PesataDTO pes, List<PesataDTO> pesatas) {
		if (pesatas.indexOf(pes) > 0) {

			if (pes.getPesPeso().equals(pesatas.get((pesatas.indexOf(pes) - 1)).getPesPeso())) {
				// peso uguale a quello precedende
				return 0;
			} else if (pes.getPesPeso() < pesatas.get((pesatas.indexOf(pes) - 1)).getPesPeso()) {
				// peso minore a quello precedende
				return -1;
			} else if (pes.getPesPeso() > pesatas.get((pesatas.indexOf(pes) - 1)).getPesPeso()) {
				// peso maggiore a quello precedende
				return 1;
			}
		}
		// return fake
		return 9;

	}

	public void savePes() {

		if (nuovaPesata != null) {

			if (nuovaPesata.getPesData() == null || nuovaPesata.getPesData().after(new Date())) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "data.pesata.mancante");
				return;
			}
			if (nuovaPesata.getPesPeso().equals(null) || nuovaPesata.getPesPeso().equals(new Double(0))) {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "peso.inserito.errato");
				return;
			}
			boolean saved = federiciService.salvaNuovaPesata(nuovaPesata);
			if (saved) {
				List<PesataDTO> pesatas = new ArrayList<>();
				if (nuovaPesata.getAnagrafica().getPesatas() != null
						&& nuovaPesata.getAnagrafica().getPesatas().size() > 0) {
					pesatas = nuovaPesata.getAnagrafica().getPesatas();
					nuovaPesata.setDeltaPeso(nuovaPesata.getPesPeso() - pesatas.get((pesatas.size()) - 1).getPesPeso());
					pesatas.add(nuovaPesata);
				} else {
					pesatas.add(nuovaPesata);
				}
				nuovaPesata.getAnagrafica().setPesatas(pesatas);

				nuovaPesata = new PesataDTO();
				RequestContext.getCurrentInstance().update("@all");
			} else {
				// log
			}
		} else {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "errore.tecnico");
			return;
		}
	}

	public void createLineModels(AnagraficaDTO ana) {
		lineModelAndamento = initLinearModel(ana);
		lineModelAndamento.setTitle("Linear Chart");
		lineModelAndamento.setLegendPosition("e");
		Axis yAxis = lineModelAndamento.getAxis(AxisType.Y);
		yAxis.setMin(ana.getPesatas().get(0).getPesData());
		yAxis.setMax(ana.getPesatas().get((ana.getPesatas().size()) - 1).getPesData());

		graficoRendered = true;
	}

	private LineChartModel initLinearModel(AnagraficaDTO ana) {
		LineChartModel model = new LineChartModel();

		LineChartSeries series = new LineChartSeries();
		series.setLabel("Andamento pesate");

		for (PesataDTO pes : ana.getPesatas()) {
			series.set(pes.getPesData(), pes.getPesPeso());
		}

		model.addSeries(series);

		return model;
	}

	public AnagraficaDTOLazyModel getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(AnagraficaDTOLazyModel lazyModel) {
		this.lazyModel = lazyModel;
	}

	public List<AnagraficaDTO> getAnagraficaFilteredList() {
		return anagraficaFilteredList;
	}

	public void setAnagraficaFilteredList(List<AnagraficaDTO> anagraficaFilteredList) {
		this.anagraficaFilteredList = anagraficaFilteredList;
	}

	public UserMB getUserMB() {
		return userMB;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public PesataDTO getNuovaPesata() {
		return nuovaPesata;
	}

	public void setNuovaPesata(PesataDTO nuovaPesata) {
		this.nuovaPesata = nuovaPesata;
	}

	public LineChartModel getLineModelAndamento() {
		return lineModelAndamento;
	}

	public void setLineModelAndamento(LineChartModel lineModelAndamento) {
		this.lineModelAndamento = lineModelAndamento;
	}

	public boolean isGraficoRendered() {
		return graficoRendered;
	}

	public void setGraficoRendered(boolean graficoRendered) {
		this.graficoRendered = graficoRendered;
	}

}
