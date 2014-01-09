package com.conexia.saludcoop.validador.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.DocumentoSoporteDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaInsumoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaProcedimientoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormularioCTCInsumoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormularioCTCMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormularioCTCProcedimientoDto;
import com.conexia.saludcoop.common.dto.transaccional.GrupoAutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.MedicamentoPosPrevioDto;
import com.conexia.saludcoop.common.dto.transaccional.ProcedimientoHomologoDto;
import com.conexia.saludcoop.common.dto.transaccional.ProcedimientoPosPrevioDto;
import com.conexia.saludcoop.common.dto.transaccional.ResumenDiagnosticoDto;
import com.conexia.saludcoop.common.dto.transaccional.ResumenHistoriaClinicaDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDiagnosticoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudInsumoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudProcedimientoDto;
import com.conexia.saludcoop.common.entity.maestro.CausaExterna;
import com.conexia.saludcoop.common.entity.maestro.Finalidad;
import com.conexia.saludcoop.common.entity.maestro.ObjetivoProcedimiento;
import com.conexia.saludcoop.common.entity.maestro.RespuestaClinicaObservada;
import com.conexia.saludcoop.common.entity.maestro.TipoCatastrofico;
import com.conexia.saludcoop.common.entity.maestro.TipoDocumentoSoporte;
import com.conexia.saludcoop.common.entity.maestro.ViaAdministracion;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.transaccional.Autorizacion;
import com.conexia.saludcoop.common.entity.transaccional.DocumentoSoporte;
import com.conexia.saludcoop.common.entity.transaccional.FormulaInsumo;
import com.conexia.saludcoop.common.entity.transaccional.FormulaMedicamento;
import com.conexia.saludcoop.common.entity.transaccional.FormulaProcedimiento;
import com.conexia.saludcoop.common.entity.transaccional.FormularioCTCInsumo;
import com.conexia.saludcoop.common.entity.transaccional.FormularioCTCMedicamento;
import com.conexia.saludcoop.common.entity.transaccional.FormularioCTCProcedimiento;
import com.conexia.saludcoop.common.entity.transaccional.GrupoAutorizacion;
import com.conexia.saludcoop.common.entity.transaccional.MedicamentoPosPrevio;
import com.conexia.saludcoop.common.entity.transaccional.ProcedimientoHomologo;
import com.conexia.saludcoop.common.entity.transaccional.ProcedimientoPosPrevio;
import com.conexia.saludcoop.common.entity.transaccional.ResumenDiagnostico;
import com.conexia.saludcoop.common.entity.transaccional.ResumenHistoriaClinica;
import com.conexia.saludcoop.common.entity.transaccional.Solicitud;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudDiagnostico;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudInsumo;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudMedicamento;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudProcedimiento;
import com.conexia.saludcoop.common.repository.AfiliadoRepository;
import com.conexia.saludcoop.common.repository.CausaExternaRepository;
import com.conexia.saludcoop.common.repository.DiagnosticoRepository;
import com.conexia.saludcoop.common.repository.EspecialidadRepository;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
import com.conexia.saludcoop.common.repository.FinalidadRepository;
import com.conexia.saludcoop.common.repository.InsumoRepository;
import com.conexia.saludcoop.common.repository.LateralidadRepository;
import com.conexia.saludcoop.common.repository.MedicamentoRepository;
import com.conexia.saludcoop.common.repository.ObjetivoProcedimientoRepository;
import com.conexia.saludcoop.common.repository.OrigenRepeticionRepository;
import com.conexia.saludcoop.common.repository.ProcedimientoRepository;
import com.conexia.saludcoop.common.repository.ProfesionalRepository;
import com.conexia.saludcoop.common.repository.RespuestaClinicaObservadaRepository;
import com.conexia.saludcoop.common.repository.RoleRepository;
import com.conexia.saludcoop.common.repository.SedeIpsRepository;
import com.conexia.saludcoop.common.repository.ServicioRepository;
import com.conexia.saludcoop.common.repository.TipoCatastroficoRepository;
import com.conexia.saludcoop.common.repository.TipoDocumentoSoporteRepository;
import com.conexia.saludcoop.common.repository.TipoPPMRepository;
import com.conexia.saludcoop.common.repository.TipoPrestacionRepository;
import com.conexia.saludcoop.common.repository.TipoServicioRepository;
import com.conexia.saludcoop.common.repository.TipoTecnologiaRepository;
import com.conexia.saludcoop.common.repository.ViaAdministracionRepository;
import com.conexia.saludcoop.security.dao.UserRepository;

@Component
public class ParserSolicitudEntity {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AfiliadoRepository aRepo;

    
    @Autowired
    private ServicioRepository servicioRepo;
    
    
    
    @Autowired
    private SedeIpsRepository sedeRepo;

    @Autowired
    private ProfesionalRepository profesionalRepo;

    @Autowired
    private DiagnosticoRepository diagnosticoRepo;

    @Autowired
    private ProcedimientoRepository procedimientoRepo;

    @Autowired
    private MedicamentoRepository medicamentoRepo;

    @Autowired
    private InsumoRepository insumoRepo;
    
    @Autowired
    private LateralidadRepository lateralidadRepo;

    @Autowired
    private ViaAdministracionRepository viaAdministracionRepo;

    @Autowired
    private CausaExternaRepository causaExternaRepo;

    @Autowired
    private FinalidadRepository finalidadRepo;

    @Autowired
    private TipoCatastroficoRepository tipoCatastroficoRepo;

    @Autowired
    private TipoDocumentoSoporteRepository tipoDocSoporteRepo;

    @Autowired
    private ObjetivoProcedimientoRepository objetivoRepo;

    @Autowired
    private OrigenRepeticionRepository origenRepeticionRepo;

    @Autowired
    private RespuestaClinicaObservadaRepository respuestaClinicaObservadaRepo;

    @Autowired
    private TipoPrestacionRepository tipoPrestacionRepo;

    @Autowired
    private TipoPPMRepository tipoPPMRepo;

    @Autowired
    private TipoServicioRepository tipoServicioRepo;

    @Autowired
    private TipoTecnologiaRepository tipoTecnologiaRepo;

    @Autowired
    private EstadoAutorizacionRepository estadoAutorizacionRepo;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private UserRepository userRepo;

    private HashMap<String, SolicitudDiagnostico> diagnosticos = new HashMap<>();

    public Solicitud getSolicitud(SolicitudDto solicitudDto) {

        Solicitud solicitud = new Solicitud();
        if (solicitudDto.getNroSolicitud() != null) {
            solicitud.setNroSolicitud(solicitudDto.getNroSolicitud());
        }
        solicitud.setObservacion(solicitudDto.getObservaciones());
        solicitud.setUsuarioCreador(userRepo.findOne(solicitudDto.getUsuarioCreador()));
        solicitud.setAfiliado(aRepo.findOne(solicitudDto.getAfiliado().getId()));
        solicitud.setSedeIps(sedeRepo.findOne(solicitudDto.getSedeIps().getId()));
        solicitud.setProfesionalSolicitante(profesionalRepo.findOne(solicitudDto.getProfesionalSolicitante().getId()));

        for (DocumentoSoporte doc : this.getDocumentosSoporte(solicitudDto.getDocumentosSoporte())) {
            solicitud.addDocumentoSoporte(doc);
        }

        solicitud.setResumenHistoriaClinica(this.getResumen(solicitudDto.getResumenHistoriaClinica()));
        solicitud.setSolDiagnosticos(this.getSolicitudDiagnosticos(solicitudDto.getSolDiagnosticos()));

        setGruposAutorizacion(solicitudDto.getGruposAutorizaciones(), solicitud);
        return solicitud;

    }

    private void setGruposAutorizacion(List<GrupoAutorizacionDto> gruposAutorizaciones, Solicitud solicitud) {

        GrupoAutorizacion grupoEntity = null;
        for (GrupoAutorizacionDto grupoAutorizacionDto : gruposAutorizaciones) {
            grupoEntity = new GrupoAutorizacion();
            grupoEntity.setAutorizadoAutomaticamente(grupoAutorizacionDto.isAutorizadoAutomaticamente());
            grupoEntity.setCuotaModeradoraEstimada(grupoAutorizacionDto.getCuotaModeradoraEstimada());
            grupoEntity.setCuotaModeradoraPagada(grupoAutorizacionDto.isCuotaModeradoraPagada());
            grupoEntity.setTipoPago(grupoAutorizacionDto.getTipoPago());

            for (AutorizacionDto autorizacionDto : grupoAutorizacionDto.getAutorizaciones()) {
                grupoEntity.addAutorizacion(buildAutorizacion(autorizacionDto, solicitud));

            }
            solicitud.addGrupoAutorizacion(grupoEntity);
        }

    }

    private Autorizacion buildAutorizacion(AutorizacionDto autorizacionDto, Solicitud solicitud) {
        if (autorizacionDto != null) {
            Autorizacion autorizacionEntity = new Autorizacion();
            if (autorizacionDto.getSedeIpsEfector() != null) {
                autorizacionEntity.setSedeIpsEfectora(sedeRepo.findOne(autorizacionDto.getSedeIpsEfector().getId()));
            } else {
                // TODO: Quitar esta parte, es solo para que no se explote más adelante
                autorizacionEntity.setSedeIpsEfectora(sedeRepo.findOne(1L));
            }
            if(autorizacionDto.getServicioId()!= null)
            	autorizacionEntity.setServicio(servicioRepo.findOne(autorizacionDto.getServicioId()));
            if(autorizacionDto.getEspecialidadId()!= null)
            	autorizacionEntity.setEspecialidad(especialidadRepository.findOne(autorizacionDto.getEspecialidadId()));
            
            autorizacionEntity.setFechaAutorizacion(autorizacionDto.getFechaAutorizacion());
            
            autorizacionEntity.setEstadoAutorizacion(estadoAutorizacionRepo.findOne(autorizacionDto.getEstadoAutorizacion().getId()));
            if (autorizacionDto.getTarget() != null) {
                Role role = this.roleRepository.findOne(autorizacionDto.getTarget().getId());
                if (role != null)
                    autorizacionEntity.setRoleDestinatario(role);
            }
            for (SolicitudItemDto solicitudItemDto : autorizacionDto.getSolicitudItem()) {
                SolicitudItem solItemEntity = buildSolicitudItem(solicitudItemDto);
                solicitud.addSolicitudItem(solItemEntity);
                autorizacionEntity.addSolicitudItem(solItemEntity);
            }

            return autorizacionEntity;
        }
        return null;

    }

    private SolicitudItem buildSolicitudItem(SolicitudItemDto solicitudItemDto) {
        SolicitudItem solItem = new SolicitudItem();
        solItem.setCantidad(solicitudItemDto.getCantidad());
        solItem.setCopagoEstimado(solicitudItemDto.getCopagoEstimado());
        solItem.setSaldoAConsumir(solicitudItemDto.getCantidad());
        solItem.setTipoPPM(tipoPPMRepo.findOne(solicitudItemDto.getTipoPPM().getId()));
        solItem.setTipoServicio(tipoServicioRepo.findOne(solicitudItemDto.getTipoServicio()));
        solItem.setTipoTecnologia(tipoTecnologiaRepo.findOne(solicitudItemDto.getTipoTecnologia()));
        if (solicitudItemDto.isMedicamento()) {

            solItem.setSolMedicamento(this.getSolicitudMedicamento(solicitudItemDto.getSolMedicamento()));

        } else if (solicitudItemDto.isProcedimiento()) {

            solItem.setSolProcedimiento(this.getSolicitudProcedimiento(solicitudItemDto.getSolProcedimiento()));
        } else if (solicitudItemDto.isInsumo() ){
        	solItem.setSolInsumo(this.getSolicitudInsumo(solicitudItemDto.getSolInsumo()));
        	solItem.setSuperaTopes(solicitudItemDto.getSuperaTopes());
        }

        if (solicitudItemDto.getFormularioCTCAdjunto() != null) {
            for (DocumentoSoporteDto doc : solicitudItemDto.getFormularioCTCAdjunto()) {
                solItem.addDocumentoSoporte(getDocumentoSoporte(doc));
            }
        }
        SolicitudDiagnostico solDiag = diagnosticos.get(solicitudItemDto.getDiagnostico().getDiagnostico().getCodigo());
        solItem.setAplicaTutela(solicitudItemDto.getAplicaTutela());
        // solDiag.addSolicitudItem(solItem);
        solItem.setDiagnostico(solDiag);
        return solItem;
    }

    private SolicitudInsumo getSolicitudInsumo(SolicitudInsumoDto solInsumoDto) {

        SolicitudInsumo si = new SolicitudInsumo();
        if (solInsumoDto.getFormCTCInsumo() != null) {
            si.setFormCTCInsumo(this.getFormularioCTCInsumo(solInsumoDto.getFormCTCInsumo()));
        }
        si.setFormulaInsumo(this.getFormulaInsumo(solInsumoDto.getFormulaInsumo()));
        si.setInsumo(insumoRepo.findOne(solInsumoDto.getInsumo().getId()));

        return si;
    }

    private FormulaInsumo getFormulaInsumo(FormulaInsumoDto formulaInsumoDto) {

        FormulaInsumo fm = new FormulaInsumo();
        if (formulaInsumoDto.getCausaExterna() != null) {
            fm.setCausaExterna((CausaExterna) getById(causaExternaRepo, formulaInsumoDto.getCausaExterna()));
        }
        if (formulaInsumoDto.getFinalidad() != null) {
            fm.setFinalidad((Finalidad) getById(finalidadRepo, formulaInsumoDto.getFinalidad()));
        }
        fm.setTipoCatastrofico((TipoCatastrofico) getById(tipoCatastroficoRepo, formulaInsumoDto.getTipoCatastrofico()));
        fm.setCantidad(Integer.valueOf(formulaInsumoDto.getCantidad()));
        fm.setDuracion(Integer.valueOf(formulaInsumoDto.getDuracion()));

        return fm;
    }

    private FormularioCTCInsumo getFormularioCTCInsumo(FormularioCTCInsumoDto formCTCInsumoDto) {

        FormularioCTCInsumo formCTCEntity = new FormularioCTCInsumo();
        if (formCTCInsumoDto.getInsumoHomologo() != null) {
            formCTCEntity.setInsumoHomologo(insumoRepo.findOne(formCTCInsumoDto.getInsumoHomologo().getId()));
        }
//        if (formCTCInsumoDto.getInsumosAnteriores() != null) {
//            for (InsumoPosPrevioDto insumoPosForm : formCTCInsumoDto.getInsumosAnteriores()) {
//                formCTCEntity.addInsumoPosPrevio(getInsumoPosPrevio(insumoPosForm));
//            }
//        }
        formCTCEntity.setCausaExterna((CausaExterna) getById(causaExternaRepo, formCTCInsumoDto.getCausaExterna()));

        formCTCEntity.setFinalidad((Finalidad) getById(finalidadRepo, formCTCInsumoDto.getFinalidad()));
        formCTCEntity.setJustificacionRiesgoInminente(formCTCInsumoDto.getJustificacionRiesgoInminente());
        formCTCEntity.setJustificacionSinPosPrevio(formCTCInsumoDto.getJustificacionSinPosPrevio());
        formCTCEntity.setJustificacionMedico(formCTCInsumoDto.getJustificacionMedico());
        formCTCEntity.setTipoCatastrofico((TipoCatastrofico) getById(tipoCatastroficoRepo, formCTCInsumoDto.getTipoCatastrofico()));
        formCTCEntity.setSinAlternativaPos(formCTCInsumoDto.getSinAlternativaPos());
        formCTCEntity.setExisteRiesgoInminente(formCTCInsumoDto.getExisteRiesgoInminente());
        formCTCEntity.setPosibilidadesPosAgotadas(formCTCInsumoDto.getPosibilidadesPosAgotadas());
        formCTCEntity.setAutorizadoINVIMA(formCTCInsumoDto.getAutorizadoINVIMA());
        formCTCEntity.setResumenHistoriaClinica(formCTCInsumoDto.getResumenHistoriaClinica());

        return formCTCEntity;
    }

//    private InsumoPosPrevio getInsumoPosPrevio(InsumoPosPrevioDto insumoPosForm) {
//        InsumoPosPrevio m = new InsumoPosPrevio();
//        m.setDiasTratamiento(insumoPosForm.getDiasTratamiento());
//        m.setCantidad(insumoPosForm.getCantidad());
//        m.setInsumo(insumoRepo.findOne(insumoPosForm.getInsumo().getId()));
//        m.setRespuestaClinicaObservada((RespuestaClinicaObservada) getById(respuestaClinicaObservadaRepo,
//                insumoPosForm.getRespuestaClinicaObservada()));
//
//        return m;
//    }


	private DocumentoSoporte getDocumentoSoporte(DocumentoSoporteDto doc) {

        DocumentoSoporte docSoporte = null;

        docSoporte = new DocumentoSoporte();
        docSoporte.setTipoDocSoporte((TipoDocumentoSoporte) getById(tipoDocSoporteRepo, doc.getTipoDocSoporte()));
        docSoporte.setNombreArchivoServidor(doc.getNombreArchivoServidor());
        docSoporte.setNombreArchivoOriginal(doc.getNombreArchivoOriginal());

        return docSoporte;

    }

    private SolicitudProcedimiento getSolicitudProcedimiento(SolicitudProcedimientoDto solProcedimientoDto) {

        SolicitudProcedimiento solProcedimiento = new SolicitudProcedimiento();
        if (solProcedimientoDto.getFormCTCProcedimiento() != null) {
            solProcedimiento.setFormCTCProcedimiento(this.getFormCTCProcedimiento(solProcedimientoDto.getFormCTCProcedimiento()));
        }
        solProcedimiento.setFormulaProcedimiento(this.getFormulaProcedimiento(solProcedimientoDto.getFormulaProcedimiento()));
        solProcedimiento.setProcedimiento(procedimientoRepo.findOne(solProcedimientoDto.getProcedimiento().getId()));
        solProcedimiento.setEspecialidad(especialidadRepository.findOne(solProcedimientoDto.getEspecialidad().getId()));
        return solProcedimiento;
    }

    private FormulaProcedimiento getFormulaProcedimiento(FormulaProcedimientoDto formulaProcedimientoDto) {

        FormulaProcedimiento formulaProcedimiento = new FormulaProcedimiento();
        if (formulaProcedimientoDto.getCausaExterna() != null) {
            formulaProcedimiento.setCausaExterna((CausaExterna) getById(causaExternaRepo, formulaProcedimientoDto.getCausaExterna()));
        }
        if (formulaProcedimientoDto.getFinalidad() != null) {
            formulaProcedimiento.setFinalidad((Finalidad) getById(finalidadRepo, formulaProcedimientoDto.getFinalidad()));
        }
        formulaProcedimiento.setTipoCatastrofico((TipoCatastrofico) getById(tipoCatastroficoRepo,
                formulaProcedimientoDto.getTipoCatastrofico()));
        formulaProcedimiento.setLateralidad(formulaProcedimientoDto.getLateralidad() != null ? lateralidadRepo
                .findOne(formulaProcedimientoDto.getLateralidad().getId()) : null);
        formulaProcedimiento.setObjetivoProcedimiento(formulaProcedimientoDto.getObjetivo() != null ? objetivoRepo
                .findOne(formulaProcedimientoDto.getObjetivo().getId()) : null);
        formulaProcedimiento.setOrigenRepeticion(formulaProcedimientoDto.getOrigenRepeticion() != null ? origenRepeticionRepo
                .findOne(formulaProcedimientoDto.getOrigenRepeticion().getId()) : null);
        formulaProcedimiento.setTipoPrestacion(formulaProcedimientoDto.getTipoPrestacion() != null ? tipoPrestacionRepo
                .findOne(formulaProcedimientoDto.getTipoPrestacion().getId()) : null);

        formulaProcedimiento.setPosologia(formulaProcedimientoDto.getPosologia());

        return formulaProcedimiento;
    }

    private FormularioCTCProcedimiento getFormCTCProcedimiento(FormularioCTCProcedimientoDto formCTCProcedimientoDto) {

        FormularioCTCProcedimiento formCTCProcedimiento = new FormularioCTCProcedimiento();
        if (formCTCProcedimientoDto.getProcedimientoHomologo() != null) {
            formCTCProcedimiento.setProcedimientoHomologo(getProcedimientoHomologo(formCTCProcedimientoDto.getProcedimientoHomologo()));
        }

        if (formCTCProcedimientoDto.getProcedimientosAnteriores() != null) {
            for (ProcedimientoPosPrevioDto procAnteriores : formCTCProcedimientoDto.getProcedimientosAnteriores()) {
                formCTCProcedimiento.addProcedimientoPosPrevio(getProcedimientoPosPrevio(procAnteriores));
            }
        }
        if (formCTCProcedimientoDto.getCausaExterna() != null) {
            formCTCProcedimiento.setCausaExterna((CausaExterna) getById(causaExternaRepo, formCTCProcedimientoDto.getCausaExterna()));
        }
        if (formCTCProcedimientoDto.getFinalidad() != null) {
            formCTCProcedimiento.setFinalidad((Finalidad) getById(finalidadRepo, formCTCProcedimientoDto.getFinalidad()));
        }
        formCTCProcedimiento.setSinAlternativaPos(formCTCProcedimientoDto.isSinAlternativaPos());
        formCTCProcedimiento.setJustificacionSinHomologo(formCTCProcedimientoDto.getJustificacionSinHomologo());
        formCTCProcedimiento.setExisteRiesgoInminente(formCTCProcedimientoDto.getExisteRiesgoInminente());
        formCTCProcedimiento.setPosibilidadesPosAgotadas(formCTCProcedimientoDto.getPosibilidadesPosAgotadas());
        formCTCProcedimiento.setAutorizadoINVIMA(formCTCProcedimientoDto.getAutorizadoINVIMA());
        formCTCProcedimiento.setJustificacionMedico(formCTCProcedimientoDto.getJustificacionMedico());
        formCTCProcedimiento.setJustificacionRiesgoInminente(formCTCProcedimientoDto.getJustificacionRiesgoInminente());
        formCTCProcedimiento.setJustificacionSinPosPrevio(formCTCProcedimientoDto.getJustificacionSinPosPrevio());
        formCTCProcedimiento.setTipoCatastrofico((TipoCatastrofico) getById(tipoCatastroficoRepo,
                formCTCProcedimientoDto.getTipoCatastrofico()));
        formCTCProcedimiento.setResumenHistoriaClinica(formCTCProcedimientoDto.getResumenHistoriaClinica());
        formCTCProcedimiento.setJustificacionSinHomologo(formCTCProcedimientoDto.getJustificacionSinHomologo());
        return formCTCProcedimiento;
    }

    private Object getById(CrudRepository repo, DescriptivoDto descriptivo) {
        if (descriptivo != null) {
            return repo.findOne(descriptivo.getId());
        }
        return null;
    }

    private Object getById(CrudRepository repo, Integer id) {
        if (id != null) {
            return repo.findOne(id);
        }
        return null;
    }

    private Object getById(CrudRepository repo, Long id) {
        if (id != null) {
            return repo.findOne(id);
        }
        return null;
    }

    private ProcedimientoPosPrevio getProcedimientoPosPrevio(ProcedimientoPosPrevioDto procedimientoPosPrevioDto) {
        ProcedimientoPosPrevio p = new ProcedimientoPosPrevio();
        p.setProcedimiento(procedimientoRepo.findOne(procedimientoPosPrevioDto.getProcedimiento().getId()));
        p.setRespuestaClinicaObservada((RespuestaClinicaObservada) getById(respuestaClinicaObservadaRepo,
                procedimientoPosPrevioDto.getRespuestaClinicaObservada()));
        return p;
    }

    private ProcedimientoHomologo getProcedimientoHomologo(ProcedimientoHomologoDto procedimientoHomologo) {
        ProcedimientoHomologo p = new ProcedimientoHomologo();
        p.setCantidadPeriodo(procedimientoHomologo.getCantidadPeriodo());
        p.setDiasDeUso(procedimientoHomologo.getDiasDeUso());
        p.setFrecuenciaDeUso(procedimientoHomologo.getFrecuenciaDeUso());
        p.setObjetivoProcedimiento((ObjetivoProcedimiento) getById(objetivoRepo, procedimientoHomologo.getObjetivoProcedimiento()));
        p.setProcedimiento(procedimientoRepo.findOne(procedimientoHomologo.getProcedimiento().getId()));
        return p;
    }

    private SolicitudMedicamento getSolicitudMedicamento(SolicitudMedicamentoDto solMedicamentoDto) {

        SolicitudMedicamento sm = new SolicitudMedicamento();
        if (solMedicamentoDto.getFormCTCMedicamento() != null) {
            sm.setFormCTCMedicamento(this.getFormularioCTCMedicamento(solMedicamentoDto.getFormCTCMedicamento()));
        }
        sm.setFormulaMedicamento(this.getFormulaMedicamento(solMedicamentoDto.getFormulaMedicamento()));
        sm.setMedicamento(medicamentoRepo.findOne(solMedicamentoDto.getMedicamento().getId()));

        return sm;
    }

    private FormulaMedicamento getFormulaMedicamento(FormulaMedicamentoDto formulaMedicamentoDto) {

        FormulaMedicamento fm = new FormulaMedicamento();
        if (formulaMedicamentoDto.getCausaExterna() != null) {
            fm.setCausaExterna((CausaExterna) getById(causaExternaRepo, formulaMedicamentoDto.getCausaExterna()));
        }
        if (formulaMedicamentoDto.getFinalidad() != null) {
            fm.setFinalidad((Finalidad) getById(finalidadRepo, formulaMedicamentoDto.getFinalidad()));
        }
        fm.setTipoCatastrofico((TipoCatastrofico) getById(tipoCatastroficoRepo, formulaMedicamentoDto.getTipoCatastrofico()));
        fm.setDosis(Integer.valueOf(formulaMedicamentoDto.getDosis()));
        fm.setDuracion(Integer.valueOf(formulaMedicamentoDto.getDuracion()));
        fm.setFrecuencia(Integer.valueOf(formulaMedicamentoDto.getFrecuencia()));
        fm.setTipoFrecuenciaId(formulaMedicamentoDto.getCada());
        fm.setPosologia(formulaMedicamentoDto.getPosologia());
        fm.setViaAdministracion((ViaAdministracion) getById(viaAdministracionRepo, formulaMedicamentoDto.getViaAdministracion()));
        fm.setEfectosAdversos(formulaMedicamentoDto.getEfectosAdversos());
        return fm;
    }

    private FormularioCTCMedicamento getFormularioCTCMedicamento(FormularioCTCMedicamentoDto formCTCMedicamentoDto) {

        FormularioCTCMedicamento formCTCEntity = new FormularioCTCMedicamento();
        if (formCTCMedicamentoDto.getMedicamentoHomologo() != null) {
            formCTCEntity.setMedicamentoHomologo(medicamentoRepo.findOne(formCTCMedicamentoDto.getMedicamentoHomologo().getId()));
        }
        if (formCTCMedicamentoDto.getMedicamentosAnteriores() != null) {
            for (MedicamentoPosPrevioDto medicamentoPosForm : formCTCMedicamentoDto.getMedicamentosAnteriores()) {
                formCTCEntity.addMedicamentoPosPrevio(getMedicamentoPosPrevio(medicamentoPosForm));
            }
        }
        formCTCEntity.setCausaExterna((CausaExterna) getById(causaExternaRepo, formCTCMedicamentoDto.getCausaExterna()));

        formCTCEntity.setFinalidad((Finalidad) getById(finalidadRepo, formCTCMedicamentoDto.getFinalidad()));
        formCTCEntity.setJustificacionRiesgoInminente(formCTCMedicamentoDto.getJustificacionRiesgoInminente());
        formCTCEntity.setJustificacionSinPosPrevio(formCTCMedicamentoDto.getJustificacionSinPosPrevio());
        formCTCEntity.setJustificacionMedico(formCTCMedicamentoDto.getJustificacionMedico());
        formCTCEntity.setTipoCatastrofico((TipoCatastrofico) getById(tipoCatastroficoRepo, formCTCMedicamentoDto.getTipoCatastrofico()));
        formCTCEntity.setSinAlternativaPos(formCTCMedicamentoDto.getSinAlternativaPos());
        formCTCEntity.setExisteRiesgoInminente(formCTCMedicamentoDto.getExisteRiesgoInminente());
        formCTCEntity.setPosibilidadesPosAgotadas(formCTCMedicamentoDto.getPosibilidadesPosAgotadas());
        formCTCEntity.setAutorizadoINVIMA(formCTCMedicamentoDto.getAutorizadoINVIMA());
        formCTCEntity.setResumenHistoriaClinica(formCTCMedicamentoDto.getResumenHistoriaClinica());

        return formCTCEntity;
    }

    private MedicamentoPosPrevio getMedicamentoPosPrevio(MedicamentoPosPrevioDto medicamentoPosForm) {
        MedicamentoPosPrevio m = new MedicamentoPosPrevio();
        m.setDiasTratamiento(medicamentoPosForm.getDiasTratamiento());
        m.setDosis(medicamentoPosForm.getDosis());
        m.setMedicamento(medicamentoRepo.findOne(medicamentoPosForm.getMedicamento().getId()));
        m.setRespuestaClinicaObservada((RespuestaClinicaObservada) getById(respuestaClinicaObservadaRepo,
                medicamentoPosForm.getRespuestaClinicaObservada()));

        return m;
    }

    private Set<SolicitudDiagnostico> getSolicitudDiagnosticos(Set<SolicitudDiagnosticoDto> solDiagnosticosDto) {

        Set<SolicitudDiagnostico> solicitudDiagnosticos = new HashSet<>();
        SolicitudDiagnostico sDiagnostico = null;
        for (SolicitudDiagnosticoDto solicitudDiagnosticoDto : solDiagnosticosDto) {
            sDiagnostico = new SolicitudDiagnostico();
            sDiagnostico.setDiagnostico(diagnosticoRepo.findOne(solicitudDiagnosticoDto.getDiagnostico().getId()));
            sDiagnostico.setEsPrincipal(solicitudDiagnosticoDto.getEsPrincipal());
            solicitudDiagnosticos.add(sDiagnostico);

            // HashMap para facilitar la asociacion entre SolicitudDiagnostico e
            // Item
            diagnosticos.put(sDiagnostico.getDiagnostico().getCodigo(), sDiagnostico);
        }

        return solicitudDiagnosticos;
    }

    private ResumenHistoriaClinica getResumen(ResumenHistoriaClinicaDto resumenDTO) {
        ResumenHistoriaClinica resumen = null;
        if (resumenDTO != null) {
            resumen = new ResumenHistoriaClinica();
            resumen.setFechaInicio(resumenDTO.getFechaInicio());
            resumen.setFechaFin(resumenDTO.getFechaFin());
            resumen.setEvolucion(resumenDTO.getEvolucion());
            resumen.setCausaExterna((CausaExterna) getById(causaExternaRepo, resumenDTO.getCausaExterna()));
            resumen.setTipoCatastrofico((TipoCatastrofico) getById(tipoCatastroficoRepo, resumenDTO.getTipoCatastrofico()));
            if (!CollectionUtils.isEmpty(resumen.getResumenDiagnosticos())) {
                resumen.setResumenDiagnosticos(new HashSet<ResumenDiagnostico>(resumen.getResumenDiagnosticos().size()));
                for (ResumenDiagnosticoDto d : resumenDTO.getResumenDiagnosticos()) {
                    ResumenDiagnostico rsDto = new ResumenDiagnostico();
                    rsDto.setEsPrincipal(d.getEsPrincipal());
                    rsDto.setDiagnostico(diagnosticoRepo.findOneByCodigoIgnoreCase(d.getDiagnostico().getCodigo()));
                    resumen.getResumenDiagnosticos().add(rsDto);
                }
            }
            resumen.setConducta(resumenDTO.getConducta());

        }
        return resumen;
    }

    private Set<DocumentoSoporte> getDocumentosSoporte(Set<DocumentoSoporteDto> documentosSoporteDto) {

        DocumentoSoporte docSoporte = null;
        Set<DocumentoSoporte> documentosSoprte = new HashSet<>();
        for (DocumentoSoporteDto documentoSoporteDto : documentosSoporteDto) {
            docSoporte = new DocumentoSoporte();
            docSoporte.setTipoDocSoporte((TipoDocumentoSoporte) getById(tipoDocSoporteRepo, documentoSoporteDto.getTipoDocSoporte()));
            docSoporte.setNombreArchivoServidor(documentoSoporteDto.getNombreArchivoServidor());
            docSoporte.setNombreArchivoOriginal(documentoSoporteDto.getNombreArchivoOriginal());
            documentosSoprte.add(docSoporte);
        }

        return documentosSoprte;
    }
}
