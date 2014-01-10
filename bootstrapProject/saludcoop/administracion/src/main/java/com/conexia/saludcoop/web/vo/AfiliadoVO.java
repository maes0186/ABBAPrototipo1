package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;


public class AfiliadoVO {
	
	private Long id;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Long eps;
	private String epsNombre;
	private String tipoIdent;
	private String numeroIdentificacion;
	private String genero;
	private String edad;
	private String fechaNacimiento;
	private String nivel;
	private String tipoAfiliado;
	private String estado;
	private String razonEstado;
	private String ipsPrimaria;
	private String municipioIpsPrimaria;
	private String direccionIPS;
	private String telefonoIPS;
	private String departamento;
	private Long departamentoId;
	private String municipio;
	private Long municipioId;
	private String direccionResidencial;
	private String emailPersonal;
	private String telefonoResidencial;
	private String telefonoCelular;
	private Integer tipoIdentID;
	private String nombreCompleto;
	private Short tutela;
	private Long regimenAfiliacion;

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Long getEps() {

		return eps;
	}

	public void setEps(Long eps) {
		this.eps = eps;
	}

	public String getTipoIdent() {
		return tipoIdent;
	}

	public void setTipoIdent(String tipoIdent) {
		this.tipoIdent = tipoIdent;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

    public String getNivel() {
    	return nivel;
    }
	
    public void setNivel(String nivel) {
    	this.nivel = nivel;
    }

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRazonEstado() {
		return razonEstado;
	}

	public void setRazonEstado(String razonEstado) {
		this.razonEstado = razonEstado;
	}

	public String getIpsPrimaria() {
		return ipsPrimaria;
	}

	public void setIpsPrimaria(String ipsPrimaria) {
		this.ipsPrimaria = ipsPrimaria;
	}

	public String getDireccionIPS() {
		return direccionIPS;
	}

	public void setDireccionIPS(String direccionIPS) {
		this.direccionIPS = direccionIPS;
	}

	public String getTelefonoIPS() {
		return telefonoIPS;
	}

	public void setTelefonoIPS(String telefonoIPS) {
		this.telefonoIPS = telefonoIPS;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDireccionResidencial() {
		return direccionResidencial;
	}

	public void setDireccionResidencial(String direccionResidencial) {
		this.direccionResidencial = direccionResidencial;
	}

	public String getEmailPersonal() {
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}

	public String getTelefonoResidencial() {
		return telefonoResidencial;
	}

	public void setTelefonoResidencial(String telefonoResidencial) {
		this.telefonoResidencial = telefonoResidencial;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public void setTipoIdentID(Integer code) {
		this.tipoIdentID = code;
		
	}

	public Integer getTipoIdentID() {
		return tipoIdentID;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Short getTutela() {
        return tutela;
    }

    public void setTutela(Short tutela) {
        this.tutela = tutela;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }

    public String getEpsNombre() {
    	return epsNombre;
    }

    public void setEpsNombre(String epsNombre) {
    	this.epsNombre = epsNombre;
    }
    
    public String getMunicipioIpsPrimaria() {
    	return municipioIpsPrimaria;
    }
	
    public void setMunicipioIpsPrimaria(String municipioIpsPrimaria) {
    	this.municipioIpsPrimaria = municipioIpsPrimaria;
    }

    public Long getRegimenAfiliacion() {
    	return regimenAfiliacion;
    }
	
    public void setRegimenAfiliacion(Long regimenAfiliacion) {
    	this.regimenAfiliacion = regimenAfiliacion;
    }

    public boolean getEsRegimenContributivo() {
    	return this.getRegimenAfiliacion() == RegimenAfiliacion.CONTRIBUTIVO.getId();
    }

	//TODO Ver si esto se puede hacer menos quemado!
    public boolean getHabilitarCreacionSolicitud() {
    	return this.estado.equals("VIGENTES") && !this.razonEstado.equals("Protección Laboral");
    }
}
