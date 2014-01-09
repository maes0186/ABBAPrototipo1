package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.transaccional.SolicitudParcial;

public interface SolicitudParcialRepository extends
		CrudRepository<SolicitudParcial, Integer> {

	public SolicitudParcial findOneByNroSolicitud(Integer nroSolicitud);
//	public List<SolicitudParcial> findAllByNombreLikeAndTipoIdentificacionAfiliadoAndNumeroDocumento(
//			String nombre, TipoIdentificacion tipoIdenAfil,
//			String numeroDocumento);
	
	
	//public List<SolicitudParcial> findBy
}
