package com.conexia.saludcoop.common.dto;

import java.util.Date;
import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Contrato;
@Mappeable(mappedTo=Contrato.class)
public class ContratoDto { 
	private Long id;
	private String nombreContrato;
	private Date fechaInicioContrato;
	private Date fechaFinContrato;
	private Boolean aprAbogado;
	private Integer plazoDiasPago;
	private Date fechaAprobado;
	private Date fechaDiasPlazo;
	private TipoPlanContratoDto tipoPlanContrato;
	private Double porcentajeNegociado;
	private Set<ServicioContratadoDto>serviciosContratados;
	private String estado;
	

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setNombreContrato(String nombreContrato){
		this.nombreContrato = nombreContrato;
	}

	public String getNombreContrato(){
		return this.nombreContrato;
	}
	public void setFechaInicioContrato(Date fechaInicioContrato){
		this.fechaInicioContrato = fechaInicioContrato;
	}

	public Date getFechaInicioContrato(){
		return this.fechaInicioContrato;
	}
	public void setFechaFinContrato(Date fechaFinContrato){
		this.fechaFinContrato = fechaFinContrato;
	}

	public Date getFechaFinContrato(){
		return this.fechaFinContrato;
	}
	public void setAprAbogado(Boolean aprAbogado){
		this.aprAbogado = aprAbogado;
	}

	public Boolean getAprAbogado(){
		return this.aprAbogado;
	}
	public void setPlazoDiasPago(Integer plazoDiasPago){
		this.plazoDiasPago = plazoDiasPago;
	}

	public Integer getPlazoDiasPago(){
		return this.plazoDiasPago;
	}
	public void setFechaAprobado(Date fechaAprobado){
		this.fechaAprobado = fechaAprobado;
	}

	public Date getFechaAprobado(){
		return this.fechaAprobado;
	}
	public void setFechaDiasPlazo(Date fechaDiasPlazo){
		this.fechaDiasPlazo = fechaDiasPlazo;
	}

	public Date getFechaDiasPlazo(){
		return this.fechaDiasPlazo;
	}
	public void setTipoPlanContrato(TipoPlanContratoDto tipoPlanContrato){
		this.tipoPlanContrato = tipoPlanContrato;
	}

	public TipoPlanContratoDto getTipoPlanContrato(){
		return this.tipoPlanContrato;
	}

	public void setPorcentajeNegociado(Double porcentajeNegociado){
		this.porcentajeNegociado = porcentajeNegociado;
	}

	public Double getPorcentajeNegociado(){
		return this.porcentajeNegociado;
	}
	public void setServiciosContratados(Set<ServicioContratadoDto>serviciosContratados){
		this.serviciosContratados = serviciosContratados;
	}

	public Set<ServicioContratadoDto>getServiciosContratados(){
		return this.serviciosContratados;
	}

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	

}
