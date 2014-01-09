package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.DescriptivoDto;

@Entity
@Table(name = "razon_estado_afiliacion", schema = "maestros")
@Mappeable(mappedTo=DescriptivoDto.class)
public class RazonEstadoAfiliacion extends Descriptivo{
	
	@Column(name="codigo_estado_afiliacion", nullable=true, length=50)	
	private String codigoEstadoAfiliacion;

	
    public String getCodigoEstadoAfiliacion() {
    	return codigoEstadoAfiliacion;
    }
	
    public void setCodigoEstadoAfiliacion(String codigoEstadoAfiliacion) {
    	this.codigoEstadoAfiliacion = codigoEstadoAfiliacion;
    }
}
