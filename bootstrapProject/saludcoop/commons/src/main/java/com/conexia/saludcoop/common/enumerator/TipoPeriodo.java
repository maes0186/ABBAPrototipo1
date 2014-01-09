package com.conexia.saludcoop.common.enumerator;

import java.util.HashMap;
import java.util.Map;

public enum TipoPeriodo {

	DIA(1, "Día"),
	SEMANA(2, "Semana"),
	MES(2, "Mes"),
	ANIO(2, "Año");
	
	private Integer id;
	private String description;
	private static Map<Integer, TipoPeriodo> map;
	private static Map<String, TipoPeriodo> mapDescripcion;
	
	TipoPeriodo(Integer id, String description){
		this.id = id;
		this.description = description;
	}
	
	static {
		TipoPeriodo[] tiposPeriodo = TipoPeriodo.values();
		map = new HashMap<Integer, TipoPeriodo>();
		mapDescripcion = new HashMap<String, TipoPeriodo>();
		for (int i = 0; i < tiposPeriodo.length; i++) {
			map.put(tiposPeriodo[i].id, tiposPeriodo[i]);
			mapDescripcion.put(tiposPeriodo[i].description, tiposPeriodo[i]);
		}
	}
	
	public static TipoPeriodo getEstadoAfiliacionById(Integer id) {
		return map.get(id);
	}
	
	public static TipoPeriodo getEstadoAfiliacionByDescripcion(String descripcion) {
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
