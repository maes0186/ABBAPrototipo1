package org;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.util.*;
import org.xml.sax.SAXException;

public class Beans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Nodo allMapping;

	public Nodo getAllMapping() throws MalformedURLException, SAXException, IOException {
		if(allMapping==null)allMapping= getXMLNode();
		return allMapping;
	}

	public void setAllMapping(Nodo allMapping) {
		this.allMapping = allMapping;
	}

	private Nodo getXMLNode() throws MalformedURLException, SAXException, IOException {
		Nodo mapping = new Nodo();
		mapping = MenuUtil.getXMLMapeado();
		return mapping;
	}



	public String getRequestURL() {
		Object request = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request instanceof HttpServletRequest) {
			return ((HttpServletRequest) request).getRequestURL().toString();
		} else {
			return "";
		}
	}

}
