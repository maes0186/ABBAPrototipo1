package com.conexia.saludcoop.validador.businessRules;

import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.transaccional.TopTenSedeIpsDto;
import com.conexia.saludcoop.common.entity.transaccional.Autorizacion;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.repository.AutorizacionRepository;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.manager.ListaSedeIpsManager;

@Component
@Rule(description = "Regla que realiza la consulta del TOP 10 de sedes ips para el redireccionamiento de items.")
public class RN0003 extends RNProcess {

	private static Logger LOGGER = LoggerFactory.getLogger(RN0003.class);
	
	@Autowired
	private ListaSedeIpsManager listaSedeIpsManager;
	
	@Autowired
	private AutorizacionRepository autorizacionRepository;
	
	@Autowired
	private SolicitudItemRepository solicitudItemRepository;
	
	
	private boolean validarRegla(HashMap<String, Object> aContext)
			throws Exception {
		try {
			TopTenSedeIpsDto dto = (TopTenSedeIpsDto) aContext
					.get(ConstantesContexto.TOP_TEN_DTO);
			Autorizacion autorizacion = null;
			SolicitudItem solicitudItem = null;
			Set<SedeIpsDto> sedesIpsDtoGrouping=null;
			if (dto.getIdAutorizacion() != null) {
				autorizacion = autorizacionRepository.findOne(dto
						.getIdAutorizacion());
				Set<SolicitudItem> items = autorizacion.getSolicitudItems();
				if (items != null && items.size() != 0)
					for (SolicitudItem item : items) {
						Set<SedeIpsDto> sedesIpsDto = listaSedeIpsManager
								.obtenerListaIps(item);
						if (sedesIpsDtoGrouping == null){
							sedesIpsDtoGrouping = sedesIpsDto;
						}
						else {
							//Interseccion
							sedesIpsDtoGrouping.retainAll(sedesIpsDto);
						}
						
					}
				aContext.put(ConstantesContexto.SEDES_IPS_REDIRECCION,
						sedesIpsDtoGrouping);
			} else {
				Long solicitudItemId = dto.getIdSolicitudItem();
				solicitudItem = solicitudItemRepository.findOneItemByNroItem(solicitudItemId);
				Set<SedeIpsDto> sedesIpsDto = listaSedeIpsManager
						.obtenerListaIps(solicitudItem);
				aContext.put(ConstantesContexto.SEDES_IPS_REDIRECCION,
						sedesIpsDto);
			}
			return true;

		} catch (Throwable e) {
			LOGGER.error("Error ejecutando la regla RN0003", e);
			return false;
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
