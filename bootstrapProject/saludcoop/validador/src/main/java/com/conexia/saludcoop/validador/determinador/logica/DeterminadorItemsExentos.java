package com.conexia.saludcoop.validador.determinador.logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.common.enumerator.TipoAfiliado;
import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;
import com.conexia.saludcoop.validador.determinador.dto.AfiliadoSolicitanteItems;
import com.conexia.saludcoop.validador.determinador.dto.ItemSolicitado;
import com.conexia.saludcoop.validador.determinador.dto.ResultadoVerificacionExencion;
import com.conexia.saludcoop.validador.determinador.dto.TipoItemSolicitado;
import com.conexia.saludcoop.validador.manager.AfiliadoManager;
import com.conexia.saludcoop.validador.manager.SolicitudManager;

/**
 * Clase encargada de determinar, en un momento dado, qué ítems están exentos de pago (sea
 * cuota moderadora, o bien copago), y cuáles no.
 * 
 * @author Sebastián Matienzo
 */
@Service
@Transactional
public class DeterminadorItemsExentos {
	
	/**
	 * Administrador de afiliados.
	 */
	@Autowired
	private AfiliadoManager afiliadoManager;
	
	/**
	 * Administrador de solicitudes.
	 */
	@Autowired
	private SolicitudManager solicitudManager;

	/**
	 * Determina si un único ítem está exento de pago alguno.
	 * 
	 * @param idSolicitud Id de la solicitud a la que corresponden los ítems (null si es nueva).
	 * @param item Ítem a verificar.
	 * @param afiliado Afiliado al que aplica la verificación.
	 * @param fechaSolicitud Fecha para la cual solicita la verificación.
	 * @return True si está exento de pago; caso contrario, False.
	 */
	public boolean isItemExentoPago(final Long idSolicitud, final ItemSolicitado item, final AfiliadoSolicitanteItems afiliado, final Date fechaSolicitud) {
		
		final List<ItemSolicitado> listaUnResultado = new ArrayList<ItemSolicitado>();
		listaUnResultado.add(item);
		
		return (this.isItemExentoPago(idSolicitud, listaUnResultado, afiliado, fechaSolicitud).iterator().next().isExento());
	}

	/**
	 * Determina, para una lista de ítems, cuáles de ellos están exentos de pago.
	 * 
	 * @param idSolicitud Id de la solicitud a la que corresponden los ítems (null si es nueva).
	 * @param items Ítems a verificar.
	 * @param afiliado Afiliado al que aplica la verificación.
	 * @param fechaSolicitud Fecha para la cual solicita la verificación.
	 * @return Lista de resultados de la verificación.
	 */
	public List<ResultadoVerificacionExencion> isItemExentoPago(final Long idSolicitud, final List<ItemSolicitado> items,
			final AfiliadoSolicitanteItems afiliado, final Date fechaSolicitud) {
		
		final List<ResultadoVerificacionExencion> resultados = new ArrayList<ResultadoVerificacionExencion>();
		
		/*  Verifica en primera instancia si una generalidad del afiliado exime a todo ítem */
		final boolean exentosPorGeneralidad = this.isExentoPorGeneralidad(afiliado);
		
		/* Determina, de cara a los elementos iterados, de antemano, si es la primera consulta (la cual
		 * exime el pago de cuota moderadora de todos los ítems de la solicitud).
		 */
		final boolean esPrimeraConsultaAnio = this.isPrimeraConsultaAfiliadoAnio(idSolicitud, afiliado, fechaSolicitud);
		
		for (final ItemSolicitado item : items) {
			
			Boolean exencionDeterminada = null;
			boolean exencionPorPrimeraConsultaAnio = false;
			
			/* Los insumos están siempre exentos */
			if (exentosPorGeneralidad || item.getTipoItem() == TipoItemSolicitado.INSUMO) {
				exencionDeterminada = true;
			} else {
				if (item.getTipoPagoRequerido() == TipoPagoRequerido.CUOTA_MODERADORA && esPrimeraConsultaAnio) {
					exencionDeterminada = true;
					exencionPorPrimeraConsultaAnio = true;
				} else {
					exencionDeterminada = this.isItemEspecificoExento(item, afiliado);
				}
			}
			
			resultados.add(new ResultadoVerificacionExencion(item, exencionDeterminada, exencionPorPrimeraConsultaAnio));
		}
		
		return (resultados);
	}
	
	/**
	 * Indica si todos los ítems están exentos por una generalidad propia del afiliado.
	 * 
	 * @param afiliado Afiliado a verificar.
	 * @return True si están todos exentos por generalidad; caso contrario, False.
	 */
	private boolean isExentoPorGeneralidad(final AfiliadoSolicitanteItems afiliado) {
		
		if (afiliado.getRegimenAfiliacion() == RegimenAfiliacion.CONTRIBUTIVO) {
			
			/* No hay exención general alguna dependiendo únicamente del afiliado */
			return (false);
			
		} else if (afiliado.getRegimenAfiliacion() == RegimenAfiliacion.SUBSIDIADO) {
			
			/* Los niveles Sisben 0 y 1 no abonan nunca copago ni cuota moderadora */
			if (afiliado.getNivelAfiliacion() == 0 || afiliado.getNivelAfiliacion() == 1) {
				return (true);
			}
			
			/* Si es una población especial eximida, tampoco abona */
			if (this.afiliadoManager.esGrupoPoblacionalEximidoSubsidiado(afiliado.getId())) {
				return (true);
			}
			
			return (false);
		}
		
		throw new IllegalArgumentException("El régimen de afiliación es inválido");
	}
	
	/**
	 * Verifica si un ítem en particular está exento.
	 * 
	 * @param item Ítem a verificar.
	 * @param afiliado Afiliado solicitante.
	 * @return True si está exento; caso contrario, False.
	 */
	private boolean isItemEspecificoExento(final ItemSolicitado item, final AfiliadoSolicitanteItems afiliado) {
		
		/* El pago compartido no existe más en Saludcoop, y nunca debería haber un ítem
		 * con Copago + Cuota moderadora (se indicó que ese tipo figura en la tabla de tipos de pago,
		 * pero que no aplica a ítem alguno).
		 */
		if (item.getTipoPagoRequerido() == TipoPagoRequerido.COPAGO_MAS_CUOTA_MODERADORA
				|| item.getTipoPagoRequerido() == TipoPagoRequerido.PAGO_COMPARTIDO
				|| item.getTipoPagoRequerido() == TipoPagoRequerido.NO_APLICA) {
			
			return (true);
		}
	
		/* Verifica una excepción general propia del régimen, primero */
		if (afiliado.getRegimenAfiliacion() == RegimenAfiliacion.CONTRIBUTIVO) {
			
			if (item.getTipoPagoRequerido() == TipoPagoRequerido.COPAGO) {
				
				/* El cotizante no debe abonar copago */
				if (afiliado.getTipoAfiliado() == TipoAfiliado.COTIZANTE) {
					return (true);
				}
			}
			
		} else if (afiliado.getRegimenAfiliacion() == RegimenAfiliacion.SUBSIDIADO) {
			
			/* No existe cuota moderadora en el régimen subsidiado. Se entiende que no aplica pago. */
			if (item.getTipoPagoRequerido() == TipoPagoRequerido.CUOTA_MODERADORA) {
				
				return (true);
			}
			
		} else {
			throw new IllegalArgumentException("El régimen de afiliación es inválido");
		}
		
		/* Si el ítem está incluido en un programa del afiliado de promoción y prevención, queda exento
		 * de pago (sin importar si es cuota moderadora o copago).
		 */
		switch (item.getTipoItem()) {
		
			case PROCEDIMIENTO:
				return (this.afiliadoManager.esProcedimientoExentoProgramaAfiliado(afiliado.getId(), item.getItemId()));
				
			case MEDICAMENTO:
				return (this.afiliadoManager.esMedicamentoExentoProgramaAfiliado(afiliado.getId(), item.getItemId()));
			
			case INSUMO:
				/* Conforme a lo visto con Darío Camarro, no existe exención por PyP de insumos */
				return (false);
				
			default:
				throw new IllegalArgumentException("El tipo de ítem es inválido.");
		}
	}
	
	/**
	 * Determina si es la primera consulta que se realiza el afiliado en el año.
	 * 
	 * @param idSolicitud Id de la solicitud a la que corresponden los ítems (null si es nueva).
	 * @param afiliado Afiliado a verificar.
	 * @param fechaSolicitud Fecha de la solicitud.
	 * @return True si es la primera consulta médica; caso contrario, False.
	 */
	private boolean isPrimeraConsultaAfiliadoAnio(final Long idSolicitud, final AfiliadoSolicitanteItems afiliado, final Date fechaSolicitud) {
		
		if (idSolicitud == null) {
			final Calendar cal = Calendar.getInstance();
			cal.setTime(fechaSolicitud);
			
			return (!this.afiliadoManager.tieneTransaccionesEnAnio(afiliado.getId(), cal.get(Calendar.YEAR)));
		}
		
		return (this.solicitudManager.isPrimeraFormulacionAnio(idSolicitud));
	}
}
