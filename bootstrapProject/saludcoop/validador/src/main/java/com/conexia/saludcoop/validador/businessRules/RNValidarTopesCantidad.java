package com.conexia.saludcoop.validador.businessRules;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.ValidarTopesCantidadDto;
import com.conexia.saludcoop.common.entity.maestro.InsumoTope;
import com.conexia.saludcoop.common.repository.AfiliadoRepository;
import com.conexia.saludcoop.common.repository.InsumoTopeRepository;
import com.conexia.saludcoop.common.repository.SolicitudRepository;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;


@Component
@Rule(description = "Valida el tope de elemento dado.")
public class RNValidarTopesCantidad extends RNProcess {
    
    @Autowired
    AfiliadoRepository afiliadoRepository;
    
    @Autowired
    private SolicitudRepository solicitudRepo;
    
    @Autowired
    private InsumoTopeRepository insumoTopeRepo;
    
    private boolean validarRegla(HashMap<String, Object> context) {
        ValidarTopesCantidadDto validarTopesDto = (ValidarTopesCantidadDto) context.get(ConstantesContexto.TOPE_CANTIDAD_DTO);
        
        InsumoTope tope = insumoTopeRepo.findOneByIdInsumo(validarTopesDto.getIdInsumo());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -(tope.getPeriodicidadDias()));
        long countInsumos = 0; 
        long cantInsumos = 0;

        countInsumos = solicitudRepo.countSolicitudesByInsumoAndAfiliado(validarTopesDto.getIdAfiliado(), validarTopesDto.getIdInsumo(), c.getTime(), new Date());
        if(countInsumos > 0){
            cantInsumos = solicitudRepo.sumCantidadSolicitadaByInsumoAndAfiliado(validarTopesDto.getIdAfiliado(), validarTopesDto.getIdInsumo(), c.getTime(), new Date());
        } else {
            cantInsumos = 0;
        }
        
        if ((cantInsumos + validarTopesDto.getCantidad()) > tope.getTope()) {
            // excedio tope, pendiente ctc           
            return true;
        }

        RespuestaDto rta = new RespuestaDto();
        Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
        rta.setCodigoRespuesta(codRespuesta);
        rta.setMensajeRespuesta("No excedio tope");

        context.put(ConstantesContexto.RESPUESTA_TRX, rta);
        return false;
    }    
    
    /**
     * Ejecuta la regla pasando los datos necesarios para su ejecucion.
     */
    public int executeRegla(HashMap<String, Object> aContext) throws Exception {

        int execResult = RESULT_NOK;

        if (validarRegla(aContext)) {
            execResult = RESULT_OK;
        } else {
            execResult = RESULT_NOK;
        }
        return execResult;
    }

    
}
