package com.conexia.saludcoop.web.manager;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.DiagnosticoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.maestro.TipoPPM;
import com.conexia.saludcoop.common.repository.ProcedimientoRepository;
import com.conexia.saludcoop.common.repository.TipoPPMRepository;

@Component
public class ProcedimientoManager extends GeneralManager{
	@Autowired
	private ProcedimientoRepository procedimientoRepository;
	@Autowired
	private TipoPPMRepository tipoPPMRepository;
	
	public List<ProcedimientoDto> getProcedimientoByCodigoDescripcion(String codigo, String descripcion) {
		
		List<Procedimiento> procedimientos = procedimientoRepository.findDistinctByCodigoLikeAndDescripcionLikeAllIgnoreCase(getLikeParameter(codigo), getLikeParameter(descripcion));
		List<ProcedimientoDto> procedimientosDTO = new Vector<>();
		for (Procedimiento procedimiento : procedimientos) {
			
				procedimientosDTO.add(procedimiento.toDto());
				
		}
	
		return procedimientosDTO;

	}
	
	public List<ProcedimientoDto> getProcedimientosPosByCodigoDescripcion(String codigo, String descripcion) {

		List<Procedimiento> procedimientos = procedimientoRepository.findByTipoIdAndCodigoLikeIgnoreCaseAndDescripcionLikeIgnoreCase(TipoPPM.POS_ID, getLikeParameter(codigo), getLikeParameter(descripcion));
		List<ProcedimientoDto> procedimientosDTO = new Vector<>();
		for (Procedimiento procedimiento : procedimientos) {
			procedimientosDTO.add(procedimiento.toDto());
		}
	
		return procedimientosDTO;
	}

	public ProcedimientoDto getProcedimientoByCodigo(String codigo) {
		
		return procedimientoRepository.findOneByCodigoIgnoreCase(codigo).toDto();
				
	}
	
	public ProcedimientoDto getProcedimientoByCodigoAndDescripcion(String codigo, String descripcion) {
		
		return procedimientoRepository.findOneByCodigoAndDescripcionAllIgnoreCase(codigo, descripcion).toDto();
				
	}
	public ProcedimientoDto findById(Long id) {

		return procedimientoRepository.findOne(id).toDto();
	}

}
