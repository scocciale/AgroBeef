package com.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.dto.AnagraficaDTO;
import com.service.FedericiService;

public class AnagraficaDTOLazyModel extends LazyDataModel<AnagraficaDTO> {

	private static final long serialVersionUID = -9045696725776984798L;

	private FedericiService federiciService;

	private UserMB userMB;

	private List<AnagraficaDTO> list;

	private int rowCount;

	public AnagraficaDTOLazyModel() {
		super();
	}

	public AnagraficaDTOLazyModel(UserMB userMB, FedericiService federiciService) {

		setUserMB(userMB);
		setFedericiService(federiciService);

		rowCount = Integer.parseInt(federiciService.countAllAnagrafica(userMB.getUtente().getUteRifId()).toString());
	}

	@Override
	public List<AnagraficaDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		String sortOrderToStr = "";
		list = new ArrayList<>();

		if (sortOrder.equals(SortOrder.DESCENDING)) {
			sortOrderToStr = "desc";
		} else {
			sortOrderToStr = "asc";
		}

		if (filters != null && filters.size() != 0) {

			list = federiciService.getAllAnimaliAnagraficaFiltered(userMB.getUtente().getUteRifId(), first, pageSize,
					sortOrderToStr, filters, sortField);

			setRowCount(federiciService.countAllAnagraficaFiltered(userMB.getUtente().getUteRifId(), filters));
		} else {

			list = federiciService.getAllAnimaliAnagrafica(userMB.getUtente().getUteRifId(), first, pageSize,
					sortOrderToStr, sortField);

			setRowCount(rowCount);
		}

		return list;
	}

	@Override
	public Object getRowKey(AnagraficaDTO dto) {
		return dto.getAnaId();
	}

	public UserMB getUserMB() {
		return userMB;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public FedericiService getFedericiService() {
		return federiciService;
	}

	public void setFedericiService(FedericiService federiciService) {
		this.federiciService = federiciService;
	}

}