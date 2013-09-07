package org.empresa.fachada.administrarpersonas;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.empresa.dto.PersonaDTO;
import org.empresas.negocio.administrarpersonas.AdministrarPersonasNegocio;

@ManagedBean
@SessionScoped
public class AdministrarPersonasFachada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	AdministrarPersonasNegocio administrarPersonasNegocio;

	public boolean creacionPersona(PersonaDTO personaDTO) throws Throwable{
		try{
		return administrarPersonasNegocio.creacionPersona(personaDTO);
		}
		catch(Throwable sys){
			throw sys;
		}
	}
	
}
