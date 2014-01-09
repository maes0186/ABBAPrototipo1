package com.conexia.saludcoop.validador.businessRules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.ProfesionalDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.UbicacionSedeIpsEfectorProcedimientoDto;
import com.conexia.saludcoop.common.dto.UbicacionSedeIpsProveedorInsumoDto;
import com.conexia.saludcoop.common.dto.UbicacionSedeIpsProveedorMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.PreAutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.entity.configuration.MailContent;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.DepartamentoRegional;
import com.conexia.saludcoop.common.entity.maestro.DirectorMedicoRegional;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.mail.exception.CannotSendEmailException;
import com.conexia.saludcoop.common.mail.service.IEmailSenderService;
import com.conexia.saludcoop.common.repository.AfiliadoRepository;
import com.conexia.saludcoop.common.repository.DirectorMedicoRegionalRepository;
import com.conexia.saludcoop.common.repository.InsumoRepository;
import com.conexia.saludcoop.common.repository.MailContentRepository;
import com.conexia.saludcoop.common.repository.MedicamentoRepository;
import com.conexia.saludcoop.common.repository.ProcedimientoRepository;
import com.conexia.saludcoop.common.util.beanutils.MailContentToken;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.RedireccionInsumo;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.RedireccionMedicamento;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.RedireccionProcedimiento;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.manager.SedeIpsManager;

@Component
@Rule(description = "Regla que realiza el direccionamiento de los Procedimientos, Insumos y Medicamentos.")
public class RN0002 extends RNProcess {

	private static Logger LOGGER = LoggerFactory.getLogger(RN0002.class);

	@Autowired
	private AfiliadoRepository afiliadoRepository;

	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@Autowired
	private InsumoRepository insumoRepository;
	
	@Autowired
	private ProcedimientoRepository procedimientoRepository;

	@Autowired
	private MailContentRepository mailContentRepository;

	@Autowired
	private IEmailSenderService iEmailSenderService;

	@Autowired
	private DirectorMedicoRegionalRepository directorMedicoRegionalRepository;
	
	/**
	 * Lógica para efectuar la redirección de un procedimiento.
	 */
	@Autowired
	private RedireccionProcedimiento redireccionProcedimiento;
	
	/**
	 * Lógica para efectuar la redirección de un procedimiento.
	 */
	@Autowired
	private RedireccionMedicamento redireccionMedicamento;
	
	/**
     * Lógica para efectuar la redirección de un insumo.
     */
    @Autowired
    private RedireccionInsumo redireccionInsumo;
	/**
	 * Administrador de sedes de Ips.
	 */
	@Autowired
	private SedeIpsManager sedeIpsManager;

	
	private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

		SolicitudDto solicitudDto = (SolicitudDto) aContext.get(ConstantesContexto.SOLICITUD);

		Afiliado afiliado = afiliadoRepository.findOne(solicitudDto.getAfiliado().getId());

		List<SolicitudItemDto> solicitudesProcedimientoDto = new ArrayList<SolicitudItemDto>();
		List<SolicitudItemDto> solicitudesMedicamentoDto = new ArrayList<SolicitudItemDto>();
		List<SolicitudItemDto> solicitudesInsumoDto = new ArrayList<SolicitudItemDto>();

		for (SolicitudItemDto siDto : solicitudDto.getSolicitudItems()) {
			if (siDto.isProcedimiento()) {
				solicitudesProcedimientoDto.add(siDto);
			} else if(siDto.isMedicamento()){
				solicitudesMedicamentoDto.add(siDto);
			} else if( siDto.isInsumo()){
				solicitudesInsumoDto.add(siDto);
			}
		}

		//Redirecciono Procedimiento
		if (!solicitudesProcedimientoDto.isEmpty()) {

			Procedimiento procedimiento = null ;

			for (SolicitudItemDto siDto : solicitudesProcedimientoDto) {

				if (siDto.getPreAutorizacion() == null) {
					siDto.setPreAutorizacion(new PreAutorizacionDto());
				}

				procedimiento = procedimientoRepository.findOne(siDto.getSolProcedimiento().getProcedimiento().getId());

				final List<UbicacionSedeIpsEfectorProcedimientoDto> ubicaciones = 
														this.redireccionProcedimiento.redireccionar(procedimiento, afiliado, null, 1);

				if (ubicaciones != null && !ubicaciones.isEmpty()) {
					
					final UbicacionSedeIpsEfectorProcedimientoDto ubicacion = ubicaciones.iterator().next();
					
					siDto.getPreAutorizacion().setSedeIpsEfector(this.sedeIpsManager.getSedeIps(ubicacion.getSedeIpsId()));
					siDto.getPreAutorizacion().setEspecialidadId(ubicacion.getEspecialidadId());
					siDto.getPreAutorizacion().setServicioId(ubicacion.getServicioId());
					
				} else {
					
					DescriptivoDto estado = new DescriptivoDto();
					estado.setId(EstadoAutorizacion.NEGADA_POR_NO_ENCONTRAR_IPS_EN_REDIRECCION);
					siDto.getPreAutorizacion().setEstadoAutorizacion(estado);
					enviarCorreoElectronicoDirectoresRegionales(
							afiliado, solicitudDto.getSedeIps(), procedimiento.getDescripcion(), 
							solicitudDto.getProfesionalSolicitante());
				}
			}
		}

		//Redirecciono Medicamentos
		if (!solicitudesMedicamentoDto.isEmpty()) {

			Medicamento medicamento;

			for (SolicitudItemDto siDto : solicitudesMedicamentoDto) {

				if (siDto.getPreAutorizacion() == null) {
					siDto.setPreAutorizacion(new PreAutorizacionDto());
				}

				medicamento = medicamentoRepository.findOne(siDto.getSolMedicamento().getMedicamento().getId());

				final List<UbicacionSedeIpsProveedorMedicamentoDto> ubicaciones = 
															this.redireccionMedicamento.redireccionar(medicamento, afiliado, null, 1);

				if (ubicaciones != null && !ubicaciones.isEmpty()) {
					
					final UbicacionSedeIpsProveedorMedicamentoDto ubicacion = ubicaciones.iterator().next();
					
					siDto.getPreAutorizacion().setSedeIpsEfector(this.sedeIpsManager.getSedeIps(ubicacion.getSedeIpsId()));
					siDto.getPreAutorizacion().setEspecialidadId(ubicacion.getEspecialidadId());
					siDto.getPreAutorizacion().setServicioId(ubicacion.getServicioId());
					
				} else {
					
					DescriptivoDto estado = new DescriptivoDto();
					estado.setId(EstadoAutorizacion.NEGADA_POR_NO_ENCONTRAR_IPS_EN_REDIRECCION);
					siDto.getPreAutorizacion().setEstadoAutorizacion(estado);
					enviarCorreoElectronicoDirectoresRegionales(
							afiliado, solicitudDto.getSedeIps(), medicamento.getDescripcion(), 
							solicitudDto.getProfesionalSolicitante());
				} 
			}
		}
		
		//Redirecciono Insumos
		if (!solicitudesInsumoDto.isEmpty()) {
		    Insumo insumo;

            for (SolicitudItemDto siDto : solicitudesInsumoDto) {

                if (siDto.getPreAutorizacion() == null) {
                    siDto.setPreAutorizacion(new PreAutorizacionDto());
                }

                insumo = insumoRepository.findOne(siDto.getSolInsumo().getInsumo().getId());

                final List<UbicacionSedeIpsProveedorInsumoDto> ubicaciones =
                                                            this.redireccionInsumo.redireccionar(insumo, afiliado, null, 1);

                if (ubicaciones != null && !ubicaciones.isEmpty()) {
                    
                    final UbicacionSedeIpsProveedorInsumoDto ubicacion = ubicaciones.iterator().next();
                    
                    siDto.getPreAutorizacion().setSedeIpsEfector(this.sedeIpsManager.getSedeIps(ubicacion.getSedeIpsId()));
                    siDto.getPreAutorizacion().setEspecialidadId(ubicacion.getEspecialidadId());
                    siDto.getPreAutorizacion().setServicioId(ubicacion.getServicioId());
                    
                } else {
                    
                    DescriptivoDto estado = new DescriptivoDto();
                    estado.setId(EstadoAutorizacion.NEGADA_POR_NO_ENCONTRAR_IPS_EN_REDIRECCION);
                    siDto.getPreAutorizacion().setEstadoAutorizacion(estado);
                    enviarCorreoElectronicoDirectoresRegionales(
                            afiliado, solicitudDto.getSedeIps(), insumo.getDescripcion(), 
                            solicitudDto.getProfesionalSolicitante());
                } 
            }
		}

		return true;
	}

	private void enviarCorreoElectronicoDirectoresRegionales(Afiliado afiliado, SedeIpsDto sedeIpsDto,
			String descripcionProcedimientoMedicamento, ProfesionalDto profesionalDto) {

		MailContent mailContent =  mailContentRepository.findOneMailContentById(
				MailContent.AVISO_DIRECTOR_REGIONAL_NO_IPS_PARA_DIRECCIONAR_PROCEDIMIENTO);

		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");

		String body = mailContent.getBody().replace(MailContentToken.FECHA_ACTUAL.getCodigo(), sdfDate.format(new Date()));
		body = body.replace(MailContentToken.TIPO_DOCUMENTO.getCodigo(), afiliado.getTipoIdentificacion().getDescripcion());
		body = body.replace(MailContentToken.NUMERO_DOCUMENTO.getCodigo(), afiliado.getNumeroIdentificacion());
		body = body.replace(MailContentToken.NOMBRE_COMPLETO_PACIENTE.getCodigo(), afiliado.getNombreCompleto());
		body = body.replace(MailContentToken.CUPS_NOMBRE_PROCEDIMIENTO.getCodigo(), descripcionProcedimientoMedicamento);
		body = body.replace(MailContentToken.NOMBRE_IPS.getCodigo(), sedeIpsDto.getNombre());
		body = body.replace(MailContentToken.NOMBRE_MEDICO.getCodigo(), profesionalDto.getNombreCompleto());

		List<DirectorMedicoRegional> directoresRegionales;

		for (DepartamentoRegional regional : afiliado.getMunicipioResidencia().getDepartamento().getRegionales()) {
			directoresRegionales = directorMedicoRegionalRepository.findByRegionalId(regional.getRegional().getId());
			for (DirectorMedicoRegional dmr : directoresRegionales) {
				try {
					//TODO Para la salida, descomentar esta linea y comentar la que sigue.
					//iEmailSenderService.sendEmail(dmr.getEmail(), mailContent.getSubject(), body);
					iEmailSenderService.sendEmail("ebarbin@conexia.com", mailContent.getSubject(), body);
				} catch (CannotSendEmailException e) {
					LOGGER.error("Error al enviar correo electronico " + dmr.getEmail() + " " + e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * Ejecuta la regla pasando los datos necesarios para su ejecucion.
	 */
	public int executeRegla(HashMap<String, Object> aContext) throws Exception {
		try {
			int execResult = RESULT_OK;
			if (validarRegla(aContext)) {
				execResult = RESULT_OK;
			}
			LOGGER.info("Se ejecuto con exito la regla" + this.getClass().getName());
			return execResult;
		} catch (Exception e) {
			LOGGER.error("Regla" + this.getClass().getName() + " - " + e.getMessage(), e);
			return RESULT_NOK;
		}
	}
}
