package com.conexia.saludcoop.common.dto.transaccional;

import java.util.Date;

import com.conexia.saludcoop.common.dto.ProfesionalDto;

public class ConsumoDto {

	private Date fechaConsumo;
	private ProfesionalDto profesional;
	
	public Date getFechaConsumo() {
		return fechaConsumo;
	}
	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}
	public ProfesionalDto getProfesional() {
		return profesional;
	}
	public void setProfesional(ProfesionalDto profesional) {
		this.profesional = profesional;
	}
}
