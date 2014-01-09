package com.conexia.saludcoop.common.entity.maestro;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.conexia.saludcoop.common.dto.DiagnosticoDto;

@Entity
@Table(name = "diagnostico", schema="maestros")
public class Diagnostico extends BaseMaestro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Index(name="ix_diagnostico_codigo")
	@Column(name = "codigo", nullable = false, length=6)
	private String codigo;
	
	@Index(name="ix_diagnostico_descripcion")
	@Column(name = "descripcion", nullable = false, length=500)
	private String descripcion;
	


	@OneToMany(fetch = FetchType.LAZY,mappedBy="diagnostico")
	private Set<DiagnosticoProcedimiento> procedimientos = new HashSet<DiagnosticoProcedimiento>();
	
	@OneToMany(fetch =FetchType.LAZY,mappedBy="diagnostico")
	private Set<DiagnosticoMedicamento> medicamentos = new HashSet<DiagnosticoMedicamento>();
	
	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Set<DiagnosticoProcedimiento> getProcedimientos() {
		return procedimientos;
	}


	public void setProcedimientos(Set<DiagnosticoProcedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public DiagnosticoDto toDto(){
		DiagnosticoDto dto = new DiagnosticoDto();
		dto.setId(this.id);
		dto.setCodigo(this.codigo);
		dto.setDescripcion(this.descripcion);
//		dto.setTipoDiagnostico(this.getTipoDiagnostico().toDto());
		return dto;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public Set<DiagnosticoMedicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(Set<DiagnosticoMedicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	
	
		
	
	
}
