package com.conexia.saludcoop.common.repository;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.conexia.saludcoop.common.entity.maestro.SedeIps;

@Component
@Transactional
public class SedeIpsDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public List<SedeIps> findByIPS(Integer tipoIdentificacion, String numeroIdentificacion, String razonSocial, 
			String departamento, String municipio, String direccion, Long epsId){


		StringBuffer  queryString = new StringBuffer();
		queryString.append("SELECT s.* ");
		queryString.append("FROM   maestros.sede_ips s ");
		queryString.append("       INNER JOIN maestros.ips ips ");
		queryString.append("               ON ips.id = s.ips_id ");
		queryString.append("       INNER JOIN maestros.municipio m ");
		queryString.append("               ON m.id = s.municipio_id ");
		queryString.append("       INNER JOIN maestros.departamento d ");
		queryString.append("               ON d.id = m.departamento_id ");
		queryString.append("       LEFT JOIN maestros.eps eps ");
		queryString.append("               ON eps.id = s.eps_id ");
		
		queryString.append("WHERE  1 = 1 ");

		if(tipoIdentificacion!= null && tipoIdentificacion >0)
			queryString.append("       AND ips.tipo_identificacion_id = "+ String.valueOf(tipoIdentificacion));

		if(numeroIdentificacion!= null && !numeroIdentificacion.isEmpty())
			queryString.append("       AND ips.numero_identificacion = '" + numeroIdentificacion.toUpperCase() + "'");

		if(razonSocial!= null && !razonSocial.isEmpty())
			queryString.append("       AND ips.razon_social LIKE '%" + razonSocial.toUpperCase() + "%'");

		if(departamento!= null && !departamento.isEmpty())
			queryString.append("       AND d.id = '" + departamento + "'");

		if(municipio != null && !municipio.isEmpty())
			queryString.append("       AND m.id = '" + municipio + "'");

		if(direccion!= null && !direccion.isEmpty())
			queryString.append("       AND s.direccion LIKE '%" + direccion.toUpperCase()+ "%'");
		
		if(epsId != null)
			queryString.append("       AND s.eps_id = '" + epsId.intValue() + "'");
		
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(queryString.toString())
				.addEntity(SedeIps.class);

		@SuppressWarnings("unchecked")
        List<SedeIps> ret = (List<SedeIps>) query.list();

		return ret;
	}
}
