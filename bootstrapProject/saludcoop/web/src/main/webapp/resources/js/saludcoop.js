$(function() {	
	//Time display
	//Metodos para que funcione el reloj del menu
	showDate();
	clock();
	self.setInterval("clock()",500);
	
	//JS buttons
	//Form actions es el estilo de un td donde se posicionan los botones
	//Si se respeta el modelo esto logra evitar que en todas las paginas se necesite indicar que determinado elemento es un boton.
	$(".formActions button").button();
	
	//Show wrapper to avoid fouc
	//El modelo de html establece un div con id="wrapper" para el contenido que inicialmente se carga como hidden.
	//Al realizar show sobre este wrapper al finalizar el javascript se logra evitar que se muestre contenido sin el estilo correspondiente
	$("#wrapper").show();
	
	//Default language para el datepicker
	$.datepicker.regional['es'] = {
		closeText: 'Cerrar',
		prevText: '&#x3c;Ant',
		nextText: 'Sig&#x3e;',
		currentText: 'Hoy',
		monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
		'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
		monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
		'Jul','Ago','Sep','Oct','Nov','Dic'],
		dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
		dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
		dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
		weekHeader: 'Sm',
		dateFormat: 'dd/mm/yy',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''};
	$.datepicker.setDefaults($.datepicker.regional['es']);
	
	
	//Se define una funcion que intercepta el backspace y solamente lo habilita para los campos de texto
	//Esto se hace para evitar que el usuario sin querer al apretar el backspace vuelva a la pagina anterior perdiendo los datos del formulario
	$(document).keydown(function(e) {
		var element = e.target.nodeName.toLowerCase();
		if (element != 'input' && element != 'textarea') {
		    if (e.keyCode === 8) {
		        return false;
		    }
		}
	});
});



//Show exit dialog
function exit(exitUrl)
{
	$("#exitDialog").dialog({
		resizable: false,
		height:180,
		modal: true,
		buttons: {
			"Salir": function() {
				$( this ).dialog( "close" );
				window.location = exitUrl;
			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}
		}
	});
}


/*
 * Funcion que imprime la fecha en el menu
 */
function showDate()
{
	var today=new Date();
	var displayDate = checkTime(today.getDate()) + '/' + checkTime(today.getMonth()+1) + '/' + today.getFullYear();
	$("#menu-date").html(displayDate);
}

/*
 * Funcion que muestra el reloj en el menu
 */
function clock()
{
	var today=new Date();
	var h=today.getHours();
	var m=today.getMinutes();
	var s=today.getSeconds();
	// add a zero in front of numbers<10
	m=checkTime(m);
	s=checkTime(s);
	
	$("#menu-hour").html(h+":"+m+":"+s);
}

/*
 * Agrega un 0 adelante de los numeros < 10
 */
function checkTime(i)
{
	if (i<10)
	  {
		i="0" + i;
	  }
	return i;
}

/*
 * Funcion utilitaria que convierte un array en un objeto JS.
 */
function toObject(arr) {
	var rv = {};
 	for (var i = 0; i < arr.length; ++i) {
 		rv[arr[i].name] = arr[i].value;
  	}
	return rv;
}


//Objeto para guardar los diagnosticos
function diagnostico(id,codigo,descripcion,complicacion)
{
	this.id = id;
	this.codigo = codigo;
  	this.descripcion = descripcion;
  	this.complicacion = complicacion;
}

function deCamelize(aString){
var deCamelized = aString
					// insert a space between lower & upper
					.replace(/([a-z])([A-Z])/g, '$1 $2')
					// space before last upper in a sequence followed by lower
					.replace(/\b([A-Z]+)([A-Z])([a-z])/, '$1 $2$3')
					// uppercase the first character
					.replace(/^./, function(str){ return str.toUpperCase(); })
return deCamelized;
}


var _select_width_fix;
function SetWidthToWidth(drpLst) {
	var selectedOption = $(drpLst).find("option:selected");
	$(drpLst).attr("title", selectedOption.text());
	if($.browser.msie) {
		_select_width_fix = $(drpLst).css("width");
	}
}

function SetWidthToAuto(drpLst) {
	if($.browser.msie) {
		drpLst.style.width = 'auto';
	}
}

function ResetWidth(drpLst) {
	if($.browser.msie) {
		drpLst.style.width = _select_width_fix;
	}
}



/*Funcion que con parametro loader true muestra el loader y con false vuelve a la lupita*/
function magImgSwitch(img,loader){
	if(loader){
		img.attr("src",context+"/resources/images/standard/loader.gif");
	}else{
		img.attr("src",context+"/resources/images/standard/lupa-table.png");
	}
}


/* Objecto para realizar la busqueda por codigo 
 * Contiene muchos parametros hardcodeados de la grilla que en este caso no se utilizan 
 */
function searchObject(value){
	this.page="1";
	this.rows="10";
	this.sord="asc";
	this.sidx="codigo";
	this.descripcion="";
	this.codigo=value;
}

//Agrego el metodo para los forms asi no tengo que llamarlo sin usar jquery
jQuery.fn.reset = function() {
    var $element = $(this[0]);
    if ($element.is("form"))
    	document.getElementById($element.attr("id")).reset();
    else
    	console.log("Esta funcion es solo para formularios");
    
    
    // Para contar atributos en un objeto JS (compatibilidad  para browser viejos) uso var len = Object.keys(obj).length;
    if (!Object.keys) {
        Object.keys = function (obj) {
            var keys = [],
                k;
            for (k in obj) {
                if (Object.prototype.hasOwnProperty.call(obj, k)) {
                    keys.push(k);
                }
            }
            return keys;
        };
    }
};
