package com.conexia.saludcoop.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;

public interface SolicitudItemRepository extends CrudRepository<SolicitudItem, Long> {
	
	@Query(value = "select s from SolicitudItem s, Regional r where "
			+ " cast(s.solicitud.nroSolicitud as string) = coalesce(cast(?1 as string),cast(s.solicitud.nroSolicitud as string))"
			+ " and cast(s.solicitud.afiliado.tipoIdentificacion.id as string) = coalesce(cast(?2 as string), cast(s.solicitud.afiliado.tipoIdentificacion.id as string))"
			+ " and s.solicitud.afiliado.numeroIdentificacion = coalesce(cast(?3 as string), s.solicitud.afiliado.numeroIdentificacion)"
			+ " and cast(s.solicitud.afiliado.eps.id as string) = coalesce(cast(?4 as string), cast(s.solicitud.afiliado.eps.id as string))"
			+ " and cast(r.id as string) = coalesce(cast(?5 as string), cast(r.id as string)) and (select r2.id from Regional r2 where cast(r2.id as string) = coalesce(cast(?5 as string), cast(r.id as string))) member of s.solicitud.afiliado.municipioResidencia.departamento.regionales"
			+ " and cast(s.autorizacion.estadoAutorizacion.id as string) = coalesce(cast(?6 as string), cast(s.autorizacion.estadoAutorizacion.id as string))"
			+ " and cast(s.solicitud.sedeIps.id as string) = coalesce(cast(?8 as string), cast(s.solicitud.sedeIps.id as string))"
			+ " and cast(s.solicitud.profesionalSolicitante.id as string) = coalesce(cast(?9 as string), cast(s.solicitud.profesionalSolicitante.id as string))")
		public Page<SolicitudItem> findBandeja(	Integer numero, 
												Integer tipoIdentificacionAfiliado,
												String numeroIdentificacion,
												Integer epsId, 
												Integer regionalId,
												Integer estadoAutorizacionId,
												Integer ipsId,
												Integer profesionalSolicitanteId,
												Pageable p);
	
		public Page<SolicitudItem> findAll(Pageable pageable);
		
		@Query
		public SolicitudItem findOneItemByNroItem(Long nroItem);
		
}
