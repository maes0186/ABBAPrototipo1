package com.conexia.saludcoop.validador.businessRules.direccionamiento;

import java.util.List;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorMedicamento;

/**
 * Interfaz de estrategia de redireccionamiento de medicamento.
 * 
 * @author Emmanuel Barbín
 * @author Sebastián Matienzo
 */
public interface IEstrategiaRedireccionMedicamento {
	
	/**
	 * Obtiene las ubicaciones de posibles redirecciones.
	 * 
	 * @param afiliado Afiliado involucrado.
	 * @param medicamento Medicamento a redireccionar.
	 * @param idSedeIpsExcluir Identificador de la sede de Ips a excluir (null si no hay exclusión).
	 * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
	 * @return Lista de ubicaciones de sedes de IPS a donde se puede redireccionar un medicamento.
	 */
	List<UbicacionSedeIpsProveedorMedicamento> getUbicacionesConformeEstrategia(final Afiliado afiliado, 
			final Medicamento medicamento, final Long idSedeIpsExcluir,	final int numeroPagina);
	
}
