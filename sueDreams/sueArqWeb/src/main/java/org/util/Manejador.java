package org.util;

import java.util.ArrayList;

import org.constantes.MenuConstantes;
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
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		if (name.equalsIgnoreCase(MenuConstantes.PADRE_ETIQUETA_XML)) {
			nodo.setTipo(name);

		} else {
			anterior = actual;
			actual = new Nodo();
			actual.setTipo(name);
			for (int i = 0; i < attributes.getLength(); i++) {
				String nombre = attributes.getQName(i);
				String valor = attributes.getValue(i);
				if (nombre.equalsIgnoreCase(MenuConstantes.NOMBRE_ETIQUETA))
					actual.setName(valor);
				else if (nombre.equalsIgnoreCase(MenuConstantes.VALOR_ETIQUETA))
					actual.setValue(valor);
				else if (nombre.equalsIgnoreCase(MenuConstantes.VALOR_ID))
					actual.setId(valor);
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
			if (cad.equalsIgnoreCase(MenuConstantes.PADRE_ETIQUETA_XML))
				return 10;
			int dist=cad.length();
			int num = Integer.valueOf(cad.substring(dist-1, dist));
			return num;
		} catch (Exception e) {
			return -1;
		}
	}

}