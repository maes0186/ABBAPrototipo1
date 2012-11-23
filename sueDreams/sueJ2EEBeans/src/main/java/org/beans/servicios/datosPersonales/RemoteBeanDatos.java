package org.beans.servicios.datosPersonales;

import java.io.Serializable;

import javax.ejb.Stateless;

import org.interfaces.servicios.datosPersonales.RemoteIntDatos;


//@Stateless
public class RemoteBeanDatos implements RemoteIntDatos,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int cantidad(){
		return 5;
	}
}
