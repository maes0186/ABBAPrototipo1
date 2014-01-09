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
import com.conexia.saludcoop.common.entity.maestro.SedeIps;
import com.conexia.saludcoop.common.entity.transaccional.Autorizacion;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.repository.AutorizacionRepository;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
import com.conexia.saludcoop.common.repository.RoleRepository;
import com.conexia.saludcoop.common.repository.SedeIpsRepository;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Redirecciona la ips de un determinado item")
public class RNRedireccionamientoIPS extends RNProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(RNRedireccionamientoIPS.class);

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

    /*
     * Constantes
     */
    private static final String MENSAJE_IPS_IGUAL = "respuestaTrx.ipsIgual";
    private static final String ITEM_CONSUMIDO = "respuestaTrx.itemConsumido";

    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {
        GestionItemRedir_AnulaDto dto = (GestionItemRedir_AnulaDto) aContext.get(ConstantesContexto.REDIRECCIONAMIENTO_ITEM_IPS);
        try {
            SedeIps sede = null;
            SolicitudItem item = null;

            Long autorizacionId = dto.getIdAutorizacion();
            Autorizacion autorizacion = null;
            if (dto.getIdAutorizacion() == null) {
                item = solicitudItemRepo.findOne(dto.getIdSolicitudItem());
                autorizacion = item.getAutorizacion();
            } else {
                autorizacion = autorizacionRepo.findOne(autorizacionId);
                for (SolicitudItem it : autorizacion.getSolicitudItems()) {
                    item = it;
                    break;
                }
            }
            
            Autorizacion newAutorizacion = autorizacion.cloneAutorizacion();
            
            if (dto.getIdSedeIps() != null){
            	sede = sedeIpsRepo.findOne(dto.getIdSedeIps());
            	newAutorizacion.setSedeIpsEfectora(sede);
            }
            
            if (autorizacion.getSedeIpsEfectora() != null) {
                if (dto.getIdSedeIps() != null && autorizacion.getSedeIpsEfectora().getId().equals(dto.getIdSedeIps())) {
                    return construirRespuestaItemConsumido(aContext, MENSAJE_IPS_IGUAL);
                }
            }

            if (dto.getEsGrupo() && !dto.getEsParaAuditor()) {
                if (validarItemConsumido(autorizacion.getSolicitudItems())) {
                    autorizacionRepo.save(newAutorizacion);
                    for (SolicitudItem it : autorizacion.getSolicitudItems()) {
                        it.setAutorizacion(newAutorizacion);
                        solicitudItemRepo.save(it);
                    }

                    // Se anula la autorización anterior
                    autorizacion.setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.ANULADA));
                    autorizacion.setJustificacion(dto.getJustificacion());
                    autorizacionRepo.save(autorizacion);
                } else {
                    return construirRespuestaItemConsumido(aContext, ITEM_CONSUMIDO);
                }
            } else if (dto.getEsParaAuditor()) {
                String justificacion = dto.getJustificacion();

                newAutorizacion.setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.PENDIENTE_REDIRECCION_AUDITOR));
                newAutorizacion.setJustificacion(justificacion);
                newAutorizacion.setRoleDestinatario(roleRepo.findOne(dto.getRoleId()));
                autorizacionRepo.save(newAutorizacion);

                if (!dto.getEsGrupo()) {
                    SolicitudItem solItem = solicitudItemRepo.findOne(dto.getIdSolicitudItem());
                    if (validarItemConsumido(new HashSet<>(Collections.singletonList(solItem)))) {
                        solItem.setAutorizacion(newAutorizacion);
                        solicitudItemRepo.save(solItem);
                    } else {
                        return construirRespuestaItemConsumido(aContext, ITEM_CONSUMIDO);
                    }
                } else {
                    if (validarItemConsumido(autorizacion.getSolicitudItems())) {
                        for (SolicitudItem it : autorizacion.getSolicitudItems()) {
                            it.setAutorizacion(newAutorizacion);
                            solicitudItemRepo.save(it);
                        }

                        // Se anula la autorización anterior
                        autorizacion.setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.ANULADA));
                        autorizacion.setJustificacion(dto.getJustificacion());
                        autorizacionRepo.save(autorizacion);
                    } else {
                        return construirRespuestaItemConsumido(aContext, ITEM_CONSUMIDO);
                    }
                }
            } else {
                SolicitudItem solItem = solicitudItemRepo.findOne(dto.getIdSolicitudItem());
                if (validarItemConsumido(new HashSet<>(Collections.singletonList(solItem)))) {
                    autorizacionRepo.save(newAutorizacion);
                    solItem.setAutorizacion(newAutorizacion);
                    solicitudItemRepo.save(solItem);

                } else {
                    return construirRespuestaItemConsumido(aContext, ITEM_CONSUMIDO);
                }
            }
            return true;
        } catch (Throwable e) {
            LOGGER.error("Error ejecutando la regla de redireccionamineto", e);
            throw e;
        }
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
    private boolean construirRespuestaItemConsumido(HashMap<String, Object> aContext, String mensaje) {

        RespuestaDto rta = new RespuestaDto();
        Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
        rta.setCodigoRespuesta(codRespuesta);
        rta.setMensajeRespuesta(I18NUtils.getInstance().getText(mensaje));

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
