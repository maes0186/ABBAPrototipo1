package com.conexia.saludcoop.common.util.beanutils;


/**
 * @author ebarbin
 *
 */
public enum MailContentToken {

	NOMBRE_COMPLETO_PACIENTE("[NOMBRE COMPLETO PACIENTE]"),
	
	TIPO_DOCUMENTO("[TIPO DOCUMENTO]"),
	
	NUMERO_DOCUMENTO("[NUMERO DOCUMENTO]"),
	
	CUPS_NOMBRE_PROCEDIMIENTO("[CUPS NOMBRE PROCEDIMIENTO]"),
	
	FECHA_ACTUAL("[dd/mm/aaaa]"),
	
	NOMBRE_IPS("[NOMBRE IPS]"),
	
	NOMBRE_MEDICO("[NOMBRE MEDICO]");
			 
	private String codigo;
	
	private MailContentToken(String codigo) {
		this.codigo = codigo;
	}

    public String getCodigo() {
    	return codigo;
    }
}
