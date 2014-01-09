package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.DiagnosticoDto;
import com.conexia.saludcoop.common.entity.maestro.Diagnostico;
import com.conexia.saludcoop.common.repository.DiagnosticoRepository;

@Component
public class DiagnosticoManager {
	@Autowired
	private DiagnosticoRepository diagnosticoRepository;

	public DiagnosticoDto getDiagnosticoByCodigo(String codigo) {
		return diagnosticoRepository.findOneByCodigoIgnoreCase(codigo).toDto();
	}

	public List<DiagnosticoDto> getDiagnosticosByCodigoDescripcion(String codigo, String descripcion) {

		List<DiagnosticoDto> diagnosticosDTO = new Vector<>();

		if (codigo == null) {
			codigo = "";
		}
		codigo = "%" + codigo.replace(" ", "%") + "%";

		if (descripcion == null) {
			descripcion = "";
		}
		descripcion = "%" + descripcion.replace(" ", "%") + "%";
		Page<Diagnostico> diagnosticos = diagnosticoRepository.findByCodigoLikeAndDescripcionLikeAllIgnoreCase(codigo, descripcion, null);
		for (Diagnostico diagnostico : diagnosticos) {
			diagnosticosDTO.add(diagnostico.toDto());
		}

		return diagnosticosDTO;
	}

	public List<DiagnosticoDto> findAll() {
		List<DiagnosticoDto> diagnosticosDTO = new ArrayList<>();

		for (Diagnostico diagnostico : diagnosticoRepository.findAll()) {
			diagnosticosDTO.add(diagnostico.toDto());
		}
		return diagnosticosDTO;
	}

	public DiagnosticoDto findById(Long id) {

		return diagnosticoRepository.findOne(id).toDto();
	}

}
