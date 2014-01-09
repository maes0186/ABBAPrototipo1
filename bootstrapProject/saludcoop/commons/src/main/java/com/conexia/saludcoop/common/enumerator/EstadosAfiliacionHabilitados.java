package com.conexia.saludcoop.common.enumerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa la tabla estado_afiliacion
 * @author dcamarro
 *
 */
public enum EstadosAfiliacionHabilitados {
	

	VIGENTES			    (1, "VIGENTES"),
	INSCRITO				(4, "INSCRITO");

	private Integer id;
	private String description;
	private static Map<Integer, EstadosAfiliacionHabilitados> map = null;
	private static Map<String, EstadosAfiliacionHabilitados> mapDescripcion = null;
	 
	static {
		EstadosAfiliacionHabilitados[] parentezcos = EstadosAfiliacionHabilitados.values();
		map = new HashMap<Integer, EstadosAfiliacionHabilitados>();
		mapDescripcion = new HashMap<String, EstadosAfiliacionHabilitados>();
		
		for (int i = 0; i < parentezcos.length; i++) {
			map.put(parentezcos[i].id, parentezcos[i]);
			mapDescripcion.put(parentezcos[i].description, parentezcos[i]);
		}
	}
	

	private EstadosAfiliacionHabilitados(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	
	public static EstadosAfiliacionHabilitados getEstadoAfiliacionById(Integer id) {
		return map.get(id);
	}
	
	public static EstadosAfiliacionHabilitados getEstadoAfiliacionByDescripcion(String descripcion) {
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
