package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.maestro.ProfesionalEspecialidad;

@Component
@Transactional
public class ProfesionalEspecialidadDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<ProfesionalEspecialidad> findBySedeIps(String registroMedico, Integer tipoDocumento, String numeroDocumento, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, Integer especialidad, Long sedeIpsId) {
		
		
		StringBuffer  queryString = new StringBuffer();
		queryString.append("SELECT p.* ");
		queryString.append("FROM   maestros.profesional_especialidad p ");
		queryString.append("       INNER JOIN maestros.profesional pro ");
		queryString.append("               ON pro.id = p.profesional_id ");
		queryString.append("       INNER JOIN maestros.especialidad esp ");
		queryString.append("               ON esp.id = p.especialidad_id ");
		queryString.append("       INNER JOIN maestros.profesional_especialidad_x_sede_ips esips ");
		queryString.append("               ON esips.profesional_especialidad_id = p.id ");
		queryString.append("       INNER JOIN maestros.sede_ips ips ");
		queryString.append("               ON esips.sede_ips_id = ips.id ");
		
		queryString.append("WHERE  1=1 ");

		if (registroMedico != null && !registroMedico.isEmpty())
			queryString.append(" and   pro.registro_medico LIKE '%" + registroMedico
					+ "%' ");

		if (tipoDocumento != null)
			queryString.append(" AND pro.identificacion_profesional_id = "
					+ tipoDocumento.intValue() + " ");

		if (numeroDocumento != null && !numeroDocumento.isEmpty())
			queryString.append(" AND pro.numero_identificacion = '"
					+ numeroDocumento + "' ");
		if (primerNombre != null)
			queryString.append("       AND pro.primer_nombre LIKE '%"
					+ primerNombre + "%' ");
		if (segundoNombre != null)
			queryString.append("       AND pro.segundo_nombre LIKE '%"
					+ segundoNombre + "%' ");
		if (primerApellido != null)
			queryString.append("       AND pro.primer_apellido LIKE '%"
					+ primerApellido + "%' ");
		if (segundoApellido != null)
			queryString.append("       AND pro.segundo_apellido LIKE '%"
					+ segundoApellido + "%' ");
		if (especialidad != null)
			queryString.append("       AND esp.id = " + especialidad + " ");
		if (sedeIpsId != null)
			queryString.append("       AND ips.id = " + sedeIpsId + " ");
		
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(queryString.toString())
				.addEntity(ProfesionalEspecialidad.class);

		List<ProfesionalEspecialidad> ret = (List<ProfesionalEspecialidad>) query.list();

		return ret;
		
	}
}
