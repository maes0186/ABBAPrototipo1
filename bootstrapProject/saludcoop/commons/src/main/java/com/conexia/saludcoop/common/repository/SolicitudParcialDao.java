package com.conexia.saludcoop.common.repository;

import java.util.List;

import com.conexia.saludcoop.common.entity.transaccional.SolicitudParcial;
import com.conexia.saludcoop.common.repository.exceptions.FormatoParametroException;
import com.conexia.saludcoop.common.util.Pagination;

public interface SolicitudParcialDao {

    public List<SolicitudParcial> findForBandeja(Integer tipoDocumentoAfiliado, String numeroDocumentoAfiliado, Integer lineaDeFrenteId, String fechaDesde, String fechaHasta, Integer nroSolicitud, Pagination pagina)
	throws FormatoParametroException ;
	
}
