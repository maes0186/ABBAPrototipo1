package org.util;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.constantes.J2EEConstantes;

public class J2EEUtil {
public static Properties getJ2EEProperties() throws IOException{
	Properties properties=GeneralUtil.loadProperties(J2EEConstantes.J2EE_FILE_PROPERTIES);
	return properties;
}


public static Context getContextJ2EE() throws NamingException, IOException{
	Properties p=J2EEUtil.getJ2EEProperties();
	Context ctx=new InitialContext(p);
	return ctx;
}

public static Object readEJB(String bean) throws NamingException, IOException{
	Context ctx=J2EEUtil.getContextJ2EE();
	Object ref=ctx.lookup(bean);
	return ref;
}

}
