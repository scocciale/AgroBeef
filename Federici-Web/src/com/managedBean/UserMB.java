package com.managedBean;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.dto.ProfiloDTO;
import com.dto.UtenteDTO;
import com.service.FedericiService;
import com.util.BCrypt;

@ManagedBean(name = "userMB")
@SessionScoped
public class UserMB extends BaseMB {

	Logger logger = Logger.getLogger(UserMB.class.getName());

	private static final long serialVersionUID = 1L;

	private String patternPwd = BaseMB.getTextFromProp("pattern.pwd");

	private String username;
	private String password;
	private UtenteDTO utente;
	private ProfiloDTO profilo;

	// Change pwd
	private String oldPwd;
	private String newPwd;
	private String confirmNewPwd;
	private String backupPwd;

	@EJB
	FedericiService federiciService;

	public String logIn() {
		logger.info("L'utente '" + username + "' sta cercando di fare la login.");
		this.utente = federiciService.logIn(username, password);
		if (utente == null) {
			addMessage("messages", FacesMessage.SEVERITY_FATAL, "message.warning", "message.fatal.error.login");

			return null;
		} else {
			if (federiciService.updateLastAccess(this.utente)) {
				logger.info("L'utente '" + username + "' si è loggato correttamente.");
				return navigateTo("HOME");
			} else {
				addMessage("messages", FacesMessage.SEVERITY_FATAL, "message.warning", "errore.tecnico");
				return null;
			}
		}
	}

	public void logOut() {
		getSession().invalidate();
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect("../login.xhtml");
			logger.info("L'utente '" + username + "' ha eseguito il logout.");
		} catch (IOException e) {
			logger.info("L'utente '" + username + "' ha provato ad eseguire il logout ma c'è stato un errore.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkPwd() {
	
		if (oldPwd != null && BCrypt.checkpw(oldPwd, utente.getUtePwd())) {
			if (newPwd != null && confirmNewPwd != null && newPwd.equals(confirmNewPwd)) {
				if (!BCrypt.checkpw(newPwd, utente.getUtePwd())) {
					backupPwd = utente.getUtePwd();

					Pattern r = Pattern.compile(BaseMB.getTextFromProp("pattern.pwd"));
					Matcher m = r.matcher(newPwd);
					if(!m.find()){
						addMessage("messages", FacesMessage.SEVERITY_ERROR, BaseMB.getTextFromProp("message.warning"),
								"message.pwd.pattern.not.respected");
					}else{
					utente.setUtePwd(BCrypt.hashpw(newPwd, BCrypt.gensalt()));
					boolean edit = federiciService.modifyPwd(utente);

					if (!edit) {
						utente.setUtePwd(backupPwd);
						addMessage("messages", FacesMessage.SEVERITY_ERROR, BaseMB.getTextFromProp("message.warning"),
								"message.system.error");
					}else{
						logger.info("L'utente '" + username + "' ha eseguito il cambio password.");
					}
					RequestContext context = RequestContext.getCurrentInstance();
					context.execute("PF('dlgChangePwdWv').hide();");}
				} else {
					addMessage(null, FacesMessage.SEVERITY_ERROR, BaseMB.getTextFromProp("message.warning"),
							"message.pwd.old.new.equals");
				}
			} else {
				addMessage("messages", FacesMessage.SEVERITY_ERROR, BaseMB.getTextFromProp("message.warning"),
						"message.new.pwd.confirm.pwd.wrong");
			}
		} else {
			addMessage("messages", FacesMessage.SEVERITY_ERROR, BaseMB.getTextFromProp("message.warning"),
					"message.pwd.old.wrong");
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

	public String getBackupPwd() {
		return backupPwd;
	}

	public void setBackupPwd(String backupPwd) {
		this.backupPwd = backupPwd;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConfirmNewPwd() {
		return confirmNewPwd;
	}

	public void setConfirmNewPwd(String confirmNewPwd) {
		this.confirmNewPwd = confirmNewPwd;
	}

	public String getPatternPwd() {
		return patternPwd;
	}

	public void setPatternPwd(String patternPwd) {
		this.patternPwd = patternPwd;
	}

	@Override
	public String toString() {
		return "UserMB [username=" + username + ", password=" + password + ", utente=" + utente + ", profilo=" + profilo
				+ ", oldPwd=" + oldPwd + ", newPwd=" + newPwd + ", confirmNewPwd=" + confirmNewPwd + "]";
	}

}
