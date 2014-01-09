(function(window, $) {	
	
	var Indicadores = window.Indicadores;
	
	/**
	 * Metodo inicial para el indicador de montos
	 */
	Indicadores.montos = function(){
		var html = _.template($("#3-chart-template").html(),{'title':'Montos'});
		$("#indicadores").html(html);
		
		Indicadores.montos.drawChart();
	};
	
	
	/**
	 * Funcion que dibuja el grafico de torta de las practicas
	 */
	Indicadores.montos.drawChart = function(){
		
		//Chart CTC
		var response = $.parseJSON('{"series":[{"name":"Activos","type":null,"data":[{"name":"oct.12","description":null,"afiliadosActivos":190737,"category":"oct.12","y":193839.0,"periodo":"2012-10-01","percent":0.0,"aditionalData":[]},{"name":"nov.12","description":null,"afiliadosActivos":191504,"category":"nov.12","y":191504.0,"periodo":"2012-11-01","percent":0.0,"aditionalData":[]},{"name":"dic.12","description":null,"afiliadosActivos":192886,"category":"dic.12","y":192886.0,"periodo":"2012-12-01","percent":0.0,"aditionalData":[]},{"name":"ene.13","description":null,"afiliadosActivos":193839,"category":"ene.13","y":190737.0,"periodo":"2013-01-01","percent":0.0,"aditionalData":[]},{"name":"feb.13","description":null,"afiliadosActivos":194486,"category":"feb.13","y":184486.0,"periodo":"2013-02-01","percent":0.0,"aditionalData":[]},{"name":"mar.13","description":null,"afiliadosActivos":195437,"category":"mar.13","y":185437.0,"periodo":"2013-03-01","percent":0.0,"aditionalData":[]},{"name":"abr.13","description":null,"afiliadosActivos":196206,"category":"abr.13","y":186206.0,"periodo":"2013-04-01","percent":0.0,"aditionalData":[]},{"name":"may.13","description":null,"afiliadosActivos":196527,"category":"may.13","y":176527.0,"periodo":"2013-05-01","percent":0.0,"aditionalData":[]},{"name":"jun.13","description":null,"afiliadosActivos":198135,"category":"jun.13","y":178135.0,"periodo":"2013-06-01","percent":0.0,"aditionalData":[]},{"name":"jul.13","description":null,"afiliadosActivos":199540,"category":"jul.13","y":179540.0,"periodo":"2013-07-01","percent":0.0,"aditionalData":[]},{"name":"ago.13","description":null,"afiliadosActivos":200315,"category":"ago.13","y":180315.0,"periodo":"2013-08-01","percent":0.0,"aditionalData":[]},{"name":"sep.13","description":null,"afiliadosActivos":201193,"category":"sep.13","y":178193.0,"periodo":"2013-09-01","percent":0.0,"aditionalData":[]},{"name":"oct.13","description":null,"afiliadosActivos":200401,"category":"oct.13","y":175737.0,"periodo":"2013-10-01","percent":0.0,"aditionalData":[]}],"showInit":true}],"title":null,"noData":false,"subtitle":null,"categories":["oct.12","nov.12","dic.12","ene.13","feb.13","mar.13","abr.13","may.13","jun.13","jul.13","ago.13","sep.13","oct.13"]}');
	
		var options = createOptions('line', response, 'chart-container-1');

		setxAxisTitle(options, "Meses");
		setyAxisTitle(options, "Montos");
		options.setTooltipFormatter(function() {
					return '<b>$ </b>' + toMoney(this.y);
				});
		//Oculto las leyendas
		options.legend.enabled = false;
		options.title.text = "CTC";
		
		new Highcharts.Chart(options);
		
		
		
		//Chart Alto Costo
		var response = $.parseJSON('{"series":[{"name":"Activos","type":null,"data":[{"name":"oct.12","description":null,"afiliadosActivos":190737,"category":"oct.12","y":183839.0,"periodo":"2012-10-01","percent":0.0,"aditionalData":[]},{"name":"nov.12","description":null,"afiliadosActivos":191504,"category":"nov.12","y":181504.0,"periodo":"2012-11-01","percent":0.0,"aditionalData":[]},{"name":"dic.12","description":null,"afiliadosActivos":192886,"category":"dic.12","y":182886.0,"periodo":"2012-12-01","percent":0.0,"aditionalData":[]},{"name":"ene.13","description":null,"afiliadosActivos":193839,"category":"ene.13","y":180737.0,"periodo":"2013-01-01","percent":0.0,"aditionalData":[]},{"name":"feb.13","description":null,"afiliadosActivos":194486,"category":"feb.13","y":184486.0,"periodo":"2013-02-01","percent":0.0,"aditionalData":[]},{"name":"mar.13","description":null,"afiliadosActivos":195437,"category":"mar.13","y":185437.0,"periodo":"2013-03-01","percent":0.0,"aditionalData":[]},{"name":"abr.13","description":null,"afiliadosActivos":196206,"category":"abr.13","y":176206.0,"periodo":"2013-04-01","percent":0.0,"aditionalData":[]},{"name":"may.13","description":null,"afiliadosActivos":196527,"category":"may.13","y":166527.0,"periodo":"2013-05-01","percent":0.0,"aditionalData":[]},{"name":"jun.13","description":null,"afiliadosActivos":198135,"category":"jun.13","y":168135.0,"periodo":"2013-06-01","percent":0.0,"aditionalData":[]},{"name":"jul.13","description":null,"afiliadosActivos":199540,"category":"jul.13","y":169540.0,"periodo":"2013-07-01","percent":0.0,"aditionalData":[]},{"name":"ago.13","description":null,"afiliadosActivos":200315,"category":"ago.13","y":160315.0,"periodo":"2013-08-01","percent":0.0,"aditionalData":[]},{"name":"sep.13","description":null,"afiliadosActivos":201193,"category":"sep.13","y":168193.0,"periodo":"2013-09-01","percent":0.0,"aditionalData":[]},{"name":"oct.13","description":null,"afiliadosActivos":200401,"category":"oct.13","y":165737.0,"periodo":"2013-10-01","percent":0.0,"aditionalData":[]}],"showInit":true}],"title":null,"noData":false,"subtitle":null,"categories":["oct.12","nov.12","dic.12","ene.13","feb.13","mar.13","abr.13","may.13","jun.13","jul.13","ago.13","sep.13","oct.13"]}');
		var options = createOptions('line', response, 'chart-container-2');

		setxAxisTitle(options, "Meses");
		setyAxisTitle(options, "Montos");
		options.setTooltipFormatter(function() {
					return '<b>$ </b>' + toMoney(this.y);
				});
		//Oculto las leyendas
		options.legend.enabled = false; 
		options.title.text = "Alto Costo";
		
		new Highcharts.Chart(options);
		
		
		
		//Chart POS
		var response = $.parseJSON('{"series":[{"name":"Activos","type":null,"data":[{"name":"oct.12","description":null,"afiliadosActivos":190737,"category":"oct.12","y":2183839.0,"periodo":"2012-10-01","percent":0.0,"aditionalData":[]},{"name":"nov.12","description":null,"afiliadosActivos":191504,"category":"nov.12","y":2181504.0,"periodo":"2012-11-01","percent":0.0,"aditionalData":[]},{"name":"dic.12","description":null,"afiliadosActivos":192886,"category":"dic.12","y":2182886.0,"periodo":"2012-12-01","percent":0.0,"aditionalData":[]},{"name":"ene.13","description":null,"afiliadosActivos":193839,"category":"ene.13","y":2180737.0,"periodo":"2013-01-01","percent":0.0,"aditionalData":[]},{"name":"feb.13","description":null,"afiliadosActivos":194486,"category":"feb.13","y":2184486.0,"periodo":"2013-02-01","percent":0.0,"aditionalData":[]},{"name":"mar.13","description":null,"afiliadosActivos":195437,"category":"mar.13","y":2185437.0,"periodo":"2013-03-01","percent":0.0,"aditionalData":[]},{"name":"abr.13","description":null,"afiliadosActivos":196206,"category":"abr.13","y":2176206.0,"periodo":"2013-04-01","percent":0.0,"aditionalData":[]},{"name":"may.13","description":null,"afiliadosActivos":196527,"category":"may.13","y":2176527.0,"periodo":"2013-05-01","percent":0.0,"aditionalData":[]},{"name":"jun.13","description":null,"afiliadosActivos":198135,"category":"jun.13","y":2178135.0,"periodo":"2013-06-01","percent":0.0,"aditionalData":[]},{"name":"jul.13","description":null,"afiliadosActivos":199540,"category":"jul.13","y":2179540.0,"periodo":"2013-07-01","percent":0.0,"aditionalData":[]},{"name":"ago.13","description":null,"afiliadosActivos":200315,"category":"ago.13","y":2180315.0,"periodo":"2013-08-01","percent":0.0,"aditionalData":[]},{"name":"sep.13","description":null,"afiliadosActivos":201193,"category":"sep.13","y":2188193.0,"periodo":"2013-09-01","percent":0.0,"aditionalData":[]},{"name":"oct.13","description":null,"afiliadosActivos":200401,"category":"oct.13","y":2185737.0,"periodo":"2013-10-01","percent":0.0,"aditionalData":[]}],"showInit":true}],"title":null,"noData":false,"subtitle":null,"categories":["oct.12","nov.12","dic.12","ene.13","feb.13","mar.13","abr.13","may.13","jun.13","jul.13","ago.13","sep.13","oct.13"]}');
		var options = createOptions('line', response, 'chart-container-3');

		setxAxisTitle(options, "Meses");
		setyAxisTitle(options, "Montos");
		options.setTooltipFormatter(function() {
					return '<b>$ </b>' + toMoney(this.y);
				});
		//Oculto las leyendas
		options.legend.enabled = false; 
		options.title.text = "POS";
		
		new Highcharts.Chart(options);
		
	};
	

})(window, jQuery);