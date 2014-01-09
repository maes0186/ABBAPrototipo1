
function changeTipoIdentificacion(){
		var $option = $(this).find("option:selected");
		$(this).parents(".identificacionComponent").find("[id^=numero]").attr("maxLength", $option.attr("data-max-length"));
}
						
function keypressNumeroIdentificacion(evt) {
	if(!$.parseJSON($(this).parents(".identificacionComponent").find("[id^=tipo]>option:selected").attr("data-alpha"))){
		allowOnlyDigits(evt);
	}
}
		
