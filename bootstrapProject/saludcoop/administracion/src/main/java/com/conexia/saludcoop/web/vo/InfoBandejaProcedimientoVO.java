package com.conexia.saludcoop.web.vo;

import java.util.List;
public class InfoBandejaProcedimientoVO {
	
	private List<ProcedimientoPosPrevioVO> procedimientosAnteriores;	
	private boolean sinAlternativaPOSPrev;								
	private String justificacionSinPOSPrev;
	private ProcedimientoHomologoVO procedimientoPOSHomologo;			
	private String justificacionMedicoNoHomologo;
	//Listado Documentos adjuntos (Justificacion Solicitud)
	private String esRiesgoParaVida;									
	private String justificacionRiesgo;									
	private String seAgotoPosibilTerapEnPOS;							
	private String esAutorizadoInvima;									
	private ProcedimientoMedicamentoVO procedimientoSolicitado;
	private String especialidadProcSolicitado;
	private String objetivoSolicitud;
	private String lateralidad;
	private String tipoPrestacion;
	private String origenRepeticion;
	private String cantidadSolicitada;
	private String indicaciones;
	private String justificacionMedicoSolicitud;						
	private boolean tieneFormCTC = false;
	
	public String getJustificacionMedicoNoHomologo() {
		return justificacionMedicoNoHomologo;
	}
	public void setJustificacionMedicoNoHomologo(
			String justificacionMedicoNoHomologo) {
		this.justificacionMedicoNoHomologo = justificacionMedicoNoHomologo;
	}
	public String getEsRiesgoParaVida() {
		return esRiesgoParaVida;
	}
	public void setEsRiesgoParaVida(String esRiesgoParaVida) {
		this.esRiesgoParaVida = esRiesgoParaVida;
	}
	public String getJustificacionRiesgo() {
		return justificacionRiesgo;
	}
	public void setJustificacionRiesgo(String justificacionRiesgo) {
		this.justificacionRiesgo = justificacionRiesgo;
	}
	public String getEsAutorizadoInvima() {
		return esAutorizadoInvima;
	}
	public void setEsAutorizadoInvima(String esAutorizadoInvima) {
		this.esAutorizadoInvima = esAutorizadoInvima;
	}
	public String getObjetivoSolicitud() {
		return objetivoSolicitud;
	}
	public void setObjetivoSolicitud(String objetivoSolicitud) {
		this.objetivoSolicitud = objetivoSolicitud;
	}
	public String getLateralidad() {
		return lateralidad;
	}
	public void setLateralidad(String lateralidad) {
		this.lateralidad = lateralidad;
	}
	public String getIndicaciones() {
		return indicaciones;
	}
	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}
	public String getJustificacionMedicoSolicitud() {
		return justificacionMedicoSolicitud;
	}
	public void setJustificacionMedicoSolicitud(String justificacionMedicoSolicitud) {
		this.justificacionMedicoSolicitud = justificacionMedicoSolicitud;
	}
	public boolean isSinAlternativaPOSPrev() {
		return sinAlternativaPOSPrev;
	}
	public void setSinAlternativaPOSPrev(boolean sinAlternativaPOSPrev) {
		this.sinAlternativaPOSPrev = sinAlternativaPOSPrev;
	}
	public String getSeAgotoPosibilTerapEnPOS() {
		return seAgotoPosibilTerapEnPOS;
	}
	public void setSeAgotoPosibilTerapEnPOS(String seAgotoPosibilTerapEnPOS) {
		this.seAgotoPosibilTerapEnPOS = seAgotoPosibilTerapEnPOS;
	}
	public String getJustificacionSinPOSPrev() {
		return justificacionSinPOSPrev;
	}
	public void setJustificacionSinPOSPrev(String justificacionSinPOSPrev) {
		this.justificacionSinPOSPrev = justificacionSinPOSPrev;
	}
	public ProcedimientoHomologoVO getProcedimientoPOSHomologo() {
		return procedimientoPOSHomologo;
	}
	public void setProcedimientoPOSHomologo(
			ProcedimientoHomologoVO procedimientoPOSHomologo) {
		this.procedimientoPOSHomologo = procedimientoPOSHomologo;
	}
	public List<ProcedimientoPosPrevioVO> getProcedimientosAnteriores() {
		return procedimientosAnteriores;
	}
	public void setProcedimientosAnteriores(List<ProcedimientoPosPrevioVO> procedimientosAnteriores) {
		this.procedimientosAnteriores = procedimientosAnteriores;
	}
	public String getTipoPrestacion() {
		return tipoPrestacion;
	}
	public void setTipoPrestacion(String tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
	}
	public String getOrigenRepeticion() {
		return origenRepeticion;
	}
	public void setOrigenRepeticion(String origenRepeticion) {
		this.origenRepeticion = origenRepeticion;
	}
	public String getCantidadSolicitada() {
		return cantidadSolicitada;
	}
	public void setCantidadSolicitada(String cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}
	public ProcedimientoMedicamentoVO getProcedimientoSolicitado() {
		return procedimientoSolicitado;
	}
	public void setProcedimientoSolicitado(
			ProcedimientoMedicamentoVO procedimientoSolicitado) {
		this.procedimientoSolicitado = procedimientoSolicitado;
	}
	public String getEspecialidadProcSolicitado() {
		return especialidadProcSolicitado;
	}
	public void setEspecialidadProcSolicitado(String especialidadProcSolicitado) {
		this.especialidadProcSolicitado = especialidadProcSolicitado;
	}
    public boolean isTieneFormCTC() {
        return tieneFormCTC;
    }
    public void setTieneFormCTC(boolean tieneFormCTC) {
        this.tieneFormCTC = tieneFormCTC;
    }	
	
}
