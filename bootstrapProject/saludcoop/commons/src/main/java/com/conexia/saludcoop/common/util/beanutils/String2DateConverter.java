package com.conexia.saludcoop.common.util.beanutils;

import java.util.Date;

import org.apache.commons.beanutils.locale.LocaleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conexia.saludcoop.Globals;



public class String2DateConverter implements LocaleConverter {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(String2DateConverter.class);	
	
	private String pattern;
	private boolean useDefaultValue;
	private String defaultValue;

	public String2DateConverter() {
		pattern = Globals.getInstance().getDateFormatPattern();
		useDefaultValue = false;
		defaultValue = "0000-00-00";
	}

	public String2DateConverter(boolean useDefaultValue) {
		pattern = Globals.getInstance().getDateFormatPattern();;
		defaultValue = "0000-00-00";
		this.useDefaultValue = useDefaultValue;
	}

	public String2DateConverter(String datePattern) {
		pattern = datePattern;
		useDefaultValue = false;
		defaultValue = "0000-00-00";
	}

	public Object convert(Class type, Object value, String pattern) {
		this.pattern = pattern;
		return convert(type, value);
	}

	public Object convert(Class type, Object value) {
		if (value == null) {
			if (useDefaultValue) {
				return defaultValue;
			} else {
				return value;
			}
		}
		if (value instanceof Date) {
			return value;
		}
		Object result = null;
		try {
			result = TypeConverter.parseDate((String) value, pattern);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return result;
	}
}
