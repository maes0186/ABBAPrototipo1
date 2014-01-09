package com.conexia.saludcoop.web.vo;


public class EpsVO { 
	private Long id;
	private String razonSocial;
    private Integer idTipoIdentificacion;
    private String descripcionTipoIdentificacion;
	private String numeroIdentificacion;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	
	public void setRazonSocial(String razonSocial){
		this.razonSocial = razonSocial;
	}

	public String getRazonSocial(){
		return this.razonSocial;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion){
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNumeroIdentificacion(){
		return this.numeroIdentificacion;
	}

    public Integer getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getDescripcionTipoIdentificacion() {
        return descripcionTipoIdentificacion;
    }

    public void setDescripcionTipoIdentificacion(String descripcionTipoIdentificacion) {
        this.descripcionTipoIdentificacion = descripcionTipoIdentificacion;
    }

}
