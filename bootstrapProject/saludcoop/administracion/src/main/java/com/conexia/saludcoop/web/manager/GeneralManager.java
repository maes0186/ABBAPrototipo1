package com.conexia.saludcoop.web.manager;

public abstract class GeneralManager {
	protected String getLikeParameter(String parameter){
		String var = parameter;
		if(var== null){
			var="";
		}
		var = "%" + var.replaceAll("\\s","") + "%";
		
		return var;
	}
}
