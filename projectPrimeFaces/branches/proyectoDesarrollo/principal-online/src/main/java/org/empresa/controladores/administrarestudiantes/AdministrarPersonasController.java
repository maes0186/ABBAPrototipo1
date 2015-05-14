package org.empresa.controladores.administrarestudiantes;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.empresa.constantes.enumeraciones.HttpVariables;
import org.empresa.constantes.variables.Generales;
import org.empresa.dto.PersonaDTO;
import org.empresa.fachada.administrarpersonas.AdministrarPersonasFachada;
import org.empresa.modelo.administrarpersonas.AdministrarPersonasModel;
import org.empresa.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AdministrarPersonasController implements Serializable {
	/**
	 * 
	 */
	private final static Logger LOGGER = Logger
			.getLogger(AdministrarPersonasController.class.getName());
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{administrarPersonasFachada}")
	AdministrarPersonasFachada administrarPersonasFachada;
	@ManagedProperty(value = "#{administrarPersonasModel}")
	AdministrarPersonasModel administrarPersonasModel;

	public void init() {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(true);

			HttpVariables link = (HttpVariables) session.getAttribute(Generales.RENDERIZACION_ANTERIOR);
			if (link.equals(HttpVariables.PRINCIPAL)) {
				session.setAttribute(Generales.RENDERIZACION_ANTERIOR, HttpVariables.ADMINISTRAR_PERSONAS);
				administrarPersonasModel.setPersonaDTO(new PersonaDTO());
				administrarPersonasModel
						.setTodasPersonas(administrarPersonasFachada
	 							.obtenerTodasPersonas());

			}
		} catch (Throwable e) {
			LOGGER.log(Level.SEVERE,e.getMessage());
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,
					"Error Inesperado" + e.getMessage());
		}

	}

	public void guardarPersona() {
		try {
			if (administrarPersonasFachada
					.creacionPersona(administrarPersonasModel.getPersonaDTO())) {
				administrarPersonasModel
				.setTodasPersonas(administrarPersonasFachada
							.obtenerTodasPersonas());
				FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
						"Persona Creado Correctamente");
			} else {
				FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,
						"No se pudo crear el Persona");
			}
		} catch (Throwable e) {
			LOGGER.log(Level.SEVERE,e.getMessage());
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,
					"Error Inesperado" + e.getMessage());
		}
	}

	public void editarPersona(PersonaDTO personaDTO) {
		try {
			if (administrarPersonasFachada
					.creacionPersona(personaDTO)) {
				administrarPersonasModel
				.setTodasPersonas(administrarPersonasFachada
							.obtenerTodasPersonas());
				FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
						"Persona editada Correctamente");
			} else {
				FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,
						"No se pudo editar el Persona");
			}
		} catch (Throwable e) {
			LOGGER.log(Level.SEVERE,e.getMessage());
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,
					"Error Inesperado" + e.getMessage());
		}
	}
	public AdministrarPersonasFachada getAdministrarPersonasFachada() {
		return administrarPersonasFachada;
	}

	public void setAdministrarPersonasFachada(
			AdministrarPersonasFachada administrarPersonasFachada) {
		this.administrarPersonasFachada = administrarPersonasFachada;
	}

	public final AdministrarPersonasModel getAdministrarPersonasModel() {
		return administrarPersonasModel;
	}

	public final void setAdministrarPersonasModel(
			AdministrarPersonasModel administrarPersonasModel) {
		this.administrarPersonasModel = administrarPersonasModel;
	}

}
