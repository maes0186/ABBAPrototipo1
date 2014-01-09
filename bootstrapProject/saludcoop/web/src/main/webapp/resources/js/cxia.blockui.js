/**
 * 
 * Bloquea un contenedor especificado mientras carga info (ej: contenedores de gráficos o tablas dinámicas)
 * 
 * @param $this se refiere al contenedor que se quiera bloquear
 * @param data  recibe configuraciones específicas para acomodar el estilo del .blockOverlay
 * 				{
 * 					tl: BORDER_TOP_LEFT_RADIUS,
 *				  	tr: BORDER_TOP_RIGHT_RADIUS,
 *				 	bl: BORDER_BOTTOM_LEFT_RADIUS,
 *				 	br: BORDER_BOTTOM_RIGHT_RADIUS,
 * 				} 
 */	
	function showLoading($this, data) {
		
		var id_aux = (new Date()).getSeconds() + (new Date()).getMilliseconds();
		$this.block({
			message : '<img id="loadingimage'+id_aux+'" src="resources/images/loading30.gif"/><br/>'+i18n("block.cargando"),
			css: { 
	            border: 'none', 
	            padding: '15px', 
	            backgroundColor: '#E0E0E0', 
	            '-webkit-border-radius': '10px', 
	            '-moz-border-radius': '10px', 
	            opacity: .7, 
	            color: '#000',
	            'font-weight': 'bold',
	            'font-size': 'normal',
	            'font-family': 'Tahoma',
	            height:"50px",
	            "z-index":0
        	} 
		});
		if (data){
			$("#loadingimage"+id_aux).parent().parent().find(".blockOverlay").css({
				"z-index":0,
				"-webkit-border-top-left-radius": data.tl ? data.tl : 0 ,
						"-moz-border-radius-topleft": data.tl ? data.tl : 0 ,
							"border-top-left-radius": data.tl ? data.tl : 0 ,
					"-webkit-border-top-right-radius": data.tr ? data.tr : 0 ,
						"-moz-border-radius-topright": data.tr ? data.tr : 0 ,
							"border-top-right-radius": data.tr ? data.tr : 0 ,
					"-webkit-border-bottom-right-radius": data.br ? data.br : 0 ,
						"-moz-border-radius-bottomright": data.br ? data.br : 0 ,
							"border-bottom-right-radius": data.br ? data.br : 0 ,
					"-webkit-border-bottom-left-radius": data.bl ? data.bl : 0,
						"-moz-border-radius-bottomleft": data.bl ? data.bl : 0,
							"border-bottom-left-radius": data.bl ? data.bl : 0
			});
		}
	}
	function hideLoading($this) {
		$this.unblock();
	}
	
	/**
	 * Por temas de compatibilidad con IE se codea esta funcion por fuera de la libreria de BlockUI
	 * 
	 * @param $container
	 * @param message
	 * @param data
	 */
	function showBlockingMessage($container, message, data){
		
		$container.css({	
			background:"rgba(0,0,0,0)"						
});

		if (data){
			$container.css({
				"z-index":0,
				"-webkit-border-top-left-radius": data.tl ? data.tl : 0 ,
					"-moz-border-radius-topleft": data.tl ? data.tl : 0 ,
						"border-top-left-radius": data.tl ? data.tl : 0 ,
				"-webkit-border-top-right-radius": data.tr ? data.tr : 0 ,
					"-moz-border-radius-topright": data.tr ? data.tr : 0 ,
						"border-top-right-radius": data.tr ? data.tr : 0 ,
				"-webkit-border-bottom-right-radius": data.br ? data.br : 0 ,
					"-moz-border-radius-bottomright": data.br ? data.br : 0 ,
						"border-bottom-right-radius": data.br ? data.br : 0 ,
				"-webkit-border-bottom-left-radius": data.bl ? data.bl : 0,
					"-moz-border-radius-bottomleft": data.bl ? data.bl : 0,
						"border-bottom-left-radius": data.bl ? data.bl : 0
			});

		}

		
		var $message = $("<div id='mensaje'/>").css({ 
            border: 'none', 
            padding: '10px', 
            backgroundColor: '#E0E0E0', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .9, 
            color: '#000',
            'font-weight': 'bold',
            'font-size': 'normal',
            'font-family': 'Tahoma',
            position:"absolute",
            width:'50%',
            height: '50%',
            top:"25%",
           	left:"25%"
    	});
		
		$message.append($("<img src='resources/images/invisible.png'/>")).append(message).appendTo($container);

	}
	function hideBlocking($container){
		$container.css({	
			background:"rgba(0,0,0,0)"						
});
		
		$container.css({"-webkit-border-top-left-radius": 0 ,
					"-moz-border-radius-topleft": 0 ,
						"border-top-left-radius": 0 ,
				"-webkit-border-top-right-radius": 0 ,
					"-moz-border-radius-topright": 0 ,
						"border-top-right-radius": 0 ,
				"-webkit-border-bottom-right-radius": 0 ,
					"-moz-border-radius-bottomright": 0 ,
						"border-bottom-right-radius": 0 ,
				"-webkit-border-bottom-left-radius": 0,
					"-moz-border-radius-bottomleft": 0,
						"border-bottom-left-radius": 0,
						background:"#fff"});
		$container.find("#mensaje").remove();
	}