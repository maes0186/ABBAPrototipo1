(function(window, $) {	
	
	var Indicadores = window.Indicadores;
	
	/**
	 * Metodo inicial para el indicador de afiliados
	 */
	Indicadores.afiliados = function(){
		var html = _.template($("#1-chart-template").html(),{'title':'Afiliados por programa'});
		$("#indicadores").html(html);
		
		Indicadores.afiliados.drawChart();
	};
	
	
	/**
	 * Funcion que dibuja el grafico de torta de las practicas
	 */
	Indicadores.afiliados.drawChart = function(){
		var datos = $.parseJSON('{"series":[{"name":"Afiliados","type":null,"data":[{"name":"Hipertensión","description":"","y":10.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Diabétes","description":"","y":10.0,"category":"","aditionalData":[],"percent":0.0},{"name":"VIH","description":"","y":1.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Renal","description":"","y":5.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Control Prenatal","description":"","y":5.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Enfermedad Renal Cronica","description":"","y":7.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Enf. Respi. Crónica","description":"","y":4.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Sind. Convulsivo","description":"","y":3.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Cáncer","description":"","y":15.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Formulación Crónica","description":"","y":20.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Plan canguro","description":"","y":5.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Hemofilia","description":"","y":5.0,"category":"","aditionalData":[],"percent":0.0},{"name":"Artritis","description":"","y":10.0,"category":"","aditionalData":[],"percent":0.0}],"showInit":true}],"categories":[],"subtitle":null,"title":"Afiliados","noData":false}');
		
		var options = createOptions("pie",datos,"chart-container-1");
		options.setTooltipFormatter( 
			function() {
				return '<p><b>' + this.point.name + '</b>: '
						+ Highcharts.numberFormat(this.percentage, 2, ',')
						+ ' %</p>';
			}
		);
		
		new Highcharts.Chart(options);
		
	};
	

})(window, jQuery);