package com.conexia.saludcoop.common.dto.transaccional;

/**
 * 
 * @author mortega
 * 
 */
public class TopTenSedeIpsDto {
	
	private Long idSolicitudItem;
	
	private Long idAutorizacion;

	public Long getIdSolicitudItem() {
		return idSolicitudItem;
	}

	public void setIdSolicitudItem(Long idSolicitudItem) {
		this.idSolicitudItem = idSolicitudItem;
	}

	public Long getIdAutorizacion() {
		return idAutorizacion;
	}

	public void setIdAutorizacion(Long idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
	}
}
