package com.conexia.saludcoop.common.entity.maestro;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.IpsDto;

/**
 * @author ebarbin
 *
 */
@Entity
@Table(name = "ips", schema = "maestros")
@Mappeable(mappedTo=IpsDto.class)
public class Ips extends BaseMaestro implements Identifiable<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "direccion", nullable = false, length=100)
	private String direccion;
	
	@Column(name = "telefono", nullable = false, length=50)
	private String telefono;

	@Column(name = "razon_social", nullable = false, length=100)
	private String razonSocial;
	
	
	@ManyToOne
	@JoinColumn(name="tipo_identificacion_id", nullable = false)
	private TipoIdentificacion tipoIdentificacion; //MAPEGA CONTRA ENUM TIPO IDENTIFICACION IPS
	
	@Column(name = "numero_identificacion", nullable = false, length=10)
	private String numeroIdentificacion;
	
	@ManyToOne
	@JoinColumn(name="tipo_ips_id", nullable = false)
	private TipoIps tipoIps;

	
	@ManyToOne
	@JoinColumn(name="estado_ips_id", nullable = false)
	private EstadoIps estadoIps;
	
	@OneToMany(mappedBy = "ips")
	private Set<SedeIps> sedes = new HashSet<SedeIps>();
	
	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;
	
	
	
	@Override
    public Long getId() {
	    return this.id;
    }

    public String getDireccion() {
    	return direccion;
    }
	
    public void setDireccion(String direccion) {
    	this.direccion = direccion;
    }
	
    public String getTelefono() {
    	return telefono;
    }
	
    public void setTelefono(String telefono) {
    	this.telefono = telefono;
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

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public Set<SedeIps> getSedes() {
		return sedes;
	}

	public void setSedes(Set<SedeIps> sedes) {
		this.sedes = sedes;
	}
	
	public IpsDto toDto(){
		IpsDto dto = new IpsDto();
		dto.setId(this.id);
		dto.setDireccion(this.direccion);
		dto.setTelefono(this.telefono);
		dto.setRazonSocial(this.razonSocial);
		dto.setTipoIdentificacion(this.tipoIdentificacion.toDto());
		dto.setNumeroIdentificacion(this.numeroIdentificacion);
//		dto.setSedes(this.sedes);
		return dto;
	}

	@Override
	public boolean equals(Object obj) {

		return this.id.equals(((Ips)obj).getId());
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public TipoIps getTipoIps() {
		return tipoIps;
	}

	public void setTipoIps(TipoIps tipoIps) {
		this.tipoIps = tipoIps;
	}

	public EstadoIps getEstadoIps() {
		return estadoIps;
	}

	public void setEstadoIps(EstadoIps estadoIps) {
		this.estadoIps = estadoIps;
	}


	
	
}