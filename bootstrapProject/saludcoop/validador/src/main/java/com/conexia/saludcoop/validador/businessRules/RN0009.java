package com.conexia.saludcoop.validador.businessRules;

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
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
import com.conexia.saludcoop.common.repository.MedicamentoRepository;
import com.conexia.saludcoop.common.util.ConstantesTarget;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Validar si un medicamento es condicionado sin diagnostico asociado valido, en cuyo caso setea estado para direccionar a CTC.")
public class RN0009 extends RNProcess {

	private static Logger LOGGER = LoggerFactory.getLogger(RN0009.class);
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	@Autowired
	private EstadoAutorizacionRepository estadoAutorizacionRepo;
	
	private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

		SolicitudDto solicitudDto = (SolicitudDto) aContext.get(ConstantesContexto.SOLICITUD);

		if (solicitudDto != null) {
			Medicamento medicamento;
			for (SolicitudItemDto siDto : solicitudDto.getSolicitudItems()) {
				if (siDto.getSolMedicamento() != null) {
					medicamento = this.medicamentoRepository.findOne(siDto.getSolMedicamento().getMedicamento().getId());
					if (!medicamento.getVisibleCtc() && siDto.getSolMedicamento().getFormCTCMedicamento() != null){
						if (siDto.getPreAutorizacion() == null)
							siDto.setPreAutorizacion(new PreAutorizacionDto());
						RoleDTO roleDto = new RoleDTO();
						roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
						DescriptivoDto estadoAutorizacion = new DescriptivoDto();
						estadoAutorizacion.setId(EstadoAutorizacion.PENDIENTE_CTC);
						siDto.getPreAutorizacion().setEstadoAutorizacion(estadoAutorizacion);
						siDto.getPreAutorizacion().setTarget(roleDto);
					}
				}
			}
		}
		return true;
	}

	/**
	 * Ejecuta la regla pasando los datos necesarios para su ejecucion.
	 */
	public int executeRegla(HashMap<String, Object> aContext) throws Exception {

		int execResult = RESULT_OK;

		if (validarRegla(aContext)) {
			execResult = RESULT_OK;

			LOGGER.info("Se ejecuto con exito la regla "
					+ this.getClass().getName());
		} else {
			RespuestaDto rta = new RespuestaDto();

			Integer codRespuesta = new Integer(I18NUtils.getInstance().getText(
					"respuestaTrx.codigoNoOk"));
			rta.setCodigoRespuesta(codRespuesta);
			rta.setMensajeRespuesta(I18NUtils.getInstance().getText(
					"respuestaTrx.transaccionNoOk"));

			aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
			LOGGER.error("Error al validar medicamento condicionado");

		}

		return execResult;
	}

}