package com.conexia.saludcoop.validador.businessRules;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.NivelAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.maestro.TipoPPM;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
import com.conexia.saludcoop.common.repository.MedicamentoRepository;
import com.conexia.saludcoop.common.repository.ProcedimientoRepository;
import com.conexia.saludcoop.common.repository.SolicitudItemDao;
import com.conexia.saludcoop.common.util.ConstantesTarget;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Valida nivel de autorizacion y setea el estado de autorizacion para redireccionamiento")
public class RNNivelAutorizacion01 extends RNProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(RNNivelAutorizacion01.class);

    @Autowired
    private ProcedimientoRepository procedimientoRepository;
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    @Autowired
    private EstadoAutorizacionRepository estadoAutorizacionRepo;
    @Autowired
    private SolicitudItemDao sd;

    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

        SolicitudItemDto itemDto = (SolicitudItemDto) aContext.get(ConstantesContexto.SOLICITUD_ITEM);

        if (itemDto != null) {
            Procedimiento procedimiento;
            Medicamento medicamento;
            if (itemDto.getSolProcedimiento() != null) {
                
                procedimiento = this.procedimientoRepository.findOne(itemDto.getSolProcedimiento().getProcedimiento().getId());
                
                if (itemDto.getAutorizacion() == null){
                    itemDto.setAutorizacion(new AutorizacionDto());
                }
                actualizarAutorizacionProcedimiento(procedimiento.getNivelAutorizacion(), procedimiento.getTipoPPM(),itemDto.getAutorizacion());

            }
            if (itemDto.getSolMedicamento() != null) {

                medicamento = this.medicamentoRepository.findOne(itemDto.getSolMedicamento().getMedicamento().getId());

                if (itemDto.getAutorizacion() == null){
                    itemDto.setAutorizacion(new AutorizacionDto());
                }

                actualizarAutorizacionMedicamento(medicamento.getAltoCosto(), medicamento.getVisibleCtc(), itemDto.getAutorizacion());
            }

        }
        aContext.put(ConstantesContexto.SOLICITUD_ITEM, itemDto);
        return true;
    }

    private void actualizarAutorizacionMedicamento(Boolean altoCosto, Boolean visibleCtc, AutorizacionDto autorizacion) {
        DescriptivoDto estadoAutorizacion = new DescriptivoDto();
        if (visibleCtc) {
            autorizacion.setEstadoAutorizacion(null);
            autorizacion.setTarget(null);
        } else if (altoCosto) {
            RoleDTO roleDto = new RoleDTO();
            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_AC_NAC);
            estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_ALTO_COSTO);
            autorizacion.setEstadoAutorizacion(estadoAutorizacion);
            autorizacion.setTarget(roleDto);
        } else {
            autorizacion.setEstadoAutorizacion(null);
            autorizacion.setTarget(null);
        }
    }

    private void actualizarAutorizacionProcedimiento(Integer nivelAutorizacion, TipoPPM tipoPPM, AutorizacionDto autorizacionDto) {
        try {
            DescriptivoDto estadoAutorizacion = new DescriptivoDto();
            RoleDTO roleDto = new RoleDTO();
            if (nivelAutorizacion.equals(NivelAutorizacion.NIVEL_5)) {
                roleDto.setId(ConstantesTarget.ROLE_AUDITOR_AC_NAC);
                estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_CTC);
                autorizacionDto.setEstadoAutorizacion(estadoAutorizacion);
                autorizacionDto.setTarget(roleDto);
            } else if (nivelAutorizacion.equals(NivelAutorizacion.NIVEL_3)) {
                roleDto.setId(ConstantesTarget.ROLE_CONTACT_SERVICE_NAC);
                estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_CONTACT_SERVICE);
                autorizacionDto.setEstadoAutorizacion(estadoAutorizacion);
                autorizacionDto.setTarget(roleDto);
            } else {
                autorizacionDto.setEstadoAutorizacion(null);
                autorizacionDto.setTarget(null);
            }
        } catch (Exception e) {
            LOGGER.error("Error en el calculo de nivel de la autorizacion", e);
        }

    }

    /**
     * Ejecuta la regla pasando los datos necesarios para su ejecucion.
     */
    public int executeRegla(HashMap<String, Object> aContext) throws Exception {

        int execResult = RESULT_OK;

        if (validarRegla(aContext)) {
            execResult = RESULT_OK;

            LOGGER.info("Se ejecuto con exito la regla " + this.getClass().getName());
        } else {
            RespuestaDto rta = new RespuestaDto();

            Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
            rta.setCodigoRespuesta(codRespuesta);
            rta.setMensajeRespuesta(I18NUtils.getInstance().getText("respuestaTrx.transaccionNoOk"));

            aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
            LOGGER.error("Pincho la regla que autoriza los items");

        }

        return execResult;
    }

}