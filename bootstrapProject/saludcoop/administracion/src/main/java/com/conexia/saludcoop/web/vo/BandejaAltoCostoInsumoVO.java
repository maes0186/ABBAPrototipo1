package com.conexia.saludcoop.web.vo;

/**
 * <b>Bandeja auditor alto costo</b> 
 * Clase usada para mostrar los datos del medicamento a autorizar en la bandeja de alto costo
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 15/10/2013
 * 
 */
public class BandejaAltoCostoInsumoVO {
    private String codigo;
    private String nombre;
    private Integer cantidadSolicitada;
    private Integer duracionTratamiento;
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
    
        
}
