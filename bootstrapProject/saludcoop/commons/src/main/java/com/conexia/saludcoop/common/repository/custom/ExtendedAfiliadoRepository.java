package com.conexia.saludcoop.common.repository.custom;

import java.util.List;

import com.conexia.saludcoop.common.entity.maestro.Programa;

/**
 * Interfaz para lógica personalizada de repositorio del afiliado.
 * 
 * @author Sebastián Matienzo
 */
public interface ExtendedAfiliadoRepository {
	
	/**
	 * Verifica si un afiliado tiene al menos una transacción en un año dado.
	 * 
	 * @param afiliadoId Identificador del afiliado.
	 * @param anio Año a contemplar.
	 * @return True si tiene al menos una transacción en el año indicado; caso contrario, False.
	 */
	public boolean tieneTransaccionesEnAnio(final Long afiliadoId, final Integer anio);
	
	/**
	 * Verifica si un afiliado de un régimen subsidiado pertenece a un grupo poblacional 
	 * eximido de copagos.
	 * 
	 * @param afiliadoId Identificador del afiliado.
	 * @return True si pertenece a un grupo eximido; caso contrario, False.
	 */
	public boolean esGrupoPoblacionalEximidoSubsidiado(final Long afiliadoId);
	
	public List<Programa> getProgrmasByIdAfiliado(Long afiliado);
	
}
