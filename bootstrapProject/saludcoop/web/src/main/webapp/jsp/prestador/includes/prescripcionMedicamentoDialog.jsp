<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="webContext" value="${pageContext.request.contextPath}" />

<fmt:message key='label.finalidad' var="FINALIDAD" />
<fmt:message key='label.causaExterna' var="CAUSA_EXTERNA" />
<fmt:message key='label.tipoCatastrofico' var="TIPO_CATASTROFICO" />
<fmt:message key='label.dosis' var="DOSIS" />
<fmt:message key='label.frecuencia' var="FRECUENCIA" />
<fmt:message key='label.duracionTratamiento' var="DURACION" />
<fmt:message key='label.viaAdministracion' var="VIA_ADMINISTRACION" />
<fmt:message key='label.posologia' var="POSOLOGIA" />
<fmt:message key='label.medicamento.nopos.efectosAdversos.mini' var="EFECTOS_ADVERSOS" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />
<fmt:message key='label.dias' var="DIAS" />
<fmt:message key='label.horas' var="HORAS" />

<div class="modal fade" id="prescripcionMedicamento" data-width="1000" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Prescripción del Medicamento</h3>
	</div>
	<div class="modal-body">
		<div id="prescripcion-medicamento-messages"></div>
		<form id="prescripcionMedicamentoForm" class="form-horizontal">
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
					<label for="dosis" class="col-lg-3 control-label control-label-sm">${DOSIS }</label>
					<div class="col-lg-2">
						<input type="text" class="form-control input-sm digits-only" name="dosis" id="dosis" />
					</div>
					<label for="frecuencia" class="col-lg-3 control-label control-label-sm">${FRECUENCIA}</label>
					<div class="col-lg-2">
						<input type="text" class="form-control input-sm digits-only" name="frecuencia" id="frecuencia" />
					</div>
                    <div class="col-lg-2">
                        <select name="cada" id="cadaSelect" class="form-control input-sm">
                        	<option value="">${SELECCIONE_LABEL}</option>
                            <option value="1">${HORAS}</option>
                            <option value="2">${DIAS}</option>
                        </select>
                    </div>
				</div>
				<div class="form-group form-group-sm">
					<label for="duracionTratamiento" class="col-lg-3 control-label control-label-sm">${DURACION }</label>
					<div class="col-lg-2">
						<input type="text" class="form-control input-sm digits-only" name="duracion" id="duracionTratamiento" />
					</div>
                    <label for="via_adm" class="col-lg-3 control-label control-label-sm">${VIA_ADMINISTRACION }</label>
                    <div class="col-lg-4">
                        <form:select path="viasAdministracion" name="viaAdministracion" id="viasAdministracion" class="form-control input-sm">
                            <form:option value="" label="${SELECCIONE_LABEL}" />
                            <form:options items="${viasAdministracion}" itemLabel="descripcion" itemValue="id" />
                        </form:select>
                    </div>
				</div>
				<div class="form-group form-group-sm">
					<label for="posologia" class="col-lg-3 control-label control-label-sm">${POSOLOGIA}</label>
					<div class="col-lg-9">
						<textarea class="form-control input-sm" name="posologia" id="posologia"></textarea>
					</div>
				</div>
				<div class="form-group form-group-sm template-hidden nopos-field">
					<label for="efectosAdversos" class="col-lg-3 control-label control-label-sm">${EFECTOS_ADVERSOS}</label>
					<div class="col-lg-9">
						<textarea class="form-control input-sm" name="efectosAdversos" id="efectosAdversos"></textarea>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" id="idAceptarPrescripcionMedicamento" class="btn btn-primary btn-success">Aceptar</button>
		<button type="button" id="idCancelarPrescripcionMedicamento" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
	</div>
</div>

<script>

	function fillprescripcionMedicamento($container) {
	    $("#dosis").change(function() {
	        setCantidad();
	    });
	    $("#frecuencia").change(function() {
	        setCantidad();
	    });
	    $("#cadaSelect").change(function() {
	        setCantidad();
	    });
	    $("#duracionTratamiento").change(function() {
	        setCantidad();
	    });
		
		$("#prescripcionMedicamentoForm .nopos-field").toggle($container.parents("tr").data("data").visibleCtc);	
		$("#prescripcionMedicamentoForm #item-description").text($container.parents("tr").find("[id='descripcion']").val());
		
		if ($container.data()) {
			$.each($container.data(), function(i, e) {
			    $("#prescripcionMedicamentoForm [name='" + e.name + "']").val(e.value);
			});
		}else{
			$("#prescripcionMedicamentoForm .form-control").val('');
		}
	}

			
	function applyPrescripcionMedicamento() {
		$container.data($("#prescripcionMedicamentoForm").serializeArray());
		$container.attr("data-form-prescr", $("#prescripcionMedicamentoForm").serialize());
	}

	function resetFieldsPrescripcionMedicamento() {
		genericResetFields("prescripcionMedicamentoForm");
		setDefaults("prescripcionMedicamentoForm");
		resetValidateForm("prescripcionMedicamentoForm");
	}
	
	function setCantidad() {
	    
	    var dosis = $("#dosis").val() ? $("#dosis").val() : 0;
	    var frecuencia = $("#frecuencia").val() ? $("#frecuencia").val() : 0;
	    var cada = $("#cadaSelect").val() ? $("#cadaSelect").val() : 1;
	    var duracion = $("#duracionTratamiento").val() ? $("#duracionTratamiento").val() : 1;
	    
	    // Se calcula cada cuantos días se toma el medicamento
	    if(cada == 1) {
	        frecuencia = parseFloat(frecuencia / 24);
	    }
	    
	    var cantidad = dosis * (duracion / frecuencia);
	    
	    if(!isFinite(cantidad))
	        cantidad = 0;
	    
	   	$($container.closest('tr').find("input[id=cant]")).val(parseInt("" + cantidad));
	}
	
	$(document).ready(function() {
		
		$("#prescripcionMedicamentoForm").validate({
		    onfocusout: false,
		    focusInvalid: false,
		    focusCleanup: false,
		    onkeyup: false,
		    onclick: false,
		    rules: {
		    	dosis:{required:true, digits:true},
		    	frecuencia:{required:true, digits:true},
		    	duracion:{required:true, digits:true},
		    	cada : {required:true},
		    	viaAdministracion:"required",
		    	posologia:{required:true, minlength:30, maxlength:500},
		    	causaExterna: {required:true},
		    	finalidad: {required:true},
		    	tipoCatastrofico: {required:true},
		    	efectosAdversos:{required:function(){return $("#prescripcionMedicamentoForm .nopos-field").is(":visible");},minlength:30, maxlength:500}
		    },
		    messages: {
		    	dosis:{required:'<fmt:message key="validation.required"><fmt:param value="${DOSIS}"/></fmt:message>',digits:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${DOSIS}"/></fmt:message>'},
		    	frecuencia:{required:'<fmt:message key="validation.required"><fmt:param value="${FRECUENCIA}"/></fmt:message>',digits:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${FRECUENCIA}"/></fmt:message>'},
		    	duracion:{required:'<fmt:message key="validation.required"><fmt:param value="${DURACION}"/></fmt:message>',digits:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${DURACION}"/></fmt:message>'},
		    	cada : {required:'<fmt:message key="validation.required"><fmt:param value="Tipo de frecuencia"/></fmt:message>'},
		    	viaAdministracion:'<fmt:message key="validation.required"><fmt:param value="${VIA_ADMINISTRACION}"/></fmt:message>',
		    	posologia:{required:'<fmt:message key="validation.required"><fmt:param value="${POSOLOGIA}"/></fmt:message>',
		    	    		minlength:'<fmt:message key="message.error.minLength.30"><fmt:param value="${POSOLOGIA}"/></fmt:message>',
		    	    		maxlength:'<fmt:message key="validation.restriction.betweenNandMChars"><fmt:param value="${POSOLOGIA}"/></fmt:message>.'.replace("{1}",30).replace("{2}",500)},
		    	efectosAdversos:{required:'<fmt:message key="validation.required"><fmt:param value="${EFECTOS_ADVERSOS}"/></fmt:message>',
		    	    	minlength:'<fmt:message key="message.error.minLength.30"><fmt:param value="${EFECTOS_ADVERSOS}"/></fmt:message>',
		    	    	maxlength:'<fmt:message key="validation.restriction.betweenNandMChars"><fmt:param value="${EFECTOS_ADVERSOS}"/></fmt:message>.'.replace("{1}",30).replace("{2}",500)},		    	
			    causaExterna: 	 '<fmt:message key="validation.required"><fmt:param value="${CAUSA_EXTERNA}"/></fmt:message>',
			    finalidad: 		 '<fmt:message key="validation.required"><fmt:param value="${FINALIDAD}"/></fmt:message>',
			    tipoCatastrofico:'<fmt:message key="validation.required"><fmt:param value="${TIPO_CATASTROFICO}"/></fmt:message>'
		    },  
		    errorPlacement: function (error, element){
		    	appendErrorMessage($("#prescripcion-medicamento-messages"), error.text());
		    }
		});
		
		$("#idAceptarPrescripcionMedicamento").click(function() {
			$("#prescripcion-medicamento-messages").empty();
			if ($("#prescripcionMedicamentoForm").valid()){
				applyPrescripcionMedicamento();
				resetFieldsPrescripcionMedicamento();
				$container.removeClass("btn-warning");
				$container.addClass("btn-success");
				$("#prescripcionMedicamento").modal('hide');	
			}
		});

		$("#idCancelarPrescripcionMedicamento").click(function() {
			resetFieldsPrescripcionMedicamento();
		});

	});		
		$("#prescripcionMedicamento button.close").click(function() {
			resetFieldsPrescripcionMedicamento();
		});
;
</script>