package cliente.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import pruebaMario.Test2;
import pruebaMario.claseRemota;

import relationsEntities.Hijo;
import relationsEntities.Papa;
import server.entidades.Articulo;
import server.entidades.ArticuloException;
import server.entidades.ArticuloRemote;
import server.entidades.Test;
import server.entidades.TestException;
import server.entidades.TestInterface;
 class ClienteArticulo {
	public static void main(String[] args) throws TestException {
		try {
			Properties p=new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			p.put(Context.URL_PKG_PREFIXES,"org.jboss.naming.org.jnp.interfaces");
			p.put(Context.PROVIDER_URL,"jnp://localhost:1099");
			Context ctx=new InitialContext(p);

			/*Object ref = ctx.lookup("ArticuloBean/remote");
			ArticuloRemote mr = (ArticuloRemote) PortableRemoteObject.narrow(ref,
					ArticuloRemote.class);
			Articulo ar=new Articulo();
			ar.setArtnr(3);
			ar.setCantidad(1);
			ar.setDes("Des");
			ar.setPrecio(new BigDecimal(23));
			adsa
			mr.addArticulo(ar);
			Test test = new Test();
			test.setA(1);
			mr.addTest(test);
			*/
			/*Object ref = ctx.lookup("TestDao/remote");
			TestInterface testDao = (TestInterface) PortableRemoteObject.narrow(ref,
					TestInterface.class);
			Test test = new Test();
			test.setA(2);
			testDao.addTest2(test);
			///testDao.observar(test);
			//testDao.addTest(test);*/
			Object ref = ctx.lookup("entidadBean/remote");
			claseRemota cRemota = (claseRemota)PortableRemoteObject.narrow(ref,
					claseRemota.class);
			Test2 test2= new Test2();
			test2.setId("id");
			test2.setId2("test");
			
			
			Hijo hijo = new Hijo();
			hijo.setDescHijo("DescHijo");
			hijo.setIdHijo(1);
			hijo.setIdPapaFk(new Papa());
			Papa papa = new Papa();
			papa.setIdPapa(1);
			List<Hijo> hijos=new ArrayList<Hijo>();
			cRemota.loadPapa(papa);
			hijos=papa.getHijos();
		   // cRemota.addEntidad(hijo);
		//	hijo=cRemota.loadHijo(hijo);
		System.out.println(hijos.get(0).getDescHijo());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
