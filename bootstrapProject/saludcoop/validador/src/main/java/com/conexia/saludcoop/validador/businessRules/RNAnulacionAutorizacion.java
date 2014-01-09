package com.conexia.saludcoop.validador.businessRules;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.GestionItemRedir_AnulaDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.transaccional.Autorizacion;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.repository.AutorizacionRepository;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
import com.conexia.saludcoop.common.repository.RoleRepository;
import com.conexia.saludcoop.common.repository.SedeIpsRepository;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.util.ConstantesTarget;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.common.util.RoleUtils;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Anula la autorización de uno o varios items")
public class RNAnulacionAutorizacion extends RNProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(RNAnulacionAutorizacion.class);

    @Autowired
    private AutorizacionRepository autorizacionRepo;

    @Autowired
    private SedeIpsRepository sedeIpsRepo;

    @Autowired
    private EstadoAutorizacionRepository estadoAutorizacionRepo;

    @Autowired
    private SolicitudItemRepository solicitudItemRepo;

    @Autowired
    private RoleRepository roleRepo;    

    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {
        GestionItemRedir_AnulaDto dto = (GestionItemRedir_AnulaDto) aContext.get(ConstantesContexto.ANULACION_ITEM_IPS);

        Long autorizacionId=dto.getIdAutorizacion();
        Autorizacion autorizacion = null;
        if(dto.getIdAutorizacion() == null){
        	SolicitudItem item = solicitudItemRepo.findOne(dto.getIdSolicitudItem());
        	autorizacion = item.getAutorizacion();
        } else {
        	autorizacion = autorizacionRepo.findOne(autorizacionId);
        }

        if (dto.getEsGrupo() && !dto.getEsParaAuditor()) {
            if (validarItemConsumido(autorizacion.getSolicitudItems())) {

                autorizacion.setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.ANULADA));
                autorizacion.setJustificacion(dto.getJustificacion());
                autorizacionRepo.save(autorizacion);
            } else {
                return construirRespuestaItemConsumido(aContext);
            }
        } else if (dto.getEsParaAuditor()) {
            if (validarItemConsumido(autorizacion.getSolicitudItems())) {

                autorizacion.setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.PENDIENTE_ANULACION_AUDITOR));
                autorizacion.setJustificacion(dto.getJustificacion());
                autorizacion.setRoleDestinatario(roleRepo.findOne(dto.getRoleId()));
                autorizacionRepo.save(autorizacion);
            } else {
                return construirRespuestaItemConsumido(aContext);
            }
        } else {
            Autorizacion newAutorizacion = autorizacion.cloneAutorizacion();
            newAutorizacion.setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.ANULADA));
            newAutorizacion.setJustificacion(dto.getJustificacion());

            SolicitudItem solItem = solicitudItemRepo.findOne(dto.getIdSolicitudItem());
            if (validarItemConsumido(new HashSet<>(Collections.singletonList(solItem)))) {
                // Si se trata de un medicamento No POS se debe pasar al auditor regional
                
                if(solItem.getSolMedicamento() != null && 
                        solItem.getSolMedicamento().getMedicamento() != null && 
                        solItem.getSolMedicamento().getMedicamento().getTipoPPM() != null && 
                        solItem.getSolMedicamento().getMedicamento().getTipoPPM().getCodigo().equals(SystemConstants.PPM_NOP) && 
                        solItem.getSolMedicamento().getMedicamento().getVisibleCtc() && 
                        dto.getRoleId() != null){
                    newAutorizacion.setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.PENDIENTE_ANULACION_AUDITOR));                    
                    Role role = roleRepo.findOne(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                    newAutorizacion.setRoleDestinatario(role);
                }
                autorizacionRepo.save(newAutorizacion);
                solItem.setAutorizacion(newAutorizacion);
                solicitudItemRepo.save(solItem);

            } else {
                return construirRespuestaItemConsumido(aContext);
            }
                
        }
        return true;
    }


    /**
     * Valida si el item o items que se desean redireccionar ya fueron consumidos o no
     * 
     * @param items
     * @return
     */
    private boolean validarItemConsumido(Set<SolicitudItem> items) {
        for (SolicitudItem solicitudItem : items) {
            if (solicitudItem.getConsumos() != null && solicitudItem.getConsumos().size() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Construye una respuesta no existosa en caso que el item o items que se desean redireccionar ya fueron consumidos
     * 
     * @return
     */
    private boolean construirRespuestaItemConsumido(HashMap<String, Object> aContext) {

        RespuestaDto rta = new RespuestaDto();
        Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
        rta.setCodigoRespuesta(codRespuesta);
        rta.setMensajeRespuesta(I18NUtils.getInstance().getText("respuestaTrx.itemConsumido"));

        aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
        return false;
    }

    /**
     * Ejecuta la regla pasando los datos necesarios para su ejecucion.
     */
    public int executeRegla(HashMap<String, Object> aContext) throws Exception {

        int execResult = RESULT_NOK;

        if (validarRegla(aContext)) {
            execResult = RESULT_OK;
        }

        LOGGER.info("Se ejecuto con exito la regla" + this.getClass().getName());
        return execResult;
    }

}
