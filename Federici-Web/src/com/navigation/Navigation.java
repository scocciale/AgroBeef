package com.navigation;

public enum Navigation {
	
	LOGIN("/login.xhtml"),
	HOME("/pages/home.xhtml"),
	ANAGRAFICA("/pages/anagrafica.xhtml"),
	VETERINARIA("/pages/veterinaria.xhtml"),
	ALLARMI("/pages/allarmi.xhtml"),
	GRUPPI_MONTA("/pages/gruppiDiMonta.xhtml"),
	GRUPPI_ACCRESCIMENTO("/pages/gruppiDiAccrescimento.xhtml"),
	PESATA_COMMERCIALE("/pages/pesataCommerciale.xhtml");
	
	private String pageUrl;
	
	Navigation(String pageUrl){
		this.pageUrl = pageUrl;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
}
