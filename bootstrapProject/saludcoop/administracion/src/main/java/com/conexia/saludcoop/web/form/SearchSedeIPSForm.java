package com.conexia.saludcoop.web.form;

public class SearchSedeIPSForm {

	private Integer tipoIdentificacion;
	private String numeroIdentificacion;
	private String razonSocial;
	private String municipioSedeIpsId;
	private String departamentoSedeIpsId;
	private String direccion;

	public Integer getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(Integer tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
    public String getMunicipioSedeIpsId() {
    	return municipioSedeIpsId;
    }

    public void setMunicipioSedeIpsId(String municipioSedeIpsId) {
    	this.municipioSedeIpsId = municipioSedeIpsId;
    }
	
    public String getDepartamentoSedeIpsId() {
    	return departamentoSedeIpsId;
    }

    public void setDepartamentoSedeIpsId(String departamentoSedeIpsId) {
    	this.departamentoSedeIpsId = departamentoSedeIpsId;
    }

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
