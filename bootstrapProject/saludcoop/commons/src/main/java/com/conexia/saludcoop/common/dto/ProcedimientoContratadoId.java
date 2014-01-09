package com.conexia.saludcoop.common.dto;

import java.io.Serializable;

import org.dozer.DozerBeanMapper;


public class ProcedimientoContratadoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045066643321408823L;

	
	
	public ProcedimientoContratadoIdDto toDto() {
		return new DozerBeanMapper().map(this, ProcedimientoContratadoIdDto.class);
	}
}
