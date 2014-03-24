package org.empresa.negocio.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	public String mensaje(){
		List<String> mensajes=new ArrayList<String>();
		
		mensajes.add("Buenos Dias Princesa");
		mensajes.add("Te Amo mi Amor");
		mensajes.add("Eres lo mejor que me ha pasado");
		mensajes.add("Vamos a llegar juntos a uvas pasas");
		
		Integer valorDado = (int) (Math.random()*mensajes.size());
		return mensajes.get(valorDado);
	}

}
