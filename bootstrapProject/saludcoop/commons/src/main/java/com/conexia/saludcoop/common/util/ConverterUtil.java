package com.conexia.saludcoop.common.util;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.conexia.saludcoop.Globals;


public class ConverterUtil {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConverterUtil.class);	
	
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

    public static String getStringSI_NOFromBoolean(Boolean bool) {
        return (bool) ? SystemConstants.SI : SystemConstants.NO;
    }

    public static String getStringSI_NO_NOSABEFromStringNumber(String string) {
        String resp = "";
        switch (string) {
        case "1":
            resp = SystemConstants.SI;
            break;
        case "2":
            resp = SystemConstants.NO;
            break;
        case "3":
            resp = SystemConstants.NO_SABE;
            break;
        }
        return resp;
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
	

	
	/**
	 * true si fecha1 esta entre fInicial y fFinal.
	 * false en otro caso.
	 * @param fecha1
	 * @param fInicial
	 * @param fFinal
	 * @return
	 */
	public static final boolean isFechaEntreIncialFinal(Date fecha1 , Date fInicial, Date fFinal){
		return fecha1.compareTo(fInicial) >= 0 && fecha1.compareTo(fFinal) <= 0;
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

	public static String getString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static String capitalizeString(String capitalizeMe){
		return capitalizeMe.substring(0,1).toUpperCase()+capitalizeMe.substring(1);
	}
    
    /**
     * Método encargado de transformar una lista de instancias de una clase en una lista 
     * de instancia de la clase indicada por el parámetro <code>clazz</code>
     * @param <T>
     * @param source
     *          Lista origen desde la cual se quiere realizar la conversión
     * @param clazz
     *          Corresponde al tipo para el que se quiere la lista de destino
     * @return 
     *          Lista de objetos de tipo clazz a partir de la lista source
     */    
    public static <T> List<T> tranformObjects(List<? extends Object> source, Class<T> clazz) {
        List<T> dtos = new ArrayList<>();
        for (Object o : source) {
            try {
                T t = clazz.newInstance();
                
                BeanUtils.copyProperties(o, t);
                dtos.add(t);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ConverterUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dtos;
    }

    /**
     * Método encargado de devolver lista de valores de la clase {@link SystemConstants} , 
     * recibiendo como parametro el prefijo de las variables.
     * @author rcarbonell
     * @param prefixVariables
     *   prefijo de las variables
     * @param type 
     *  tipo objetos en la lista
     * 
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getListValuesFromSC_ByPrefixName(String prefixVariables, T type){
        List<T> listValues = new ArrayList<>();
        
        Field[] fields = SystemConstants.class.getDeclaredFields() ;
        for(Field field : fields) {
            String name = field.getName();
            try {
                if(name.contains(prefixVariables.toString())){
                    listValues.add((T) field.get(name).toString());
                }
                
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return listValues;
    }
    
    /**
     * @param <X>
     * @param <X>
     * 
     */
    @SuppressWarnings("unchecked")
    public static <T, X> X getDataFromTransactionData(T transactionData, String keyData, X returnedType){
        LinkedHashMap<String, Object> mapa = (LinkedHashMap<String, Object>) transactionData;
        X data = (X) mapa.get(keyData);
        return data;
        
    }
}
