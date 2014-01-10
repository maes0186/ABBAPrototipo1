package com.conexia.saludcoop.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.conexia.saludcoop.Globals;
import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.common.exception.ServiceException;
import com.conexia.saludcoop.web.form.FormError;
import com.conexia.saludcoop.web.vo.DescriptivoVO;
import com.conexia.saludcoop.web.vo.TipoIdentificacionVO;

@Component
public class BaseValidatingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseValidatingController.class);

	@Autowired
	protected MessageSource messageSource;
	
	public static final String FORM_ERRORS_KEY = "form_errors";
	public static final String GENERAL_ERRORS_KEY = "general_errors";
	

	/**
	 * Inicializa el binder de parametros para ete controller y todos su hijos. Agregar aca todos los custom format binders que haga falta
	 * 
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		// fechas
		initDateFortmatBinder(request, binder);
		// archivos
		initFileUploadBinder(request, binder);
	}

	/**
	 * Metodo util para traducir fechas segun cierto formato establecido y que el fwk sepa interpretarlas y convertirlas a Date
	 * 
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	private void initDateFortmatBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Globals.getInstance().getDateFormatPattern());
		CustomDateEditor cde = new CustomDateEditor(sdf, true);
		binder.registerCustomEditor(Date.class, cde);
	}

	/**
	 * Metodo util para traducir archivos que se quieren subir y convertilos a un arreglo de bytes
	 * 
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	private void initFileUploadBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	
	/**
	 * Metodo util para convertir los errores de validacion en request de spring mvc a un mapa para usar en el jsp, sin que sea intrusivo y asi no tener que usar los tags de spring Si el controlador
	 * no llama a este metodo, se puede acceder a los errores en request de la forma tradicional de springMVC
	 * 
	 * @param bindingResult
	 * @return
	 */
	protected Map<String, FormError> getErrorMap(BindingResult bindingResult) {
		Map<String, FormError> errorMessages = new HashMap<String, FormError>();
		List<FieldError> allErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : allErrors) {
			String key = fieldError.getField();
			String value = fieldError.getDefaultMessage();
			FormError error = new FormError(key, value);
			errorMessages.put(key, error);
		}
		return errorMessages;
	}
	
	protected FormError createFormError(String field, String errorMessage) {
		String key = field;
		String value = errorMessage;
		FormError error = new FormError(key, value);
		return error;
	}
	
	/**
	 * Metodo util para convertir los errores de validacion en request de spring mvc a un mapa para usar en el jsp, 
	 * sin que sea intrusivo y asi no tener que usar los tags de spring. Si el controlador
	 * no llama a este metodo, se puede acceder a los errores en request de la forma tradicional de springMVC.
	 * Este metodo es igual a con
	 * la diferencia de que devuelve un mapa cuyo key es el nombre del campo a validar y cuyo valor
	 * es el mensaje de validacion totalmente resuelto como un String.
	 * 
	 * @see BaseValidatingController#getErrorMap(BindingResult)
	 * @param bindingResult
	 * @return
	 */
	protected Map<String, String> getFieldErrorsStringMap(BindingResult bindingResult) {
		Map<String, String> errorMessages = new HashMap<String, String>();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			String key = fieldError.getField();
			String value = fieldError.getDefaultMessage();
			errorMessages.put(key, value);
		}
		return errorMessages;
	}
	
	/**
	 * Igual al getErrorStringMap pero inspecciona los errores "globales" a la pantalla, 
	 * es decir, aquellos que no están asociados a ningun campo. Pueden ser los errores de validacion 
	 * generales o los errores provenientes de las reglas de negocio.
	 * Por eso devuelve una lista, en vez de un mapa
	 * @param bindingResult
	 * @return
	 */
	protected List<String> getGlobalErrorsList(BindingResult bindingResult) {
		List<String> errorMessages = new ArrayList<String>();
		
		List<ObjectError> objectErrors = bindingResult.getGlobalErrors();
		for (ObjectError objectError : objectErrors) {
			errorMessages.add(objectError.getCode());
        }
		
		return errorMessages;
	}
	
	
	protected String getMessage(String key) {
		return this.messageSource.getMessage(key ,null, Locale.getDefault());
	}
	protected String getMessage(String key, Object... args) {
		return this.messageSource.getMessage(key ,args, Locale.getDefault());
	}
	
	protected String getMessage(String key, Locale locale) {
		return this.messageSource.getMessage(key ,null, locale);
	}
	
	protected String getMessage(String key, Locale locale, Object... args) {
		return this.messageSource.getMessage(key ,args, locale);
	}
	
	
	@ExceptionHandler(ServiceException.class)
	public String handleIOException(ServiceException ex, HttpServletRequest request) {
	  return "/error.jsp";
	}
	
	public static void cargarElementosDescriptivo(String elemento, ModelMap map, List<DescriptivoDto> descriptivoDtos){
		Vector<DescriptivoVO> elementos = new Vector<DescriptivoVO>();
		for (DescriptivoDto enumItem : descriptivoDtos){
			elementos.add(new DescriptivoVO(enumItem));
		}
		map.put(elemento, elementos);
	}
	
	public static void cargarElementosTipoIdentificacion(String elemento,ModelMap map, List<TipoIdentificacionDto> tipoIdentificacionDtos){
		Vector<TipoIdentificacionVO> elementos = new Vector<TipoIdentificacionVO>();
		for (TipoIdentificacionDto enumItem : tipoIdentificacionDtos){
			elementos.add(new TipoIdentificacionVO(enumItem));
		}
		map.put(elemento, elementos);
	}
	
	public void removeFromSession(ModelMap modelMap, HttpSession session, String key){
		modelMap.remove(key);
		session.removeAttribute(key);
	}
}
