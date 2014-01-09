package com.conexia.saludcoop.common.repository.custom;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsEfectorProcedimiento;
import com.conexia.saludcoop.common.repository.UbicacionSedeIpsEfectorProcedimientoRepository;

/**
 * Implementación de lógica personalizada de repositorio del afiliado.
 * 
 * @author Sebastián Matienzo
 */
@Component
@Transactional
public class UbicacionSedeIpsEfectorProcedimientoRepositoryImpl implements ExtendedUbicacionSedeIpsEfectorProcedimientoRepository {

	/**
	 * Fábrica de sesiones.
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UbicacionSedeIpsEfectorProcedimiento> getSedesIpsPorMunicipio(final Long epsId, final Long procedimientoId,
			final String tipoMinutaCodigo, final Long sedeIpsExcluidaId, final Long municipioId, 
			final boolean debeOrdenarPorValorAjustado, final int numeroPagina) {

		final Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UbicacionSedeIpsEfectorProcedimiento.class);
		criteria.add(Restrictions.eq("municipioId", municipioId));
		
		this.addCommonConditionsToCriteria(criteria, epsId, procedimientoId, tipoMinutaCodigo, sedeIpsExcluidaId, 
											debeOrdenarPorValorAjustado, numeroPagina);
		
		return ((List<UbicacionSedeIpsEfectorProcedimiento>) criteria.list());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UbicacionSedeIpsEfectorProcedimiento> getSedesIpsPorDivisionSeccional(final Long epsId, final Long procedimientoId,
			final String tipoMinutaCodigo, final Long sedeIpsExcluidaId, final List<Long> divisionesSeccionalesId, 
			final boolean debeOrdenarPorValorAjustado, final int numeroPagina) {

		final Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UbicacionSedeIpsEfectorProcedimiento.class);
		criteria.add(Restrictions.in("divisionSeccionalId", divisionesSeccionalesId));
		
		this.addCommonConditionsToCriteria(criteria, epsId, procedimientoId, tipoMinutaCodigo, sedeIpsExcluidaId, 
											debeOrdenarPorValorAjustado, numeroPagina);
		
		return ((List<UbicacionSedeIpsEfectorProcedimiento>) criteria.list());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UbicacionSedeIpsEfectorProcedimiento> getSedesIpsPorRegional(final Long epsId, final Long procedimientoId,
			final String tipoMinutaCodigo, final Long sedeIpsExcluidaId, final List<Long> regionalesId, 
			final boolean debeOrdenarPorValorAjustado, final int numeroPagina) {

		final Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UbicacionSedeIpsEfectorProcedimiento.class);
		criteria.add(Restrictions.in("regionalId", regionalesId));
		
		this.addCommonConditionsToCriteria(criteria, epsId, procedimientoId, tipoMinutaCodigo, sedeIpsExcluidaId, 
											debeOrdenarPorValorAjustado, numeroPagina);
		
		return ((List<UbicacionSedeIpsEfectorProcedimiento>) criteria.list());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UbicacionSedeIpsEfectorProcedimiento> getSedesIpsNivelNacional(final Long epsId, final Long procedimientoId,
			final String tipoMinutaCodigo, final Long sedeIpsExcluidaId, final boolean debeOrdenarPorValorAjustado,
			final int numeroPagina) {

		final Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UbicacionSedeIpsEfectorProcedimiento.class);
		
		this.addCommonConditionsToCriteria(criteria, epsId, procedimientoId, tipoMinutaCodigo, sedeIpsExcluidaId, 
											debeOrdenarPorValorAjustado, numeroPagina);
		
		return ((List<UbicacionSedeIpsEfectorProcedimiento>) criteria.list());
	}
	
	/**
	 * Agrega al criteria de búsqueda específico, los parámetros comunes a todas las búsquedas de esta entidad.
	 * 
	 * @param criteria Criteria sobre el cual operar.
	 * @param epsId Identificador de la EPS correspondiente.
	 * @param procedimientoId Identificador del procedimiento.
	 * @param tipoMinutaCodigo Código del tipo de minuta (o null si ninguno).
	 * @param sedeIpsExcluidaId Identificador de la sede de IPS a excluir de los resultados (o null si debe evaluar todas).
	 * @param debeOrdenarPorValorAjustado Indica si debe ordenar por el valor ajustado, o por el valor original.
	 * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
	 * @param municipioCodigo Código del municipio en el cual deben estar las IPS.
	 */
	private void addCommonConditionsToCriteria(final Criteria criteria, final Long epsId, final Long procedimientoId,
			final String tipoMinutaCodigo, final Long sedeIpsExcluidaId, final boolean debeOrdenarPorValorAjustado,
			final int numeroPagina) {
		
		criteria.add(Restrictions.eq("epsId", epsId));
		criteria.add(Restrictions.eq("procedimientoId", procedimientoId));
		
		if (tipoMinutaCodigo != null) {
			criteria.add(Restrictions.eq("tipoMinutaCodigo", tipoMinutaCodigo));
		} else {
			criteria.add(Restrictions.isNotNull("procedimientoMontoFijo"));
		}
		
		if (sedeIpsExcluidaId != null) {
			criteria.add(Restrictions.ne("sedeIpsId", sedeIpsExcluidaId));
		}
		
		final String propiedadOrdenamiento = (debeOrdenarPorValorAjustado) ? "procedimientoValorAjustado" : "procedimientoValorOriginal";
		
		criteria.setFirstResult(numeroPagina * UbicacionSedeIpsEfectorProcedimientoRepository.TAMANIO_PAGINA);
		criteria.setMaxResults(UbicacionSedeIpsEfectorProcedimientoRepository.TAMANIO_PAGINA);
		
		criteria.addOrder(Order.asc(propiedadOrdenamiento));
	}
}
