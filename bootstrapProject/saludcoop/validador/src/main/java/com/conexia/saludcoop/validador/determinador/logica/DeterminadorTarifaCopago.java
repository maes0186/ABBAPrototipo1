package com.conexia.saludcoop.validador.determinador.logica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.TarifaProcedimientoMedicamentoInsumoDto;
import com.conexia.saludcoop.validador.determinador.dto.ItemSolicitado;
import com.conexia.saludcoop.validador.determinador.dto.ResultadoCalculoCopago;
import com.conexia.saludcoop.validador.determinador.dto.TipoItemSolicitado;
import com.conexia.saludcoop.validador.manager.HistorialSMLDVManager;
import com.conexia.saludcoop.validador.manager.HistorialVariacionIPCManager;
import com.conexia.saludcoop.validador.manager.TarifaManager;

@Service
@Transactional
public class DeterminadorTarifaCopago {
	
	/**
	 * Cantidad de decimales que se van a manipular. Para centavos, se usan dos, pero el IPC
	 * fuerza a utilizar 4.
	 */
	private static final Integer CANTIDAD_DECIMALES = 4;
	
	/**
	 * Representa el 100 %.
	 */
	private static final BigDecimal CIEN_PORCIENTO = new BigDecimal(100);

	/**
	 * Año a partir del cual existen tarifarios que contemplan variaciones de IPC.
	 */
	private static final Integer ANIO_INICIO_VARIACION_IPC = 2000;

	/**
	 * Año a partir del cual existe el tarifario ISS 2000.
	 */
	private static final Integer ANIO_INICIO_ISS_2000 = 2000;

	/**
	 * Año a partir del cual existe el tarifario ISS 2001.
	 */
	private static final Integer ANIO_INICIO_ISS_2001 = 2001;

	/**
	 * Año a partir del cual existe el tarifario ISS 2004.
	 */
	private static final Integer ANIO_INICIO_ISS_2004 = 2004;

	/**
	 * Administrador de tarifas.
	 */
	@Autowired
	private TarifaManager tarifaManager;

	/**
	 * Administrador de historial de salario SMLDV.
	 */
	@Autowired
	private HistorialSMLDVManager historialSMLDVManager;

	/**
	 * Administrador de historial de variación de IPC.
	 */
	@Autowired
	private HistorialVariacionIPCManager historialVariacionIPCManager;
	
	/**
	 * Calcula la tarifa a aplicar por copago a un ítem especificado.
	 * 
	 * @param item Ítem para el cual realizar el cálculo.
	 * @param sedeIpsId Sede de IPS.
	 * @param fechaSolicitud Fecha de solicitud.
	 * @return Cálculo realizado.
	 */
	public ResultadoCalculoCopago getTarifaCopago(final ItemSolicitado item, final Long sedeIpsId, final Date fechaSolicitud) {
		
		final List<ItemSolicitado> items = new ArrayList<ItemSolicitado>();
		items.add(item);
		
		final List<ResultadoCalculoCopago> resultados = this.getTarifasCopagos(items, sedeIpsId, fechaSolicitud);
		
		if (resultados.size() > 0) {
			return (resultados.iterator().next());
		}
		
		return (null);		
	}
	
	/**
	 * Calcula todas las tarifas a aplicar por copagos a los ítems especificados.
	 * 
	 * @param items Ítems para los cuales realizar el cálculo.
	 * @param sedeIpsId Sede de IPS.
	 * @param fechaSolicitud Fecha de solicitud.
	 * @return Cálculo realizado.
	 */
	public List<ResultadoCalculoCopago> getTarifasCopagos(final List<ItemSolicitado> items, final Long sedeIpsId, 
			final Date fechaSolicitud) {
		
		final List<ResultadoCalculoCopago> resultados = new ArrayList<ResultadoCalculoCopago>();
		
		/* Primero obtiene el valor del salario SLMV */
		final BigDecimal salarioSmldv = this.historialSMLDVManager.getValorSalarioEnFecha(fechaSolicitud);
		
		final Map<Integer, BigDecimal> variacionesIpc = this.historialVariacionIPCManager
												.getVariacionesDesdeAnio(DeterminadorTarifaCopago.ANIO_INICIO_VARIACION_IPC);
		
		final Integer anioFin = this.getAnioForFecha(fechaSolicitud);
				
		for (final ItemSolicitado item : items) {
			
			BigDecimal valorCopago = null;
			
			if (item.getTipoItem() == TipoItemSolicitado.INSUMO) {
				/* Conforme a lo hablado con Darío Camarro, los insumos no tienen valor alguno de copago */
				valorCopago = new BigDecimal(0);
			} else { 
			
				final TarifaProcedimientoMedicamentoInsumoDto dto = this.tarifaManager.getTarifa(item.getItemId(), 
						item.getTipoItem(), sedeIpsId, item.getEspecialidadId(), item.getServicioId(), fechaSolicitud);
				
				if (dto == null) {
					throw new IllegalArgumentException("No hay contrato para el procedimiento/medicamento.");
				}
				
				if (dto.isQuirurgico()) {
					valorCopago = dto.getValor();
				} else {
				
					switch (dto.getTipoTarifario()) {
						
						case NO_NORMATIVO:
							valorCopago = dto.getValor();
							break;
							
						case ISS_2000:
							valorCopago = dto.getUvr().multiply(this.getCoeficienteMultiplicadorIpc(variacionesIpc, 
												DeterminadorTarifaCopago.ANIO_INICIO_ISS_2000, anioFin))
													.setScale(DeterminadorTarifaCopago.CANTIDAD_DECIMALES, RoundingMode.HALF_EVEN);
							break;
						
						case ISS_2001:
							valorCopago = dto.getUvr().multiply(this.getCoeficienteMultiplicadorIpc(variacionesIpc, 
									DeterminadorTarifaCopago.ANIO_INICIO_ISS_2001, anioFin))
										.setScale(DeterminadorTarifaCopago.CANTIDAD_DECIMALES, RoundingMode.HALF_EVEN);
							break;
							
						case ISS_2004:
							/* Esta multiplicación solo aplica a ISS 2004 */
							valorCopago = dto.getUvr().multiply(DeterminadorTarifaCopago.CIEN_PORCIENTO)
											.setScale(DeterminadorTarifaCopago.CANTIDAD_DECIMALES, RoundingMode.HALF_EVEN);
							
							valorCopago = valorCopago.multiply(this.getCoeficienteMultiplicadorIpc(variacionesIpc, 
											DeterminadorTarifaCopago.ANIO_INICIO_ISS_2004, anioFin))
											.setScale(DeterminadorTarifaCopago.CANTIDAD_DECIMALES, RoundingMode.HALF_EVEN);
							break;
							
						case SOAT:
							valorCopago = salarioSmldv.multiply(dto.getFactor())
												.setScale(DeterminadorTarifaCopago.CANTIDAD_DECIMALES, RoundingMode.HALF_EVEN);
							break;
							
						default:
							throw new IllegalArgumentException("Tipo de tarifario desconocido.");
					}
				}
			}
			
			resultados.add(new ResultadoCalculoCopago(item, valorCopago));
		}
		
		return (resultados);
	}
	
	/**
	 * Obtiene el coeficiente multiplicador de variación de IPC.
	 * 
	 * @param variacionesIpc Mapa con variaciones anuales.
	 * @param anioInicio Año de inicio de la variación.
	 * @param anioFin Año de fin (inclusive) de la variación.
	 * @return Coeficiente multiplicador de variación de IPC determinado.
	 */
	private BigDecimal getCoeficienteMultiplicadorIpc(final Map<Integer, BigDecimal> variacionesIpc, final Integer anioInicio,
			final Integer anioFin) {
		
		if (anioFin < anioInicio) {
			return (new BigDecimal(0));
		}
		
		BigDecimal valorAcumulado = new BigDecimal(1);
		
		for (int anio = anioInicio; anio <= anioFin; anio++) {
			
			final BigDecimal monto = variacionesIpc.get(anio);
			
			if (monto == null) {
				throw new IllegalArgumentException("No está definida la variación de IPC del año: " + anio + ".");
			}
			
			BigDecimal montoAjustado = monto.divide(CIEN_PORCIENTO).setScale(DeterminadorTarifaCopago.CANTIDAD_DECIMALES, 
											RoundingMode.HALF_EVEN);
			
			montoAjustado = montoAjustado.add(new BigDecimal(1)).setScale(DeterminadorTarifaCopago.CANTIDAD_DECIMALES, 
					RoundingMode.HALF_EVEN);
			
			valorAcumulado = valorAcumulado.multiply(montoAjustado).setScale(DeterminadorTarifaCopago.CANTIDAD_DECIMALES, 
					RoundingMode.HALF_EVEN);
		}
		
		return (valorAcumulado);
	}
	
	/**
	 * Obtiene el año de una fecha.
	 * 
	 * @param fecha Fecha a analizar.
	 * @return Año obtenido.
	 */
	private Integer getAnioForFecha(final Date fecha) {
		
		final Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);

		return (cal.get(Calendar.YEAR));
	}
}
