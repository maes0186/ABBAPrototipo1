package cliente.billete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import server.billete.BilletefalsoException;
import server.billete.BilletefalsoRemote;

public class Billetefalsoclient {
	public static void main(String[] args) {
		try {
			Properties p=new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			p.put(Context.URL_PKG_PREFIXES,"org.jboss.naming.org.jnp.interfaces");
			p.put(Context.PROVIDER_URL,"jnp://localhost:1099");
			Context ctx=new InitialContext(p);

			Object ref = ctx.lookup("BilletefalsoBean/remote");
			BilletefalsoRemote fgr = (BilletefalsoRemote) PortableRemoteObject
					.narrow(ref, BilletefalsoRemote.class);

			BufferedReader din = new BufferedReader(new InputStreamReader(
					System.in));

			String nummer;
			do {
				System.out.print("Bitte Geldscheinnummer: ");
				nummer = din.readLine();
				if (nummer.length() > 0) {
					try {
						if (fgr.numeroValido(nummer))
							System.out.println("El numero es valido");
						else
							System.out.println("Billetefalso !!");
					} catch (BilletefalsoException e) {
						e.printStackTrace();
					}
				}
			} while (nummer.length() > 0);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
