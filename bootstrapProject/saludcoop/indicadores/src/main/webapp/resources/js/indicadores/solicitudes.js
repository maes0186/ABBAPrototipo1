(function(window, $) {	
	
	var Indicadores = window.Indicadores;
	
	/**
	 * Metodo inicial para el indicador de estado de solicitudes
	 */
	Indicadores.solicitudes = function(){
		var html = _.template($("#1-chart-template").html(),{'title':'Estado de Solicitudes'});
		$("#indicadores").html(html);
		
		Indicadores.solicitudes.drawChart();
	};
	
	
	/**
	 * Funcion que dibuja el grafico de torta de las practicas
	 */
	Indicadores.solicitudes.drawChart = function(){
		var response = $.parseJSON('{"categories":["ene.2011-dic.2011","ene.2012-dic.2012","ene.2013-dic.2013"],"series":[{"name":"Autorizado Sistema","type":null,"data":[{"name":"Autorizado Sistema","description":"","y":5827671.0,"category":"Autorizado Sistema","cantidad":5827671,"estado":"Autorizado Sistema","aditionalData":[],"percent":91.7866328563666},{"name":"Autorizado Sistema","description":"","y":5837580.0,"category":"Autorizado Sistema","cantidad":5837580,"estado":"Autorizado Sistema","aditionalData":[],"percent":92.09927621610399},{"name":"Autorizado Sistema","description":"","y":4440886.0,"category":"Autorizado Sistema","cantidad":4440886,"estado":"Autorizado Sistema","aditionalData":[],"percent":92.03523500156055}],"showInit":false},{"name":"Autorizado Auditor","type":null,"data":[{"name":"Autorizado Auditor","description":"","y":214444.0,"category":"Autorizado Auditor","cantidad":214444,"estado":"Autorizado Auditor","aditionalData":[],"percent":3.3775229755164076},{"name":"Autorizado Auditor","description":"","y":216724.0,"category":"Autorizado Auditor","cantidad":216724,"estado":"Autorizado Auditor","aditionalData":[],"percent":3.419246252498282},{"name":"Autorizado Auditor","description":"","y":176061.0,"category":"Autorizado Auditor","cantidad":176061,"estado":"Autorizado Auditor","aditionalData":[],"percent":3.648779885277342}],"showInit":true},{"name":"Rechazado Sistema","type":null,"data":[{"name":"Rechazado Sistema","description":"","y":256752.0,"category":"Rechazado Sistema","cantidad":256752,"estado":"Rechazado Sistema","aditionalData":[],"percent":4.043879889434018},{"name":"Rechazado Sistema","description":"","y":235358.0,"category":"Rechazado Sistema","cantidad":235358,"estado":"Rechazado Sistema","aditionalData":[],"percent":3.7132341572483463},{"name":"Rechazado Sistema","description":"","y":170794.0,"category":"Rechazado Sistema","cantidad":170794,"estado":"Rechazado Sistema","aditionalData":[],"percent":3.5396238333649035}],"showInit":true},{"name":"Rechazado Auditor","type":null,"data":[{"name":"Rechazado Auditor","description":"","y":50283.0,"category":"Rechazado Auditor","cantidad":50283,"estado":"Rechazado Auditor","aditionalData":[],"percent":0.7919642786829734},{"name":"Rechazado Auditor","description":"","y":48694.0,"category":"Rechazado Auditor","cantidad":48694,"estado":"Rechazado Auditor","aditionalData":[],"percent":0.7682433741493851},{"name":"Rechazado Auditor","description":"","y":37461.0,"category":"Rechazado Auditor","cantidad":37461,"estado":"Rechazado Auditor","aditionalData":[],"percent":0.7763612797971982}],"showInit":true}],"title":null,"noData":false,"subtitle":null}');
	
		var options = createOptions( 'column', response, 'chart-container-1');
		options.setTooltipFormatter(
				function() {
							return '<p><b>' + this.point.series.name + ': </b>' + toMoney(this.y)
									+ ' trans.</p>' + 
									'<p><b>Porcentaje: </b>' + toMoney(this.point.percent,2) + '%';
							});
		setyAxisTitle(options,'Transacciones');
		setxAxisTitle(options,'Estado');

		masterChart = new Highcharts.Chart(options);
		
		$.each(options.series,function(i,e){
			if(e.showInit == false){
				masterChart.series[i].hide();
			}
		});
		
	};
	

})(window, jQuery);