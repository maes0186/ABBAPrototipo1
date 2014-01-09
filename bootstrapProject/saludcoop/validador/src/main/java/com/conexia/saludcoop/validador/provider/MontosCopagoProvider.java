package com.conexia.saludcoop.validador.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conexia.saludcoop.common.dto.MontosCopagoDto;
import com.conexia.saludcoop.common.entity.maestro.MontosCopagoContributivo;
import com.conexia.saludcoop.common.entity.maestro.MontosCopagoSubsidiado;
import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.common.repository.MontosCopagoContributivoRepository;
import com.conexia.saludcoop.common.repository.MontosCopagoSubsidiadoRepository;

/**
 * Único proveedor de montos de copago de la aplicación.
 * 
 * @author Sebastián Matienzo
 */
@Service
public final class MontosCopagoProvider {

	/**
	 * Montos de copago por año.
	 */
	private Map<AnioNivelRegimen, MontosCopagoDto> montos;
	
	/**
	 * Administrador de montos de copago contributivo.
	 */
	@Autowired
	private MontosCopagoContributivoRepository contributivoManager;
	
	/**
	 * Administrador de montos de copago subsidiado.
	 */
	@Autowired
	private MontosCopagoSubsidiadoRepository subsidiadoManager;

	/**
	 * Obtiene los montos de copago contributivo para un año específico.
	 * 
	 * @param anio Año para el cual buscar.
	 * @param nivel Nivel para el cual buscar.
	 * @return Montos de copago contributivo hallados.
	 */
	public MontosCopagoDto getMontosCopagoPorAnio(final Integer anio, final Integer nivel,
			final RegimenAfiliacion regimen) {
		
		final AnioNivelRegimen key = new AnioNivelRegimen(anio, nivel, regimen);
		
		if (this.montos != null && this.montos.containsKey(key)) {
			return (this.montos.get(key));
		}
		
		/* Un año solicitado no existe; asumiendo un cambio completo de información, recarga primero todo */
		this.initializeAllMontos();
		
		return (this.montos.get(key));
	}
	
	/**
	 * Inicializa todos los montos.
	 */
	private synchronized void initializeAllMontos() {
		
		if (montos == null) {
			this.montos = new HashMap<MontosCopagoProvider.AnioNivelRegimen, MontosCopagoDto>();
		} else {
			this.montos.clear();
		}
		
		for (final MontosCopagoContributivo contributivo : this.contributivoManager.findAll()) {
			this.montos.put(new AnioNivelRegimen(contributivo.getAnio(), 
					new Integer(contributivo.getNivelIBC().getCodigo()),
					RegimenAfiliacion.CONTRIBUTIVO), contributivo.toDto());
		}
		
		for (final MontosCopagoSubsidiado subsidiado : this.subsidiadoManager.findAll()) {
			this.montos.put(new AnioNivelRegimen(subsidiado.getAnio(), 
					new Integer(subsidiado.getNivelSisben().getCodigo()),
					RegimenAfiliacion.SUBSIDIADO), subsidiado.toDto());
		}
	}
	
	/**
	 * Clave de año y nivel.
	 * 
	 * @author Sebastián Matienzo
	 */
	private class AnioNivelRegimen {
		
		/**
		 * Año.
		 */
		private Integer anio;
		
		/**
		 * Nivel.
		 */
		private Integer nivel;
		
		/**
		 * Régimen de afiliación.
		 */
		private RegimenAfiliacion regimen;
		
		/**
		 * Constructor privado.
		 * 
		 * @param anio Año.
		 * @param nivel Nivel.
		 * @param regimen Régimen.
		 */
		public AnioNivelRegimen(final Integer anio, final Integer nivel, final RegimenAfiliacion regimen) {
			
			this.anio = anio;
			this.nivel = nivel;
			this.regimen = regimen;
		}
		
		/**
		 * Obtiene el año.
		 * 		
		 * @return Año.
		 */
		public Integer getAnio() {
			
			return (this.anio);
		}

		/**
		 * Obtiene el nivel.
		 * 
		 * @return Nivel.
		 */
		public Integer getNivel() {
			
			return (this.nivel);
		}
		
		/**
		 * Obtiene el régimen de afiliación.
		 * 
		 * @return Régimen de afiliación.
		 */
		private RegimenAfiliacion getRegimen() {
			
			return (this.regimen);
		}

		@Override
		public boolean equals(final Object otro) {
			
			if (!(otro instanceof AnioNivelRegimen)) {
				return (false);
			}
			
			final AnioNivelRegimen otroParseado = (AnioNivelRegimen) otro;
			
			return (this.getAnio().equals(otroParseado.getAnio())
					&& this.getNivel().equals(otroParseado.getNivel())
					&& this.getRegimen() == otroParseado.getRegimen());
		}
		
		@Override
		public int hashCode() {
			
			return (this.getAnio().hashCode() + this.getNivel().hashCode() + this.getRegimen().hashCode());
		}
	}
}
