package com.managedBean;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.dto.ProfiloDTO;
import com.dto.UtenteDTO;
import com.service.FedericiService;

@ManagedBean(name = "userMB")
@SessionScoped
public class UserMB extends BaseMB {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private UtenteDTO utente;
	private ProfiloDTO profilo;

	@EJB
	FedericiService federiciService;

	public String logIn() {
		this.utente = federiciService.logIn(username, password);
		if (utente == null) {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "message.fatal.error.login");
			return null;
		} else {
			if (federiciService.updateLastAccess(this.utente))
				return navigateTo("HOME");
			else {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "Attenzione !", "errore.tecnico");
				return null;
			}
		}
	}

	public void logOut() {
		getSession().invalidate();
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect("../login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public ProfiloDTO getProfilo() {
		return profilo;
	}

	public void setProfilo(ProfiloDTO profilo) {
		this.profilo = profilo;
	}

	@Override
	public String toString() {
		return "UserMB [username=" + username + ", password=" + password + "\n, utente=" + utente + ", profilo="
				+ profilo + "]";
	}

}
