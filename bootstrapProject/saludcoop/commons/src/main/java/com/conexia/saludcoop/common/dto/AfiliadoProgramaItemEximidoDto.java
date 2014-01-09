package com.conexia.saludcoop.common.dto;

/**
 * Indica un ítem eximido (procedimiento o medicamento) para un programa dado de un afiliado.
 * 
 * @author Sebastián Matienzo
 */
public class AfiliadoProgramaItemEximidoDto {

	/**
	 * Identificador del afiliado.
	 */
	private Long afiliadoId;

	/**
	 * Identificador del programa.
	 */
	private Long programaId;

	/**
	 * Identificador del procedimiento (si es tal el ítem).
	 */
	private Long procedimientoId;

	/**
	 * Identificador del medicamento (si es tal el ítem).
	 */
	private Long medicamentoId;
	
	/**
	 * Obtiene el ID del afiliado.
	 * 
	 * @return ID del afiliado.
	 */
	public Long getAfiliadoId() {
		
		return (this.afiliadoId);
	}

	/**
	 * Asigna el ID del afiliado al que corresponde la totalización.
	 * 
	 * @param afiliadoId ID del afiliado al que corresponde la totalización.
	 */
	public void setAfiliadoId(final Long afiliadoId) {
		
		this.afiliadoId = afiliadoId;
	}
	
	/**
	 * Obtiene el ID del programa.
	 * 
	 * @return ID del programa.
	 */
	public Long getProgramaId() {
		
		return (this.programaId);
	}

	/**
	 * Asigna el ID del programa.
	 * 
	 * @param programaId ID del programa.
	 */
	public void setProgramaId(final Long programaId) {
		
		this.programaId = programaId;
	}
	
	/**
	 * Obtiene el ID del procedimiento.
	 * 
	 * @return ID del procedimiento.
	 */
	public Long getProcedimientoId() {
		
		return (this.procedimientoId);
	}

	/**
	 * Asigna el ID del procedimiento.
	 * 
	 * @param procedimientoId ID del procedimiento.
	 */
	public void setProcedimientoId(final Long procedimientoId) {
		
		this.procedimientoId = procedimientoId;
	}
	
	/**
	 * Obtiene el ID del medicamento.
	 * 
	 * @return ID del medicamento.
	 */
	public Long getMedicamentoId() {
		
		return (this.medicamentoId);
	}

	/**
	 * Asigna el ID del medicamento.
	 * 
	 * @param medicamentoId ID del medicamento.
	 */
	public void setMedicamentoId(final Long medicamentoId) {
		
		this.medicamentoId = medicamentoId;
	}
}
