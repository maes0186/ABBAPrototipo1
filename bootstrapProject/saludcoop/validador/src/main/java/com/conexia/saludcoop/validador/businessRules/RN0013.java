package com.conexia.saludcoop.validador.businessRules;

import java.util.Calendar;
import java.util.HashMap;

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
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Vigencia;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
import com.conexia.saludcoop.common.repository.MedicamentoRepository;
import com.conexia.saludcoop.common.repository.ProcedimientoRepository;
import com.conexia.saludcoop.common.repository.VigenciaRepository;
import com.conexia.saludcoop.common.util.ConstantesTarget;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.util.DateUtilities;

@Component
@Rule(description = "Valida la vigencia de los items y setea el vencimiento en caso negativo.")
public class RN0013 extends RNProcess {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RN0013.class);

	@Autowired
	private ProcedimientoRepository procedimientoRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private EstadoAutorizacionRepository estadoAutorizacionRepo;
	
	@Autowired
	private VigenciaRepository vigenciaRepository;

	private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

		SolicitudDto solicitudDto = (SolicitudDto) aContext.get(ConstantesContexto.SOLICITUD);

		if (solicitudDto != null) {
			Vigencia vig;
			DescriptivoDto estado = new DescriptivoDto();
			RoleDTO role = new RoleDTO();
			boolean vencida = false;
			for (SolicitudItemDto siDto : solicitudDto.getSolicitudItems()) {
				
				if (siDto.getPreAutorizacion() == null)
					siDto.setPreAutorizacion(new PreAutorizacionDto());
				//TODO
				vig = new Vigencia();//vigenciaRepository.findVigenciaByTipoTecnologiaAndTipoPPM(siDto.getTipoTecnologia(), siDto.getTipoPPM().getId());
				
				if (vig.getDiasVigencia() != null){
					Long diasTranscurridos = DateUtilities.getDaysBetween(solicitudDto.getFechaCreacion(), Calendar.getInstance().getTime());
					if (diasTranscurridos > vig.getDiasVigencia())
						vencida = true;
				}
				else {
					Long horasTranscurridas = DateUtilities.getDaysBetween(solicitudDto.getFechaCreacion(), Calendar.getInstance().getTime());
					if (horasTranscurridas > vig.getHorasVigencia())
						vencida = true;
				}
				
				if (vencida){
					estado.setId(EstadoAutorizacion.VENCIDA);
					siDto.getAutorizacion().setEstadoAutorizacion(estado);
					role.setId(ConstantesTarget.SISTEMA);
					siDto.getAutorizacion().setTarget(role);
				}
					
			}
		}
		return true;
	}

	@Override
	protected int executeRegla(HashMap<String, Object> context) throws Exception {
		int execResult = RESULT_OK;

		if (validarRegla(context)) {
			execResult = RESULT_OK;

			LOGGER.info("Se ejecuto con exito la regla " + this.getClass().getName());
		} else {
			RespuestaDto rta = new RespuestaDto();

			Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
			rta.setCodigoRespuesta(codRespuesta);
			rta.setMensajeRespuesta(I18NUtils.getInstance().getText("respuestaTrx.transaccionNoOk"));

			context.put(ConstantesContexto.RESPUESTA_TRX, rta);
			LOGGER.error("Error en regla de verificacion de vigencia");

		}

		return execResult;
	}

}
