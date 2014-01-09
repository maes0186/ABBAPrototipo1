package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.view.AfiliadoProgramaItemEximido;

/**
 * DAO de acceso a datos de ítems incluidos en programas del afiliado.
 * 
 * @author Sebastián Matienzo
 */
public interface AfiliadoProgramaItemEximidoRepository extends CrudRepository<AfiliadoProgramaItemEximido, Long> {
	
	/**
	 * Busca la relación entre el programa de un afiliado y un procedimiento.
	 * 
	 * @param afiliadoId Identificador del afiliado.
	 * @param procedimientoId Identificador del procedimiento.
	 * @return Relación hallada, o null si no existe.
	 */
	List<AfiliadoProgramaItemEximido> findByAfiliadoIdAndProcedimientoId(final Long afiliadoId, final Long procedimientoId);
	
	/**
	 * Busca la relación entre el programa de un afiliado y un medicamento.
	 * 
	 * @param afiliadoId Identificador del afiliado.
	 * @param medicamentoId Identificador del medicamento.
	 * @return Relación hallada, o null si no existe.
	 */
	List<AfiliadoProgramaItemEximido> findByAfiliadoIdAndMedicamentoId(final Long afiliadoId, final Long medicamentoId);
	
}
