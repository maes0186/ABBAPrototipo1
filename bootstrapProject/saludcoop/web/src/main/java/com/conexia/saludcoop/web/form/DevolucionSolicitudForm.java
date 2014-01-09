package com.conexia.saludcoop.web.form;

public class DevolucionSolicitudForm {

	private String nroSolicitud;
	private String respuestaIps;
	
	private String dosisFrecuencia;
	private Integer viaAdministracion;
	private String duracionTratamiento;
	private String posologia;
	private String tipoItem;

	public String getNroSolicitud() {
		return nroSolicitud;
	}

	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}

	public String getRespuestaIps() {
		return respuestaIps;
	}

	public void setRespuestaIps(String respuestaIps) {
		this.respuestaIps = respuestaIps;
	}

	public String getDosisFrecuencia() {
		return dosisFrecuencia;
	}

	public void setDosisFrecuencia(String dosisFrecuencia) {
		this.dosisFrecuencia = dosisFrecuencia;
	}

	public Integer getViaAdministracion() {
		return viaAdministracion;
	}

	public void setViaAdministracion(Integer viaAdministracion) {
		this.viaAdministracion = viaAdministracion;
	}

	public String getDuracionTratamiento() {
		return duracionTratamiento;
	}

	public void setDuracionTratamiento(String duracionTratamiento) {
		this.duracionTratamiento = duracionTratamiento;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

}
