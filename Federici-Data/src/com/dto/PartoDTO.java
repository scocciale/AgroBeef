package com.dto;

import java.io.Serializable;
import java.util.Date;

public class PartoDTO implements Serializable {
	private static final long serialVersionUID = -863172501544148133L;

	private int parId;
	// ana_id matricola del bovino nato
	private int parAnaId;
	private Date parData;
	// ana_id matricola della madre
	private int parMadreAnaId;

	public int getParId() {
		return parId;
	}

	public void setParId(int parId) {
		this.parId = parId;
	}

	public int getParAnaId() {
		return parAnaId;
	}

	public void setParAnaId(int parAnaId) {
		this.parAnaId = parAnaId;
	}

	public Date getParData() {
		return parData;
	}

	public void setParData(Date parData) {
		this.parData = parData;
	}

	public int getParMadreAnaId() {
		return parMadreAnaId;
	}

	public void setParMadreAnaId(int parMadreAnaId) {
		this.parMadreAnaId = parMadreAnaId;
	}
}
