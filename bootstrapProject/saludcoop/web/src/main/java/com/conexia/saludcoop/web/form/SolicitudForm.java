package com.conexia.saludcoop.web.form;

import java.util.List;
import java.util.Vector;

import com.conexia.saludcoop.web.vo.ProfesionalVO;
import com.conexia.saludcoop.web.vo.SedeIpsVO;

public class SolicitudForm {
	
	private Long userId;
	
	private List<DiagnosticoItemForm> diagnosticos= new Vector<>();
	private List<ProcedimientoItemForm> procedimientos = new Vector<>();
	private List<MedicamentoItemForm> medicamentos = new Vector<>();
	private List<InsumoItemForm> insumos = new Vector<>();
	private ResumenForm resumen;
	private ProfesionalVO profesionalEspecialidad;
	private SedeIpsVO sedeIps;
	private List<String> archivos = new Vector<>();
	private String observaciones;
	private Integer nroSolicitud;
	private Integer idProfesional;
	private Integer idSedeIps;
	
			
	
	
	public Integer getIdProfesional() {
		return idProfesional;
	}
	public void setIdProfesional(Integer idProfesional) {
		this.idProfesional = idProfesional;
	}
	public Integer getIdSedeIps() {
		return idSedeIps;
	}
	public void setIdSedeIps(Integer idSedeIps) {
		this.idSedeIps = idSedeIps;
	}
	public Integer getNroSolicitud() {
		return nroSolicitud;
	}
	public void setNroSolicitud(Integer nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	public ResumenForm getResumen() {
		return resumen;
	}
	public void setResumen(ResumenForm resumen) {
		this.resumen = resumen;
	}
	public ProfesionalVO getProfesionalEspecialidad() {
		return profesionalEspecialidad;
	}
	public void setProfesionalEspecialidad(ProfesionalVO profesionalEspecialidad) {
		this.profesionalEspecialidad = profesionalEspecialidad;
	}
	public SedeIpsVO getSedeIps() {
		return sedeIps;
	}
	public void setSedeIps(SedeIpsVO sedeIps) {
		this.sedeIps = sedeIps;
	}
	public List<InsumoItemForm> getInsumos() {
		return insumos;
	}
	public void setInsumos(List<InsumoItemForm> insumos) {
		this.insumos = insumos;
	}

	
}
