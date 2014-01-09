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
@Table(name="afiliado_programa", schema="maestros", 
uniqueConstraints={@UniqueConstraint(columnNames={"afiliado_id","programa_id","fecha_delete"})
})
public class AfiliadoPrograma{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="afiliado_id", nullable=false)
	private Afiliado afiliado;
	
	@ManyToOne
	@JoinColumn(name="programa_id", nullable=false)
	private Programa programa;
	
	@Column(name="fecha_delete", nullable= true)
	private Date fechaDelete;
	
	public Afiliado getAfiliado(){
		return afiliado;
	}
	public Programa getPrograma(){
		return programa;
	}
	
	public void setAfiliado(Afiliado afiliado){
		this.afiliado = afiliado;
	}
	public void setPrograma(Programa programa){
		this.programa = programa;
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
