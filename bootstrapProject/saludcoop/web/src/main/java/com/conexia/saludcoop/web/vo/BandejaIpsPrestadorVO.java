package com.conexia.saludcoop.web.vo;

import java.util.List;

public class BandejaIpsPrestadorVO {
	private String respuestaMotivoDevolucion;
	private String respuestaDevolucion;
	private List<DocumentoSoporteVO> adjuntos;
	private DiagnosticoVO diagnostico;
	private BandejaAltoCostoMedicamentoVO medicamento;
	private BandejaAltoCostoProcedimientoVO procedimiento;
	private BandejaAltoCostoInsumoVO insumo;
	private boolean editable;
	private boolean autorizada;
	private String tipoItem;
	private Integer estado;
	private String justificacion;
	private boolean aprobada;

	public String getRespuestaMotivoDevolucion() {
		return respuestaMotivoDevolucion;
	}

	public void setRespuestaMotivoDevolucion(String respuestaMotivoDevolucion) {
		this.respuestaMotivoDevolucion = respuestaMotivoDevolucion;
	}

	public String getRespuestaDevolucion() {
		return respuestaDevolucion;
	}

	public void setRespuestaDevolucion(String respuestaDevolucion) {
		this.respuestaDevolucion = respuestaDevolucion;
	}

	public List<DocumentoSoporteVO> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<DocumentoSoporteVO> adjuntos) {
		this.adjuntos = adjuntos;
	}

	public DiagnosticoVO getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(DiagnosticoVO diagnostico) {
		this.diagnostico = diagnostico;
	}

	public BandejaAltoCostoMedicamentoVO getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(BandejaAltoCostoMedicamentoVO medicamento) {
		this.medicamento = medicamento;
	}

	public BandejaAltoCostoProcedimientoVO getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(BandejaAltoCostoProcedimientoVO procedimiento) {
		this.procedimiento = procedimiento;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isAutorizada() {
		return autorizada;
	}

	public void setAutorizada(boolean autorizada) {
		this.autorizada = autorizada;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public BandejaAltoCostoInsumoVO getInsumo() {
		return insumo;
	}

	public void setInsumo(BandejaAltoCostoInsumoVO insumo) {
		this.insumo = insumo;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public boolean isAprobada() {
		return aprobada;
	}

	public void setAprobada(boolean aprobada) {
		this.aprobada = aprobada;
	}

}
