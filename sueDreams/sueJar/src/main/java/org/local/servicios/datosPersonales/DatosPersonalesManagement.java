package org.local.servicios.datosPersonales;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.interfaces.servicios.datosPersonales.RemoteIntDatos;
import org.paginas.criteria.IngresoDatos;
import org.util.GeneralUtil;
import org.util.J2EEUtil;


@SuppressWarnings("serial")
@SessionScoped @Named("datosPersonalesManagement")
public class DatosPersonalesManagement implements Serializable {

	public String getProcesar(IngresoDatos ingresoDatos){
		String sal="processInDatos";
		return sal;
	}
	
	public int getCantidad() throws NamingException, IOException{
			Object ref=J2EEUtil.readEJB("RemoteBeanDatos/remote");
			RemoteIntDatos ebr=(RemoteIntDatos) PortableRemoteObject.narrow(ref, RemoteIntDatos.class);
			return ebr.cantidad();
		
	}
	
}
