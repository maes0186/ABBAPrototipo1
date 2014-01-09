package com.conexia.saludcoop.validador.businessRules;

import java.util.Calendar;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.entity.maestro.Vigencia;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.repository.VigenciaRepository;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.util.DateUtilities;

@Component
@Rule(description = "Valida la vigencia de un items.")
public class RN0008 extends RNProcess {

	private static Logger LOGGER = LoggerFactory.getLogger(RN0013.class);

	@Autowired
	private SolicitudItemRepository sr;

	@Autowired
	private VigenciaRepository vigenciaRepository;

	private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

		Long nroItem = (Long) aContext.get(ConstantesContexto.NRO_SOLICITUD_ITEM);

		SolicitudItem item = sr.findOne(nroItem);

		Vigencia vig;
		boolean vencida = false;

		vig = vigenciaRepository.findVigenciaByTipoTecnologiaAndTipoPPM(item.getTipoTecnologia(), item.getTipoPPM());

		if (vig.getDiasVigencia() != null) {
			Long diasTranscurridos = DateUtilities.getDaysBetween(item.getSolicitud().getFechaCreacion(), Calendar.getInstance().getTime());
			if (diasTranscurridos > vig.getDiasVigencia())
				vencida = true;
		} else {
			Long horasTranscurridas = DateUtilities.getHoursBetween(item.getSolicitud().getFechaCreacion(), Calendar.getInstance().getTime());
			if (horasTranscurridas > vig.getHorasVigencia())
				vencida = true;
		}

		if (vencida)
			return false;
		else
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
			rta.setMensajeRespuesta("Item vencido");

			context.put(ConstantesContexto.RESPUESTA_TRX, rta);
			LOGGER.error("Error en regla: Item vencido");

		}

		return execResult;
	}

}