package org.empresa.modelo.administrarpersonas;

import java.io.Serializable;
import java.util.List;

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

}
