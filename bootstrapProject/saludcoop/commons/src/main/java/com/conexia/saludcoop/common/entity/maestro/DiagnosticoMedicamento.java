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
@Table(name="diagnostico_medicamento", schema="maestros", 
uniqueConstraints={@UniqueConstraint(columnNames={"diagnostico_id","medicamento_id","fecha_delete"})
})
public class DiagnosticoMedicamento{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="diagnostico_id", nullable=false)
	private Diagnostico diagnostico;
	@ManyToOne
	@JoinColumn(name="medicamento_id", nullable=false)
	private Medicamento medicamento;
	
	@Column(name="fecha_delete", nullable= true)
	private Date fechaDelete;
	
	public Diagnostico getDiagnostico(){
		return diagnostico;
	}
	public Medicamento getMedicamento(){
		return medicamento;
	}
	
	public void setDiagnostico(Diagnostico diagnostico){
		this.diagnostico = diagnostico;
	}
	public void setMedicamento(Medicamento medicamento){
		this.medicamento = medicamento;
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
