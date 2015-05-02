package br.edu.granbery.sisestagio.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtil {

	// Não é possível instanciar esta classe
	private MessagesUtil() {
	}

	public static void error(String summary) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null));
	}

	public static void info(String summary) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
	}

	public static void warn(String summary) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null));
	}

	public static void fatal(String summary) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, null));
	}

}
