package org.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.constantes.MenuConstantes;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class MenuUtil {

	public static String getMenuXMLURL() {
		String menuFileString = MenuConstantes.PROPERTY_NAME_MENU_XML;
		String rootApp = GeneralUtil.rootUrl();
		String menuXMLURL = rootApp + menuFileString;
		return menuXMLURL;
	}
	

	public static Document getDocumentMenu() throws IOException,
			ParserConfigurationException, SAXException {
		Document documento = GeneralUtil.getDocumentXML(getMenuXMLURL());
		return documento;
	}

	public static Nodo getXMLMapeado() throws SAXException,
			MalformedURLException, IOException {
		Nodo datosMenu = new Nodo();
		Manejador manejador = new Manejador();
		XMLReader reader = XMLReaderFactory.createXMLReader();
		// Añadimos nuestro manejador al reader
		reader.setContentHandler(manejador);
		// Procesamos el xml
		reader.parse(new InputSource(new URL(MenuUtil.getMenuXMLURL())
				.openStream()));
		datosMenu = manejador.getSalida();
		return datosMenu;

	}

}
