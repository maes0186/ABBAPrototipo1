package com.conexia.saludcoop.common.repository.custom;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.transaccional.Solicitud;

/**
 * Implementación de lógica personalizada de repositorio del afiliado.
 * 
 * @author Sebastián Matienzo
 */
@Component
@Transactional
public class SolicitudRepositoryImpl implements ExtendedSolicitudRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean esPrimeraFormulacionAnio(final Long solicitudId) {

		if (solicitudId == null) {
			throw new IllegalArgumentException("El parámetro solicitudId es obligatorio.");
		}

		final Criteria solicitudCriteria = this.sessionFactory.getCurrentSession().createCriteria(Solicitud.class);
		solicitudCriteria.add(Restrictions.eq("nroSolicitud", solicitudId));
		
		solicitudCriteria.setProjection(Projections.rowCount());

		return (((Long) solicitudCriteria.list().get(0)) > 0);
	}
}
