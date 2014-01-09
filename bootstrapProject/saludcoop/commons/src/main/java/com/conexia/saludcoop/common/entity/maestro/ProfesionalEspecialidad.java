package com.conexia.saludcoop.common.entity.maestro;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.UniqueConstraint;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.dto.ProfesionalEspecialidadDto;

@Entity
@Table(name = "profesional_especialidad", schema = "maestros", uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "profesional_id", "especialidad_id", "nivel_autorizacion_id" }) })
public class ProfesionalEspecialidad implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "profesional_id", nullable = false)
	private Profesional profesional;
	@ManyToOne
	@JoinColumn(name = "especialidad_id", nullable = false)
	private Especialidad especialidad;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profesionalEspecialidad")
	private Set<ProfesionalEspecialidadSedeIps> sedes = new HashSet<ProfesionalEspecialidadSedeIps>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nivel_autorizacion_id", nullable = true)
	private NivelAutorizacion nivelAutorizacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado_profesional_id", nullable = false)
	private EstadoProfesional estadoProfesional;

	@Column(name = "fecha_insert", nullable = true)
	private Date fechaInsert;

	@Column(name = "fecha_delete", nullable = true)
	private Date fechaDelete;

	@Column(name = "fecha_update", nullable = true)
	private Date fechaUpdate;

	@Column(name = "version", nullable = true)
	private Integer version;

	public Date getFechaInsert() {
		return fechaInsert;
	}

	public void setFechaInsert(Date fechaInsert) {
		this.fechaInsert = fechaInsert;
	}

	public Date getFechaDelete() {
		return fechaDelete;
	}

	public void setFechaDelete(Date fechaDelete) {
		this.fechaDelete = fechaDelete;
	}

	public Date getFechaUpdate() {
		return fechaUpdate;
	}

	public void setFechaUpdate(Date fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ProfesionalEspecialidadDto toDto() {
		
		ProfesionalEspecialidadDto peDto = new ProfesionalEspecialidadDto();
		peDto.setId(this.id);
		
		peDto.setEspecialidad(this.getEspecialidad().toDto());
		peDto.setProfesional(this.getProfesional().toDto());
		return peDto;
	}

	public NivelAutorizacion getNivelAutorizacion() {
		return nivelAutorizacion;
	}

	public void setNivelAutorizacion(NivelAutorizacion nivelAutorizacion) {
		this.nivelAutorizacion = nivelAutorizacion;
	}

	public EstadoProfesional getEstadoProfesional() {
		return estadoProfesional;
	}

	public void setEstadoProfesional(EstadoProfesional estadoProfesional) {
		this.estadoProfesional = estadoProfesional;
	}

	public Long getId() {
		return id;
	}

	public Set<ProfesionalEspecialidadSedeIps> getSedes() {
		return sedes;
	}

	public void setSedes(Set<ProfesionalEspecialidadSedeIps> sedes) {
		this.sedes = sedes;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

}
