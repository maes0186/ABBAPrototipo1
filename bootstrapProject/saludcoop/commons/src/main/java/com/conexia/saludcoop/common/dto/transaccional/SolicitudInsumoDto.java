package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.InsumoDto;

public class SolicitudInsumoDto {
	private Long id;
	private InsumoDto insumo;
	private FormularioCTCInsumoDto formCTCInsumo;
	private FormulaInsumoDto formulaInsumo;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setInsumo(InsumoDto insumo){
		this.insumo = insumo;
	}

	public InsumoDto getInsumo(){
		return this.insumo;
	}
	public void setFormCTCInsumo(FormularioCTCInsumoDto formCTCInsumo){
		this.formCTCInsumo = formCTCInsumo;
	}

	public FormularioCTCInsumoDto getFormCTCInsumo(){
		return this.formCTCInsumo;
	}
	public void setFormulaInsumo(FormulaInsumoDto formulaInsumo){
		this.formulaInsumo = formulaInsumo;
	}

	public FormulaInsumoDto getFormulaInsumo(){
		return this.formulaInsumo;
	}
}
