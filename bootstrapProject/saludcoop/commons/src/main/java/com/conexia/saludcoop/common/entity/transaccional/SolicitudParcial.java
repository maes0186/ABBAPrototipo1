package com.conexia.saludcoop.common.entity.transaccional;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.conexia.saludcoop.common.dto.transaccional.SolicitudParcialDto;
import com.conexia.saludcoop.common.entity.maestro.SedeIps;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacion;
import com.conexia.saludcoop.common.entity.security.User;

@Entity
@Table(name="solicitud_parcial", schema="transaccional")
@org.hibernate.annotations.Entity(dynamicInsert=true)
public class SolicitudParcial{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name="numero_solicitud")
	private Integer nroSolicitud; 
	
	@ManyToOne
	@JoinColumn(name = "sede_ips_id")
	private SedeIps sedeIps;

	@Column(name = "nombre_completo_afiliado", length = 100)
	private String nombreCompletoAfiliado;
	
	@ManyToOne
	@JoinColumn(name = "tipo_identificacion_afiliado_id")
	private TipoIdentificacion tipoIdentificacionAfiliado;

	@Column(name = "numero_identificacion_afiliado", length = 30)
	private String numeroIdentificacionAfiliado;

	@Column(name = "fecha_creacion", columnDefinition="timestamp default now()")
	private Date fechaCreacion;
	
	@Column(name = "fecha_update", columnDefinition="timestamp default now()")
	private Date fechaUpdate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name="form_data",columnDefinition="json" )
	private String formData;

	public TipoIdentificacion getTipoIdentificacionAfiliado() {
		return tipoIdentificacionAfiliado;
	}

	public void setTipoIdentificacionAfiliado(
			TipoIdentificacion tipoIdentificacionAfiliado) {
		this.tipoIdentificacionAfiliado = tipoIdentificacionAfiliado;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaUpdate() {
		return fechaUpdate;
	}

	public void setFechaUpdate(Date fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}

	public String getNumeroIdentificacionAfiliado() {
		return numeroIdentificacionAfiliado;
	}

	public void setNumeroIdentificacionAfiliado(String numeroIdentificacionAfiliado) {
		this.numeroIdentificacionAfiliado = numeroIdentificacionAfiliado;
	}

	public SedeIps getSedeIps() {
		return sedeIps;
	}

	public void setSedeIps(SedeIps sedeIps) {
		this.sedeIps = sedeIps;
	}
	

	public Integer getNroSolicitud() {
		return nroSolicitud;
	}

	public void setNroSolicitud(Integer nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
	
    public String getNombreCompletoAfiliado() {
    	return nombreCompletoAfiliado;
    }
	
    public void setNombreCompletoAfiliado(String nombreCompletoAfiliado) {
    	this.nombreCompletoAfiliado = nombreCompletoAfiliado;
    }

	public SolicitudParcialDto toDto() {
		SolicitudParcialDto sDto = new SolicitudParcialDto();
		sDto.setId(this.id);
		sDto.setNroSolicitud(this.nroSolicitud);
		sDto.setFechaCreacion(this.fechaCreacion);
		sDto.setFechaUpdate(this.fechaUpdate);
		sDto.setFormData(this.formData);
		sDto.setNumeroIdentificacionAfiliado(this.numeroIdentificacionAfiliado);
		if(this.sedeIps != null){
			sDto.setSedeIps(this.sedeIps.toDto());
		}
		sDto.setTipoIdentificacionAfiliado(this.tipoIdentificacionAfiliado.toDto());
		sDto.setUser(this.user.getName());
		sDto.setNombreCompletoAfiliado(this.nombreCompletoAfiliado);
		return sDto;
	}
}
