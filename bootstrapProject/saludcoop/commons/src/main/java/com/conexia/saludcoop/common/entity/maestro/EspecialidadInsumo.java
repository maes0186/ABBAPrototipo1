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
@Table(name="especialidad_insumo", schema="maestros", 
uniqueConstraints={@UniqueConstraint(columnNames={"especialidad_id","insumo_id","fecha_delete"})
})
public class EspecialidadInsumo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="especialidad_id", nullable=false)
	private Especialidad especialidad;
	
	@ManyToOne
	@JoinColumn(name="insumo_id", nullable=false)
	private Insumo insumo;
	
	@Column(name="fecha_delete", nullable= true)
	private Date fechaDelete;
	
	public Especialidad getEspecialidad(){
		return especialidad;
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


	public Insumo getInsumo() {
		return insumo;
	}


	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	
	
	
}