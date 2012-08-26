package org;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.util.menuUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Beans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private List<String> lista1;

	public List<String> getLista1(){
		
		        
		        try {
		        	String menuXML = menuUtil.getMenuXMLURL();			
		        	URL url=
		        	    new URL(menuXML);
		        	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		        	String entrada;
		        	String cadena="";

		        	while ((entrada = br.readLine()) != null){
		        		cadena = cadena + entrada;
		        	}

		        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		        	DocumentBuilder db = dbf.newDocumentBuilder();

		        	InputSource archivo = new InputSource();
		        	archivo.setCharacterStream(new StringReader(cadena)); 

		        	Document documento = db.parse(archivo);
		        	documento.getDocumentElement().normalize();

		        	NodeList nodeLista = documento.getElementsByTagName("descarga");
		        	System.out.println("Informacion de los libros");
		        	
		        	for (int s = 0; s < nodeLista.getLength(); s++) {

		        		Node primerNodo = nodeLista.item(s);
		        		String titulo;
		        		String autor;
		        		String hits;

		        		if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {

		        		Element primerElemento = (Element) primerNodo;

		        		NodeList primerNombreElementoLista =
		        	                        primerElemento.getElementsByTagName("titulo");
		        		Element primerNombreElemento =
		        	                        (Element) primerNombreElementoLista.item(0);
		        		NodeList primerNombre = primerNombreElemento.getChildNodes();
		        		titulo = ((Node) primerNombre.item(0)).getNodeValue().toString();
		        		System.out.println("Titulo : "  + titulo);

		        		NodeList segundoNombreElementoLista =
		        	                        primerElemento.getElementsByTagName("autor");
		        		Element segundoNombreElemento =
		        	                        (Element) segundoNombreElementoLista.item(0);
		        		NodeList segundoNombre = segundoNombreElemento.getChildNodes();

		        		autor = ((Node) segundoNombre.item(0)).getNodeValue().toString();
		        		System.out.println("Autor : "  + autor);

		        		NodeList tercerNombreElementoLista =
		        	                        primerElemento.getElementsByTagName("hits");
		        		Element tercerNombreElemento =
		        	                        (Element) tercerNombreElementoLista.item(0);
		        		NodeList tercerNombre = tercerNombreElemento.getChildNodes();
		        		hits = ((Node) tercerNombre.item(0)).getNodeValue().toString();
		        		System.out.println("Hits : "  + hits);

		        		}
		        	      }
		        	  }
		        	  catch (Exception e) {
		        	    	e.printStackTrace();
		        	  }
		        if(lista1==null){
			lista1=new ArrayList<String>();
			lista1.add("campo1");
			lista1.add("campo2");
		        
		    }
		return lista1;
	}

	public void setLista1(List<String> lista1) {
		this.lista1 = lista1;
	}

	public String getNombre() {
		return nombre+"Prueba";
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRequestURL()
	{
	    Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    if(request instanceof HttpServletRequest)
	    {
	            return ((HttpServletRequest) request).getRequestURL().toString();
	    }else
	    {
	        return "";
	    }
	}

}
