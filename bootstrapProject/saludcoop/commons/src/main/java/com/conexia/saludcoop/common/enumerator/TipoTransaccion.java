package com.conexia.saludcoop.common.enumerator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mcuervo
 *
 */
public enum TipoTransaccion {

	COMPROBACION_DERECHOS			(1, "COMPROBACION_DERECHOS"),
	
	SOLICITUD_AUTORIZACION			(2, "SOLICITUD_AUTORIZACION"),
	
	/**
	 * Tipo de transacción para la autorización de auditores CTC
	 */
	AUTORIZACION_CTC				(3, "AUTORIZACION_CTC"),
	
	CONSUMIR_SOLICITUD_ITEM			(4, "CONSUMIR_SOLICITUD_ITEM"),

	DEVOLVER_SOLICITUD_ITEM			(5, "DEVOLVER_SOLICITUD_ITEM"),

    /**
     * Tipo de transacción para la autorización de auditores Alto Costo
     */
    AUTORIZACION_AC                 (6, "AUTORIZACION_AC"),

    /**
     * Tipo de transacción para la autorización desde la bandeja de contact service
     */
    AUTORIZACION_CS                 (7, "AUTORIZACION_CS"),
	/**
	 * Tipo de transaccion para obtener informacion de la autorizacion del item
	 */
	OBTENER_AUTORIZACION			(8, "OBTENER_AUTORIZACION"),
	/**
	 * Tipo de transaccion para redireccionar la ips del item
	 */
	REDIRECCION_IPS_ITEM			(9, "REDIRECCION_IPS_ITEM"),
	/**
	 * Tipo de transaccion para anular la ips del item
	 */
	ANULAR_IPS_ITEM			(10, "ANULAR_IPS_ITEM"),
	/**
	 * Tipo Transaccion para el top 10 de ips
	 */
	CONSULTAR_TOP_TEN_SEDES_IPS_REDIRECCIONAMIENTO			(11, "CONSULTAR_TOP_TEN_SEDES_IPS_REDIRECCIONAMIENTO"),

    /**
     * Tipo de transacción para las operaciones realizadas por el auditor de tutelas
     */
	GESTIONAR_ITEM_TUTELA                 (12, "GESTIONAR_ITEM_TUTELA"),
	
    /**
     * Tipo de transacción para realizar la actualizacion de los datos de contacto del afiliado.
     */
	ACTUALIZAR_DATOS_CONTACTO_AFILIADO (13, "ACTUALIZAR_DATOS_CONTACTO_AFILIADO"), 
    /**
     * Tipo de transacción para la autorización desde la bandeja de de auditores especializados
     */
    AUTORIZACION_BANDEJA_ESPECIALIZADA (14, "AUTORIZACION_BANDEJA_ESPECIALIZADA"),
	
	/**
	 * Tipo de transacción para obtener el TARGET al que debe escalarse la autorización desde LDF
	 */
	OBTENER_ROLE_ESCALAMIENTO (15, "OBTENER_ROLE_ESCALAMIENTO"),
	
	/**
	 * Tipo de transacción para validar el tope de un elemento dado
	 */
	VALIDAR_TOPE_CANTIDAD (16, "VALIDAR_TOPE_CANTIDAD");
	
	private Integer id;
	private String description;
	private static Map<Integer, TipoTransaccion> map = null;
	 
	static {
		TipoTransaccion[] parentezcos = TipoTransaccion.values();
		map = new HashMap<Integer, TipoTransaccion>();
		for (int i = 0; i < parentezcos.length; i++) {
			map.put(parentezcos[i].id, parentezcos[i]);
		}
	}

	private TipoTransaccion(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	
	public static TipoTransaccion getTipoTransaccionById(Integer id) {
		return map.get(id);
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
