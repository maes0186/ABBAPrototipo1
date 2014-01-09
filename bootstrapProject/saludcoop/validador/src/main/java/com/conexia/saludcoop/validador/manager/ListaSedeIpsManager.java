package com.conexia.saludcoop.validador.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.UbicacionSedeIpsEfectorProcedimientoDto;
import com.conexia.saludcoop.common.dto.UbicacionSedeIpsProveedorInsumoDto;
import com.conexia.saludcoop.common.dto.UbicacionSedeIpsProveedorMedicamentoDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.RedireccionInsumo;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.RedireccionMedicamento;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.RedireccionProcedimiento;

@Component
@Transactional
public class ListaSedeIpsManager {
	
	/**
	 * Cantidad máxima de sedes de IPS esperada.
	 */
	private static final int CANTIDAD_MAXIMA_SEDES_IPS_ESPERADA = 10;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ListaSedeIpsManager.class);

	/**
	 * Lógica para efectuar la redirección de un procedimiento.
	 */
	@Autowired
	private RedireccionProcedimiento redireccionProcedimiento;
	
	/**
	 * Lógica para efectuar la redirección de un procedimiento.
	 */
	@Autowired
	private RedireccionMedicamento redireccionMedicamento;
	
	/**
     * Lógica para efectuar la redirección de un insumos.
     */
    @Autowired
    private RedireccionInsumo redireccionInsumo;
	
	/**
	 * Administrador de sedes de Ips.
	 */
	@Autowired
	private SedeIpsManager sedeIpsManager;

	public Set<SedeIpsDto> obtenerListaIps(SolicitudItem solicitudItem) throws Throwable {
		
		try {
			final Set<SedeIpsDto> sedesIpsDto = new HashSet<SedeIpsDto>();
		
			final Afiliado afiliado = solicitudItem.getSolicitud().getAfiliado();
			final Long sedeIpsId = (solicitudItem.getAutorizacion().getSedeIpsEfectora() != null ? solicitudItem.getAutorizacion().getSedeIpsEfectora().getId() : null);
			
			if (solicitudItem.getSolMedicamento() != null) {

				final Medicamento medicamento = solicitudItem.getSolMedicamento().getMedicamento();

				final List<UbicacionSedeIpsProveedorMedicamentoDto> ubicaciones = this.redireccionMedicamento.redireccionar(medicamento,
					afiliado, sedeIpsId, ListaSedeIpsManager.CANTIDAD_MAXIMA_SEDES_IPS_ESPERADA);

				for (final UbicacionSedeIpsProveedorMedicamentoDto ubicacion : ubicaciones) {
					sedesIpsDto.add(this.sedeIpsManager.getSedeIps(ubicacion.getSedeIpsId()));
				}
				
			} else if(solicitudItem.getSolInsumo() != null) { 
			    
			    final Insumo insumo = solicitudItem.getSolInsumo().getInsumo();

                final List<UbicacionSedeIpsProveedorInsumoDto> ubicaciones = this.redireccionInsumo.redireccionar(insumo,
                    afiliado, sedeIpsId, ListaSedeIpsManager.CANTIDAD_MAXIMA_SEDES_IPS_ESPERADA);

                for (final UbicacionSedeIpsProveedorInsumoDto ubicacion : ubicaciones) {
                    sedesIpsDto.add(this.sedeIpsManager.getSedeIps(ubicacion.getSedeIpsId()));
                }
			
			}else {
			
				final Procedimiento procedimiento = solicitudItem.getSolProcedimiento().getProcedimiento();
	
				final List<UbicacionSedeIpsEfectorProcedimientoDto> ubicaciones = this.redireccionProcedimiento.redireccionar(
						procedimiento, afiliado, sedeIpsId, ListaSedeIpsManager.CANTIDAD_MAXIMA_SEDES_IPS_ESPERADA);
					
				for (final UbicacionSedeIpsEfectorProcedimientoDto ubicacion : ubicaciones) {
					sedesIpsDto.add(this.sedeIpsManager.getSedeIps(ubicacion.getSedeIpsId()));
				}
			}

			return (sedesIpsDto);
		
		} catch (final Throwable e) {
			ListaSedeIpsManager.LOGGER.error("Error calculando listas de ips", e);
			
			throw (e);
		}
	}
}
