package org.empresa.modelo.menu;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.empresa.constantes.enumeraciones.HttpVariables;
import org.empresa.constantes.variables.Generales;
import org.empresa.util.FacesUtil;

/**
 * Clase encargada de la logica del Menu Dinamico
 * 
 * @author Mario
 * 
 */
@ManagedBean
@SessionScoped
public class MenuBeans implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	 
	public void inicializar() {
		try {
			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(true);
			session.setAttribute(Generales.RENDERIZACION_ANTERIOR, HttpVariables.PRINCIPAL);
		} catch (Throwable e) {
			e.printStackTrace();
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,
					"Error Inesperado" + e.getMessage());
		}

	}
	      
	    public void save() {  
	        addMessage("Data saved");  
	    }  
	      
	    public void update() {  
	        addMessage("Data updated");  
	    }  
	      
	    public void delete() {  
	        addMessage("Data deleted");  
	    }  
	      
	    public void addMessage(String summary) {  
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	    }  

}
