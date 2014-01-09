package com.conexia.saludcoop.web.vo;

public class SolicitudItemVo {

	private String numeroItem;
	private String fechaModificacion;
	private String servicioSolicitado;
	private String diagnostico;
	private String estado;
	private String tipoServicio;
    private String tipoSolicitud;

	public String getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(String numeroItem) {
		this.numeroItem = numeroItem;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getServicioSolicitado() {
		return servicioSolicitado;
	}

	public void setServicioSolicitado(String servicioSolicitado) {
		this.servicioSolicitado = servicioSolicitado;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

}
