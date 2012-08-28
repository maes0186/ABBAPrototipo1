package org.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler;  
  
/** 
 * Manejador de eventos SAX de ejemplo. 
 *  
 * @author Xela 
 * 
 */  
public class ManejadorEjemplo extends DefaultHandler{  
	
	List<String[]> lista=new ArrayList<String[]>();
public List<String[]> getLista() {
	return lista;
}

public void setLista(List<String[]> lista) {
	this.lista = lista;
}

String[] valores;
int i;
  
   @Override  
   public void startDocument() throws SAXException {
      System.out.println("\nPrincipio del documento...");  
      i=0;
   }  
  
   @Override  
   public void endDocument() throws SAXException {  
      System.out.println("\nFin del documento...");  
   }  
  
   @Override  
   public void startElement(String uri, String localName, String name,  
         Attributes attributes) throws SAXException {  
	   valores=new String[2];
      System.out.println("\nProcesando etiqueta...");  
      System.out.println("\tNamespace uri: "+uri);  
      System.out.println("\tNombre: "+localName);  
      System.out.println("\tNombre con prefijo: "+name);  
        
      //Recorremos los atributos  
      System.out.println("\tProcesando "+attributes.getLength()+" atributos...");  
      for(int i=0;i<attributes.getLength();i++){  
         System.out.println("\t\tNombre: "+attributes.getQName(i));  
         System.out.println("\t\tValor: "+attributes.getValue(i));  
      }  
        
      // También podemos obtener los atributos por nombre  
      valores[0] = attributes.getValue("value");
      valores[1] = attributes.getValue("action");
      lista.add(valores);
     // if(valorId!=null){  
     //    System.out.println("\tId: "+valorId);  
     // }  
  
   }  
     
   @Override  
   public void characters(char[] ch, int start, int length)  
         throws SAXException {  
      System.out.println("\nProcesando texto dentro de una etiqueta... ");  
      System.out.println("\tTexto: "+String.valueOf(ch, start, length));  
   }  
  
   @Override  
   public void endElement(String uri, String localName, String name)  
         throws SAXException {  
      System.out.println("\nFin de etiqueta...");  
      System.out.println("\tNamespace uri: "+uri);  
      System.out.println("\tNombre: "+localName);  
      System.out.println("\tNombre con prefijo: "+name);  
   }  
  
}  