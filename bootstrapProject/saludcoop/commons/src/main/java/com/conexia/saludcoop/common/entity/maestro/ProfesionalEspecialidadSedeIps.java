package com.conexia.saludcoop.common.entity.maestro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="profesional_especialidad_x_sede_ips", schema="maestros", 
uniqueConstraints={@UniqueConstraint(columnNames={"profesional_especialidad_id","sede_ips_id","fecha_delete"})
})
public class ProfesionalEspecialidadSedeIps{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="profesional_especialidad_id", nullable=false)
	private ProfesionalEspecialidad profesionalEspecialidad;
	
	@ManyToOne
	@JoinColumn(name="sede_ips_id", nullable=false)
	private SedeIps sedeIps;
	
	@Column(name="fecha_delete", nullable= true)
	private Date fechaDelete;
	
	public ProfesionalEspecialidad getProfesionalEspecialidad(){
		return profesionalEspecialidad;
	}
	public SedeIps getSedeIps(){
		return sedeIps;
	}
	
	public void setProfesionalEspecialidad(ProfesionalEspecialidad profesionalEspecialidad){
		this.profesionalEspecialidad = profesionalEspecialidad;
	}
	public void setSedeIps(SedeIps sedeIps){
		this.sedeIps = sedeIps;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFechaDelete() {
		return fechaDelete;
	}
	public void setFechaDelete(Date fechaDelete) {
		this.fechaDelete = fechaDelete;
	}
	
	
	
}
