package client.cap02;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.naming.*;

import server.cap02.PrimerBeanRemote;

public class PrimerBean {
public static void main(String[] args){
	
	try{
		Properties p=new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		p.put(Context.URL_PKG_PREFIXES,"org.jboss.naming.org.jnp.interfaces");
		p.put(Context.PROVIDER_URL,"jnp://localhost:1099");
		Context ctx=new InitialContext(p);
		Object ref=ctx.lookup("PrimerBean/remote");
		PrimerBeanRemote ebr=(PrimerBeanRemote) PortableRemoteObject.narrow(ref, PrimerBeanRemote.class);
		System.out.println("5 + 8 ="+ebr.adieren(5, 8));
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
}
