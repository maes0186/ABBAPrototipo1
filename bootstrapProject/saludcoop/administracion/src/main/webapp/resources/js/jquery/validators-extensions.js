//Metodo que valida el profesional
//Es una extension del plugin validator de jquery
//Recibe el valor del campo, el campo y los parametros definidos en la creacion del validator
$.validator.addMethod('profesionalValidator', function(value, element, param) {
	
	//Variable de retorno
	var ret = true;
	
	//El tbody con todas las practicas
	var tr = $(element).parent();
	
	var profesional = tr.find('[name="codigoProfesional"]');
	if(profesional.val() == "")
	{
		tr.find('#nombreProfesional').addClass("error");
		ret = false;		
	}
	
	var especialidad = $("#tdEspecialidadProfesional select");
	if(especialidad)
	{
		if(especialidad.val() == "")
		{
			especialidad.addClass("error");
			ret = false;		
		}
	}
	
	return ret;
}, 'Profesional');


//Metodo que valida que la hora ingresada no sea futura
//Es una extension del plugin validator de jquery
//Recibe el valor del campo, el campo y los parametros definidos en la creacion del validator
$.validator.addMethod('horaValidator', function(value, element, param) {
	
	//Variable de retorno
	var ret = true;
	
	if(value){
		
		//Tomo la hora del formulario
		var horaSplit = value.split(":");
		
		var horaForm = new Date();
		horaForm.setHours(horaSplit[0]);
		horaForm.setMinutes(horaSplit[1]);
		
		//Tomo la hora actual
		var hora = new Date();

		//Se compara la diferencia, si es menor a 0 la fecha es futura
		var diff = hora - horaForm;
		if (diff < 0)
		{
			ret=false;
		}
		
	}
	else{
		ret = false;
	}
	
	return ret;
}, 'Hora');



/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ES
 */
jQuery.extend(jQuery.validator.messages, {
  required: "Este campo es obligatorio.",
  remote: "Por favor, rellenar este campo.",
  email: "Por favor, ingrese una dirección de correo válida",
  url: "Por favor, ingrese una URL válida.",
  date: "Por favor, ingrese una fecha válida.",
  dateISO: "Por favor, ingrese una fecha (ISO) válida.",
  number: "Por favor, ingrese un número entero válido.",
  digits: "Por favor, ingrese sólo dígitos.",
  creditcard: "Por favor, ingrese un número de tarjeta válido.",
  equalTo: "Por favor, ingrese el mismo valor de nuevo.",
  accept: "Por favor, ingrese un valor con una extensión aceptada.",
  maxlength: jQuery.validator.format("Por favor, no ingrese más de {0} caracteres."),
  minlength: jQuery.validator.format("Por favor, no ingrese menos de {0} caracteres."),
  rangelength: jQuery.validator.format("Por favor, ingrese un valor entre {0} y {1} caracteres."),
  range: jQuery.validator.format("Por favor, ingrese un valor entre {0} y {1}."),
  max: jQuery.validator.format("Por favor, ingrese un valor menor o igual a {0}."),
  min: jQuery.validator.format("Por favor, ingrese un valor mayor o igual a {0}.")
});