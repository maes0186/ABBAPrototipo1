package org.empresa.modelo.prueba;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.empresa.controladores.administrarestudiantes.AdministrarPersonasController;
import org.empresa.dto.PersonaDTO;
import org.empresa.fachada.administrarpersonas.AdministrarPersonasFachada;
import org.empresa.util.FacesUtil;

@ManagedBean
@SessionScoped
public class PersonasDepartamentos implements Serializable {

	/**
	 * 
	 */
	
	@ManagedProperty(value = "#{administrarPersonasFachada}")
	private AdministrarPersonasFachada administrarPersonasFachada;
	
	private PersonaDTO personaeditable;
	
	
	public PersonaDTO getPersonaeditable() {
		//if(personaeditable==null)personaeditable=new PersonaDTO();
		return personaeditable;
	}

	public void setPersonaeditable(PersonaDTO personaeditable) {
		this.personaeditable = personaeditable;
	}

	public AdministrarPersonasFachada getAdministrarPersonasFachada() {
		return administrarPersonasFachada;
	}

	public void setAdministrarPersonasFachada(
			AdministrarPersonasFachada administrarPersonasFachada) {
		this.administrarPersonasFachada = administrarPersonasFachada;
	}

	private final static Logger LOGGER = Logger
			.getLogger(PersonasDepartamentos.class.getName());
	private static final long serialVersionUID = 1L;
	private List<String> departamentos;
	private String departamentoSeleccionado;
	private List<String> ciudades;
	private String ciudad;
	private List<PersonaDTO> todasPersonas;
	

	public List<PersonaDTO> getTodasPersonas() {
		try{
		todasPersonas=administrarPersonasFachada
		.obtenerTodasPersonas();
		} catch (Throwable e) {
			LOGGER.log(Level.SEVERE,e.getMessage());
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,
					"Error Inesperado" + e.getMessage());
		}
		return todasPersonas;
	}

	public void setTodasPersonas(List<PersonaDTO> todasPersonas) {
		this.todasPersonas = todasPersonas;
	}

	public List<String> getCiudades() {
		if(departamentoSeleccionado!=null&&departamentoSeleccionado.equals("Dept1")){
			ciudades= new ArrayList<String>();
			ciudades.add("Ciudad1");
			ciudades.add("Ciudad2");
			ciudades.add("Ciudad3");
		}
		else{
			ciudades= new ArrayList<String>();
			ciudades.add("Ciudad4");
			ciudades.add("Ciudad5");
			ciudades.add("Ciudad6");
		}
		return ciudades;
	}

	public void setCiudades(List<String> ciudades) {
		this.ciudades = ciudades;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<String> getDepartamentos() {
		if (departamentos == null) {
			departamentos = new ArrayList<String>();
			departamentos.add("Dept1");
			departamentos.add("Dept2");
		}
		return departamentos;
	}

	public String getDepartamentoSeleccionado() {
		return departamentoSeleccionado;
	}

	public void setDepartamentoSeleccionado(String departamentoSeleccionado) {
		this.departamentoSeleccionado = departamentoSeleccionado;
	}

	public void setDepartamentos(List<String> departamentos) {
		this.departamentos = departamentos;
	}

	
	public void editarPersona(PersonaDTO personaDTO) {
    personaeditable=personaDTO;
	}

	public void eliminarPersona(PersonaDTO personaDTO) {

	}
}
