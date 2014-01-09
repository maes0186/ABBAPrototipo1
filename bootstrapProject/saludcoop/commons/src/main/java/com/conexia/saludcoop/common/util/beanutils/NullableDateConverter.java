/**
 * 
 */
package com.conexia.saludcoop.common.util.beanutils;

import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jamezquita
 *
 */
public class NullableDateConverter implements Converter {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(NullableDateConverter.class);
	
	@Override
	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof java.util.Date) {
			return value;
		}
		Object result = null;
		try {
			result = TypeConverter.parseDate((String) value);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return result;

	}

}
