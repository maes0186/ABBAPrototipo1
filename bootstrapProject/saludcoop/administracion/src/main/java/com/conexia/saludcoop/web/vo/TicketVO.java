package com.conexia.saludcoop.web.vo;

import java.util.List;

public class TicketVO {

	private List<ProcedimientoTicketVO> procedimientos;
	// TODO : al momento de hacer el VO, no había una definición distinta para medicamentos.
	private List<ProcedimientoTicketVO> medicamentos;
	private List<ProcedimientoTicketVO> insumos;

	public List<ProcedimientoTicketVO> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<ProcedimientoTicketVO> insumos) {
		this.insumos = insumos;
	}

	public List<ProcedimientoTicketVO> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(List<ProcedimientoTicketVO> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public List<ProcedimientoTicketVO> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<ProcedimientoTicketVO> medicamentos) {
		this.medicamentos = medicamentos;
	}

	
}
