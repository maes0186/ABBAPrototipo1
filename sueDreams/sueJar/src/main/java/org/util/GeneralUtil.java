package org.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GeneralUtil {

	private static String URL_SEPARATOR = "/";
	private static String TWO_POINTS = ":";
//Clase para cargar el root de la aplicacion de faces
	public static String rootUrl() {
		ExternalContext ec = (FacesContext.getCurrentInstance())
				.getExternalContext();
		String server = ec.getRequestServerName();
		int port = ec.getRequestServerPort();
		String servletPath = ec.getRequestServletPath();
		String webApp = ec.getRequestContextPath();
		String httpProtocol = ec.getRequestScheme();
		String rootURL = httpProtocol + TWO_POINTS + URL_SEPARATOR
				+ URL_SEPARATOR + server + TWO_POINTS + port + webApp
				+ servletPath + URL_SEPARATOR;
		return rootURL;
	}
	
	public static Properties loadProperties(String direccion){
		try{
		Properties defaultProps = new Properties();
		defaultProps.load(GeneralUtil.class.getResourceAsStream(direccion));
		return defaultProps;
		//String fileMenu=defaultProps.getProperty(PROPERTY_NAME_MENU_XML);
		//in.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	public static Document getDocumentXML(String adress) throws IOException, ParserConfigurationException, SAXException{
    	URL url=
    	    new URL(adress);
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
    	return documento;
		
	}
	
}
