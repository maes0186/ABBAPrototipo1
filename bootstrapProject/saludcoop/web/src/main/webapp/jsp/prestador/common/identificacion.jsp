<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="./../../includes/header.jsp">
	<jsp:param name="includeMenu" value="true" />
</jsp:include>
<fmt:message key='label.id.type' var="TIPO_DOCUMENTO_LABEL" />
<fmt:message key='label.id.number' var="NUMERO_DOCUMENTO_LABEL" />
<fmt:message key="label.names" var="NOMBRES"/>
<fmt:message key="label.name.lasts" var="APELLIDOS"/>
<fmt:message key='label.select' var="SELECCIONE_LABEL" />
<fmt:message key='label.EPS' var="EPS" />
<c:set var="webContext" value="${pageContext.request.contextPath}" />

<div class="row">
	<div class="col-lg-12 title">
		<div id="formAfiliadoBusqueda" class="well clearfix">
			<fieldset>
				<blockquote class="col-lg-12" style="padding: 2px; font-size: 20px;">
					<strong>Consulta de Afiliado</strong>
				</blockquote>
				<ul class="nav nav-tabs" style="margin-bottom: 15px">
					<li class="active"><a href="#formDocumento" data-toggle="tab">Documento</a></li>
					<li class=""><a href="#formNombre" data-toggle="tab">Nombre</a></li>
				</ul>
				<div id="identificacionTabContent" class="tab-content">
					<div class="abmComponent withSearch uniqueResult tab-pane fade active in" id="formDocumento" javaObject="afiliadoDocumento" searchheader="Afiliado">
						<form class="row form-group form-group-sm form-group form-group-sm-sm searchForm " >
							<input role="identificacion" tipoDatos="afiliado" nameLeft="tipoIdentificacion" nameRight="numeroIdentificacion" class="template-hidden col-lg-12" avoidDescriptionLabel="true"/>
							<input placeholder="EPS" row="2" role="combo-search" valueFrom="epsCombo" id="eps" class="col-lg-3 template-hidden"> 
						</form>
						<div class="row">
							<div class="col-lg-12">
								<table class="table  table-hover">
									<thead>
										<tr class="template row">
											<th class="data col-lg-1" name="tipoIdentificacion">Tipo Ident.</th>
											<th class="data col-lg-3" name="numeroIdentificacion">Número Ident.</th>
											<th class="data col-lg-5" name="nombreCompleto">Nombre y Apellido</th>

										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="abmComponent withSearch uniqueResult tab-pane fade" id="formNombre" javaObject="afiliadoNombres" searchheader="Afiliado">
						<form class="row form-group form-group-sm form-group form-group-sm-sm searchForm" searchheader="Afiliado">
							<input placeholder="${NOMBRES }" id="nombres" class="template-hidden col-lg-3" > <input	placeholder="${APELLIDOS }" id="apellidos" class="template-hidden col-lg-3" >
							<input placeholder="EPS" row="2" role="combo-search" valueFrom="epsCombo" id="eps" class="col-lg-3 template-hidden"> 
						</form>
						<div class="row">
							<div class="col-lg-12">
								<table class="table  table-hover">
									<thead>
										<tr class="template row">
											<th class="data col-lg-4" name="tipoIdent">Tipo Ident.</th>
											<th class="data col-lg-3" name="numeroIdentificacion">Número Ident.</th>
											<th class="data col-lg-5" name="nombreCompleto">Nombre y Apellido</th>

										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>
						</div>
					</div>		
				</div>
			</fieldset>
		</div>
	</div>
</div>

<jsp:include page="./../includes/datosAfiliado.jsp">
	<jsp:param name="editable" value="true" />
</jsp:include>


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

<jsp:include page="./../../includes/components.jsp" />	
<select id ="epsCombo" class="template-hidden">
	<option value="">${SELECCIONE_LABEL}</option>
	<c:forEach items="${eps}" var="eps">
		<option value="${eps.id}" >${eps.razonSocial}</option>	
	</c:forEach>
</select>
<script>
	//PRESETEOS Y FUNCIONES
	var errorsContainer;
	var target = '${param.target}';
	
	function getMensajeRango(){
		if($("#formDocumento [id^=tipo] option:selected").attr("data-min-length") === $("#formDocumento [id^=tipo] option:selected").attr("data-max-length")){
			return '<fmt:message key="validation.restriction.exactlyNChars"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'.replace("{1}",$("#formDocumento [id^=tipo] option:selected").attr("data-min-length"));
		}else{
			return '<fmt:message key="validation.restriction.betweenNandMChars"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'.replace("{1}",$("#formDocumento [id^=tipo] option:selected").attr("data-min-length")).replace("{2}",$("#formDocumento [id^=tipo] option:selected").attr("data-max-length"));	
		}
	}
	
	function validarBusquedaPorDocumento(){
		$("#formDocumento>form>.messages").empty();
		
		if (!$.parseJSON($("#formDocumento [id^=tipo] option:selected").attr("data-alpha")))
			$("#formDocumento>form")
			.validate(
					{
						onfocusout : false,
						focusInvalid : false,
						focusCleanup : false,
						onkeyup : false,
						onclick : false,
						rules : {
							tipoIdentificacion : "required",
							numeroIdentificacion : {required:true, 
													minlength:$("#formDocumento [id^=tipo] option:selected").attr("data-min-length"),
													maxlength:$("#formDocumento [id^=tipo] option:selected").attr("data-max-length"),
													digits:true
														},
							eps :		"required"
						},
						messages : {
							tipoIdentificacion : '<fmt:message key="validation.required"><fmt:param value="${TIPO_DOCUMENTO_LABEL}"/></fmt:message>.',
							numeroIdentificacion : {	required:'<fmt:message key="validation.required"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.',
												minlength:getMensajeRango,
												maxlength:getMensajeRango,
												digits: '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'},
							eps : '<fmt:message key="validation.required"><fmt:param value="${EPS}"/></fmt:message>.'
						},  errorPlacement: function (error, element){
					    	appendErrorMessage($("#formDocumento>form>.messages"), error.text());
					    }
					});
		else
			$("#formDocumento>form")
			.validate(
					{
						onfocusout : false,
						focusInvalid : false,
						focusCleanup : false,
						onkeyup : false,
						onclick : false,
						rules : {
							tipoIdentificacion : "required",
							numeroIdentificacion : {required:true, 
													minlength:$("#formDocumento [id^=tipo] option:selected").attr("data-min-length"),
													maxlength:$("#formDocumento [id^=tipo] option:selected").attr("data-max-length"),
													digits:false
														},
							eps :		"required"
						},
						messages : {
							tipoIdentificacion : '<fmt:message key="validation.required"><fmt:param value="${TIPO_DOCUMENTO_LABEL}"/></fmt:message>.',
							numeroIdentificacion : {	required:'<fmt:message key="validation.required"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.',
												minlength:getMensajeRango,
												maxlength:getMensajeRango},
							eps : '<fmt:message key="validation.required"><fmt:param value="${EPS}"/></fmt:message>.'
						},  errorPlacement: function (error, element){
					    	appendErrorMessage($("#formDocumento>form>.messages"), error.text());
					    }
					});
		
		
		return $("#formDocumento>form").valid();
	}
	
	function validarBusquedaPorNombre(){
		$("#formNombre>form>.messages").empty();

		return $("#formNombre>form").valid();
	}
	
	$(function() {
		configABMComponents();
		
		$("#tipoIdentificacion").focus();
		
		$("#formNombre>form")
		.validate(
				{
					onfocusout : false,
					focusInvalid : false,
					focusCleanup : false,
					onkeyup : false,
					onclick : false,
					rules : {
						nombres : 	{required:true, 
							 		 minlength:4},
						apellidos : {required:true, 
									 minlength:4},
						eps :		"required"
					},
					messages : {
						nombres : {required:'<fmt:message key="validation.required"><fmt:param value="${NOMBRES}"/></fmt:message>.',
									minlength:'<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="${NOMBRES}"/><fmt:param value="4"/></fmt:message>'
						},
						apellidos : {	required:'<fmt:message key="validation.required"><fmt:param value="${APELLIDOS}"/></fmt:message>.',
										minlength:'<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="${APELLIDOS}"/><fmt:param value="4"/></fmt:message>'},
						eps : '<fmt:message key="validation.required"><fmt:param value="${EPS}"/></fmt:message>.'
									
					},  errorPlacement: function (error, element){
				    	appendErrorMessage($("#formNombre>form>.messages"), error.text());
				    }
				});
		
		
		$("#formDocumento")[0].setOnClean(
					function(){
						$("#datosAfiliado").hide();
					}
		);
		$("#formNombre")[0].setOnClean(
				function(){
					$("#datosAfiliado").hide();
				}
		);
		
		$("#formDocumento")[0].setCheckAfterAdd(function(event) {
			var formData = $('#formDocumento').data("data");
			 
			errorsContainer = $("#formDocumento .messages");
			$.post('${pageContext.request.contextPath}/prestador/identificacion',
							formData, displayDatosAfiliado);
		});
		 

		$("#formNombre")[0].setCheckAfterAdd(function(event) {
			var formData = $('#formNombre').data("data");
			
			errorsContainer = $("#formNombre .messages");
			$.post('${pageContext.request.contextPath}/prestador/identificacion',
							formData, displayDatosAfiliado);

		});
		
		$("#formDocumento")[0].setValidateBeforeSearch(validarBusquedaPorDocumento);
		$("#formNombre")[0].setValidateBeforeSearch(validarBusquedaPorNombre);

		var menuId = 0;
		if (target == 'solicitud') {
			menuId = 2;
		} else if (target == 'comprobacion') {
			menuId = 1;
		}
		
		$("#formAfiliadoBusqueda [role=limpiar]").bind("click", function(ev){$.get("${webContext}/removeAfiliadoFromSession");});


	});

</script>

<%@include file="./../../includes/footer.jsp"%>
