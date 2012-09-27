package org.local.servicios.datosPersonales;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.interfaces.servicios.datosPersonales.RemoteIntDatos;


public class DatosPersonalesManagement {

	public int getCantidad() throws NamingException{

			Properties p=new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			p.put(Context.URL_PKG_PREFIXES,"org.jboss.naming.org.jnp.interfaces");
			p.put(Context.PROVIDER_URL,"jnp://localhost:1199");
			Context ctx=new InitialContext(p);
			Object ref=ctx.lookup("RemoteBeanDatos/remote");
			RemoteIntDatos ebr=(RemoteIntDatos) PortableRemoteObject.narrow(ref, RemoteIntDatos.class);
			return ebr.cantidad();
		
	}
	
}
