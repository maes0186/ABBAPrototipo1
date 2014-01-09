package com.conexia.saludcoop.validador.businessRules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.MontosCopagoDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.GrupoAutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.determinador.dto.ItemSolicitado;
import com.conexia.saludcoop.validador.determinador.dto.ResultadoCalculoCopago;
import com.conexia.saludcoop.validador.determinador.logica.DeterminadorTarifaCopago;
import com.conexia.saludcoop.validador.manager.DiagnosticoManager;
import com.conexia.saludcoop.validador.manager.MedicamentoManager;
import com.conexia.saludcoop.validador.manager.ProcedimientoManager;
import com.conexia.saludcoop.validador.manager.TotalizacionCopagosManager;
import com.conexia.saludcoop.validador.provider.MontosCopagoProvider;

/**
 * Regla encargada de realizar el cálculo estimado de cuota moderadora y copago.
 * 
 * @author Sebastián Matienzo
 */
@Component
@Rule(description = "Calculo estimado de cuota moderadora y copago.")
public class RN0014 extends RNProcess {
	
	/**
	 * Logger de la regla.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(RN0014.class);
	
	/**
	 * Cien porciento.
	 */
	private static final int CIEN_PORCIENTO = 100;
	
	/**
	 * Proveedor de montos de copago.
	 */
	@Autowired
	private MontosCopagoProvider montosCopagoProvider;
	
	/**
	 * Administrador de totalización del copago de un afiliado en un año.
	 */
	@Autowired
	private TotalizacionCopagosManager totalizacionCopagosManager;
	
	/**
	 * Administrador de diagnósticos.
	 */
	@Autowired
	private DiagnosticoManager diagnosticoManager;
	
	/**
	 * Administrador de procedimientos.
	 */
	@Autowired
	private ProcedimientoManager procedimientoManager;
	
	/**
	 * Administrador de medicamentos.
	 */
	@Autowired
	private MedicamentoManager medicamentoManager;
	
	/**
	 * Determinador de tarifas de copago.
	 */
	@Autowired
	private DeterminadorTarifaCopago determinadorTarifaCopago;

	/**
	 * Ejecuta la regla pasando los datos necesarios para su ejecución.
	 */
	public int executeRegla(HashMap<String, Object> aContext) throws Exception {

		if (this.validarRegla(aContext)) {
			LOGGER.info("Se ejecuto con exito la regla" + this.getClass().getName());
			
			return (RN0014.RESULT_OK);
		}
		
		Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
		
		RespuestaDto rta = new RespuestaDto();
		
		rta.setCodigoRespuesta(codRespuesta);
		rta.setMensajeRespuesta(I18NUtils.getInstance().getText("respuestaTrx.transaccionNoOk")); 
		
		aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
		RN0014.LOGGER.info("Error desconocido al procesar la transacción.");
		
		return (RN0014.RESULT_NOK);
	}

	/**
	 * Valida la regla.
	 * 
	 * @param aContext Contexto de ejecución.
	 * @return True si la ejecución fue correcta; caso contrario, False.
	 * @throws Exception En caso de problemas durante la ejecución.
	 */
	private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {
		
		final Afiliado afiliado = (Afiliado) aContext.get(ConstantesContexto.AFILIADO);
		final SolicitudDto solicitudDto = (SolicitudDto) aContext.get(ConstantesContexto.SOLICITUD);
		
		final MontosCopagoDto montosAbonar = this.getMontosAbonar(afiliado, solicitudDto);
		
		BigDecimal porcentajeValorServicioCobro = (montosAbonar == null) ?
				new BigDecimal(RN0014.CIEN_PORCIENTO) : montosAbonar.getCopagoPorcentajeValorServicio();
		
		porcentajeValorServicioCobro = porcentajeValorServicioCobro.divide(new BigDecimal(RN0014.CIEN_PORCIENTO));
		
		for (final GrupoAutorizacionDto grupo : solicitudDto.getGruposAutorizaciones()) {
			
			if (grupo.getTipoPago() == TipoPagoRequerido.COPAGO) {
				
				/* No aplica cuota moderadora */
				grupo.setCuotaModeradoraEstimada(new BigDecimal(0));
				
				boolean isGrupoAutorizadoAutomaticamente = this.isGrupoAutorizadoAutomaticamente(grupo);
				
				if (isGrupoAutorizadoAutomaticamente) {
					
					/* Aplica copago */
					for (final AutorizacionDto autorizacion : grupo.getAutorizaciones()) {
						
						final Long sedeIpsId = autorizacion.getSedeIpsEfector().getId();
						final Date fechaSolicitud = solicitudDto.getFechaCreacion();
						
						/* El ítem solicitado necesito conocerlo por posición de memoria, por eso no interesa que redefina
						 * equals ni hashCode.
						 */
						final Map<ItemSolicitado, SolicitudItemDto> itemsSolicitados = 
								new HashMap<ItemSolicitado, SolicitudItemDto>();
						
						for (final SolicitudItemDto item : autorizacion.getSolicitudItems()) {
							itemsSolicitados.put(new ItemSolicitado(item), item);
						}
						
						final List<ResultadoCalculoCopago> resultados = this.determinadorTarifaCopago
								.getTarifasCopagos(new ArrayList<ItemSolicitado>(itemsSolicitados.keySet()), 
										sedeIpsId, fechaSolicitud);
						
						for (final ResultadoCalculoCopago resultado : resultados) {
							/* Se toma el porcentaje del monto del copago que corresponde conforme al nivel de Sisben/Ibc del afiliado */
							itemsSolicitados.get(resultado.getItem()).setCopagoEstimado(
													resultado.getMontoCopago().multiply(porcentajeValorServicioCobro));
						}
					}
					
				} else {
					/* No aplica copago */
					for (final AutorizacionDto autorizacion : grupo.getAutorizaciones()) {
						for (final SolicitudItemDto item : autorizacion.getSolicitudItems()) {
							item.setCopagoEstimado(new BigDecimal(0));
						}
					}
				}
				
			} else if (grupo.getTipoPago() == TipoPagoRequerido.CUOTA_MODERADORA) {
				
				if (!this.isGrupoAutorizadoAutomaticamente(grupo)) {
					/* No aplica cuota moderadora */
					grupo.setCuotaModeradoraEstimada(new BigDecimal(0));
				} else {
					/* Aplica cuota moderadora */
					if (montosAbonar == null) {
						throw new IllegalArgumentException("No hay datos del valor de la cuota moderadora.");
					}
					
					grupo.setCuotaModeradoraEstimada(montosAbonar.getValorCuotaModeradora());
				}
				
				/* No aplica copago */
				for (final AutorizacionDto autorizacion : grupo.getAutorizaciones()) {
					for (final SolicitudItemDto item : autorizacion.getSolicitudItems()) {
						item.setCopagoEstimado(new BigDecimal(0));
					}
				}
				
			} else if (grupo.getTipoPago() == TipoPagoRequerido.NO_APLICA) {
				
				/* No aplica cuota moderadora */
				grupo.setCuotaModeradoraEstimada(new BigDecimal(0));
				
				/* No aplica copago */
				for (final AutorizacionDto autorizacion : grupo.getAutorizaciones()) {
					for (final SolicitudItemDto item : autorizacion.getSolicitudItems()) {
						item.setCopagoEstimado(new BigDecimal(0));
					}
				}
			}
		}
		
		return (true);
	}
	
	/**
	 * Verifica si un ítem fue autorizado automáticamente.
	 * 
	 * @param item Ítem a verificar.
	 * @return True si fue autorizado automáticamente; caso contrario, False.
	 */
	private boolean isGrupoAutorizadoAutomaticamente(final GrupoAutorizacionDto grupo) {
		
		if (grupo == null || grupo.getAutorizaciones() == null
				 || grupo.getAutorizaciones().size() == 0) {
			throw new IllegalArgumentException("El grupo no está definido, o no tiene autorizaciones.");
		}
		
		for (final AutorizacionDto autorizacion : grupo.getAutorizaciones()) {
			if (autorizacion.getEstadoAutorizacion() == null
					|| !EstadoAutorizacion.AUTORIZADO.equals(autorizacion.getEstadoAutorizacion().getId())) {
				
				return (false);
			}
		}
		
		return (true);
	}
	
	/**
	 * Obtiene el nivel del afiliado.
	 * 
	 * @param afiliado Afiliado cuyo nivel obtener.
	 * @return Nivel obtenido.
	 * @throws Exception Si el afiliado no tiene un régimen de afiliación válido.
	 */
	private Integer getNivelAfiliado(final Afiliado afiliado) throws Exception {
		
		if (afiliado.getRegimenAfiliacion() == RegimenAfiliacion.CONTRIBUTIVO
				&& afiliado.getNivelIbc() != null && afiliado.getNivelIbc().getCodigo() != null) {
			return (new Integer(afiliado.getNivelIbc().getCodigo()));
		} else if (afiliado.getRegimenAfiliacion() == RegimenAfiliacion.SUBSIDIADO
				&& afiliado.getNivelSisBen() != null && afiliado.getNivelSisBen().getCodigo() != null) {
			return (new Integer(afiliado.getNivelSisBen().getCodigo()));
		}

		throw new Exception("El régimen de afiliación es inválido, o no fue especificado.");
	}
	
	/**
	 * Obtiene el año de la solicitud.
	 * 
	 * @param solicitudDto Solicitud cuyo año obtener.
	 * @return Año obtenido.
	 * @throws Exception Si la solicitud no tiene la fecha de creación.
	 */
	private Integer getAnioSolicitud(final SolicitudDto solicitudDto) throws Exception {
		
		if (solicitudDto.getFechaCreacion() == null) {
			throw new Exception("La fecha de creación de la solicitud no fue especificada.");
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(solicitudDto.getFechaCreacion());
		
		return (cal.get(Calendar.YEAR));
	}
	
	/**
	 * Obtiene los montos de copago que corresponden al afiliado y a la solicitud.
	 * 
	 * @param afiliado Afiliado.
	 * @param solicitudDto Solicitud.
	 * @return Montos de copago correspondientes.
	 * @throws Exception Si el monto de copago no existe, o si la fecha de solicitud,
	 * 						o bien el nivel del afiliado, no es válido.
	 */
	private MontosCopagoDto getMontosAbonar(final Afiliado afiliado, final SolicitudDto solicitudDto) throws Exception {
		
		final Integer anio = this.getAnioSolicitud(solicitudDto);
		final Integer nivel = this.getNivelAfiliado(afiliado);
		final RegimenAfiliacion regimen = afiliado.getRegimenAfiliacion();
		
		return (this.montosCopagoProvider.getMontosCopagoPorAnio(anio, nivel, regimen));
	}
}
