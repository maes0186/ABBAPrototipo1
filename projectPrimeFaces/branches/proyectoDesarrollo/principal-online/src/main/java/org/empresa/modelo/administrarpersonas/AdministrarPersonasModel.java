package org.empresa.modelo.administrarpersonas;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.empresa.dto.PersonaDTO;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
@SessionScoped
public class AdministrarPersonasModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersonaDTO personaDTO;
	private List<PersonaDTO> todasPersonas;

	public final List<PersonaDTO> getTodasPersonas() {
		return todasPersonas;
	}

	public final void setTodasPersonas(List<PersonaDTO> todasPersonas) {
		this.todasPersonas = todasPersonas;
	}

	public PersonaDTO getPersonaDTO() {
		return personaDTO;
	}

	public void setPersonaDTO(PersonaDTO personaDTO) {
		this.personaDTO = personaDTO;
	}
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Car Selected", ((PersonaDTO) event.getObject()).getNombre1());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((PersonaDTO) event.getObject()).getNombre2());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
