package org.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.Properties;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.constantes.GeneralConstantes;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GeneralUtil {

	/**
	 *  Clase para cargar el root de la aplicacion de faces
	 * @return
	 */
	public static String rootUrl() {
		ExternalContext ec = (FacesContext.getCurrentInstance())
				.getExternalContext();
		String server = ec.getRequestServerName();
		int port = ec.getRequestServerPort();
		String webApp = ec.getRequestContextPath();
		String httpProtocol = ec.getRequestScheme();
		String rootURL = httpProtocol + GeneralConstantes.TWO_POINTS
				+ GeneralConstantes.URL_SEPARATOR
				+ GeneralConstantes.URL_SEPARATOR + server
				+ GeneralConstantes.TWO_POINTS + port + webApp
				+ GeneralConstantes.URL_SEPARATOR;
		// + servletPath + URL_SEPARATOR
		return rootURL;
	}
/**
 * Clase que carga properties
 * @param direccion
 * @return
 * @throws IOException
 */

	public static Properties loadProperties(String direccion)
			throws IOException {

		Properties defaultProps = new Properties();
		defaultProps.load(GeneralUtil.class.getResourceAsStream(direccion));
		return defaultProps;
		// String fileMenu=defaultProps.getProperty(PROPERTY_NAME_MENU_XML);
		// in.close();

	}
   /**
    * Clase generadora de un dcumento
    * en XML
    * @param adress
    * @return
    * @throws IOException
    * @throws ParserConfigurationException
    * @throws SAXException
    */
	public static Document getDocumentXML(String adress) throws IOException,
			ParserConfigurationException, SAXException {
		URL url = new URL(adress);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String entrada;
		String cadena = "";

		while ((entrada = br.readLine()) != null) {
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
