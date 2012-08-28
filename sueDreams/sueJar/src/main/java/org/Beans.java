package org;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Beans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> lista1;

	public List<String> getLista1(){
		
		        
		        try {
		        	ManejadorEjemplo manejador= new ManejadorEjemplo();
		        	XMLReader reader = XMLReaderFactory.createXMLReader();  
		            // Añadimos nuestro manejador al reader  
		            reader.setContentHandler(manejador);      
		            // Procesamos el xml de ejemplo  
		            reader.parse(new InputSource(new URL(MenuUtil.getMenuXMLURL()).openStream()));  
		        
		            List<String[]> datos = manejador.getLista();
		          Document documento=MenuUtil.getDocumentMenu();
		         
		          

		        	/*NodeList nodeLista = documento.getElementsByTagName("MENU");
		       // 	NodeList nodeLista = documento.getElementsByTagName("NODO1").item(0);
		        	for (int s = 0; s < nodeLista.getLength(); s++) {

		        		Node primerNodo = nodeLista.item(s);
		        		String value2;
		        		String value;
		        		NodeList nodeLista2=primerNodo.getChildNodes();
		        		for (int i = 0; i < nodeLista2.getLength(); i++) {
		        			Node segNodo = nodeLista2.item(s);
		        			if (segNodo.getNodeType() == Node.ELEMENT_NODE) {
		        				Element segElemento = (Element) segNodo;
		        				NodeList segNombreElementoLista =
	        	                        segElemento.getElementsByTagName("value");
		        				
		        				Element segNombreElemento =
	        	                        (Element) segNombreElementoLista.item(0);
	        		NodeList segNombre = segNombreElemento.getChildNodes();
	        		value2 = ((Node) segNombre.item(0)).getNodeValue().toString();
	        		System.out.println("value2 : "  + value2);
		        				
		        				
		        			}
		        		}
		        		
		        		if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {

		        		Element primerElemento = (Element) primerNodo;

		        		NodeList primerNombreElementoLista =
		        	                        primerElemento.getElementsByTagName("value");
		        		Element primerNombreElemento =
		        	                        (Element) primerNombreElementoLista.item(0);
		        		NodeList primerNombre = primerNombreElemento.getChildNodes();
		        		value = ((Node) primerNombre.item(0)).getNodeValue().toString();
		        		System.out.println("value : "  + value);

		        		NodeList segundoNombreElementoLista =
		        	                        primerElemento.getElementsByTagName("autor");
		        		Element segundoNombreElemento =
		        	                        (Element) segundoNombreElementoLista.item(0);
		        		NodeList segundoNombre = segundoNombreElemento.getChildNodes();

		        		autor = ((Node) segundoNombre.item(0)).getNodeValue().toString();
		        		//System.out.println("Autor : "  + autor);

		        		NodeList tercerNombreElementoLista =
		        	                        primerElemento.getElementsByTagName("hits");
		        		Element tercerNombreElemento =
		        	                        (Element) tercerNombreElementoLista.item(0);
		        		NodeList tercerNombre = tercerNombreElemento.getChildNodes();
		        		hits = ((Node) tercerNombre.item(0)).getNodeValue().toString();
		        		System.out.println("Hits : "  + hits);

		        		}
		        	      }*/
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
