package com.conexia.saludcoop.common.enumerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa la tabla estado_afiliacion
 * @author dcamarro
 *
 */
public enum EstadosAfiliacion {
	

	VIGENTES			    (1, "VIGENTES"),
	RETIRADOS				(2, "RETIRADOS"),
	SUSPENDIDO				(3, "SUSPENDIDO"),
	INSCRITO				(4, "INSCRITO"),
	CANCELACION				(5, "CANCELACION"),
	RECHAZADO				(6, "RECHAZADO"),
	DESAFILIADO				(7, "DESAFILIADO"),
	MULTIAFILIADO			(8, "MULTIAFILIADO");

	private Integer id;
	private String description;
	private static Map<Integer, EstadosAfiliacion> map = null;
	private static Map<String, EstadosAfiliacion> mapDescripcion = null;
	 
	static {
		EstadosAfiliacion[] estadosAfiliacion = EstadosAfiliacion.values();
		map = new HashMap<Integer, EstadosAfiliacion>();
		mapDescripcion = new HashMap<String, EstadosAfiliacion>();
		for (int i = 0; i < estadosAfiliacion.length; i++) {
			map.put(estadosAfiliacion[i].id, estadosAfiliacion[i]);
			mapDescripcion.put(estadosAfiliacion[i].description, estadosAfiliacion[i]);
		}
	}
	

	private EstadosAfiliacion(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	
	public static EstadosAfiliacion getEstadoAfiliacionById(Integer id) {
		return map.get(id);
	}
	
	public static EstadosAfiliacion getEstadoAfiliacionByDescripcion(String descripcion) {
		return mapDescripcion.get(descripcion);
	}
	/**
	 * Devuelve el valor de id.
	 *
	 * @return El valor de id.
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * Asigna un nuevo valor a id.
	 *
	 * @param id El valor a asignar a id.
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * Devuelve el valor de description.
	 *
	 * @return El valor de description.
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * Asigna un nuevo valor a description.
	 *
	 * @param description El valor a asignar a description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
