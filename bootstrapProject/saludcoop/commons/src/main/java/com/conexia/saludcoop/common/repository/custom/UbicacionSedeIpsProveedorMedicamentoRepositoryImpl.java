package com.conexia.saludcoop.common.repository.custom;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorMedicamento;
import com.conexia.saludcoop.common.repository.UbicacionSedeIpsEfectorProcedimientoRepository;

/**
 * Implementación de lógica personalizada de repositorio del afiliado.
 * 
 * @author Sebastián Matienzo
 */
@Component
@Transactional
public class UbicacionSedeIpsProveedorMedicamentoRepositoryImpl implements ExtendedUbicacionSedeIpsProveedorMedicamentoRepository {

	/**
	 * Fábrica de sesiones.
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UbicacionSedeIpsProveedorMedicamento> getSedesIpsPorMunicipio(final Long epsId, final Long medicamentoId,
			final Long sedeIpsExcluidaId, final Long municipioId, final int numeroPagina) {

		final Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UbicacionSedeIpsProveedorMedicamento.class);
		criteria.add(Restrictions.eq("municipioId", municipioId));
		
		this.addCommonConditionsToCriteria(criteria, epsId, medicamentoId, sedeIpsExcluidaId, numeroPagina);
		
		return ((List<UbicacionSedeIpsProveedorMedicamento>) criteria.list());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UbicacionSedeIpsProveedorMedicamento> getSedesIpsPorDivisionSeccional(final Long epsId, final Long medicamentoId,
			final Long sedeIpsExcluidaId, final List<Long> divisionesSeccionalesId, final int numeroPagina) {

		final Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UbicacionSedeIpsProveedorMedicamento.class);
		criteria.add(Restrictions.in("divisionSeccionalId", divisionesSeccionalesId));
		
		this.addCommonConditionsToCriteria(criteria, epsId, medicamentoId, sedeIpsExcluidaId, numeroPagina);
		
		return ((List<UbicacionSedeIpsProveedorMedicamento>) criteria.list());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UbicacionSedeIpsProveedorMedicamento> getSedesIpsPorRegional(final Long epsId, final Long medicamentoId,
			final Long sedeIpsExcluidaId, final List<Long> regionalesId, final int numeroPagina) {

		final Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UbicacionSedeIpsProveedorMedicamento.class);
		criteria.add(Restrictions.in("regionalId", regionalesId));
		
		this.addCommonConditionsToCriteria(criteria, epsId, medicamentoId, sedeIpsExcluidaId, numeroPagina);
		
		return ((List<UbicacionSedeIpsProveedorMedicamento>) criteria.list());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UbicacionSedeIpsProveedorMedicamento> getSedesIpsNivelNacional(final Long epsId, final Long medicamentoId,
			final Long sedeIpsExcluidaId, final int numeroPagina) {

		final Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UbicacionSedeIpsProveedorMedicamento.class);
		
		this.addCommonConditionsToCriteria(criteria, epsId, medicamentoId, sedeIpsExcluidaId, numeroPagina);
		
		return ((List<UbicacionSedeIpsProveedorMedicamento>) criteria.list());
	}
	
	/**
	 * Agrega al criteria de búsqueda específico, los parámetros comunes a todas las búsquedas de esta entidad.
	 * 
	 * @param criteria Criteria sobre el cual operar.
	 * @param epsId Identificador de la EPS correspondiente.
	 * @param medicamentoId Identificador del procedimiento.
	 * @param tipoMinutaCodigo Código del tipo de minuta (o null si ninguno).
	 * @param sedeIpsExcluidaId Identificador de la sede de IPS a excluir de los resultados (o null si debe evaluar todas).
	 * @param municipioCodigo Código del municipio en el cual deben estar las IPS.
	 */
	private void addCommonConditionsToCriteria(final Criteria criteria, final Long epsId, final Long medicamentoId,
			final Long sedeIpsExcluidaId, final int numeroPagina) {
		
		/* Por el momento, el identificador de la EPS no se utiliza como elemento de filtrado */
		
		criteria.add(Restrictions.eq("medicamentoId", medicamentoId));
		
		if (sedeIpsExcluidaId != null) {
			criteria.add(Restrictions.ne("sedeIpsId", sedeIpsExcluidaId));
		}
		
		criteria.setFirstResult(numeroPagina * UbicacionSedeIpsEfectorProcedimientoRepository.TAMANIO_PAGINA);
		criteria.setMaxResults(UbicacionSedeIpsEfectorProcedimientoRepository.TAMANIO_PAGINA);
		
		criteria.addOrder(Order.asc("sedeIpsId"));
	}
}
