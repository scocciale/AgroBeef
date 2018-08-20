package com.managedBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.dto.AnagraficaDTO;
import com.dto.PesataDTO;
import com.service.FedericiService;

@ManagedBean(name = "pesataMB")
@ViewScoped
public class PesataAnagraficaMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	private final int margineGrafico = 10;

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
		nuovaPesata.setPesData(Calendar.getInstance(Locale.ITALY).getTime());

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

			if (nuovaPesata.getPesData() == null || nuovaPesata.getPesData().after(Calendar.getInstance(Locale.ITALY).getTime())) {
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
		lineModelAndamento.setTitle("Andamento pesate");
		lineModelAndamento.setAnimate(true);

		Double min = new Double(ana.getPesatas().get(0).getPesPeso());
		Double max = new Double(ana.getPesatas().get(0).getPesPeso());
		for (PesataDTO pes : ana.getPesatas()) {
			if (pes.getPesPeso() < min) {
				min = new Double(pes.getPesPeso());
			} else if (pes.getPesPeso() > max) {
				max = new Double(pes.getPesPeso());
			}
		}
		Axis yAxis = lineModelAndamento.getAxis(AxisType.Y);
		yAxis.setMin(min - ((min / 100) * margineGrafico));
		yAxis.setMax(calcoloMaxY(max + ((max / 100) * margineGrafico)));
		yAxis.setTickFormat("%#.2f");

		Calendar margineDataMin = Calendar.getInstance();
		Calendar margineDataMax = Calendar.getInstance();
		margineDataMin.setTime(new Date((ana.getPesatas().get(0).getPesData().getTime()) + 10000));
		margineDataMax.setTime(
				new Date((ana.getPesatas().get((ana.getPesatas().size()) - 1).getPesData().getTime()) + 10000));
		margineDataMin.add(Calendar.DATE, -(margineGrafico));
		margineDataMax.add(Calendar.DATE, margineGrafico);

		DateAxis xAxis = new DateAxis();
		xAxis.setTickAngle(-50);
		xAxis.setMin(margineDataMin.getTimeInMillis());
		xAxis.setMax(margineDataMax.getTimeInMillis());
		xAxis.setTickFormat("%#d/%#m/%y");

		lineModelAndamento.getAxes().put(AxisType.X, xAxis);
		lineModelAndamento.getAxes().put(AxisType.Y, yAxis);

		graficoRendered = true;

	}

	private LineChartModel initLinearModel(AnagraficaDTO ana) {
		LineChartModel model = new LineChartModel();

		LineChartSeries series = new LineChartSeries();
		series.setLabel("Andamento pesate");

		for (PesataDTO pes : ana.getPesatas()) {
			series.set(pes.getPesData().getTime(), pes.getPesPeso());
		}

		model.addSeries(series);

		return model;
	}

	// questo metodo arrotonda il massimo alla cifra tonda piu vicina
	public Double calcoloMaxY(Double max) {
		String y = new Long(max.longValue()).toString();
		char[] primoNum = { y.charAt(0) };
		Integer primaCifra = new Integer(new String(primoNum));
		primaCifra++;
		char[] cifreSuccessive = y.substring(1).toCharArray();
		for (int i = 0; i < cifreSuccessive.length; i++)
			cifreSuccessive[i] = '0';
		String ultimeCifre = new String(cifreSuccessive);
		Double valY = new Double(primaCifra.toString().concat(ultimeCifre.toString()));
		return valY;
	}

	public void cleanVars(ToggleEvent event) {

		createLineModels((AnagraficaDTO) event.getData());

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
