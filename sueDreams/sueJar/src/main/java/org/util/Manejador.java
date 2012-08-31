package org.util;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Manejador de eventos SAX 
 * 
 * @author Mario Ortega
 * 
 */
public class Manejador extends DefaultHandler {

	private Nodo nodo;
	private Nodo anterior;
	private Nodo actual;

	public Nodo getSalida() {
		return salida;
	}

	public void setSalida(Nodo salida) {
		this.salida = salida;
	}

	private Nodo salida;
	private int ini = 0;

	@Override
	public void startDocument() throws SAXException {
		nodo = new Nodo();
		nodo.setChilds(new ArrayList<Nodo>());
		actual = new Nodo();
		anterior = new Nodo();
		salida = nodo;
	}

	@Override
	public void endDocument() throws SAXException {
		int test=9;
		test=test+8;
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		if (name == "config") {
			nodo.setTipo(name);

		} else {
			anterior = actual;
			actual = new Nodo();
			actual.setTipo(name);
			for (int i = 0; i < attributes.getLength(); i++) {
				String nombre = attributes.getQName(i);
				String valor = attributes.getValue(i);
				if (nombre.equalsIgnoreCase("name"))
					actual.setName(nombre);
				else if (nombre.equalsIgnoreCase("action"))
					actual.setValue(valor);
			}

			if (actual.getTipo().equalsIgnoreCase(anterior.getTipo())
					|| anterior.getTipo() == null) {
				if (anterior.getTipo() != null) {
					nodo.getChilds().add(anterior);
					anterior.setPadre(nodo);
				}
			} else {
				int diferencia = dif(actual, anterior);
				if (diferencia >= 0) {

					if (ini == 0)
						nodo.getChilds().add(anterior);
					ini++;
					anterior.setPadre(nodo);
					nodo = anterior;
					anterior = new Nodo();
					nodo.setChilds(new ArrayList<Nodo>());
					nodo.getChilds().add(actual);
					actual.setPadre(nodo);
				} else {
					actual.setPadre(nodo);
				}

			}
		}

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		nodo = actual.getPadre();
		actual = nodo;
		anterior = new Nodo();
		ini = 0;

	}

	public int dif(Nodo a, Nodo b) {
		int uno = num(a.getTipo());
		int dos = num(b.getTipo());
		return uno - dos;
	}

	public int num(String cad) {
		try {
			if (cad == null)
				return -1;
			if (cad.equalsIgnoreCase("config"))
				return 10;
			int num = Integer.valueOf(cad.substring(4, 5));
			return num;
		} catch (Exception e) {
			return -1;
		}
	}

}