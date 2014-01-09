package com.conexia.saludcoop.common.repository.custom;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Programa;
import com.conexia.saludcoop.common.entity.transaccional.Solicitud;
import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.common.util.SystemConstants;

/**
 * Implementación de lógica personalizada de repositorio del afiliado.
 * 
 * @author Sebastián Matienzo
 */
@Component
@Transactional
public class AfiliadoRepositoryImpl implements ExtendedAfiliadoRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean tieneTransaccionesEnAnio(final Long afiliadoId, final Integer anio) {

		if (afiliadoId == null) {
			throw new IllegalArgumentException("El parámetro afiliadoId es obligatorio.");
		}

		if (anio == null) {
			throw new IllegalArgumentException("El parámetro anio es obligatorio.");
		}
		
		final Calendar calculoFechas = Calendar.getInstance();
		calculoFechas.set(Calendar.YEAR, anio);
		calculoFechas.set(Calendar.MONTH, Calendar.JANUARY);
		calculoFechas.set(Calendar.DATE, 1);
		calculoFechas.set(Calendar.HOUR, 0);
		calculoFechas.set(Calendar.MINUTE, 0);
		calculoFechas.set(Calendar.SECOND, 0);
		calculoFechas.set(Calendar.MILLISECOND, 0);
		
		final Date fechaInicio = calculoFechas.getTime();
		
		calculoFechas.set(Calendar.YEAR, anio + 1);
		
		final Date fechaFin = calculoFechas.getTime();
		
		final Criteria solicitudCriteria = this.sessionFactory.getCurrentSession().createCriteria(Solicitud.class);
		solicitudCriteria.add(Restrictions.ge("fechaCreacion", fechaInicio));
		solicitudCriteria.add(Restrictions.lt("fechaCreacion", fechaFin));

		final Criteria afiliadoCriteria = solicitudCriteria.createCriteria("afiliado", "afiliado", Criteria.INNER_JOIN);
		afiliadoCriteria.add(Restrictions.ge("id", afiliadoId));
		
		solicitudCriteria.setProjection(Projections.count("id"));

		return (((Long) solicitudCriteria.list().get(0)) > 0);
	}

	@Override
	public boolean esGrupoPoblacionalEximidoSubsidiado(final Long afiliadoId) {

		if (afiliadoId == null) {
			throw new IllegalArgumentException("El parámetro afiliadoId es obligatorio.");
		}
		
		final Criteria afiliadoCriteria = this.sessionFactory.getCurrentSession().createCriteria(Afiliado.class);
		afiliadoCriteria.add(Restrictions.eq("id", afiliadoId));
		afiliadoCriteria.add(Restrictions.eq("regimenAfiliacionId", RegimenAfiliacion.SUBSIDIADO.getId()));
	
		final Criteria grupoCriteria = afiliadoCriteria.createCriteria("grupoPoblacional", "grupoPoblacional", Criteria.INNER_JOIN);
		grupoCriteria.add(Restrictions.eq("eximidoRegimenSubsidiado", SystemConstants.SHORT_TRUE));
		
		afiliadoCriteria.setProjection(Projections.count("id"));

		return (((Long) afiliadoCriteria.list().get(0)) > 0);
	}
	
	
	@Override
   public List<Programa> getProgrmasByIdAfiliado(Long afiliado){
        
       StringBuffer  queryString = new StringBuffer();
       queryString.append("SELECT pro.* ");
       queryString.append("from maestros.programa pro  ");
       queryString.append("inner join maestros.afiliado_programa ap on ap.programa_id = pro.id ");
       queryString.append("inner join maestros.afiliado afi on afi.id = ap.afiliado_id ");
       queryString.append("where afi.id = "+ afiliado);
       
       Query query = this.sessionFactory.getCurrentSession()
               .createSQLQuery(queryString.toString())
               .addEntity(Programa.class);

       List<Programa> ret = (List<Programa>) query.list();
       
       return ret;
    }
}
