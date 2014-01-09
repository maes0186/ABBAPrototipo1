package com.conexia.saludcoop.common.repository;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.transaccional.Solicitud;

@Component
@Transactional
public class SolicitudDaoImpl implements SolicitudDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    public Integer getNextNumeroSolicitud(){
    	 Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select (NEXT VALUE FOR [transaccional].[seq_numero_solicitud])");
    	 return (Integer) query.uniqueResult();
    }
   
    @Override
    public Solicitud findLastSolicitudByInsumoAndAfiliado(Long afiliadoId, String codMedicamento) {
        if (afiliadoId == null) {
            throw new IllegalArgumentException("El parametro afiliadoId es obligatorio.");
        }
        
        if (codMedicamento == null) {
            throw new IllegalArgumentException("El parametro codMedicamento es obligatorio.");
        }
        
        int paramsIndex = 0;
        
        
        StringBuilder select = new StringBuilder("select s.* from transaccional.solicitud s  ");
        StringBuilder joins = new StringBuilder(" inner join maestros.afiliado a on s.afiliado_id = a.id ").append
                                            (" inner join transaccional.solicitud_item si on s.id = si.solicitud_id  ").append
                                            (" inner join transaccional.solicitud_insumo sm on si.id = sm.solicitud_item_id ").append
                                            (" inner join maestros.insumo m on m.id = sm.insumo_id ");
        StringBuilder where = new StringBuilder(" where a.id = ? ");
        where.append(" and m.codigo = ? order by s.fecha_creacion desc");
        
        
        
       
        
        String queryStr = select.toString()+joins.toString()+where.toString();
        
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(queryStr).addEntity(Solicitud.class);
        
        query.setParameter(paramsIndex++, afiliadoId);
        query.setParameter(paramsIndex++, codMedicamento);
        
        query.setMaxResults(1);
       
        
        return (Solicitud) query.uniqueResult();
    }

}
