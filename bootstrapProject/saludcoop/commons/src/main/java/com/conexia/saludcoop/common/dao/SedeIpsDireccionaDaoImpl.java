package com.conexia.saludcoop.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.maestro.SedeIps;

@Component
@Transactional
public class SedeIpsDireccionaDaoImpl {
	
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<SedeIps> findSedeIpsDireccionaMedPorMunicipio(String codigoMunicipio, Long epsId, Long idSedeIpsExcluir,
            String numeroIdentificacion) {

        StringBuffer queryString = new StringBuffer();

        queryString.append("SELECT sede.* FROM maestros.sede_ips sede");
        queryString.append("	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id");
        queryString.append("	INNER JOIN maestros.municipio muni ON sede.municipio_id = muni.id ");
        //queryString.append("	INNER JOIN maestros.contrato cont ON sede.id = cont.sede_ips_id");
        queryString.append("	INNER JOIN maestros.ips ips ON sede.ips_id = ips.id");
        queryString.append(" WHERE muni.codigo = '" + codigoMunicipio + "'");
        queryString.append(" AND  eps.id = " + epsId);
        //queryString.append(" AND  cont.estado_contrato_id = 4");
        queryString.append(" AND  ips.numero_identificacion = '" + numeroIdentificacion + "'");
        if (idSedeIpsExcluir != null) {
            queryString.append(" AND sede.id != " + idSedeIpsExcluir);
        }

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(queryString.toString()).addEntity(SedeIps.class);

        return (List<SedeIps>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<SedeIps> findSedeIpsDireccionaMedPorDivisionSeccional(String codigoDivisionSeccional, Long epsId, Long idSedeIpsExcluir,
            String numeroIdentificacion) {

        StringBuffer queryString = new StringBuffer();

        queryString.append("SELECT sede.* FROM maestros.sede_ips sede");
        queryString.append("	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id");
        queryString.append("	INNER JOIN maestros.sede_ips_division_seccional sids ON sede.id = sids.sede_ips_id");
        queryString.append("	INNER JOIN maestros.division_seccional divsec ON sids.division_seccional_id = divsec.id");
        //queryString.append("	INNER JOIN maestros.contrato cont ON sede.id = cont.sede_ips_id");
        queryString.append("	INNER JOIN maestros.ips ips ON sede.ips_id = ips.id");
        queryString.append(" WHERE divsec.codigo = '" + codigoDivisionSeccional + "'");
        queryString.append(" AND eps.id = " + epsId);
        //queryString.append(" AND cont.estado_contrato_id = 4");
        queryString.append(" AND ips.numero_identificacion = '" + numeroIdentificacion + "'");

        if (idSedeIpsExcluir != null) {
            queryString.append(" AND sede.id != " + idSedeIpsExcluir);
        }

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(queryString.toString()).addEntity(SedeIps.class);

        return (List<SedeIps>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<SedeIps> findSedeIpsDireccionaMedPorRegional(String codigoRegional, Long epsId, Long idSedeIpsExcluir,
            String numeroIdentificacion) {

        StringBuffer queryString = new StringBuffer();

        queryString.append("SELECT sede.* FROM maestros.sede_ips sede");
        queryString.append("	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id");
        queryString.append("	INNER JOIN maestros.regional reg ON sede.regional_id = reg.id ");
        //queryString.append("	INNER JOIN maestros.contrato cont ON sede.id = cont.sede_ips_id");
        queryString.append("	INNER JOIN maestros.ips ips ON sede.ips_id = ips.id");
        queryString.append(" WHERE reg.codigo = '" + codigoRegional + "'");
        queryString.append(" AND eps.id = " + epsId);
        //queryString.append(" AND cont.estado_contrato_id = 4");
        queryString.append(" AND ips.numero_identificacion = '" + numeroIdentificacion + "'");

        if (idSedeIpsExcluir != null) {
            queryString.append(" AND sede.id != " + idSedeIpsExcluir);
        }

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(queryString.toString()).addEntity(SedeIps.class);

        return (List<SedeIps>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<SedeIps> findSedeIpsDireccionaMedPorPais(Long epsId, Long idSedeIpsExcluir, String numeroIdentificacion) {

        StringBuffer queryString = new StringBuffer();

        queryString.append("SELECT sede.* FROM maestros.sede_ips sede");
        queryString.append("	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id");
        //queryString.append("	INNER JOIN maestros.contrato cont ON sede.id = cont.sede_ips_id");
        queryString.append("	INNER JOIN maestros.ips ips ON sede.ips_id = ips.id");
        queryString.append(" WHERE eps.id = " + epsId);
        //queryString.append(" AND cont.estado_contrato_id = 4");
        queryString.append(" AND ips.numero_identificacion = '" + numeroIdentificacion + "'");

        if (idSedeIpsExcluir != null) {
            queryString.append(" AND sede.id != " + idSedeIpsExcluir);
        }

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(queryString.toString()).addEntity(SedeIps.class);

        return (List<SedeIps>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<SedeIps> findSedeIpsDireccionaMedEpsifarmaPorMunicipio(String codigoMunicipio, Long epsId, Long idSedeIpsExcluir) {

        StringBuffer queryString = new StringBuffer();

        queryString.append("SELECT sede.* FROM maestros.sede_ips sede");
        queryString.append("	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id");
        queryString.append("	INNER JOIN maestros.municipio muni ON sede.municipio_id = muni.id ");
        queryString.append("	INNER JOIN maestros.ips ips ON sede.ips_id = ips.id");
        queryString.append(" WHERE sede.es_epsifarma = 1");
        queryString.append(" AND muni.codigo = '" + codigoMunicipio + "'");
        queryString.append(" AND eps.id = " + epsId);

        if (idSedeIpsExcluir != null) {
            queryString.append(" AND sede.id != " + idSedeIpsExcluir);
        }

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(queryString.toString()).addEntity(SedeIps.class);

        return (List<SedeIps>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<SedeIps> findSedeIpsDireccionaMedEpsifarmaPorPais(Long epsId, Long idSedeIpsExcluir) {

        StringBuffer queryString = new StringBuffer();

        queryString.append("SELECT sede.* FROM maestros.sede_ips sede");
        queryString.append("	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id");
        queryString.append("	INNER JOIN maestros.municipio muni ON sede.municipio_id = muni.id ");
        queryString.append("	INNER JOIN maestros.ips ips ON sede.ips_id = ips.id");
        queryString.append(" WHERE sede.es_epsifarma = 1");
        queryString.append(" AND eps.id = " + epsId);

        if (idSedeIpsExcluir != null) {
            queryString.append(" AND sede.id != " + idSedeIpsExcluir);
        }

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(queryString.toString()).addEntity(SedeIps.class);

        return (List<SedeIps>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<SedeIps> findSedeIpsDireccionaMedEpsifarmaPorDivisionSeccional(String codigoDivisionSeccional, Long epsId,
            Long idSedeIpsExcluir) {

        StringBuffer queryString = new StringBuffer();

        queryString.append("SELECT sede.* FROM maestros.sede_ips sede");
        queryString.append("	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id");
        queryString.append("	INNER JOIN maestros.sede_ips_division_seccional sids ON sede.id = sids.sede_ips_id");
        queryString.append("	INNER JOIN maestros.division_seccional divsec ON sids.division_seccional_id = divsec.id");
        queryString.append("	INNER JOIN maestros.ips ips ON sede.ips_id = ips.id");
        queryString.append(" WHERE sede.es_epsifarma = 1");
        queryString.append(" AND divsec.codigo = '" + codigoDivisionSeccional + "'");
        queryString.append(" AND eps.id = " + epsId);

        if (idSedeIpsExcluir != null) {
            queryString.append(" AND sede.id != " + idSedeIpsExcluir);
        }

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(queryString.toString()).addEntity(SedeIps.class);

        return (List<SedeIps>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<SedeIps> findSedeIpsDireccionaMedEpsifarmaPorRegional(String codigoRegional, Long epsId, Long idSedeIpsExcluir) {

        StringBuffer queryString = new StringBuffer();

        queryString.append("SELECT sede.* FROM maestros.sede_ips sede");
        queryString.append("	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id");
        queryString.append("	INNER JOIN maestros.regional reg ON sede.regional_id = reg.id ");
        queryString.append("	INNER JOIN maestros.ips ips ON sede.ips_id = ips.id");
        queryString.append(" WHERE sede.es_epsifarma = 1");
        queryString.append(" AND reg.codigo	= '" + codigoRegional + "'");
        queryString.append(" AND eps.id = " + epsId);

        if (idSedeIpsExcluir != null) {
            queryString.append(" AND sede.id != " + idSedeIpsExcluir);
        }

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(queryString.toString()).addEntity(SedeIps.class);

        return (List<SedeIps>) query.list();
    }

}
