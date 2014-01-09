package com.conexia.saludcoop.web.manager.exceptions;

public class NoEncontradoException extends Exception {

	public NoEncontradoException(){
		super();
	}
	
	public NoEncontradoException(Long id){
		super("No se encontró la entidad con id "+id.toString());
	}
}
