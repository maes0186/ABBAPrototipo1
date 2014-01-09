package com.conexia.saludcoop.listener;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

import com.conexia.saludcoop.Globals;


public class SaludCoopAppListener implements ServletContextListener {

	private static Log logger = LogFactory.getLog(SaludCoopAppListener.class);
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		logger.debug("Context saludcoop Destroyed...");
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		//configure log4j first
		DOMConfigurator.configure(this.getClass().getResource("/log4j.xml"));
		
		//configure app later
		logger.info("saludcoop system startup");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("saludcoop.properties");

		Properties p = new Properties();
		try {
			p.load(is);
			is.close();
		} catch (Exception e) {
			logger.error(e.toString());
		}

		Globals.getInstance().setDateFormatPattern(p.getProperty("dateFormatPattern"));
		Globals.getInstance().setUIdateFormatPattern(p.getProperty("UIdateFormatPattern"));
		Globals.getInstance().setDecimalFormatPattern(p.getProperty("decimalFormatPattern"));
		Globals.getInstance().setHourFormatPattern(p.getProperty("hourFormatPattern"));
		Globals.getInstance().setHourFormatNoSecondsPattern(p.getProperty("hourFormatNoSecondsPattern"));
		Globals.getInstance().setPathFolderRips(p.getProperty("pathFolderRips"));
		Globals.getInstance().setPathFolderHistoricos(p.getProperty("pathFolderHistoricos"));
		Globals.getInstance().setPathFolderHistoricosZips(p.getProperty("pathFolderHistoricosZips"));
		Globals.getInstance().setMaxLoginAttempts(p.getProperty("maxLoginAttempts"));
		Globals.getInstance().setWarningDaysForExpirationDate(p.getProperty("warningDaysForExpirationDate"));
		Globals.getInstance().setMaxDaysForExpirationTime(p.getProperty("maxDaysForExpirationTime"));
		
		logger.debug("Context saludcoop Started ["+servletContextEvent.getServletContext()+"]");
		
	}

}
