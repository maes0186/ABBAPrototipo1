package org.beanding.menu;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.panelmenu.PanelMenu;

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

	 private PanelMenu model;  
	  
	    public MenuBeans() {  
	        model = new PanelMenu();
	      
	          
	          
	        MenuItem item = new MenuItem();  
	        item.setValue("Dynamic Menuitem 1.1");  
	        item.setUrl("#");  
	        model.getChildren().add(item);  
	       
	       
	          
	        item = new MenuItem();  
	        item.setValue("Dynamic Menuitem 2.1");  
	        item.setUrl("#");  
	        model.getChildren().add(item);  
	          
	        item = new MenuItem();  
	        item.setValue("Dynamic Menuitem 2.2");  
	        item.setUrl("#");  
	        model.getChildren().add(item);  
	          
	        
	    }  
	  
	    public PanelMenu getModel() {  
	        return model;  
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
