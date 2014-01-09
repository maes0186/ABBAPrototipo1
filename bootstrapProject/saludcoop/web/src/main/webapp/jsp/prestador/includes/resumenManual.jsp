<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fmt:message key='label.causaExterna' var="CAUSA_EXTERNA" />
<fmt:message key='label.tipoCatastrofico' var="TIPO_CATASTROFICO" />
<fmt:message key='label.fecha.inicio' var="FECHA_INICIO" />
<fmt:message key='label.fecha.fin' var="FECHA_FIN" />
<fmt:message key='label.conducta' var="CONDUCTA" />
<fmt:message key='label.evolucion' var="EVOLUCION" />
<fmt:message key='label.codigoCIE10' var="CODIGO_CIE10" />
<fmt:message key='label.name' var="NOMBRE" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />

<div class="modal fade" id="resumenManual" data-width="900" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Resumen Historia Clínica</h3>
	</div>
	<div class="modal-body">
		<div id="resumen-manual-messages"></div>
		<form id="resumenManualForm" class="form-horizontal">
			<fieldset>
				<div class="row col-lg-12 col-sm-12 col-xs-12 alert-error text-danger" id="alert" style="display: block;">
					<strong></strong>
			  	</div>
				<div class="form-group form-group-sm">
					<label class="control-label control-label-sm col-lg-3">${FECHA_INICIO }</label>
				  	<div id="fechaInicioButton" class="col-lg-3 input-group date"   data-date-format="dd-mm-yyyy" data-date="">
						<input type="text" name="fechaInicio" class="form-control input-sm" id="fechaInicio" readonly/>
						<span class="input-group-addon input-sm"><i class="icon-calendar"></i></span>
					</div>
					<label class="control-label control-label-sm col-lg-3">${FECHA_FIN }</label>
					<div id="fechaFinButton" class="col-lg-3 input-group date" data-date-format="dd-mm-yyyy" data-date="">
						<input type="text" name="fechaFin" class="form-control input-sm " id="fechaFin" readonly/>
						<span class="input-group-addon input-sm" ><i class="icon-calendar"></i></span>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="evolucion" class="col-lg-3 control-label control-label-sm">${EVOLUCION }</label>
					<div class="col-lg-9">
						<textarea rows="3" class="form-control input-sm" name="evolucion" id="evolucion"></textarea>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="causaExterna" class="col-lg-3 control-label control-label-sm">${CAUSA_EXTERNA }</label>
					<div class="col-lg-3">
						<form:select path="causasExternas" name="causaExterna" id="causasExternas" class="form-control input-sm" default-value="13">
							<form:option value="" label="${SELECCIONE_LABEL}" />
							<form:options items="${causasExternas}" itemLabel="descripcion" itemValue="id" />
						</form:select>
					</div>
					<label for="tiposCatastroficos" class="col-lg-3 control-label control-label-sm">${TIPO_CATASTROFICO }</label>
					<div class="col-lg-3">
						<form:select path="tiposCatastroficos" name="tipoCatastrofico" id="tiposCatastroficos" class="form-control input-sm" default-value="1">
							<form:option value="" label="${SELECCIONE_LABEL}" />
							<form:options items="${tiposCatastroficos}" itemLabel="descripcion" itemValue="id" />
						</form:select>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 title">
						<div id="resumenDiagnosticos"
							class="well  clearfix abmComponent withSearch"
							javaObject="diagnosticos">
							<fieldset>
								<blockquote class="col-lg-12 collapsible-header"
									style="padding: 2px; font-size: 20px;">
									<strong>Diagnóstico</strong>
								</blockquote>
								<div class="form-group form-group-sm searchForm  collapsible-content">
								 <input	placeholder="${CODIGO_CIE10}" id="codigo" class="template-hidden col-lg-3">
								 <input placeholder="${NOMBRE}"  id="descripcion" class="template-hidden col-lg-3">
						        </div>
								<div class="row collapsible-content">
									<div class="col-lg-12">
										<table class="table  table-hover">
											<thead>
												<tr class="row">
													<th class="data" name="id" thclass="template-hidden" tdclass="template-hidden" role="hidden"></th>
													<th class="data col-lg-2" name="codigo" tdClass="col-lg-2">${CODIGO_CIE10}</th>
													<th class="data col-lg-8" name="descripcion" tdClass="col-lg-10">${NOMBRE}</th>
<!-- 													<th class="editable col-lg-1" role="radioButton" -->
<!-- 														name="esPrincipal">Principal</th> -->
													<th class="editable col-lg-2" role="buttonEliminar"></th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="conducta" class="col-lg-3 control-label control-label-sm">${CONDUCTA}</label>
					<div class="col-lg-9">
						<textarea rows="3" class="form-control input-sm" name="conducta" id="conducta"></textarea>
					</div>
				</div>
						
		</fieldset>
	</form>
</div>
<div class="modal-footer">
	<button type="button" id="idAceptarResumen" class="btn btn-primary btn-success">Aceptar</button>
	<button type="button" id="idCancelarResumen" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
</div>
</div>

<script>

	function fillResumenManual(container) {
		
		if (container.data().data) {
			var mapaDiagnosticos = new Array();
			$.each(container.data().data, function(i, e) {
				if (e.name.indexOf("[") != -1)  {
					agregaAMapa(e, mapaDiagnosticos);
				} else {
					switch($("#resumenManual [name='" + e.name + "']").prop("type")) {
					case "radio":
						$("#resumenManual [name='" + e.name + "'][value=" + e.value + "]").prop("checked", true);
						break;
					case "checkbox":
						if (e.value) {
							$("#resumenManual [name='" + e.name + "']").click();	
						}
						break;
					default:
						$("#resumenManual [name='" + e.name + "']").val(e.value);
					}
				}
			});
			for (var tipoDiagnostico in mapaDiagnosticos) {
				for (var diagnostico in mapaDiagnosticos[tipoDiagnostico]) {
					var diag = mapaDiagnosticos[tipoDiagnostico][diagnostico];
					$("#resumenDiagnosticos")[0].addRow(diag, $("#resumenDiagnosticos")[0].techFields);

				}
			}
		}
	}



	function applyResumen() {
		$container.attr("data-form-resumen", $("#resumenManualForm").serialize());
		resumenManual=true;
	}

	function resetFieldsResumen() {
		$("#resumenManualForm .form-control").val("");
		$("#resumenDiagnosticos tbody").empty();
		$("#resumenDiagnosticos thead").hide();
	}
	
	function validateResumenDiagnosticos(){
		if ($("#resumenManualForm input.input-flat[name*='diagnostico']").length==0 && !esLDF){
			appendErrorMessage($("#resumen-manual-messages"),"Debe diligenciar al menos 1 diagnóstico, y marcar alguno como principal.");
			return false;
		}
		return true;
	}
	
	$(document).ready(function() {
		
		$("#resumenDiagnosticos")[0].setAddHandler(addDiagnostico);
		$("#resumenDiagnosticos")[0].setRemoveHandler(removeDiagnostico);
		
		$("#resumenManualForm").validate({
		    onfocusout: false,
		    focusInvalid: false,
		    focusCleanup: false,
		    onkeyup: false,
		    onclick: false,
		    rules: {
		    	tipoCatastrofico:"required",
		    	causaExterna:"required",
		    	evolucion:"required",
		    	fechaFin:"required",
		    	fechaInicio:"required",
		    	conducta:"required"
		    },
		    messages: {
		    	tipoCatastrofico:'<fmt:message key="validation.required"><fmt:param value="${TIPO_CATASTROFICO}"/></fmt:message>',
		    	causaExterna:'<fmt:message key="validation.required"><fmt:param value="${CAUSA_EXTERNA}"/></fmt:message>',
		    	evolucion:'<fmt:message key="validation.required"><fmt:param value="${EVOLUCION}"/></fmt:message>',
		    	fechaFin:'<fmt:message key="validation.required"><fmt:param value="${FECHA_FIN}"/></fmt:message>',
		    	fechaInicio:'<fmt:message key="validation.required"><fmt:param value="${FECHA_INICIO}"/></fmt:message>',
		    	conducta:'<fmt:message key="validation.required"><fmt:param value="${CONDUCTA}"/></fmt:message>'
		    },  
		    errorPlacement: function (error, element){
		    	appendErrorMessage($("#resumen-manual-messages"), error.text());
		    }
		});
		
		
		$("#idAceptarResumen").click(function() {
			$("#resumen-manual-messages").empty();
			
			var isValid = $("#resumenManualForm").valid();
			var tieneDiagnosticos = validateResumenDiagnosticos();
			
			if (isValid && tieneDiagnosticos){
				applyResumen();
				$("#resumenManual").modal('hide');
				$container.removeClass("btn-warning-danger");
				$container.addClass("btn-success");
				$container.text("<fmt:message key='label.button.modificarResumen'/>");
				archivos();
			}
			
		});

		$("#idCancelarResumen").click(function() {
			resetFieldsResumen();
		});
		
		var startDate ="" , endDate = "" ;
		var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
		$('#fechaInicioButton')
		    .datepicker({startDate: "now"})
		    .on('changeDate', function(ev){
		        if (endDate != "" && endDate!=undefined && ev.date.valueOf() > endDate.valueOf()){
		            $('#alert').show().find('strong').text('<fmt:message key="validation.date.range.notGreater"/>');
		            startDate = "";
		            $('#fechaInicio').val("");
		        } else {
		            $('#alert').hide();
		            startDate = new Date(ev.date);
		        }
		        $('#fechaInicioButton').datepicker('hide');
		    });
		$('#fechaFinButton')
		    .datepicker()
		    .on('changeDate', function(ev){
		        if (startDate != "" && startDate!=undefined && ev.date.valueOf() < startDate.valueOf()){
		            $('#alert').show().find('strong').text('<fmt:message key="validation.date.range.notLess"/>');
		            endDate = "";
		            $('#fechaFin').val("");
		        } else {
		            $('#alert').hide();
		            endDate = new Date(ev.date);
		        }
		        $('#fechaFinButton').datepicker('hide');
		    });

	});
</script>