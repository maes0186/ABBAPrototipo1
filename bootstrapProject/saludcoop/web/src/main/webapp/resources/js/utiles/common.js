/**
 * Codigo reusable y separado por "namespaces".
 * @author rpadilla
 */

/**
 * Closure de entrada para encapsular contexto de ejecución.
 * @param {Window} window Objeto global
 * @param {jQuery} $ Objeto jQuery
 */
(function(window, $) {

	/**
	 * Se lee o instancia el namespace personalizado reusable.
	 * @type {Object}
	 */
	window.Custom = window.Custom || {};
	/**
	 * Namespace para encapsular código personalizado.
	 */
	var Custom = window.Custom;
	/**
	 * Namespace para utilidades relacionadas a la interfaz grafica.
	 */
	Custom.Ui = {};
	/**
	 * Namespace para utilidades relacionadas a los formularios.
	 */
	Custom.Form = {};
	/**
	 * Namespace para utilidades relacionadas a funciones varias genericas.
	 */
	Custom.Util = {};

	/**
	 * Fines de linea en campos.
	 * @type RegExp
	 */
	var regexFieldValCRLF = /\r?\n/g;

	/**
	 * Permite asegurarse de importar un archivo JavaScript una unica vez.
	 * Util cuando se renderizan pantallas via AJAX y dichas pantallas incluyen archivos javascript,
	 * esta funcion debería ser usada en dichas pantallas incrustables.
	 * El identificador proporcionado permite verificar que no se haya importado previamente
	 * el archivo. El callback proporcionado se ejecutará al terminar de importar el
	 * archivo, o inmediamente si se había importado previamente el archivo con el identificador dado.
	 * Ejemplo de uso desde un JSP:
	 * <pre><code>
	 * var id = 'gp_tr_gestionar-seguimiento_js';
	 * var path = "${appCtx}/resources/js/custom/gestionProveedores/transversales/gestionar-seguimiento.js";
	 * var callback = function() {
	 * 	Custom.gp.tr.gs.formulario.init();
	 * };
	 * Custom.Util.loadJs(id, path, callback);
	 * </code></pre>
	 * @param {string} id Identificador a colocar al tag &lt;script&gt; del archivo importado
	 * @param {string} path Ruta del archivo a importar
	 * @param {Function=} callback Funcion a ejecutar luego de importar el archivo
	 */
	Custom.Util.loadJs = function(id, path, callback) {
		if (document.getElementById(id))  {
			if (callback) {
				callback.call(this);
			}
		} else {
			var script = document.createElement('script');
			script.id = id;
			script.src = path;
			script.type = 'text/javascript';
			if (callback) {
				script.onload = function() {
					callback.call(this);
				};
			}
			document.getElementsByTagName('head')[0].appendChild(script);
		}
	};

	/**
	 * Formatters comunes hechos reusables y estandar para diferentes grillas.
	 */
	Custom.Ui.Formatters = {
		permitido: function(cellvalue, options, obj) {
			return cellvalue ? '<span class="icon icon-permitido" title="Si"></span>'
												: '<span class="icon icon-denegado" title="No"></span>';
		},
		accionesCrud: function(cellvalue, options, obj) {
			var btnEditar = '<a class="icon icon-edit" data-id="' + obj.id + '" title="Editar">Editar</a>';
			var btnCambiarActiva = '';
			if (obj.activo || obj.activa) {
				btnCambiarActiva = '<a class="icon icon-disable" data-id="' + obj.id + '" title="Desactivar">Desactivar</a>';
			} else {
				btnCambiarActiva = '<a class="icon icon-enable" data-id="' + obj.id + '" title="Activar">Activar</a>';
			}
			return btnEditar + btnCambiarActiva;
		},
		principal: function(cellvalue, options, obj) {
			return obj.principal ? '<span class="icon icon-principal"></span>' : '';
		}
	};

	/**
	 * Muestra u oculta el overlay "ocupado" segun el valor del parametro 'flag'.
	 * @param {boolean} flag Muestra la capa "ocupado" si true, en caso contrario la cierra
	 */
	Custom.Ui.busy = function(flag) {
		if (flag) {
			// solo crear el overlay si no esta ya visible.
			var $overlay = $('#jquery-msg-overlay');
			if ($overlay.length === 0) {
				$.msg({
					autoUnblock : false,
					clickUnblock : false,
					content : '<div>Cargando...</div>\n\
							   <div style="width: 32px; margin: auto;">\n\
								 <img src="' + Custom.Params.contextPath + '/resources/images/standard/ajax-loader.gif"/>\n\
							   </div>'
					,
					bgPath : Custom.Params.contextPath + "/resources/images/jquery-msg/"
				});
				var $document = $(window.document);
				$overlay.width($document.width()).height($document.height()).css('z-index', 1000);
			}
		} else {
			$.msg('unblock');
		}
	};

	/*
	 * Muestra un mensaje de informacion o alerta segun el valor enviado en el parametro 'type'.
	 * @param {string} message Texto o html del mensaje a mostrar.
	 * @param {string=} type Tipo de mensaje a mostrar: 'info' o 'alert'; por defecto 'info'.
	 * @param {number=} height Alto del dialogo, default a 150.
	 * @param {number=} width Ancho del dialogo, default a 250.
	 */
	Custom.Ui.showMessage = function (message, type, height, width, callback) {
		type = type || 'info';
		height = height || 150;
		width = width || 250;
		var wrapperClass;
		var title;
		if (type === 'info') {
			title = 'Información';
			wrapperClass = 'highlight';
		} else {
			title = 'Advertencia';
			wrapperClass = 'error';
		}
		var content = '<div class="dialogContainer">'
										+ '<div class="ui-corner-all ui-state-' + wrapperClass + '" style="padding: .7em;">'
											+ '<span class="ui-icon ui-icon-' + type + '" style="float: left; margin-right: .3em;"></span>'
											+ message
										+ '</div>'
								+ '</div>';
		$(content).dialog({
			title: title,
			autoOpen: true,
			height: height,
			width: width,
			modal: true,
			buttons: {
				'Aceptar': function() {
					$(this).dialog('close');
				}
			},
			close: function(event, ui) {
				var $this = $(this);
				if (callback) {
					callback.call(this);
				} 
				$this.dialog('destroy');
				$this.remove();
			}
		});
	}
	/*
	 * Muestra un mensaje de confirmacion y ejecuta 'callbackYes' si se elige 'Si',
	 * y 'callbackNo' si se elige 'No' o se cierra el dialogo.
	 * @param {string} message El texto del mensaje a mostrar
	 * @param {Function=} callbackYes Callback a invocar cuando la respuesta sea 'Si'
	 * @param {Function=} callbackNo Callback a invocar cuando la respuesta sea 'No'
	 * @param {number=} height int Alto del dialogo, default a 150
	 * @param {number=} width Ancho del dialogo, default a 250
	 */
	Custom.Ui.confirmMessage = function (message, callbackYes, callbackNo, height, width) {

		height = height || 150;
		width = width || 250;
		var yesSelected = false;

		$('<div>').addClass('dialogContainer').html(message).dialog({
			autoOpen: true,
			height: height,
			width: width,
			modal: true,
			buttons: {
				'Si': function() {
					yesSelected = true;
					$(this).dialog('close');
				},
				'No': function() {
					$(this).dialog('close');
				}
			},
			close: function(event, ui) {
				if (yesSelected) {
					if (callbackYes) {
						callbackYes.call(this);
					}
				} else if (callbackNo) {
					callbackNo.call(this);
				}
				var $this = $(this);
				$this.dialog('destroy');
				$this.remove();
			}
		});
	};

	/**
	 * Mueve el scroll de la ventana hasta la parte inferior.
	 */
	Custom.Ui.scrollToBottom = function () {
		Custom.Ui.scrollTo('bottom');
	};

	/**
	 * Mueve el scroll de la ventana hasta la parte superior.
	 */
	Custom.Ui.scrollToTop = function () {
		Custom.Ui.scrollTo('top');
	};

	/**
	 * Mueve el scroll de la ventana hasta la parte indicada.
	 * 'top' para desplazar hacia la parte superior de la ventana; 'bottom' para desplazar
	 * hacia la parte inferior; o el identificador (con el prefijo #) de un elemento en el DOM
	 * para desplazar hacia la posicion de ese elemento.
	 * @param {string} to Indicacion de hacia donde hacer el scroll
	 */
	Custom.Ui.scrollTo = function (to) {		
		var topPosition;
		if (to === 'top') {
			topPosition = 0;
		} else if (to === 'bottom') {
			topPosition = $(document).height() - $(window).height();
		} else if (to.indexOf('#') === 0) {
			topPosition = $(to).offset().top;
		} else {
			throw new Error("Parametro incorrecto para 'Custom.Ui.scrollTo'");
		}
		$('html, body').animate({scrollTop: topPosition}, 400, 'linear');
	};

	/**
	 * Limpia los campos del formulario. El 'reset' nativo no siempre limpia los campos pq su funcion es dejarlos
	 * tal cual se setearon al cargar la pagina (pueden quedar con los datos de un POST).
	 * @param {string} formId Identificador del formulario
	 * @param {Array.<string>=} fields Listado con los nombres de los campos especificos a limpiar; si no se pasa,
	 * se limpian todos los campos del formulario.
	 */
	Custom.Form.clearFields = function(formId, fields) {
		var form = document.getElementById(formId);
		var elements = form.elements;
		for (var i=0, n=elements.length; i < n; i++) {
			var elem = elements[i];
			if (fields && fields.indexOf(elem.name) === -1) {
				continue;
			}
			Custom.Form.setFieldVal(elem, '');
		}
	};

	/**
	 * Habilita o deshabilita los campos de un formulario.
	 * @param {string} formId Identificador del formulario
	 * @param {boolean} flag Habilitar si true, en caso contrario deshabilitar
	 */
	Custom.Form.enable = function(formId, flag) {
		var form = document.getElementById(formId);
		var elements = form.elements;
		if (flag) {
			for (var i=0, n=elements.length; i < n; i++) {
				var elem = elements[i];
				if (Custom.Form.isFieldButton(elem)) {
					var $elem = $(elem);
					if ($.fn.button && $elem.hasClass('ui-widget')) {
						$elem.button('enable');
					}
				}
				elem.removeAttr('readonly');
				elem.removeAttr('disabled');
			}
		} else {
			for (var i=0, n=elements.length; i < n; i++) {
				var elem = elements[i];
				if (elem.type === 'text' || elem.type === 'hidden' || elem.type === 'password') {
					elem.setAttribute('readonly', 'readonly');
				} else if (Custom.Form.isFieldButton(elem)) {
					var $elem = $(elem);
					if ($.fn.button && $elem.hasClass('ui-widget')) {
						$elem.button('disable');
					} else {
						elem.setAttribute('disabled', 'disabled');
					}
				} else {
					elem.setAttribute('disabled', 'disabled');
				}
			}
		}
	};

/**
 * Llena un formulario usando un objeto, los campos del formulario son
 * alimentados usando las propiedades del objeto. Las propiedades del objeto
 * deben corresponder a nombres de campos del formulario.
 * @param {string} formId Identificador del formulario
 * @param {Object.<string, Object>} objSrc DataSource para poblar los campos del form.
 */
	Custom.Form.setData = function(formId, objSrc) {
		var form = document.getElementById(formId);
		var elements = form.elements;
		for (var propName in objSrc) {
			if (elements[propName] !== undefined) {
				Custom.Form.setFieldVal(elements[propName], objSrc[propName]);
			}
		}
	};

	/**
	 * Genera un objeto JavaScript a partir de un formulario dado, donde cada propiedad y su valor,
	 * se obtiene respectivamente del nombre de los campos y sus valores en el formulario.
	 * Funciona tambien para nombres de campo tipo array (tipicamente checkboxes),
	 * ejemplo: 'nombreCampo[]'.
	 * @param {string} formId Identificador del formulario
	 * @return {Object.<string, Object>} Objeto JavaScript generado
	 */
	Custom.Form.obtainData = function(formId) {

		var objResp = {},
				nameArrSubCount,
				form = document.getElementById(formId),
				elements = form.elements,
				regexFieldNameArr = /^([^\[]+)(\[\])$/;

		for (var i=0, n=elements.length; i < n; ++i) {

			var elem = elements[i];			

			var val = Custom.Form.obtainFieldVal(elem);

			if (val === undefined || val === '') {
				continue;
			}

			/* Si el nombre del campo tiene la estructura de un array (tipicamente varios "checkbox" con el mismo name)
			 * 'nombreCampo[]'. */
			if (regexFieldNameArr.test(elem.name)) {

				var regexResult = elem.name.split(regexFieldNameArr),
						fieldName = regexResult[1];

				if (objResp[fieldName] === undefined) {
					objResp[fieldName] = {};
					nameArrSubCount = 0;
				}

				objResp[fieldName][nameArrSubCount] = val;
				++nameArrSubCount;

			} else {
				// Para manejar fields cuyos valores son arrays, tipicamente "select-multiple".
				if (Array.isArray(val)) {
					objResp[elem.name] = {};
					for (var j=0, m=val.length; j<m; ++j) {
						objResp[elem.name][j] = val[j];
					}
				} else {
					objResp[elem.name] = val;
				}
			}
		}

		return objResp;
	};

	/**
	 * Evalua si el elemento pasado es 'submittable'.
	 * @param {DomElement} elem Elemento del Formulario
	 * @return {boolean} Si condicion validada true, false en caso contrario
	 */
	Custom.Form.isFieldSubmittable = function(elem) {
		return elem.name && !Custom.Form.isFieldButton(elem);
	};

	/**
	 * Evalua si el elemento pasado es de tipo botón.
	 * @param {DomElement} elem Elemento del Formulario
	 * @return {boolean} Si condicion validada true, false en caso contrario
	 */
	Custom.Form.isFieldButton = function(elem) {
		return elem.type === 'button' || elem.type === 'submit' || elem.type === 'reset';
	};

	/**
	 * Obtiene el valor del campo dado.
	 * @param {DomElement} elem Elemento del DOM
	 * @return {Object} Valor del campo
	 */
	Custom.Form.obtainFieldVal = function(elem) {

		if (Custom.Form.isFieldSubmittable(elem)) {
			
			if (elem.type === 'text' || elem.type === 'hidden' || elem.type === 'password') {
				return elem.value.replace(regexFieldValCRLF, "\r\n" );
			} else if (elem.type === 'checkbox' || elem.type === 'radio') {
				return elem.checked ? elem.value : undefined;
			} else if (elem.type === 'select-one') {
				return elem.options[elem.selectedIndex].value;
			} else if (elem.type === 'select-multiple') {
				var values = [], count = 0;
				for (var i=0, n=elem.options.length; i<n; ++i) {
					if (elem.options[i].selected) {
						values[count] = elem.options[i].value;
						++count;
					}
				}
				return values.length > 0 ? values : undefined;
			}
			return $(elem).val();
		}

		return undefined;
	};

	/**
	 * Pone un valor al campo dado.
	 * @param {DomObject} elem Elemento del DOM
	 * @param {Object} val Valor a poner
	 */
	Custom.Form.setFieldVal = function(elem, val) {
		if (Custom.Form.isFieldSubmittable(elem)) {
			if (elem.type === 'text' || elem.type === 'hidden' || elem.type === 'password') {
				elem.value = val;
			} else if (elem.type === 'checkbox' || elem.type === 'radio') {
				elem.checked = val ? true : false;
			} else if (elem.type === 'select-one') {
				if (val === '' || val === null || val === undefined) {
					elem.selectedIndex = 0;
				} else {
					elem.value = val;
				}
			} else {
				$(elem).val(val);
			}
		}
	};

	/**
	 * Llena un elemento select con los municipios del departamento dado, y opcionalmente
	 * preselecciona el municipio dado.
	 * @param {string} idCombo Identificador del select
	 * @param {string} codigoDepartamento Codigo del Departamento
	 * @param {string=} codigoMunicipio Codigo del Municipio a preseleccionar, opcional
	 */
	Custom.Form.cargarComboMunicipio = function(idCombo, codigoDepartamento, codigoMunicipio) {
		var html = '<option value="" selected="selected">Seleccionar</option>';
		for (var i = 0, n = departamentosMunicipios.departamentos.length; i < n; i++) {
			var dep = departamentosMunicipios.departamentos[i];
			if(dep.codigo === codigoDepartamento) {
				for (var j = 0, m = dep.municipios.length; j < m; j++) {
					var mun = dep.municipios[j];
					var selected = (mun.codigo === codigoMunicipio) ? 'selected="selected"' : '';
					html += '<option value="' + mun.codigo + '" ' + selected + '>' + mun.descripcion + '</option>';
				}
				break;
			}
		}
		$('#' + idCombo).html(html).removeAttr('disabled');
	};

	/**
	 * Remueve los mensajes y marcas de error del formulario dado.
	 * @param {string} formId Identificador del formulario
	 * @param {string} prefix Prefijo del identificador del componente de mensajes de error
	 * @param {Function=} callback Funcion a ejecutar luego de terminar
	 */
	Custom.Form.removeErrors = function(formId, prefix, callback) {
		$('#' + formId).find('.campoResaltado').removeClass('campoResaltado');
		$('#' + prefix + 'stateErrorGeneric').slideUp('fast', function() {
			$(this).find('p').empty();
			if (callback) {
				callback.call(this);
			}
		});
	};

	/**
	 * Muestra un mensaje de error.
	 * @param {string} prefix Prefijo del identificador del componente de mensajes de error
	 * @param {string} message Mensaje a mostrar
	 */
	Custom.Form.showErrors = function(prefix, message) {
		$('#' + prefix + 'stateErrorGeneric').find('p').html(message).end().slideDown('fast');
	};

})(window, jQuery);
