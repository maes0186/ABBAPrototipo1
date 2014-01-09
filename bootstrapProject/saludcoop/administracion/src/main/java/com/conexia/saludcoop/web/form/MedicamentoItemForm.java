package com.conexia.saludcoop.web.form;


public class MedicamentoItemForm {

	private String tipoPPM;
	private String codigo;
	private String descripcion;
	private String descEspecialidad;
	private Integer cant;
	private String dxAsociado;
	private FormularioCTCMedicamentoForm formularioCTC;
	private PrescripcionProcedimientoForm prescripcion;
	
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
	public String getDescEspecialidad() {
		return descEspecialidad;
	}
	public void setDescEspecialidad(String descEspecialidad) {
		this.descEspecialidad = descEspecialidad;
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
	public FormularioCTCMedicamentoForm getFormularioCTC() {
		return formularioCTC;
	}
	public void setFormularioCTC(FormularioCTCMedicamentoForm formularioCTC) {
		this.formularioCTC = formularioCTC;
	}
	public PrescripcionProcedimientoForm getPrescripcion() {
		return prescripcion;
	}
	public void setPrescripcion(PrescripcionProcedimientoForm prescripcion) {
		this.prescripcion = prescripcion;
	}
}
