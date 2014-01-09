
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

@Entity
@Table(name="servicio_especialidad", schema="maestros")
public class ServicioEspecialidad{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="servicio_id", nullable=false)
	private Servicio servicio;
	
	@ManyToOne
	@JoinColumn(name="especialidad_id", nullable=false)
	private Especialidad especialidad;
	
	@Column(name="fecha_delete", nullable= true)
	private Date fechaDelete;
	
	public Servicio getServicio(){
		return servicio;
	}
	public Especialidad getEspecialidad(){
		return especialidad;
	}
	
	public void setServicio(Servicio servicio){
		this.servicio = servicio;
	}
	public void setEspecialidad(Especialidad especialidad){
		this.especialidad = especialidad;
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