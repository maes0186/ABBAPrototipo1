package com.conexia.saludcoop.validador.determinador.dto;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;

/**
 * Ítem solicitado por un afiliado.
 * 
 * @author Sebastián Matienzo
 */
public class ItemSolicitado {

	/**
	 * Identificador del procedimiento/medicamento.
	 */
	private Long itemId;

	/**
	 * Tipo de ítem.
	 */
	private TipoItemSolicitado tipoItem;

	/**
	 * Tipo de pago ya determinado para el ítem.
	 */
	private TipoPagoRequerido tipoPagoRequerido;

	/**
	 * Identificador del diagnóstico asociado al ítem.
	 */
	private Long diagnosticoId;
	
	/**
	 * Identificador del servicio asociado al ítem.
	 */
	private Long servicioId;
	
	/**
	 * Identificador de la especialidad asociada al ítem.
	 */
	private Integer especialidadId;
	
	/**
	 * Constructor público.
	 * 
	 * @param itemId Identificador del procedimiento/medicamento.
	 * @param tipoItem Tipo de ítem.
	 * @param tipoPagoRequerido Tipo de pago ya determinado para el ítem.
	 * @param diagnosticoId Identificador del diagnóstico asociado al ítem.
	 * @param servicioId Identificador del servicio asociado al ítem.
	 * @param especialidadId Identificador de la especialidad asociada al ítem.
	 */
	public ItemSolicitado(final Long itemId, final TipoItemSolicitado tipoItem,
			final TipoPagoRequerido tipoPagoRequerido, final Long diagnosticoId, 
			final Long servicioId, final Integer especialidadId) {

		this.itemId = itemId;
		this.tipoItem = tipoItem;
		this.tipoPagoRequerido = tipoPagoRequerido;
		this.diagnosticoId = diagnosticoId;
		this.servicioId = servicioId;
		this.especialidadId = especialidadId;
	}
	
	/**
	 * Constructor público.
	 * 
	 * @param itemId Identificador del procedimiento/medicamento.
	 * @param tipoItem Tipo de ítem.
	 * @param tipoPagoRequerido Tipo de pago ya determinado para el ítem.
	 * @param diagnosticoId Identificador del diagnóstico asociado al ítem.
	 * @param servicioId Identificador del servicio asociado al ítem.
	 * @param especialidadId Identificador de la especialidad asociada al ítem.
	 */
	public ItemSolicitado(final SolicitudItemDto item) {

		this.itemId = item.getItemId();
		this.tipoItem = this.getTipoItemSolicitado(item);
		this.tipoPagoRequerido = item.getTipoPagoRequerido();
		this.diagnosticoId = item.getDiagnostico().getDiagnostico().getId();
		this.servicioId = item.getPreAutorizacion().getServicioId();
		this.especialidadId = item.getPreAutorizacion().getEspecialidadId();
	}
	
	/**
	 * Constructor público.
	 * 
	 * @param itemId Identificador del procedimiento/medicamento.
	 * @param tipoItem Tipo de ítem.
	 * @param tipoPagoRequerido Tipo de pago ya determinado para el ítem.
	 * @param diagnosticoId Identificador del diagnóstico asociado al ítem.
	 * @param servicioId Identificador del servicio asociado al ítem.
	 * @param especialidadId Identificador de la especialidad asociada al ítem.
	 */
	public ItemSolicitado(final SolicitudItem item) {

		if (item.isMedicamento() && item.getSolMedicamento().getMedicamento() != null) {
			this.itemId = item.getSolMedicamento().getMedicamento().getId();
		} else if (item.isProcedimiento() && item.getSolProcedimiento().getProcedimiento() != null) {
			this.itemId = item.getSolProcedimiento().getProcedimiento().getId();
		} else if (item.isInsumo() && item.getSolInsumo().getInsumo() != null) {
			this.itemId = item.getSolInsumo().getInsumo().getId();
		}

		this.tipoItem = this.getTipoItemSolicitado(item);
		this.tipoPagoRequerido = item.getAutorizacion().getGrupoAutorizacion().getTipoPago();
		this.diagnosticoId = item.getDiagnostico().getDiagnostico().getId();
		this.servicioId = (item.getAutorizacion().getServicio() != null) 
								? item.getAutorizacion().getServicio().getId() : null;
		this.especialidadId = (item.getAutorizacion().getEspecialidad() != null)
								? item.getAutorizacion().getEspecialidad().getId() : null;
	}

	/**
	 * Obtiene el identificador del procedimiento/medicamento.
	 * 
	 * @return Identificador del procedimiento/medicamento.
	 */
	public Long getItemId() {
		
		return (this.itemId);
	}

	/**
	 * Asigna el identificador del procedimiento/medicamento.
	 * 
	 * @param itemId Identificador del procedimiento/medicamento.
	 */
	public void setItemId(final Long itemId) {
		
		this.itemId = itemId;
	}

	/**
	 * Obtiene el tipo de ítem.
	 * 
	 * @return Tipo de ítem.
	 */
	public TipoItemSolicitado getTipoItem() {
		
		return (this.tipoItem);
	}

	/**
	 * Asigna el tipo de ítem.
	 * 
	 * @param tipoItem Tipo de ítem.
	 */
	public void setTipoItem(final TipoItemSolicitado tipoItem) {
		
		this.tipoItem = tipoItem;
	}

	/**
	 * Obtiene el tipo de pago ya determinado para el ítem.
	 * 
	 * @return Tipo de pago ya determinado para el ítem.
	 */
	public TipoPagoRequerido getTipoPagoRequerido() {
		
		return (this.tipoPagoRequerido);
	}

	/**
	 * Asigna el tipo de pago ya determinado para el ítem.
	 * 
	 * @param tipoPagoRequerido Tipo de pago ya determinado para el ítem.
	 */
	public void setTipoPagoRequerido(final TipoPagoRequerido tipoPagoRequerido) {
		
		this.tipoPagoRequerido = tipoPagoRequerido;
	}

	/**
	 * Obtiene el identificador del diagnóstico asociado al ítem.
	 * 
	 * @return Identificador del diagnóstico asociado al ítem.
	 */
	public Long getDiagnosticoId() {
		
		return (this.diagnosticoId);
	}

	/**
	 * Asigna el identificador del diagnóstico asociado al ítem.
	 * 
	 * @param diagnosticoId Identificador del diagnóstico asociado al ítem.
	 */
	public void setDiagnosticoId(final Long diagnosticoId) {
		
		this.diagnosticoId = diagnosticoId;
	}

	/**
	 * Obtiene el identificador del servicio asociado al ítem.
	 * 
	 * @return Identificador del servicio asociado al ítem.
	 */
	public Long getServicioId() {
		
		return (this.servicioId);
	}

	/**
	 * Asigna el identificador del servicio asociado al ítem.
	 * 
	 * @param servicioId Identificador del servicio asociado al ítem.
	 */
	public void setServicioId(final Long servicioId) {
		
		this.servicioId = servicioId;
	}

	/**
	 * Obtiene el identificador de la especialidad asociada al ítem.
	 * 
	 * @return Identificador de la especialidad asociada al ítem.
	 */
	public Integer getEspecialidadId() {
		
		return (this.especialidadId);
	}

	/**
	 * Asigna el identificador de la especialidad asociada al ítem.
	 * 
	 * @param especialidadId Identificador de la especialidad asociada al ítem.
	 */
	public void setEspecialidadId(final Integer especialidadId) {
		
		this.especialidadId = especialidadId;
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
	 * Obtiene el tipo de ítem solicitado correspondiente a un ítem de solicitud.
	 * 
	 * @param item Ítem de solicitud.
	 * @return Tipo de ítem solicitado.
	 */
	private TipoItemSolicitado getTipoItemSolicitado(final SolicitudItem item) {
		
		return ((item.isProcedimiento()) ? TipoItemSolicitado.PROCEDIMIENTO 
					: ((item.isMedicamento()) ? TipoItemSolicitado.MEDICAMENTO : TipoItemSolicitado.INSUMO));
	}
}
