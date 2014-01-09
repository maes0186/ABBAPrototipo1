package com.conexia.saludcoop.web.form;

public class ProcedimientoPosHomologoForm {

private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String codigo;
	private String descripcion;
	private Integer cantidadPeriodo;
	private Integer tiempoDeUso;
	private Integer frecuenciaDeUso;
	private Integer objetivo;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCantidadPeriodo() {
		return cantidadPeriodo;
	}
	public void setCantidadPeriodo(Integer cantidadPeriodo) {
		this.cantidadPeriodo = cantidadPeriodo;
	}
	public Integer getTiempoDeUso() {
		return tiempoDeUso;
	}
	public void setTiempoDeUso(Integer tiempoDeUso) {
		this.tiempoDeUso = tiempoDeUso;
	}
	public Integer getFrecuenciaDeUso() {
		return frecuenciaDeUso;
	}
	public void setFrecuenciaDeUso(Integer frecuenciaDeUso) {
		this.frecuenciaDeUso = frecuenciaDeUso;
	}
	public Integer getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(Integer objetivo) {
		this.objetivo = objetivo;
	}
	


}
