package com.conexia.saludcoop.common.entity.maestro;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.DepartamentoDto;


/**
 * Maestro de Departamentos.
 * 
 * @author ebarbin
 *
 */
@Entity

@Table(name = "departamento", schema = "maestros")
@Mappeable(mappedTo=DepartamentoDto.class)
public class Departamento extends BaseMaestro implements Identifiable<Long> {

	/**
	 * Identificador del departamento.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * Código del departamento.
	 */
	@Index(name="ix_departamento_codigo")
	@Column(name = "codigo", nullable = false, length=10)
	private String codigo;

	/**
	 * Descripción del departamento.
	 */
	@Index(name="ix_departamento_descripcion")
	@Column(name = "descripcion", nullable = false, length=50)
	private String descripcion;

	/**
	 * Municipios correspondientes al departamento.
	 */
	@OneToMany
	@JoinColumn(referencedColumnName="id", name="departamento_id")
	private Set<Municipio> municipios;

	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="departamento")
	private Set<DepartamentoRegional> regionales;
	
	
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
	
    public Set<Municipio> getMunicipios() {
    	return municipios;
    }
	
    public void setMunicipios(Set<Municipio> municipios) {
    	this.municipios = municipios;
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
		
		if (this.getId() != null && ((Departamento) obj).getId() != null) {
			return this.getId().equals(((Departamento) obj).getId());
		}
		
		return false;
	}

    
    
	

	public DepartamentoDto toDto(){
		DepartamentoDto dto = new DepartamentoDto();
		dto.setId(this.id);
		dto.setCodigo(this.codigo);
		dto.setDescripcion(this.descripcion);
		return dto;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public Set<DepartamentoRegional> getRegionales() {
		return regionales;
	}

	public void setRegionales(Set<DepartamentoRegional> regionales) {
		this.regionales = regionales;
	}


}
