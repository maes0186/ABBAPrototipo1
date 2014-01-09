package com.conexia.saludcoop.common.repository;

import com.conexia.saludcoop.common.entity.transaccional.Solicitud;

public interface SolicitudDao {
    public Integer getNextNumeroSolicitud();
	public Solicitud findLastSolicitudByInsumoAndAfiliado(Long afiliadoId, String codMedicamento);
	
}
