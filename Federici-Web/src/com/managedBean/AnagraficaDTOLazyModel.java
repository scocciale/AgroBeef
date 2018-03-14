package com.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.dto.AnagraficaDTO;
import com.service.FedericiService;

public class AnagraficaDTOLazyModel extends LazyDataModel<AnagraficaDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9045696725776984798L;

	@EJB
	FedericiService federiciService;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

private List<AnagraficaDTO> list;

	public AnagraficaDTOLazyModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<AnagraficaDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		 list = new ArrayList<>();
		
		 for (int i = first; i < first + pageSize; i++) {
		 AnagraficaDTO a = new AnagraficaDTO();
		 a.setAnaId(i);
		 a.setAnaNumMatricola("aaa" + new Integer(i).toString());
		 a.setAnaFlagToro(1);
		 a.setAnaFlagGemello("1");
		 list.add(a);
		 }

		//List<AnagraficaDTO> list = federiciService.getAllAnimaliAnagrafica(userMB.getUtente().getUteRifId(), first, pageSize,
				//sortField, sortOrder);
		setRowCount(125);

		return list;
	}

//	@Override
//	public AnagraficaDTO getRowData(String rowKey) {
//		for (AnagraficaDTO anag : list) {
//			if (new Integer(anag.getAnaId()).toString().equals(rowKey))
//				return anag;
//		}
//		return null;
//	}

	@Override
	public Object getRowKey(AnagraficaDTO dto) {
		return dto.getAnaId();
	}

}