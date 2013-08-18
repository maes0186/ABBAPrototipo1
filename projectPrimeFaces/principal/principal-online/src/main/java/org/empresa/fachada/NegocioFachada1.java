package org.empresa.fachada;

import javax.ejb.EJB;

import org.empresa.begin.Negocio1;


public class NegocioFachada1 {

	@EJB
	Negocio1 negocio1;
	
	
	public int suma(int a,int b){
		return negocio1.suma(a, b);
	}
	
}
