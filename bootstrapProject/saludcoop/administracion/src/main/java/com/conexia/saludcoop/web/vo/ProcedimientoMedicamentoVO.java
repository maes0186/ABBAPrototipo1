package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;


public class ProcedimientoMedicamentoVO {
	private Integer id;
	private String tipoPPM;
	private String codigo;
	private String descripcion;
	
	public ProcedimientoMedicamentoVO(ProcedimientoDto o){
		this.id = o.getId();
		this.tipoPPM = o.getTipoPPM().getDescripcion();
		this.codigo = o.getCodigo();
		this.descripcion = o.getDescripcion();
	}
	public ProcedimientoMedicamentoVO(MedicamentoDto o){
		this.id = o.getId();
		this.tipoPPM = o.getTipoPPM().getDescripcion();
		this.codigo = o.getCodigo();
		this.descripcion = o.getDescripcion();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipoPPM() {
		return tipoPPM;
	}
	public void setTipoPPM(String tipoPPM) {
		this.tipoPPM = tipoPPM;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
