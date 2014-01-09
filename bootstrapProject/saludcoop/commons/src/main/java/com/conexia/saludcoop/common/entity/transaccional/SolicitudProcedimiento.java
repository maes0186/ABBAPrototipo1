package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.entity.maestro.Especialidad;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;

@Entity
@Table(name = "solicitud_procedimiento", schema = "transaccional")
public class SolicitudProcedimiento implements Identifiable<Long>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	public Long getId() {
		return id;
	}
	
	@OneToOne
	@JoinColumn(name="solicitud_item_id")
	private SolicitudItem solicitudItem;
	
	@ManyToOne
	@JoinColumn(name="procedimiento_id", nullable=false)
	private Procedimiento procedimiento;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="solicitudProcedimiento")
	private FormularioCTCProcedimiento formCTCProcedimiento;
	
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="solicitudProcedimiento")
	private FormulaProcedimiento formulaProcedimiento;

	@ManyToOne
    @JoinColumn(nullable = false, name="especialidad_id")
    private Especialidad especialidad;

	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	public Procedimiento getProcedimiento() {
		return procedimiento;
	}
	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}
	public FormularioCTCProcedimiento getFormCTCProcedimiento() {
		return formCTCProcedimiento;
	}
	public void setFormCTCProcedimiento(
			FormularioCTCProcedimiento formCTCProcedimiento) {
		this.formCTCProcedimiento = formCTCProcedimiento;
		this.formCTCProcedimiento.setSolicitudProcedimiento(this);
	}
	public FormulaProcedimiento getFormulaProcedimiento() {
		return formulaProcedimiento;
	}
	public void setFormulaProcedimiento(FormulaProcedimiento formulaProcedimiento) {
		this.formulaProcedimiento = formulaProcedimiento;
		this.formulaProcedimiento.setSolicitudProcedimiento(this);
	}
	public SolicitudItem getSolicitudItem() {
		return solicitudItem;
	}
	public void setSolicitudItem(SolicitudItem solicitudItem) {
		this.solicitudItem = solicitudItem;
	}
	

}
