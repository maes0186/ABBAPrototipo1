
var radial;

/**
 * 
 * Reliza la llamada AJAX al controller para un evento Click
 * 
 * context: name: callBack:
 * 
 * @param name
 *            Nombre del action que recibe el controller
 * @param context
 *            agrupamiento de graficos. Vinculado con el controller.
 * @param callBack
 *            funcion que se ejecutara luego del Success de la llamada Ajax
 */
function masterClickHandler(name, context, callBack, object) {

	var path = context + "/" + name;
	$.get(path, object, callBack, "json");

}

function setxAxisTitle(options, title) {
	options.xAxis.title.text = title;
}
function setyAxisTitle(options, title) {
	options.yAxis.title.text = title;
}

function sliceAnimation(point) {
	for ( var i = 0; i < point.series.data.length; i++) {
		point.series.data[i].selected = false;
		if (point.series.data[i].sliced == true) {
			point.series.data[i].slice(false, false, {duration:150}); 
		}
	}
	point.slice(!point.sliced, false, {duration:150});
};

function initCharts($this) {
	transparentBackground();
	var img = $this.renderer.image('resources/images/maximizar.png',
			$this.chartWidth - 85, 12, 16, 16);
	img.add();
	img.css({
		'cursor' : 'pointer'
	});
	img.attr({
		'title' : i18n("tooltip.maximizar")
	});
	img.on('click', function() {
		//El div donde va a estar el grafico maximizado
		var $divMax;
		//Si no existe divMax lo creo
		if ($("div#max").length == 0){
			//Creo el divMax
			$divMax = $("<div id='max'/>");

			$("body").append($divMax);
		}
		else 
			//Como existe, lo asigno del DOM a la variable
			$divMax = $("div#max");
		
		//Cargo las options y el title del div contenedor original
		var $contenedorPadre = $(this).parent().parent().parent(); 
		var options = $contenedorPadre.data("options");
		
		//Modifico el contenedor para que el graf se muestre en el divMax
		options.chart.renderTo = 'max';		
		
		$divMax.dialog({
			modal:true,
			width:900,
			height:700,
			margin:"auto",
			resizable:false
		});
		
		new Highcharts.Chart(options,transparentBackground);

	});
}

function transparentBackground() {
	$(".highcharts-container>svg>rect:not([transform]):not([id])").attr("fill",
			'none');
}
/**
 * Genera una configuraci�n de grafico en base al ChartOption obtenido del
 * Controller
 * 
 * @param options
 *            ChartOptions obtenido del controller
 * @param container
 *            Div donde se renderizara el gr�fico
 * @returns configuraci�n gen�rica del gr�fico
 */
function createOptions(type, options, container) {
	var chartOptions = createGenericOptions(type, options, container);
	switch (type) {
	case 'pie':
		return createOptionsPie(chartOptions, options);
		break;

	case 'bar':
		return createOptionsBar(chartOptions, options);
		break;

	case 'line':
		return createOptionsLine(chartOptions, options);
		break;		
		
	case 'column':
		return createOptionsColumn(chartOptions, options);
		break;
	}
}
/**
 * Crea opciones de grafico tipo Line por defecto
 * 
 * @param chartOptions
 *            Opciones genericas heredadas
 * @param options
 *            ChartOption JSON obtenido desde el controller
 * @returns JSON con opciones genericas del grafico
 */
function createOptionsLine(chartOptions, options) {
	
	chartOptions.chart.plotBorderWidth = 2;
	chartOptions.xAxis = {
		title : {
			text : 'SIN TITULO',

		},
		categories : options.categories
	};
	chartOptions.yAxis = {
		title : {
			text : 'SIN TITULO',

		}
	};
	chartOptions.tooltip = {
		formatter : function() {
			return 'SIN TOOLTIP';
		}

	};
	return chartOptions;
}

/**
 * Crea opciones de grafico tipo Column por defecto
 * 
 * @param chartOptions
 *            Opciones genericas heredadas
 * @param options
 *            ChartOption JSON obtenido desde el controller
 * @returns JSON con opciones genericas del grafico
 */
function createOptionsColumn(chartOptions, options) {
	
	chartOptions.xAxis = {
		title : {
			text : 'SIN TITULO',

		},
		categories : options.categories
	};
	chartOptions.yAxis = {
		title : {
			text : 'SIN TITULO',

		}
	};
	chartOptions.tooltip = {
		formatter : function() {
			return 'SIN TOOLTIP';
		}

	};
	chartOptions.plotOptions = {
		column : {
			borderWidth : 0,
			events : {

				legendItemClick : function(event) {
					chartOptions.handler.legendItemClick(event);
				},
				click : function(event) {
					chartOptions.handler.click(event);
				}
			}
		}
	};
	return chartOptions;
}
/**
 * Crea opciones de grafico tipo bar por defecto
 * 
 * @param chartOptions
 *            Opciones genericas heredadas
 * @param options
 *            ChartOption JSON obtenido desde el controller
 * @returns JSON con opciones genericas del grafico
 */
function createOptionsBar(chartOptions, options) {
	if (chartOptions.noData == true) {
		chartOptions.title = i18n("msg.noHayDatos");
	}

	chartOptions.xAxis = {
		title : {
			text : 'SIN TITULO',

		},
		categories : options.categories
	};
	chartOptions.yAxis = {
		title : {
			text : 'SIN TITULO',

		}
	};
	chartOptions.tooltip = {
		formatter : function() {
			return 'SIN TOOLTIP';
		}

	};
	chartOptions.plotOptions = {
		bar : {
			borderWidth : 0,
			events : {

				legendItemClick : function(event) {
					chartOptions.handler.legendItemClick(event);
				},
				click : function(event) {
					chartOptions.handler.click(event);
				}
			}
		}
	};
	return chartOptions;

}
/**
 * Crea opciones de grafico tipo Pie por defecto
 * 
 * @param chartOptions
 *            Opciones genericas heredadas
 * @param options
 *            ChartOption JSON obtenido desde el controller
 * @returns JSON con opciones genericas del grafico
 */
function createOptionsPie(chartOptions, options) {
	if (chartOptions.noData == true) {
		chartOptions.title = i18n("msg.noHayDatos");
	}
	chartOptions.legend = {
		layout : 'vertical',
		backgroundColor : '#FFFFFF',
		align : 'left',
		verticalAlign : 'middle',
		floating : false,
		x : 10
	};

	chartOptions.yAxis = {
		title : {
			text : 'SIN TITULO',

		},
		categories : options.categories
	};
	chartOptions.xAxis = {
		title : {
			text : 'SIN TITULO',

		}
	};
	chartOptions.tooltip = {
			useHTML : true,
		formatter : function() {
			return 'SIN TOOLTIP';
		}

	};

	chartOptions.plotOptions = {
		pie : {
			allowPointSelect: true,
			borderWidth : 0,
			cursor: "pointer",
			center : [ "50%", "48%" ],
			size : "85%",
			point : {
				events : {
						legendItemClick : function(event) {
						sliceAnimation(this);
						chartOptions.handler.legendItemClick(this);
						return false;
					},
					click : function(event) {
						sliceAnimation(this);
						chartOptions.handler.click(this);
						return false;
					}

				}
			},
			dataLabels : {
				enabled : false
			},
			showInLegend : true
		}

	};

	return chartOptions;
}
/**
 * Crea opciones de grafico genericas
 * 
 * @param container
 *            Div donde se renderizar� el gr�fico
 * @param options
 *            ChartOption JSON obtenido desde el controller
 * @returns JSON con opciones genericas del grafico
 */
function createGenericOptions(type, options, container) {
	

	var chartOptions = {
		handler : {
			click : function(event) {
				
			},
			legendItemClick : function(event) {

			}
		},
		setClickHandler : function(hndler) {
			this.handler.click = hndler;
		},
		setLegendClick : function(hndler) {
			this.handler.legendItemClick = hndler;
		},
		setTooltipFormatter: function(formatter){
			this.tooltip = {
					useHTML : true,
					formatter: formatter
					};
			
		},
		chart : {
			renderTo : container,
			backgroundColor: '',
			type : type,
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false
		},
		credits : {
			enabled : false,
			text : "conexia.com"
		},
		title : {
			text : ""
		},
		subtitle : {
			text : options.subtitle
		},
		legend : {
			layout : 'vertical',
			backgroundColor : '#FFFFFF',
			align : 'left',
			verticalAlign : 'top',
			y : 70,
			floating : false,
			shadow : true
		},
		series : options.series,
		lang : {
			downloadJPEG: "Descargar en formato JPEG",
			downloadPDF:  "Descargar en formato PDF",
			downloadPNG:  "Descargar en formato PNG",
			downloadSVG:  "Descargar en formato SVG",
			exportButtonTitle : "Exportar como...",
			printButtonTitle  : "Imprimir"
		}

	};

	if (options.noData == true) {
		chartOptions.title.text = i18n("msg.noHayDatos");
	}
/**
 * Se agrega Funcion GetData para obtener un atributo adicional.
 * Ver AditionalDataNode
 */
	$(options.series).each(function(i,e){
		$(e.data).each(function(i,point){
			this.getData = function getData(nom){
				var data = $.grep(this.aditionalData, function(element){
					return element.nombre == nom;
				})[0]; 			
				return data.data; 
			};
		});
	});
	return chartOptions;
}
function toMoney(obj, decimals, decimal_sep, thousands_sep)
{ 
   var n = obj,
   c = (typeof decimals === 'undefined') ? 0 : Math.abs(decimals), //if decimal is zero we must take it, it means user does not want to show any decimal
   d = decimal_sep || ',', //if no decimal separator is passed we use the dot as default decimal separator (we MUST use a decimal separator)

   /*
   according to [http://stackoverflow.com/questions/411352/how-best-to-determine-if-an-argument-is-not-sent-to-the-javascript-function]
   the fastest way to check for not defined parameter is to use typeof value === 'undefined' 
   rather than doing value === undefined.
   */   
   t = (typeof thousands_sep === 'undefined') ? '.' : thousands_sep, //if you don't want to use a thousands separator you can pass empty string as thousands_sep value

   sign = (n < 0) ? '-' : '',

   //extracting the absolute value of the integer part of the number and converting to string
   i = parseInt(n = Math.abs(n).toFixed(c)) + '', 

   j = ((j = i.length) > 3) ? j % 3 : 0; 
   return sign + (j ? i.substr(0, j) + t : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : ''); 
}
