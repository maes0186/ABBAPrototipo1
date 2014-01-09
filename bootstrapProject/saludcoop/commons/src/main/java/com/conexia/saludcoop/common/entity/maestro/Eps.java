package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.EpsDto;

/**
 * @author ebarbin
 *
 */
@Entity
@Table(name = "eps", schema = "maestros")
@Mappeable(mappedTo=EpsDto.class)
public class Eps extends BaseMaestro implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	

	@Index(name="ix_eps_razon_social")
	@Column(name = "razon_social", nullable = false, length=100)
	private String razonSocial;
	
	@ManyToOne
	@JoinColumn(name="tipo_identificacion_id", nullable = false)
	private TipoIdentificacion tipoIdentificacion; //MAPEGA CONTRA ENUM TIPO IDENTIFICACION EPS
	
	@Column(name = "numero_identificacion", nullable = false)
	private String numeroIdentificacion;
	
	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;
	
	@Override
    public Long getId() {
	    return this.id;
    }

    public String getRazonSocial() {
    	return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
    	this.razonSocial = razonSocial;
    }
	
    public void setId(Long id) {
    	this.id = id;
    }


	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	public EpsDto toDto(){
		EpsDto dto = new EpsDto();
		dto.setId(this.id);
		dto.setRazonSocial(this.razonSocial);
		dto.setTipoIdentificacion(this.tipoIdentificacion.toDto());
		dto.setNumeroIdentificacion(this.numeroIdentificacion);
		return dto;
	}

	
	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}
}
