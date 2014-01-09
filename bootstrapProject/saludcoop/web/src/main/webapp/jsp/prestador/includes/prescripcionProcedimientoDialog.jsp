<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="webContext" value="${pageContext.request.contextPath}" />

<fmt:message key='label.finalidad' var="FINALIDAD" />
<fmt:message key='label.causaExterna' var="CAUSA_EXTERNA" />
<fmt:message key='label.tipoCatastrofico' var="TIPO_CATASTROFICO" />
<fmt:message key='label.lateralidad' var="LATERALIDAD" />
<fmt:message key='label.tipoPrestacion' var="TIPO_PRESTACION" />
<fmt:message key='label.origenRepeticion' var="ORIGEN_REPETICION" />
<fmt:message key='label.objetivo' var="OBJETIVO" />
<fmt:message key="label.indicaciones" var="INDICACIONES"/>
<fmt:message key='label.select' var="SELECCIONE_LABEL" />

<div class="modal fade" id="prescripcionProcedimiento" data-width="900" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Prescripción del Procedimiento</h3>
	</div>
	<div class="modal-body">
		<div id="prescripcion-procedimiento-messages"></div>
		<form id="prescripcionProcedimientoForm" class="form-horizontal">
			<fieldset>
				<div class="form-group form-group-sm">
					<h4 class="col-lg-10 col-lg-offset-2" id="item-description"></h4>
				</div>
				<div class="form-group form-group-sm">
					<label for="causaExterna" class="col-lg-3 control-label control-label-sm">Causa externa</label>
					<div class="col-lg-3">
						<form:select path="causasExternas" name="causaExterna" id="causasExternas" class="form-control input-sm" default-value="13">
							<form:option value="" label="${SELECCIONE_LABEL}" />
							<form:options items="${causasExternas}" itemLabel="descripcion" itemValue="id" />
						</form:select>
					</div>
					<label for="finalidad" class="col-lg-3 control-label control-label-sm">Finalidad</label>
					<div class="col-lg-3">
						<form:select path="finalidades" name="finalidad" id="finalidades" class="form-control input-sm" default-value="2">
							<form:option value="" label="${SELECCIONE_LABEL}" />
							<form:options items="${finalidades}" itemLabel="descripcion" itemValue="id" />
						</form:select>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="tiposCatastroficos" class="col-lg-3 control-label control-label-sm">Tipo Catastrofico</label>
					<div class="col-lg-3">
						<form:select path="tiposCatastroficos" name="tipoCatastrofico" id="tiposCatastroficos" class="form-control input-sm" default-value="1">
							<form:option value="" label="${SELECCIONE_LABEL}" />
							<form:options items="${tiposCatastroficos}" itemLabel="descripcion" itemValue="id" />
						</form:select>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="dosis" class="col-lg-3 control-label control-label-sm">${LATERALIDAD}</label>
					<div class="col-lg-9">
						<form:select path="lateralidades" name="lateralidad" id="lateralidades" class="form-control input-sm" default-value="1">
							<form:option value="" label="${SELECCIONE_LABEL}" />
							<form:options items="${lateralidades}" itemLabel="descripcion" itemValue="id" />
						</form:select>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="posologia" class="col-lg-3 control-label control-label-sm">${INDICACIONES }</label>
					<div class="col-lg-9">
						<textarea class="form-control input-sm" name="posologia" id="posologia"></textarea>
					</div>
				</div>
				<div class="form-group form-group-sm template-hidden nopos-field">
					<label for="dosis" class="col-lg-3 control-label control-label-sm">${OBJETIVO}</label>
					<div class="col-lg-9">
						<form:select path="objetivos" name="objetivo" id="objetivos" class="form-control input-sm">
							<form:option value="" label="${SELECCIONE_LABEL}" />
							<form:options items="${objetivos}" itemLabel="descripcion" itemValue="id" />
						</form:select>
					</div>
				</div>
				<div class="form-group form-group-sm template-hidden nopos-field">
					<label for="dosis" class="col-lg-3 control-label control-label-sm">${TIPO_PRESTACION}</label>
					<div class="col-lg-9">
						<form:select path="tiposPrestacion" name="tipoPrestacion" id="tiposPrestacion" class="form-control input-sm">
							<form:option value="" label="${SELECCIONE_LABEL}" />
							<form:options items="${tiposPrestacion}" itemLabel="descripcion" itemValue="id" />
						</form:select>
					</div>
				</div>
				<div class="form-group form-group-sm template-hidden tipo-prestacion-unico-repetido-field">
					<label for="dosis" class="col-lg-3 control-label control-label-sm">${ORIGEN_REPETICION}</label>
					<div class="col-lg-9">
						<form:select path="origenesRepeticion" name="origen" id="origenes" class="form-control input-sm">
							<form:option value="" label="${SELECCIONE_LABEL}" />
							<form:options items="${origenesRepeticion}" itemLabel="descripcion" itemValue="id" />
						</form:select>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" id="idAceptarPrescripcionProcedimiento" class="btn btn-primary btn-success">Aceptar</button>
		<button type="button" id="idCancelarPrescripcionProcedimiento" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
	</div>
</div>
<script>

function fillprescripcionProcedimiento($container) {
	
	$("#prescripcionProcedimientoForm .nopos-field").toggle($container.parents("tr").data("data").nivel == 5);
	$("#prescripcionProcedimientoForm #item-description").text($container.parents("tr").find("[id='descripcion']").val());
	
	if ($container.data()) {
		$.each($container.data(), function(i, e) {
		    $("#prescripcionProcedimientoForm [name='" + e.name + "']").val(e.value);
		    if (e.name == 'tipoPrestacion' && e.value==2){
		    	$(".tipo-prestacion-unico-repetido-field").show();
		    }
		});
	}else{
		$("#prescripcionProcedimientoForm .form-control").val('');
	}

}
	
function applyPrescripcionProcedimiento() {
	$container.data($("#prescripcionProcedimientoForm").serializeArray());
	$container.attr("data-form-prescr", $("#prescripcionProcedimientoForm").serialize());
}

function resetFieldsPrescripcionProcedimiento() {
	genericResetFields("prescripcionProcedimientoForm");
	setDefaults("prescripcionProcedimientoForm");
	resetValidateForm("prescripcionProcedimientoForm");
	$(".tipo-prestacion-unico-repetido-field").hide();
}

$(document).ready(function() {
	
	$("#prescripcionProcedimientoForm").validate({
	    onfocusout: false,
	    focusInvalid: false,
	    focusCleanup: false,
	    onkeyup: false,
	    onclick: false,
	    rules: {
	    	lateralidad:"required",
	    	causaExterna: {required:true},
	    	finalidad: {required:true},
	    	tipoCatastrofico: {required:true},
	    	posologia:{required:true, minlength:30,  maxlength:500},
	    	objetivo:{required:function(){return $("#prescripcionProcedimientoForm .nopos-field").is(":visible");}},
	    	origen:{required:function(){return $("#prescripcionProcedimientoForm .nopos-field").is(":visible") &&  $("#prescripcionProcedimientoForm #tiposPrestacion").val() == 2;}},
	    	tipoPrestacion:{required:function(){return $("#prescripcionProcedimientoForm .nopos-field").is(":visible");}}
	    },
	    messages: {
	    	lateralidad:'<fmt:message key="validation.required"><fmt:param value="${LATERALIDAD}"/></fmt:message>',
	    	causaExterna: 	 '<fmt:message key="validation.required"><fmt:param value="${CAUSA_EXTERNA}"/></fmt:message>',
	    	finalidad: 		 '<fmt:message key="validation.required"><fmt:param value="${FINALIDAD}"/></fmt:message>',
	    	tipoCatastrofico:'<fmt:message key="validation.required"><fmt:param value="${TIPO_CATASTROFICO}"/></fmt:message>',
	    	posologia:{required:'<fmt:message key="validation.required"><fmt:param value="${INDICACIONES}"/></fmt:message>',
	    	    minlength:'<fmt:message key="message.error.minLength.30"><fmt:param value="${INDICACIONES}"/></fmt:message>',
	    	    maxlength:'<fmt:message key="validation.restriction.betweenNandMChars"><fmt:param value="${INDICACIONES}"/></fmt:message>.'.replace("{1}",30).replace("{2}",500)},
	    	objetivo:'<fmt:message key="validation.required"><fmt:param value="${OBJETIVO}"/></fmt:message>',
	    	origen:'<fmt:message key="validation.required"><fmt:param value="${ORIGEN_REPETICION}"/></fmt:message>',
	    	tipoPrestacion:'<fmt:message key="validation.required"><fmt:param value="${TIPO_PRESTACION}"/></fmt:message>',
	    },  
	    errorPlacement: function (error, element){
	    	appendErrorMessage($("#prescripcion-procedimiento-messages"), error.text());
	    }
	});
	
	
	$("#prescripcionProcedimientoForm").validate();
	
	$("#idAceptarPrescripcionProcedimiento").click(function() {
		$("#prescripcion-procedimiento-messages").empty();
		if ($("#prescripcionProcedimientoForm").valid()){
			applyPrescripcionProcedimiento();
			resetFieldsPrescripcionProcedimiento();
			$("#prescripcionProcedimiento").modal('hide');
			$container.removeClass("btn-warning");
			$container.addClass("btn-success");	
		}
	});

	$("#idCancelarPrescripcionProcedimiento").click(function() {
		resetFieldsPrescripcionProcedimiento();
	});
	
	$("#prescripcionProcedimiento button.close").click(function() {
		resetFieldsPrescripcionProcedimiento();
	});
	
	$("#prescripcionProcedimientoForm #tiposPrestacion").bind("change", 
			function(){
				$(".tipo-prestacion-unico-repetido-field").toggle($(this).val()==2);
				if ($(this).val()!=2){
					$(".tipo-prestacion-unico-repetido-field select").val('');
				}
			});

});
</script>