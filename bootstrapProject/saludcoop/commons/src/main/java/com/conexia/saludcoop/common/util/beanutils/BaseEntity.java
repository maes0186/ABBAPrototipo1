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
@SuppressWarnings({"rawtypes","unchecked", "serial"})
public abstract class BaseEntity implements Serializable {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseEntity.class);	
	
	public BaseEntity() {

	}

	/**
	 * Metodo publico que atiende la generacion de BaseDTO dependiento del BaseEntity que lo invoca.
	 * @return
	 */
	public BaseDTO toDTO() {
		String []excepciones = null;
		return toDTO(((Collection) (new ArrayList())), excepciones);
	}
	
	/**
	 * Metodo publico que atiende la generacion de BaseDTO dependiento del BaseEntity y sus excepciones.
	 * @return
	 */
	public BaseDTO toDTO(String[] excepciones) {
		return toDTO(((Collection) (new ArrayList())), excepciones);
	}

	protected BaseDTO toDTO(Collection padres, String[] excepciones) {
		//Obtiene el tipo de clase a la cual va a retornar.
		Class clase = obtenerTipoDTO();
		//Inicializa las variables que son requisito.
		Class estaClase = null;
		BaseDTO bo = null;
		Method setters[] = (Method[]) null;
		Method getter = null;
		String nombreGetter = null;
		try {
			//Obtiene la clase propia.
			estaClase = getClass();
			//Adiciona a la coleccion de padres el punto inicial.
			padres.add(this);
			//Invoca una nueva instancia del DTO.
			bo = (BaseDTO) (BaseDTO) clase.newInstance();
			//Obtiene los metodos de la clase a la cual se va a migrar el Entity.
			if(excepciones == null){
				setters = clase.getMethods();
			}else{
				setters = removerMetodosException(clase.getMethods(),excepciones);
			}
			if (setters != null) {
				//Recorre cada uno de los metodos setter.
				for (int i = 0; i < setters.length; i++)
					//Si el metodo inicia con un set
					if (setters[i] != null && setters[i].getName().startsWith("set")) {
						//genera el nombre del get Correspondiente.
						nombreGetter =
						        (new StringBuilder("g")).append(setters[i].getName().substring(1))
						                .toString();
						//Obtiene el metodo get que sera invocado en la copia.
						getter = estaClase.getMethod(nombreGetter, new Class[0]);
						//Invoca el metodo que copiara el valor que se requiere.
						copiarCampoADTO(bo, setters[i], getter, padres, excepciones);
					}

			}
			return bo;
		} catch (InstantiationException e) {
			LOGGER.error(e.toString() + nombreGetter);
			return null;
		} catch (IllegalAccessException e) {
			LOGGER.error(e.toString() + nombreGetter);
			return null;
		} catch (SecurityException e) {
			LOGGER.error(e.toString() + nombreGetter);
			return null;
		} catch (NoSuchMethodException e) {
			LOGGER.error(e.toString() + nombreGetter);
			return null;
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.toString() + nombreGetter);
			return null;
		} catch (InvocationTargetException e) {
			LOGGER.error(e.toString() + nombreGetter);
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
				for(int j=0; j < setters.length;j++){
					if (setters[j] != null && setters[j].getName().toLowerCase().equals("set" + excepciones[i].toLowerCase())) {
						setters[j]=null;
					}
				}
			}
		}
		return setters;
	}

	/**
	 * Metodo encargado de hacer la copia de los atributos sin tener en cuenta las excepciones.
	 * @param bo
	 * @param setter
	 * @param getter
	 * @param padres
	 * @param excepciones
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void copiarCampoADTO(BaseDTO bo, Method setter, Method getter, Collection padres, String[] excepciones)
	        throws IllegalAccessException, InvocationTargetException {

		BaseEntity temp = null;
		Object obj = null;
		Collection col = null;
		Collection colBO = null;
		if (BaseEntity.class.isAssignableFrom(getter.getReturnType())) {
			temp = (BaseEntity) getter.invoke(this, new Object[0]);
			if (temp != null) {
				if (!padres.contains(temp))
					setter.invoke(bo, new Object[] { temp.toDTO(padres, excepciones) });
				setter.invoke(bo, new Object[] { temp.toDTO(excepciones) });
			}
		} else if (Collection.class.isAssignableFrom(getter.getReturnType())) {
			col = (Collection) getter.invoke(this, new Object[0]);
			if (col != null) {
				colBO = new ArrayList();
				for (Iterator iterator = col.iterator(); iterator.hasNext();) {
					Object o = iterator.next();
					if (o instanceof BaseEntity)
						colBO.add(((BaseEntity) o).toDTO(padres,excepciones));
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

	protected abstract Class obtenerTipoDTO();

	public boolean equals(Object o) {

		if (o != null)
			return (o instanceof BaseEntity) && toString().equals(o.toString());
		else
			return false;
	}
}
