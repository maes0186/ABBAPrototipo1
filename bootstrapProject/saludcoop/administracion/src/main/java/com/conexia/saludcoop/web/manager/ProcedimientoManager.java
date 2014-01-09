package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.repository.ProcedimientoRepository;
import com.conexia.saludcoop.common.repository.TipoPPMRepository;

@Component
public class ProcedimientoManager {
	@Autowired
	private ProcedimientoRepository procedimientoRepository;
	@Autowired
	private TipoPPMRepository tipoPPMRepository;
	
	public List<ProcedimientoDto> getProcedimientoByCodigoDescripcion(
			String codigo, String descripcion) {

		
		List<ProcedimientoDto> procedimientosDTO = new java.util.Vector<>();
		
		DescriptivoDto pos = tipoPPMRepository.findOne(1).toDto();
		DescriptivoDto nopos = tipoPPMRepository.findOne(2).toDto();
		DescriptivoDto ac = tipoPPMRepository.findOne(3).toDto();
		
		ProcedimientoDto proc = new ProcedimientoDto();
		proc.setId(1);
		proc.setCodigo("D002");
		proc.setDescripcion("CARCINOMA IN SITU DEL ESTOMAGO");
		proc.setTipoPPM(pos);
		procedimientosDTO.add(proc);
		proc = new ProcedimientoDto();
		proc.setId(2);
		proc.setCodigo("D131");
		proc.setDescripcion("TUMOR BENIGNO DEL ESTOMAGO");
		proc.setTipoPPM(pos);
		procedimientosDTO.add(proc);
		proc = new ProcedimientoDto();
		proc.setId(3);
		proc.setCodigo("D371");
		proc.setDescripcion("TUMOR DE COMPORTAMIENTO INCIERTO O DESCONOCIDO DEL ESTOMAGO");
		proc.setTipoPPM(nopos);
		procedimientosDTO.add(proc);
		proc = new ProcedimientoDto();
		proc.setId(4);
		proc.setCodigo("K310");
		proc.setDescripcion("DILATACION AGUDA DEL ESTOMAGO");
		proc.setTipoPPM(nopos);
		procedimientosDTO.add(proc);
		proc = new ProcedimientoDto();
		proc.setId(5);
		proc.setCodigo("K312");
		proc.setDescripcion("ESTRECHEZ O ESTENOSIS DEL ESTOMAGO EN RELOJ DE ARENA");
		proc.setTipoPPM(ac);
		procedimientosDTO.add(proc);
		proc = new ProcedimientoDto();
		proc.setId(6);
		proc.setCodigo("K316");
		proc.setDescripcion("FISTULA DEL ESTOMAGO Y DEL DUODENO");
		proc.setTipoPPM(ac);
		procedimientosDTO.add(proc);
		
		
		/* ASi DEBERIA SER
		List<Procedimiento> procedimientos = procedimientoRepository.findByCodigoAndDescripcion(codigo, descripcion);
		for (Procedimiento procedimiento : procedimientos) {
			
				procedimientosDTO.add(procedimiento.toDto());
				
		}*/
	
		return procedimientosDTO;

	}
	
	public List<ProcedimientoDto> getProcedimientosPosByCodigoDescripcion(
			String codigo, String descripcion) {

		
		List<ProcedimientoDto> procedimientosDTO = new java.util.Vector<>();
		
		DescriptivoDto pos = tipoPPMRepository.findOne(1).toDto();
		
		ProcedimientoDto proc = new ProcedimientoDto();
		proc.setId(1);
		proc.setCodigo("D002");
		proc.setDescripcion("CARCINOMA IN SITU DEL ESTOMAGO");
		proc.setTipoPPM(pos);
		procedimientosDTO.add(proc);
		proc = new ProcedimientoDto();
		proc.setId(2);
		proc.setCodigo("D131");
		proc.setDescripcion("TUMOR BENIGNO DEL ESTOMAGO");
		proc.setTipoPPM(pos);
		procedimientosDTO.add(proc);
		proc = new ProcedimientoDto();
		proc.setId(3);
		proc.setCodigo("D371");
		proc.setDescripcion("TUMOR DE COMPORTAMIENTO INCIERTO O DESCONOCIDO DEL ESTOMAGO");
		proc.setTipoPPM(pos);
		procedimientosDTO.add(proc);
		proc = new ProcedimientoDto();
		proc.setId(4);
		proc.setCodigo("K310");
		proc.setDescripcion("DILATACION AGUDA DEL ESTOMAGO");
		proc.setTipoPPM(pos);
		procedimientosDTO.add(proc);
		proc = new ProcedimientoDto();
		proc.setId(5);
		proc.setCodigo("K312");
		proc.setDescripcion("ESTRECHEZ O ESTENOSIS DEL ESTOMAGO EN RELOJ DE ARENA");
		proc.setTipoPPM(pos);
		procedimientosDTO.add(proc);
		proc = new ProcedimientoDto();
		proc.setId(6);
		proc.setCodigo("K316");
		proc.setDescripcion("FISTULA DEL ESTOMAGO Y DEL DUODENO");
		proc.setTipoPPM(pos);
		procedimientosDTO.add(proc);
		
		/* ASi DEBERIA SER
		List<Procedimiento> procedimientos = procedimientoRepository.findPOSByCodigoDescripcion(codigo, descripcion);
		for (Procedimiento procedimiento : procedimientos) {
			
				procedimientosDTO.add(procedimiento.toDto());
				
		}*/
	
		return procedimientosDTO;
	}
	

}
