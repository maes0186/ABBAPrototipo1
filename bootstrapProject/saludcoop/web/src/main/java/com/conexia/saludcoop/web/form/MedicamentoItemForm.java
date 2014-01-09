package com.conexia.saludcoop.web.form;


public class MedicamentoItemForm {

	
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
	private String descEspecialidad;
	private Integer cant;
	private String dxAsociado;
	private FormularioCTCMedicamentoForm formularioCTC;
	private PrescripcionMedicamentoForm prescripcion;
	private boolean visibleCtc;
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
	public PrescripcionMedicamentoForm getPrescripcion() {
		return prescripcion;
	}
	public void setPrescripcion(PrescripcionMedicamentoForm prescripcion) {
		this.prescripcion = prescripcion;
	}
	public boolean isVisibleCtc() {
		return visibleCtc;
	}
	public void setVisibleCtc(boolean visibleCtc) {
		this.visibleCtc = visibleCtc;
	}
    public Boolean getAplicaTutela() {
        return aplicaTutela;
    }
    public void setAplicaTutela(Boolean aplicaTutela) {
        this.aplicaTutela = aplicaTutela;
    }

}
