package com.conexia.saludcoop.common.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.transaccional.Solicitud;
import com.conexia.saludcoop.common.repository.custom.ExtendedSolicitudRepository;

public interface SolicitudRepository extends CrudRepository<Solicitud, Long>, ExtendedSolicitudRepository {

    @Query(value = "select COUNT(si) from Solicitud s JOIN s.afiliado a JOIN s.solicitudItems si JOIN si.solInsumo sins "
            + " where a.id = ? and sins.insumo.id = ?  and s.fechaCreacion between ? and ? ")
    public Long countSolicitudesByInsumoAndAfiliado(Long afiliadoId, Long insumoId, Date fechaDesde, Date fechaHasta);
   
    @Query(value = "select SUM(si.cantidad) from Solicitud s JOIN s.afiliado a JOIN s.solicitudItems si JOIN si.solInsumo sins "
            + " where a.id = ? and sins.insumo.id = ?  and s.fechaCreacion between ? and ? ")
       public Long sumCantidadSolicitadaByInsumoAndAfiliado(Long afiliadoId, Long insumoId, Date fechaDesde, Date fechaHasta);
    
}
