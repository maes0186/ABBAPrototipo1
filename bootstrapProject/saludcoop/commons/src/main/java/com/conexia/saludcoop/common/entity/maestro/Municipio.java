package com.conexia.saludcoop.common.entity.maestro;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.MunicipioDto;


/**
 * Maestro de Municipios.
 * 
 * @author ebarbin
 *
 */
@Entity
@Table(name = "municipio", schema = "maestros")
@Mappeable(mappedTo=MunicipioDto.class)
public class Municipio extends BaseMaestro implements Identifiable<Long> {

	/**
	 * Identificador de un municipio.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * Código del municipio.
	 */
	@Column(name = "codigo", nullable = false, length=10)
	private String codigo;

	/**
	 * Descripción del municipio.
	 */
	@Column(name = "descripcion", nullable = false, length=50)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="departamento_id")
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name="regional_id")
	private Regional regional;
	
	@Column(name = "cliente_pk", nullable = true)
	private Integer clientePk;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name = "municipio_division_seccional", schema = "maestros", 
			joinColumns = { @JoinColumn(name = "municipio_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "division_seccional_id") })
	private Set<DivisionSeccional> divisionesSeccionales;
	
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
	
    

    
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public final int hashCode() {
		return ((this.id == null) ? 0 : this.id.hashCode());
	}

    @Override
	public boolean equals(final Object obj) {
    	
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		if (this.getId() != null && ((Municipio) obj).getId() != null) {
			return this.getId().equals(((Municipio) obj).getId());
		}
		
		return false;
	}
    
	public MunicipioDto toDto(){
		MunicipioDto dto = new MunicipioDto();
		dto.setId(this.id);
		dto.setCodigo(this.codigo);
		dto.setDescripcion(this.descripcion);
		dto.setDepartamento(this.departamento.toDto());
		return dto;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Set<DivisionSeccional> getDivisionesSeccionales() {
		return divisionesSeccionales;
	}

	public void setDivisionesSeccionales(Set<DivisionSeccional> divisionesSeccionales) {
		this.divisionesSeccionales = divisionesSeccionales;
	}


}
