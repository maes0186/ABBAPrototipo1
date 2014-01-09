package com.conexia.saludcoop.common.repository.custom;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.view.TarifaMedicamentoView;

/**
 * Implementación de lógica personalizada de repositorio de tarifa de medicamento.
 * 
 * @author Sebastián Matienzo
 */
@Component
@Transactional
public class TarifaMedicamentoRepositoryImpl implements ExtendedTarifaMedicamentoRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
    @Override
	public TarifaMedicamentoView getTarifaMedicamento(final Long sedeIpsId, final Long medicamentoId,
			final Long servicioId, final Integer especialidadId, final Date fechaSolicitud) {

		if (sedeIpsId == null) {
			throw new IllegalArgumentException("El parámetro sedeIpsId es obligatorio.");
		}

		if (medicamentoId == null) {
			throw new IllegalArgumentException("El parámetro medicamentoId es obligatorio.");
		}

		if (servicioId == null) {
			throw new IllegalArgumentException("El parámetro servicioId es obligatorio.");
		}

		if (especialidadId == null) {
			throw new IllegalArgumentException("El parámetro especialidadId es obligatorio.");
		}

		if (fechaSolicitud == null) {
			throw new IllegalArgumentException("El parámetro fechaSolicitud es obligatorio.");
		}
		
		final Criteria solicitudCriteria = this.sessionFactory.getCurrentSession().createCriteria(TarifaMedicamentoView.class);
		solicitudCriteria.add(Restrictions.eq("sedeIpsId", sedeIpsId));
		solicitudCriteria.add(Restrictions.eq("medicamentoId", medicamentoId));
		solicitudCriteria.add(Restrictions.eq("servicioId", servicioId));
		solicitudCriteria.add(Restrictions.eq("especialidadId", especialidadId));
		
		solicitudCriteria.add(Restrictions.le("fechaInicioContrato", fechaSolicitud));
		solicitudCriteria.add(Restrictions.ge("fechaFinalizacionContrato", fechaSolicitud));
		solicitudCriteria.add(Restrictions.ge("fechaFinalizacionServicioContratado", fechaSolicitud));
		solicitudCriteria.add(Restrictions.ge("fechaFinalizacionEspecialidadContratada", fechaSolicitud));
		solicitudCriteria.add(Restrictions.ge("fechaFinalizacionMedicamentoContratado", fechaSolicitud));

        List<TarifaMedicamentoView> result = solicitudCriteria.list();
        
        if(result == null || result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
	}
}
