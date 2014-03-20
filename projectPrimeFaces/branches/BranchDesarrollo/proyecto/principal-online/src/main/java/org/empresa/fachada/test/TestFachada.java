package org.empresa.fachada.test;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.empresa.negocio.test.Negocio1;

@ManagedBean
@SessionScoped
public class TestFachada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	Negocio1 negocio1;
	public String mensaje(){
		return negocio1.mensaje();
	}
	
	public int suma(int a,int b){
		return negocio1.suma(a, b);
	}
	
}
