package com.conexia.saludcoop.web.form;

import java.util.List;
import java.util.Vector;

public class ResumenForm {
	
	private String fechaInicio;
	private String fechaFin;
	private String evolucion;
	private Integer causaExterna;
	private Integer tipoCatastrofico;
	private List<DiagnosticoItemForm> diagnosticos = new Vector<>();
	private String conducta;
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getEvolucion() {
		return evolucion;
	}
	public void setEvolucion(String evolucion) {
		this.evolucion = evolucion;
	}
	public Integer getCausaExterna() {
		return causaExterna;
	}
	public void setCausaExterna(Integer causaExterna) {
		this.causaExterna = causaExterna;
	}

	public Integer getTipoCatastrofico() {
		return tipoCatastrofico;
	}
	public void setTipoCatastrofico(Integer tipoCatastrofico) {
		this.tipoCatastrofico = tipoCatastrofico;
	}
	public List<DiagnosticoItemForm> getDiagnosticos() {
		return diagnosticos;
	}
	public void setDiagnosticos(List<DiagnosticoItemForm> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
	public String getConducta() {
		return conducta;
	}
	public void setConducta(String conducta) {
		this.conducta = conducta;
	}
	
	
	
}
