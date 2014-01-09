package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.DiagnosticoDto;


public class ResumenDiagnosticoDto { 
	private Long id;
	private DiagnosticoDto diagnostico;
	private Boolean esPrincipal;

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
	public void setEsPrincipal(Boolean esPrincipal){
		this.esPrincipal = esPrincipal;
	}

	public Boolean getEsPrincipal(){
		return this.esPrincipal;
	}

}
