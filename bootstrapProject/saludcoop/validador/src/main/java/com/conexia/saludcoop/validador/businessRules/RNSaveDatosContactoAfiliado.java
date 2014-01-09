package com.conexia.saludcoop.validador.businessRules;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ar.com.conexia.rules.Rule;
import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Municipio;
import com.conexia.saludcoop.common.repository.AfiliadoRepository;
import com.conexia.saludcoop.common.repository.MunicipioRepository;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;


/**
 * @author ebarbin
 *
 */
@Component
@Rule(description = "Actualiza los datos de contacto del afiliado.")
public class RNSaveDatosContactoAfiliado extends RNProcess {

	@Autowired
	AfiliadoRepository afiliadoRepository;
	
	@Autowired
	MunicipioRepository municipioRepository;
	
	/**
	 * Logger de la regla.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(RNSaveDatosContactoAfiliado.class);
	
	private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {
		AfiliadoDto afiliadoDto = (AfiliadoDto) aContext.get(ConstantesContexto.AFILIADO_DTO);
		Afiliado afiliado = afiliadoRepository.findOne(afiliadoDto.getId());
		
		Municipio municipio = municipioRepository.findOne(afiliadoDto.getMunicipioResidencia().getId());
		afiliado.setMunicipioResidencia(municipio);
		afiliado.setDepartamentoSeccional(municipio.getDepartamento());
		afiliado.setDireccionDeResidencia(afiliadoDto.getDireccionDeResidencia());
		afiliado.setEmailPersonal(afiliadoDto.getEmailPersonal());
		afiliado.setTelefonoResidencial(afiliadoDto.getTelefonoResidencial());
		afiliado.setTelefonoCelular(afiliadoDto.getTelefonoCelular());
		
		afiliadoRepository.save(afiliado);
		
		//TODO Crear Novedades en tablas de intercambio.
		
		return true;
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
