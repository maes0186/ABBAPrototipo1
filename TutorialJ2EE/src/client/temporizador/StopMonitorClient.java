package client.temporizador;

import java.util.Properties;

import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import server.temporizador.*;

public class StopMonitorClient {

	public static void main(String[] args) {
		try {
			Properties p=new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			p.put(Context.URL_PKG_PREFIXES,"org.jboss.naming.org.jnp.interfaces");
			p.put(Context.PROVIDER_URL,"jnp://localhost:1099");
			Context ctx=new InitialContext(p);

			Object ref = ctx.lookup("MonitorBean/remote");
			MonitorRemote mr = (MonitorRemote) PortableRemoteObject.narrow(ref,
					MonitorRemote.class);

			mr.stopMonitor();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
