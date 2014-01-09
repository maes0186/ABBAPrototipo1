package com.conexia.saludcoop.common.entity.maestro;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.RegionalDto;
@Entity
@Table(name = "regional", schema = "maestros")
@Mappeable(mappedTo=RegionalDto.class)
public class Regional extends BaseMaestro implements Identifiable<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="division_seccional_id", nullable = false)
	private DivisionSeccional divisionSeccional;
	
	@Index(name="ix_regional_codigo")
	@Column(name = "codigo", nullable = false, length=10)
	private String codigo;
	
	@Index(name="ix_regional_descripcion")
	@Column(name = "descripcion", nullable = false, length=50)
	private String descripcion;
	
	@Column(name = "cliente_pk", nullable = true)
	private Integer clientePk;
	
	@OneToMany(mappedBy="regional", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<DirectorMedicoRegional> directoresMedicosRegionales = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="regional")
	private Set<DepartamentoRegional> departamentos;

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

	public Set<DepartamentoRegional> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<DepartamentoRegional> departamentos) {
		this.departamentos = departamentos;
	}

	public Long getId() {
		return id;
	}
	
	public RegionalDto toDto(){
		RegionalDto dto = new RegionalDto();
		dto.setCodigo(this.codigo);
		dto.setDescripcion(this.descripcion);
		dto.setId(this.id);
		return dto;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public DivisionSeccional getDivisionSeccional() {
		return divisionSeccional;
	}

	public void setDivisionSeccional(DivisionSeccional divisionSeccional) {
		this.divisionSeccional = divisionSeccional;
	}
	
    public Set<DirectorMedicoRegional> getDirectoresMedicosRegionales() {
    	return directoresMedicosRegionales;
    }

    public void setDirectoresMedicosRegionales(Set<DirectorMedicoRegional> directoresMedicosRegionales) {
    	this.directoresMedicosRegionales = directoresMedicosRegionales;
    }
	
    public void setId(Long id) {
    	this.id = id;
    }
}
