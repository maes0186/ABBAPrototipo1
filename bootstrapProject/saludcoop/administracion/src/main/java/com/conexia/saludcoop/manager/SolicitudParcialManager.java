package com.conexia.saludcoop.manager;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;

import com.conexia.saludcoop.common.dto.transaccional.SolicitudParcialDto;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacionDocumento;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudParcial;
import com.conexia.saludcoop.common.repository.SolicitudParcialRepository;
import com.conexia.saludcoop.web.manager.TipoIdentificacionAfiliadoManager;

public class SolicitudParcialManager {
	@Autowired
	private TipoIdentificacionAfiliadoManager tipoIdenManager;

	@Autowired
	private SolicitudParcialRepository solParcialRepo;

	public List<SolicitudParcialDto> getSolicitudesParcialesByAfiliado(
			String nombre, String tipoDocumento, String numeroDocumento) {

		TipoIdentificacionDocumento tia = tipoIdenManager
				.getByCodigo(tipoDocumento);
		List<SolicitudParcial> solicitudesParciales=  solParcialRepo
				.findAllByNombreLikeTipoIdentificacionAfiliadoNumeroDocumentoOrderByFechaCreacion(
						nombre, tia, numeroDocumento);

		List<SolicitudParcialDto> solicitudesParcialesDto = new Vector<SolicitudParcialDto>();
		for (SolicitudParcial solicitudParcial : solicitudesParciales) {
			
			//TODO Descomentar cuando exista el metodo
			//solicitudesParcialesDto.add(solicitudParcial.toDto());
		}
		
		return solicitudesParcialesDto;
		
	}
}
