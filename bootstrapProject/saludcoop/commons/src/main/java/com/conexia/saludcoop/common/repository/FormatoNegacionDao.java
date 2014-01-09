package com.conexia.saludcoop.common.repository;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.conexia.common.persistence.dao.GenericDao;

import com.conexia.saludcoop.common.dto.FormatoNegacionServiciosDto;
import com.conexia.saludcoop.common.entity.transaccional.Solicitud;

/**
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 26/12/2013
 * 
 */
@Component
@Transactional
public class FormatoNegacionDao extends GenericDao<Solicitud, Long> {

    @Autowired
    private SessionFactory sessionFactory;

    private static Logger LOGGER = LoggerFactory.getLogger(FormatoNegacionDao.class);

    /**
     * Consulta la información para la generación del formato de negación de servicios
     * 
     * @param nroItem
     *            Número del item para el cual se generará el formato
     * @return Información para generar el formato correspondiente al item enviado por parámetro
     * 
     * @throws IllegalArgumentException
     *             si el número del item es null
     */
    public FormatoNegacionServiciosDto getDatosFormato(Long nroItem) throws IllegalArgumentException {

        if (nroItem == null) {
            throw new IllegalArgumentException("El parametro afiliadoId es obligatorio.");
        }

        try {
            int paramsIndex = 0;
            StringBuilder select = new StringBuilder();
            select.append("SELECT   a.primer_nombre primerNombre, a.segundo_nombre segundoNombre, ");
            select.append("         a.primer_apellido primerApellido, a.segundo_apellido segundoApellido, ");
            select.append("         s.fecha_creacion fechaSolicitud, aut.fecha_update_cambio_estado fechaNegacion, ");
            select.append("         e.razon_social eps, s.numero_solicitud numeroSolicitud, ");
            select.append("         ti.descripcion tipoIdentificacion, a.numero_identificacion numeroIdentificacion, ");
            select.append("         a.telefono_residencial telefonoResidencial, a.telefono_celular telefonoCelular, ");
            select.append("         m.descripcion ciudadMunicipio, d.descripcion departamento, ");
            select.append("         a.sem_sgsss numerosemanassgss, eaf.descripcion estadoAfiliacion, ");
            select.append("         u.name nombreFuncionarioNiega, a.regimen_afiliacion_enum tipoPlanUsuario, ");
            select.append("         can.justificacion justificacionNacional, ");
            select.append("         car.justificacion justificacionRegional, ");
            select.append("         med.codigo codMedicamento, med.descripcion descMedicamento, ");
            select.append("         pro.codigo codProcedimiento, pro.descripcion descProcedimiento, ");
            select.append("         insu.codigo codInsumo, insu.descripcion descInsumo ");
            select.append("FROM transaccional.solicitud_item si ");
            select.append("     JOIN transaccional.solicitud s ON s.id = si.solicitud_id ");
            select.append("     JOIN maestros.afiliado a ON s.afiliado_id = a.id ");
            select.append("     JOIN transaccional.autorizacion aut ON si.autorizacion_id = aut.id ");
            select.append("     JOIN maestros.eps e ON a.eps_id = e.id ");
            select.append("     JOIN maestros.tipo_identificacion ti ON a.tipo_identificacion_id = ti.id ");
            select.append("     JOIN maestros.municipio m ON a.municipio_residencia_id = m.id ");
            select.append("     JOIN maestros.departamento d ON m.departamento_id = d.id ");
            select.append("     JOIN maestros.estado_afiliacion eaf ON a.estado_afiliacion_id = eaf.id ");
            select.append("     JOIN security.[user] u ON s.user_id = u.id ");
            select.append("     LEFT JOIN transaccional.concepto_autorizacion car ON aut.concepto_regional_id = car.id ");
            select.append("     LEFT JOIN transaccional.concepto_autorizacion can ON aut.concepto_nacional_id = can.id ");
            select.append("     LEFT JOIN transaccional.solicitud_medicamento sm ON sm.solicitud_item_id = si.id ");
            select.append("     LEFT JOIN transaccional.solicitud_procedimiento sp ON sp.solicitud_item_id = si.id ");
            select.append("     LEFT JOIN transaccional.solicitud_insumo sin ON sin.solicitud_item_id = si.id ");
            select.append("     LEFT JOIN maestros.medicamento med ON sm.medicamento_id = med.id ");
            select.append("     LEFT JOIN maestros.procedimiento pro ON sp.procedimiento_id = pro.id ");
            select.append("     LEFT JOIN maestros.insumo insu ON sin.insumo_id = insu.id ");
            select.append("WHERE si.id = ?");

            Query query = this.sessionFactory.getCurrentSession().createSQLQuery(select.toString());
            query.setResultTransformer(Transformers.aliasToBean(FormatoNegacionServiciosDto.class));

            query.setParameter(paramsIndex++, nroItem);

            query.setMaxResults(1);

            FormatoNegacionServiciosDto dto = (FormatoNegacionServiciosDto) query.uniqueResult();
            return dto;
        } catch (HibernateException e) {
            LOGGER.error("HibernateException Error SolicitudItemDao:", e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error SolicitudItemDao:", e);
            throw e;
        }
    }

}
