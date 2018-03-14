package com.managedBean;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.navigation.Navigation;

public class BaseMB implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String REDIRECT = "?faces-redirect=true";

	protected String titoloPag = "Home";

	// protected UserDTO user;

	/*************** CONTEXT *******************/
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected ExternalContext getContext() {
		return getFacesContext().getExternalContext();
	}

	protected RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	protected static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	/*************** SESSION *******************/
	protected HttpSession getSession() {
		return (HttpSession) getContext().getSession(false);
	}

	protected Map<String, Object> getSessionMap() {
		return this.getContext().getSessionMap();
	}

	/*************** SESSION BEANS *******************/
	protected void destroyBean(final String beanName) {
		Map<String, Object> sessionMap = getSessionMap();
		if (sessionMap.containsKey(beanName) && null != sessionMap.get(beanName)) {
			sessionMap.put(beanName, null);
		}
	}

	public String navigateTo(String page) {
		return Navigation.valueOf(page).getPageUrl() + REDIRECT;
	}

	/**
	 * Adds a message for the user using Primefaces interface.
	 * 
	 * @param severity
	 *            the severity of the message. Typically, INFO, WARN, ERROR or
	 *            FATAL.
	 * @param messaggio
	 *            the PropertiesDefinition of the title of the message.
	 * @param dettaglio
	 *            the PropertiesDefinition of the detail of the message. May be
	 *            NULL.
	 * @param args
	 *            an optional variable amount of strings, which will be used to
	 *            replace placeholders in the message.
	 */
	protected void addMessage(String elemMessage, final Severity severity, String titleMessage, String keyMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle backendText = context.getApplication().getResourceBundle(context, "text");
		String valueMessage = backendText.getString(keyMessage);
		FacesMessage message = new FacesMessage(severity, titleMessage, valueMessage);
		context.addMessage(elemMessage, message);
	}

	public FacesMessage getErrorFacesMessage(String messaggio, String dettaglio) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, messaggio, dettaglio);
	}

	public void addError(String messaggio, String dettaglio) {
		FacesMessage facesMessage = getErrorFacesMessage(messaggio, dettaglio);

		getFacesContext().addMessage(null, facesMessage);
	}

	public String getTitoloPag() {
		return titoloPag;
	}

	public void setTitoloPag(String titoloPag) {
		this.titoloPag = titoloPag;
	}

}