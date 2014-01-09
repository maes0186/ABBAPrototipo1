package com.conexia.saludcoop.validador.util;

import java.util.ResourceBundle;

//@Component
//@Scope("singleton")
public class StringMessageProperties {

	private ResourceBundle rb;
	
	public StringMessageProperties() {
		this.rb = ResourceBundle.getBundle("messages");
	}


	public String getByName(String string) {
		
		return this.rb.getString(string);
		
	}



}
