package com.conexia.saludcoop.validador.businessRules.direccionamiento;

import java.util.List;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsEfectorProcedimiento;

/**
 * Interfaz de estrategia de redireccionamiento de procedimientos.
 * 
 * @author Emmanuel Barbín
 * @author Sebastián Matienzo
 */
public interface IEstrategiaRedireccionProcedimiento {
	
	/**
	 * Obtiene las ubicaciones de posibles redirecciones conforme a la estrategia especificada..
	 * 
	 * @param afiliado Afiliado involucrado.
	 * @param procedimiento Procedimiento a redireccionar.
	 * @param codigoTipoMinuta Código de tipo de minuta (null si ninguno).
	 * @param idSedeIpsExcluir Identificador de la sede de Ips a excluir (null si no hay exclusión).
	 * @param debeOrdenarPorValorAjustado Indica si debe ordenar por el valor ajustado, o por el valor original.
	 * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
	 * @return Lista de ubicaciones de sedes de IPS a donde se puede redireccionar un procedimiento.
	 */
	List<UbicacionSedeIpsEfectorProcedimiento> getUbicacionesConformeEstrategia(final Afiliado afiliado, 
			final Procedimiento procedimiento, final String codigoTipoMinuta, final Long idSedeIpsExcluir,
			final boolean debeOrdenarPorValorAjustado, final int numeroPagina);
	
}
