package com.conexia.saludcoop.common.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.dto.UsuarioEntidadDto;
import com.conexia.saludcoop.common.entity.maestro.LineaDeFrente;
import com.conexia.saludcoop.common.entity.maestro.ProfesionalEspecialidad;
import com.conexia.saludcoop.common.entity.maestro.SedeIps;

@Entity
@Table(name = "usuario_entidad", schema = "security")
public class UsuarioEntidad implements Identifiable<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private User usuario;

	@OneToOne
	@JoinColumns({ @JoinColumn(name = "profesional_especialidad_id", nullable = true)})
	private ProfesionalEspecialidad profesionalEspecialidad;

	@OneToOne
	@JoinColumn(name = "sede_ips_id", nullable = true)
	private SedeIps sedeIps;
	
	@ManyToOne
	@JoinColumn(name = "ldf_id", nullable = true)
	private LineaDeFrente ldf;

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public ProfesionalEspecialidad getProfesionalEspecialidad() {
		return profesionalEspecialidad;
	}

	public void setProfesionalEspecialidad(ProfesionalEspecialidad profesionalEspecialidad) {
		this.profesionalEspecialidad = profesionalEspecialidad;
	}

	public SedeIps getSedeIps() {
		return sedeIps;
	}

	public void setSedeIps(SedeIps sedeIps) {
		this.sedeIps = sedeIps;
	}

	public Long getId() {
		return id;
	}

	public LineaDeFrente getLdf() {
		return ldf;
	}

	public void setLdf(LineaDeFrente ldf) {
		this.ldf = ldf;
	}

	public UsuarioEntidadDto toDto() {
		UsuarioEntidadDto ueDto = new UsuarioEntidadDto();
		ueDto.setId(this.id);
		if (this.sedeIps != null) {
			ueDto.setSedeIps(this.getSedeIps().toDto());
		}

		ueDto.setUsuarioId(this.usuario.getId());
		if (this.profesionalEspecialidad != null) {
			ueDto.setProfesionalEspecialidad(profesionalEspecialidad.toDto());
		}
		return ueDto;

	}

}
