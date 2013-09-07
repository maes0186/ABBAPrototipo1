package org.empresa.modelo.administrarpersonas;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.empresa.dto.PersonaDTO;

@ManagedBean
@SessionScoped
public class AdministrarPersonasModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersonaDTO personaDTO;
	public PersonaDTO getPersonaDTO() {
		return personaDTO;
	}
	public void setPersonaDTO(PersonaDTO personaDTO) {
		this.personaDTO = personaDTO;
	}

}
