package com.conexia.saludcoop.common.dto.transaccional;

import java.util.Date;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;


public class PreAutorizacionDto {

//	private Integer numero;
//	private List<SolicitudItemDto> solicitudItems;
	private DescriptivoDto estadoAutorizacion;
	private SedeIpsDto sedeIpsEfector;
	
	/**
	 * Identificador del servicio.
	 */
	private Long servicioId;
	
	/**
	 * Identificador de la especialidad.
	 */
	private Integer especialidadId;
	
	private RoleDTO target;
	private Date fechaAutorizacion;
//	private Long tipoPagoRequerido;
	
//	public void setNumero(Integer numero){
//		this.numero = numero;
//	}
//
//	public Integer getNumero(){
//		return this.numero;
//	}
//	public void setSolicitudItem(List<SolicitudItemDto> solicitudItems){
//		this.solicitudItems = solicitudItems;
//	}
//
//	
//
//	public List<SolicitudItemDto> getSolicitudItem(){
//		return this.solicitudItems;
//	}
	public void setEstadoAutorizacion(DescriptivoDto estadoAutorizacion){
		this.estadoAutorizacion = estadoAutorizacion;
		
	}

	public DescriptivoDto getEstadoAutorizacion(){
		return this.estadoAutorizacion;
	}
	
	
	
    public SedeIpsDto getSedeIpsEfector() {
    	return sedeIpsEfector;
    }
	
    public void setSedeIpsEfector(SedeIpsDto sedeIpsEfector) {
    	this.sedeIpsEfector = sedeIpsEfector;
    }
    
    /**
     * Obtiene el identificador del servicio.
     * 
     * @return El identificador del servicio.
     */
	public Long getServicioId() {
		
		return (this.servicioId);
	}

	/**
	 * Asigna el identificador del servicio.
	 * 
	 * @param servicio El identificador del servicio.
	 */
	public void setServicioId(final Long servicioId) {
		
		this.servicioId = servicioId;
	}

	/**
	 * Obtiene el identificador de la especialidad.
	 * 
	 * @return El identificador de la especialidad.
	 */
	public Integer getEspecialidadId() {
		
		return (this.especialidadId);
	}

	/**
	 * Asigna el identificador de la especialidad.
	 * 
	 * @param especialidadId El identificador de la especialidad.
	 */
	public void setEspecialidadId(final Integer especialidadId) {
		
		this.especialidadId = especialidadId;
	}

	public RoleDTO getTarget() {
		return target;
	}

	public void setTarget(RoleDTO target) {
		this.target = target;
	}

	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}


//	public List<SolicitudItemDto> getSolicitudItems() {
//		return solicitudItems;
//	}
//
//	public void setSolicitudItems(List<SolicitudItemDto> solicitudItems) {
//		this.solicitudItems = solicitudItems;
//	}

//	public Long getTipoPagoRequerido() {
//		return tipoPagoRequerido;
//	}
//
//	public void setTipoPagoRequerido(Long tipoPagoRequerido) {
//		this.tipoPagoRequerido = tipoPagoRequerido;
//	}
    
    
    
}
