package com.conexia.saludcoop.validador.businessRules;

import java.math.BigDecimal;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
import java.util.Map;
//import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.MontosCopagoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
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
import com.conexia.saludcoop.validador.determinador.dto.AfiliadoSolicitanteItems;
import com.conexia.saludcoop.validador.determinador.dto.ItemSolicitado;
import com.conexia.saludcoop.validador.determinador.dto.ResultadoVerificacionExencion;
import com.conexia.saludcoop.validador.determinador.dto.TipoItemSolicitado;
import com.conexia.saludcoop.validador.determinador.logica.DeterminadorItemsExentos;
import com.conexia.saludcoop.validador.manager.AfiliadoManager;
import com.conexia.saludcoop.validador.manager.DiagnosticoManager;
import com.conexia.saludcoop.validador.manager.InsumoManager;
import com.conexia.saludcoop.validador.manager.MedicamentoManager;
import com.conexia.saludcoop.validador.manager.ProcedimientoManager;
import com.conexia.saludcoop.validador.provider.MontosCopagoProvider;

/**
 * Regla encargada de realizar la separación de la solicitud en autorizaciones.
 * 
 * @author Sebastián Matienzo
 */
@Component
@Rule(description = "Separacion de la solicitud en autorizaciones.")
public class RN0004 extends RNProcess {

	/**
	 * Logger de la regla.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(RN0004.class);
	
//	/**
//	 * Estados de negación de un ítem (no debe crear grupos de autorización para estos casos).
//	 */
//	private static final Integer[] ESTADOS_NEGADOS = { EstadoAutorizacion.NEGADA_REGIONAL, EstadoAutorizacion.NEGADA_NACIONAL,
//		EstadoAutorizacion.NEGADA_POR_NO_ENCONTRAR_IPS_EN_REDIRECCION, EstadoAutorizacion.NO_AUTORIZADA,
//		EstadoAutorizacion.VENCIDA, EstadoAutorizacion.PENDIENTE_TUTELA, EstadoAutorizacion.PENDIENTE_FORMULARIO_CTC };
	
	/**
	 * Proveedor de montos de copago.
	 */
	@Autowired
	private MontosCopagoProvider montosCopagoProvider;
	
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
	 * Administrador de insumos.
	 */
	@Autowired
	private InsumoManager insumoManager;

	/**
	 * Administrador de afiliados.
	 */
	@Autowired
	private AfiliadoManager afiliadoManager;
	
	/**
	 * Determinador de ítems extensos de pago.
	 */
	@Autowired
	private DeterminadorItemsExentos determinadorItemsExentos;

	/**
	 * Ejecuta la regla pasando los datos necesarios para su ejecución.
	 */
	public int executeRegla(HashMap<String, Object> aContext) throws Exception {

		if (this.validarRegla(aContext)) {
			LOGGER.info("Se ejecuto con exito la regla" + this.getClass().getName());
			
			return (RN0004.RESULT_OK);
		}
		
		Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
		
		RespuestaDto rta = new RespuestaDto();
		
		rta.setCodigoRespuesta(codRespuesta);
		rta.setMensajeRespuesta(I18NUtils.getInstance().getText("respuestaTrx.transaccionNoOk")); 
		
		aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
		RN0004.LOGGER.info("Error desconocido al procesar la transacción.");
		
		return (RN0004.RESULT_NOK);
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
		
		final Map<CriterioUnicidadAutorizacion, AutorizacionDto> autorizacionesAutomaticas = 
				new HashMap<CriterioUnicidadAutorizacion, AutorizacionDto>();
		
		final MontosCopagoDto montosAbonar = this.getMontosAbonar(afiliado, solicitudDto);
		
		final Map<IdentificacionItem, Boolean> exencionDeterminada = 
				this.getIndicadorExencionPorItemSolicitud(afiliado, solicitudDto);
		
		/* Los insumos se agruparán en un único grupo, y se los marcarán como exentos de pago,
		 * conforme a lo convenido con Darío Camarro.
		 */
		final List<SolicitudItemDto> insumosAgrupados = new ArrayList<SolicitudItemDto>();
	
		for (final SolicitudItemDto item : solicitudDto.getSolicitudItems()) {
			
			if (item.isInsumo()) {
				insumosAgrupados.add(item);
				
			} else if (this.isItemAutorizadoAutomaticamente(item)) {
				
				/* Los ítems autorizados automáticamente, se agrupan por varios criterios. Pueden aplicarle exenciones. */
				
				/* Primero se procede a verificar las exenciones que puedan aplicar al ítem */
				final IdentificacionItem claveItem = new IdentificacionItem(item.getItemId(), this.getTipoItemSolicitado(item));
				
				final boolean exentoPago = exencionDeterminada.get(claveItem);
				
				/* Finalmente se procede a asignar al ítem a la autorización correspondiente (si no existe aún, se crea) */
				this.agregarItemMapaAutorizaciones(autorizacionesAutomaticas, item, exentoPago);
				
			} else {
				
				/* Para los ítems no autorizados automáticamente (pero que tampoco sean negados), no se agrupan, 
				 * y no les aplica exención alguna. Si hubiera sido negado, directamente no se crea grupo de autorización.
				 */
				final TipoPagoRequerido tipoPago = item.getTipoPagoRequerido();
				
				final GrupoAutorizacionDto grupoAutorizacion = this.createGrupoAutorizacion(afiliado.getRegimenAfiliacion(), 
						tipoPago, montosAbonar);
				
				final AutorizacionDto autorizacion = this.createAutorizacion(item);
				
				grupoAutorizacion.addAutorizacion(autorizacion);
				
				solicitudDto.addAutorizacion(grupoAutorizacion);
			}
		}
		
		/* Para todas las autorizaciones automáticas generadas, se procede a armar la agrupación correspondiente */
		for (final GrupoAutorizacionDto grupo : this.getGruposAutorizaciones(autorizacionesAutomaticas, 
				afiliado.getRegimenAfiliacion(), montosAbonar)) {
			
			solicitudDto.addAutorizacion(grupo);
		}
		
		/* Para todos los insumos, se procede a armar la agrupación correspondiente (está separado porque,
		 * para cada insumo, no se determinó el servicio y especialidad que les corresponde. Cuando eso se desarrolle,
		 * se puede integrar perfectamente esta lógica de la misma manera que se hace para procedimientos y medicamentos).
		 */
		if (!insumosAgrupados.isEmpty()) {
			
			/* Todos los insumos se encuentran automáticamente exentos de pago, siempre */
			final GrupoAutorizacionDto insumosAutorizadosAutomaticamente = 
					this.createGrupoAutorizacion(afiliado.getRegimenAfiliacion(), TipoPagoRequerido.NO_APLICA, montosAbonar);
			
			insumosAutorizadosAutomaticamente.setAutorizadoAutomaticamente(true);

			for (final SolicitudItemDto insumo : insumosAgrupados) {
				
				if (this.isItemAutorizadoAutomaticamente(insumo)) {
					/* Si fue autorizado automáticamente, lo agrega al grupo correspondiente */
					insumosAutorizadosAutomaticamente.addAutorizacion(this.createAutorizacion(insumo));
				} else {
					/* No fue autorizado automáticamente, pero tampoco negado; arma un grupo exclusivo para el mismo */					
					final GrupoAutorizacionDto grupoNoAutorizadoAutomaticamente = this.createGrupoAutorizacion(
							afiliado.getRegimenAfiliacion(), TipoPagoRequerido.NO_APLICA, montosAbonar);
					
					grupoNoAutorizadoAutomaticamente.addAutorizacion(this.createAutorizacion(insumo));
					
					solicitudDto.addAutorizacion(grupoNoAutorizadoAutomaticamente);
				}
			}
			
			/* Solo se agrega el grupo de autorizados automáticamente, si al menos tiene una autorización */
			if (!insumosAutorizadosAutomaticamente.getAutorizaciones().isEmpty()) {
				solicitudDto.addAutorizacion(insumosAutorizadosAutomaticamente);
			}
		}
				
		return (true);
	}
	
	/**
	 * Obtiene un mapa que indica, para cada ítem autorizado automáticamente de la solicitud, si el mismo está
	 * exento de pago o no.
	 * 
	 * @param afiliado Afiliado.
	 * @param solicitudDto Solicitud.
	 * @return Mapa con indicador, por ítem, de si está exento de pago o no.
	 * @throws Exception En acso de problemas determinando el nivel de Sisben/Ibc del afiliado.
	 */
	private Map<IdentificacionItem, Boolean> getIndicadorExencionPorItemSolicitud(final Afiliado afiliado, 
			final SolicitudDto solicitudDto) throws Exception {
		
		final Map<IdentificacionItem, Boolean> exencionDeterminada = new HashMap<IdentificacionItem, Boolean>();
		
		final List<ItemSolicitado> itemsSolicitados = new ArrayList<ItemSolicitado>();
		
		for (final SolicitudItemDto item : solicitudDto.getSolicitudItems()) {
			
			this.cargaInsumoMedicamentoProcedimientoItem(item);
			
			if (this.isItemAutorizadoAutomaticamente(item)) {
				
				final Long itemId = item.getItemId();
				final TipoItemSolicitado tipoItem = this.getTipoItemSolicitado(item);
				final Long diagnosticoId = (item.isInsumo()) ? null : item.getDiagnostico().getDiagnostico().getId();
				final Long servicioId = (item.isInsumo()) ? null : item.getServicioItem().getId();
				final Integer especialidadId = (item.isInsumo()) ? null : item.getEspecialidadItem().getId();
				
				itemsSolicitados.add(new ItemSolicitado(itemId, tipoItem, item.getTipoPagoRequerido(), 
						diagnosticoId, servicioId, especialidadId));
			}
		}
		
		final AfiliadoSolicitanteItems afiliadoSolicitante = new AfiliadoSolicitanteItems(afiliado.getId(), 
				afiliado.getRegimenAfiliacion(), this.getNivelAfiliado(afiliado), afiliado.getTipoAfiliado());
		
		final List<ResultadoVerificacionExencion> resultadosVerificacionExencion = 
				this.determinadorItemsExentos.isItemExentoPago(solicitudDto.getId(), itemsSolicitados, 
						afiliadoSolicitante, solicitudDto.getFechaCreacion());
		
		for (final ResultadoVerificacionExencion resultado : resultadosVerificacionExencion) {
			
			final IdentificacionItem claveItem = new IdentificacionItem(resultado.getItem().getItemId(), 
					resultado.getItem().getTipoItem());
			
			exencionDeterminada.put(claveItem, resultado.isExento());				

			/* Marca la solicitud en caso de detectar al menos un ítem exento por primera formulación del año */
			if (resultado.isExentoPorPrimeraConsultaAnio()) {
				solicitudDto.setPrimeraFormulacionAnio(true);
			}
		}
		
		return (exencionDeterminada);
	}
	
	/**
	 * Verifica si un ítem fue autorizado automáticamente.
	 * 
	 * @param item Ítem a verificar.
	 * @return True si fue autorizado automáticamente; caso contrario, False.
	 */
	private boolean isItemAutorizadoAutomaticamente(final SolicitudItemDto item) {
		
		if (item == null || item.getPreAutorizacion() == null) {
			throw new IllegalArgumentException("El ítem no está definido, o no tiene una preautorización.");
		}
		
		return (item.getPreAutorizacion().getEstadoAutorizacion() != null
				&& EstadoAutorizacion.AUTORIZADO.equals(item.getPreAutorizacion().getEstadoAutorizacion().getId())
				&& item.getPreAutorizacion().getSedeIpsEfector() != null
				&& item.getPreAutorizacion().getSedeIpsEfector().getId() != null);
	}
	
//	/**
//	 * Verifica si un ítem fue negado.
//	 * 
//	 * @param item Ítem a verificar.
//	 * @return True si fue negado; caso contrario, False.
//	 */
//	private boolean isItemNegado(final SolicitudItemDto item) {
//		
//		if (item == null || item.getPreAutorizacion() == null) {
//			throw new IllegalArgumentException("El ítem no está definido, o no tiene una preautorización.");
//		}
//		
//		final Set<Integer> estadosNegadosSet = new HashSet<Integer>(Arrays.asList(RN0004.ESTADOS_NEGADOS));
//		
//		return (item.getPreAutorizacion().getEstadoAutorizacion() != null
//				&& estadosNegadosSet.contains(item.getPreAutorizacion().getEstadoAutorizacion().getId()));
//	}
	
	/**
	 * Realiza la carga del insumo, medicamento, o procedimiento, contenido en el ítem.
	 * 
	 * @param item Ítem al cual y para el cual realizar la carga.
	 */
	private void cargaInsumoMedicamentoProcedimientoItem(final SolicitudItemDto item) {
		
		if (item.getTipoPagoRequerido() != null) {
			/* Ya se encuentra cargado el ítem, en lo referido a los datos necesarios para la regla */
			return;
		}
		
		if (item.isMedicamento() && item.getSolMedicamento().getMedicamento() != null) {
			
			final MedicamentoDto medicamentoSolicitado = item.getSolMedicamento().getMedicamento();
			
			if (medicamentoSolicitado.getId() != null) {
				item.getSolMedicamento().setMedicamento(this.medicamentoManager
						.getMedicamentoById(medicamentoSolicitado.getId()));
			} else {
				item.getSolMedicamento().setMedicamento(this.medicamentoManager
						.getMedicamentoByCodigo(medicamentoSolicitado.getCodigo()));
			}
			
			return;
			
		} else if (item.getSolProcedimiento() != null && item.getSolProcedimiento().getProcedimiento() != null) {
			
			final ProcedimientoDto procedimientoSolicitado = item.getSolProcedimiento().getProcedimiento();
			
			if (procedimientoSolicitado.getId() != null) {
				item.getSolProcedimiento().setProcedimiento(this.procedimientoManager
						.getProcedimientoById(procedimientoSolicitado.getId()));
			} else {
				item.getSolProcedimiento().setProcedimiento(this.procedimientoManager
						.getProcedimientoByCodigo(procedimientoSolicitado.getCodigo()));
			}
			
			return;
			
		} else if (item.getSolInsumo() != null && item.getSolInsumo().getInsumo() != null) {
			
			final InsumoDto insumoSolicitado = item.getSolInsumo().getInsumo();
			
			if (insumoSolicitado.getId() != null) {
				item.getSolInsumo().setInsumo(this.insumoManager.getInsumoById(insumoSolicitado.getId()));
			} else {
				item.getSolInsumo().setInsumo(this.insumoManager.getInsumoByCodigo(insumoSolicitado.getCodigo()));
			}
			
			return;
		}
		
		throw new IllegalArgumentException("El ítem no tiene definido procedimiento ni medicamento ni insumo.");
	}
	
	/**
	 * Crea un grupo de autorización.
	 * 
	 * @param regimenAfiliacion Régimen de afiliación.
	 * @param tipoPagoRequerido Tipo de pago requerido.
	 * @param montosAbonar Monto a abonar (para cuota moderadora).
	 * @return Grupo de autorización creado.
	 */
	private GrupoAutorizacionDto createGrupoAutorizacion(final RegimenAfiliacion regimenAfiliacion, 
			final TipoPagoRequerido tipoPagoRequerido, final MontosCopagoDto montosAbonar) {
		
		BigDecimal cuotaModeradoraEstimada = new BigDecimal(0);
		
		if (regimenAfiliacion == RegimenAfiliacion.CONTRIBUTIVO 
				&& tipoPagoRequerido == TipoPagoRequerido.CUOTA_MODERADORA) {
			
			if (montosAbonar == null) {
				throw new IllegalArgumentException("Para un contributivo con cuota moderadora, "
						+ "no se especificó el detalle completo de montos a abonar.");
			}
			
			cuotaModeradoraEstimada = montosAbonar.getValorCuotaModeradora();
		}
		
		final GrupoAutorizacionDto grupoAutorizacion = new GrupoAutorizacionDto();
		grupoAutorizacion.setAutorizadoAutomaticamente(false);
		grupoAutorizacion.setCuotaModeradoraEstimada(cuotaModeradoraEstimada);
		grupoAutorizacion.setCuotaModeradoraPagada(false);
		grupoAutorizacion.setTipoPago(tipoPagoRequerido);
		
		return (grupoAutorizacion);
	}
	
	/**
	 * Crea una autorización, a partir de la preautorización de un ítem dado.
	 * 
	 * @param item Ítem cuya preautorización evaluar.
	 * @return Autorización creada.
	 */
	private final AutorizacionDto createAutorizacion(final SolicitudItemDto item) {
		
		if (item.getPreAutorizacion() == null) {
			throw new IllegalArgumentException("El ítem no cuenta con una preautorización.");
		}
		
		final AutorizacionDto autorizacionDto = new AutorizacionDto();
		autorizacionDto.setEstadoAutorizacion(item.getPreAutorizacion().getEstadoAutorizacion());
		autorizacionDto.setFechaAutorizacion(item.getPreAutorizacion().getFechaAutorizacion());
		autorizacionDto.setNumero(null);
		autorizacionDto.setSedeIpsEfector(item.getPreAutorizacion().getSedeIpsEfector());
		autorizacionDto.setSolicitudItems(new ArrayList<SolicitudItemDto>());
		autorizacionDto.setTarget(item.getPreAutorizacion().getTarget());
		autorizacionDto.setTipoPagoRequerido(item.getTipoPagoRequerido());
		autorizacionDto.setServicioId(item.getPreAutorizacion().getServicioId());
		autorizacionDto.setEspecialidadId(item.getPreAutorizacion().getEspecialidadId());
		
		autorizacionDto.getSolicitudItems().add(item);
		item.setAutorizacion(autorizacionDto);
		
		return (autorizacionDto);
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
	 * Genera los grupos de autorizaciones automáticas, conteniendo todas las autorizaciones que se hayan generado.
	 * 
	 * @param autorizacionesAutomaticas Autorizaciones automáticas a agrupar.
	 * @param regimenAfiliacion Régimen de afiliación.
	 * @param montosAbonar Montos a abonar (para determinar cuota moderadora).
	 * @return Grupos de autorizaciones automáticas generados.
	 */
	private Collection<GrupoAutorizacionDto> getGruposAutorizaciones(
			final Map<CriterioUnicidadAutorizacion, AutorizacionDto> autorizacionesAutomaticas,
			final RegimenAfiliacion regimenAfiliacion, final MontosCopagoDto montosAbonar) {
		
		final Map<CriterioUnicidadGrupoAutorizaciones, GrupoAutorizacionDto> gruposAutomaticos = 
				new HashMap<CriterioUnicidadGrupoAutorizaciones, GrupoAutorizacionDto>();
		
		for (final CriterioUnicidadAutorizacion criterio : autorizacionesAutomaticas.keySet()) {
			
			final AutorizacionDto autorizacion = autorizacionesAutomaticas.get(criterio);
			
			final SolicitudItemDto solicitudItemDto = autorizacion.getSolicitudItems().iterator().next();
			
			final Long servicioId = solicitudItemDto.getPreAutorizacion().getServicioId();
			
			final Integer especialidadId = solicitudItemDto.getPreAutorizacion().getEspecialidadId();
			
			final CriterioUnicidadGrupoAutorizaciones claveGrupo = 
					new CriterioUnicidadGrupoAutorizaciones(servicioId, especialidadId, 
							autorizacion.getTipoPagoRequerido());
			
			if (!gruposAutomaticos.containsKey(claveGrupo)) {
				final GrupoAutorizacionDto grupoAutorizacion = this.createGrupoAutorizacion(regimenAfiliacion,
						autorizacion.getTipoPagoRequerido(), montosAbonar);
				
				grupoAutorizacion.addAutorizacion(autorizacion);
				
				gruposAutomaticos.put(claveGrupo, grupoAutorizacion);
				
			} else {
				gruposAutomaticos.get(claveGrupo).addAutorizacion(autorizacion);
			}
		}
		
		return (gruposAutomaticos.values());
	}
	
	/**
	 * Agrega al mapa de autorizaciones, un ítem (generando previamente su autorización, si corresponde).
	 * 
	 * @param autorizaciones Mapa de autorizaciones.
	 * @param item Ítem a agregar.
	 * @param exentoPago Indica si el ítem, por alguna circunstancia, está exento de pago.
	 */
	private void agregarItemMapaAutorizaciones(
			final Map<CriterioUnicidadAutorizacion, AutorizacionDto> autorizaciones, 
			final SolicitudItemDto item, final boolean exentoPago) {
		
		if (item == null || item.getPreAutorizacion() == null 
				|| item.getPreAutorizacion().getSedeIpsEfector() == null
				|| item.getPreAutorizacion().getSedeIpsEfector().getId() == null
				|| item.getPreAutorizacion().getServicioId() == null
				|| item.getPreAutorizacion().getEspecialidadId() == null) {
			
			throw new IllegalArgumentException("El ítem no cuenta con una preautorización");
		}
		
		final TipoPagoRequerido tipoPago = 
				(exentoPago) ? TipoPagoRequerido.NO_APLICA : item.getTipoPagoRequerido();
		
		final CriterioUnicidadAutorizacion claveAutorizaciones = 
				new CriterioUnicidadAutorizacion(item.getPreAutorizacion().getServicioId(), 
						item.getPreAutorizacion().getEspecialidadId(), 
						item.getPreAutorizacion().getSedeIpsEfector().getId(), tipoPago);
		
		if (!autorizaciones.containsKey(claveAutorizaciones)) {
			final AutorizacionDto autorizacion = this.createAutorizacion(item);
			
			autorizaciones.put(claveAutorizaciones, autorizacion);
		} else {
			final AutorizacionDto autorizacion = autorizaciones.get(claveAutorizaciones);
			
			autorizacion.getSolicitudItems().add(item);
			item.setAutorizacion(autorizacion);
		}
	}
	
	/**
	 * Obtiene el tipo de ítem solicitado correspondiente a un ítem de solicitud.
	 * 
	 * @param item Ítem de solicitud.
	 * @return Tipo de ítem solicitado.
	 */
	private TipoItemSolicitado getTipoItemSolicitado(final SolicitudItemDto item) {
		
		return ((item.isProcedimiento()) ? TipoItemSolicitado.PROCEDIMIENTO 
					: ((item.isMedicamento()) ? TipoItemSolicitado.MEDICAMENTO : TipoItemSolicitado.INSUMO));
	}
	
	/**
	 * Criterio utilizado para la unicidad de una autorización.
	 * 
	 * @author Sebastián Matienzo
	 */
	private class CriterioUnicidadAutorizacion {
		
		/**
		 * Identificación del servicio.
		 */
		private Long servicioId;
		
		/**
		 * Identificación de la especialidad.
		 */
		private Integer especialidadId;
		
		/**
		 * Identificador de la sede IPS efectora.
		 */
		private Long sedeIpsEfectoraId;
		
		/**
		 * Tipo de pago.
		 */
		private TipoPagoRequerido tipoPago;
		
		/**
		 * Constructor por defecto.
		 * 
		 * @param servicioId Identificación del servicio.
		 * @param especialidadId Identificación de la especialidad.
		 * @param sedeIpsEfectoraId Identificador de la sede IPS efectora.
		 * @param tipoPago Tipo de pago.
		 */
		public CriterioUnicidadAutorizacion(final Long servicioId, final Integer especialidadId,
				final Long sedeIpsEfectoraId, final TipoPagoRequerido tipoPago) {
			
			this.servicioId = servicioId;
			this.especialidadId = especialidadId;
			this.sedeIpsEfectoraId = sedeIpsEfectoraId;
			this.tipoPago = tipoPago;
		}

		/**
		 * Obtiene la identificación del servicio.
		 * 
		 * @return Identificación del servicio.
		 */
		public Long getServicioId() {
			
			return (this.servicioId);
		}

		/**
		 * Obtiene la identificación de la especialidad.
		 * 
		 * @return Identificación de la especialidad.
		 */
		public Integer getEspecialidadId() {
			
			return (this.especialidadId);
		}

		/**
		 * Obtiene el identificador de la sede IPS efectora.
		 * 
		 * @return Identificador de la sede IPS efectora.
		 */
		public Long getSedeIpsEfectoraId() {
			
			return (this.sedeIpsEfectoraId);
		}

		/**
		 * Obtiene el tipo de pago.
		 * 
		 * @return Tipo de pago.
		 */
		public TipoPagoRequerido getTipoPago() {
			
			return (this.tipoPago);
		}

		@Override
		public int hashCode() {

			final int prime = 31;
			int result = ((this.servicioId != null) ? this.servicioId.hashCode() : 0);
			result = prime * result 
					+ ((this.especialidadId != null) ? this.especialidadId.hashCode() : 0);
			result = prime * result 
					+ ((this.sedeIpsEfectoraId != null) ? this.sedeIpsEfectoraId.hashCode() : 0);
			result = prime * result + ((this.tipoPago != null) ? this.tipoPago.hashCode() : 0);

			return (result);
		}

		@Override
		public boolean equals(final Object obj) {

			if (this == obj) {
				return (true);
			}
			
			if (obj == null || this.getClass() != obj.getClass()) {
				return (false);
			}
			
			final CriterioUnicidadAutorizacion other = (CriterioUnicidadAutorizacion) obj;
			
			if (!this.areObjectsEquals(this.getSedeIpsEfectoraId(), other.getSedeIpsEfectoraId())) {
				return (false);
			}
			
			if (!this.areObjectsEquals(this.getServicioId(), other.getServicioId())) {
				return (false);
			}
			
			if (!this.areObjectsEquals(this.getEspecialidadId(), other.getEspecialidadId())) {
				return (false);
			}
			
			if (this.getTipoPago() != other.getTipoPago()) {
				return (false);
			}
			
			return (true);
		}
		
		/**
		 * Verifica si dos objetos son iguales.
		 * 
		 * @param one Objeto uno.
		 * @param two Objeto dos.
		 * @return True si son iguales; caso contrario, False.
		 */
		private boolean areObjectsEquals(final Object one, final Object two) {
			
			if (one == null) {
				if (two == null) {
					return (true);
				}
				
				return (false);
			}
			
			return (one.equals(two));
		}
	}
	
	/**
	 * Criterio utilizado para la unicidad de un grupo de autorizaciones.
	 * 
	 * @author Sebastián Matienzo
	 */
	private class CriterioUnicidadGrupoAutorizaciones {
		
		/**
		 * Identificación del servicio.
		 */
		private Long servicioId;
		
		/**
		 * Identificación de la especialidad.
		 */
		private Integer especialidadId;
		
		/**
		 * Tipo de pago.
		 */
		private TipoPagoRequerido tipoPago;
		
		/**
		 * Constructor por defecto.
		 * 
		 * @param servicioId Identificación del servicio.
		 * @param especialidadId Identificación de la especialidad.
		 * @param tipoPago Tipo de pago.
		 */
		public CriterioUnicidadGrupoAutorizaciones(final Long servicioId, final Integer especialidadId,
				final TipoPagoRequerido tipoPago) {
			
			this.servicioId = servicioId;
			this.especialidadId = especialidadId;
			this.tipoPago = tipoPago;
		}

		/**
		 * Obtiene la identificación del servicio.
		 * 
		 * @return Identificación del servicio.
		 */
		public Long getServicioId() {
			
			return (this.servicioId);
		}

		/**
		 * Obtiene la identificación de la especialidad.
		 * 
		 * @return Identificación de la especialidad.
		 */
		public Integer getEspecialidadId() {
			
			return (this.especialidadId);
		}

		/**
		 * Obtiene el tipo de pago.
		 * 
		 * @return Tipo de pago.
		 */
		public TipoPagoRequerido getTipoPago() {
			
			return (this.tipoPago);
		}

		@Override
		public int hashCode() {

			final int prime = 31;
			int result = ((this.servicioId != null) ? this.servicioId.hashCode() : 0);
			result = prime * result + ((this.especialidadId != null) ? this.especialidadId.hashCode() : 0);
			result = prime * result + ((this.tipoPago != null) ? this.tipoPago.hashCode() : 0);

			return (result);
		}

		@Override
		public boolean equals(final Object obj) {

			if (this == obj) {
				return (true);
			}
			
			if (obj == null || this.getClass() != obj.getClass()) {
				return (false);
			}
			
			final CriterioUnicidadGrupoAutorizaciones other = (CriterioUnicidadGrupoAutorizaciones) obj;
			
			if (!this.areObjectsEquals(this.getServicioId(), other.getServicioId())) {
				return (false);
			}
			
			if (!this.areObjectsEquals(this.getEspecialidadId(), other.getEspecialidadId())) {
				return (false);
			}
			
			if (this.getTipoPago() != other.getTipoPago()) {
				return (false);
			}
			
			return (true);
		}
		
		/**
		 * Verifica si dos objetos son iguales.
		 * 
		 * @param one Objeto uno.
		 * @param two Objeto dos.
		 * @return True si son iguales; caso contrario, False.
		 */
		private boolean areObjectsEquals(final Object one, final Object two) {
			
			if (one == null) {
				if (two == null) {
					return (true);
				}
				
				return (false);
			}
			
			return (one.equals(two));
		}
	}
	
	/**
	 * Identificación de un ítem solicitado.
	 * 
	 * @author Sebastián Matienzo
	 */
	private class IdentificacionItem {

		/**
		 * Identificador del procedimiento/medicamento.
		 */
		private Long itemId;

		/**
		 * Tipo de ítem.
		 */
		private TipoItemSolicitado tipoItem;
		
		/**
		 * Constructor por defecto.
		 * 
		 * @param itemId Identificador de ítem.
		 * @param tipoItem Tipo de ítem.
		 */
		public IdentificacionItem(final Long itemId, final TipoItemSolicitado tipoItem) {
			
			this.itemId = itemId;
			this.tipoItem = tipoItem;
		}

		/**
		 * Obtiene el identificador del ítem.
		 * 
		 * @return Identificador del ítem.
		 */
		public Long getItemId() {
			
			return (this.itemId);
		}

		/**
		 * Obtiene el tipo de ítem.
		 * 
		 * @return Tipo de ítem.
		 */
		public TipoItemSolicitado getTipoItem() {
			
			return (this.tipoItem);
		}

		@Override
		public int hashCode() {

			final int prime = 31;
			int result = ((this.itemId != null) ? this.itemId.hashCode() : 0);
			result = prime * result + ((this.tipoItem != null) ? this.tipoItem.hashCode() : 0);

			return (result);
		}

		@Override
		public boolean equals(final Object obj) {

			if (this == obj) {
				return (true);
			}
			
			if (obj == null || this.getClass() != obj.getClass()) {
				return (false);
			}
			
			final IdentificacionItem other = (IdentificacionItem) obj;
			
			if (!this.getItemId().equals(other.getItemId())) {
				return (false);
			}
			
			if (this.getTipoItem() != other.getTipoItem()) {
				return (false);
			}
			
			return (true);
		}
	}
}
