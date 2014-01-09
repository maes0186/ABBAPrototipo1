package com.conexia.saludcoop.common.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.AfiliadoProgramaItemEximidoDto;

/**
 * Indica un ítem eximido (procedimiento o medicamento) para un programa dado de un afiliado.
 * 
 * @author Sebastián Matienzo
 */
@Entity
@Table(name = "afiliado_programa_item_eximido_view", schema = "vista")
@Mappeable(mappedTo = AfiliadoProgramaItemEximidoDto.class)
public class AfiliadoProgramaItemEximido implements Identifiable<Long> {

	/**
	 * Identificador de la entidad.
	 */
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * Identificador del afiliado.
	 */
	@Column(name = "afiliado_id", nullable = false)
	private Long afiliadoId;

	/**
	 * Identificador del programa.
	 */
	@Column(name = "programa_id", nullable = false)
	private Long programaId;

	/**
	 * Identificador del procedimiento (si es tal el ítem).
	 */
	@Column(name = "procedimiento_id", nullable = true)
	private Long procedimientoId;

	/**
	 * Identificador del medicamento (si es tal el ítem).
	 */
	@Column(name = "medicamento_id", nullable = true)
	private Long medicamentoId;

	@Override
	public Long getId() {
		
		return (this.id);
	}
	
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

	/**
	 * Obtiene el DTO que representa a esta entidad.
	 * 
	 * @return DTO que representa a esta entidad.
	 */
	public AfiliadoProgramaItemEximidoDto toDto() {
		
		AfiliadoProgramaItemEximidoDto dto = new AfiliadoProgramaItemEximidoDto();
		dto.setAfiliadoId(this.afiliadoId);
		dto.setProgramaId(this.programaId);
		dto.setProcedimientoId(this.procedimientoId);
		dto.setMedicamentoId(this.medicamentoId);

		return (dto);
	}
}
