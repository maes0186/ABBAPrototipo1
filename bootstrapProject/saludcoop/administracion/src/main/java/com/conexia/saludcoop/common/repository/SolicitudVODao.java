package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.conexia.common.persistence.dao.GenericDao;

import com.conexia.saludcoop.common.dto.FiltroBandejasDto;
import com.conexia.saludcoop.common.entity.transaccional.Solicitud;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.util.Pagination;
import com.conexia.saludcoop.common.util.RoleUtils;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.web.vo.BandejaItemProjVO;
import com.conexia.saludcoop.web.vo.BandejaSubItemProjVO;

/**
 * Data access object que permite popular VO de Solicitud a medida de las necesidades.
 * 
 * @author Julio Sejtman
 * 
 */
@Component
@Transactional
public class SolicitudVODao extends GenericDao<Solicitud, Long> {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Lista las solicitudes en función del filtro pasado.
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
	 * @return El listado.
	 */
	@SuppressWarnings("unchecked")
	public List<BandejaItemProjVO> getSolicitudes(Integer nroSol, Integer tipoId, String nroId,
	        Long epsId, List<Integer> estados,  Long ipsId, Integer tecnologia, Pagination pagination, List<Integer> roles, Boolean esTutela, Long sedeIpsId) {

		final Criteria solicitudCriteria = this.sessionFactory.getCurrentSession().createCriteria(Solicitud.class);
		
		if (ipsId != null)
			solicitudCriteria.add(Restrictions.eq("sedeIps.id", ipsId));
		if (nroSol != null) 
			solicitudCriteria.add(Restrictions.eq("nroSolicitud", nroSol));
		if (tipoId != null) 
			solicitudCriteria.add(Restrictions.eq("afiliado.tipoIdentificacion.id", tipoId));
		if (nroId != "") 
			solicitudCriteria.add(Restrictions.eq("afiliado.numeroIdentificacion", nroId));
		if (epsId != null) 
			solicitudCriteria.add(Restrictions.eq("eps.id", epsId));
        if (tecnologia != null)
            solicitudCriteria.add(Restrictions.eq("items.tipoTecnologia.id", tecnologia));
		if (roles != null) {
        	List<Integer> rolesEx = RoleUtils.getRolesExcepcion();
        	
        	boolean contains = false;
        	
        	for (Integer role : roles) {
                if(rolesEx.contains(role)) {
                    contains = true;
                    break;
                }
            }
        	
        	if(!contains || Boolean.TRUE.equals(esTutela)) {
        		solicitudCriteria.add(Restrictions.in("autorizacion.roleDestinatario.id", roles));
        	}
        }
		if (!estados.isEmpty())
			solicitudCriteria.add(Restrictions.in("autorizacion.estadoAutorizacion.id", estados));
		
		solicitudCriteria.createAlias("afiliado", "afiliado");
		solicitudCriteria.createAlias("afiliado.tipoIdentificacion", "tipoIdentificacionAfiliado");
		solicitudCriteria.createAlias("afiliado.eps", "eps");
		solicitudCriteria.createAlias("solicitudItems", "items");
		solicitudCriteria.createAlias("items.autorizacion", "autorizacion");
		solicitudCriteria.createAlias("profesionalSolicitante", "profesionalSolicitante");
		solicitudCriteria.createAlias("usuarioCreador", "usuario");
        solicitudCriteria.createAlias("usuario.roles", "roles");
        solicitudCriteria.createAlias("sedeIps", "sede");

        solicitudCriteria.setProjection(Projections.count("solicitudItems"));
        Long countItems = (Long) solicitudCriteria.uniqueResult();
        pagination.setTotalItems(countItems);
        
        solicitudCriteria.setProjection(Projections.countDistinct("id"));
		Long count = (Long) solicitudCriteria.uniqueResult();
		pagination.setTotalPages((int) Math.ceil(count.doubleValue() / pagination.getPageSize()));

		final ProjectionList projList = Projections.projectionList();
		        
		projList.add(Projections.distinct(Projections.property("nroSolicitud")));
		
		projList.add(Projections.property("tipoIdentificacionAfiliado.descripcion"), "codigoIdentificacion");
		projList.add(Projections.property("afiliado.numeroIdentificacion"), "numeroIdentificacion");
		projList.add(Projections.property("afiliado.primerNombre"), "primerNombre");
		projList.add(Projections.property("afiliado.segundoNombre"), "segundoNombre");
		projList.add(Projections.property("afiliado.primerApellido"), "primerApellido");
		projList.add(Projections.property("afiliado.segundoApellido"), "segundoApellido");
		projList.add(Projections.property("eps.razonSocial"), "eps");
		projList.add(Projections.property("fechaCreacion"), "fechaCreacionSolicitud");
		projList.add(Projections.property("nroSolicitud"), "numeroSolicitud");
		projList.add(Projections.property("profesionalSolicitante.primerNombre"), "primerNombreSolicitante");
		projList.add(Projections.property("profesionalSolicitante.segundoNombre"), "segundoNombreSolicitante");
		projList.add(Projections.property("profesionalSolicitante.primerApellido"), "primerApellidoSolicitante");
		projList.add(Projections.property("profesionalSolicitante.segundoApellido"), "segundoApellidoSolicitante");
		
		solicitudCriteria.setProjection(projList);
		solicitudCriteria.addOrder(Order.asc("fechaCreacion"));
		solicitudCriteria.setResultTransformer(Transformers.aliasToBean(BandejaItemProjVO.class));

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
	 * Busca los sub items de una solicitud por su número.
	 * 
	 * @param numeroSolicitud
	 * @return El listado.
	 */
	@SuppressWarnings("unchecked")
	public List<BandejaSubItemProjVO> getGetSubitemSolicitud(FiltroBandejasDto filtro, List<Integer> estados, List<Integer> roles) {

		Criteria solicitudCriteria = sessionFactory.getCurrentSession().createCriteria(SolicitudItem.class);

        // Se establecen los filtros para la consulta
        if (filtro.getNumeroSolicitud() != null)
            solicitudCriteria.add(Restrictions.eq("sol.nroSolicitud", filtro.getNumeroSolicitud()));
        if (filtro.getNumeroAutorizacion() != null)
        	solicitudCriteria.add(Restrictions.eq("autorizacion.numeroAutorizacion", filtro.getNumeroAutorizacion()));
        if (!estados.isEmpty())
			solicitudCriteria.add(Restrictions.in("autorizacion.estadoAutorizacion.id", estados));
//        if (filtro.getTipoSolicitud() != null){
//        	if (filtro.getTipoSolicitud().equals(2))
//        		solicitudCriteria.add(Restrictions.or(Restrictions.eq("tipoPPM.id", filtro.getTipoSolicitud()), Restrictions.isNotNull("solMedicamento.formCTCMedicamento")));
//        	else
//        		solicitudCriteria.add(Restrictions.eq("tipoPPM.id", filtro.getTipoSolicitud()));
//        }

        if (filtro.getTecnologia() != null)
            solicitudCriteria.add(Restrictions.eq("tipoTecnologia.id", filtro.getTecnologia()));
        if (roles != null) {
            List<Integer> rolesEx = RoleUtils.getRolesExcepcion();
            
            boolean contains = false;
            
            for (Integer role : roles) {
                if(rolesEx.contains(role)) {
                    contains = true;
                    break;
                }
            }

            if(!contains || Boolean.TRUE.equals(filtro.getEsTutela())) {
                solicitudCriteria.add(Restrictions.in("autorizacion.roleDestinatario.id", roles));
            }
        }

		final ProjectionList projList = Projections.projectionList();
		solicitudCriteria.setProjection(projList);

		solicitudCriteria.createAlias("diagnostico", "diag", Criteria.LEFT_JOIN);
        solicitudCriteria.createAlias("solicitud", "sol");
        solicitudCriteria.createAlias("tipoPPM", "tipoSol");
		solicitudCriteria.createAlias("diag.diagnostico", "diagnostico", Criteria.LEFT_JOIN);
		solicitudCriteria.createAlias("autorizacion", "autorizacion",
		        Criteria.LEFT_JOIN);
		solicitudCriteria.createAlias("autorizacion.estadoAutorizacion", "estadoAutorizacion",
		        Criteria.LEFT_JOIN);
		solicitudCriteria.createAlias("solMedicamento", "solMedicamento",
		        Criteria.LEFT_JOIN);
		solicitudCriteria.createAlias("solMedicamento.medicamento", "medicamento",
		        Criteria.LEFT_JOIN);
		solicitudCriteria.createAlias("solInsumo", "solInsumo",
		        Criteria.LEFT_JOIN);
		solicitudCriteria.createAlias("solInsumo.insumo", "insumo",
		        Criteria.LEFT_JOIN);

		solicitudCriteria.createAlias("solProcedimiento", "solProcedimiento",
		        Criteria.LEFT_JOIN);
		solicitudCriteria.createAlias("solProcedimiento.procedimiento", "procedimiento",
		        Criteria.LEFT_JOIN);
		//Para filtrar los consumidos
		solicitudCriteria.createAlias("consumos", "consum",CriteriaSpecification.LEFT_JOIN);
        solicitudCriteria.createAlias("solicitud.usuarioCreador", "usuario");
        solicitudCriteria.createAlias("usuario.roles", "roles");
        if (SystemConstants.BANDEJA_AUTORIZACIONES.equals(filtro.getBandeja()))
        	solicitudCriteria.createAlias("autorizacion.sedeIpsEfectora", "sede");        	
        else
        	solicitudCriteria.createAlias("sol.sedeIps", "sede");
        
        // TODO: se comenta para solucionar JIRA SALCOOP-214
		//solicitudCriteria.add(Restrictions.isNull("consum.id")); 
		///
		projList.add(Projections.property("diagnostico.descripcion"), "diagnostico");
        // TODO: Se deja comentado para saber donde estaba, en cualquier momento los funcionales se arrepienten XD
		projList.add(Projections.property("estadoAutorizacion.id"), "estadoId");
		projList.add(Projections.property("estadoAutorizacion.descripcion"), "estado");
		projList.add(Projections.property("tipoSol.descripcion"), "tipoSolicitud");
		projList.add(Projections.property("autorizacion.fechaUpdateCambioEstado"), "fechaModificacion");
		projList.add(Projections.property("nroItem"), "numeroItem");

        projList.add(Projections.property("procedimiento.descripcion"), "procedimiento");
        projList.add(Projections.property("procedimiento.id"), "idProcedimiento");
        projList.add(Projections.property("medicamento.descripcion"), "medicamento");
        projList.add(Projections.property("medicamento.id"), "idMedicamento");
        projList.add(Projections.property("insumo.descripcion"), "insumo");
        projList.add(Projections.property("insumo.id"), "idInsumo");

		solicitudCriteria.setProjection(projList);
		solicitudCriteria
		        .setResultTransformer(Transformers.aliasToBean(BandejaSubItemProjVO.class));

		return solicitudCriteria.list();
	}

}

