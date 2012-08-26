package org.util;

import java.util.Properties;

public class menuUtil {
	
	private static String PROPERTY_NAME_MENU_XML="MENUXML";
	private static String MENU_PROPERTIES_ADRESS="propiedades.menu";
	private static Properties menuProps;
	public menuUtil(){
		menuProps=generalUtil.loadProperties(MENU_PROPERTIES_ADRESS);
	}
	
	public static String getPropertyMenu(String prop){
		if(menuProps!=null)return menuProps.getProperty(prop);
		return null;
	}
	
	public static String getMenuXMLURL(){
		menuProps=generalUtil.loadProperties(MENU_PROPERTIES_ADRESS);
		String menuFileString=getPropertyMenu(PROPERTY_NAME_MENU_XML);
		String rootApp=generalUtil.rootUrl();
		String menuXMLURL=rootApp+menuFileString;
		return menuXMLURL;
	}
	
	
}

