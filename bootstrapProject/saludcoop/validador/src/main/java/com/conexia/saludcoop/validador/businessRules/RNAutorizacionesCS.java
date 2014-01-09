package com.conexia.saludcoop.validador.businessRules;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.BandejasDto;
import com.conexia.saludcoop.common.dto.InfoEntregasDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.DocumentoSoporteDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.entity.transaccional.ConceptoAutorizacion;
import com.conexia.saludcoop.common.entity.transaccional.DocumentoSoporte;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudDiagnostico;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.repository.CausaExternaRepository;
import com.conexia.saludcoop.common.repository.CausalAnulacionRepository;
import com.conexia.saludcoop.common.repository.CausalDevolucionRepository;
import com.conexia.saludcoop.common.repository.CriterioNegacionRepository;
import com.conexia.saludcoop.common.repository.DiagnosticoRepository;
import com.conexia.saludcoop.common.repository.EntidadRecobroRepository;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
import com.conexia.saludcoop.common.repository.FinalidadRepository;
import com.conexia.saludcoop.common.repository.LateralidadRepository;
import com.conexia.saludcoop.common.repository.ObjetivoProcedimientoRepository;
import com.conexia.saludcoop.common.repository.ProcedimientoRepository;
import com.conexia.saludcoop.common.repository.RoleRepository;
import com.conexia.saludcoop.common.repository.SedeIpsRepository;
import com.conexia.saludcoop.common.repository.SolicitudDiagnosticoRepository;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.repository.SolicitudRepository;
import com.conexia.saludcoop.common.repository.TipoCatastroficoRepository;
import com.conexia.saludcoop.common.repository.TipoDocumentoSoporteRepository;
import com.conexia.saludcoop.common.util.ConstantesTarget;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.util.ParserSolicitudEntity;

@Component
@Rule(description = "Proceso que actualiza la solicitud desde la bandeja de contact service.")
public class RNAutorizacionesCS extends RNProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(RNAutorizacionesCS.class);
    @Autowired
    SolicitudItemRepository solicitudItemRepository;

    @Autowired
    EstadoAutorizacionRepository estadoAutorizacionRepo;

    @Autowired
    CausalAnulacionRepository causalAnulacionRepo;

    @Autowired
    CausalDevolucionRepository causalDevolucionRepo;

    @Autowired
    CriterioNegacionRepository criterioNegacionRepo;

    @Autowired
    private CausaExternaRepository causaExternaRepo;

    @Autowired
    private FinalidadRepository finalidadRepo;

    @Autowired
    private TipoCatastroficoRepository tipoCatastroficoRepo;

    @Autowired
    private EntidadRecobroRepository entidadRecobroRepo;

    @Autowired
    private ParserSolicitudEntity parserEntity;

    @Autowired
    private DiagnosticoRepository diagnosticoRepo;

    @Autowired
    private SedeIpsRepository sedeIpsRepo;

    @Autowired
    private SolicitudDiagnosticoRepository solicitudDiagnosticoRepo;

    @Autowired
    private SolicitudRepository solicitudRepo;

    @Autowired
    private ObjetivoProcedimientoRepository objetivoRepo;

    @Autowired
    private ProcedimientoRepository procedimientoRepo;

    @Autowired
    private LateralidadRepository lateralidadRepo;
    
    @Autowired 
	private RoleRepository roleRepository;
    

    @Autowired
    private TipoDocumentoSoporteRepository tipoDocSoporteRepo;

    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {
        boolean result = true;
        BandejasDto dto = (BandejasDto) aContext.get(ConstantesContexto.BANDEJAS_DTO);

        try {
            SolicitudItem solItem = solicitudItemRepository.findOne(dto.getItemId());

            ConceptoAutorizacion ca = new ConceptoAutorizacion();

            if (dto.getTipoBandeja().equals(SystemConstants.BANDEJA_NACIONAL)) {
                if (solItem.getAutorizacion().getConceptoNacional() != null) {
                    ca = solItem.getAutorizacion().getConceptoNacional();
                }
            } else {
                if (solItem.getAutorizacion().getConceptoRegional() != null) {
                    ca = solItem.getAutorizacion().getConceptoRegional();
                }
            }

            actualizarInfoSolicitud(dto, solItem);
            ca.setJustificacion(dto.getJustificacion());

            if (dto.getTipoBandeja().equals(SystemConstants.BANDEJA_NACIONAL)) {
                // Se verifica si se pueden actualizar los demás campos del concepto dependiendo del estado
                if (EstadoAutorizacion.EN_ESTUDIO == solItem.getAutorizacion().getEstadoAutorizacion().getId()
                        || EstadoAutorizacion.RESPUESTA_REGIONAL == solItem.getAutorizacion().getEstadoAutorizacion().getId()
                		|| EstadoAutorizacion.PENDIENTE_ALTO_COSTO == solItem.getAutorizacion().getEstadoAutorizacion().getId()
                        || EstadoAutorizacion.PENDIENTE_CONTACT_SERVICE == solItem.getAutorizacion().getEstadoAutorizacion().getId()
                        || EstadoAutorizacion.PENDIENTE_CTC == solItem.getAutorizacion().getEstadoAutorizacion().getId()
                        || EstadoAutorizacion.RESPUESTA_IPS == solItem.getAutorizacion().getEstadoAutorizacion().getId()) {
                    ca.setDiasXperiodo(dto.getDiasPeriodo());
                    ca.setNumeroEntregas(dto.getNumeroEntregas());
                    ca.setPeriodoAprobado(dto.getPeriodoAprobado());
                    ca.setUnidadesAprobadas(dto.getUnidadesAprobadas());
                }
                solItem.getAutorizacion().setConceptoNacional(ca);
            } else {
                solItem.getAutorizacion().setConceptoRegional(ca);
            }

            Role role;
            switch (dto.getConcepto()) {
            case SystemConstants.APROBAR:
                if (EstadoAutorizacion.EN_ESTUDIO == solItem.getAutorizacion().getEstadoAutorizacion().getId()
                        || EstadoAutorizacion.RESPUESTA_IPS == solItem.getAutorizacion().getEstadoAutorizacion().getId()
                		|| EstadoAutorizacion.PENDIENTE_ALTO_COSTO == solItem.getAutorizacion().getEstadoAutorizacion().getId()
                        || EstadoAutorizacion.PENDIENTE_CONTACT_SERVICE == solItem.getAutorizacion().getEstadoAutorizacion().getId()
                        || EstadoAutorizacion.PENDIENTE_CTC == solItem.getAutorizacion().getEstadoAutorizacion().getId()
                        || EstadoAutorizacion.RESPUESTA_REGIONAL == solItem.getAutorizacion().getEstadoAutorizacion().getId()) {
                    solItem.getAutorizacion().setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.AUTORIZADO));

                    // Se actualiza la fecha de modificación de estado del item
                    solItem.getAutorizacion().setFechaUpdateCambioEstado(new Date());

                    // Se actualiza la fecha de autorización
                    solItem.getAutorizacion().setFechaAutorizacion(new Date());

                    role = this.roleRepository.findOne(ConstantesTarget.SISTEMA);
    				solItem.getAutorizacion().setRoleDestinatario(role);

                    // Se verifica si se trata de un medicamento para generar las entregas
                    if(solItem.getSolMedicamento() != null) {
                        InfoEntregasDto infoEntregas = new InfoEntregasDto();
                        infoEntregas.setSolicitud(false);
                        infoEntregas.setDiasPeriodo(ca.getDiasXperiodo());
                        infoEntregas.setItemId(solItem.getId());
                        infoEntregas.setUnidadesAprobadas(ca.getUnidadesAprobadas());
                        infoEntregas.setNumeroEntregas(ca.getNumeroEntregas());

                        aContext.put(ConstantesContexto.INFO_ENTREGAS, Collections.singletonList(infoEntregas));
                    }else{
                    	aContext.put(ConstantesContexto.AUTORIZACIONES, Collections.singletonList(solItem.getAutorizacion()));
                    }
                }
                break;

            case SystemConstants.ANULAR:
                // Se establece la causal de anulación
                ca.setCausalAnulacion(causalAnulacionRepo.findOne(dto.getCausalAnulacion()));

                // Se eliminan las causales de conceptos dados previamente
                ca.setCausalDevolucion(null);

                solItem.getAutorizacion().setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.ANULADA));

                // Se actualiza la fecha de modificación de estado del item
                solItem.getAutorizacion().setFechaUpdateCambioEstado(new Date());

                role = this.roleRepository.findOne(ConstantesTarget.SISTEMA);
				solItem.getAutorizacion().setRoleDestinatario(role);
    			
                break;

            case SystemConstants.DEVOLVER:
                // Se establece la causal de devolución
                ca.setCausalDevolucion(causalDevolucionRepo.findOne(dto.getCausalDevolucion()));

                // Se eliminan las causales de conceptos dados previamente
                ca.setCausalAnulacion(null);

                // Se actualiza la fecha de modificación de estado del item
                solItem.getAutorizacion().setFechaUpdateCambioEstado(new Date());

                User user = solItem.getSolicitud().getUsuarioCreador();

                // Se verifica el usuario que creó la solicitud para devolverla
                if (user != null) {
                    for (Role r : user.getRoles()) {
                        if (SystemConstants.ROLE_IPS.equals(r.getRole()) || SystemConstants.ROLE_MEDICO.equals(r.getRole())
                                || SystemConstants.ROLE_RECEPCION_IPS.equals(r.getRole())) {

                            solItem.getAutorizacion()
                                    .setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.DEVUELTA_IPS));

                            role = this.roleRepository.findOne(ConstantesTarget.ROLE_BACKOFFICE_IPS);
                            solItem.getAutorizacion().setRoleDestinatario(role);

                            break;
                        } else if (SystemConstants.ROLE_LINEA_FRENTE.equals(r.getRole())
                                || SystemConstants.ROLE_BACKOFFICE_LDF.equals(r.getRole())) {

                            solItem.getAutorizacion()
                                    .setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.DEVUELTA_IPS));

                            role = this.roleRepository.findOne(ConstantesTarget.ROLE_BACKOFFICE_LDF);
                            solItem.getAutorizacion().setRoleDestinatario(role);

                            break;
                        }
                    }
                } else {

                    solItem.getAutorizacion().setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.DEVUELTA_IPS));

                    role = this.roleRepository.findOne(ConstantesTarget.ROLE_BACKOFFICE_IPS);
                    solItem.getAutorizacion().setRoleDestinatario(role);

                }
                
                break;

            case SystemConstants.RESPONDER:
                if (EstadoAutorizacion.DEVUELTA_REGIONAL == solItem.getAutorizacion().getEstadoAutorizacion().getId()) {
                    solItem.getAutorizacion().setEstadoAutorizacion(
                            estadoAutorizacionRepo.findOne(EstadoAutorizacion.RESPUESTA_REGIONAL));

                    // Se actualiza la fecha de modificación de estado del item
                    solItem.getAutorizacion().setFechaUpdateCambioEstado(new Date());

                    role = this.roleRepository.findOne(ConstantesTarget.ROLE_CONTACT_SERVICE_NAC);
    				solItem.getAutorizacion().setRoleDestinatario(role);
        			
                } else if (EstadoAutorizacion.DEVUELTA_IPS == solItem.getAutorizacion().getEstadoAutorizacion().getId()) {
                    solItem.getAutorizacion().setEstadoAutorizacion(estadoAutorizacionRepo.findOne(EstadoAutorizacion.RESPUESTA_IPS));

                    // Se actualiza la fecha de modificación de estado del item
                    solItem.getAutorizacion().setFechaUpdateCambioEstado(new Date());

                    role = this.roleRepository.findOne(ConstantesTarget.ROLE_CONTACT_SERVICE_NAC);
    				solItem.getAutorizacion().setRoleDestinatario(role);
                }
                break;

            default:
                break;
            }

            solicitudItemRepository.save(solItem);
        } catch (Exception e) {
            RespuestaDto rta = new RespuestaDto();
            Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
            rta.setCodigoRespuesta(codRespuesta);
            rta.setMensajeRespuesta(I18NUtils.getInstance().getText("respuestaTrx.errorAlProcesar"));

            aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
            LOGGER.info("Error al procesar la transacción", e);

            return false;
        }

        return result;
    }

    /**
     * Verifica si los valores del formulario CTC del item cambiaron y establece los nuevos valores
     * 
     * @param dto
     *            Dto con los valores modificados para el item
     * @param item
     *            Item que se desea actualizar
     */
    private void actualizarInfoSolicitud(BandejasDto dto, SolicitudItem item) {
        // Se actualiza la fecha de modificación del item
        item.getAutorizacion().setFechaUpdate(new Date());

        // Se verifica si cambiaron los valores del item
        if(item.getSolMedicamento() != null) {
            if (dto.getFinalidad() != null
                    && (item.getSolMedicamento().getFormulaMedicamento().getFinalidad() == null || !dto.getFinalidad().equals(
                            item.getSolMedicamento().getFormulaMedicamento().getFinalidad().getId()))) {
                item.getSolMedicamento().getFormulaMedicamento().setFinalidad(finalidadRepo.findOne(dto.getFinalidad()));
            }
            if (dto.getTipoCatastrofico() != null
                    && (item.getSolMedicamento().getFormulaMedicamento().getTipoCatastrofico() == null || !dto.getTipoCatastrofico().equals(
                            item.getSolMedicamento().getFormulaMedicamento().getTipoCatastrofico().getId()))) {
                item.getSolMedicamento().getFormulaMedicamento().setTipoCatastrofico(tipoCatastroficoRepo.findOne(dto.getTipoCatastrofico()));
            }
            if (dto.getCausaExterna() != null
                    && (item.getSolMedicamento().getFormulaMedicamento().getCausaExterna() == null || !dto.getCausaExterna().equals(
                            item.getSolMedicamento().getFormulaMedicamento().getCausaExterna().getId()))) {
                item.getSolMedicamento().getFormulaMedicamento().setCausaExterna(causaExternaRepo.findOne(dto.getCausaExterna()));
            }
        } if (item.getSolProcedimiento() != null) {
            if (dto.getFinalidad() != null
                    && (item.getSolProcedimiento().getFormulaProcedimiento().getFinalidad() == null || !dto.getFinalidad().equals(
                            item.getSolProcedimiento().getFormulaProcedimiento().getFinalidad().getId()))) {
                item.getSolProcedimiento().getFormulaProcedimiento().setFinalidad(finalidadRepo.findOne(dto.getFinalidad()));
            }
            if (dto.getTipoCatastrofico() != null
                    && (item.getSolProcedimiento().getFormulaProcedimiento().getTipoCatastrofico() == null || !dto.getTipoCatastrofico().equals(
                            item.getSolProcedimiento().getFormulaProcedimiento().getTipoCatastrofico().getId()))) {
                item.getSolProcedimiento().getFormulaProcedimiento().setTipoCatastrofico(tipoCatastroficoRepo.findOne(dto.getTipoCatastrofico()));
            }
            if (dto.getCausaExterna() != null
                    && (item.getSolProcedimiento().getFormulaProcedimiento().getCausaExterna() == null || !dto.getCausaExterna().equals(
                            item.getSolProcedimiento().getFormulaProcedimiento().getCausaExterna().getId()))) {
                item.getSolProcedimiento().getFormulaProcedimiento().setCausaExterna(causaExternaRepo.findOne(dto.getCausaExterna()));
            }
        }
        
        if (dto.getEntidadRecobro() != null && !dto.getEntidadRecobro().equals(item.getAutorizacion().getEntidadRecobro())) {
            item.getAutorizacion().setEntidadRecobro(entidadRecobroRepo.findOne(dto.getEntidadRecobro()));
        }

        // Se actualizan los demás datos del item

        if (dto.getIdDiagnostico() != null
                && (item.getDiagnostico().getDiagnostico() == null || !dto.getIdDiagnostico().equals(
                        item.getDiagnostico().getDiagnostico().getId()))) {
            // En caso que la solicitud tenga solo un item, se modifica en caso contrario se agrega uno nuevo
            if (item.getSolicitud().getSolicitudItems().size() == 1) {
                item.getDiagnostico().setDiagnostico(diagnosticoRepo.findOne(dto.getIdDiagnostico()));
            } else {
                SolicitudDiagnostico solDiag = new SolicitudDiagnostico();
                solDiag.setDiagnostico(diagnosticoRepo.findOne(dto.getIdDiagnostico()));
                Hibernate.initialize(item.getSolicitud().getSolDiagnosticos());
                item.getSolicitud().getSolDiagnosticos().add(solDiag);
                item.setDiagnostico(solDiag);
                solicitudRepo.save(item.getSolicitud());
            }
        }

        if (dto.getIdSedeIps() != null
                && (item.getAutorizacion().getSedeIpsEfectora() == null || !dto.getIdSedeIps().equals(
                        item.getAutorizacion().getSedeIpsEfectora().getId()))) {
            item.getAutorizacion().setSedeIpsEfectora(sedeIpsRepo.findOne(dto.getIdSedeIps()));
        }

        if(dto.getDocumentosSoporte() != null && !dto.getDocumentosSoporte().isEmpty()) {
            Hibernate.initialize(item.getSolicitud().getDocumentosSoporte());
            for(DocumentoSoporte documentoSoporte : getDocumentosSoporte(dto.getDocumentosSoporte())){
                item.getSolicitud().addDocumentoSoporte(documentoSoporte);
            }
        }
    }

    /**
     * obtiene los documentos que se agregan a la solicitud
     * 
     * @param documentosSoporteDto
     * @return
     */
    private Set<DocumentoSoporte> getDocumentosSoporte(Set<DocumentoSoporteDto> documentosSoporteDto) {

        DocumentoSoporte docSoporte = null;
        Set<DocumentoSoporte> documentosSoprte = new HashSet<>();
        for (DocumentoSoporteDto documentoSoporteDto : documentosSoporteDto) {
            docSoporte = new DocumentoSoporte();
            docSoporte.setTipoDocSoporte(tipoDocSoporteRepo.findOne(documentoSoporteDto.getTipoDocSoporte().getId()));
            docSoporte.setNombreArchivoServidor(documentoSoporteDto.getNombreArchivoServidor());
            docSoporte.setNombreArchivoOriginal(documentoSoporteDto.getNombreArchivoOriginal());
            documentosSoprte.add(docSoporte);
        }

        return documentosSoprte;
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
