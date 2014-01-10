package com.conexia.saludcoop.common.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ar.com.conexia.common.persistence.dao.GenericDao;
import com.conexia.saludcoop.common.entity.history.HistorialSolicitud;
import com.conexia.saludcoop.common.util.Pagination;
import com.conexia.saludcoop.web.vo.SolicitudHistorialVO;

/**
 * Data access object que permite popular VO de Solicitud a medida de las necesidades.
 * 
 * 
 */
@Component
@Transactional
public class SolicitudHistorialVODao extends GenericDao<HistorialSolicitud, Long> {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	private Criteria buildCriteria(Integer tipoDocumento,
			String numeroDocumento, 
			Long numeroSolicitud,
			Integer estadoSolicitud, 
			Integer regional,
			Date fechaDesde,
			Date fechaHasta,
			String codigoProducto){
		
		Criteria solicitudCriteria = this.sessionFactory.getCurrentSession().createCriteria(HistorialSolicitud.class);
		
		if (tipoDocumento != null) 
			solicitudCriteria.add(Restrictions.eq("idTipoDocumentoAfiliado", tipoDocumento));
		
		if (StringUtils.isNotBlank(numeroDocumento)) 
			solicitudCriteria.add(Restrictions.eq("numeroDocumentoAfiliado", numeroDocumento));
		
		if (numeroSolicitud != null) 
			solicitudCriteria.add(Restrictions.eq("nroSolicitud", numeroSolicitud));
		
		if (estadoSolicitud != null) 
			solicitudCriteria.add(Restrictions.eq("idEstado", estadoSolicitud));
		
		if (regional != null) 
			solicitudCriteria.add(Restrictions.eq("idRegional", regional));
		
		if (fechaDesde != null)
			solicitudCriteria.add(Restrictions.ge("fecha", fechaDesde));

		if (fechaHasta != null)
			solicitudCriteria.add(Restrictions.le("fecha", fechaHasta));
		
		if (StringUtils.isNotBlank(codigoProducto))
            solicitudCriteria.add(Restrictions.eq("codigoProducto", codigoProducto));
		
		
		return solicitudCriteria;
	}
	
	/**
	 * Lista todo el historial en función del filtro pasado con paginación
	 * 
	 * @param nroSol Número de solicitud.
	 * @param tipoId Tipo de identificador.
	 * @param nroId Número de identificador.
	 * @param epsId Id de EPS.
	 * @param estadoId Id del estado.
	 * @param tipoSolId Id del tipo de solicitud.
	 * @param ipsId Id del IPS.
	 * @param tecnologiaId Id de tecnología.
	 * @param pagination Paginación.
	 * @return 
	 * @return El listado.
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitudHistorialVO> getHistorialSolicitudes(
			Integer tipoDocumento,
			String numeroDocumento, 
			Long numeroSolicitud,
			Integer estadoSolicitud, 
			Integer regional,
			Date fechaDesde,
			Date fechaHasta, 
			Pagination pagination) {

		Criteria solicitudCriteria = this.buildCriteria(tipoDocumento, numeroDocumento, numeroSolicitud, estadoSolicitud, regional, fechaDesde, fechaHasta, null);
	
		solicitudCriteria.setProjection(Projections.countDistinct("id"));
		Long count = (Long) solicitudCriteria.uniqueResult();
		pagination.setTotalPages((count.intValue() / pagination.getPageSize()) + 1);

		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("nroSolicitud"), "numeroSolicitud");
		projList.add(Projections.property("descripcionRegional"), "regional");
		projList.add(Projections.property("fecha"), "fecha");
		projList.add(Projections.property("descripcion"), "descripcion");
		projList.add(Projections.property("descripcionEstado"), "estado");
		projList.add(Projections.property("tipoSolicitud"), "tipoDeSolicitud");
		projList.add(Projections.property("codigoProducto"), "codigoProducto");
		projList.add(Projections.property("unidadesAprobadas"), "unidadesAprobadas");
		projList.add(Projections.property("periodoAprobado"), "periodoAprobado");
		projList.add(Projections.property("diasPorPeriodo"), "diasPeriodo");
		
		solicitudCriteria.setProjection(projList);
		solicitudCriteria.addOrder(Order.desc("fecha"));
		solicitudCriteria.setResultTransformer(Transformers.aliasToBean(SolicitudHistorialVO.class));
		
		/*
		 * Se determina el offset del registro a partir del cual obtener (el offset es exclusive: es
		 * decir, se obtienen registros a partir del inmediato siguiente al offset).
		 */
		Integer offset = (pagination.getActualPage() - 1) * pagination.getPageSize();

		solicitudCriteria.setFirstResult(offset);
		solicitudCriteria.setMaxResults(pagination.getPageSize());

		return solicitudCriteria.list();
	}

	
	/**
	 * Cuenta la cantidad de resultados del listado de historial
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @param numeroSolicitud
	 * @param estadoSolicitud
	 * @param regional
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	public Long countHistorialSolicitudes(
			Integer tipoDocumento,
			String numeroDocumento, 
			Long numeroSolicitud,
			Integer estadoSolicitud, 
			Integer regional, 
			Date fechaDesde,
			Date fechaHasta) {
		Criteria solicitudCriteria = this.buildCriteria(tipoDocumento, numeroDocumento, numeroSolicitud, estadoSolicitud, regional, fechaDesde, fechaHasta, null);
		
		solicitudCriteria.setProjection(Projections.countDistinct("id"));
		Long count = (Long) solicitudCriteria.uniqueResult();
		
		return count;
	}
	
	
	
	/**
	 * Lista todo el historial en función del filtro pasado sin paginación
	 * 
	 * @param nroSol Número de solicitud.
	 * @param tipoId Tipo de identificador.
	 * @param nroId Número de identificador.
	 * @param epsId Id de EPS.
	 * @param estadoId Id del estado.
	 * @param tipoSolId Id del tipo de solicitud.
	 * @param ipsId Id del IPS.
	 * @param tecnologiaId Id de tecnología.
	 * @return 
	 * @return El listado.
	 */
	public List<SolicitudHistorialVO> getHistorialSolicitudes(
			Integer tipoDocumento,
			String numeroDocumento, 
			Long numeroSolicitud,
			Integer estadoSolicitud, 
			Integer regional,
			Date fechaDesde,
			Date fechaHasta) {

		Criteria solicitudCriteria = this.buildCriteria(tipoDocumento, numeroDocumento, numeroSolicitud, estadoSolicitud, regional, fechaDesde, fechaHasta, null);
	
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("nroSolicitud"), "numeroSolicitud");
		projList.add(Projections.property("descripcionRegional"), "regional");
		projList.add(Projections.property("fecha"), "fecha");
		projList.add(Projections.property("descripcion"), "descripcion");
		projList.add(Projections.property("tipoSolicitud"), "tipoDeSolicitud");
		projList.add(Projections.property("codigoProducto"), "codigoProducto");
		projList.add(Projections.property("unidadesAprobadas"), "unidadesAprobadas");
		projList.add(Projections.property("periodoAprobado"), "periodoAprobado");
		projList.add(Projections.property("diasPorPeriodo"), "diasPeriodo");
		
		solicitudCriteria.setProjection(projList);
		solicitudCriteria.addOrder(Order.desc("fecha"));
		solicitudCriteria.setResultTransformer(Transformers.aliasToBean(SolicitudHistorialVO.class));
		
		return solicitudCriteria.list();
	}
	
	
	/**
	 * Realiza la busqueda de Solicitudes en los ultimos 30 Dias del mismo procedimiento o medicamento
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @param procedimientoId
	 * @param medicamentoId
	 * @param estadoSolicitud
	 * @return List<SolicitudHistorialVO>
	 */	
    public List<SolicitudHistorialVO> getHistoricoSolicitud(Integer tipoDocumento, String numeroDocumento, String codigoProcedimiento, String codigoMedicamento,
            Integer estadoSolicitud, Integer periodo) {
        if(periodo == null)periodo = 1;
        Date fechaHasta = Calendar.getInstance().getTime();
        Date fechaDesde = new Date(fechaHasta.getTime() - Math.abs(periodo * 24 * 60 * 60 * 1000));

        Criteria solicitudCriteria = this.buildCriteria(tipoDocumento, numeroDocumento, null, estadoSolicitud, null, fechaDesde, fechaHasta, StringUtils.isNotBlank(codigoProcedimiento)?codigoProcedimiento:StringUtils.isNotBlank(codigoMedicamento)?codigoMedicamento:null);
        
        ProjectionList projList = Projections.projectionList();
        
        projList.add(Projections.property("nroSolicitud"), "numeroSolicitud");
        projList.add(Projections.property("fecha"), "fecha");
        projList.add(Projections.property("descripcion"), "descripcion");
        projList.add(Projections.property("codigoProducto"), "codigoProducto");
        projList.add(Projections.property("unidadesAprobadas"), "unidadesAprobadas");
        projList.add(Projections.property("periodoAprobado"), "periodoAprobado");
        projList.add(Projections.property("diasPorPeriodo"), "diasPeriodo");
        
        solicitudCriteria.setProjection(projList);
        solicitudCriteria.addOrder(Order.desc("fecha"));
        solicitudCriteria.setResultTransformer(Transformers.aliasToBean(SolicitudHistorialVO.class));
        
        return solicitudCriteria.list();
        
        
    }

}
