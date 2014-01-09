/**
 * 
 */
package com.conexia.saludcoop.security;


/**
 * @author gchiesa
 *
 */
public class TipoUsuarioDto {

	private String codigo;
	private String descripcion;
	private Long id;
	
    
    /**
     * Devuelve el valor de id.
     *
     * @return El valor de id.
     */
    public Long getId() {
    
    	return this.id;
    }

	/**
     * Devuelve el valor de codigo.
     *
     * @return El valor de codigo.
     */
    public String getCodigo() {
    
    	return this.codigo;
    }
	
    /**
     * Asigna un nuevo valor a codigo.
     *
     * @param codigo El valor a asignar a codigo.
     */
    public void setCodigo(String codigo) {
    
    	this.codigo = codigo;
    }
	
    /**
     * Devuelve el valor de descripcion.
     *
     * @return El valor de descripcion.
     */
    public String getDescripcion() {
    
    	return this.descripcion;
    }
	
    /**
     * Asigna un nuevo valor a descripcion.
     *
     * @param descripcion El valor a asignar a descripcion.
     */
    public void setDescripcion(String descripcion) {
    
    	this.descripcion = descripcion;
    }

	/**
     * @param id
     */
    public void setId(Long id) {
    	this.id = id;
    }
	
	
	
}
