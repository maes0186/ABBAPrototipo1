package com.conexia.saludcoop.web.vo;

/**
 * <b>Bandeja auditor alto costo</b> 
 * Clase usada para mostrar los datos de autorización de solicitudes para la bandeja
 * de alto costo
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 15/10/2013
 * 
 */
public class BandejaAltoCostoVO {
    private String respuestaMotivoDevolucion;
    private String respuestaDevolucionCNA;
    private String observacionesLineaFrente;
    private String descripcionEntidad;
    private String codigoDiagnostico;
    private String descDiagnostico;
    private Integer numeroAutorizaciones30Dias;
    private String fechaLiberacion;
    private String solicitudEntregasPendientes;
    private BandejaAltoCostoMedicamentoVO medicamento;
    private BandejaAltoCostoProcedimientoVO procedimiento;
    private BandejaAltoCostoInsumoVO insumo;
    private boolean devuelta;
    private String tipoItem;
    private String tipoBandeja;
    private boolean devueltaLF;
    private String numeroTutela;
    
    public String getRespuestaMotivoDevolucion() {
        return respuestaMotivoDevolucion;
    }
    public void setRespuestaMotivoDevolucion(String respuestaMotivoDevolucion) {
        this.respuestaMotivoDevolucion = respuestaMotivoDevolucion;
    }
    public String getRespuestaDevolucionCNA() {
        return respuestaDevolucionCNA;
    }
    public void setRespuestaDevolucionCNA(String respuestaDevolucionCNA) {
        this.respuestaDevolucionCNA = respuestaDevolucionCNA;
    }
    public String getObservacionesLineaFrente() {
        return observacionesLineaFrente;
    }
    public void setObservacionesLineaFrente(String observacionesLineaFrente) {
        this.observacionesLineaFrente = observacionesLineaFrente;
    }
    public String getDescripcionEntidad() {
        return descripcionEntidad;
    }
    public void setDescripcionEntidad(String descripcionEntidad) {
        this.descripcionEntidad = descripcionEntidad;
    }
    public String getCodigoDiagnostico() {
        return codigoDiagnostico;
    }
    public void setCodigoDiagnostico(String codigoDiagnostico) {
        this.codigoDiagnostico = codigoDiagnostico;
    }
    public String getDescDiagnostico() {
        return descDiagnostico;
    }
    public void setDescDiagnostico(String descDiagnostico) {
        this.descDiagnostico = descDiagnostico;
    }
    public Integer getNumeroAutorizaciones30Dias() {
        return numeroAutorizaciones30Dias;
    }
    public void setNumeroAutorizaciones30Dias(Integer numeroAutorizaciones30Dias) {
        this.numeroAutorizaciones30Dias = numeroAutorizaciones30Dias;
    }
    public String getFechaLiberacion() {
        return fechaLiberacion;
    }
    public void setFechaLiberacion(String fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }
    public String getSolicitudEntregasPendientes() {
        return solicitudEntregasPendientes;
    }
    public void setSolicitudEntregasPendientes(String solicitudEntregasPendientes) {
        this.solicitudEntregasPendientes = solicitudEntregasPendientes;
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
    public boolean isDevuelta() {
        return devuelta;
    }
    public void setDevuelta(boolean devuelta) {
        this.devuelta = devuelta;
    }
    public String getTipoItem() {
        return tipoItem;
    }
    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }
    public String getTipoBandeja() {
        return tipoBandeja;
    }
    public void setTipoBandeja(String tipoBandeja) {
        this.tipoBandeja = tipoBandeja;
    }
    public boolean isDevueltaLF() {
        return devueltaLF;
    }
    public void setDevueltaLF(boolean devueltaLF) {
        this.devueltaLF = devueltaLF;
    }
    public String getNumeroTutela() {
        return numeroTutela;
    }
    public void setNumeroTutela(String numeroTutela) {
        this.numeroTutela = numeroTutela;
    }
	public BandejaAltoCostoInsumoVO getInsumo() {
		return insumo;
	}
	public void setInsumo(BandejaAltoCostoInsumoVO insumo) {
		this.insumo = insumo;
	}
    
}
