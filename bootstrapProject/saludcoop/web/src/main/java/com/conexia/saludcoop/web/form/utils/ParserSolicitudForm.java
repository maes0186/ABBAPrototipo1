package com.conexia.saludcoop.web.form.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.transaccional.DocumentoSoporteDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaInsumoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaProcedimientoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormularioCTCInsumoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormularioCTCMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormularioCTCProcedimientoDto;
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
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.web.form.DiagnosticoItemForm;
import com.conexia.saludcoop.web.form.FormularioCTCInsumoForm;
import com.conexia.saludcoop.web.form.FormularioCTCMedicamentoForm;
import com.conexia.saludcoop.web.form.FormularioCTCProcedimientoForm;
import com.conexia.saludcoop.web.form.InsumoItemForm;
import com.conexia.saludcoop.web.form.MedicamentoItemForm;
import com.conexia.saludcoop.web.form.MedicamentoPosPrevioForm;
import com.conexia.saludcoop.web.form.PrescripcionInsumoForm;
import com.conexia.saludcoop.web.form.PrescripcionMedicamentoForm;
import com.conexia.saludcoop.web.form.PrescripcionProcedimientoForm;
import com.conexia.saludcoop.web.form.ProcedimientoItemForm;
import com.conexia.saludcoop.web.form.ProcedimientoPosPrevioForm;
import com.conexia.saludcoop.web.form.ResumenForm;
import com.conexia.saludcoop.web.form.SolicitudForm;
import com.conexia.saludcoop.web.manager.CausaExternaManager;
import com.conexia.saludcoop.web.manager.DiagnosticoManager;
import com.conexia.saludcoop.web.manager.EspecialidadManager;
import com.conexia.saludcoop.web.manager.FinalidadManager;
import com.conexia.saludcoop.web.manager.IAfiliadoManager;
import com.conexia.saludcoop.web.manager.InsumosManager;
import com.conexia.saludcoop.web.manager.LateralidadManager;
import com.conexia.saludcoop.web.manager.MedicamentoManager;
import com.conexia.saludcoop.web.manager.ObjetivoProcedimientoManager;
import com.conexia.saludcoop.web.manager.OrigenRepeticionManager;
import com.conexia.saludcoop.web.manager.ProcedimientoManager;
import com.conexia.saludcoop.web.manager.RespuestaClinicaObservadaManager;
import com.conexia.saludcoop.web.manager.TipoCatastroficoManager;
import com.conexia.saludcoop.web.manager.TipoPPMManager;
import com.conexia.saludcoop.web.manager.TipoPrestacionManager;
import com.conexia.saludcoop.web.manager.ViaAdministracionManager;
import com.conexia.saludcoop.web.manager.exceptions.NoEncontradoException;

@Component
public class ParserSolicitudForm {
	@Autowired
	private DiagnosticoManager diagnosticoManager;
	@Autowired
	private MedicamentoManager mManager;
	@Autowired
	private ProcedimientoManager pManager;
	@Autowired
	private IAfiliadoManager aManager;
	@Autowired
	private EspecialidadManager eManager;
	@Autowired
	private LateralidadManager lateralidadManager;
	@Autowired
	private ViaAdministracionManager viaAdministracionManager;
	@Autowired
	private CausaExternaManager causaExternaManager;
	@Autowired
	private FinalidadManager finalidadManager;
	@Autowired
	private TipoCatastroficoManager tipoCatastroficoManager;
	@Autowired
	private RespuestaClinicaObservadaManager respClinicaObservadaManager;
	@Autowired
	private ObjetivoProcedimientoManager objetivoManager;
	@Autowired
	private TipoPrestacionManager tipoPrestacionManager;
	@Autowired
	private OrigenRepeticionManager origenRepeticionManager;
	@Autowired
	private EspecialidadManager especialidadManager;
	@Autowired
	private InsumosManager iManager;

	@Autowired
	private TipoPPMManager tipoPPMManager;

	public SolicitudDiagnosticoDto toSolicitudDiagnosticoDto(DiagnosticoItemForm dForm) {

		SolicitudDiagnosticoDto sDiagnosticoDto = new SolicitudDiagnosticoDto();

		sDiagnosticoDto.setDiagnostico(diagnosticoManager.findById(dForm.getId()));
		sDiagnosticoDto.setEsPrincipal(dForm.isEsPrincipal());

		return sDiagnosticoDto;

	}

	public SolicitudDto toSolicitudDto(SolicitudForm form, SedeIpsDto sedeIps, HashMap<String,DocumentoSoporteDto> archivos) {
		SolicitudDto solicitud = new SolicitudDto();
		solicitud.setNroSolicitud(form.getNroSolicitud());
		HashMap<String, SolicitudDiagnosticoDto> diagnosticos = new HashMap<>();
		// Se mapean los diagnosticos como SolicitudDiagnosticoDto
		for (DiagnosticoItemForm diagnosticoForm : form.getDiagnosticos()) {
			diagnosticos.put(diagnosticoForm.getCodigo(), this.toSolicitudDiagnosticoDto(diagnosticoForm));
			solicitud.getSolDiagnosticos().add(diagnosticos.get(diagnosticoForm.getCodigo()));
		}

		solicitud.setUsuarioCreador(form.getUserId());
		// Se guarda el formulario de Epicrisis
		try {
			solicitud.setResumenHistoriaClinica(this.toResumenDto(form.getResumen()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SolicitudItemDto sItem;

		// Se mapean los medicamentos a un SolicitudItemDto
		for (MedicamentoItemForm medicamentoForm : form.getMedicamentos()) {
			sItem = new SolicitudItemDto();
			sItem.setDiagnostico(diagnosticos.get(medicamentoForm.getDxAsociado()));
			sItem.setSolMedicamento(this.toSolicitudMedicamentoDto(medicamentoForm));
			sItem.addFormularioAdjunto(archivos.get(SystemConstants.DOC_FORMULARIO_CTC_MEDICAMENTOS + "_" + medicamentoForm.getId()));
			sItem.setTipoPPM(medicamentoForm.getTipoPPM() != null ? tipoPPMManager.getByDescripcion(medicamentoForm.getTipoPPM()) : null);
			sItem.setCantidad(medicamentoForm.getCant());
			sItem.setTipoServicio(sedeIps.getTipoServicio());
			sItem.setTipoTecnologia(SystemConstants.ITEM_MEDICAMENTO);
			sItem.setAplicaTutela(medicamentoForm.getAplicaTutela());
			solicitud.getSolicitudItems().add(sItem);

		}

		// Se mapean los procedimientos a un SolicitudItemDto
		for (ProcedimientoItemForm procedimientoForm : form.getProcedimientos()) {
			sItem = new SolicitudItemDto();
			sItem.setCantidad(procedimientoForm.getCant());
			sItem.setDiagnostico(diagnosticos.get(procedimientoForm.getDxAsociado()));
			sItem.addFormularioAdjunto(archivos.get(SystemConstants.DOC_FORMULARIO_CTC_PROCEDIMIENTO + "_" + procedimientoForm.getId()));
			sItem.setSolProcedimiento(this.toSolicitudProcedimientoDto(procedimientoForm));
			sItem.setTipoPPM(procedimientoForm.getTipoPPM() != null ? tipoPPMManager.getByDescripcion(procedimientoForm.getTipoPPM()) : null);
			sItem.setTipoServicio(sedeIps.getTipoServicio());
			sItem.setTipoTecnologia(SystemConstants.ITEM_PROCEDIMIENTO);
			sItem.setAplicaTutela(procedimientoForm.getAplicaTutela());
			solicitud.getSolicitudItems().add(sItem);
		}
		
		// Se mapean los insumos a un SolicitudItemDto
		for (InsumoItemForm insumoForm : form.getInsumos()) {
			sItem = new SolicitudItemDto();
			sItem.setCantidad(insumoForm.getCant());
			sItem.setDiagnostico(diagnosticos.get(insumoForm.getDxAsociado()));
			sItem.addFormularioAdjunto(archivos.get(SystemConstants.DOC_FORMULARIO_CTC_INSUMO + "_" + insumoForm.getId()));
			sItem.setSolInsumo(this.toSolicitudInsumoDto(insumoForm));
			sItem.setTipoPPM(insumoForm.getTipoPPM() != null ? tipoPPMManager.getByDescripcion(insumoForm.getTipoPPM()) : null);
			sItem.setTipoServicio(sedeIps.getTipoServicio());
			sItem.setTipoTecnologia(SystemConstants.ITEM_INSUMO);
			sItem.setAplicaTutela(insumoForm.getAplicaTutela());
			sItem.setSuperaTopes(insumoForm.getSuperaTopes());
			solicitud.getSolicitudItems().add(sItem);
		}

		return solicitud;
	}

	private SolicitudInsumoDto toSolicitudInsumoDto(InsumoItemForm insumoForm) {
		SolicitudInsumoDto sid = new SolicitudInsumoDto();
		if (insumoForm.getFormularioCTC() != null) {
			sid.setFormCTCInsumo(this.toFormularioCTCInsumoDto(insumoForm.getFormularioCTC()));
		}
		sid.setFormulaInsumo(this.toFormulaInsumoDto(insumoForm.getPrescripcion()));
		try {
			sid.setInsumo(iManager.findById(insumoForm.getId()));
		} catch (NoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
		return sid;
	}

	private FormularioCTCInsumoDto toFormularioCTCInsumoDto(FormularioCTCInsumoForm formularioCTC) {
		FormularioCTCInsumoDto formDto = new FormularioCTCInsumoDto();
		if (formularioCTC.getInsumoPosHomologo() != null && formularioCTC.getInsumoPosHomologo().getId()!= null) {
			try {
				formDto.setInsumoHomologo(iManager.findById(formularioCTC.getInsumoPosHomologo().getId()));
			} catch (NoEncontradoException e) {

				e.printStackTrace();
				return null;
			}
		}
//		InsumoPosPrevioDto insumoDto = null;
//		for (InsumoPosPrevioForm insumoPosForm : formularioCTC.getInsumosPosPrevios()) {
//			if (insumoPosForm.getCodigo() != null && !insumoPosForm.getCodigo().isEmpty()) {
//				insumoDto = new InsumoPosPrevioDto();
//
//				insumoDto.setDiasTratamiento(insumoPosForm.getDiasTratamiento());
//				insumoDto.setCantidad(insumoPosForm.getCantidad());
//				if (insumoPosForm.getRespuestaClinicaObservada() != null) {
//					insumoDto.setRespuestaClinicaObservada(insumoPosForm.getRespuestaClinicaObservada() != null ? respClinicaObservadaManager.findOne(insumoPosForm
//							.getRespuestaClinicaObservada()) : null);
//				}
//				try {
//					insumoDto.setInsumo(iManager.findById(insumoPosForm.getId()));
//				} catch (NoEncontradoException e) {
//					e.printStackTrace();
//					return null;
//				}
//				formDto.getInsumosAnteriores().add(insumoDto);
//			}
//		}

		formDto.setAutorizadoINVIMA(formularioCTC.getAutorizadoINVIMA());
		formDto.setCausaExterna(causaExternaManager.findOne(formularioCTC.getCausaExterna()));
		formDto.setFinalidad((formularioCTC.getFinalidad() != null) ? finalidadManager.findOne(formularioCTC.getFinalidad()) : null);
		formDto.setJustificacionRiesgoInminente(formularioCTC.getJustificacionRiesgoInminente());
		formDto.setJustificacionMedico(formularioCTC.getJustificacionMedico());
//		formDto.setJustificacionSinPosPrevio(formularioCTC.getJustificacionSinPosPrevio());
		formDto.setTipoCatastrofico(formularioCTC.getTipoCatastrofico() != null ? tipoCatastroficoManager.findOne(formularioCTC.getTipoCatastrofico()) : null);

		formDto.setSinAlternativaPos(formularioCTC.isSinAlternativaPos());
		formDto.setExisteRiesgoInminente(formularioCTC.isRiesgoInminente());
		formDto.setPosibilidadesPosAgotadas(formularioCTC.isPosibilidadesPOSAgotadas());
		formDto.setResumenHistoriaClinica(formularioCTC.getResumenHistoriaClinica());
		return formDto;
	}

	private FormulaInsumoDto toFormulaInsumoDto(PrescripcionInsumoForm prescripcion) {
		FormulaInsumoDto formulaDto = new FormulaInsumoDto();

		formulaDto.setCausaExterna(prescripcion.getCausaExterna());
		formulaDto.setFinalidad(prescripcion.getFinalidad());
		formulaDto.setTipoCatastrofico(prescripcion.getTipoCatastrofico());
		formulaDto.setCantidad(prescripcion.getCantidad());
		formulaDto.setDuracion(prescripcion.getDuracion());
		return formulaDto;
	}

	private SolicitudProcedimientoDto toSolicitudProcedimientoDto(ProcedimientoItemForm procedimientoForm) {

		SolicitudProcedimientoDto spd = new SolicitudProcedimientoDto();
		if (procedimientoForm.getFormularioCTC() != null) {
			spd.setFormCTCProcedimiento(this.toFormularioCTCProcedimientoDto(procedimientoForm.getFormularioCTC()));
		}
		spd.setFormulaProcedimiento(this.toFormulaProcedimientoDto(procedimientoForm.getPrescripcion()));
		spd.setProcedimiento(pManager.findById(procedimientoForm.getId()));
		spd.setEspecialidad(especialidadManager.findOne(procedimientoForm.getEspecialidad()));

		return spd;
	}

	private FormularioCTCProcedimientoDto toFormularioCTCProcedimientoDto(FormularioCTCProcedimientoForm formularioCTC) {
		FormularioCTCProcedimientoDto formDto = new FormularioCTCProcedimientoDto();
		ProcedimientoHomologoDto homDto = new ProcedimientoHomologoDto();
		
		if (formularioCTC.getProcedimientoPosHomologo() != null && !StringUtils.isEmpty(formularioCTC.getProcedimientoPosHomologo().getCodigo())) {
			ProcedimientoDto homologo = pManager.findById(formularioCTC.getProcedimientoPosHomologo().getId());
			// ProcedimientoDto homologo
			// =pManager.getProcedimientoByCodigo("872121");
			homDto.setProcedimiento(homologo);
			homDto.setCantidadPeriodo(formularioCTC.getProcedimientoPosHomologo().getCantidadPeriodo());
			homDto.setDiasDeUso(formularioCTC.getProcedimientoPosHomologo().getTiempoDeUso());
			homDto.setFrecuenciaDeUso(formularioCTC.getProcedimientoPosHomologo().getFrecuenciaDeUso());
			homDto.setObjetivoProcedimiento(formularioCTC.getProcedimientoPosHomologo().getObjetivo() != null ? objetivoManager.findOne(formularioCTC.getProcedimientoPosHomologo().getObjetivo())
					: null);
			formDto.setProcedimientoHomologo(homDto);
		}

		for (ProcedimientoPosPrevioForm procedimientoPosForm : formularioCTC.getProcedimientosPosPrevios()) {
			if (procedimientoPosForm != null && !procedimientoPosForm.getCodigo().isEmpty()) {
				ProcedimientoDto proc = pManager.findById(procedimientoPosForm.getId());

				ProcedimientoPosPrevioDto procPrevio = new ProcedimientoPosPrevioDto();
				procPrevio.setProcedimiento(proc);
				procPrevio.setRespuestaClinicaObservada(procedimientoPosForm.getRespuestaClinicaObservada() != null ? respClinicaObservadaManager.findOne(procedimientoPosForm
						.getRespuestaClinicaObservada()) : null);
				formDto.getProcedimientosAnteriores().add(procPrevio);
			}
		}

		formDto.setAutorizadoINVIMA(formularioCTC.getAutorizadoINVIMA());
		formDto.setCausaExterna(causaExternaManager.findOne(formularioCTC.getCausaExterna()));

		formDto.setFinalidad(formularioCTC.getFinalidad() != null ? finalidadManager.findOne(formularioCTC.getFinalidad()) : null);
		formDto.setJustificacionRiesgoInminente(formularioCTC.getJustificacionRiesgoInminente());
		formDto.setJustificacionMedico(formularioCTC.getJustificacionMedico());
		formDto.setJustificacionSinPosPrevio(formularioCTC.getJustificacionSinPosPrevio());
		formDto.setTipoCatastrofico(formularioCTC.getTipoCatastrofico() != null ? tipoCatastroficoManager.findOne(formularioCTC.getTipoCatastrofico()) : null);
		formDto.setSinAlternativaPos(formularioCTC.isSinAlternativaPos());
		formDto.setExisteRiesgoInminente(formularioCTC.isRiesgoInminente());
		formDto.setPosibilidadesPosAgotadas(formularioCTC.isPosibilidadesPOSAgotadas());
		formDto.setResumenHistoriaClinica(formularioCTC.getResumenHistoriaClinica());
		formDto.setJustificacionSinHomologo(formularioCTC.getJustificacionSinHomologo());
		return formDto;

	}

	private FormulaProcedimientoDto toFormulaProcedimientoDto(PrescripcionProcedimientoForm prescripcion) {

		FormulaProcedimientoDto formulaDto = new FormulaProcedimientoDto();
		formulaDto.setCausaExterna(prescripcion.getCausaExterna());
		formulaDto.setFinalidad(prescripcion.getFinalidad());
		formulaDto.setTipoCatastrofico(prescripcion.getTipoCatastrofico());
		formulaDto.setPosologia(prescripcion.getPosologia());
		formulaDto.setLateralidad(prescripcion.getLateralidad() != null ? lateralidadManager.findOne(prescripcion.getLateralidad()) : null);
		formulaDto.setObjetivo(prescripcion.getObjetivo() != null ? objetivoManager.findOne(prescripcion.getObjetivo()) : null);
		formulaDto.setOrigenRepeticion(prescripcion.getOrigen() != null ? origenRepeticionManager.findOne(prescripcion.getOrigen()) : null);
		formulaDto.setTipoPrestacion(prescripcion.getTipoPrestacion() != null ? tipoPrestacionManager.findOne(prescripcion.getTipoPrestacion()) : null);
		return formulaDto;

	}

	private SolicitudMedicamentoDto toSolicitudMedicamentoDto(MedicamentoItemForm medicamentoForm) {

		SolicitudMedicamentoDto smd = new SolicitudMedicamentoDto();
		if (medicamentoForm.getFormularioCTC() != null) {
			smd.setFormCTCMedicamento(this.toFormularioCTCMedicamentoDto(medicamentoForm.getFormularioCTC()));
		}
		smd.setFormulaMedicamento(this.toFormulaMedicamentoDto(medicamentoForm.getPrescripcion()));
		smd.setMedicamento(mManager.findById(medicamentoForm.getId()));
		return smd;
	}

	private FormulaMedicamentoDto toFormulaMedicamentoDto(PrescripcionMedicamentoForm prescripcion) {
		FormulaMedicamentoDto formulaDto = new FormulaMedicamentoDto();

		formulaDto.setCausaExterna(prescripcion.getCausaExterna());
		formulaDto.setFinalidad(prescripcion.getFinalidad());
		formulaDto.setTipoCatastrofico(prescripcion.getTipoCatastrofico());
		formulaDto.setDosis(prescripcion.getDosis());
		formulaDto.setDuracion(prescripcion.getDuracion());
		formulaDto.setFrecuencia(prescripcion.getFrecuencia());
		formulaDto.setCada(prescripcion.getCada());
		formulaDto.setPosologia(prescripcion.getPosologia());
		formulaDto.setViaAdministracion(prescripcion.getViaAdministracion() != null ? viaAdministracionManager.findOne(prescripcion.getViaAdministracion()) : null);
		formulaDto.setEfectosAdversos(prescripcion.getEfectosAdversos());
		return formulaDto;

	}

	private FormularioCTCMedicamentoDto toFormularioCTCMedicamentoDto(FormularioCTCMedicamentoForm formularioCTC) {
		FormularioCTCMedicamentoDto formDto = new FormularioCTCMedicamentoDto();
		if (formularioCTC.getMedicamentoPosHomologo() != null) {
			formDto.setMedicamentoHomologo(mManager.findById(formularioCTC.getMedicamentoPosHomologo().getId()));
			// formDto.setMedicamentoHomologo(mManager.getMedicamentoByCodigo("M000000007"));
		}
		MedicamentoPosPrevioDto medicamentoDto = null;
		for (MedicamentoPosPrevioForm medicamentoPosForm : formularioCTC.getMedicamentosPosPrevios()) {
			if (medicamentoPosForm.getCodigo() != null && !medicamentoPosForm.getCodigo().isEmpty()) {
				medicamentoDto = new MedicamentoPosPrevioDto();

				medicamentoDto.setDiasTratamiento(medicamentoPosForm.getDiasTratamiento());
				medicamentoDto.setDosis(medicamentoPosForm.getDosis());
				if (medicamentoPosForm.getRespuestaClinicaObservada() != null) {
					medicamentoDto.setRespuestaClinicaObservada(medicamentoPosForm.getRespuestaClinicaObservada() != null ? respClinicaObservadaManager.findOne(medicamentoPosForm
							.getRespuestaClinicaObservada()) : null);
				}
				medicamentoDto.setMedicamento(mManager.findById(medicamentoPosForm.getId()));
				formDto.getMedicamentosAnteriores().add(medicamentoDto);
			}
		}

		formDto.setAutorizadoINVIMA(formularioCTC.getAutorizadoINVIMA());
		formDto.setCausaExterna(causaExternaManager.findOne(formularioCTC.getCausaExterna()));
		formDto.setFinalidad((formularioCTC.getFinalidad() != null) ? finalidadManager.findOne(formularioCTC.getFinalidad()) : null);
		formDto.setJustificacionRiesgoInminente(formularioCTC.getJustificacionRiesgoInminente());
		formDto.setJustificacionMedico(formularioCTC.getJustificacionMedico());
		formDto.setJustificacionSinPosPrevio(formularioCTC.getJustificacionSinPosPrevio());
		formDto.setTipoCatastrofico(formularioCTC.getTipoCatastrofico() != null ? tipoCatastroficoManager.findOne(formularioCTC.getTipoCatastrofico()) : null);

		formDto.setSinAlternativaPos(formularioCTC.isSinAlternativaPos());
		formDto.setExisteRiesgoInminente(formularioCTC.isRiesgoInminente());
		formDto.setPosibilidadesPosAgotadas(formularioCTC.isPosibilidadesPOSAgotadas());
		formDto.setResumenHistoriaClinica(formularioCTC.getResumenHistoriaClinica());
		return formDto;
	}

	public ResumenHistoriaClinicaDto toResumenDto(ResumenForm resumen) throws ParseException {
		ResumenHistoriaClinicaDto dto = null;
		if (resumen != null) {
			dto = new ResumenHistoriaClinicaDto();
			dto.setCausaExterna(resumen.getCausaExterna() != null ? causaExternaManager.findOne(resumen.getCausaExterna()) : null);
			dto.setTipoCatastrofico(resumen.getTipoCatastrofico() != null ? tipoCatastroficoManager.findOne(resumen.getTipoCatastrofico()) : null);
			dto.setConducta(resumen.getConducta());
			dto.setEvolucion(resumen.getEvolucion());
			SimpleDateFormat sdf = new SimpleDateFormat(SystemConstants.DATE_PATTERN_SIMPLE);
			dto.setFechaInicio(sdf.parse(resumen.getFechaInicio()));
			dto.setFechaFin(sdf.parse(resumen.getFechaFin()));
			if (!CollectionUtils.isEmpty(resumen.getDiagnosticos())) {
				dto.setResumenDiagnosticos(new HashSet<ResumenDiagnosticoDto>(resumen.getDiagnosticos().size()));
				for (DiagnosticoItemForm d : resumen.getDiagnosticos()) {
					ResumenDiagnosticoDto rsDto = new ResumenDiagnosticoDto();
					rsDto.setEsPrincipal(d.isEsPrincipal());
					rsDto.setDiagnostico(diagnosticoManager.getDiagnosticoByCodigo(d.getCodigo()));
					dto.getResumenDiagnosticos().add(rsDto);
				}
			}
		}
		return dto;
	}

}
