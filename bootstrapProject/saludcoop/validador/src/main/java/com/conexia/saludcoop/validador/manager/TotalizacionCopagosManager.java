package com.conexia.saludcoop.validador.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.TotalizacionCopagoAfiliadoDiagnosticoDto;
import com.conexia.saludcoop.common.dto.TotalizacionCopagoAfiliadoDto;
import com.conexia.saludcoop.common.entity.auxiliar.TotalizacionCopagoAfiliado;
import com.conexia.saludcoop.common.entity.auxiliar.TotalizacionCopagoAfiliadoDiagnostico;
import com.conexia.saludcoop.common.repository.TotalizacionCopagoAfiliadoDiagnosticoRepository;
import com.conexia.saludcoop.common.repository.TotalizacionCopagoAfiliadoRepository;

/**
 * Administrador de totalizaciones de copagos.
 * 
 * @author Sebastián Matienzo
 */
@Service
@Transactional
public class TotalizacionCopagosManager {

	/**
	 * Repositorio de totalización general.
	 */
	@Autowired
	private TotalizacionCopagoAfiliadoRepository totalizacionGeneralRepository;
	
	/**
	 * Repositorio de totalización discriminada por diagnóstico.
	 */
	@Autowired
	private TotalizacionCopagoAfiliadoDiagnosticoRepository totalizacionPorDiagnosticoRepository;
	
	/**
	 * Obtiene la totalización de copago del afiliado en un año específico.
	 * 
	 * @param anio Año de totalización.
	 * @param afiliadoId Identificación del afiliado.
	 * @return Totalización hallada.
	 */
	public TotalizacionCopagoAfiliadoDto getTotalizacionAnualAfiliado(final Integer anio, final Long afiliadoId) {
		
		final TotalizacionCopagoAfiliado entity = this.totalizacionGeneralRepository.findByAnioAndAfiliadoId(anio, afiliadoId);
		
		if (entity == null) {
			return (null);
		}
		
		return (entity.toDto());
	}
	
	/**
	 * Obtiene la totalización de copago del afiliado, discriminada por diagnóstico, en un año específico.
	 * 
	 * @param anio Año de totalización.
	 * @param afiliadoId Identificación del afiliado.
	 * @param diagnosticoId Identificación del diagnóstico.
	 * @return Totalización hallada.
	 */
	public TotalizacionCopagoAfiliadoDiagnosticoDto getTotalizacionAnualAfiliadoPorDiagnostico(final Integer anio, 
			final Long afiliadoId, final Long diagnosticoId) {
		
		final TotalizacionCopagoAfiliadoDiagnostico entity = this.totalizacionPorDiagnosticoRepository
										.findByAnioAndAfiliadoIdAndDiagnosticoId(anio, afiliadoId, diagnosticoId);
		
		if (entity == null) {
			return (null);
		}
		
		return (entity.toDto());
	}
	
	/**
	 * Guarda la totalización de copago del afiliado en un año específico.
	 * 
	 * @param dto DTO que representa la totalización a guardar.
	 */
	public void saveTotalizacionAnualAfiliado(final TotalizacionCopagoAfiliadoDto dto) {
		
		if (dto == null) {
			throw new IllegalArgumentException("El DTO a almacenar es un parámetro requerido.");
		}
		
		if (dto.getAfiliadoId() == null || dto.getAnio() == null || dto.getTotalCopagos() == null) {
			throw new IllegalArgumentException("El afiliado, año, y total de copagos, son datos requeridos.");
		}
	
		TotalizacionCopagoAfiliado entity = this.totalizacionGeneralRepository.findByAnioAndAfiliadoId(dto.getAnio(), dto.getAfiliadoId());
		
		if (entity == null) {
			entity = new TotalizacionCopagoAfiliado();
			entity.setAfiliadoId(dto.getAfiliadoId());
			entity.setAnio(dto.getAnio());
		}
		
		entity.setTotalCopagos(dto.getTotalCopagos());
		
		this.totalizacionGeneralRepository.save(entity);
	}
	
	/**
	 * Guarda la totalización de copago del afiliado, discriminada por diagnóstico, en un año específico.
	 * 
	 * @param dto DTO que representa la totalización a guardar.
	 */
	public void saveTotalizacionAnualAfiliadoPorDiagnostico(final TotalizacionCopagoAfiliadoDiagnosticoDto dto) {
		
		if (dto == null) {
			throw new IllegalArgumentException("El DTO a almacenar es un parámetro requerido.");
		}
		
		if (dto.getAfiliadoId() == null || dto.getAnio() == null 
				|| dto.getDiagnosticoId() == null || dto.getTotalCopagos() == null) {
			throw new IllegalArgumentException("El afiliado, año, diagnóstico, y total de copagos, son datos requeridos.");
		}
	
		TotalizacionCopagoAfiliadoDiagnostico entity = this.totalizacionPorDiagnosticoRepository
								.findByAnioAndAfiliadoIdAndDiagnosticoId(dto.getAnio(), dto.getAfiliadoId(), dto.getDiagnosticoId());
		
		if (entity == null) {
			entity = new TotalizacionCopagoAfiliadoDiagnostico();
			entity.setAfiliadoId(dto.getAfiliadoId());
			entity.setAnio(dto.getAnio());
			entity.setDiagnosticoId(dto.getDiagnosticoId());
		}
		
		entity.setTotalCopagos(dto.getTotalCopagos());
		
		this.totalizacionPorDiagnosticoRepository.save(entity);
	}
}
