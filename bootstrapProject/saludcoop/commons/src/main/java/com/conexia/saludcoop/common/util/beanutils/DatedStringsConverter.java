
package com.conexia.saludcoop.common.util.beanutils;

import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.StringConverter;

public class DatedStringsConverter implements Converter {

	public Object convert(Class type, Object value) {
		String ret = null;
		if (value instanceof Date) {
			ret = TypeConverter.getString((Date) value);
		} else {
			StringConverter sc = new StringConverter();
			ret = (String) sc.convert(java.lang.String.class, value);
		}
		return ret;
	}
}
