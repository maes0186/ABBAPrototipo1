package com.conexia.saludcoop.common.dto.transaccional;

import java.util.Date;
import java.util.Set;

import com.conexia.saludcoop.common.dto.DescriptivoDto;

public class ResumenHistoriaClinicaDto { 
	private Long id;
	private Date fechaInicio;
	private Date fechaFin;
	private String evolucion;
	private DescriptivoDto causaExterna;
	private DescriptivoDto tipoCatastrofico;
	private  Set<ResumenDiagnosticoDto> resumenDiagnosticos;
	private String conducta;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaInicio(){
		return this.fechaInicio;
	}
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}

	public Date getFechaFin(){
		return this.fechaFin;
	}
	public void setEvolucion(String evolucion){
		this.evolucion = evolucion;
	}

	public String getEvolucion(){
		return this.evolucion;
	}
	public void setCausaExterna(DescriptivoDto causaExterna){
		this.causaExterna = causaExterna;
	}

	public DescriptivoDto getCausaExterna(){
		return this.causaExterna;
	}
	public void setTipoCatastrofico(DescriptivoDto tipoCatastrofico){
		this.tipoCatastrofico = tipoCatastrofico;
	}

	public DescriptivoDto getTipoCatastrofico(){
		return this.tipoCatastrofico;
	}
	public void setResumenDiagnosticos( Set<ResumenDiagnosticoDto> resumenDiagnosticos){
		this.resumenDiagnosticos = resumenDiagnosticos;
	}

	public Set<ResumenDiagnosticoDto> getResumenDiagnosticos(){
		return this.resumenDiagnosticos;
	}
	public void setConducta(String conducta){
		this.conducta = conducta;
	}

	public String getConducta(){
		return this.conducta;
	}

}
