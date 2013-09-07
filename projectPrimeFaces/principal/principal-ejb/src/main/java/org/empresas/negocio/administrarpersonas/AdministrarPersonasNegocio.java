package org.empresas.negocio.administrarpersonas;

import java.io.Serializable;

import javax.ejb.Stateless;

import org.empresa.dto.PersonaDTO;

@Stateless
public class AdministrarPersonasNegocio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public boolean creacionPersona(PersonaDTO personaDTO){
		return true;
	}
	public boolean creacionPersona2(){
		return true;
	}

}
