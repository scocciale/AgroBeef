package com.managedBean;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.service.FedericiService;

@ManagedBean(name = "anagMB")
@ViewScoped
public class InterpartoMB extends BaseMB {

	Logger logger = Logger.getLogger(InterpartoMB.class.getName());

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

	@EJB
	FedericiService federiciService;

	@PostConstruct
	public void init() {

	}

}
