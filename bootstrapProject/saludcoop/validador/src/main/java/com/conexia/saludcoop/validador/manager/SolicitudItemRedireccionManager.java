package com.conexia.saludcoop.validador.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudInsumoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudProcedimientoDto;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.repository.SolicitudItemDao;

/**
 * 
 * @author mortega
 * 
 */
@Service
@Transactional
public class SolicitudItemRedireccionManager {
    @Autowired
    private SolicitudItemDao sd;

    /**
     * 
     * @param nro
     * @return
     */
    public SolicitudItemDto obtenerItemDtoParaRedireccion(Long nro) {
        SolicitudItem item = sd.getSolicitudItemByNumero(nro);
        SolicitudItemDto solicitudItemDto = new SolicitudItemDto();
        solicitudItemDto.setNroItem(solicitudItemDto.getNroItem());
        solicitudItemDto.setAutorizacion(item.getAutorizacion().toDto());
        // Procedimiento
        if (item.getSolProcedimiento() != null && item.getSolProcedimiento().getProcedimiento() != null) {
            SolicitudProcedimientoDto soloProcedimientoDto = new SolicitudProcedimientoDto();
            ProcedimientoDto procedimientoDto = new ProcedimientoDto();
            procedimientoDto.setId(item.getSolProcedimiento().getProcedimiento().getId());
            soloProcedimientoDto.setProcedimiento(procedimientoDto);
            solicitudItemDto.setSolProcedimiento(soloProcedimientoDto);
        }
        // Medicamento
        if (item.getSolMedicamento() != null && item.getSolMedicamento().getMedicamento() != null) {
            SolicitudMedicamentoDto solicitudMedicamentoDto = new SolicitudMedicamentoDto();
            MedicamentoDto medicamentoDto = new MedicamentoDto();
            medicamentoDto.setId(item.getSolMedicamento().getMedicamento().getId());
            solicitudMedicamentoDto.setMedicamento(medicamentoDto);
            solicitudItemDto.setSolMedicamento(solicitudMedicamentoDto);

        }
        // Insumo
        if (item.getSolInsumo() != null && item.getSolInsumo().getInsumo() != null) {
            SolicitudInsumoDto solicitudInsumoDto = new SolicitudInsumoDto();
            InsumoDto insumoDto = new InsumoDto();
            insumoDto.setId(item.getSolInsumo().getInsumo().getId());
            solicitudInsumoDto.setInsumo(insumoDto);
            solicitudItemDto.setSolInsumo(solicitudInsumoDto);

        }        
        
        return solicitudItemDto;
    }
}
