package cliente.entidades;

import java.math.BigDecimal;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import server.entidades.Articulo;
import server.entidades.ArticuloException;
import server.entidades.ArticuloRemote;

public class ClienteArticulo {
	public static void main(String[] args) {
		try {
			Properties p=new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			p.put(Context.URL_PKG_PREFIXES,"org.jboss.naming.org.jnp.interfaces");
			p.put(Context.PROVIDER_URL,"jnp://localhost:1099");
			Context ctx=new InitialContext(p);

			Object ref = ctx.lookup("ArticuloBean/remote");
			ArticuloRemote mr = (ArticuloRemote) PortableRemoteObject.narrow(ref,
					ArticuloRemote.class);
			Articulo ar=new Articulo();
			ar.setArtnr(2);
			ar.setCantidad(1);
			ar.setDes("Des");
			ar.setPrecio(new BigDecimal(23));
			
			mr.addArticulo(ar);
			

	
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ArticuloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
