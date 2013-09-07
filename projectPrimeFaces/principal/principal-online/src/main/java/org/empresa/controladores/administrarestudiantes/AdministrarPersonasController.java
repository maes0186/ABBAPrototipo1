package org.empresa.controladores.administrarestudiantes;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{administrarPersonasFachada}")
	AdministrarPersonasFachada administrarPersonasFachada;
	@ManagedProperty(value = "#{administrarPersonasModel}")
	AdministrarPersonasModel administrarPersonasModel;

	public void init() {
		administrarPersonasModel.setPersonaDTO(new PersonaDTO());
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
			int tr=0;
			// ...
		}
	}

	public void guardarPersona() {
		try {
			if (administrarPersonasFachada
					.creacionPersona(administrarPersonasModel
							.getPersonaDTO())) {
				FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
						"Persona Creado Correctamente");
			} else {
				FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,
						"No se pudo crear el Persona");
			}
		} catch (Throwable e) {
			e.printStackTrace();
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
