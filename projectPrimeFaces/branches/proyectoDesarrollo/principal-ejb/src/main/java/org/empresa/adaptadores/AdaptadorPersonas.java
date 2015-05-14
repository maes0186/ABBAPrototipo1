package org.empresa.adaptadores;

import java.util.ArrayList;
import java.util.List;

import org.empresa.dto.PersonaDTO;
import org.empresa.entidades.proyecto.Persona;

/**
 * 
 * @author Mario
 * 
 */
public class AdaptadorPersonas {
	/**
	 * 
	 * @param persona
	 * @return
	 */
	public static PersonaDTO toPersonaDTO(Persona persona) {
		PersonaDTO personaDTO = new PersonaDTO();
		personaDTO.setApellido1(persona.getApellido1());
		personaDTO.setApellido2(persona.getApellido2());
		personaDTO.setNombre1(persona.getNombre1());
		personaDTO.setNombre2(persona.getNombre2());
		personaDTO.setId(persona.getId());
		return personaDTO;
	}

	public static Persona toPersona(PersonaDTO personaDTO) {
		Persona persona = new Persona();
		persona.setApellido1(personaDTO.getApellido1());
		persona.setApellido2(personaDTO.getApellido2());
		persona.setNombre1(personaDTO.getNombre1());
		persona.setNombre2(personaDTO.getNombre2());
		persona.setId(personaDTO.getId());
		return persona;
	}

	public static List<PersonaDTO> toPersonasDTO(List<Persona> personas) {
		List<PersonaDTO> personaDTOs = new ArrayList<PersonaDTO>();
		for (Persona persona : personas) {
			personaDTOs.add(toPersonaDTO(persona));
		}
		return personaDTOs;
	}
}
