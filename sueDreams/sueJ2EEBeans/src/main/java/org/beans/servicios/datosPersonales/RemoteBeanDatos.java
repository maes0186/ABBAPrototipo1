package org.beans.servicios.datosPersonales;

import javax.ejb.Stateless;

import org.interfaces.servicios.datosPersonales.RemoteIntDatos;


@Stateless
public class RemoteBeanDatos implements RemoteIntDatos {

	public int cantidad(){
		return 5;
	}
}
