package com.conexia.saludcoop.web.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

public class BandejaSubItemProjVO {

    private final static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    private Long numeroItem;
    private Date fechaModificacion;
    private String estado;
    private Integer estadoId;
    private String tecnologia;

    private String tipoSolicitud;
    private String diagnostico;
    private Boolean esNivelAutorizacionAuditor;

    private Long idProcedimiento;
    private String procedimiento;
    private Long idMedicamento;
    private String medicamento;
    private Long idInsumo;
    private String insumo;


    public Long getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Long numeroItem) {
        this.numeroItem = numeroItem;
    }

    public String getFechaModificacion() {

        return BandejaSubItemProjVO.SDF.format(fechaModificacion);
    }

    public void setFechaModificacion(Date fechaModificacion) {

        this.fechaModificacion = fechaModificacion;
    }

    public String getEstado() {

        return estado;
    }

    public void setEstado(String estado) {

        this.estado = estado;
    }

    @JsonIgnore
    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public String getTecnologia() {

        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {

        this.tecnologia = tecnologia;
    }

    public String getTipoSolicitud() {

        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {

        this.tipoSolicitud = tipoSolicitud;
    }

    public String getServicioSolicitado() {
        if (this.medicamento != null) {
            return this.medicamento;
        } else if (this.procedimiento != null) {
            return this.procedimiento;
        } else if (this.insumo != null) {
            return this.insumo;
        } else {
            return null;
        }

    }

    public String getDiagnostico() {

        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {

        this.diagnostico = diagnostico;
    }

    /**
     * @param procedimiento
     *            the procedimiento to set
     */
    public void setProcedimiento(String procedimiento) {

        this.procedimiento = procedimiento;
    }

    /**
     * @param medicamento
     *            the medicamento to set
     */
    public void setMedicamento(String medicamento) {

        this.medicamento = medicamento;
    }

    public Boolean getEsNivelAutorizacionAuditor() {
        return esNivelAutorizacionAuditor;
    }

    public void setEsNivelAutorizacionAuditor(Boolean esNivelAutorizacionAuditor) {
        this.esNivelAutorizacionAuditor = esNivelAutorizacionAuditor;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    @JsonIgnore
    public Long getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Long idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    @JsonIgnore
    public Long getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    @JsonIgnore
    public Long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }

}
