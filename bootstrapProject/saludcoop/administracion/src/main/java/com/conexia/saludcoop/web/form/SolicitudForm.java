package com.conexia.saludcoop.web.form;

import java.util.List;

public class SolicitudForm {
	
	private List<DiagnosticoItemForm> diagnosticos;
	private List<ProcedimientoItemForm> procedimientos;
	private List<MedicamentoItemForm> medicamentos;
	private EpicrisisForm epicrisis;
	private List<String> archivos;
			
	public List<String> getArchivos() {
		return archivos;
	}
	public void setArchivos(List<String> archivos) {
		this.archivos = archivos;
	}
	public List<DiagnosticoItemForm> getDiagnosticos() {
		return diagnosticos;
	}
	public void setDiagnosticos(List<DiagnosticoItemForm> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
	public List<ProcedimientoItemForm> getProcedimientos() {
		return procedimientos;
	}
	public void setProcedimientos(List<ProcedimientoItemForm> procedimientos) {
		this.procedimientos = procedimientos;
	}
	public List<MedicamentoItemForm> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<MedicamentoItemForm> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public EpicrisisForm getEpicrisis() {
		return epicrisis;
	}
	public void setEpicrisis(EpicrisisForm epicrisis) {
		this.epicrisis = epicrisis;
	}

}
