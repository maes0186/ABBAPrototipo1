package com.conexia.saludcoop.common.util.beanutils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conexia.saludcoop.Globals;



public class TypeConverter {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TypeConverter.class);	
	
	private static final String DEFAULT_STRING_NUMBER = "0";
	private static final int DEFAULT_NUMBER_STRING = 0;

	public static Date parseDate(String dateString) throws ParseException {
		return SimpleDateFormat.getDateInstance().parse(dateString);
	}

	public static Date parseDate(String dateString, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date aux = new Date();
		try {
			aux = sdf.parse(dateString);
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			throw e;
		}
		return aux;
	}
	
	public static Timestamp parseTimestamp(String timestampString) {
		Timestamp aux = null;
		aux = Timestamp.valueOf(timestampString);
		return aux;
	}

	public static final Integer parseInteger(String string, boolean returnNull) {
		Integer result = null;
		if (!StringUtils.isEmpty(string)) {
			result = Integer.valueOf(string);
		} else if (!returnNull) {
			result = new Integer(0);
		}
		return result;
	}

	public static final Integer parseInteger(String string) {
		return parseInteger(string, true);
	}

	public static Double parseDouble(String string, String parsePattern, boolean returnNull) throws ParseException {
		NumberFormat nf = new DecimalFormat(parsePattern);
		Double result = null;
		try {
			result = new Double(nf.parse(string).doubleValue());
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			if (!returnNull) {
				result = new Double(0.0D);
			} else {
				throw e;
			}
		}
		return result;
	}

	public static Double parseDouble(String string, boolean returnNull) throws ParseException {
		return parseDouble(string, Globals.getInstance().getDecimalFormatPattern(), returnNull);
	}

	public static Double parseDouble(String string) throws ParseException {
		return parseDouble(string, true);
	}

	public static Character parseCharacter(String check) {
		return StringUtils.isEmpty(check) ? new Character('y') : new Character('n');
	}

	public static boolean getBooleanFromCharacter(Character value) {
		boolean result = false;
		if (value.charValue() == 'n') {
			result = false;
		} else if (value.charValue() == 'y') {
			result = true;
		}
		return result;
	}

	public static final String getString(Number number, boolean returnNull) {
		String result = null;
		if (number != null) {
			result = String.valueOf(number);
		} else if (!returnNull) {
			result = "0";
		}
		return result;
	}

	public static final String getString(Number number, String pattern, boolean returnNull) {
		NumberFormat nf = null;
		if (pattern == null) {
			nf = new DecimalFormat(Globals.getInstance().getDecimalFormatPattern());
		} else {
			nf = new DecimalFormat(pattern);
		}
		String result = null;
		if (number != null) {
			result = nf.format(number);
		} else if (!returnNull) {
			result = "0";
		}
		return result;
	}

	public static final String getString(Number number, String pattern) {
		return getString(number, pattern, true);
	}

	public static final String getString(Number number) {
		return getString(number, null, true);
	}

	public static final String getString(double number) {
		return getString(((Number) (new Double(number))), null, true);
	}

	public static final String getString(int number) {
		return getString(((Number) (new Integer(number))), null, true);
	}

	public static String getString(Date date) {
		DateFormat sdf = SimpleDateFormat.getDateInstance();
		return sdf.format(date);
	}
	public static String getString(Timestamp timestamp) {
		DateFormat sdf = SimpleDateFormat.getDateInstance();
		return sdf.format(timestamp);
	}

	public static String getString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	public static String getString(Timestamp timestamp, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(timestamp);
	}
}
