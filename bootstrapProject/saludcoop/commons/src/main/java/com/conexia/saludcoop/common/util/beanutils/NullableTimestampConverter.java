package com.conexia.saludcoop.common.util.beanutils;

import java.sql.Timestamp;

import org.apache.commons.beanutils.locale.LocaleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NullableTimestampConverter implements LocaleConverter {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(NullableTimestampConverter.class);	
	
	private Timestamp defaultValue;

	public NullableTimestampConverter() {
	}


	public Object convert(Class type, Object value, String pattern) {
		return convert(type, value);
	}

	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof java.sql.Timestamp) {
			return value;
		}
		Object result = null;
		try {
			result = TypeConverter.parseTimestamp((String) value);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return result;
	}
}
