package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.repository.MedicamentoRepository;
import com.conexia.saludcoop.common.repository.TipoPPMRepository;

@Component
public class MedicamentoManager {
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	@Autowired
	private TipoPPMRepository tipoPPMRepository;
	

	public List<MedicamentoDto> getMedicamentosByCodigoDescripcion(
			String codigo, String descripcion) {

		List<MedicamentoDto> medicamentosDTO = new java.util.Vector<>();
		DescriptivoDto pos = tipoPPMRepository.findOne(1).toDto();
		DescriptivoDto nopos = tipoPPMRepository.findOne(2).toDto();
		DescriptivoDto ac = tipoPPMRepository.findOne(3).toDto();
		
		MedicamentoDto med = new MedicamentoDto();
		med.setId(1);
		med.setCodigo("D002");
		med.setDescripcion("CARCINOMA IN SITU DEL ESTOMAGO");
		med.setTipoPPM(pos);
		medicamentosDTO.add(med);
		med = new MedicamentoDto();
		med.setId(2);
		med.setCodigo("D131");
		med.setDescripcion("TUMOR BENIGNO DEL ESTOMAGO");
		med.setTipoPPM(pos);
		medicamentosDTO.add(med);
		med = new MedicamentoDto();
		med.setId(3);
		med.setCodigo("D371");
		med.setDescripcion("TUMOR DE COMPORTAMIENTO INCIERTO O DESCONOCIDO DEL ESTOMAGO");
		med.setTipoPPM(nopos);
		medicamentosDTO.add(med);
		med = new MedicamentoDto();
		med.setId(4);
		med.setCodigo("K310");
		med.setDescripcion("DILATACION AGUDA DEL ESTOMAGO");
		med.setTipoPPM(nopos);
		medicamentosDTO.add(med);
		med = new MedicamentoDto();
		med.setId(5);
		med.setCodigo("K312");
		med.setDescripcion("ESTRECHEZ O ESTENOSIS DEL ESTOMAGO EN RELOJ DE ARENA");
		med.setTipoPPM(ac);
		medicamentosDTO.add(med);
		med = new MedicamentoDto();
		med.setId(6);
		med.setCodigo("K316");
		med.setDescripcion("FISTULA DEL ESTOMAGO Y DEL DUODENO");
		med.setTipoPPM(ac);
		medicamentosDTO.add(med);
		
		
		/* ASi DEBERIA SER
		List<Medicamento> medicamentos = medicamentoRepository.findByCodigoAndDescripcion(codigo, descripcion);
		for (Medicamento medicamento : medicamentos) {
			
				medicamentosDTO.add(medicamento.toDto());
				
		}*/
	
		return medicamentosDTO;
	}

	public List<MedicamentoDto> getMedicamentosPOSByCodigoDescripcion(
			String codigo, String descripcion) {


		List<MedicamentoDto> medicamentosDTO = new java.util.Vector<>();
		DescriptivoDto pos = tipoPPMRepository.findOne(1).toDto();
		MedicamentoDto med = new MedicamentoDto();
		med.setId(1);
		med.setCodigo("D002");
		med.setDescripcion("CARCINOMA IN SITU DEL ESTOMAGO");
		med.setTipoPPM(pos);
		medicamentosDTO.add(med);
		med = new MedicamentoDto();
		med.setId(2);
		med.setCodigo("D131");
		med.setDescripcion("TUMOR BENIGNO DEL ESTOMAGO");
		med.setTipoPPM(pos);
		medicamentosDTO.add(med);
		med = new MedicamentoDto();
		med.setId(3);
		med.setCodigo("D371");
		med.setDescripcion("TUMOR DE COMPORTAMIENTO INCIERTO O DESCONOCIDO DEL ESTOMAGO");
		med.setTipoPPM(pos);
		medicamentosDTO.add(med);
		med = new MedicamentoDto();
		med.setId(4);
		med.setCodigo("K310");
		med.setDescripcion("DILATACION AGUDA DEL ESTOMAGO");
		med.setTipoPPM(pos);
		medicamentosDTO.add(med);
		med = new MedicamentoDto();
		med.setId(5);
		med.setCodigo("K312");
		med.setDescripcion("ESTRECHEZ O ESTENOSIS DEL ESTOMAGO EN RELOJ DE ARENA");
		med.setTipoPPM(pos);
		medicamentosDTO.add(med);
		med = new MedicamentoDto();
		med.setId(6);
		med.setCodigo("K316");
		med.setDescripcion("FISTULA DEL ESTOMAGO Y DEL DUODENO");
		med.setTipoPPM(pos);
		medicamentosDTO.add(med);
		
		
		/* ASi DEBERIA SER
		List<Medicamento> medicamentos = medicamentoRepository.findPOSByCodigoDescripcion(codigo, descripcion);
		for (Medicamento medicamento : medicamentos) {
			
				medicamentosDTO.add(medicamento.toDto());
				
		}*/
	
		return medicamentosDTO;
	}

}
