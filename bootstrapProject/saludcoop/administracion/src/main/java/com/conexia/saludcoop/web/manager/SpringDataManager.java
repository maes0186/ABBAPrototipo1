package com.conexia.saludcoop.web.manager;

import java.util.List;

public abstract class SpringDataManager<ENT,DTO> {
	
	public abstract DTO findOne(Long id);
	public abstract List<DTO> findAll();
	

	public abstract List<DTO> toDtos(Iterable<ENT> entidades);
	public String getNullable(String param){
		if(param.isEmpty()){
			return null;
		}else{
			return param;
		}
	}
	public String getLikeParameter(String parameter){
		String var = parameter;
		if(var== null){
			var="";
		}
		var = "%" + var + "%";
		
		return var;
	}
}
