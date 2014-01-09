package com.conexia.saludcoop.common.dto.transaccional;

import java.util.Date;
import java.util.List;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;

public class AutorizacionDto {
	private Long id;
	private Integer numero;
	private List<SolicitudItemDto> solicitudItems;
	private DescriptivoDto estadoAutorizacion;
	private SedeIpsDto sedeIpsEfector;
	private RoleDTO target;
	private Date fechaAutorizacion;
	private TipoPagoRequerido tipoPagoRequerido;
	private AutorizacionDto autorizacionAnterior = null;
    private String justificacionIps;
    private Long numeroSolicitud;
    private Integer especialidadId;
    private Long servicioId;
    

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setSolicitudItem(List<SolicitudItemDto> solicitudItems) {
		this.solicitudItems = solicitudItems;
	}

	public List<SolicitudItemDto> getSolicitudItem() {
		return this.solicitudItems;
	}

	public void setEstadoAutorizacion(DescriptivoDto estadoAutorizacion) {
		this.estadoAutorizacion = estadoAutorizacion;
	}

	public DescriptivoDto getEstadoAutorizacion() {
		return this.estadoAutorizacion;
	}

	public SedeIpsDto getSedeIpsEfector() {
		return sedeIpsEfector;
	}

	public void setSedeIpsEfector(SedeIpsDto sedeIpsEfector) {
		this.sedeIpsEfector = sedeIpsEfector;
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

	public List<SolicitudItemDto> getSolicitudItems() {
		return solicitudItems;
	}

	public void setSolicitudItems(List<SolicitudItemDto> solicitudItems) {
		this.solicitudItems = solicitudItems;
	}

	public TipoPagoRequerido getTipoPagoRequerido() {
		return tipoPagoRequerido;
	}

	public void setTipoPagoRequerido(TipoPagoRequerido tipoPagoRequerido) {
		this.tipoPagoRequerido = tipoPagoRequerido;
	}

	public AutorizacionDto getAutorizacionAnterior() {
		return autorizacionAnterior;
	}

	public void setAutorizacionAnterior(AutorizacionDto autorizacionAnterior) {
		this.autorizacionAnterior = autorizacionAnterior;
	}

    public String getJustificacionIps() {
        return justificacionIps;
    }

    public void setJustificacionIps(String justificacionIps) {
        this.justificacionIps = justificacionIps;
    }

    public Long getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(Long numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

	public Integer getEspecialidadId() {
		return especialidadId;
	}

	public void setEspecialidadId(Integer especialidadId) {
		this.especialidadId = especialidadId;
	}

	public Long getServicioId() {
		return servicioId;
	}

	public void setServicioId(Long servicioId) {
		this.servicioId = servicioId;
	}



}
