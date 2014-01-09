package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.web.xls.BandejaXls;

/**
 * Data access object que permite popular VO de reporte CTC nacional.
 * 
 * @author mcuervo
 * 
 */
@Component
@Transactional
public class SolicitudItemVODao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<BandejaXls> getReporte(Integer nroSol, Integer tipoId, String nroId, Long epsId, Long regId, Integer estado, Integer tecnologia, List<Integer> roles){
		
		final Criteria c = this.sessionFactory.getCurrentSession().createCriteria(SolicitudItem.class);		
		
		if (nroSol != null) 
			c.add(Restrictions.eq("sol.nroSolicitud", nroSol));
		if (tipoId != null) 
			c.add(Restrictions.eq("tipoIdentificacionAfiliado.id", tipoId));
		if (!StringUtils.isEmpty(nroId)) 
			c.add(Restrictions.eq("afi.numeroIdentificacion", nroId));
		if (epsId != null) 
			c.add(Restrictions.eq("eps.id", epsId));
		if (regId != null) 
			c.add(Restrictions.eq("reg.id", regId));
		if (estado != null)
			c.add(Restrictions.eq("estado.id", estado));
        if (tecnologia != null)
            c.add(Restrictions.eq("tipoTecnologia.id", tecnologia));
        
        c.add(Restrictions.in("au.roleDestinatario.id", roles));
        
		c.createAlias("solicitud", "sol");
		c.createAlias("sol.afiliado", "afi");
		c.createAlias("sol.sedeIps", "sedeIps");
		c.createAlias("sedeIps.regional", "reg");
		c.createAlias("afi.tipoIdentificacion", "tipoIdentificacionAfiliado");
		c.createAlias("afi.eps", "eps");
		c.createAlias("autorizacion", "au");
		c.createAlias("au.estadoAutorizacion", "estado");
		c.createAlias("au.conceptoRegional", "concepto", Criteria.LEFT_JOIN);
		c.createAlias("concepto.causalAnulacion", "causaAnulacion", Criteria.LEFT_JOIN);
		c.createAlias("concepto.causalDevolucion", "causaDevolucion", Criteria.LEFT_JOIN);
		c.createAlias("solMedicamento", "solMedicamento", Criteria.LEFT_JOIN);
		c.createAlias("solMedicamento.medicamento", "medicamento", Criteria.LEFT_JOIN);
		c.createAlias("solInsumo", "solInsumo", Criteria.LEFT_JOIN);
		c.createAlias("solInsumo.insumo", "insumo", Criteria.LEFT_JOIN);
		c.createAlias("solProcedimiento", "solProcedimiento", Criteria.LEFT_JOIN);
		c.createAlias("solProcedimiento.procedimiento", "procedimiento", Criteria.LEFT_JOIN);
		c.createAlias("diagnostico", "Soldx");
		c.createAlias("Soldx.diagnostico", "dx");
		c.createAlias("tipoTecnologia", "tt");
			
		final ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("sol.nroSolicitud"), "solId");
		projList.add(Projections.property("nroItem"), "solItemId");
		projList.add(Projections.property("reg.descripcion"), "regional");
		projList.add(Projections.property("sol.fechaCreacion"), "fecha");
		projList.add(Projections.property("tipoIdentificacionAfiliado.descripcion"), "tipoDocumento");
		projList.add(Projections.property("afi.numeroIdentificacion"), "documento");
		projList.add(Projections.property("afi.primerNombre"), "primerNombre");
		projList.add(Projections.property("afi.segundoNombre"), "segundoNombre");
		projList.add(Projections.property("afi.primerApellido"), "primerApellido");
		projList.add(Projections.property("afi.segundoApellido"), "segundoApellido");
		projList.add(Projections.property("procedimiento.descripcion"), "procedimiento");
		projList.add(Projections.property("medicamento.descripcion"), "medicamento");
		projList.add(Projections.property("insumo.descripcion"), "insumo");
		projList.add(Projections.property("dx.descripcion"), "diagnostico");
		projList.add(Projections.property("causaAnulacion.descripcion"), "conceptoAnulacion");
		projList.add(Projections.property("causaDevolucion.descripcion"), "conceptoDevolucion");
		projList.add(Projections.property("estado.descripcion"), "estado");
		projList.add(Projections.property("medicamento.visibleCtc"), "mVisibleCtc");
		projList.add(Projections.property("medicamento.altoCosto"), "mAltoCosto");
		projList.add(Projections.property("medicamento.insumo"), "mIsInsumo");
		projList.add(Projections.property("insumo.visibleCtc"), "iVisibleCtc");
		projList.add(Projections.property("insumo.altoCosto"), "iAltoCosto");
		projList.add(Projections.property("insumo.insumo"), "iIsInsumo");
		projList.add(Projections.property("procedimiento.nivelAutorizacion"), "nivel");
		projList.add(Projections.property("eps.razonSocial"), "eps");
		projList.add(Projections.property("tt.descripcion"), "tipoCTC");
		
		c.setProjection(projList);
		c.addOrder(Order.asc("sol.fechaCreacion"));
		c.setResultTransformer(Transformers.aliasToBean(BandejaXls.class));

		CriteriaImpl criteriaImpl = (CriteriaImpl)c;
		SessionImplementor session = criteriaImpl.getSession();
		SessionFactoryImplementor factory = session.getFactory();
		CriteriaQueryTranslator translator=new CriteriaQueryTranslator(factory,criteriaImpl,criteriaImpl.getEntityOrClassName(),CriteriaQueryTranslator.ROOT_SQL_ALIAS);
		String[] implementors = factory.getImplementors( criteriaImpl.getEntityOrClassName() );

		CriteriaJoinWalker walker = new CriteriaJoinWalker((OuterJoinLoadable)factory.getEntityPersister(implementors[0]), 
		                        translator,
		                        factory, 
		                        criteriaImpl, 
		                        criteriaImpl.getEntityOrClassName(), 
		                        session.getLoadQueryInfluencers()   );

		String sql=walker.getSQLString();
		
		return c.list();	
	}
}
