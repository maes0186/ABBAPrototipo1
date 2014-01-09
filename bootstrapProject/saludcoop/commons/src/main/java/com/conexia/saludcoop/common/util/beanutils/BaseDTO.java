/**
 * 
 */
package com.conexia.saludcoop.common.util.beanutils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jamezquita
 * 
 */
@SuppressWarnings({"rawtypes","unchecked"})
public abstract class BaseDTO implements Serializable {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDTO.class);	
	
	public BaseDTO() {

	}

    public BaseEntity toEntity() {
		String []excepciones = null;
		return toEntity(((Collection) (new ArrayList())), excepciones);
	}
	
	public BaseEntity toEntity(String[] excepciones) {
		return toEntity(((Collection) (new ArrayList())), excepciones);
	}


	/**
	 * Metodo encargado de obtener el BaseEntity correspondiente para el DTO.
	 * @param padres
	 * @return
	 */
	public BaseEntity toEntity(Collection padres, String[] excepciones) {

		Class clase = obtenerTipoEntity();
		Class estaClase = null;
		BaseEntity bo = null;
		Method setters[] = (Method[]) null;
		Method getter = null;
		String nombreGetter = null;
		try {
			estaClase = getClass();
			padres.add(this);
			bo = (BaseEntity) (BaseEntity) clase.newInstance();
			if(excepciones == null){
				setters = clase.getMethods();
			}else{
				setters = removerMetodosException(clase.getMethods(),excepciones);
			}
			if (setters != null) {
				for (int i = 0; i < setters.length; i++)
					if (setters[i] != null && setters[i].getName().startsWith("set")) {
						nombreGetter =
						        (new StringBuilder("g")).append(setters[i].getName().substring(1))
						                .toString();
						getter = estaClase.getMethod(nombreGetter, new Class[0]);
						copiarCampoAEntity(bo, setters[i], getter, padres, excepciones);
					}

			}
			return bo;
		} catch (InstantiationException e) {
			LOGGER.error(e.toString());
			return null;
		} catch (IllegalAccessException e) {
			LOGGER.error(e.toString());
			return null;
		} catch (SecurityException e) {
			LOGGER.error(e.toString());
			return null;
		} catch (NoSuchMethodException e) {
			LOGGER.error(e.toString());
			return null;
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.toString());
			return null;
		} catch (InvocationTargetException e) {
			LOGGER.error(e.toString());
		}
		return null;
	}
	
	/**
	 * Metodo encargado de remover los metodos que no se van a procesar.
	 * @param setters 
	 * @param excepciones
	 * @return
	 */
	private Method[] removerMetodosException(Method[] setters, String[] excepciones) {
		//verifica que el numero de excepciones a quitar sea menor.
		if(setters.length > excepciones.length){
			for(int i=0; i < excepciones.length; i++){
				for(int j=0; j<setters.length;j++){
					if (setters[j].getName().toLowerCase().contains(excepciones[i].toLowerCase())) {
						setters[j]=null;
					}
				}
			}
		}
		return setters;
	}

	private void copiarCampoAEntity(BaseEntity bo, Method setter, Method getter, Collection padres, String[] excepciones)
	        throws IllegalAccessException, InvocationTargetException {

		BaseDTO temp = null;
		Object obj = null;
		Collection col = null;
		Collection colBO = null;
		if (BaseDTO.class.isAssignableFrom(getter.getReturnType())) {
			temp = (BaseDTO) getter.invoke(this, new Object[0]);
			if (temp != null) {
				if (!padres.contains(temp))
					setter.invoke(bo, new Object[] { temp.toEntity(padres, excepciones) });
				setter.invoke(bo, new Object[] { temp.toEntity(excepciones) });
			}
		} else if (Collection.class.isAssignableFrom(getter.getReturnType())) {
			col = (Collection) getter.invoke(this, new Object[0]);
			if (col != null) {
				colBO = new ArrayList();
				for (Iterator iterator = col.iterator(); iterator.hasNext();) {
					Object o = iterator.next();
					if (o instanceof BaseDTO)
						colBO.add(((BaseDTO) o).toEntity(padres, excepciones));
					else
						colBO.add(o);
				}

				setter.invoke(bo, new Object[] { colBO });
			}
		} else
			obj = getter.invoke(this, new Object[0]);
		if (obj != null)
			setter.invoke(bo, new Object[] { obj });
	}

	public String toString() {

		StringBuffer sb = new StringBuffer();
		Method metodos[] = getClass().getMethods();
		Object ret = null;
		try {
			if (metodos != null) {
				sb.append((new StringBuilder(String.valueOf(getClass().getSimpleName()))).append(
				        ":[").toString());
				for (int i = 0; i < metodos.length; i++)
					if (metodos[i].getName().startsWith("get")) {
						ret = metodos[i].invoke(this, new Object[0]);
						sb.append((new StringBuilder(String.valueOf(metodos[i].getName())))
						        .append(": ").append(ret).append(" ").toString());
					}

				sb.append("]");
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.toString());
			return null;
		} catch (IllegalAccessException e) {
			LOGGER.error(e.toString());
			return null;
		} catch (InvocationTargetException e) {
			LOGGER.error(e.toString());
			return null;
		}
		return sb.toString();
	}

	protected abstract Class obtenerTipoEntity();

	private static final long serialVersionUID = -1311699730383992264L;
}
