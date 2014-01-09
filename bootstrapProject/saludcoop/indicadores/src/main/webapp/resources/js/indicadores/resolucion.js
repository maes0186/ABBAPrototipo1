(function(window, $) {	
	
	var Indicadores = window.Indicadores;
	
	/**
	 * Metodo inicial para el indicador de tiempo de resolucion
	 */
	Indicadores.resolucion = function(){
		var html = _.template($("#1-chart-template").html(),{'title':'Tiempo de resolución'});
		$("#indicadores").html(html);
		
		Indicadores.resolucion.drawChart();
	};
	
	
	/**
	 * Funcion que dibuja el grafico de torta de las practicas
	 */
	Indicadores.resolucion.drawChart = function(){
		var response = $.parseJSON('{"series":[{"name":"CTC","type":null,"data":[{"name":"ago.13","description":null,"category":"","y":10.0,"periodo":"","percent":0.0,"aditionalData":[]},{"name":"sept.13","description":null,"category":"","y":9.5,"periodo":"","percent":0.0,"aditionalData":[]},{"name":"oct.13","description":null,"category":"","y":8.0,"periodo":"","percent":0.0,"aditionalData":[]},{"name":"nov.13","description":null,"category":"","y":7.0,"periodo":"","percent":0.0,"aditionalData":[]}],"showInit":true},{"name":"Alto Costo","type":null,"data":[{"name":"ago.13","description":null,"category":"","y":8.0,"periodo":"","percent":0.0,"aditionalData":[]},{"name":"sept.13","description":null,"category":"","y":6.5,"periodo":"","percent":0.0,"aditionalData":[]},{"name":"oct.13","description":null,"category":"","y":6.0,"periodo":"","percent":0.0,"aditionalData":[]},{"name":"nov.13","description":null,"category":"","y":5.5,"periodo":"","percent":0.0,"aditionalData":[]}],"showInit":true}],"categories":[],"subtitle":null,"title":null,"noData":false}');
	
		var options = createOptions('line', response, 'chart-container-1');

		setxAxisTitle(options, "Meses");
		setyAxisTitle(options, "Tiempo de Resolución");
		options.setTooltipFormatter(function() {
					return '<b>' + this.point.series.name + ': </b>' + this.y;
				});
		//Oculto las leyendas
		options.legend.enabled = true; 
		
		new Highcharts.Chart(options);
		
	};
	

})(window, jQuery);