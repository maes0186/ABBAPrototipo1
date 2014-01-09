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
@Table(name="departamento_regional", schema="maestros", 
uniqueConstraints={@UniqueConstraint(columnNames={"departamento_id","regional_id","fecha_delete"})
})
public class DepartamentoRegional{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="departamento_id", nullable=false)
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name="regional_id", nullable=false)
	private Regional regional;
	
	@Column(name="fecha_delete", nullable= true)
	private Date fechaDelete;
	
	public Departamento getDepartamento(){
		return departamento;
	}
	public Regional getRegional(){
		return regional;
	}
	
	public void setDepartamento(Departamento departamento){
		this.departamento = departamento;
	}
	public void setRegional(Regional regional){
		this.regional = regional;
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