package org.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLManager {

	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document doc;
	String string_xml;

	static String dato = "contenido";

	public void procesarXML() {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			string_xml = MenuUtil.getMenuXMLURL();
			doc = MenuUtil.getDocumentMenu();
			recorrerXML(doc.getElementsByTagName("padre"), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * piso = 0 -> nodo padrepiso = 1 -> nodo hijopiso = 2 -> nodo nietopiso = n
	 * -> asi sucesivamente
	 */
	private void recorrerXML(NodeList lst, int piso) {
		if (lst != null) {
			for (int i = 0; i < lst.getLength(); i++) {
				obtenerDatos(lst.item(i), XMLManager.dato, piso);
				// generarContenido( lst.item( i ).getChildNodes() , piso+1 );
			}
		}
	}

	private void obtenerDatos(Node n, String dato, int piso) {
		NamedNodeMap atributos = n.getAttributes();
		Node aux = atributos.getNamedItem(dato);

		// este codigo es solo para mostrar ordenada la informacion
		String tabs = "";
		for (int i = 0; i < piso; i++)
			tabs += "\n";
		if (aux != null)
			System.out.println(tabs + aux.getNodeValue() + "  -  " + piso);
		else
			System.out.println(tabs + " null " + piso);
	}

}