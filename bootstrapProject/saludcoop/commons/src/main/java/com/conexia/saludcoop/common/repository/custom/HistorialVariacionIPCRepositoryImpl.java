package com.conexia.saludcoop.common.repository.custom;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.maestro.HistorialVariacionIPC;

/**
 * Implementación de lógica personalizada de repositorio de historial de variación de IPC.
 * 
 * @author Sebastián Matienzo
 */
@Component
@Transactional
public class HistorialVariacionIPCRepositoryImpl implements ExtentedHistorialVariacionIPCRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialVariacionIPC> getVariacionesDesdeAnio(final Integer anio) {

		if (anio == null) {
			throw new IllegalArgumentException("El parámetro anio es obligatorio.");
		}
		
		final Criteria solicitudCriteria = this.sessionFactory.getCurrentSession().createCriteria(HistorialVariacionIPC.class);
		solicitudCriteria.add(Restrictions.ge("anio", anio));

		return (solicitudCriteria.list());
	}
}
