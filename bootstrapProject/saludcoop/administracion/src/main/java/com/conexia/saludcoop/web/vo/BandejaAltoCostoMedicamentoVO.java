package com.conexia.saludcoop.web.vo;

/**
 * <b>Bandeja auditor alto costo</b> 
 * Clase usada para mostrar los datos del medicamento a autorizar en la bandeja de alto costo
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 15/10/2013
 * 
 */
public class BandejaAltoCostoMedicamentoVO {
    private String codigo;
    private String nombre;
    private Integer cantidadSolicitada;
    private Integer dosis;
    private Integer frecuencia;
    private String tipoFrecuencia;
    private Integer duracionTratamiento;
    private String posologia;
    private String viaAdministracion;
    private String viaAdministracionId;
    
    public Integer getDosis() {
        return dosis;
    }
    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }
    public Integer getFrecuencia() {
        return frecuencia;
    }
    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }
    public String getPosologia() {
        return posologia;
    }
    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }
    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }
    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }
    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }
	public String getViaAdministracion() {
		return viaAdministracion;
	}
	public void setViaAdministracion(String viaAdministracion) {
		this.viaAdministracion = viaAdministracion;
	}
    public String getTipoFrecuencia() {
        return tipoFrecuencia;
    }
    public void setTipoFrecuencia(String tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }
    public String getViaAdministracionId() {
        return viaAdministracionId;
    }
    public void setViaAdministracionId(String viaAdministracionId) {
        this.viaAdministracionId = viaAdministracionId;
    }
        
}
