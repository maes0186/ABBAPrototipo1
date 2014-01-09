package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.LocalidadDto;



/**
 * Maestro de Localidades.
 * 
 * @author ebarbin
 *
 */
@Entity
@Table(name = "localidad", schema = "maestros")
@Mappeable(mappedTo=LocalidadDto.class)
public class Localidad extends BaseMaestro implements Identifiable<Long> {

	/**
	 * Identificador de un localidad.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * Código del municipio.
	 */
	@Column(name = "cliente_pk", nullable = false, length=10)
	private Integer clientePk;

	/**
	 * Descripción del municipio.
	 */
	@Column(name = "descripcion", nullable = false, length=50)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="municipio_id", nullable = false)
	private Municipio municipio; //MAPEGA CONTRA ENUM TIPO IDENTIFICACION IPS
	
	@ManyToOne
	@JoinColumn(name="regional_id", nullable = false)
	private Regional regional; //MAPEGA CONTRA ENUM TIPO IDENTIFICACION IPS
	
    public Long getId() {
    	return id;
    }

    public void setId(Long id) {
    	this.id = id;
    }
    
	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		
		if (this.getId() != null && ((Localidad) obj).getId() != null) {
			return this.getId().equals(((Localidad) obj).getId());
		}
		
		return false;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}


    

}
