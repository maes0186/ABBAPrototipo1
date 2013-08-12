package org.empresa.controller;

import java.io.Serializable;

import javax.ejb.Stateless;

@Stateless
public class BeanPrueba implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4923953413707667928L;
	
	public String returnTest(){
		return "hola";
	}

}
