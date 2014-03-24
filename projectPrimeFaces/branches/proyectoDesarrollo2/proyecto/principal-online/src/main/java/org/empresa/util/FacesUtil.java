package org.empresa.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesUtil {
	 public static void addMessage(Severity  sev,String summary) {  
	        FacesMessage message = new FacesMessage(sev, summary,  null);  
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	    } 
}
