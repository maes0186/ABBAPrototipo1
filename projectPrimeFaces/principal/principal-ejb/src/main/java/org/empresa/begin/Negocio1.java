package org.empresa.begin;

import java.io.Serializable;

import javax.ejb.Stateless;

@Stateless
public class Negocio1 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7670926722272271323L;

	public int suma(int a,int b ){
		return a+b;
	}

}
