package com.conexia.saludcoop.web.vo;

import java.util.List;

public class CheckEntregasVO {

	private Integer cantidad;
	private List<EntregaVO> entregas;
	
	
	
	public CheckEntregasVO(Integer cantidad, List<EntregaVO> entregas) {
		super();
		this.cantidad = cantidad;
		this.entregas = entregas;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public CheckEntregasVO setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
		return this;
	}
	public List<EntregaVO> getEntregas() {
		return entregas;
	}
	public void setEntregas(List<EntregaVO> entregas) {
		this.entregas = entregas;
	}
	
	
	
}
