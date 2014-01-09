package com.conexia.saludcoop.common.entity.maestro;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.conexia.saludcoop.common.dto.EspecialidadDto;

@Entity
@Table(name = "especialidad", schema="maestros")
public class Especialidad extends Descriptivo {
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="especialidad")
	private Set<EspecialidadProcedimiento> procedimientos = new HashSet<EspecialidadProcedimiento>();
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="especialidad")
	private Set<EspecialidadMedicamento> medicamentos = new HashSet<EspecialidadMedicamento>();
		
	public EspecialidadDto toEspecialidadDto(){
		EspecialidadDto dto = new EspecialidadDto();
		dto.setId(this.getId());
		dto.setDescripcion(this.getDescripcion());
		dto.setCodigo(this.getCodigo());
		return dto;
	}
}
