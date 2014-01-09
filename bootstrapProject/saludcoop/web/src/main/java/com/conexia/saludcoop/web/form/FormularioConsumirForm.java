package com.conexia.saludcoop.web.form;

import java.util.Date;

public class FormularioConsumirForm {

	private Long solicitudItemId;
	private Date fechaConsumo;

	public Long getSolicitudItemId() {
		return solicitudItemId;
	}

	public void setSolicitudItemId(Long solicitudItemId) {
		this.solicitudItemId = solicitudItemId;
	}

	public Date getFechaConsumo() {
		return fechaConsumo;
	}

	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}
}

