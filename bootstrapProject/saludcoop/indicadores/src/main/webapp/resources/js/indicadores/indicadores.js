/**
 * Closure de entrada para encapsular contexto de ejecución.
 * @param {Window} window Objeto global
 * @param {jQuery} $ Objeto jQuery
 */
(function(window, $) {
	
	/**
	 * Configuracion de underscore para que no entre en conflicto con jsp
	 * Se cambia el % por @
	 */
	_.templateSettings = {
	    interpolate: /\<\@\=(.+?)\@\>/gim,
	    evaluate: /\<\@(.+?)\@\>/gim,
	    escape: /\<\@\-(.+?)\@\>/gim
	};

	window.Indicadores = window.Indicadores || {};
	/**
	 * Namespace de indicadores
	 */

	var Indicadores = window.Indicadores;

	/**
	 * Metodo de inicio
	 * Se definen las rutas de la aplicacion
	 */
	Indicadores.startup = function(){

		var routes = {
				'/practicas': Indicadores.practicas,
				'/solicitudes': Indicadores.solicitudes,
				'/afiliados': Indicadores.afiliados,
				'/resolucion': Indicadores.resolucion,
				'/montos': Indicadores.montos
		};

		/**
		 * Funcion que se va a ejecutar siempre que se cargue una ruta
		 */
		var allroutes = function() {
			//La ruta de la url
			var route = window.location.hash;
			
			//La barra de navegacion
			var navbar = $(".navbar-responsive-collapse");
			
			//Elimino la clase active de todos
			navbar.find('.active').removeClass("active");
			
			//Agrego la clase active al li padre del link correspondiente
			var a = navbar.find('a[href$="'+route+'"]');
			a.parent().addClass("active");
			
			//Cierro la navbar en caso de que este abierta
			if(navbar.hasClass("in")){
				navbar.collapse('hide');
			}
		};

		//Se inicia el router
		var router = Router(routes);   
		router.configure({
			on: allroutes
		});
		router.init();
		
		
		
		Indicadores.defaultChartConfig = {
			chart: {
		        renderTo: 'chart-container-1',
		        type: 'pie'
		    },
		    series: [{}]
		};
		
		
	};
	
	//Namespace
	Indicadores.util = {};
	
	Indicadores.util.getSign = function(){
		return "$ ";
	};
	
	Indicadores.util.getTipo = function(){
		return "Importes";
	};
	

})(window, jQuery);
