<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<fmt:message key='label.SedeIps' var="VARIABLE_IPS" />
<fmt:message key='label.nopos.justificacion' var="VARIABLE_JUSTIFICACION" />
<fmt:message key='label.button.anular' var="ANULAR" />
<fmt:message key='label.button.volver' var="VOLVER" />
<fmt:message key='label.button.enviar' var="ENVIAR" />
<fmt:message key='label.formCTC' var="FORM_CTC_LABEL" />
<fmt:message key='label.listadoDocAdjuntos' var="DOC_ADJ_LABEL" />
<fmt:message key='label.soportes' var="SOPORTES" />

<jsp:include page="./../../includes/header.jsp">
    <jsp:param name="includeMenu" value="true" />
</jsp:include>

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<c:set var="redireccionar" value="${redirec}" />
<c:set var="anulacion" value="${anul}" />
<button class="btn btn-link btn-sm collapse-button" onclick="collapseContent(this)" title="<fmt:message key='label.colapsarTodos' />"></button>
<div class="row">
	<div class=" col-lg-12">
		<div id="messages"></div>
		<div class="well">
			<form class="form-horizontal" id="informacionForm">
				<blockquote class="col-lg-12 collapsible-header"
					style="padding: 2px; font-size: 20px;">
					<strong> Detalle Solicitud </strong>
				</blockquote>
				<div class="collapsible-content">
					<jsp:include
						page="./../../bandejas/includes/informacionGeneral_Usuario.jsp">
						<jsp:param name="bandejaIps" value="true" />
					</jsp:include>
				</div>
			</form>
		</div>
	</div>
</div>
<c:if test="${bandejaIps.editable}">
	<div class="row">
		<div class=" col-lg-12">
			<div id="messages"></div>
			<div class="well form-horizontal">
				<fieldset>
					<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
						<strong>Informacion de Devolución</strong>
					</blockquote>
					<div class="form-group form-group-sm collapsible-content">
						<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="motivoDevolucion"><fmt:message
								key="label.devolucion.motivo" /></label>
						<div class="col-lg-4 col-sm-2 col-xs-6">
							<textarea class="form-control textarea" disabled="disabled" id="motivoDevolucion" rows="5" cols="40" maxlength="200">${bandejaIps.respuestaMotivoDevolucion}</textarea>
					    </div>
						<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="observacionCNA"><fmt:message
								key="label.observacionCTC" /></label>
						<div class="col-lg-4 col-sm-2 col-xs-6">
							<textarea class="form-control textarea" id="observacionCNA" disabled="disabled" rows="5" cols="40" maxlength="200">${bandejaIps.respuestaDevolucion}</textarea>
					    </div>
					</div>
					<div class="form-group form-group-sm collapsible-content">
					
					</div>
					<div class="form-group form-group-sm col-lg-12 collapsible-content">
						<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="respuestaDevolucion"><fmt:message
								key="label.devolucion.respuesta" /></label>
						<div class="col-lg-4 col-sm-2 col-xs-6">
							<textarea class="form-control textarea" id="respuestaDevolucion" rows="5" cols="40" maxlength="200"></textarea>
					    </div>
					    <div class="col-lg-6">
							<div class="panel panel-dropzone">
								<div class="panel-heading">Documentacion Complementaria (Si Aplica)</div>
								<div id="documentosComplementarios" class="panel-body dropzone"></div>
							</div>
					    </div>
					</div>
				</fieldset>
			</div>
            
            <fieldset class="well">
                <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                    <strong>${DOC_ADJ_LABEL}</strong>
                </blockquote>
                <div class="collapsible-content">
                    
                    <div class="form-group form-group-sm">
                        <div class="form-group form-group-sm col-lg-6">
                            <div class="form-group form-group-sm">
                                <label class="control-label control-label-sm text-right col-lg-6" style="text-align: left">${FORM_CTC_LABEL}</label>
                            </div>
                            <jsp:include page="./../../bandejas/includes/docAdjuntos.jsp">
                                <jsp:param name="docAdjuntosName" value="docAdjuntosItem" />
                            </jsp:include>
                            <br>
                            <div class="form-group form-group-sm">
                                <label class="control-label control-label-sm text-right col-lg-6" style="text-align: left">${SOPORTES}</label>
                            </div>
                            <jsp:include page="./../../bandejas/includes/docAdjuntos.jsp">
                                <jsp:param name="docAdjuntosName" value="docAdjuntos" />
                            </jsp:include>
                        </div>
                    </div>
                    <br />
                </div>
            </fieldset> 
		</div>
	</div>
</c:if>
<c:if test="${not empty bandejaIps.justificacion}">
    <div class="row">
        <div class=" col-lg-12">
            <div class="well form-horizontal">
                <fieldset>
                    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
                        <c:if test="${bandejaIps.aprobada}">
                            <strong>Justificación Aprobación</strong>
                        </c:if>

                        <c:if test="${not bandejaIps.aprobada}">
                            <strong>Justificación No Aprobación</strong>
                        </c:if>
                    </blockquote>
                    <div class=" col-lg-11">
                            <textarea class="form-control textarea" id="justificacion" rows="3" disabled="disabled">${bandejaIps.justificacion}</textarea>
                    </div>                    
                </fieldset>
            </div>
        </div>
    </div>
</c:if>
<div class="row">
	<div class=" col-lg-12">
		<div id="messages"></div>
		<div class="well form-horizontal">
			<fieldset>
				<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
					<strong>Diagnostico</strong>
				</blockquote>
				<div class="form-group form-group-sm collapsible-content">
					<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="diagnosticoCodigo"><fmt:message
							key="label.code" /></label>
					<div class=" col-lg-2 col-sm-2 col-xs-6">
						<input class="form-control input-sm" disabled="disabled" id="diagnosticoCodigo"	value="${bandejaIps.diagnostico.codigo}">
				    </div>
				</div>
				<div class="form-group form-group-sm collapsible-content">
					<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="diagnosticoDescripcion"><fmt:message
							key="label.description" /></label>
					<div class=" col-lg-6 col-sm-2 col-xs-6">
						<input class="form-control input-sm" disabled="disabled" id="diagnosticoDescripcion" value="${bandejaIps.diagnostico.descripcion}">
				    </div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
<c:choose>
	<c:when test="${bandejaIps.tipoItem == '1'}">
		<div class="row">
			<div class=" col-lg-12">
				<div id="messages"></div>
				<div class="well form-horizontal">
					<fieldset>
						<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
							<strong>Medicamento Solicitado</strong>
						</blockquote>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoCodigo"><fmt:message
									key="label.code" /></label>
							<div class=" col-lg-2 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="medicamentoCodigo" value="${bandejaIps.medicamento.codigo}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoNombre"><fmt:message
									key="label.name" /></label>
							<div class=" col-lg-6 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="medicamentoNombre" value="${bandejaIps.medicamento.nombre}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoCantidadSolicitada"><fmt:message
									key="label.infoSolicitud.cantidadSolicitada" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="medicamentoCantidadSolicitada" value="${bandejaIps.medicamento.cantidadSolicitada}">
						    </div>
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoDosis"><fmt:message
									key="label.infoSolicitud.dosis" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="medicamentoDosis" value="${bandejaIps.medicamento.dosis}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoViaAdministracion"><fmt:message
									key="label.viaAdministracion" /></label>
							<div class=" col-lg-2 col-sm-2 col-xs-6">
								<c:if test="${bandejaIps.editable}">
									<form:select path="bandejaIps.medicamento.viaAdministracionId" name="vias" id="medicamentoViaAdministracion" 
	                                    class="form-control input-sm">
	                                    <form:options items="${vias}" itemLabel="descripcion" itemValue="id" />
	                                </form:select>
								</c:if>
								<c:if test="${not bandejaIps.editable}">
									<input class="form-control input-sm" id="medicamentoViaAdministracion" value="${bandejaIps.medicamento.viaAdministracion}" disabled="disabled">
								</c:if>
						    </div>
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoDuracion"><fmt:message
									key="label.infoSolicitud.duracionTratamiento" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" <c:if test="${not bandejaIps.editable}">disabled="disabled"</c:if> id="medicamentoDuracion" value="${bandejaIps.medicamento.duracionTratamiento}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoPosologia"><fmt:message
									key="label.posologia" /></label>
							<div class=" col-lg-6 col-sm-2 col-xs-6">
								<textarea class="form-control textarea" rows="2" cols="40" <c:if test="${not bandejaIps.editable}">disabled="disabled"</c:if> id="medicamentoPosologia">${bandejaIps.medicamento.posologia}</textarea>
						    </div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${bandejaIps.tipoItem == '2'}">
		<div class="row">
			<div class=" col-lg-12">
				<div id="messages"></div>
				<div class="well form-horizontal">
					<fieldset>
						<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
							<strong>Procedimiento Solicitado</strong>
						</blockquote>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="procedimientoCodigo"><fmt:message
									key="label.code" /></label>
							<div class=" col-lg-2 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="procedimientoCodigo" value="${bandejaIps.procedimiento.codigo}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="procedimientoNombre"><fmt:message
									key="label.name" /></label>
							<div class=" col-lg-6 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="procedimientoNombre" value="${bandejaIps.procedimiento.nombre}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="procedimientoCantidadSolicitada"><fmt:message
									key="label.infoSolicitud.cantidadSolicitada" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="procedimientoCantidadSolicitada" value="${bandejaIps.procedimiento.cantidadSolicitada}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="procedimientoLateralidad"><fmt:message
									key="label.lateralidad" /></label>
							<div class=" col-lg-2 col-sm-4 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="procedimientoLateralidad" value="${bandejaIps.procedimiento.lateralidad}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="procedimientoIpsRemitente"><fmt:message
									key="label.solicitud.ipsRemitente" /></label>
							<div class=" col-lg-6 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="procedimientoIpsRemitente" value="${bandejaIps.procedimiento.ipsRemitente}">
						    </div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${bandejaIps.tipoItem == '3'}">
		<div class="row">
			<div class=" col-lg-12">
				<div id="messages"></div>
				<div class="well form-horizontal">
					<fieldset>
						<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
							<strong>Insumo Solicitado</strong>
						</blockquote>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="insumoCodigo"><fmt:message
									key="label.code" /></label>
							<div class=" col-lg-2 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="insumoCodigo" value="${bandejaIps.insumo.codigo}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="insumoNombre"><fmt:message
									key="label.name" /></label>
							<div class=" col-lg-6 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="insumoNombre" value="${bandejaIps.insumo.nombre}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="insumoCantidadSolicitada"><fmt:message
									key="label.infoSolicitud.cantidadSolicitada" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="insumoCantidadSolicitada" value="${bandejaIps.insumo.cantidadSolicitada}">
						    </div>
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="insumoDuracion"><fmt:message
									key="label.infoSolicitud.duracionTratamiento" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" <c:if test="${not bandejaIps.editable}">disabled="disabled"</c:if> id="insumoDuracion" value="${bandejaIps.insumo.duracionTratamiento}">
						    </div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</c:when>
</c:choose>
<c:if test="${redireccionar and not anulacion}">
<form id="idDireccionamientoForm">
<div class="row">
<div class="col-lg-12">
<div class="form-horizontal">
    <jsp:include page="./../../bandejas/includes/direccionamientoIps.jsp">
         <jsp:param value="true" name="editable" />
         <jsp:param value="${ipsTitulo}" name="ipsTitulo" />
    </jsp:include>
    </div>
    </div>
</div>
</form>
</c:if>

<c:if test="${not redireccionar and anulacion}">
<form id="idJustificacionForm">
	<fieldset class="well">
	<div class="row">
<div class="col-lg-12">
<div class="form-horizontal">
<div class="form-group form-group-sm">
					<div class="col-lg-3">
						<label for="idJustificacion"
							class="control-label control-label-sm text-right ">Justificacion</label>
					</div>
					<div class="col-lg-9">
						<input class="form-control input-sm" id="idJustificacion"
							name="idJustificacion" placeholder="Justificacion" value="" />
					</div>
				</div>
		</div>
		</div>
		</div>
	</fieldset>
	</form>
</c:if>

<div class="well-sm text-right clearfix" style="padding: 20px">
	<div class="row">
		<c:if test="${bandejaIps.editable}">
			<div class="col-lg-offset-8 col-lg-2">
				<button type="button" id="enviar" class="btn btn-success btn-block">${ENVIAR}</button>
			</div>
			<div class="col-lg-2">
				<button type="button" id="cancelar" class="btn btn-danger btn-block">${VOLVER}</button>
			</div>
		</c:if>
        
		<c:if test="${bandejaIps.autorizada and not redireccionar and not anulacion and permiteConsumo}">
			<div class="col-lg-offset-8 col-lg-2">
				<button type="button" id="preConsumir" class="btn btn-success btn-block" data-toggle="modal" href="#formularioConsumir">Consumir</button>
			</div>
			<div class="col-lg-2">
				<button type="button" id="cancelar" class="btn btn-danger btn-block">${VOLVER}</button>
			</div>
		</c:if>
		<c:if test="${not anulacion and not redireccionar and not bandejaIps.autorizada and not bandejaIps.editable}">
			<div class="col-lg-offset-10 col-lg-2">
				<button type="button" id="cancelar" class="btn btn-danger btn-block">${VOLVER}</button>
			</div>
		</c:if>
        <c:if test="${redireccionar and not anulacion}">
            <div class="col-lg-offset-8 col-lg-2">
                <button type="button" id="redireccionar" class="btn btn-success btn-block" onclick="funcionalidadRedirecion()">Redireccionar</button>
            </div>
            <div class="col-lg-2">
                <button type="button" id="cancelar" class="btn btn-danger btn-block">${VOLVER}</button>
            </div>
        </c:if>
        <c:if test="${not redireccionar and anulacion}">
            <div class="col-lg-offset-8 col-lg-2">
                <button type="button" id="anularItem" class="btn btn-success btn-block" onclick="funcionalidadAnulacion()">${ANULAR}</button>
            </div>
            <div class="col-lg-2">
                <button type="button" id="cancelar" class="btn btn-danger btn-block">${VOLVER}</button>
            </div>
        </c:if>
	</div>
</div>
<div id="alert-area" class="alert-messages col-lg-9-5 row" style="display: none;"></div>
<c:if test="${redireccionar}">
	<input type="hidden" value="${itemId}" id="itemId" name="itemId" />
</c:if>
<c:if test="${bandejaIps.autorizada and not redireccionar}">
	<%@include file="./formularioConsumir.jsp"%>
</c:if>
<%@include file="./../../includes/footer.jsp"%>

<script>
var complementariosDZ=null;

    $(document).ready(function() { 
    	$('#itemId').val=$('#numeroSolicitudInput').val();
    	Dropzone.autoDiscover = false;
    	<c:if test="${bandejaIps.editable}">
	    	$("#enviar").click(function(){
				$.post("${webContext}/prestador/enviarItem", buildDataEnviar(), callbackEnviar);
			});
	    </c:if>
    	$("#cancelar").click(function(){
    		window.history.back();
		});
		try{
			complementariosDZ = new Dropzone("#documentosComplementarios", {
				url : "${webContext}/prestador/upload/docComplementarios"
			});
		}catch(excep){
			console.log(excep);
		}

    });
	function buildDataEnviar() {

		data = {
			nroSolicitud : $('#numeroSolicitudInput').val(),
			respuestaIps : $('#respuestaDevolucion').val(),
			dosisFrecuencia : $('#medicamentoDosis').val(),
			viaAdministracion : $('#medicamentoViaAdministracion').val(),
			duracionTratamiento : $('#medicamentoDuracion').val(),
			posologia : $('#medicamentoPosologia').val(),
			tipoItem : '${bandejaIps.tipoItem}'

		}
		return data;
	}
	function callbackEnviar(data) {
		if (data.generalErrors.length > 0) {
			$("#messages").empty();
			createErrorMessages($("#messages"), data.generalErrors);
			window.location = "#";
		} else {
			location.href = '${pageContext.request.contextPath}/bandejas/bandejaIpsMedico';
		}
	}
	function descargarArchivo(nombreArchivoOriginal, nombreArchivoServidor) {
		$.get("${webContext}/prestador/buscarArchivo/" + nombreArchivoOriginal
				+ "/" + nombreArchivoServidor, function(existe) {
			if (existe)
				location.href = "${webContext}/prestador/download/"
						+ nombreArchivoOriginal + "/" + nombreArchivoServidor;
			else {
				createErrorMessages($("#messages"), new Array(
						"El archivo no esta disponible para su descarga."));
				window.location = "#";
			}

		});
	}
	
	function funcionalidadRedirecion() {
		
		$('#idDireccionamientoForm')
        .validate(
                {
                    onfocusout : false,
                    focusInvalid : false,
                    focusCleanup : false,
                    onkeyup : false,
                    onclick : false,
                });
		
	   jQuery.validator
				.addMethod(
						"equalTo",
						function(value, element, param) {
							return !esNuloidSedeIps();
						},
						'<fmt:message key="validation.required2"><fmt:param value="${VARIABLE_IPS}"/></fmt:message>');

	   $("#nombreIps").rules('add', {
		   equalTo : true,
       });

		if ($('#idDireccionamientoForm').valid()) {
			obtenerIps();
		}
	}
	function funcionalidadAnulacion() {
		$('#idJustificacionForm')
				.validate(
						{
							onfocusout : false,
							focusInvalid : false,
							focusCleanup : false,
							onkeyup : false,
							onclick : false,
							rules : {
								idJustificacion : "required"
							},
							messages : {
								idJustificacion : {
									required : '<fmt:message key="validation.required"><fmt:param value="${VARIABLE_JUSTIFICACION}"/></fmt:message>'
								}
							},
						});

		if ($('#idJustificacionForm').valid()) {
			$.ajax({
				type : 'POST',
				async : false,
				url : "${webContext}/prestador/funcionalidadAnulacion/"
						+ $('#numeroSolicitudInput').val(),
				data : $("#idJustificacion").serializeArray(),
				success : function(response) {
         			location.href = "${webContext}/bandejas/${nombreBandeja}";
				}
			});
		}
	}
	function obtenerIps() {
		$.ajax({
			type : 'POST',
			async : false,
			url : "${webContext}/prestador/funcionalidadRedireccionamiento/"
					+ $('#numeroSolicitudInput').val(),
			data : $("#idSedeIps").serializeArray(),
			success : function(response) {
    			 if (response.generalErrors.length > 0) {
                     newAlert('danger', response.generalErrors, 2000, 'icon-warning-sign');
                 }
    			 else{
         			location.href = "${webContext}/bandejas/${nombreBandeja}";
    			 }
			}

		});
	}
	function esNuloidSedeIps() {
		cadenaIn = $("#idSedeIps").val();
		if (cadenaIn == '' || cadenaIn == undefined)
			return true;
		return false;
	}
</script>