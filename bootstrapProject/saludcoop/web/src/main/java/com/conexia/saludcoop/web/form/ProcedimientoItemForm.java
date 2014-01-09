package com.conexia.saludcoop.web.form;


public class ProcedimientoItemForm {
	
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String tipoPPM;
	private String codigo;
	private String descripcion;
	private Integer especialidad;
	private Integer cant;
	private String dxAsociado;
	private FormularioCTCProcedimientoForm formularioCTC;
	private PrescripcionProcedimientoForm prescripcion;
	private Integer nivel;
	private Boolean aplicaTutela;
	
	public String getTipoPPM() {
		return tipoPPM;
	}
	public void setTipoPPM(String tipoPPM) {
		this.tipoPPM = tipoPPM;
	}
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
	public Integer getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Integer especialidad) {
		this.especialidad = especialidad;
	}
	public Integer getCant() {
		return cant;
	}
	public void setCant(Integer cant) {
		this.cant = cant;
	}
	public String getDxAsociado() {
		return dxAsociado;
	}
	public void setDxAsociado(String dxAsociado) {
		this.dxAsociado = dxAsociado;
	}
	public FormularioCTCProcedimientoForm getFormularioCTC() {
		return formularioCTC;
	}
	public void setFormularioCTC(FormularioCTCProcedimientoForm formularioCTC) {
		this.formularioCTC = formularioCTC;
	}
	public PrescripcionProcedimientoForm getPrescripcion() {
		return prescripcion;
	}
	public void setPrescripcion(PrescripcionProcedimientoForm prescripcion) {
		this.prescripcion = prescripcion;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
    public Boolean getAplicaTutela() {
        return aplicaTutela;
    }
    public void setAplicaTutela(Boolean aplicaTutela) {
        this.aplicaTutela = aplicaTutela;
    }
	
}
