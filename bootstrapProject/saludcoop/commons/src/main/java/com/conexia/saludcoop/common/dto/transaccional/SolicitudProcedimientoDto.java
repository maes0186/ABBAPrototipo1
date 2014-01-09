package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.EspecialidadDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;


public class SolicitudProcedimientoDto { 
	private Long id;
	private ProcedimientoDto procedimiento;
	private FormularioCTCProcedimientoDto formCTCProcedimiento;
	private FormulaProcedimientoDto formulaProcedimiento;
	private EspecialidadDto especialidad;
	

	public EspecialidadDto getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(EspecialidadDto especialidad) {
		this.especialidad = especialidad;
	}
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setProcedimiento(ProcedimientoDto procedimiento){
		this.procedimiento = procedimiento;
	}

	public ProcedimientoDto getProcedimiento(){
		return this.procedimiento;
	}
	public void setFormCTCProcedimiento(FormularioCTCProcedimientoDto formCTCProcedimiento){
		this.formCTCProcedimiento = formCTCProcedimiento;
	}

	public FormularioCTCProcedimientoDto getFormCTCProcedimiento(){
		return this.formCTCProcedimiento;
	}
	public void setFormulaProcedimiento(FormulaProcedimientoDto formulaProcedimiento){
		this.formulaProcedimiento = formulaProcedimiento;
	}

	public FormulaProcedimientoDto getFormulaProcedimiento(){
		return this.formulaProcedimiento;
	}

}
