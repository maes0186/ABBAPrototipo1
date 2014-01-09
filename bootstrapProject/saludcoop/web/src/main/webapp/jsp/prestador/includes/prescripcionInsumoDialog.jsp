<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="webContext" value="${pageContext.request.contextPath}" />

<fmt:message key='label.finalidad' var="FINALIDAD" />
<fmt:message key='label.causaExterna' var="CAUSA_EXTERNA" />
<fmt:message key='label.tipoCatastrofico' var="TIPO_CATASTROFICO" />
<fmt:message key='label.cantidad' var="CANTIDAD" />
<fmt:message key='label.frecuencia' var="FRECUENCIA" />
<fmt:message key='label.duracionTratamiento' var="DURACION" />
<fmt:message key='label.viaAdministracion' var="VIA_ADMINISTRACION" />
<fmt:message key='label.posologia' var="POSOLOGIA" />
<fmt:message key='label.insumo.nopos.efectosAdversos.mini' var="EFECTOS_ADVERSOS" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />
<fmt:message key='label.dias' var="DIAS" />
<fmt:message key='label.horas' var="HORAS" />

<div class="modal fade" id="prescripcionInsumo" data-width="1000" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Prescripción del Insumo</h3>
	</div>
	<div class="modal-body">
		<div id="prescripcion-insumo-messages"></div>
		<form id="prescripcionInsumoForm" class="form-horizontal">
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
					<label for="cantidad" class="col-lg-3 control-label control-label-sm">${CANTIDAD}</label>
					<div class="col-lg-2">
						<input type="text" class="form-control input-sm digits-only" name="cantidad" id="cantidad" />
					</div>
					<label for="duracion" class="col-lg-3 control-label control-label-sm">${DURACION }</label>
					<div class="col-lg-2">
						<input type="text" class="form-control input-sm digits-only" name="duracion" id="duracion" />
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" id="idAceptarPrescripcionInsumo" class="btn btn-primary btn-success">Aceptar</button>
		<button type="button" id="idCancelarPrescripcionInsumo" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
	</div>
</div>

<script>

function fillprescripcionInsumo($container) {
	
	$("#prescripcionInsumoForm .nopos-field").toggle($container.parents("tr").data("data").visibleCtc);	
	$("#prescripcionInsumoForm #item-description").text($container.parents("tr").find("[id='descripcion']").val());
	
	if ($container.data()) {
		$.each($container.data(), function(i, e) {
		    $("#prescripcionInsumoForm [name='" + e.name + "']").val(e.value);
		});
	}else{
		$("#prescripcionInsumoForm .form-control").val('');
	}
}

	function applyPrescripcionInsumo() {
	    var cant = $("#prescripcionInsumoForm #cantidad").val();
	    var td = $container.closest("td");
	    var form = $container.closest("form");
	    var row = td.parent();
	    var data = row.data("data");
	    
	    var esInsumoKit = false;
		url = "${webContext}/prestador/esInsumoKit/"+data.id;
		$.get(url, function(dataEsInsumo) {
		    esInsumoKit = $.parseJSON(dataEsInsumo);
			if (esInsumoKit){
			     var agregarFormularioCTC = false;
			     url = "${webContext}/prestador/formularioCTCInsumoKit/"+data.id+"/"+cant;
			     
			     $.get(url, function(dataSuperoTope) {
                     agregarFormularioCTC = $.parseJSON(dataSuperoTope);
                     if(agregarFormularioCTC){
                         getInsumoHomologo(row, data.id);
        				 addFormularioCTC(td, form, data, "formularioCTCInsumo");
        				 
        				 $container.closest("tr").find("#superaTopes").val("true");
                     } else {
                         
                         $(td).find("button[id*='CTC']").remove();
         				 $(td).find("div[id*='formularioCTC_']").remove();
         				 $(td).find('a[title*="CTC"]').parent().remove();
                         $container.closest("tr").find("#superaTopes").val("false");
                     }
			     }, "json");			     
			 }
        }, "json");		    
	    
		$container.parents("tr").find("input[id=cant]").val(cant);
		$container.data($("#prescripcionInsumoForm").serializeArray());
		$container.attr("data-form-prescr", $("#prescripcionInsumoForm").serialize());
	}
	
	function resetFieldsPrescripcionInsumo() {
		genericResetFields("prescripcionInsumoForm");
		setDefaults("prescripcionInsumoForm");
		resetValidateForm("prescripcionInsumoForm");
	}
	
	$(document).ready(function() {
		
		$("#prescripcionInsumoForm").validate({
		    onfocusout: false,
		    focusInvalid: false,
		    focusCleanup: false,
		    onkeyup: false,
		    onclick: false,
		    rules: {
		    	cantidad:{required:true, digits:true},
		    	duracion:{required:true, digits:true},
		    	causaExterna: {required:true},
		    	finalidad: {required:true},
		    	tipoCatastrofico: {required:true},

		    },
		    messages: {
		    	cantidad:{required:'<fmt:message key="validation.required"><fmt:param value="${DOSIS}"/></fmt:message>',digits:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${CANTIDAD}"/></fmt:message>'},
		    	duracion:{required:'<fmt:message key="validation.required"><fmt:param value="${DURACION}"/></fmt:message>',digits:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${DURACION}"/></fmt:message>'},
			    causaExterna: 	 '<fmt:message key="validation.required"><fmt:param value="${CAUSA_EXTERNA}"/></fmt:message>',
			    finalidad: 		 '<fmt:message key="validation.required"><fmt:param value="${FINALIDAD}"/></fmt:message>',
			    tipoCatastrofico:'<fmt:message key="validation.required"><fmt:param value="${TIPO_CATASTROFICO}"/></fmt:message>'
		    },  
		    errorPlacement: function (error, element){
		    	appendErrorMessage($("#prescripcion-insumo-messages"), error.text());
		    }
		});
		
		$("#idAceptarPrescripcionInsumo").click(function() {
			$("#prescripcion-insumo-messages").empty();
			if ($("#prescripcionInsumoForm").valid()){
				applyPrescripcionInsumo();
				resetFieldsPrescripcionInsumo();
				$container.removeClass("btn-warning");
				$container.addClass("btn-success");
				$("#prescripcionInsumo").modal('hide');	
			}
		});

		$("#idCancelarPrescripcionInsumo").click(function() {
			resetFieldsPrescripcionInsumo();
		});

	});		
		$("#prescripcionInsumo button.close").click(function() {
			resetFieldsPrescripcionInsumo();
		});
;
</script>