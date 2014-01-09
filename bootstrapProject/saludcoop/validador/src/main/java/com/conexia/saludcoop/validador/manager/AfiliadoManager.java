package com.conexia.saludcoop.validador.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.entity.view.AfiliadoProgramaItemEximido;
import com.conexia.saludcoop.common.repository.AfiliadoProgramaItemEximidoRepository;
import com.conexia.saludcoop.common.repository.AfiliadoRepository;

/**
 * Administrador de afiliados.
 * 
 * @author Sebastián Matienzo
 */
@Component
public class AfiliadoManager {
	
	@Autowired
	private AfiliadoRepository afiliadoRepository;
	
	@Autowired
	private AfiliadoProgramaItemEximidoRepository afiliadoProgramaItemEximidoRepository;
	
	/**
	 * Verifica si un afiliado tuvo al menos una solicitud en un año dado.
	 * 
	 * @param id Identificador del afiliado.
	 * @param anio Año a verificar.
	 * @return True si tuvo al menos una; caso contrario, False.
	 */
	public boolean tieneTransaccionesEnAnio(final Long afiliadoId, final Integer anio) {
		
		return (this.afiliadoRepository.tieneTransaccionesEnAnio(afiliadoId, anio));
	}
	
	/**
	 * Verifica si un afiliado de un régimen subsidiado pertenece a un grupo poblacional 
	 * eximido de copagos.
	 * 
	 * @param afiliadoId Identificador del afiliado.
	 * @return True si pertenece a un grupo eximido; caso contrario, False.
	 */
	public boolean esGrupoPoblacionalEximidoSubsidiado(final Long afiliadoId) {
		
		return (this.afiliadoRepository.esGrupoPoblacionalEximidoSubsidiado(afiliadoId));
	}
	
	/**
	 * Verifica si un procedimiento está exento de pago por un programa vinculado al afiliado.
	 * 
	 * @param afiliadoId Identificador del afiliado.
	 * @param procedimientoId Identificador del procedimiento.
	 * @return True si pertenece a un grupo eximido; caso contrario, False.
	 */
	public boolean esProcedimientoExentoProgramaAfiliado(final Long afiliadoId, final Long procedimientoId) {
		List<AfiliadoProgramaItemEximido> list =
				this.afiliadoProgramaItemEximidoRepository.findByAfiliadoIdAndProcedimientoId(
						afiliadoId, procedimientoId);
		return (list != null && !list.isEmpty());
	}
	
	/**
	 * Verifica si un medicamento está exento de pago por un programa vinculado al afiliado.
	 * 
	 * @param afiliadoId Identificador del afiliado.
	 * @param medicamentoId Identificador del medicamento.
	 * @return True si pertenece a un grupo eximido; caso contrario, False.
	 */
	public boolean esMedicamentoExentoProgramaAfiliado(final Long afiliadoId, final Long medicamentoId) {
		List<AfiliadoProgramaItemEximido> list =
				this.afiliadoProgramaItemEximidoRepository.findByAfiliadoIdAndMedicamentoId(
						afiliadoId, medicamentoId);
		return (list != null && !list.isEmpty());
	}
}
