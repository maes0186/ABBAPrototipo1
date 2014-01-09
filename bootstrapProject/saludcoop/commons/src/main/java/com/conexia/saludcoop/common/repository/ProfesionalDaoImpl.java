package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.crud.ProfesionalItemVO;
import com.conexia.saludcoop.common.entity.maestro.Profesional;
import com.conexia.saludcoop.common.util.Pagination;

@Component
@Transactional
public class ProfesionalDaoImpl implements ProfesionalDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	public List<Profesional> findBySede( String registroMedico, Integer especialidad,
			Integer tipoDocumento, String numeroDocumento, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Long sedeId){

		Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select p.* from maestros.profesional p "
				+ "inner Join maestros.profesional_especialidad_sede_ips ps on ps.profesional_id = p.id "
				+ "inner join maestros.tipo_identificacion_afiliado tia on p.identificacion_profesional_id = tia.id "
				+ " where ps.sede_ips_id = ? "
				+ "	  and p.registro_medico = COALESCE(Cast(? as varchar), p.registro_medico)"
				+ "   and tia.id = COALESCE(Cast(? as int), tia.id)"
				+ "   and p.numero_identificacion = COALESCE(Cast(? as varchar), p.numero_identificacion)"
				+ "   and p.primer_nombre like ?"
				+ "   and p.segundo_nombre like ?"
				+ "   and p.primer_apellido like ?"
				+ "   and p.segundo_apellido like ?"
				+ "   and ps.especialidad_id = COALESCE(Cast(? as int), ps.especialidad_id)"
				 
				).addEntity(Profesional.class);
		

		query.setParameter(0,sedeId);  
		query.setParameter(1,registroMedico);       
		query.setParameter(2,tipoDocumento);        
		query.setParameter(3,numeroDocumento);      
		query.setParameter(4,primerNombre);         
		query.setParameter(5,segundoNombre);        
		query.setParameter(6,primerApellido);       
		query.setParameter(7,segundoApellido);
		query.setParameter(8,especialidad);      
		             
		
		
		List<Profesional> p = (List<Profesional>) query.list();
		return p;
	}

	/**
	 * Busca los autorizaciones.
	 * 
	 * @return El listado.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProfesionalItemVO> getProfesionales(String primerNombre,
			String segundoNombre, String primerApellido,
			String segundoApellido, Integer tipoDocumento,String numeroDocumento, Pagination pagination,String registroMedico){

		final Criteria profesionalCriteria = sessionFactory.getCurrentSession().createCriteria(Profesional.class);
		profesionalCriteria.setProjection(Projections.countDistinct("id"));
		if (!primerNombre.isEmpty())
			profesionalCriteria.add(Restrictions.eq("primerNombre", primerNombre));
		if (!segundoNombre.isEmpty())
			profesionalCriteria.add(Restrictions.eq("segundoNombre", segundoNombre));
		if (!primerApellido.isEmpty())
			profesionalCriteria.add(Restrictions.eq("primerApellido", primerApellido));
		if (!segundoApellido.isEmpty())
			profesionalCriteria.add(Restrictions.eq("segundoApellido", segundoApellido));
		if (tipoDocumento!=null)
			profesionalCriteria.add(Restrictions.eq("tipoDocumento.id", tipoDocumento));
		if (!registroMedico.isEmpty())
			profesionalCriteria.add(Restrictions.eq("registroMedico", registroMedico));
		if (!numeroDocumento.isEmpty())
			profesionalCriteria.add(Restrictions.eq("numeroIdentificacion", numeroDocumento));
		
		profesionalCriteria.createAlias("identificacionProfesional", "tipoDocumento");
		
		Long count = (Long) profesionalCriteria.uniqueResult();

		pagination.setTotalPages((int) Math.ceil(count.doubleValue() / pagination.getPageSize()));
		
		final ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("id"), "id");
		projList.add(Projections.property("primerNombre"), "primerNombre");
		projList.add(Projections.property("segundoNombre"), "segundoNombre");
		projList.add(Projections.property("primerApellido"), "primerApellido");
		projList.add(Projections.property("segundoApellido"), "segundoApellido");
		projList.add(Projections.property("registroMedico"), "registroMedico");
		projList.add(Projections.property("numeroIdentificacion"), "numeroIdentificacion");
		projList.add(Projections.property("tipoDocumento.descripcion"), "tipoDocumento");
		
		profesionalCriteria.setProjection(projList);
		
		profesionalCriteria.setResultTransformer(Transformers.aliasToBean(ProfesionalItemVO.class));
		Integer offset = (pagination.getActualPage() - 1) * pagination.getPageSize();
		profesionalCriteria.setFirstResult(offset);
		profesionalCriteria.setMaxResults(pagination.getPageSize());
		
		return profesionalCriteria.list();
	}

	


}
