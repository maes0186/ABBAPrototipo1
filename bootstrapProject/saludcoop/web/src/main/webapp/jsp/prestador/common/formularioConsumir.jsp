<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="webContext" value="${pageContext.request.contextPath}" />
<jsp:include page="./../../includes/components.jsp" />

<fmt:message key='label.select' var="SELECCIONE_LABEL" />

<div class="modal fade" id="formularioConsumir" data-width="75%" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Confirmacion de Consumo</h3>
	</div>
	<div class="modal-body">
		<div id="confirmacion-consumo-messages"></div>
			<c:if test="${empty sedeIps}">
				<div class="row">
					<div class="col-lg-12 title">
						<form id="formSedeIps" class="well clearfix abmComponent withSearch uniqueResult" javaObject="sedeIps">
							<fieldset>
								<blockquote class="col-lg-12" style="padding: 2px; font-size: 20px;">
									<strong>Información Ips</strong>
								</blockquote>
								<div class="form-group form-group-sm searchForm  collapsible-content">
									<input role="identificacion" tipoDatos="ips" nameLeft="tipoIdentificacion" nameRight="numeroIdentificacion" class="template-hidden col-lg-12" avoidDescriptionLabel="true" /> 
									<input placeholder="Razón Social" name="razonSocial" id="razonSocial" row="2" class="col-lg-4 template-hidden"> 
									<input placeholder="Código Municipio" id="municipioCodigo" row="3" class="col-lg-2 template-hidden"> 
									<input placeholder="Nombre Municipio" id="municipioNombre" row="3" class="col-lg-2 template-hidden"> 
									<input placeholder="Dirección" id="direccion" row="4" class="col-lg-4 template-hidden">
								</div>
								<div class="row collapsible-content">
									<div class="col-lg-12">
										<table class="table table-hover">
											<thead>
												<tr class="row">
													<th class="data col-lg-2" name="razonSocial">Razón Social</th>
													<th class="data col-lg-2" name="municipioNombre">Municipio</th>
													<th class="data col-lg-2" name="direccion">Dirección</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</c:if>
			<c:if test="${empty profesionalEspecialidad}">
				<div class="row">
					<div class="col-lg-12 title">
						<form id="formProfesional" class="well  clearfix abmComponent withSearch uniqueResult" javaObject="profesional">
							<fieldset>
								<blockquote class="col-lg-12" style="padding: 2px; font-size: 20px;">
									<strong>Profesional Prestador</strong>
								</blockquote>
								<div class="form-group form-group-sm searchForm  collapsible-content">
									<input placeholder="Registro Médico" id="registroMedico" class="col-lg-3"> 
									<input role="identificacion" tipoDatos="profesional" nameLeft="tipoDocumento" nameRight="numeroDocumento" class="template-hidden col-lg-12" avoidDescriptionLabel="true" row=2 /> 
									<input placeholder="Primer Nombre" id="primerNombre" class="col-lg-3 template-hidden" row="3"> 
									<input placeholder="Segundo Nombre" id="segundoNombre" class="col-lg-3 template-hidden" row="3"> 
									<input placeholder="Primer Apellido" id="primerApellido" class="col-lg-3 template-hidden" row="4"> 
									<input placeholder="Segundo Apellido" id="segundoApellido" class="col-lg-3 template-hidden" row="4"> 
									<input placeholder="Especialidad" row="5" role="combo-search" valueFrom="especialidades" id="especialidad" class="col-lg-3 template-hidden">
								</div>
								<div class="row collapsible-content">
									<div class="col-lg-12">
										<table class="table  table-hover">
											<thead>
												<tr class="row">
													<th class="data col-lg-1" name="tipoIdentificacion">Tipo Ident.</th>
													<th class="data col-lg-3" name="numeroIdentificacion">Número Ident.</th>
													<th class="data col-lg-3" name="registroMedicol">Registro Médico</th>
													<th class="data col-lg-3" name="nombreCompleto">Nombre y Apellido</th>
													<th class="data col-lg-3" name="especialidadDescripcion">Especialidad</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</c:if>
		<form id="formularioConsumirForm" class="form-horizontal">
			<fieldset>
				<div class="form-group form-group-sm">
					<label for="fechaConsumo" class="col-lg-6 control-label">Fecha de Consumo:</label>
					<div id="fechaConsumoButton" class="col-lg-5 input-group date" data-date-format="dd/mm/yyyy" data-date="">
						<input type="text" name="fechaConsumo" class="form-control"	id="fechaConsumo" /> <span class="input-group-addon input-sm"><i class="icon-calendar"></i></span>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" id="confirmarConsumir" class="btn btn-primary btn-success">Confirmar</button>
		<button type="button" id="cancelarConsumir" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
	</div>
</div>
<div id="ajax-modal" class="modal fade" tabindex="-1" style=""
	aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h3></h3>
	</div>
	<div class="modal-body"></div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-danger">Cancelar</button>
	</div>
</div>
<form:select id="especialidades" path="especialidades"  cssClass="form-control template-hidden" cssStyle="display:none">  
     <form:option value="" label="${SELECCIONE_LABEL}"/>  
     <form:options items="${especialidades}" itemLabel="descripcion" itemValue="id"/>  
</form:select> 
<script>

	function getMensajeRangoProfesional(){
		var identificacionMinLength = $("#formProfesional #tipoDocumento option:selected").attr("data-min-length");
		var identificacionMaxLength = $("#formProfesional #tipoDocumento option:selected").attr("data-max-length");
		
		if(identificacionMinLength === identificacionMaxLength){
			return '<fmt:message key="validation.restriction.exactlyNChars"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'.replace("{1}",identificacionMinLength);
		}else{
			return '<fmt:message key="validation.restriction.betweenNandMChars"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'.replace("{1}",identificacionMinLength).replace("{2}",identificacionMaxLength);	
		}
	}

	var sedeIps = false;
	<c:if test="${not empty sedeIps}">
		sedeIps=true;
	</c:if>
	
	var profesional = false;
	<c:if test="${not empty profesionalEspecialidad}">
		profesional=true;
	</c:if>
	
	function setSedeIps(data){
		$.ajax({
			url : "${webContext}/prestador/set_sedeIps",
			dataType : "json",
			type : "POST",
			data: data,
			success : function(resultado) {
				console.log(resultado);
				sedeIps = true;
				$("#formProfesional")[0].enable();
			},
			error : function(resultado) {
					console.log(resultado);
			}
				
			}
		);
	}
	
	function setProfesional(data){
		$.ajax({
			url : "${webContext}/prestador/set_profesional",
			dataType : "json",
			data: data,
			type : "POST",
			success : function(resultado) {
					console.log(resultado);
					profesional = true;
					
				},
				error : function(resultado) {
						console.log(resultado);
				}
					
				}
			);
	}
	
	function buildIps() {
		var attributes = ["sedeIps.ips.razonSocial", "sedeIps.ips.tipoId", "sedeIps.ips.numeroIdentificacion", 
		                  "sedeIps.municipio.codigo", "sedeIps.municipio.descripcion", "sedeIps.direccion"];
		var values = [$("#formIPS #razonSocial").val(), $("#formIPS #tipoIdentificacion").val(), $("#formIPS #numeroIdentificacion").val(), 
		              $("#formIPS #municipioCodigo").val(), $("#formIPS #municipioNombre").val(), $("#formIPS #direccion").val()];
		return mergeAttributeValue(attributes, encodeArray(values));
	}
	
	function buildProfesional() {
		var attributes = ["profesionalEspecialidad.registroMedico", "profesionalEspecialidad.tipoIdentificacion", "profesionalEspecialidad.numeroIdentificacion", 
		                  "profesionalEspecialidad.primerNombre", "profesionalEspecialidad.segundoNombre", "profesionalEspecialidad.primerApellido", 
		                  "profesional.segundoApellido"];
		var values = [$("#formProfesional #registroMedico").val(), $("#formProfesional #tipoDocumento").val(), $("#formProfesional #numeroDocumento").val(), 
		              $("#formProfesional #primerNombre").val(), $("#formProfesional #segundoNombre").val(), $("#formProfesional #primerApellido").val(),
		              $("#formProfesional #segundoApellido").val()];
		return mergeAttributeValue(attributes, encodeArray(values));
	}
	
	$(document).ready(function() {
		configABMComponents();
		if(!sedeIps){
			$("#formProfesional").attr("dependentForm","formSedeIps");
		}
		if(!profesional){
			$("#formProfesional")[0].setValidateBeforeSearch(function(){
				if (!$.parseJSON($("#formProfesional #tipoDocumento option:selected").attr("data-alpha"))) 
					$(this).validate(
							{
								onfocusout : false,
								focusInvalid : false,
								focusCleanup : false,
								onkeyup : false,
								onclick : false,
								rules : {
									numeroDocumento : {required:function(){return $("#formProfesional #tipoDocumento").val() != "";}, 
															minlength:parseInt($("#formProfesional #tipoDocumento option:selected").attr("data-min-length")),
															maxlength:parseInt($("#formProfesional #tipoDocumento option:selected").attr("data-max-length")),
															digits:true
																},
									primerNombre: {minlength:4},
									segundoNombre: {minlength:4},
									primerApellido: {minlength:4},
									segundoApellido: {minlength:4}						
								},
								messages : {
									numeroDocumento : {	required:'<fmt:message key="validation.required"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.',
														minlength:getMensajeRangoProfesional,
														maxlength:getMensajeRangoProfesional,
														digits: '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'},
									primerNombre: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Primer Nombre"/><fmt:param value="4"/></fmt:message>.',
									segundoNombre: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Segundo Nombre"/><fmt:param value="4"/></fmt:message>.',
									primerApellido: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Primer Apellido"/><fmt:param value="4"/></fmt:message>.',
									segundoApellido: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Segundo Apellido"/><fmt:param value="4"/></fmt:message>.'		
														
								},  errorPlacement: function (error, element){
							    	appendErrorMessage($("#formProfesional .messages"), error.text());
							    }
							});
				else
					$(this).validate(
							{
								onfocusout : false,
								focusInvalid : false,
								focusCleanup : false,
								onkeyup : false,
								onclick : false,
								rules : {
									numeroDocumento : {required:function(){return $("#formProfesional #tipoDocumento").val() != "";}, 
															minlength:parseInt($("#formProfesional #tipoDocumento option:selected").attr("data-min-length")),
															maxlength:parseInt($("#formProfesional #tipoDocumento option:selected").attr("data-max-length")),
															digits:false
																},
									primerNombre: {minlength:4},
									segundoNombre: {minlength:4},
									primerApellido: {minlength:4},
									segundoApellido: {minlength:4}						
								},
								messages : {
									numeroDocumento : {	required:'<fmt:message key="validation.required"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.',
														minlength:getMensajeRangoProfesional,
														maxlength:getMensajeRangoProfesional},
									primerNombre: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Primer Nombre"/><fmt:param value="4"/></fmt:message>.',
									segundoNombre: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Segundo Nombre"/><fmt:param value="4"/></fmt:message>.',
									primerApellido: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Primer Apellido"/><fmt:param value="4"/></fmt:message>.',
									segundoApellido: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Segundo Apellido"/><fmt:param value="4"/></fmt:message>.'		
														
								},  errorPlacement: function (error, element){
							    	appendErrorMessage($("#formProfesional .messages"), error.text());
							    }
							});
				
				
				return validateBeforeSearch(this) && $(this).valid();
			});
			$("#formProfesional")[0].setAddHandler(setProfesional);
		}
		
		if(!sedeIps){
			$("#formSedeIps")[0].setAddHandler(setSedeIps);
		}else if (!profesional){
			$("#formProfesional")[0].enable();
		}
		
		$('#fechaConsumo').val($.datepicker.formatDate('dd/mm/yy', new Date()));
		$('#fechaConsumoButton').datepicker({endDate : '+0d'}).on('changeDate', function(ev) {
			$('#fechaConsumoButton').datepicker('hide');
		});

		$("#confirmarConsumir").click(
			function() {
				$("#cancelarConsumir").click();
				$("#messages").empty();
				$.post("${webContext}/prestador/consumirSolicitud", buildDataConsumo(), callbackConsumir);
				$("#messages").show();
				createAlertMessage( $("#messages"),
						{
							type : "info",
							message : "La solicitud está siendo procesada.",
							autoShow : true,
							dismissable : false
						});
				$(window).scrollTop(0);
				/*document.forms[0].submit();*/
			});

		$("#cancelarConsumir").click(function() {
					$('#fechaConsumo').val( $.datepicker.formatDate('dd/mm/yy', new Date()));
		});
	});

	function buildDataConsumo() {
		fecha = $('#fechaConsumo').val();
		id = $('#numeroSolicitudInput').val();
		data = {
			fechaConsumo : fecha,
			solicitudItemId : id
		}
		return data;
	}
	function callbackConsumir(data) {
		if (data.generalErrors.length > 0) {
			$("#messages").empty();
			createErrorMessages($("#messages"), data.generalErrors);
		} else {
			location.href = '${pageContext.request.contextPath}/jsp/common/ticket.jsp';
		}
	}
</script>