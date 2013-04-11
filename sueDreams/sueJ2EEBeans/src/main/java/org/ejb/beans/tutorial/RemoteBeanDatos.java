package org.ejb.beans.tutorial;

import java.io.Serializable;

import javax.ejb.Stateless;



@Stateless
public class RemoteBeanDatos implements RemoteIntDatos,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int cantidad(){
		return 5;
	}
}
