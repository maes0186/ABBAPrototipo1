package com.conexia.saludcoop.common.util.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conexia.saludcoop.common.exception.GestarBeanUtilsException;



public class BeanHelper {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);
	
	private static BeanUtilsBean beanUtils = new BeanUtilsBean();

	private BeanHelper() {
	}

	public static void copyProperties(Object from, Object to) throws GestarBeanUtilsException {
		try {
			beanUtils.copyProperties(to, from); 
		} catch (IllegalAccessException e) {
			LOGGER.error(e.toString());
			throw new GestarBeanUtilsException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error(e.toString());
			throw new GestarBeanUtilsException(e);
		}
	}

	public static void copyProperties(Object from, Object to, Map<Class<?>, Converter> converters) throws GestarBeanUtilsException {
		try {
			Collection<Map.Entry<Class<?>, Converter>> convs = converters.entrySet();
			Converter conv;
			Class<?> clazz;
			for (Iterator<Map.Entry<Class<?>, Converter>> it = convs.iterator(); it.hasNext(); beanUtils.getConvertUtils().register(conv, clazz)) {
				Map.Entry<Class<?>, Converter> entry = it.next();
				conv = entry.getValue();
				clazz = entry.getKey();
			}

			beanUtils.copyProperties(to, from);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.toString());
			throw new GestarBeanUtilsException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error(e.toString());
			throw new GestarBeanUtilsException(e);
		}
	}

}
