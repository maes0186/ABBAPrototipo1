package com.conexia.saludcoop.validador.businessRules;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.entity.maestro.EspecialidadInsumo;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.NivelAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.repository.AutorizacionRepository;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
import com.conexia.saludcoop.common.repository.RoleRepository;
import com.conexia.saludcoop.common.repository.SedeIpsRepository;
import com.conexia.saludcoop.common.repository.SolicitudItemDao;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.util.ConstantesTarget;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.common.util.RoleUtils;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Obtiene el target Destino al que se debe escalar un item, para bandejas de Redirección y Anulación en LDF")
public class RNTargetEscalamiento extends RNProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(RNTargetEscalamiento.class);

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

    @Autowired
    private SolicitudItemDao sd;
    
    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception{
        RoleDTO dto = new RoleDTO();
        Long itemId = (Long) aContext.get(ConstantesContexto.NRO_SOLICITUD_ITEM);
        String nombreBandeja = (String) aContext.get(ConstantesContexto.NOMBRE_BANDEJA);
        SolicitudItem sItem = solicitudItemRepo.findOne(itemId);
        switch (nombreBandeja) {
        case SystemConstants.BANDEJA_REDIRECCION:
            if (sItem.getSolMedicamento() != null) {
                Medicamento medicamento = sItem.getSolMedicamento().getMedicamento();
                if (medicamento.getVisibleCtc()) {
                    dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                }
            } else if (sItem.getSolProcedimiento() != null) {
                Procedimiento procedimiento = sItem.getSolProcedimiento().getProcedimiento();
                String codigoEspecialidad = sItem.getSolProcedimiento().getEspecialidad().getCodigo();
                switch (procedimiento.getNivelAutorizacion()) {
                case NivelAutorizacion.NIVEL_5:
                    if (SystemConstants.PPM_POS.equals(procedimiento.getTipoPPM().getCodigo()) || procedimiento.getTipoPPM() == null) {
                        if (RoleUtils.getEspecialidadesNivel5().get("espOdontologo").contains(codigoEspecialidad)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_ODONTOLOGO);
                        } else if (RoleUtils.getEspecialidadesNivel5().get("espOrtopedista").contains(codigoEspecialidad)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_ORTOPEDISTA);
                        } else if (RoleUtils.getEspecialidadesNivel5().get("espOtorrino").contains(codigoEspecialidad)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_OTORRINO);
                        } else if (procedimiento.getTipoPPM().getCodigo() != null) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                        } else {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_NAC);
                        }
                    } else {
                        dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                    }
                    break;
                }
            } else if (sItem.getSolInsumo() != null) {
                Insumo insumo = sItem.getSolInsumo().getInsumo();
                Hibernate.initialize(insumo.getEspecialidades());
                Iterable<EspecialidadInsumo> especialidades = insumo.getEspecialidades();
                switch (insumo.getNivelAutorizacion()) {
                case NivelAutorizacion.NIVEL_5:
                    if (SystemConstants.PPM_POS.equals(insumo.getTipoPPM().getCodigo()) || insumo.getTipoPPM() == null) {
                        if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOdontologo"), especialidades)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_ODONTOLOGO);
                        } else if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOrtopedista"), especialidades)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_ORTOPEDISTA);
                        } else if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOtorrino"), especialidades)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_OTORRINO);
                        } else if (insumo.getTipoPPM().getCodigo() != null) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                        } else {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_NAC);
                        }
                    } else {
                        dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                    }
                    break;
                }
            }
            break;
        case SystemConstants.BANDEJA_ANULACION:
            if (sItem.getSolMedicamento() != null) {
                Medicamento medicamento = sItem.getSolMedicamento().getMedicamento();
                if (medicamento.getVisibleCtc()) {
                    dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                } else if (medicamento.getAltoCosto()) {
                    dto.setId(ConstantesTarget.ROLE_AUDITOR_AC_NAC);
                } 
            }else if(sItem.getSolProcedimiento() != null){
                Procedimiento procedimiento = sItem.getSolProcedimiento().getProcedimiento();
                String codigoEspecialidad = sItem.getSolProcedimiento().getEspecialidad().getCodigo();
                switch (procedimiento.getNivelAutorizacion()) {
                case NivelAutorizacion.NIVEL_3:
                    dto.setId(ConstantesTarget.ROLE_CONTACT_SERVICE_NAC);
                    break;
                case NivelAutorizacion.NIVEL_4:
                    dto.setId(ConstantesTarget.ROLE_AUDITOR_AC_NAC);
                    break;
                case NivelAutorizacion.NIVEL_5:
                    if (SystemConstants.PPM_POS.equals(procedimiento.getTipoPPM().getCodigo()) || procedimiento.getTipoPPM() == null) {
                        if (RoleUtils.getEspecialidadesNivel5().get("espOdontologo").contains(codigoEspecialidad)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_ODONTOLOGO);
                        } else if (RoleUtils.getEspecialidadesNivel5().get("espOrtopedista").contains(codigoEspecialidad)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_ORTOPEDISTA);
                        } else if (RoleUtils.getEspecialidadesNivel5().get("espOtorrino").contains(codigoEspecialidad)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_OTORRINO);
                        } else if (procedimiento.getTipoPPM().getCodigo() != null) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                        } else {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_NAC);
                        }
                    } else {
                        dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                    }
                    break;
                }
            }else if(sItem.getSolInsumo() != null){
                Insumo insumo = sItem.getSolInsumo().getInsumo();
                Hibernate.initialize(insumo.getEspecialidades());
                Iterable<EspecialidadInsumo> especialidades = insumo.getEspecialidades();
                switch (insumo.getNivelAutorizacion()) {
                case NivelAutorizacion.NIVEL_3:
                    dto.setId(ConstantesTarget.ROLE_CONTACT_SERVICE_NAC);
                    break;
                case NivelAutorizacion.NIVEL_4:
                    dto.setId(ConstantesTarget.ROLE_AUDITOR_AC_NAC);
                    break;
                case NivelAutorizacion.NIVEL_5:
                    if (SystemConstants.PPM_POS.equals(insumo.getTipoPPM().getCodigo()) || insumo.getTipoPPM() == null) {
                        if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOdontologo"), especialidades)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_ODONTOLOGO);
                        } else if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOrtopedista"), especialidades)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_ORTOPEDISTA);
                        } else if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOtorrino"), especialidades)) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_OTORRINO);
                        } else if (insumo.getTipoPPM().getCodigo() != null) {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                        } else {
                            dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_NAC);
                        }
                    } else {
                        dto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                    }
                    break;
                }
            }
            break;
        }
        
        if(dto.getId() != 0){
            aContext.put(ConstantesContexto.ESCALAMIENTO_DTO, dto);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Itera las especialidades del insumo sobre las especialidades nivel 5 para comprobar si pertenece.
     * @param especialidades
     * @param especialidadesInsumos
     * @return
     */
    
    private boolean perteneEspecialidad(List<String> especialidades, Iterable<EspecialidadInsumo> especialidadesInsumos) {
        boolean respuesta = false;
        for (EspecialidadInsumo especialidadInsumo : especialidadesInsumos) {
            for (String especialidad : especialidades) {
                if (especialidadInsumo.getEspecialidad().getCodigo().equals(especialidad)) {
                    return true;
                }
            }
        }
        return respuesta;
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
