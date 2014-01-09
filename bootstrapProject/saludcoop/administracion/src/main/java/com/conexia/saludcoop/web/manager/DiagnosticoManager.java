package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.DiagnosticoDto;
import com.conexia.saludcoop.common.repository.DiagnosticoRepository;

@Component
public class DiagnosticoManager {
	@Autowired
	private DiagnosticoRepository diagnosticoRepository;
	
	public List<DiagnosticoDto> getDiagnosticosByCodigoDescripcion(
			String codigo, String descripcion) {
		
		List<DiagnosticoDto> diagnosticosDTO = new java.util.Vector<>();
		
		DiagnosticoDto diag = new DiagnosticoDto();
		diag.setId(1);
		diag.setCodigo("D002");
		diag.setDescripcion("CARCINOMA IN SITU DEL ESTOMAGO");
		diagnosticosDTO.add(diag);
		diag = new DiagnosticoDto();
		diag.setId(2);
		diag.setCodigo("D131");
		diag.setDescripcion("TUMOR BENIGNO DEL ESTOMAGO");
		diagnosticosDTO.add(diag);
		diag = new DiagnosticoDto();
		diag.setId(3);
		diag.setCodigo("D371");
		diag.setDescripcion("TUMOR DE COMPORTAMIENTO INCIERTO O DESCONOCIDO DEL ESTOMAGO");
		diagnosticosDTO.add(diag);
		diag = new DiagnosticoDto();
		diag.setId(4);
		diag.setCodigo("K310");
		diag.setDescripcion("DILATACION AGUDA DEL ESTOMAGO");
		diagnosticosDTO.add(diag);
		diag = new DiagnosticoDto();
		diag.setId(5);
		diag.setCodigo("K312");
		diag.setDescripcion("ESTRECHEZ O ESTENOSIS DEL ESTOMAGO EN RELOJ DE ARENA");
		diagnosticosDTO.add(diag);
		diag = new DiagnosticoDto();
		diag.setId(6);
		diag.setCodigo("K316");
		diag.setDescripcion("FISTULA DEL ESTOMAGO Y DEL DUODENO");
		diagnosticosDTO.add(diag);
		
		
		/* ASi DEBERIA SER
		List<Diagnostico> diagnosticos = diagnosticoRepository.findByCodigoAndDescripcion(codigo, descripcion);
		for (Diagnostico diagnostico : diagnosticos) {
			
				diagnosticosDTO.add(diagnostico.toDto());
				
		}*/
	
		return diagnosticosDTO;
	}
}
