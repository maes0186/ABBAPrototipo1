package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conexia.saludcoop.common.dto.transaccional.ProcedimientoHomologoDto;
import com.conexia.saludcoop.common.entity.maestro.ObjetivoProcedimiento;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
@Entity
@Table(name="procedimiento_homologo", schema="transaccional")
public class ProcedimientoHomologo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="formulario_ctc_procedimiento_id", nullable=false)
	private FormularioCTCProcedimiento formularioCTCProcedimiento;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "procedimiento_id", nullable = false)
	private Procedimiento procedimiento;
	
	@Column(name="cantidad_periodo", nullable=false)
	private Integer cantidadPeriodo;
	
	@Column(name="dias_de_uso", nullable=false)
	private Integer diasDeUso;
	
	@Column(name="frecuencia_de_uso", nullable=false)
	private Integer frecuenciaDeUso;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objetivo_procedimiento_id", nullable = false)
	private ObjetivoProcedimiento objetivoProcedimiento;

	public FormularioCTCProcedimiento getFormularioCTCProcedimiento() {
		return formularioCTCProcedimiento;
	}

	public void setFormularioCTCProcedimiento(
			FormularioCTCProcedimiento formularioCTCProcedimiento) {
		this.formularioCTCProcedimiento = formularioCTCProcedimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Procedimiento getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}

	public Integer getCantidadPeriodo() {
		return cantidadPeriodo;
	}

	public void setCantidadPeriodo(Integer cantidadPeriodo) {
		this.cantidadPeriodo = cantidadPeriodo;
	}

	public Integer getDiasDeUso() {
		return diasDeUso;
	}

	public void setDiasDeUso(Integer diasDeUso) {
		this.diasDeUso = diasDeUso;
	}

	public Integer getFrecuenciaDeUso() {
		return frecuenciaDeUso;
	}

	public void setFrecuenciaDeUso(Integer frecuenciaDeUso) {
		this.frecuenciaDeUso = frecuenciaDeUso;
	}

	
	
	public ObjetivoProcedimiento getObjetivoProcedimiento() {
		return objetivoProcedimiento;
	}

	public void setObjetivoProcedimiento(ObjetivoProcedimiento objetivoProcedimiento) {
		this.objetivoProcedimiento = objetivoProcedimiento;
	}

	public ProcedimientoHomologoDto toDto(){
		ProcedimientoHomologoDto dto = new ProcedimientoHomologoDto();
		dto.setId(id);
		dto.setProcedimiento(procedimiento.toDto());
		dto.setCantidadPeriodo(cantidadPeriodo);
		dto.setDiasDeUso(diasDeUso);
		dto.setFrecuenciaDeUso(frecuenciaDeUso);		
		dto.setObjetivoProcedimiento(objetivoProcedimiento.toDto());
		return dto;
	}
}
