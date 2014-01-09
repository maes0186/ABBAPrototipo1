package com.conexia.saludcoop.common.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

public class I18NUtils {
	private static I18NUtils instance;
	private MessageSource messages;
	private I18NUtils(){
		
	}
	private I18NUtils(MessageSource message){
		this.messages = message;
	}
	public static I18NUtils getInstance(){
		if(I18NUtils.instance == null){
			I18NUtils.instance = new I18NUtils();
		}
		return I18NUtils.instance;
	}
	
	public static I18NUtils getInstance(MessageSource message){
		if(I18NUtils.instance == null){
			I18NUtils.instance = new I18NUtils(message);
		}
		return I18NUtils.instance;
	}
	public String getText(String key, Object[] params,Integer tipoFormateo) {
		String txt = "";

		try{
			txt = messages.getMessage(key, params, Locale.getDefault());
		}
		catch(NoSuchMessageException nsme){
			
			nsme.printStackTrace();
		}
		
		if (txt != "" && tipoFormateo != null) {
			switch (tipoFormateo) {
			case 1:
				txt = txt.toLowerCase();
				break;
			case 2:
				txt = txt.toUpperCase();
				break;
			case 3:
				txt = txt.substring(0, 1).toUpperCase() + txt.substring(1).toLowerCase();
			}
		}
		
		return txt;
	}

	public String getText(String key) {
		String txt = "";
		Object[] params = null;
		try{
			txt = messages.getMessage(key, params, Locale.getDefault());
		}
		catch(NoSuchMessageException nsme){
			
			nsme.printStackTrace(); // tal vez convenga dejar que arroje esta exception para que lo maneje
									// la clase que lo invoca
		}
		
		return txt;
	}

	
	public void setMessageSource(MessageSource messages) {
		this.messages = messages;
		
	}

}
