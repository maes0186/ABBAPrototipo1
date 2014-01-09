package com.conexia.saludcoop.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.maestro.ProcedimientoContratado;


/**
 * @author ebarbin
 *
 */
@Component
@Transactional
public class ProcedimientoContratadoDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
    public List<ProcedimientoContratado> findProcedimientosContratadosDireccionamientoPorMunicipio(
			String codigoMunicipio, Long epsId, Long procedimientoId, String codigoTipoMinuta, Long idSedeIpsExcluir) {
		
		StringBuffer  queryString = new StringBuffer();
	    
		queryString.append("SELECT proccont.* FROM maestros.sede_ips sede");
		queryString.append("	INNER JOIN maestros.eps eps");
		queryString.append("		ON sede.eps_id = eps.id");
		queryString.append("	INNER JOIN maestros.municipio muni");
		queryString.append("		ON sede.municipio_id = muni.id ");
		queryString.append("	INNER JOIN maestros.contrato cont");
		queryString.append("		ON sede.ips_id = cont.sede_ips_id");
		queryString.append("	INNER JOIN maestros.servicio_contratado servcont");
		queryString.append("		ON cont.id = servcont.contrato_id");
		queryString.append("	INNER JOIN maestros.especialidad_contratada espccont");
		queryString.append("		ON servcont.id = espccont.servicio_contratado_id");
		queryString.append("	INNER JOIN maestros.procedimiento_contratado proccont");
		queryString.append("		ON espccont.estado_especialidad_contratada_id = proccont.especialidad_contratada_id");
		queryString.append("	INNER JOIN maestros.procedimiento proce");
		queryString.append("		ON proccont.procedimiento_id = proce.id");
		
		if (codigoTipoMinuta != null){
			queryString.append("	INNER JOIN maestros.tipo_minuta minuta");
			queryString.append(" 		ON proccont.tipo_minuta_id = minuta.id");
		}
		
		queryString.append(" WHERE");
		queryString.append("	muni.codigo									= '" + codigoMunicipio + "'");
		queryString.append(" AND ");
		queryString.append("	eps.id      								= " + epsId);
		queryString.append(" AND ");
		queryString.append("	cont.estado_contrato_id    					= 4");
		queryString.append(" AND ");
		queryString.append("	proccont.estado_procedimiento_contratado_id = 4	");
		queryString.append(" AND ");
		queryString.append("	proce.id 									= " + procedimientoId);
		queryString.append(" AND ");
		queryString.append("	proce.estado_procedimiento_id 				= 1");
		
		if (codigoTipoMinuta != null){
			queryString.append(" AND ");
			queryString.append("	minuta.codigo 								= '" + codigoTipoMinuta + "'");
		} else {
			queryString.append(" AND ");
			queryString.append("	proccont.monto_fijo is not null");
		}
		
		if (idSedeIpsExcluir != null) {
			queryString.append(" AND ");
			queryString.append(" 	sede.id 							   != " + idSedeIpsExcluir);
		}
		
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(queryString.toString())
				.addEntity(ProcedimientoContratado.class);

        return (List<ProcedimientoContratado>) query.list();
	}
	
	@SuppressWarnings("unchecked")
    public List<ProcedimientoContratado> findProcedimientosContratadosDireccionamientoPorDivisionSeccional(
			String codigoDivisionSeccional, Long epsId, Long procedimientoId, String codigoTipoMinuta, Long idSedeIpsExcluir) {
		
		StringBuffer  queryString = new StringBuffer();
	    
		queryString.append("SELECT proccont.* FROM maestros.sede_ips sede");
		queryString.append("	INNER JOIN maestros.eps eps");
		queryString.append("		ON sede.eps_id = eps.id");
		queryString.append("	INNER JOIN maestros.sede_ips_division_seccional sids");
		queryString.append("		ON sede.id = sids.sede_ips_id");
		queryString.append("	INNER JOIN maestros.division_seccional divsec");
		queryString.append("		ON sids.division_seccional_id = divsec.id");
		queryString.append("	INNER JOIN maestros.contrato cont");
		queryString.append("		ON sede.ips_id = cont.sede_ips_id");
		queryString.append("	INNER JOIN maestros.servicio_contratado servcont");
		queryString.append("		ON cont.id = servcont.contrato_id");
		queryString.append("	INNER JOIN maestros.especialidad_contratada espccont");
		queryString.append("		ON servcont.id = espccont.servicio_contratado_id");
		queryString.append("	INNER JOIN maestros.procedimiento_contratado proccont");
		queryString.append("		ON espccont.estado_especialidad_contratada_id = proccont.especialidad_contratada_id");
		queryString.append("	INNER JOIN maestros.procedimiento proce");
		queryString.append("		ON proccont.procedimiento_id = proce.id");
		
		if (codigoTipoMinuta != null){
			queryString.append("	INNER JOIN maestros.tipo_minuta minuta");
			queryString.append(" 		ON proccont.tipo_minuta_id = minuta.id");
		}
		
		queryString.append(" WHERE");
		queryString.append("	divsec.codigo									= '" + codigoDivisionSeccional + "'");
		queryString.append(" AND ");
		queryString.append("	eps.id      								= " + epsId);
		queryString.append(" AND ");
		queryString.append("	cont.estado_contrato_id    					= 4");
		queryString.append(" AND ");
		queryString.append("	proccont.estado_procedimiento_contratado_id = 4");
		queryString.append(" AND ");
		queryString.append("	proce.id 									= " + procedimientoId);
		queryString.append(" AND ");
		queryString.append("	proce.estado_procedimiento_id 				= 1");
		
		if (codigoTipoMinuta != null){
			queryString.append(" AND ");
			queryString.append("	minuta.codigo 								= '" + codigoTipoMinuta + "'");
		} else {
			queryString.append(" AND ");
			queryString.append("	proccont.monto_fijo is not null");
		}
		
		if (idSedeIpsExcluir != null) {
			queryString.append(" AND ");
			queryString.append(" 	sede.id 							   != " + idSedeIpsExcluir);
		}
		
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(queryString.toString())
				.addEntity(ProcedimientoContratado.class);

        return (List<ProcedimientoContratado>) query.list();
	}
	
	@SuppressWarnings("unchecked")
    public List<ProcedimientoContratado> findProcedimientosContratadosDireccionamientoPorRegional(
			String codigoRegional, Long epsId, Long procedimientoId, String codigoTipoMinuta, Long idSedeIpsExcluir) {
		
		StringBuffer  queryString = new StringBuffer();
	    
		queryString.append("SELECT proccont.* FROM maestros.sede_ips sede");
		queryString.append("	INNER JOIN maestros.eps eps");
		queryString.append("		ON sede.eps_id = eps.id");
		queryString.append("	INNER JOIN maestros.regional reg");
		queryString.append("		ON sede.regional_id = reg.id ");
		queryString.append("	INNER JOIN maestros.contrato cont");
		queryString.append("		ON sede.ips_id = cont.sede_ips_id");
		queryString.append("	INNER JOIN maestros.servicio_contratado servcont");
		queryString.append("		ON cont.id = servcont.contrato_id");
		queryString.append("	INNER JOIN maestros.especialidad_contratada espccont");
		queryString.append("		ON servcont.id = espccont.servicio_contratado_id");
		queryString.append("	INNER JOIN maestros.procedimiento_contratado proccont");
		queryString.append("		ON espccont.estado_especialidad_contratada_id = proccont.especialidad_contratada_id");
		queryString.append("	INNER JOIN maestros.procedimiento proce");
		queryString.append("		ON proccont.procedimiento_id = proce.id");
		
		if (codigoTipoMinuta != null){
			queryString.append("	INNER JOIN maestros.tipo_minuta minuta");
			queryString.append(" 		ON proccont.tipo_minuta_id = minuta.id");
		} else {
			queryString.append(" AND ");
			queryString.append("	proccont.monto_fijo is not null");
		}
		
		queryString.append(" WHERE");
		queryString.append("	reg.codigo									= '" + codigoRegional + "'");
		queryString.append(" AND ");
		queryString.append("	eps.id      								= " + epsId);
		queryString.append(" AND ");
		queryString.append("	cont.estado_contrato_id    					= 4");
		queryString.append(" AND ");
		queryString.append("	proccont.estado_procedimiento_contratado_id = 4");
		queryString.append(" AND ");
		queryString.append("	proce.id 									= " + procedimientoId);
		queryString.append(" AND ");
		queryString.append("	proce.estado_procedimiento_id 				= 1");
		
		if (codigoTipoMinuta != null){
			queryString.append(" AND ");
			queryString.append("	minuta.codigo 								= '" + codigoTipoMinuta + "'");
		} else {
			queryString.append(" AND ");
			queryString.append("	proccont.monto_fijo is not null");
		}
		
		if (idSedeIpsExcluir != null) {
			queryString.append(" AND ");
			queryString.append(" 	sede.id 							   != " + idSedeIpsExcluir);
		}
		
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(queryString.toString())
				.addEntity(ProcedimientoContratado.class);

        return (List<ProcedimientoContratado>) query.list();
	}
	
	@SuppressWarnings("unchecked")
    public List<ProcedimientoContratado> findProcedimientosContratadosDireccionamientoPorPais(
    		Long epsId, Long procedimientoId, String codigoTipoMinuta, Long idSedeIpsExcluir) {
		
		StringBuffer  queryString = new StringBuffer();
	    
		queryString.append("SELECT proccont.* FROM maestros.sede_ips sede");
		queryString.append("	INNER JOIN maestros.eps eps");
		queryString.append("		ON sede.eps_id = eps.id");
		queryString.append("	INNER JOIN maestros.contrato cont");
		queryString.append("		ON sede.ips_id = cont.sede_ips_id");
		queryString.append("	INNER JOIN maestros.servicio_contratado servcont");
		queryString.append("		ON cont.id = servcont.contrato_id");
		queryString.append("	INNER JOIN maestros.especialidad_contratada espccont");
		queryString.append("		ON servcont.id = espccont.servicio_contratado_id");
		queryString.append("	INNER JOIN maestros.procedimiento_contratado proccont");
		queryString.append("		ON espccont.estado_especialidad_contratada_id = proccont.especialidad_contratada_id");
		queryString.append("	INNER JOIN maestros.procedimiento proce");
		queryString.append("		ON proccont.procedimiento_id = proce.id");
		
		if (codigoTipoMinuta != null){
			queryString.append("	INNER JOIN maestros.tipo_minuta minuta");
			queryString.append(" 		ON proccont.tipo_minuta_id = minuta.id");
		}

		queryString.append(" WHERE");
		queryString.append("	eps.id      								= " + epsId);
		queryString.append(" AND ");
		queryString.append("	cont.estado_contrato_id    					= 4");
		queryString.append(" AND ");
		queryString.append("	proccont.estado_procedimiento_contratado_id = 4");
		queryString.append(" AND ");
		queryString.append("	proce.id 									= " + procedimientoId);
		queryString.append(" AND ");
		queryString.append("	proce.estado_procedimiento_id 				= 1");
		
		if (codigoTipoMinuta != null){
			queryString.append(" AND ");
			queryString.append("	minuta.codigo 								= '" + codigoTipoMinuta + "'");
		} else {
			queryString.append(" AND ");
			queryString.append("	proccont.monto_fijo is not null");
		}
		
		if (idSedeIpsExcluir != null) {
			queryString.append(" AND ");
			queryString.append(" 	sede.id 							   != " + idSedeIpsExcluir);
		}
		
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(queryString.toString())
				.addEntity(ProcedimientoContratado.class);

        return (List<ProcedimientoContratado>) query.list();
	}
}
