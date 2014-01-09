package com.conexia.saludcoop.validador.businessRules;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.transaccional.DocumentoSoporteDto;
import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.TipoDocumentoSoporte;
import com.conexia.saludcoop.common.entity.maestro.ViaAdministracion;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.transaccional.DocumentoSoporte;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.repository.RoleRepository;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.util.ConstantesTarget;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Actualiza datos para devolucion de Ips")
public class RN0012 extends RNProcess {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    SolicitudItemRepository sir;

    private static Logger LOGGER = LoggerFactory.getLogger(RN0012.class);

    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

        boolean result = false;
        try {
            SolicitudItemDto itemDto = (SolicitudItemDto) aContext.get(ConstantesContexto.SOLICITUD_ITEM);
            SolicitudItem item = sir.findOne(itemDto.getNroItem());
            for (DocumentoSoporteDto doc : itemDto.getSolicitud().getDocumentosSoporte()) {
                DocumentoSoporte d = new DocumentoSoporte();
                TipoDocumentoSoporte tipo = new TipoDocumentoSoporte();
                tipo.setId(doc.getTipoDocSoporte().getId());
                d.setTipoDocSoporte(tipo);
                d.setNombreArchivoServidor(doc.getNombreArchivoServidor());
                d.setNombreArchivoOriginal(doc.getNombreArchivoOriginal());
                item.getSolicitud().addDocumentoSoporte(d);
            }
            if (itemDto.isMedicamento()) {
                item.getSolMedicamento().getFormulaMedicamento().setDosis(itemDto.getSolMedicamento().getFormulaMedicamento().getDosis());
                item.getSolMedicamento().getFormulaMedicamento()
                        .setDuracion(itemDto.getSolMedicamento().getFormulaMedicamento().getDuracion());
                ViaAdministracion via = new ViaAdministracion();
                via.setId(itemDto.getSolMedicamento().getFormulaMedicamento().getViaAdministracion().getId());
                item.getSolMedicamento().getFormulaMedicamento().setViaAdministracion(via);
                ;
                item.getSolMedicamento().getFormulaMedicamento()
                        .setPosologia(itemDto.getSolMedicamento().getFormulaMedicamento().getPosologia());
            }
            EstadoAutorizacion nuevoEstado = new EstadoAutorizacion();
            nuevoEstado.setId(EstadoAutorizacion.RESPUESTA_IPS);
            item.getAutorizacion().setEstadoAutorizacion(nuevoEstado);
            
            if(itemDto.getAutorizacion() != null) {
                item.getAutorizacion().setJustificacionIps(itemDto.getAutorizacion().getJustificacionIps());
            }

            NivelAutorizadorManager autorizadorManager = new NivelAutorizadorManager();
            RoleDTO roleDto = null;

            if (item.getSolMedicamento() != null) {
                roleDto = autorizadorManager.obtenerRolPorMedicamento(item.getSolMedicamento().getMedicamento().getAltoCosto(),
                        item.getSolMedicamento().getMedicamento().getVisibleCtc(), item.getAplicaTutela());
                
            } else if(item.getSolProcedimiento() != null){
                roleDto = autorizadorManager.obtenerRolPorProcedimiento(item.getSolProcedimiento().getProcedimiento()
                        .getNivelAutorizacion(), item.getAplicaTutela(), item.getSolProcedimiento().getEspecialidad().getCodigo(), item
                        .getSolProcedimiento().getProcedimiento().getTipoPPM().getDescripcion());
            }  else if(item.getSolInsumo() != null) {
                roleDto = autorizadorManager.obtenerRolPorInsumo(item.getSolInsumo().getInsumo().getNivelAutorizacion().intValue(), 
                        item.getSolInsumo().getInsumo().getAltoCosto(), item.getSolInsumo().getInsumo().getVisibleCtc(), 
                        item.getAplicaTutela(), item.getSolInsumo().getInsumo().getTipoPPM().getCodigo(), item.getSolInsumo().getInsumo().getEspecialidades(), item.getSuperaTopes());
                
            }
            if(Boolean.TRUE.equals(item.getAplicaTutela()) && ConstantesTarget.ROLE_LINEA_FRENTE.equals(roleDto.getId())) {
                roleDto.setId(ConstantesTarget.ROLE_AUDITOR_TUTELAS);
            }
            
            Role role = roleRepository.findOne(roleDto.getId());

            if (role != null) {
                item.getAutorizacion().setRoleDestinatario(role);
            }
            sir.save(item);
            
            //TODO Enviar correo electronico avisando al afiliado del cambio de estado de la autorizacion.
            
            result = true;
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    /**
     * Ejecuta la regla pasando los datos necesarios para su ejecucion.
     */
    public int executeRegla(HashMap<String, Object> aContext) throws Exception {

        int execResult = RESULT_NOK;

        if (validarRegla(aContext)) {
            execResult = RESULT_OK;
            LOGGER.info("Se ejecuto con exito la regla " + this.getClass().getName());
        } else {
            execResult = RESULT_NOK;
            LOGGER.info("Se ejecuto con errores la regla " + this.getClass().getName());
        }

        return execResult;
    }

}