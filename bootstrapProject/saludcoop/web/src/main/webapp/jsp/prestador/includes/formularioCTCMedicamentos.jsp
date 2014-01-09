<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="webContext" value="${pageContext.request.contextPath}" />


<fmt:message key="label.medicamento.nopos.efectosAdversos.mini"  var="EFECTOS_ADVERSOS"/>
<fmt:message key="label.nopos.justificacion.riesgoInminente" var="RIESGO_INMINENTE"/>
<fmt:message key="label.infoSolicitud.justificacionRiesgoInminente" var="JUSTIFICACION_RIESGO_INMINENTE"/>
<fmt:message key="label.nopos.justificacion.autorizadoINVIMA.mini" var="AUTORIZADO_INVIMA"/>
<fmt:message key='label.infoSolicitud.resHistClin' var="RESUMEN_HISTORIA_CLINICA"/>
<fmt:message key="label.nopos.justificacion.medico" var="JUSTIFICACION_MEDICO" />
<fmt:message key="label.dosis" var="DOSIS"/>
<fmt:message key="label.infoSolicitud.diasTratamiento" var="DIAS_TRATAMIENTO"/>
<fmt:message key='label.code' var="CODIGO_CUM" />
<fmt:message key='label.name' var="NOMBRE" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />
<fmt:message key='message.error.minLength.30' var="MINLENGTH_30"/>

<div class="modal fade" id="formularioCTCMedicamento" data-width="900" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Formulario CTC Medicamento</h3>
	</div>
	<div class="modal-body">
		<div id="medicamentos-ctc-messages"></div>
		<form id="formularioCTCMedicamentoForm" class="form-horizontal">
			<fieldset>
				
				<div class="form-group form-group-sm">
					<label for="resumenHistoriaClinica" class="col-lg-3 control-label control-label-sm"><fmt:message key='label.infoSolicitud.resHistClin'/></label>
					<div class="col-lg-9">
						<textarea placeholder="<fmt:message key='label.infoSolicitud.resHistClin.detalle'/>" name="resumenHistoriaClinica" id="resumenHistoriaClinica" class="form-control input-sm"></textarea>
					</div>
				</div>
				
			</fieldset>

			<div class="row">
				<div class="col-lg-12 title">
					<div id="formMedicamentos" class="previos well  clearfix abmComponent withSearch" javaObject="medicamentosPosPrevios">
						<fieldset>
							<blockquote class="col-lg-12 " style="padding: 2px; font-size: 20px;">
								<strong> <fmt:message key="label.medicamento.nopos.posPrevios" />
								</strong> <small style="display: inline"> <label><input type="checkbox" name="sinAlternativaPos" id="sinAlternativaPosCTCMedicamento" />
										<fmt:message key="label.medicamento.nopos.sinAlternativaPos" /></label>
								</small>
							</blockquote>
							<div id="sinPosPrevio" class="col-lg-12 form-horizontal" style="display:none">
									<label class="control-label control-label-sm col-lg-2"><fmt:message key="label.nopos.justificacion" /></label>
									<div class="col-lg-10">
										<textarea class="form-control input-sm" name="justificacionSinPosPrevio"></textarea>
									</div>
								</div>
								<div class="form-group form-group-sm searchForm  collapsible-content">
								 <input	placeholder="${CODIGO_CUM}" id="codigo" class="template-hidden col-lg-3">
								 <input placeholder="${NOMBRE}"  id="descripcion" class="template-hidden col-lg-3">
						        </div>
							<div class="row collapsible-content">
								<table id="medPreviosTable" class="table  table-hover">
									<thead>
										<tr class="row">
											<th class="data" name="id" thclass="template-hidden" tdclass="template-hidden" role="hidden"></th>
											<th class="data col-lg-1" name="codigo" tdClass="col-lg-2">${CODIGO_CUM}</th>
											<th class="data col-lg-4" name="descripcion"  tdClass="col-lg-10">${NOMBRE}</th>
											<th class="editable col-lg-1" name="dosis" role="input"><fmt:message key="label.dosis" /></th>
											<th class="editable col-lg-1" name="diasTratamiento" role="input"><fmt:message key="label.infoSolicitud.diasTratamiento"/></th>
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
					<div id="formMedicamentoHomologo" class="well  clearfix" >
						<fieldset>
							<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
								<strong><fmt:message key="label.medicamento.nopos.posHomologo" /></strong>
							</blockquote>
							<div class="form-group form-group-sm collapsible-content">
								<input type="hidden" id="id" name="medicamentoPosHomologo.id" />
	                            <label class="control-label control-label-sm col-lg-2" for="codigo">${CODIGO_CUM}</label>
	                            <div class="col-lg-2">
	                                <input type="text" id="codigo" name="medicamentoPosHomologo.codigo" class="form-control form-control-sm input-flat input-sm" readonly />
	                            </div>
	                            <label class="control-label control-label-sm text-right col-lg-2" for="descripcion">${NOMBRE}</label>
	                            <div class="col-lg-6">
	                                <input type="text" id="descripcion" name="medicamentoPosHomologo.descripcion" class="form-control form-control-sm input-flat input-sm" readonly />
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
            						<label for="justificacionMedico" class="col-lg-2 col-md-2 col-sm-2 col-xs-12 control-label control-label-sm"> <fmt:message key="label.nopos.justificacion.medico" /></label>
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
		<button type="button" id="idAceptarCTCMedicamento" class="btn btn-success" >Aceptar</button>
		<button type="button" id="idCancelarCTCMedicamento" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
	</div>
</div>

<script>
	function fillformularioCTCMedicamento($container) {
		
		$("#medicamentos-ctc-messages").empty();
		
		if ($container.data().data) {
			var mapaMedicamentos = new Array();
			$.each($container.data().data, function(i, e) {
				if (e.name.indexOf("[") != -1)  {
					agregaAMapa(e, mapaMedicamentos);
				} else {
					switch($("#formularioCTCMedicamentoForm [name='" + e.name + "']").prop("type")) {
						case "radio":
							$("#formularioCTCMedicamentoForm [name='" + e.name + "'][value=" + e.value + "]").prop("checked", true);
							break;
						case "checkbox":
							if (e.value) {
								$("#formularioCTCMedicamentoForm [name='" + e.name + "']").click();	
							}
							break;
						default:
							$("#formularioCTCMedicamentoForm [name='" + e.name + "']").val(e.value);
					}
				}
			});
			for (var tipoMedicamento in mapaMedicamentos) {
				for (var medicamento in mapaMedicamentos[tipoMedicamento]) {
					var copiaCamposM = $.extend(true, [], $("[javaobject=" + tipoMedicamento + "]")[0].techFields);
					var med = mapaMedicamentos[tipoMedicamento][medicamento];
					copiaCamposM.filter(function(e){return e.name == "dosis";})[0].value = med.dosis;
					copiaCamposM.filter(function(e){return e.name == "diasTratamiento";})[0].value = med.diasTratamiento;
					copiaCamposM.filter(function(e){return e.name == "respuestaClinicaObservada";})[0].value = med.respuestaClinicaObservada;
					$("[javaobject=" + tipoMedicamento + "]")[0].addRow(med, copiaCamposM);	
				}
			}
		} else {
		    resetFieldsCTCMedicamento();
		    $("#formMedicamentoHomologo #id").val($container.parents("tr").data("medicamentoHomologo").id);
			$("#formMedicamentoHomologo #codigo").val($container.parents("tr").data("medicamentoHomologo").codigo).attr("readonly", "readonly");
			$("#formMedicamentoHomologo #descripcion").val($container.parents("tr").data("medicamentoHomologo").descripcion).attr("readonly", "readonly");
			
		}
	}
	
	function applyCTCMedicamento() {
		// Para el manejo dentro del contexto de javascript
		$container.data({data:$("#formularioCTCMedicamentoForm").serializeArray()});
		// Para el envío al servidor.
		$container.attr("data-form-ctc", $("#formularioCTCMedicamentoForm").serialize());
	}
	
	function resetFieldsCTCMedicamento() {
		document.getElementById("formularioCTCMedicamentoForm").reset();
		$("#medHomologoTable tbody").empty();
		$("#medHomologoTable thead").hide();
		$("#sinAlternativaPosCTCMedicamento").change();
		$("#formularioCTCMedicamentoForm .conRiesgoInminente").toggle(false);
		$("#formMedicamentoHomologo input").removeAttr("readonly");
		$("#formMedicamentoHomologo button").removeAttr("disabled");
		$("#formularioCTCMedicamento #formMedicamentos")[0].currentIndex=0;
		resetMedicamentosPosPrevios();
	}
	
	function resetMedicamentosPosPrevios(){
		$("#medPreviosTable tbody").empty();
		$("#medPreviosTable thead").hide();
	}

	function tieneMedicamentosPosPrevios(){
		var valido=true;
		if($("#sinAlternativaPosCTCMedicamento").is(":checked")){
			valido=true;
		}
		else if ($("#formularioCTCMedicamentoForm #formMedicamentos").find("tr #descripcion").length == 0 ){
			appendErrorMessage($("#medicamentos-ctc-messages"), '<fmt:message key="validation.required.medicamentoPosPrevio"/>');
			valido = false;
		}else{
			$.each($("#formularioCTCMedicamentoForm #formMedicamentos").find("tr #descripcion"), function(i,e){
				var tr = $(e).parents("tr");
				var dosis = validateRequired(tr.find("#dosis"),  '<fmt:message key="validation.required"><fmt:param value="${DOSIS}"/></fmt:message>');
				var dosisNumerico = validateDigitsOnly(tr.find("#dosis"),  '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${DOSIS}"/></fmt:message>');
				var diasTratamiento = validateRequired(tr.find("#diasTratamiento"), '<fmt:message key="validation.required"><fmt:param value="${DIAS_TRATAMIENTO}"/></fmt:message>');
				var diasTratamientoNumerico = validateDigitsOnly(tr.find("#diasTratamiento"), '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${DIAS_TRATAMIENTO}"/></fmt:message>');
				var respClinica = validateRequired(tr.find("[name=respuestaClinicaObservada]"), '<fmt:message key="validation.required"><fmt:param value="${RESPUESTAS_CLINICAS_OBSERVADAS}"/></fmt:message>');
				valido = valido && dosis && dosisNumerico && diasTratamiento && diasTratamientoNumerico && respClinica;
			});
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
	
	$(document).ready(
			function() {
				
			
				$("#formularioCTCMedicamentoForm").validate({
				    onfocusout: false,
				    focusInvalid: false,
				    focusCleanup: false,
				    onkeyup: false,
				    onclick: false,
				    rules: {
				    	resumenHistoriaClinica : {required:true, minlength: 30},
				    	justificacionRiesgoInminente: {required:function(){return  $("#formularioCTCMedicamentoForm #riesgoInminente").is(":checked");},
							minlength:function(){if($("#formularioCTCMedicamentoForm #riesgoInminente").is(":checked")){return 30}else{return 0}}},
				    	autorizadoINVIMA: {required:true},
				    	justificacionSinPosPrevio: {required:function(){return $("#sinAlternativaPosCTCMedicamento").is(":checked");},
				    								minlength:function(){if($("#sinAlternativaPosCTCMedicamento").is(":checked")){return 30}else{return 0}}},
				    	"medicamentoPosHomologo.codigo" : "required",
				    	justificacionMedico:{required:true, minlength: 30},
				    },
				    messages: {
				    	resumenHistoriaClinica : {required:'<fmt:message key="validation.required"><fmt:param value="${RESUMEN_HISTORIA_CLINICA}"/></fmt:message>',
												 minlength:"${MINLENGTH_30}"},
						justificacionRiesgoInminente: 	{required:'<fmt:message key="validation.required"><fmt:param value="${JUSTIFICACION_RIESGO_INMINENTE}"/></fmt:message>', 
				    									minlength:"${MINLENGTH_30}"},
				    	autorizadoINVIMA:'<fmt:message key="validation.required"><fmt:param value="${AUTORIZADO_INVIMA}"/></fmt:message>',
				    	justificacionSinPosPrevio: {required:'<fmt:message key="validation.required.justificacionSinPosPrevio.medicamento"/>',
							minlength:"${MINLENGTH_30}"},

				    	"medicamentoPosHomologo.codigo": '<fmt:message key="validation.required.medicamentoHomologo"/>',
				    	justificacionMedico: {required: '<fmt:message key="validation.required"><fmt:param value="${JUSTIFICACION_MEDICO}"/></fmt:message>',
   											 minlength:"${MINLENGTH_30}"}
				    },  
				    errorPlacement: function (error, element){
				    	appendErrorMessage($("#medicamentos-ctc-messages"), error.text());
				    }
				});
					
				$("#idAceptarCTCMedicamento").click(function() {
					$("#medicamentos-ctc-messages").empty();
					
					var esValido =$("#formularioCTCMedicamentoForm").valid();
					var esValidoPosPrevios = tieneMedicamentosPosPrevios(); 
					
					if (esValido && esValidoPosPrevios){
						applyCTCMedicamento();
						resetFieldsCTCMedicamento();
						$("#formularioCTCMedicamento").modal('hide');	
						$container.removeClass("btn-warning");
						$container.addClass("btn-success");
					}
				});

				$("#idCancelarCTCMedicamento").click(function() {
					resetFieldsCTCMedicamento();
				});
				
				$("#formularioCTCMedicamento button.close").click(function() {
					resetFieldsCTCMedicamento();
				});
				
				$("#formularioCTCMedicamentoForm #riesgoInminente").change(function(){
					$("#formularioCTCMedicamentoForm .conRiesgoInminente").toggle($(this).is(":checked"));
				});

				$("#sinAlternativaPosCTCMedicamento").change(
						function() {
							
							if ($(this).is(":checked")) {
								$(this).parent().parent().parent().parent()
										.find(".searchForm").find(
												"input, button").attr(
												"disabled", "disabled");
								$("#formularioCTCMedicamento #sinPosPrevio").show();
								$("#formularioCTCMedicamento .previos .searchForm").hide();
								$("#formularioCTCMedicamento .conPosPrevio").hide();
								resetMedicamentosPosPrevios();
							} else {
								$(this).parent().parent().parent().parent()
										.find(".searchForm").find(
												"input, button").removeAttr(
												"disabled");
								$("#formularioCTCMedicamento #sinPosPrevio").hide();
								$("#formularioCTCMedicamento .previos .searchForm").show();
								$("#formularioCTCMedicamento .conPosPrevio").show();
							}
							//Por si es collapsible-header y el contenido del div quedo oculto 
							if ($(this).parent().is(".collapsible-header") && !$(this).parent().parent().find(".collapsible-content").is(":visible")){
								$(this).parent().click();
							}
						});
			});
</script>
