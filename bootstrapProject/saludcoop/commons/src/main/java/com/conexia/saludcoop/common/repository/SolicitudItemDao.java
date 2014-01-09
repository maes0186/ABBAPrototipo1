package com.conexia.saludcoop.common.repository;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.conexia.common.persistence.dao.GenericDao;

import com.conexia.saludcoop.common.entity.transaccional.MedicamentoPosPrevio;
import com.conexia.saludcoop.common.entity.transaccional.ProcedimientoPosPrevio;
import com.conexia.saludcoop.common.entity.transaccional.Solicitud;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;

/**
 * <b>SolicitudItem DAO</b> Clase encargada de realizar las consultas de las solicitudes para las bandejas CTC y alto costo
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 17/10/2013
 * 
 */
@Component
@Transactional
public class SolicitudItemDao extends GenericDao<Solicitud, Long> {

    @Autowired
    private SessionFactory sessionFactory;

    private static Logger LOGGER = LoggerFactory.getLogger(SolicitudItemDao.class);

    /**
     * Consulta una instancia de solicitudItem por su numero
     * 
     * @param nroItem
     *            Número del item a consultar
     * @return Item correspondiente al número enviado por parámetro
     */
    public SolicitudItem getSolicitudItemByNumero(Long nroItem) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SolicitudItem.class, "solicitudItem");
            criteria.add(Restrictions.eq("nroItem", nroItem));
            SolicitudItem item = (SolicitudItem) criteria.uniqueResult();
            Hibernate.initialize(item.getDiagnostico());
            Hibernate.initialize(item.getSolicitud().getDocumentosSoporte());
            item.getSolicitud().getDocumentosSoporte().size();
            Hibernate.initialize(item.getDocumentosSoporte());
            item.getDocumentosSoporte().size();

            if (item.getSolMedicamento() != null && item.getSolMedicamento().getFormCTCMedicamento() != null) {
                Hibernate.initialize(item.getSolMedicamento().getFormCTCMedicamento().getMedicamentoHomologo());
                for (MedicamentoPosPrevio m : item.getSolMedicamento().getFormCTCMedicamento().getMedicamentosAnteriores()) {
                    Hibernate.initialize(m.getMedicamento());
                    Hibernate.initialize(m.getRespuestaClinicaObservada());
                }
            }
            
            if (item.getSolInsumo() != null && item.getSolInsumo().getFormCTCInsumo() != null) {
//                Hibernate.initialize(item.getSolInsumo().getFormCTCInsumo().getInsumoHomologo());
//                for (InsumoPosPrevio m : item.getSolInsumo().getFormCTCInsumo().getInsumosAnteriores()) {
//                    Hibernate.initialize(m.getInsumo());
//                    Hibernate.initialize(m.getRespuestaClinicaObservada());
//                }
            }
            if (item.getSolProcedimiento() != null && item.getSolProcedimiento().getFormCTCProcedimiento() != null) {
                Hibernate.initialize(item.getSolProcedimiento().getFormCTCProcedimiento().getProcedimientoHomologo());
                if (item.getSolProcedimiento().getFormCTCProcedimiento().getProcedimientoHomologo() != null) {
                    Hibernate.initialize(item.getSolProcedimiento().getFormCTCProcedimiento().getProcedimientoHomologo()
                            .getObjetivoProcedimiento());
                }
                for (ProcedimientoPosPrevio p : item.getSolProcedimiento().getFormCTCProcedimiento().getProcedimientosAnteriores()) {
                    Hibernate.initialize(p.getProcedimiento());
                    Hibernate.initialize(p.getRespuestaClinicaObservada());
                }
            }
            if (item.getAutorizacion().getConceptoRegional() != null) {
                Hibernate.initialize(item.getAutorizacion().getConceptoRegional().getCriteriosNegacion());
            }
            if (item.getAutorizacion().getConceptoNacional() != null) {
                Hibernate.initialize(item.getAutorizacion().getConceptoNacional().getCriteriosNegacion());
            }
            return item;

        } catch (HibernateException e) {
            LOGGER.error("HibernateException Error SolicitudItemDao:", e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error SolicitudItemDao:", e);
            throw e;
        }
    }

}
