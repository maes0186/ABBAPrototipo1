package com.conexia.saludcoop;


public class Transaccion {

	
	public enum TIPO_TRANSACCION {
		
		COMPROBACION_DERECHOS("COMPROBACION_DERECHOS", "Comprobación de derechos");
		
		
		
		/*
		 * 
		 * Agregar nuevas transaccion simplemente copiando un enumerado, y cambiando sus datos.
		 * 
		 */
		
		private String sequencedTextReference = "";
		private String description  = "";
		
		private TIPO_TRANSACCION (String sequenceTextReference, 
								  String description) {
			
			this.sequencedTextReference = sequenceTextReference;
			this.description = description;
		}
		

		public String getSequencedTextReference() {
			return this.sequencedTextReference;
		}
		
		
		public String getDescription() {
			return  this.description;	
		}
	}
	
}
