package org.local.servicios.datosPersonales;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.ejb.beans.tutorial.RemoteIntDatos;
import org.paginas.criteria.IngresoDatos;
import org.util.J2EEUtil;


@SuppressWarnings("serial")
@SessionScoped @Named("datosPersonalesManagement")
public class DatosPersonalesManagement implements Serializable {
	public String getProcesar(IngresoDatos ingresoDatos){
		String sal="processInDatos";
		return sal;
	}
	
	public int getCantidad() throws NamingException, IOException{
		String prefix = "java:comp/env/";
		String ejbRefName = "ejb/myBean";
		Object ref=J2EEUtil.readEJB(prefix+ejbRefName);
		RemoteIntDatos ebr=(RemoteIntDatos) PortableRemoteObject.narrow(ref, RemoteIntDatos.class);
		
		return ebr.cantidad();
		
		
	}
	
}
