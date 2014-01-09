package com.conexia.saludcoop.validador.manager;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.entity.maestro.HistorialSMLDV;
import com.conexia.saludcoop.common.repository.HistorialSMLDVRepository;

/**
 * Administrador de historial de salario SMLDV.
 * 
 * @author Sebastián Matienzo
 */
@Component
public class HistorialSMLDVManager {
	
	/**
	 * Repositorio de histórico de SMLDV.
	 */
	@Autowired
	private HistorialSMLDVRepository historialSMLDVRepository;
	
	/**
	 * Obtiene el valor del salario SMLDV en un año dado.
	 * 
	 * @param anio Año para el cual buscar.
	 * @return Valor del salario hallado.
	 */
	public BigDecimal getValorSalario(final Integer anio) {
		
		final HistorialSMLDV historial = this.historialSMLDVRepository.findOneByAnio(anio);
		
		if (historial == null) {
			throw new IllegalArgumentException("No existe el valor del salario SMLDV para el año " + anio + ".");
		}
		
		return (new BigDecimal(historial.getValor()));
	}
	
	/**
	 * Obtiene el valor del salario SMLDV en una fecha dada.
	 * 
	 * @param fecha Fecha para la cual buscar.
	 * @return Valor del salario hallado.
	 */
	public BigDecimal getValorSalarioEnFecha(final Date fecha) {
		
		final Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);

		return (this.getValorSalario(cal.get(Calendar.YEAR)));
	}
}
