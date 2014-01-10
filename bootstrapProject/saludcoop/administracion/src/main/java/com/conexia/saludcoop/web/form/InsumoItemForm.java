package com.conexia.saludcoop.web.form;


public class InsumoItemForm {

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
	private Integer cant;
	private String dxAsociado;
	private FormularioCTCInsumoForm formularioCTC;
	private PrescripcionInsumoForm prescripcion;
	private boolean visibleCtc;
	private Integer nivel;
    private Boolean aplicaTutela;
	private Boolean superaTopes;
    
    
	public Boolean getSuperaTopes() {
        return superaTopes;
    }
    public void setSuperaTopes(Boolean superaTopes) {
        this.superaTopes = superaTopes;
    }
    public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
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
	public FormularioCTCInsumoForm getFormularioCTC() {
		return formularioCTC;
	}
	public void setFormularioCTC(FormularioCTCInsumoForm formularioCTC) {
		this.formularioCTC = formularioCTC;
	}
	public PrescripcionInsumoForm getPrescripcion() {
		return prescripcion;
	}
	public void setPrescripcion(PrescripcionInsumoForm prescripcion) {
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
