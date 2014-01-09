package com.conexia.saludcoop.common.dto.transaccional;

import java.util.Set;

import com.conexia.saludcoop.common.dto.DiagnosticoDto;

public class SolicitudDiagnosticoDto { 
	private Long id;
	private DiagnosticoDto diagnostico;
	private Set<SolicitudItemDto>solicitudItems;
	private boolean esPrincipal;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setDiagnostico(DiagnosticoDto diagnostico){
		this.diagnostico = diagnostico;
	}

	public DiagnosticoDto getDiagnostico(){
		return this.diagnostico;
	}
	public void setSolicitudItems(Set<SolicitudItemDto>solicitudItems){
		this.solicitudItems = solicitudItems;
	}

	public Set<SolicitudItemDto>getSolicitudItems(){
		return this.solicitudItems;
	}
	public void setEsPrincipal(boolean esPrincipal){
		this.esPrincipal = esPrincipal;
	}

	public boolean getEsPrincipal(){
		return this.esPrincipal;
	}

}
