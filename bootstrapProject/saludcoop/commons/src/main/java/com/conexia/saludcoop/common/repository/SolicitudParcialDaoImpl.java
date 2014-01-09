package com.conexia.saludcoop.common.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.crud.ProfesionalItemVO;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudParcial;
import com.conexia.saludcoop.common.repository.exceptions.FormatoParametroException;
import com.conexia.saludcoop.common.util.Pagination;

@Component
@Transactional
public class SolicitudParcialDaoImpl implements SolicitudParcialDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudParcial> findForBandeja(Integer tipoDocumentoAfiliado, String numeroDocumentoAfiliado, Integer lineaDeFrenteId, String fechaDesde, String fechaHasta, Integer nroSolicitud, Pagination pagina) throws FormatoParametroException {

		int paramsIndex = 0, paramsIndexCount = 0;

		SimpleDateFormat sfdDMA = new SimpleDateFormat("dd-MM-yyyy");
		
		 Integer offset = (pagina.getActualPage() - 1) * pagina.getPageSize();
		 Integer limite = (pagina.getPageSize() * pagina.getActualPage() + 1);
		 

		String iniPaginacion = "SELECT * FROM (SELECT ROW_NUMBER()Over(order by fecha_creacion asc) As RowNum, sp.*";
		String finPaginacion = ") AS ResultadoPaginado WHERE RowNum > "+offset +" and RowNum <= "+limite +"";
		
		StringBuffer select = new StringBuffer(" from transaccional.solicitud_parcial sp ");
		StringBuffer joins = new StringBuffer(" inner join security.[user] u on sp.user_id = u.id " + " inner join security.usuario_entidad ue on ue.usuario_id = u.id "
				+ " inner join maestros.linea_de_frente ldf on ldf.id = ue.ldf_id ");
		StringBuffer where = new StringBuffer(" where ldf.id = ? ");

		if (tipoDocumentoAfiliado != null && !StringUtils.isEmpty(numeroDocumentoAfiliado)) {
			where.append(" and sp.tipo_identificacion_afiliado_id = ? and sp.numero_identificacion_afiliado = ? ");
		}

		if (nroSolicitud != null) {
			where.append(" and numero_solicitud = ?");
		}

		if (!StringUtils.isEmpty(fechaDesde) && !StringUtils.isEmpty(fechaHasta)) {
			where.append(" and CAST(sp.fecha_creacion AS DATE) between CAST(? AS DATE) and CAST(? AS DATE) ");
		} else if (!StringUtils.isEmpty(fechaDesde)) {
			where.append(" and CAST(sp.fecha_creacion AS DATE) >= CAST(? AS DATE) ");
		} else if (!StringUtils.isEmpty(fechaHasta)) {
			where.append(" and CAST(sp.fecha_creacion AS DATE) <= CAST(? AS DATE) ");
		}
		String queryStr = select.toString() + joins.toString() + where.toString();
		
		String queryStrCount = "select count(0) "+select.toString() + joins.toString() + where.toString();
		
		queryStr = iniPaginacion + queryStr +  finPaginacion;

		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(queryStr).addEntity(SolicitudParcial.class);
		Query queryCount = this.sessionFactory.getCurrentSession().createSQLQuery(queryStrCount);

		query.setParameter(paramsIndex++, lineaDeFrenteId);
		queryCount.setParameter(paramsIndexCount++, lineaDeFrenteId);

		if (tipoDocumentoAfiliado != null && !StringUtils.isEmpty(numeroDocumentoAfiliado)) {
			query.setParameter(paramsIndex++, tipoDocumentoAfiliado);
			query.setParameter(paramsIndex++, numeroDocumentoAfiliado);
			queryCount.setParameter(paramsIndexCount++, tipoDocumentoAfiliado);
			queryCount.setParameter(paramsIndexCount++, numeroDocumentoAfiliado);
		}

		if (nroSolicitud != null) {
			query.setParameter(paramsIndex++, nroSolicitud);
			queryCount.setParameter(paramsIndexCount++, nroSolicitud);
		}

		if (!StringUtils.isEmpty(fechaDesde) && !StringUtils.isEmpty(fechaHasta)) {
			try {
				query.setParameter(paramsIndex++, sfdDMA.parse(fechaDesde));
				query.setParameter(paramsIndex++, sfdDMA.parse(fechaHasta));
				queryCount.setParameter(paramsIndexCount++, sfdDMA.parse(fechaDesde));
				queryCount.setParameter(paramsIndexCount++, sfdDMA.parse(fechaHasta));
			} catch (ParseException e) {
				throw new FormatoParametroException("formato de fecha incorrecto" + fechaDesde );
			}
		} else if (!StringUtils.isEmpty(fechaDesde)) {
			try {
				query.setParameter(paramsIndex++, sfdDMA.parse(fechaDesde));
				queryCount.setParameter(paramsIndexCount++, sfdDMA.parse(fechaDesde));
			} catch (ParseException e) {
				throw new FormatoParametroException("formato de fecha incorrecto" + fechaDesde );
			}
		} else if (!StringUtils.isEmpty(fechaHasta)) {
			try {
				query.setParameter(paramsIndex++, sfdDMA.parse(fechaHasta));
				queryCount.setParameter(paramsIndexCount++, sfdDMA.parse(fechaHasta));
			} catch (ParseException e) {
				throw new FormatoParametroException("formato de fecha incorrecto" +fechaHasta);
			}
		}
	
		List<Integer> listCount = queryCount.list();
		if(listCount != null && !listCount.isEmpty()){
		    pagina.setTotalPages((listCount.get(0) / pagina.getPageSize()) + (listCount.get(0)%pagina.getPageSize()>0?1:0));
		    pagina.setTotalItems(new Long(listCount.get(0)));
		}
		
		List<SolicitudParcial> p = (List<SolicitudParcial>) query.list();
		return p;
	}
}
