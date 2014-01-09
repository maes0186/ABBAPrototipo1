package com.conexia.saludcoop.common.entity.maestro;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.DescriptivoDto;

@Entity
@Table(name = "programa", schema = "maestros")
@Mappeable(mappedTo=DescriptivoDto.class)
public class Programa extends Descriptivo{
	public static final Integer ARTRITIS_REMATOIDEA = 13;
	public static final Integer HEMOFILIA = 12;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name = "programa_procedimiento", schema = "maestros", 
			joinColumns = { @JoinColumn(name = "programa_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "procedimiento_id") })
	private Set<Procedimiento> procedimientos;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name = "programa_medicamento", schema = "maestros", 
			joinColumns = { @JoinColumn(name = "programa_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "medicamento_id") })
	private Set<Medicamento> medicamentos;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name = "programa_diagnostico", schema = "maestros", 
			joinColumns = { @JoinColumn(name = "programa_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "diagnostico_id") })
	private Set<Diagnostico> diagnosticos;

	public Set<Procedimiento> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(Set<Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public Set<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(Set<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public Set<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(Set<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
}
