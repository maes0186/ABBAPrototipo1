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

import com.conexia.saludcoop.common.entity.transaccional.Autorizacion;
import com.conexia.saludcoop.common.util.Pagination;
import com.conexia.saludcoop.common.util.RoleUtils;
import com.conexia.saludcoop.web.vo.BandejaAutorizacionItemProjVO;
import com.conexia.saludcoop.web.vo.BandejaItemProjVO;

/**
 * Data access object que permite popular VO de Solicitud a medida de las necesidades.
 * 
 * @author mcuervo
 * 
 */
@Component
@Transactional
public class AutorizacionVODao extends GenericDao<Autorizacion, Long> {

	@Autowired
	private SessionFactory sessionFactory;

	
	/**
	 * Busca los autorizaciones.
	 * 
	 * @return El listado.
	 */
	@SuppressWarnings("unchecked")
	public List<BandejaAutorizacionItemProjVO> getAutorizaciones(Integer nroSol, Integer tipoId, String nroId, Long epsId, List<Integer> estados, Integer tipoSolId, Integer tecnologiaId, Long nroAu, Pagination pagination, List<Integer> roles, Long sedeIpsId) {

		final Criteria autorizacionCriteria = sessionFactory.getCurrentSession().createCriteria(Autorizacion.class);

        if (sedeIpsId != null)
            autorizacionCriteria.add(Restrictions.eq("sede.id", sedeIpsId));
		if (!estados.isEmpty())
        	autorizacionCriteria.add(Restrictions.in("estadoAutorizacion.id", estados));
		if (nroSol != null)
			autorizacionCriteria.add(Restrictions.eq("sol.nroSolicitud", nroSol));
		if (nroAu != null)
			autorizacionCriteria.add(Restrictions.eq("numeroAutorizacion", nroAu));
		if (tipoId != null)
			autorizacionCriteria.add(Restrictions.eq("tipoId.id", tipoId));
		if (nroId != "")
			autorizacionCriteria.add(Restrictions.eq("afi.numeroIdentificacion", nroId));
		if (epsId != null)
			autorizacionCriteria.add(Restrictions.eq("eps.id", epsId));
		if (tecnologiaId != null)
			autorizacionCriteria.add(Restrictions.eq("tipoTec.id", tecnologiaId));
		if (tipoSolId != null)
			autorizacionCriteria.add(Restrictions.eq("tipoPPM.id", tipoSolId));
        if (roles != null) {
            List<Integer> rolesEx = RoleUtils.getRolesExcepcion();
            
            boolean contains = false;
            
            for (Integer role : roles) {
                if(rolesEx.contains(role)) {
                    contains = true;
                    break;
                }
            }
            
            if(!contains) {
                autorizacionCriteria.add(Restrictions.in("roleDestinatario.id", roles));   
            }
        }
		autorizacionCriteria.createAlias("solicitudItems", "items");
		autorizacionCriteria.createAlias("items.solicitud", "sol");
		autorizacionCriteria.createAlias("sedeIpsEfectora", "sede");
		autorizacionCriteria.createAlias("sol.afiliado", "afi");
		autorizacionCriteria.createAlias("afi.tipoIdentificacion", "tipoId");
		autorizacionCriteria.createAlias("afi.eps", "eps");
		autorizacionCriteria.createAlias("items.tipoPPM", "tipoPPM");
		autorizacionCriteria.createAlias("items.tipoTecnologia", "tipoTec");
		autorizacionCriteria.createAlias("sol.profesionalSolicitante", "prof");
		autorizacionCriteria.createAlias("sol.usuarioCreador", "usuario");
		autorizacionCriteria.createAlias("usuario.roles", "roles");
		//Para filtrar los consumidos
		autorizacionCriteria.createAlias("items.consumos", "consum",CriteriaSpecification.LEFT_JOIN);
		autorizacionCriteria.add(Restrictions.isNull("consum.id")); 
				///
		autorizacionCriteria.setProjection(Projections.count("solicitudItems"));
        Long countItems = (Long) autorizacionCriteria.uniqueResult();
        pagination.setTotalItems(countItems);
		
		autorizacionCriteria.setProjection(Projections.countDistinct("numeroAutorizacion"));
		Long count = (Long) autorizacionCriteria.uniqueResult();
		pagination.setTotalPages((int) Math.ceil(count.doubleValue() / pagination.getPageSize()));

		final ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("tipoId.codigo"), "codigoIdentificacion");
		projList.add(Projections.property("afi.numeroIdentificacion"), "numeroIdentificacion");
		projList.add(Projections.property("afi.primerNombre"), "primerNombre");
		projList.add(Projections.property("afi.segundoNombre"), "segundoNombre");
		projList.add(Projections.property("afi.primerApellido"), "primerApellido");
		projList.add(Projections.property("afi.segundoApellido"), "segundoApellido");
		projList.add(Projections.property("eps.razonSocial"), "eps");
		projList.add(Projections.property("sol.fechaCreacion"), "fechaCreacionSolicitud");		
		projList.add(Projections.property("sol.nroSolicitud"), "numeroSolicitud");
		projList.add(Projections.property("numeroAutorizacion"), "numeroAutorizacion");
		projList.add(Projections.property("prof.primerNombre"), "primerNombreSolicitante");
		projList.add(Projections.property("prof.segundoNombre"), "segundoNombreSolicitante");
		projList.add(Projections.property("prof.primerApellido"), "primerApellidoSolicitante");
		projList.add(Projections.property("prof.segundoApellido"), "segundoApellidoSolicitante");
		projList.add(Projections.distinct(Projections.property("numeroAutorizacion")));
		autorizacionCriteria.setProjection(projList);
		autorizacionCriteria.addOrder(Order.asc("sol.fechaCreacion"));
		autorizacionCriteria.setResultTransformer(Transformers.aliasToBean(BandejaItemProjVO.class));
		Integer offset = (pagination.getActualPage() - 1) * pagination.getPageSize();
		
		autorizacionCriteria.setFirstResult(offset);
		autorizacionCriteria.setMaxResults(pagination.getPageSize());
		
		return autorizacionCriteria.list();
	}

}
