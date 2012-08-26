package org.util;

import java.io.FileInputStream;
import java.util.Properties;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class generalUtil {

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
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		defaultProps.load(classLoader.getResourceAsStream("/propiedades.menu"));
		FileInputStream in = new FileInputStream("propiedades/menu.properties");
		defaultProps.load(in);
		return defaultProps;
		//String fileMenu=defaultProps.getProperty(PROPERTY_NAME_MENU_XML);
		//in.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
