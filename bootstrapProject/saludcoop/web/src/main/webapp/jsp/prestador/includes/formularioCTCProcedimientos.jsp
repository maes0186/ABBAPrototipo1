<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="webContext" value="${pageContext.request.contextPath}" />

<fmt:message key='label.respuestaClinicaObservada' var="RESPUESTAS_CLINICAS_OBSERVADAS" />
<fmt:message key='label.objetivo' var="OBJETIVO" />
<fmt:message key='label.infoSolicitud.resHistClin' var="RESUMEN_HISTORIA_CLINICA"/>
<fmt:message key='message.error.minLength.30' var="MINLENGTH_30"/>
<fmt:message key="label.frecuencia.uso.dias" var="FRECUENCIA" />
<fmt:message key="label.tiempo.uso.dias" var="TIEMPO_DE_USO" />
<fmt:message key="label.cantidad.periodo" var="CANTIDAD_PERIODO" />
<fmt:message key="label.nopos.justificacion.autorizadoINVIMA.mini" var="AUTORIZADO_INVIMA"/>
<fmt:message key="label.nopos.justificacion.medico" var="JUSTIFICACION_MEDICO" />
<fmt:message key="label.justificacion.noHomologo.mini" var="JUSTIFICACION_NO_HOMOLOGO" />
<fmt:message key="label.infoSolicitud.justificacionRiesgoInminente" var="JUSTIFICACION_RIESGO_INMINENTE"/>
<fmt:message key='label.codigoCups' var="CODIGO_CUPS" />
<fmt:message key='label.name' var="NOMBRE" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />

<script>
function fillformularioCTCProcedimiento($container) {
	
	$("#formProcedimientoHomologo #codigo").attr("name", "procedimientoPosHomologo.codigo");
	$("#formProcedimientoHomologo #descripcion").attr("name", "procedimientoPosHomologo.descripcion");
	
	if ($container.data().data) {
		var mapaProcedimientos = new Array();
		$.each($container.data().data, function(i, e) {
			if (e.name.indexOf("[") != -1)  {
				agregaAMapa(e, mapaProcedimientos);
			} else {
				switch($("#formularioCTCProcedimientoForm [name='" + e.name + "']").prop("type")) {
				case "radio":
					$("#formularioCTCProcedimientoForm [name='" + e.name + "'][value=" + e.value + "]").prop("checked", true);
					break;
				case "checkbox":
					if (e.value) {
						$("#formularioCTCProcedimientoForm [name='" + e.name + "']").click();	
					}
					break;
				default:
					$("#formularioCTCProcedimientoForm [name='" + e.name + "']").val(e.value);
				}
			}
		});
		for (var tipoProcedimiento in mapaProcedimientos) {
			for (var procedimiento in mapaProcedimientos[tipoProcedimiento]) {
				var copiaCamposP = $.extend(true, [], $("[javaobject=" + tipoProcedimiento + "]")[0].techFields);
				var proc = mapaProcedimientos[tipoProcedimiento][procedimiento];
				copiaCamposP.filter(function(e){return e.name == "respuestaClinicaObservada"})[0].value = proc.respuestaClinicaObservada;
				$("[javaobject=" + tipoProcedimiento + "]")[0].addRow(proc, copiaCamposP);	
			}
		}
	} else {
	    resetFieldsCTCProcedimiento();
	}
}

	function applyCTCProcedimiento() {
		// Para el manejo dentro del contexto de javascript
		$container.data({data:$("#formularioCTCProcedimientoForm").serializeArray()});
		// Para el envío al servidor.
		$container.attr("data-form-ctc", $("#formularioCTCProcedimientoForm").serialize());
	}
	
	function resetFieldsCTCProcedimiento() {
		document.getElementById("formularioCTCProcedimientoForm").reset();
		$("#procHomologoTable tbody").empty();
		$("#procHomologoTable thead").hide();
		$("#sinAlternativaPosCTCProcedimiento").change();
		$("#formularioCTCProcedimientoForm .conRiesgoInminente").toggle(false);
		$("#formProcedimientoHomologo input").removeAttr("readonly");
		$("#formProcedimientoHomologo button").removeAttr("disabled");
		$("#formularioCTCProcedimiento #formProcedimientos")[0].currentIndex=0;
		resetPosPrevios();
	}
	
	function resetPosPrevios(){
		$("#procPreviosTable tbody").empty();
		$("#procPreviosTable thead").hide();
	}
	
	function resetHomologos(){
		$("[name^=procedimientoPosHomologo]").val("");
	}
	
	function tieneProcedimientosPosPrevios(){
		var valido=true;
		if($("#sinAlternativaPosCTCProcedimiento").is(":checked")){
			valido=true;
		}
		else if ($("#formularioCTCProcedimientoForm #formProcedimientos").find("tr #descripcion").length == 0 ){
			appendErrorMessage($("#procedimientos-ctc-messages"), '<fmt:message key="validation.required.procedimientoPosPrevio"/>');
			valido = false;
		}else{
			$.each($("#formularioCTCProcedimientoForm #formProcedimientos").find("tr #descripcion"), function(i,e){
				var tr = $(e).parents("tr");
				var respClinica = validateRequired(tr.find("[name=respuestaClinicaObservada]"), '<fmt:message key="validation.required"><fmt:param value="${RESPUESTAS_CLINICAS_OBSERVADAS}"/></fmt:message>');
				valido = valido && respClinica;
			});
		}
		return valido;
		
	}
	
	function tieneHomologo(){
		var valido=true;
		if($("#sinHomologoProcedimientoCTC").is(":checked")){
			valido=true;
		}
		else if ($("[name='procedimientoPosHomologo.codigo']").val()=='' && $("[name='procedimientoPosHomologo.descripcion']").val()=='' ){
			appendErrorMessage($("#procedimientos-ctc-messages"), '<fmt:message key="validation.required.procedimientoHomologo"/>');
			valido = false;
		}
		return valido;
		
	}
	function validateRequired(field, msg){
		if ($(field).val() == ''){
			appendEmbebbedErrorMessage(field, msg);
			return false;
		}
		return true;
	}
	
	function requiereCamposHomologo(){
		return !$("#sinHomologoProcedimientoCTC").is(":checked"); ;
	}
	
	$(document).ready(
			function() {
				
				$("#tiempoDeUso").keypress(allowOnlyDigits);
				$("#frecuenciaDeUso").keypress(allowOnlyDigits);
				$("#cantidadPeriodo").keypress(allowOnlyDigits);
				
				$("#formularioCTCProcedimientoForm").validate({
				    onfocusout: false,
				    focusInvalid: false,
				    focusCleanup: false,
				    onkeyup: false,
				    onclick: false,
				    rules: {
				    	resumenHistoriaClinica : {required:true, minlength: 30},
				    	justificacionRiesgoInminente: {required:function(){return  $("#formularioCTCProcedimientoForm #riesgoInminente").is(":checked");},
				    									minlength:function(){if($("#formularioCTCProcedimientoForm #riesgoInminente").is(":checked")){return 30}else{return 0}}},
				    	autorizadoINVIMA: {required:true},
				    	justificacionSinPosPrevio: {required:function(){return $("#sinAlternativaPosCTCProcedimiento").is(":checked");},
				    	minlength:function(){if($("#sinAlternativaPosCTCProcedimiento").is(":checked")){return 30}else{return 0}}},
				    	"procedimientoPosHomologo.frecuenciaDeUso" : {required:function(){return !$("#sinHomologoProcedimientoCTC").is(":checked");}, digits:true},
				    	"procedimientoPosHomologo.tiempoDeUso" :{required:function(){return !$("#sinHomologoProcedimientoCTC").is(":checked");}, digits:true},
				    	"procedimientoPosHomologo.objetivo" : {required:function(){return !$("#sinHomologoProcedimientoCTC").is(":checked");}},
				    	"procedimientoPosHomologo.cantidadPeriodo" : {required:function(){return !$("#sinHomologoProcedimientoCTC").is(":checked");}, digits:true},
				    	justificacionSinHomologo : {required:function(){return $("#sinHomologoProcedimientoCTC").is(":checked");},
				    		minlength:function(){if($("#sinHomologoProcedimientoCTC").is(":checked")){return 30}else{return 0}}},
				    	justificacionMedico: {required:true, minlength: 30},
				    },
				    messages: {
				    	resumenHistoriaClinica : {required:'<fmt:message key="validation.required"><fmt:param value="${RESUMEN_HISTORIA_CLINICA}"/></fmt:message>',
				    							 minlength:"${MINLENGTH_30}"},
				    	justificacionRiesgoInminente: 	{required:'<fmt:message key="validation.required"><fmt:param value="${JUSTIFICACION_RIESGO_INMINENTE}"/></fmt:message>', 
				    									minlength:"${MINLENGTH_30}"},
				    	autorizadoINVIMA:'<fmt:message key="validation.required"><fmt:param value="${AUTORIZADO_INVIMA}"/></fmt:message>',
				    	justificacionSinPosPrevio: {required:'<fmt:message key="validation.required.justificacionSinPosPrevio.medicamento"/>',
				    								minlength:"${MINLENGTH_30}"},
				    	
				    	"procedimientoPosHomologo.frecuenciaDeUso" : {required:'<fmt:message key="validation.required"><fmt:param value="${FRECUENCIA}"/></fmt:message>',
				    													digits:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${FRECUENCIA}"/></fmt:message>'},
				    	"procedimientoPosHomologo.tiempoDeUso" :{required:'<fmt:message key="validation.required"><fmt:param value="${TIEMPO_DE_USO}"/></fmt:message>',
				    											digits:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${TIEMPO_DE_USO}"/></fmt:message>'},
				    	"procedimientoPosHomologo.objetivo" :'<fmt:message key="validation.required"><fmt:param value="${OBJETIVO}"/></fmt:message>',
				    	"procedimientoPosHomologo.cantidadPeriodo" :{required:'<fmt:message key="validation.required"><fmt:param value="${CANTIDAD_PERIODO}"/></fmt:message>',
				    												digits:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${CANTIDAD_PERIODO}"/></fmt:message>'},
				    	justificacionSinHomologo : {required:'<fmt:message key="validation.required"><fmt:param value="${JUSTIFICACION_NO_HOMOLOGO}"/></fmt:message>',
				    							  minlength:"${MINLENGTH_30}"},
				    	
				    	justificacionMedico: {required: '<fmt:message key="validation.required"><fmt:param value="${JUSTIFICACION_MEDICO}"/></fmt:message>',
				    						 minlength:"${MINLENGTH_30}"}
				    },  
				    errorPlacement: function (error, element){
				    	appendErrorMessage($("#procedimientos-ctc-messages"), error.text());
				    }
				});

				$("#idAceptarCTCProcedimiento").click(function() {
					$("#procedimientos-ctc-messages").empty();
					
					var esValido =$("#formularioCTCProcedimientoForm").valid();
					var esValidoPosPrevios = tieneProcedimientosPosPrevios(); 
					var esValidoHomologo = tieneHomologo();
					
					if (esValido && esValidoPosPrevios && esValidoHomologo){
						applyCTCProcedimiento();
						resetFieldsCTCProcedimiento();
						$("#formularioCTCProcedimiento").modal('hide');	
						$container.removeClass("btn-warning");
						$container.addClass("btn-success");
					}else{
						scrollModalTop($("#formularioCTCProcedimiento"), 500);
					}
				});
				
				$("#formularioCTCProcedimientoForm #riesgoInminente").change(function(){
					$("#formularioCTCProcedimientoForm .conRiesgoInminente").toggle($(this).is(":checked"));
				});

				$("#idCancelarCTCProcedimiento").click(function() {
					resetFieldsCTCProcedimiento();
				});
				
				$("#formularioCTCProcedimiento button.close").click(function() {
					resetFieldsCTCProcedimiento();
				});
				
				$("#sinAlternativaPosCTCProcedimiento").change(
						function() {
							if ($(this).is(":checked")) {
								$(this).parents("fieldset")
										.find(".searchForm").find(
												"input, button").attr(
												"disabled", "disabled");
								$("#formularioCTCProcedimiento #sinPosPrevio").show();
								$("#formularioCTCProcedimiento .previos .searchForm").hide();
								$("#formularioCTCProcedimiento .conPosPrevio").hide();
								resetPosPrevios();
								
							} else {
								$(this).parents("fieldset")
										.find(".searchForm").find(
												"input, button").removeAttr(
												"disabled");
								$("#formularioCTCProcedimiento #sinPosPrevio").hide();
								$("#formularioCTCProcedimiento .previos .searchForm").show();
								$("#formularioCTCProcedimiento .conPosPrevio").show();
							}
						});
				
				$("#sinHomologoProcedimientoCTC").change(
						function() {
							

								$("#formularioCTCProcedimiento .sinHomologo").toggle($(this).is(":checked"));
								$("#formularioCTCProcedimiento .conHomologo").toggle(!$(this).is(":checked"));
								
								if ($(this).is(":checked")) {
									resetHomologos();									
								}

						});
			});
</script>

<div class="modal fade" id="formularioCTCProcedimiento" data-width="900" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Formulario CTC Procedimiento</h3>
	</div>
	<div class="modal-body">
		<div id="procedimientos-ctc-messages"></div>
		<form id="formularioCTCProcedimientoForm" class="form-horizontal">
			<fieldset>
				<div class="form-group form-group-sm">
					<label for="resumenHistoriaClinica" class="col-lg-3 control-label control-label-sm"><fmt:message key="label.infoSolicitud.resHistClin"/></label>
					<div class="col-lg-9">
						<textarea placeholder="<fmt:message key='label.infoSolicitud.resHistClin.detalle'/>" name="resumenHistoriaClinica" id="resumenHistoriaClinica" class="form-control input-sm"></textarea>
					</div>
				</div>
			</fieldset>
			<div class="row">
				<div class="col-lg-12 title">
					<div id="formProcedimientos" class="previos well  clearfix abmComponent withSearch" javaObject="procedimientosPosPrevios">
						<fieldset>
							<blockquote class="col-lg-12" style="padding: 2px; font-size: 20px;">
								<strong> <fmt:message key="label.procedimiento.nopos.posPrevios" />
								</strong> <small style="display: inline"> <label><input type="checkbox" name="sinAlternativaPos" id="sinAlternativaPosCTCProcedimiento" />
										<fmt:message key="label.procedimiento.nopos.sinAlternativaPos" /></label>
								</small>
							</blockquote>
							<div id="sinPosPrevio" class="col-lg-12 form-horizontal" style="display:none">
									<label class="control-label control-label-sm col-lg-2"><fmt:message key="label.nopos.justificacion" /></label>
									<div class="col-lg-10">
										<textarea class="form-control input-sm" name="justificacionSinPosPrevio"></textarea>
									</div>
								</div>
								<div class="form-group form-group-sm searchForm  collapsible-content">
								
								 <input	placeholder="${CODIGO_CUPS}" id="codigo" class="template-hidden col-lg-3">
								 <input placeholder="${NOMBRE}"  id="descripcion" class="template-hidden col-lg-3">
						        </div>
							<div class="row collapsible-content">
									<table id="procPreviosTable"  class="table  table-hover">
										<thead>
											<tr class="row">
											<th class="data" name="id" thclass="template-hidden" tdclass="template-hidden" role="hidden"></th>
												<th class="data col-lg-2" name="codigo" tdClass="col-lg-2">${CODIGO_CUPS}</th>
												<th class="data col-lg-4" name="descripcion" tdClass="col-lg-10">${NOMBRE}</th>
												<th class="editable col-lg-3" name="respuestaClinicaObservada"
												role="combo" valueFrom="respuestasClinicasObservadas"><fmt:message key="label.respuestaClinicaObservada" /></th>
												<th class="editable col-lg-1" role="buttonEliminar"></th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
							</div>
							
						</fieldset>
					</div>
					
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 title">
					<div id="formProcedimientoHomologo" class="well  clearfix abmComponent withSearch uniqueResult" javaObject="procedimientoPosHomologo">
						<fieldset>
							<blockquote class="col-lg-12" style="padding: 2px; font-size: 20px;">
								<strong><fmt:message key="label.procedimiento.nopos.posHomologo" /></strong>
								<small style="display: inline"> 
									<label>
										<input type="checkbox" name="sinHomologo" id="sinHomologoProcedimientoCTC" />
										<fmt:message key="label.procedimiento.nopos.sinHomologo" />
									</label>
								</small>
							</blockquote>
							<div class="conHomologo row form-group form-group-sm form-group-sm-sm searchForm  collapsible-content">
							 	<input	placeholder="id" id="id" role="hidden-search" class="template-hidden" avoidDescriptionLabel>
								<input placeholder="${CODIGO_CUPS}" id="codigo" class="col-lg-4">
								<input placeholder="${NOMBRE}" id="descripcion" class="col-lg-3" >
							</div>
							<div class="row collapsible-content">
								<div class="col-lg-12  ">
									<table class=" conHomologo table  table-hover">
										<thead>
											<tr class="row">
												<th class="data" name="id" thclass="template-hidden" tdclass="template-hidden" role="hidden"></th>
												<th class="data col-lg-2" name="codigo" tdClass="col-lg-2">${CODIGO_CUPS}</th>
												<th class="data col-lg-4" name="descripcion" tdClass="col-lg-10">${NOMBRE}</th>
												<th class="editable col-lg-1" role="buttonEliminar"></th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
									<div class="form-group form-group-sm conHomologo ">
										<label for="cantidadPeriodo" class="col-lg-3 control-label control-label-sm"><fmt:message key="label.cantidad.periodo"/></label>
										<div class="col-lg-3">
											<input type="text" class="form-control input-sm" name="procedimientoPosHomologo.cantidadPeriodo" id="cantidadPeriodo" >
										</div>
										<label for="objetivos" class="col-lg-3 control-label control-label-sm">${OBJETIVO}</label>
										<div class="col-lg-3">
											<form:select path="objetivos" name="procedimientoPosHomologo.objetivo" id="objetivos" class="form-control input-sm">
												<form:option value="" label="${SELECCIONE_LABEL}" />
												<form:options items="${objetivos}" itemLabel="descripcion" itemValue="id" />
											</form:select>
										</div>
									</div>
									<div class="form-group form-group-sm conHomologo ">
										<label for="tiempoDeUso" class="col-lg-3 control-label control-label-sm"><fmt:message key="label.tiempo.uso.dias"/></label>
										<div class="col-lg-3">
											<input type="text" class="form-control input-sm" name="procedimientoPosHomologo.tiempoDeUso" id="tiempoDeUso" >
										</div>
										<label for="frecuenciaDeUso" class="col-lg-3 control-label control-label-sm"><fmt:message key="label.frecuencia.uso.dias"/></label>
										<div class="col-lg-3">
											<input type="text" class="form-control input-sm" name="procedimientoPosHomologo.frecuenciaDeUso" id="frecuenciaDeUso" >
										</div>
									</div>
									<div class="sinHomologo form-group form-group-sm" style="display:none">
										<label class="control-label control-label-sm col-lg-3" for="justificacionSinHomologo"><fmt:message key="label.justificacion.noHomologo"/></label>
										<div class="col-lg-9">
											<textarea name="justificacionSinHomologo" id="justificacionSinHomologo" class="form-control input-sm"></textarea>
										</div>
									</div>
								</div>
							</div>
						</fieldset>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 title">
                    <div class="well">
            			<fieldset>
            				<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
            					<strong><fmt:message key="label.nopos.justificacion.solicitud" /></strong>
            				</blockquote>
            				<div class="collapsible-content">
            					<div class="checkbox">
            						 <label class="col-lg-12 col-md-12 col-sm-12 col-xs-12 control-label control-label-sm">
            							 <input type="checkbox" name="riesgoInminente" id="riesgoInminente" />
            							 <fmt:message key="label.nopos.justificacion.riesgoInminente" />
            						</label>
            					</div>
            					<div class="form-group form-group-sm conRiesgoInminente template-hidden">
            						<label for="justificacionRiesgoInminente" class="col-lg-2 col-md-2 col-sm-2 col-xs-12 control-label control-label-sm"> <fmt:message key="label.infoSolicitud.justificacionRiesgoInminente" /></label>
            						<div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 ">
            							<textarea name="justificacionRiesgoInminente" id="justificacionRiesgoInminente" class="form-control input-sm"></textarea>
            						</div>
            					</div>
            					<div class="checkbox">
									<label class="col-lg-12 col-md-12 col-sm-12 col-xs-12 control-label control-label-sm">
										<input type="checkbox" name="posibilidadesPOSAgotadas" id="posibilidadesPOSAgotadas" />
										<fmt:message key="label.nopos.justificacion.posibilidadesPOSAgotadas" />
           							</label>
            					</div>
            					<br /> 
            					<div class="form-group form-group-sm ">
            						<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 text-left">
	            						<strong>
	            							<fmt:message key="label.nopos.justificacion.autorizadoINVIMA" />
	            						</strong>
            						</div>
            						<div class="radio col-lg-2 col-md-2 col-sm-2 col-xs-2">
	            						<label> 
	            							<input type="radio" name="autorizadoINVIMA" id="autorizadoINVIMASi" value="1" />
											<fmt:message key="label.si" />
	            						</label>
	            					</div>
	            					<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
	            					</div>
	            					<div class="radio col-lg-2 col-md-2 col-sm-2 col-xs-2">
	            						<label> 
	            							<input type="radio" name="autorizadoINVIMA" id="autorizadoINVIMANo" value="2" /> 
	            							<fmt:message key="label.no" />
	            						</label>
	            					</div>
	            					<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
	            					</div>
	            					<div class="radio col-lg-2 col-md-2 col-sm-2 col-xs-2">
	            						<label> 
	            							<input type="radio" name="autorizadoINVIMA" id="autorizadoINVIMANoSabe" value="3" /> 
	            							<fmt:message key="label.noSabe" />
	            						</label>
	            					</div>
            					</div>
            					<div class="form-group form-group-sm ">
            						<label for="justificacionMedico" class="col-lg-2 col-md-2 col-sm-2 col-xs-12 control-label control-label-sm">
            							<fmt:message key="label.nopos.justificacion.medico" />
            						</label>
            						<div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 ">
            							<textarea name="justificacionMedico" id="justificacionMedico" class="form-control input-sm"></textarea>
            						</div>
            					</div>
            				</div>
            			</fieldset>
                    </div>
                </div>
            </div>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" id="idAceptarCTCProcedimiento" class="btn btn-primary btn-success">Aceptar</button>
		<button type="button" id="idCancelarCTCProcedimiento" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
	</div>
</div>