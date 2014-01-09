package com.conexia.saludcoop.web.form;


public class MedicamentoPosPrevioForm {

private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String codigo;
	private String descripcion;
	private Integer dosis;
	private Integer diasTratamiento;
	private Integer respuestaClinicaObservada;
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
	public Integer getDosis() {
		return dosis;
	}
	public void setDosis(Integer dosis) {
		this.dosis = dosis;
	}
	public Integer getDiasTratamiento() {
		return diasTratamiento;
	}
	public void setDiasTratamiento(Integer diasTratamiento) {
		this.diasTratamiento = diasTratamiento;
	}
	public Integer getRespuestaClinicaObservada() {
		return respuestaClinicaObservada;
	}
	public void setRespuestaClinicaObservada(Integer respuestaClinicaObservada) {
		this.respuestaClinicaObservada = respuestaClinicaObservada;
	}

		
	
}
