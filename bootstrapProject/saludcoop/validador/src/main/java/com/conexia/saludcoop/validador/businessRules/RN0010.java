package com.conexia.saludcoop.validador.businessRules;

import java.util.Date;
import java.util.HashMap;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.PreAutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.EspecialidadInsumo;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.maestro.TipoPPM;
import com.conexia.saludcoop.common.repository.AfiliadoRepository;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
import com.conexia.saludcoop.common.repository.InsumoRepository;
import com.conexia.saludcoop.common.repository.MedicamentoRepository;
import com.conexia.saludcoop.common.repository.ProcedimientoRepository;
import com.conexia.saludcoop.common.util.ConstantesTarget;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.common.util.RoleUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Valida nivel de autorizacion y setea el estado de autorizacion.")
public class RN0010 extends RNProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(RN0010.class);

    @Autowired
    private ProcedimientoRepository procedimientoRepository;
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    @Autowired
    private InsumoRepository insumoRepository;
    @Autowired
    private EstadoAutorizacionRepository estadoAutorizacionRepo;
    @Autowired
    private AfiliadoRepository afiliadoRepo;

    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

        SolicitudDto solicitudDto = (SolicitudDto) aContext.get(ConstantesContexto.SOLICITUD);

        if (solicitudDto != null) {

            Afiliado afiliado = afiliadoRepo.findOneById(solicitudDto.getAfiliado().getId());
            boolean tutela = afiliado.getTutela() == null ? false : afiliado.getTutela() == 0 ? false : true;

            Procedimiento procedimiento;
            Medicamento medicamento;
            Insumo insumo;
            for (SolicitudItemDto siDto : solicitudDto.getSolicitudItems()) {
                if (siDto.isProcedimiento()) {
                    procedimiento = procedimientoRepository.findOne(siDto.getSolProcedimiento().getProcedimiento().getId());

                    if (siDto.getPreAutorizacion() == null)
                        siDto.setPreAutorizacion(new PreAutorizacionDto());

                    if (siDto.getPreAutorizacion().getEstadoAutorizacion() == null) {
                        actualizarAutorizacionProcedimiento(procedimiento.getNivelAutorizacion(), procedimiento.getTipoPPM(), siDto,
                                tutela, solicitudDto.isLdf());
                    }

                }
                if (siDto.isMedicamento()) {

                    medicamento = this.medicamentoRepository.findOne(siDto.getSolMedicamento().getMedicamento().getId());

                    if (siDto.getPreAutorizacion() == null)
                        siDto.setPreAutorizacion(new PreAutorizacionDto());

                    if (siDto.getPreAutorizacion().getEstadoAutorizacion() == null) {
                        actualizarAutorizacionMedicamento(medicamento.getAltoCosto(), medicamento.getVisibleCtc(), siDto, tutela,
                                solicitudDto.isLdf());
                    }
                }

                if (siDto.isInsumo()) {

                    insumo = this.insumoRepository.findOne(siDto.getSolInsumo().getInsumo().getId());

                    if (siDto.getPreAutorizacion() == null)
                        siDto.setPreAutorizacion(new PreAutorizacionDto());

                    if (siDto.getPreAutorizacion().getEstadoAutorizacion() == null) {
                        Hibernate.initialize(insumo.getEspecialidades());
                        Iterable<EspecialidadInsumo> especialidades = insumo.getEspecialidades();
                        actualizarAutorizacionInsumo(insumo.getNivelAutorizacion().intValue(), insumo.getTipoPPM(), insumo.getAltoCosto(), insumo.getVisibleCtc(), siDto, tutela,
                                solicitudDto.isLdf(), especialidades);
                    }
                }

                if (tutela && siDto.getAplicaTutela() == null && !solicitudDto.isLdf()) {
                    siDto.setAplicaTutela(Boolean.TRUE);
                }
            }

        }
        return true;
    }

    private void actualizarAutorizacionMedicamento(Boolean altoCosto, Boolean visibleCtc, SolicitudItemDto siDto, Boolean tutela,
            boolean esLdf) {
        DescriptivoDto estadoAutorizacion = new DescriptivoDto();
        PreAutorizacionDto preAutorizacion = siDto.getPreAutorizacion();

        // Se verifica si la solicitud es creada desde línea de frente y el item aplica tutela
        if (esLdf && siDto.getAplicaTutela() != null && siDto.getAplicaTutela()) {
            estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_TUTELA);
            preAutorizacion.setEstadoAutorizacion(estadoAutorizacion);

            RoleDTO roleDto = new RoleDTO();
            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_TUTELAS);
            preAutorizacion.setTarget(roleDto);
        } else {
            // Si la solicitud es creada desde línea de frente y el item no aplica tutela, se toma como si no tuviera tutela
            if (esLdf) {
                tutela = false;
            }

            NivelAutorizadorManager autorizadorManager = new NivelAutorizadorManager();
            RoleDTO roleDto = autorizadorManager.obtenerRolPorMedicamento(altoCosto, visibleCtc, tutela);
            if (roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_CTC_NAC)
                    || roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_CTC_REG)) {
                estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_CTC);
                preAutorizacion.setEstadoAutorizacion(estadoAutorizacion);
                preAutorizacion.setTarget(roleDto);
            } else if (roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_AC_NAC)
                    || roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_AC_REG)) {
                estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_ALTO_COSTO);
                preAutorizacion.setEstadoAutorizacion(estadoAutorizacion);
                preAutorizacion.setTarget(roleDto);
            } else if (roleDto.getId().equals(ConstantesTarget.ROLE_LINEA_FRENTE)) {
                estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_TUTELA);
                preAutorizacion.setEstadoAutorizacion(estadoAutorizacion);
                preAutorizacion.setTarget(roleDto);
            } else {
                estadoAutorizacion.setId(EstadoAutorizacion.AUTORIZADO);
                preAutorizacion.setEstadoAutorizacion(estadoAutorizacion);
                preAutorizacion.setFechaAutorizacion(new Date());
            }
        }

    }

    private void actualizarAutorizacionInsumo(Integer nivelAutorizacion, TipoPPM tipoPPM, Boolean altoCosto, Boolean visibleCtc, SolicitudItemDto siDto, Boolean tutela,
            boolean esLdf, Iterable<EspecialidadInsumo> especialidades) {
    	try {
        DescriptivoDto estadoAutorizacion = new DescriptivoDto();
        PreAutorizacionDto preAutorizacion = siDto.getPreAutorizacion();

        // Se verifica si la solicitud es creada desde línea de frente y el item aplica tutela
        if (esLdf && siDto.getAplicaTutela() != null && siDto.getAplicaTutela()) {
            estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_TUTELA);
            preAutorizacion.setEstadoAutorizacion(estadoAutorizacion);

            RoleDTO roleDto = new RoleDTO();
            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_TUTELAS);
            preAutorizacion.setTarget(roleDto);
        } else {
            // Si la solicitud es creada desde línea de frente y el item no aplica tutela, se toma como si no tuviera tutela
            if (esLdf) {
                tutela = false;
            }

            NivelAutorizadorManager autorizadorManager = new NivelAutorizadorManager();
            RoleDTO roleDto = autorizadorManager.obtenerRolPorInsumo(nivelAutorizacion, altoCosto, visibleCtc, tutela, tipoPPM.getCodigo(), especialidades, siDto.getSuperaTopes());
            if (roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_CTC_NAC)
                    || roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_CTC_REG)) {
                estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_CTC);
                preAutorizacion.setEstadoAutorizacion(estadoAutorizacion);
                preAutorizacion.setTarget(roleDto);
            } else if (roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_AC_NAC)
                    || roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_AC_REG)) {
                estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_ALTO_COSTO);
                preAutorizacion.setEstadoAutorizacion(estadoAutorizacion);
                preAutorizacion.setTarget(roleDto);
            } else if (roleDto.getId().equals(ConstantesTarget.ROLE_LINEA_FRENTE)) {
                estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_TUTELA);
                preAutorizacion.setEstadoAutorizacion(estadoAutorizacion);
                preAutorizacion.setTarget(roleDto);
            } else {
                estadoAutorizacion.setId(EstadoAutorizacion.AUTORIZADO);
                preAutorizacion.setEstadoAutorizacion(estadoAutorizacion);
                preAutorizacion.setFechaAutorizacion(new Date());
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarAutorizacionProcedimiento(Integer nivelAutorizacion, TipoPPM tipoPPM, SolicitudItemDto siDto, Boolean tutela,
            boolean esLdf) {
        try {
            DescriptivoDto estadoAutorizacion = new DescriptivoDto();
            PreAutorizacionDto preAutorizacionDto = siDto.getPreAutorizacion();

            // Se verifica si la solicitud es creada desde línea de frente y el item aplica tutela
            if (esLdf && siDto.getAplicaTutela() != null && siDto.getAplicaTutela()) {
                estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_TUTELA);
                preAutorizacionDto.setEstadoAutorizacion(estadoAutorizacion);

                RoleDTO roleDto = new RoleDTO();
                roleDto.setId(ConstantesTarget.ROLE_AUDITOR_TUTELAS);
                preAutorizacionDto.setTarget(roleDto);
            } else {
                // Si la solicitud es creada desde línea de frente y el item no aplica tutela, se toma como si no tuviera tutela
                if (esLdf) {
                    tutela = false;
                }
                NivelAutorizadorManager autorizadorManager = new NivelAutorizadorManager();
                RoleDTO roleDto = autorizadorManager.obtenerRolPorProcedimiento(nivelAutorizacion, tutela, siDto.getSolProcedimiento()
                        .getEspecialidad().getCodigo(), siDto.getSolProcedimiento().getProcedimiento().getTipoPPM().getDescripcion());

                if (roleDto.getId().equals(ConstantesTarget.ROLE_CONTACT_SERVICE_NAC)
                        || roleDto.getId().equals(ConstantesTarget.ROLE_CONTACT_SERVICE_REG)) {
                    estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_CONTACT_SERVICE);
                    preAutorizacionDto.setEstadoAutorizacion(estadoAutorizacion);
                    preAutorizacionDto.setTarget(roleDto);
                } else if (roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_AC_NAC)
                        || roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_AC_REG)) {
                    estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_ALTO_COSTO);
                    preAutorizacionDto.setEstadoAutorizacion(estadoAutorizacion);
                    preAutorizacionDto.setTarget(roleDto);
                } else if (roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_CTC_NAC)
                        || roleDto.getId().equals(ConstantesTarget.ROLE_AUDITOR_CTC_REG)) {
                    estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_CTC);
                    preAutorizacionDto.setEstadoAutorizacion(estadoAutorizacion);
                    preAutorizacionDto.setTarget(roleDto);
                } else if (RoleUtils.getListID_ROLE_EspecialidadesNivel5().contains(roleDto.getId())) {
                    estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_AUDITORIA_ESPECIALIZADA);
                    preAutorizacionDto.setEstadoAutorizacion(estadoAutorizacion);
                    preAutorizacionDto.setTarget(roleDto);
                } else if (roleDto.getId().equals(ConstantesTarget.ROLE_CONTACT_SERVICE_NAC)
                        || roleDto.getId().equals(ConstantesTarget.ROLE_CONTACT_SERVICE_REG)) {
                    estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_CONTACT_SERVICE);
                    preAutorizacionDto.setEstadoAutorizacion(estadoAutorizacion);
                    preAutorizacionDto.setTarget(roleDto);
                } else if (roleDto.getId().equals(ConstantesTarget.ROLE_CONTACT_SERVICE_NAC)
                        || roleDto.getId().equals(ConstantesTarget.ROLE_CONTACT_SERVICE_REG)) {
                    estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_CONTACT_SERVICE);
                    preAutorizacionDto.setEstadoAutorizacion(estadoAutorizacion);
                    preAutorizacionDto.setTarget(roleDto);
                } else if (roleDto.getId().equals(ConstantesTarget.ROLE_LINEA_FRENTE)) {
                    estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_TUTELA);
                    preAutorizacionDto.setEstadoAutorizacion(estadoAutorizacion);
                    preAutorizacionDto.setTarget(roleDto);
                } else {
                    estadoAutorizacion.setId(EstadoAutorizacion.AUTORIZADO);
                    preAutorizacionDto.setEstadoAutorizacion(estadoAutorizacion);
                    preAutorizacionDto.setFechaAutorizacion(new Date());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
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