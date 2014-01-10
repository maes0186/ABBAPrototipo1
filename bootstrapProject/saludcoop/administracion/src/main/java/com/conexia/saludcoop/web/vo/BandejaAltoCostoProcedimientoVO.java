package com.conexia.saludcoop.web.vo;

/**
 * <b>Bandeja auditor alto costo</b> 
 * Clase usada para mostrar los datos del procedimiento a autorizar en la bandeja de alto costo
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 15/10/2013
 * 
 */
public class BandejaAltoCostoProcedimientoVO {
    private String codigo;
    private String nombre;
    private Integer cantidadSolicitada;
    private String lateralidad;
    private String ipsRemitente;
    
    public String getLateralidad() {
        return lateralidad;
    }
    public void setLateralidad(String lateralidad) {
        this.lateralidad = lateralidad;
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
    public String getIpsRemitente() {
        return ipsRemitente;
    }
    public void setIpsRemitente(String ipsRemitente) {
        this.ipsRemitente = ipsRemitente;
    }
}
